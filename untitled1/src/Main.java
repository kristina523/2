import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

abstract class FileManager {
    public abstract void writeToFile(String fileName, String content) throws IOException;

    public abstract String readFromFile(String fileName) throws IOException;

    public abstract void deleteFile(String fileName);
}

class FileManagerImpl extends FileManager {
    @Override
    public void writeToFile(String fileName, String content) throws IOException {
        File file = new File(fileName);
        FileWriter writer = new FileWriter(file);
        writer.write(content);
        writer.close();
    }

    @Override
    public String readFromFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new IOException("Файл не найден");
        }
        StringBuilder content = new StringBuilder();
        java.util.Scanner scanner = new java.util.Scanner(file);
        while (scanner.hasNextLine()) {
            content.append(scanner.nextLine());
        }
        scanner.close();
        return content.toString();
    }

    @Override
    public void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        FileManager fileManager = new FileManagerImpl();

        String fileName = "example.txt";
        String content = "Hello, World!";

        try {
            fileManager.writeToFile(fileName, content);
            System.out.println("Файл успешно записан");

            String readContent = fileManager.readFromFile(fileName);
            System.out.println("Содержимое файла: " + readContent);

            fileManager.deleteFile(fileName);
            System.out.println("Файл успешно удален");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}