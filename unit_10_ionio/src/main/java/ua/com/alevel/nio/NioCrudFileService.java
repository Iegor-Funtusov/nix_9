package ua.com.alevel.nio;

import ua.com.alevel.CrudFileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class NioCrudFileService implements CrudFileService {

    @Override
    public void create(String filePath, boolean isFolder) {
        Path path = Paths.get(filePath);
        try {
            if (!Files.exists(path)) {
                Path temp = Files.createTempDirectory(filePath);
                System.out.println("temp = " + temp.toString());
//                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String filePath, String newFilePath, boolean isFolder) {
        Path path = Paths.get(filePath);
        try {
            Files.move(path, Paths.get(newFilePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String filePath) {
        Path path = Paths.get(filePath);
//        Files.de
    }

    @Override
    public void read(String filePath, boolean isFolder) {

    }
}
