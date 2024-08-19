package br.com.rzaninelli.CliniConect.service.upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
public class UploadServiceImpl implements IUploadService{
    private static final Logger log = LoggerFactory.getLogger(UploadServiceImpl.class);

    @Override
    public String upload(MultipartFile arquivo) {
        try {
            log.debug("Realizando Upload do arquivo" + arquivo.getOriginalFilename());
            String pastaDestino = "C:\\Projetos\\Angular\\cliniconect-front\\src\\assets\\media";
            String extensao = arquivo.getOriginalFilename().substring(arquivo.getOriginalFilename().lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString() + extensao;

            Path path = Paths.get(pastaDestino + File.separator + newFileName);
            Files.copy(arquivo.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return newFileName;

        }catch (IOException e) {
            //TODO 05/08/2024 rhzan: melhorar saida da exception
            e.printStackTrace();
        }
        return "";
    }
}
