package com.ehu.task1.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    public static Properties readConfig(String filepath) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(filepath);
        properties.load(fileInputStream);
        return properties;
    }
}

