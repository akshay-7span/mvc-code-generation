package com.mvc_code_generation.codegen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class RepositoryGenerator {

    public static void generateRepository(String basePackage, String tableName) throws IOException {
        String className = tableName.substring(0, 1).toUpperCase() + tableName.substring(1);
        String repositoryName = className + "Repository";
        String packagePath = basePackage.replace('.', '/');
        String directoryPath = Paths.get("src", "main", "java", packagePath, "repository").toString();
        String filePath = Paths.get(directoryPath, repositoryName + ".java").toString();

        // Ensure the directory exists
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        FileWriter writer = new FileWriter(filePath);

        // Write the package declaration
        writer.write("package " + basePackage + ".repository;\n\n");

        writer.write("import org.springframework.data.jpa.repository.JpaRepository;\n");
        writer.write("import " + basePackage + ".entity." + className + ";\n\n");

        writer.write("public interface " + repositoryName + " extends JpaRepository<" + className + ", Long> {}");

        writer.close();
    }
}
