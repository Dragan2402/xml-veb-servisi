package com.users.util;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

public class Utility {

    private static BigInteger submission_number = BigInteger.valueOf(0);

    public static long getNextId(){
        return ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE);
    }

    public static BigInteger getNextSubmissionNumber(){
        submission_number = submission_number.add(BigInteger.ONE);
        return submission_number;
    }

    public static String getDescriptionFileName(String extension){
        return "desc_file"+Long.toString(getNextId())+"."+extension;
    }

    public static void saveFile(String path, byte[] bytes){
        try {
            File saveFile = new File(path);
            FileOutputStream stream = new FileOutputStream(saveFile);
            stream.write(bytes);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFileName(MultipartFile file, boolean isDescription){
        String folder = isDescription? "descriptionFiles":"exampleFiles";
        String file_descriptor = isDescription? "desc_file_" : "example_file_";

        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);

        return file_descriptor+Long.toString(getNextId())+"."+fileExtension;
    }
}
