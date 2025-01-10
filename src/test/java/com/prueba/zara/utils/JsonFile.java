package com.prueba.zara.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

public class JsonFile {

    public static String loadJsonFromFile(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);
        byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
