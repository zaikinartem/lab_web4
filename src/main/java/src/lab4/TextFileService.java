package src.lab4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Абстрактный класс для чтения и записи текстовых файлов.
 *
 * @author User
 * @version 1.0
 */
public abstract class TextFileService {

    /**
     * Читает все строки файла.
     *
     * @param path путь к файлу
     * @return список строк
     * @throws NoSuchFileException если файл не найден
     * @throws IOException         если ошибка чтения
     */
    public static List<String> readLines(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new NoSuchFileException(path.toAbsolutePath().toString(), null, "файл не найден");
        }
        List<String> lines = new ArrayList<>();
        BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } finally {
            reader.close();
        }
        return lines;
    }

    /**
     * Записывает строки в файл в порядке итератора.
     *
     * @param path  путь к файлу результата
     * @param lines коллекция строк
     * @throws IOException если ошибка записи
     */
    public static void writeLines(Path path, Iterable<MyString> lines) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
        try {
            for (MyString myString : lines) {
                writer.write(myString.getValue());
                writer.newLine();
            }
        } finally {
            writer.close();
        }
    }
}