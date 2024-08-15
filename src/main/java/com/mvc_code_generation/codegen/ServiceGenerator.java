package com.mvc_code_generation.codegen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class ServiceGenerator {

    public static void generateService(String basePackage, String tableName) throws IOException {
        String className = tableName.substring(0, 1).toUpperCase() + tableName.substring(1);
        String serviceName = className + "Service";
        String packagePath = basePackage.replace('.', '/');
        String directoryPath = Paths.get("src", "main", "java", packagePath, "service").toString();
        String filePath = Paths.get(directoryPath, serviceName + ".java").toString();

        // Ensure the directory exists
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        FileWriter writer = new FileWriter(filePath);

        // Write the package declaration
        writer.write("package " + basePackage + ".service;\n\n");

        writer.write("import java.util.List;\n");
        writer.write("import " + basePackage + ".entity." + className + ";\n");
        writer.write("import " + basePackage + ".repository." + className + "Repository;\n");
        writer.write("import org.springframework.beans.factory.annotation.Autowired;\n");
        writer.write("import org.springframework.stereotype.Service;\n\n");

        writer.write("@Service\n");
        writer.write("public class " + serviceName + " {\n");

        writer.write("    @Autowired\n");
        writer.write("    private " + className + "Repository repository;\n");

        writer.write("    public List<" + className + "> getAll() {\n");
        writer.write("        return repository.findAll();\n");
        writer.write("    }\n");

        writer.write("    public " + className + " getById(Long id) {\n");
        writer.write("        return repository.findById(id).orElse(null);\n");
        writer.write("    }\n");

        writer.write("    public " + className + " save(" + className + " entity) {\n");
        writer.write("        return repository.save(entity);\n");
        writer.write("    }\n");

        writer.write("    public " + className + " update(Long id, " + className + " entity) {\n");
        writer.write("        if (repository.existsById(id)) {\n");
        writer.write("            entity.setId(id);\n");
        writer.write("            return repository.save(entity);\n");
        writer.write("        } else {\n");
        writer.write("            return null;\n");
        writer.write("        }\n");
        writer.write("    }\n");

        writer.write("    public void delete(Long id) {\n");
        writer.write("        repository.deleteById(id);\n");
        writer.write("    }\n");

        writer.write("}\n");
        writer.close();
    }
}
