package com.pri.aa.enigma.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Optional;

@Service
public class PictureService {

    @Value("${proj.basedir}")
    private String projectPath;
    private String avatarsPath = "/src/main/resources/static/img/ava/";

    public Optional<String> picturePathToString (String filePath) {
        Optional<String> picture;
        try {
            picture = Optional.of(
                    Base64Utils.encodeToString(
                            new FileInputStream(
                                    new File(projectPath + avatarsPath + filePath)
                            ).readAllBytes()
                    )
            );
        } catch (Exception e) {
            picture = Optional.empty();
        }

        return picture;
    }

    public Optional<String> getPictureString(String picturePath) {
        Optional<String> picture = picturePathToString(picturePath);
        if (!picture.isPresent()) {
            picture = picturePathToString("nophoto.png");
        }

        return picture;
    }
}
