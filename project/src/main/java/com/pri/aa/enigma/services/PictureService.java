package com.pri.aa.enigma.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

@Service
public class PictureService {

    @Value("${proj.basedir}")
    private String projectPath;
    private String pathToImgDir = "/src/main/resources/static/img/";
    private String avaDir = "/ava/";
    private String enigmaDir = "/enigma/";

    public Optional<String> picturePathToString (String imgDir, String imgName) {
        Optional<String> picture;
        try {
            picture = Optional.of(
                    Base64Utils.encodeToString(
                            new FileInputStream(
                                    new File(projectPath + pathToImgDir + imgDir + imgName)
                            ).readAllBytes()
                    )
            );
        } catch (Exception e) {
            picture = Optional.empty();
        }

        return picture;
    }

    public Optional<String> getPictureString(String imgDir, String imgName) {
        Optional<String> picture = picturePathToString(imgDir, imgName);
        if (!picture.isPresent()) {
            picture = picturePathToString(imgDir,"nophoto.png");
        }

        return picture;
    }

    public Optional<String> savePictureLocaly(MultipartFile file, String imgDir) {

        String uploadPath = projectPath + pathToImgDir + imgDir;

        try {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String filePath = uuidFile + file.getOriginalFilename()
                    .substring(file.getOriginalFilename().lastIndexOf('.'));

            Files.write(Path.of(uploadPath + filePath), file.getBytes());

            return Optional.of(filePath);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public String getAvaDir() {
        return avaDir;
    }

    public String getEnigmaDir() {
        return enigmaDir;
    }
}
