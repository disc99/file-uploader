package io.disc99.demo.datasource;

import io.disc99.demo.model.UploadFiles;
import io.disc99.demo.model.UploadRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UploadS3 implements UploadRepository {
    @Override
    public void save(UploadFiles uploadFiles) {

    }
}
