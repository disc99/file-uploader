package io.disc99.demo.handler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import io.disc99.demo.model.UploadFile;
import io.disc99.demo.model.UploadFiles;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.MemoryFileUpload;
import org.apache.commons.codec.binary.Base64;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class UploadFileFactory {

    public static UploadFiles create(APIGatewayProxyRequestEvent event) {
        var request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, event.getHeaders().get("origin"));
        request.headers().add("content-type", event.getHeaders().get("content-type"));
        request.content().writeBytes(Base64.decodeBase64(event.getBody().getBytes()));
        var decoder = new HttpPostRequestDecoder(new DefaultHttpDataFactory(false), request);
        return decoder.getBodyHttpDatas().stream()
                .filter(data -> MemoryFileUpload.class.isAssignableFrom(data.getClass()))
                .map(data -> {
                    MemoryFileUpload upload = (MemoryFileUpload) data;
                    return new UploadFile(new MultipartMemoryFileUpload(upload));
                }).collect(collectingAndThen(toList(), UploadFiles::new));
    }
}
