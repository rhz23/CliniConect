package br.com.rzaninelli.CliniConect.controller;

import br.com.rzaninelli.CliniConect.dto.PathToFile;
import br.com.rzaninelli.CliniConect.service.upload.IUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
public class UploadController {

    @Autowired
    private IUploadService uploadService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam(name = "arquivo") MultipartFile arquivo) {
        String nomeArquivo = uploadService.upload(arquivo);
        if (nomeArquivo != null) {
            return ResponseEntity.status(201).body(new PathToFile(nomeArquivo));
        }
        return ResponseEntity.badRequest().build();
    }
}
