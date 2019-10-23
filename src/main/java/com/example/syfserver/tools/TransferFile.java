package com.example.syfserver.tools;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class TransferFile {
    public static File MultipartFileToFile(MultipartFile multipartFile) {
        File file = null;
        try {
            file = File.createTempFile("tmp", null);
            multipartFile.transferTo(file);
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
