package io.disc99.demo.model;

import lombok.AllArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@ToString
public class UploadFiles {
    @NotNull
    @Valid
    List<UploadFile> list;

    public List<UploadFile> list() {
        return list;
    }
}
