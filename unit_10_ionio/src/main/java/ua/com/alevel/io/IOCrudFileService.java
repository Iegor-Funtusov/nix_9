package ua.com.alevel.io;

import org.apache.commons.io.FileUtils;
import ua.com.alevel.CrudFileService;

import java.io.File;
import java.io.IOException;

public class IOCrudFileService implements CrudFileService {

    @Override
    public void create(String filePath, boolean isFolder) {
        File file = new File(filePath);
        if (!isFolder) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            file.mkdirs();
        }
    }

    @Override
    public void update(String filePath, String newFilePath, boolean isFolder) {
        File file = new File(filePath);
        file.renameTo(new File(newFilePath));
    }

    @Override
    public void delete(String filePath) {
        File file = new File(filePath);
//        delete(file);
        try {
            FileUtils.deleteDirectory(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void delete(File root) {
        File[] files = root.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                delete(file);
                file.delete();
            } else {
                file.delete();
            }
        }
        root.delete();
    }

    @Override
    public void read(String filePath, boolean isFolder) {

    }

    private static void setHiddenAttrib(File file) {
        try {
            Process p = Runtime.getRuntime().exec("attrib +H " + file.getPath());
            p.waitFor();
            if (file.isHidden()) {
                System.out.println(file.getName() + " hidden attribute is set to true");
            } else {
                System.out.println(file.getName() + " hidden attribute not set to true");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
