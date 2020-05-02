package io.disc99.demo.usecase;

import io.disc99.demo.model.UploadFiles;
import io.disc99.demo.model.UploadRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@AllArgsConstructor
@Validated
public class UploadService {

    UploadRepository uploadRepository;

    public void upload(@Valid UploadFiles uploadFiles) {
        uploadRepository.save(uploadFiles);
    }
}
