package io.disc99.demo.handler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UploadFileFactoryTest {

    @Test
    void test() {
        var event = createEvent("request.json");
        var files = UploadFileFactory.create(event);

        assertEquals(2, files.list().size());

        files.list().forEach(file -> {
            try {
                assertNotNull(file);
                var path = getClass().getClassLoader().getResource(".").getPath();
                var result = new File(path + "/" + UUID.randomUUID().toString() + ".bin");
                result.createNewFile();
                file.transferTo(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private APIGatewayProxyRequestEvent createEvent(String request) {
        try {
            Path path = Paths.get(getClass().getClassLoader().getResource(request).toURI());
            String json = Files.lines(path).collect(joining("\n"));
            return new ObjectMapper().readValue(json, APIGatewayProxyRequestEvent.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
