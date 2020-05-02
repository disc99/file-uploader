package io.disc99.demo.handler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import io.disc99.demo.model.UploadFiles;
import io.disc99.demo.usecase.UploadService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Function;


@Component
@Slf4j
@AllArgsConstructor
public class UploadFunction implements Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>  {

    UploadService uploadService;

    @SneakyThrows
    @Override
    public APIGatewayProxyResponseEvent apply(APIGatewayProxyRequestEvent request) {
        log.info("Event: {} ", request);

        UploadFiles uploadFiles = UploadFileFactory.create(request);
        uploadService.upload(uploadFiles);

        return new APIGatewayProxyResponseEvent()
            .withStatusCode(200)
            .withBody("{}");
    }
}
