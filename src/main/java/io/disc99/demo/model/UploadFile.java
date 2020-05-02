package io.disc99.demo.model;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@AllArgsConstructor
public class UploadFile {
    MultipartFile file;

    @SneakyThrows
    public void transferTo(File dest) {
        file.transferTo(dest);
    }
}
