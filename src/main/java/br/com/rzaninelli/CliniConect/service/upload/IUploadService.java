package br.com.rzaninelli.CliniConect.service.upload;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {

    public String upload(MultipartFile arquivo);
}
