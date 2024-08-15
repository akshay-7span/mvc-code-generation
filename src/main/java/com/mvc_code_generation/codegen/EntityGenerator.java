package com.mvc_code_generation.codegen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class EntityGenerator {

    public static void generateEntity(String basePackage, String tableName, String[] columns) throws IOException {
        String className = tableName.substring(0, 1).toUpperCase() + tableName.substring(1);
        String packagePath = basePackage.replace('.', '/');
        String directoryPath = Paths.get("src", "main", "java", packagePath, "entity").toString();
        String filePath = Paths.get(directoryPath, className + ".java").toString();

        // Ensure the directory exists
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        FileWriter writer = new FileWriter(filePath);

        writer.write("package " + basePackage + ".entity;\n\n");
        writer.write("import jakarta.persistence.*;\n");
        writer.write("import lombok.Getter;\n");
        writer.write("import lombok.Setter;\n");
        writer.write("import java.util.*;\n\n");

        writer.write("@Getter\n@Setter\n@Entity\n");
        writer.write("public class " + className + " {\n");

        // Generate fields based on the provided columns
        for (String column : columns) {
            String[] parts = column.split("\\s+");
            String fieldName = parts[0];
            String fieldType = getFieldType(parts[1]);

            if (fieldName.equals("id")) {
                writer.write("    @Id\n");
                writer.write("    @GeneratedValue(strategy = GenerationType.IDENTITY)\n");
            }

            writer.write("    private " + fieldType + " " + fieldName + ";\n");
        }

        writer.write("}\n");
        writer.close();
    }

    private static String getFieldType(String sqlType) {
        switch (sqlType.toUpperCase()) {
            case "BIGINT":
                return "Long";
            case "VARCHAR(255)":
                return "String";
            case "BOOLEAN":
                return "boolean";
            case "TIMESTAMP":
                return "LocalDateTime";
            case "INT":
                return "int";
            case "DOUBLE":
                return "double";
            default:
                if (sqlType.startsWith("VARCHAR")) {
                    return "String";
                }
                throw new IllegalArgumentException("Unsupported SQL type: " + sqlType);
        }
    }
}
