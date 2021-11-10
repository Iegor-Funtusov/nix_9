package ua.com.alevel;

public interface CrudFileService {

    void create(String filePath, boolean isFolder);
    void update(String filePath, String newFilePath, boolean isFolder);
    void delete(String filePath);
    void read(String filePath, boolean isFolder);
}
