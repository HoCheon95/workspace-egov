package kr.or.ddit.multipart;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FileUploadRequest {
    @NotBlank
    private String uploader;
    // @MultipartNotEmpty
    // @NotNull
    private MultipartFile uploadFile;
    public void setUploadFile(MultipartFile uploadFile) {
        if(uploadFile == null || uploadFile.isEmpty()) {
            return;
        }
        this.uploadFile = uploadFile;
    }
}
