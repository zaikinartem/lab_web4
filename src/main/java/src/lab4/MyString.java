package src.lab4;

import java.util.Iterator;
import java.util.Stack;

/**
 * Модель строки с сохранением её длины и контейнер для всех строк.
 * <p>
 * Класс хранит одну строку ({@code value}), её длину ({@code length})
 * и общую для всех объектов коллекцию ({@code stack}), реализованную
 * на основе {@link java.util.Stack}.
 * <p>
 * Предоставляет операции добавления строки в стек, обхода стека
 * в обратном порядке (LIFO) и получения статистики.
 *
 * @author User
 * @version 1.0
 */
public class MyString {

    /** Строка. */
    private String value;

    /** Длина строки. */
    private int length;

    /** Общий стек всех строк. */
    private static final Stack<MyString> stack = new Stack<>();

    /**
     * Конструктор по умолчанию. Создаёт пустую строку.
     */
    public MyString() {
        this.value = "";
        this.length = 0;
    }

    /**
     * Создаёт строку с заданным значением.
     *
     * @param value значение строки
     */
    public MyString(String value) {
        this.value = value;
        this.length = value.length();
    }

    /**
     * Добавляет текущую строку в стек.
     */
    public void push() {
        stack.push(this);
    }

    /**
     * Извлекает строку с вершины стека.
     *
     * @return строка с вершины стека
     */
    public static MyString pop() {
        return stack.pop();
    }

    /**
     * Возвращает строку с вершины стека, не извлекая её.
     *
     * @return строка с вершины стека
     */
    public static MyString peek() {
        return stack.peek();
    }

    /**
     * Проверяет, пуст ли стек.
     *
     * @return {@code true}, если стек пуст
     */
    public static boolean isEmpty() {
        return stack.empty();
    }

    /**
     * Возвращает количество строк в стеке.
     *
     * @return количество элементов
     */
    public static int size() {
        return stack.size();
    }

    /**
     * Возвращает длину самой большой строки в стеке.
     *
     * @return максимальная длина или 0, если стек пуст
     */
    public static int maxLength() {
        int max = 0;
        for (MyString s : stack) {
            if (s.length > max) {
                max = s.length;
            }
        }
        return max;
    }

    /**
     * Возвращает итератор для обхода стека от вершины к дну.
     *
     * @return итератор строк в порядке LIFO
     */
    public static Iterator<MyString> iterator() {
        return new TopDownIterator<>(stack);
    }

    /**
     * Очищает стек.
     */
    public static void clear() {
        stack.clear();
    }

    /**
     * Возвращает значение строки.
     *
     * @return строка
     */
    public String getValue() {
        return value;
    }

    /**
     * Возвращает длину строки.
     *
     * @return длина
     */
    public int getLength() {
        return length;
    }
}