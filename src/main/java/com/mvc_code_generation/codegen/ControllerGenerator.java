package com.mvc_code_generation.codegen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class ControllerGenerator {

    public static void generateController(String basePackage, String tableName) throws IOException {
        String className = tableName.substring(0, 1).toUpperCase() + tableName.substring(1);
        String controllerName = className + "Controller";
        String packagePath = basePackage.replace('.', '/');
        String directoryPath = Paths.get("src", "main", "java", packagePath, "controller").toString();
        String filePath = Paths.get(directoryPath, controllerName + ".java").toString();

        // Ensure the directory exists
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        FileWriter writer = new FileWriter(filePath);

        // Write the package declaration
        writer.write("package " + basePackage + ".controller;\n\n");

        writer.write("import java.util.List;\n");
        writer.write("import " + basePackage + ".entity." + className + ";\n");
        writer.write("import " + basePackage + ".service." + className + "Service;\n");
        writer.write("import org.springframework.beans.factory.annotation.Autowired;\n");
        writer.write("import org.springframework.web.bind.annotation.DeleteMapping;\n");
        writer.write("import org.springframework.web.bind.annotation.GetMapping;\n");
        writer.write("import org.springframework.web.bind.annotation.PathVariable;\n");
        writer.write("import org.springframework.web.bind.annotation.PostMapping;\n");
        writer.write("import org.springframework.web.bind.annotation.PutMapping;\n");
        writer.write("import org.springframework.web.bind.annotation.RequestBody;\n");
        writer.write("import org.springframework.web.bind.annotation.RequestMapping;\n");
        writer.write("import org.springframework.web.bind.annotation.RestController;\n\n");

        writer.write("@RestController\n");
        writer.write("@RequestMapping(\"/" + tableName.toLowerCase() + "\")\n");
        writer.write("public class " + controllerName + " {\n");

        writer.write("    @Autowired\n");
        writer.write("    private " + className + "Service service;\n");

        writer.write("\n    @GetMapping\n");
        writer.write("    public List<" + className + "> getAll() {\n");
        writer.write("        return service.getAll();\n");
        writer.write("    }\n");

        writer.write("\n    @GetMapping(\"/{id}\")\n");
        writer.write("    public " + className + " getById(@PathVariable Long id) {\n");
        writer.write("        return service.getById(id);\n");
        writer.write("    }\n");

        writer.write("\n    @PostMapping\n");
        writer.write("    public " + className + " create(@RequestBody " + className + " entity) {\n");
        writer.write("        return service.save(entity);\n");
        writer.write("    }\n");

        writer.write("\n    @PutMapping(\"/{id}\")\n");
        writer.write("    public " + className + " update(@PathVariable Long id, @RequestBody " + className + " entity) {\n");
        writer.write("        return service.update(id, entity);\n");
        writer.write("    }\n");

        writer.write("\n    @DeleteMapping(\"/{id}\")\n");
        writer.write("    public void delete(@PathVariable Long id) {\n");
        writer.write("        service.delete(id);\n");
        writer.write("    }\n");

        writer.write("}\n");
        writer.close();
    }
}
