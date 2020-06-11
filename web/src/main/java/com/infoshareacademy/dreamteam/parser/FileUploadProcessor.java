package com.infoshareacademy.dreamteam.parser;

import javax.enterprise.context.ApplicationScoped;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@ApplicationScoped
public class FileUploadProcessor {

    public File uploadFile(Part filePart) throws IOException {
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        File file = new File("/tmp/" + fileName);
        Files.deleteIfExists(file.toPath());

        InputStream fileContent = filePart.getInputStream();

        Files.copy(fileContent, file.toPath());

        fileContent.close();
        return file;
    }

}
