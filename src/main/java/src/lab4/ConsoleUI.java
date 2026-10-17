package src.lab4;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Консольный интерфейс программы.
 * <p>
 * Класс выполняет задание целиком: запрашивает у пользователя пути к файлам,
 * читает строки исходного файла в стек, записывает их в файл результата
 * в обратном порядке и выводит статистику. Весь ввод с клавиатуры и весь
 * вывод на экран в программе выполняются только через этот класс,
 * а работа с файлами поручена {@link TextFileService}.
 *
 * @author User
 * @version 1.0
 */
public class ConsoleUI {

    /** Путь к исходному файлу, используемый при пустом вводе. */
    private static final String DEFAULT_INPUT = "input.txt";

    /** Путь к файлу результата, используемый при пустом вводе. */
    private static final String DEFAULT_OUTPUT = "output.txt";

    /** Сканер для чтения ввода пользователя с клавиатуры. */
    private final Scanner scanner;

    /**
     * Конструктор по умолчанию.
     */
    public ConsoleUI() {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
    }

    /**
     * Выполняет задание.
     */
    public void run() {
        Path input = askPath("Введите путь к исходному файлу", DEFAULT_INPUT);
        Path output = askPath("Введите путь к файлу результата", DEFAULT_OUTPUT);

        try {
            List<String> lines = TextFileService.readLines(input);

            if (lines.isEmpty()) {
                System.out.println("Внимание: исходный файл пуст.");
            }

            MyString.clear();
            for (String line : lines) {
                new MyString(line).push();
            }

            TextFileService.writeLines(output, () -> MyString.iterator());

            showStack();
            showStats(MyString.size(), MyString.maxLength());
            System.out.println("Строки в обратном порядке сохранены в файл: "
                    + output.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Ошибка работы с файлом: " + e.getMessage());
        }
    }

    /**
     * Запрашивает у пользователя путь к файлу.
     *
     * @param prompt       текст приглашения
     * @param defaultValue путь по умолчанию
     * @return путь к файлу
     */
    private Path askPath(String prompt, String defaultValue) {
        System.out.print(prompt + " [" + defaultValue + "]: ");
        String input = scanner.hasNextLine() ? scanner.nextLine().trim() : "";
        return Path.of(input.isEmpty() ? defaultValue : input);
    }

    /**
     * Выводит содержимое стека от вершины к дну.
     */
    private void showStack() {
        System.out.println("Содержимое стека (от вершины к дну):");
        Iterator<MyString> iterator = MyString.iterator();
        int position = 1;
        while (iterator.hasNext()) {
            System.out.println("  " + position + ": " + iterator.next().getValue());
            position++;
        }
    }

    /**
     * Выводит статистику.
     *
     * @param lineCount количество строк
     * @param maxLength длина самой большой строки
     */
    private void showStats(int lineCount, int maxLength) {
        System.out.println("Количество строк: " + lineCount);
        System.out.println("Длина самой большой строки: " + maxLength);
    }
}