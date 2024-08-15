package com.mvc_code_generation.codegen;

import java.io.IOException;
import java.util.Arrays;

public class CodeGenerator {

    public static void main(String[] args) throws IOException {
        String basePackage = "com.mvc_code_generation"; // You can make this dynamic as needed

        String[] ddls = {
                "CREATE TABLE User (\n" +
                        "  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                        "  username VARCHAR(255) NOT NULL,\n" +
                        "  email VARCHAR(255) NOT NULL,\n" +
                        "  password VARCHAR(255) NOT NULL,\n" +
                        "  isActive BOOLEAN DEFAULT FALSE,\n" +
                        "  otp VARCHAR(255),\n" +
                        "  isVerified BOOLEAN DEFAULT FALSE\n" +
                        ");\n"
        };

        for (String ddl : ddls) {
            String tableName = parseTableName(ddl);
            String[] columns = parseColumns(ddl);

            EntityGenerator.generateEntity(basePackage, tableName, columns);
            RepositoryGenerator.generateRepository(basePackage, tableName);
            ServiceGenerator.generateService(basePackage, tableName);
            ControllerGenerator.generateController(basePackage, tableName);
        }
    }

    private static String parseTableName(String ddl) {
        String[] parts = ddl.split("\\s+");
        return parts[2];
    }

    private static String[] parseColumns(String ddl) {
        String columnsPart = ddl.substring(ddl.indexOf("(") + 1, ddl.lastIndexOf(")"));
        return Arrays.stream(columnsPart.split(",\\n")) // Split by new line and comma
                .map(String::trim)
                .toArray(String[]::new);
    }
}
