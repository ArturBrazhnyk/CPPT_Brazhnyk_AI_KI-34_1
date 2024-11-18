package Lab1;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Клас {@code Lab1} реалізує програму для створення зубчастого масиву, 
 * який представляє симетричний трикутник, заповнений символами, та його збереження у файл.
 * <p>
 * Програма дозволяє користувачеві вводити розмір трикутника та символ-заповнювач, 
 * перевіряє коректність вводу, будує трикутник і виводить його у консоль та файл.
 * </p>
 * 
 * @author Artur
 * @version 1.0
 */
public class Lab1 {

    /**
     * Точка входу в програму.
     * 
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        PrintStream out = new PrintStream(System.out, true, UTF_8);
        Scanner in = new Scanner(System.in);

        // Запит розміру трикутника
        int size = promptForTriangleSize(out, in);
        if (size == -1) return;

        // Запит символа-заповнювача
        char filler = promptForFiller(out, in);
        if (filler == '\0') return;

        // Генерація трикутника
        char[][] matrix = generateTriangle(size, filler);

        // Збереження трикутника у файл
        String outputFile = "triangle_output.txt";
        if (saveTriangleToFile(out, matrix, size, outputFile)) {
            out.println("Трикутник успішно збережено у файл " + outputFile);
        }
    }

    /**
     * Запитує у користувача розмір трикутника.
     * 
     * @param out потік для виводу повідомлень
     * @param in об'єкт для читання вводу користувача
     * @return непарний і додатний розмір трикутника або -1 у разі помилки
     */
    private static int promptForTriangleSize(PrintStream out, Scanner in) {
        out.println("Введіть непарний розмір трикутника: ");
        while (true) {
            if (!in.hasNextInt()) {
                out.println("Введіть ціле число!");
                in.next(); // Пропускаємо некоректне значення
                continue;
            }
            int size = in.nextInt();
            if (size > 0 && size % 2 == 1) {
                return size;
            }
            out.println("Розмір має бути непарним і додатнім!");
        }
    }

    /**
     * Запитує у користувача символ-заповнювач.
     * 
     * @param out потік для виводу повідомлень
     * @param in об'єкт для читання вводу користувача
     * @return символ-заповнювач або {@code '\0'} у разі помилки
     */
    private static char promptForFiller(PrintStream out, Scanner in) {
        out.println("Введіть символ-заповнювач: ");
        String input = in.next();
        if (input.length() == 1) {
            return input.charAt(0);
        } else {
            out.println("Необхідно ввести один символ!");
            return '\0';
        }
    }

    /**
     * Генерує зубчастий масив, що представляє симетричний трикутник.
     * 
     * @param size розмір трикутника
     * @param filler символ-заповнювач
     * @return двовимірний зубчастий масив, що представляє трикутник
     */
    private static char[][] generateTriangle(int size, char filler) {
        char[][] matrix = new char[size][];

        // Заповнення верхньої частини трикутника (включаючи центральний рядок)
        for (int i = 0; i <= size / 2; i++) {
            matrix[i] = createRow(size, i, filler, true);
        }

        // Заповнення нижньої частини трикутника
        for (int i = size / 2 + 1; i < size; i++) {
            matrix[i] = createRow(size, i, filler, false);
        }

        return matrix;
    }

    /**
     * Створює окремий рядок трикутника.
     * 
     * @param size загальний розмір трикутника
     * @param rowIndex індекс рядка
     * @param filler символ-заповнювач
     * @param isUpper прапорець для визначення верхньої чи нижньої частини трикутника
     * @return масив символів, що представляє один рядок трикутника
     */
    private static char[] createRow(int size, int rowIndex, char filler, boolean isUpper) {
        int rowLength = isUpper ? size - 2 * rowIndex : 2 * (rowIndex - size / 2) + 1;
        char[] row = new char[rowLength];
        for (int j = 0; j < rowLength; j++) {
            row[j] = filler;
        }
        return row;
    }

    /**
     * Зберігає трикутник у текстовий файл і виводить його на екран.
     * 
     * @param out потік для виводу повідомлень
     * @param matrix зубчастий масив, що представляє трикутник
     * @param size розмір трикутника
     * @param fileName ім'я файлу для збереження
     * @return {@code true}, якщо збереження виконано успішно; {@code false}, якщо сталася помилка
     */
    private static boolean saveTriangleToFile(PrintStream out, char[][] matrix, int size, String fileName) {
        try (PrintWriter writer = new PrintWriter(new File(fileName), UTF_8)) {
            for (char[] row : matrix) {
                String line = createAlignedLine(row, size);
                out.println(line);
                writer.println(line);
            }
            return true;
        } catch (IOException e) {
            out.println("Помилка запису у файл: " + e.getMessage());
            return false;
        }
    }

    /**
     * Створює рядок з вирівнюванням по центру для консолі та файлу.
     * 
     * @param row масив символів, що представляє один рядок трикутника
     * @param size загальний розмір трикутника
     * @return відформатований рядок з пробілами для центрування
     */
    private static String createAlignedLine(char[] row, int size) {
        StringBuilder line = new StringBuilder();

        int spaces = (size - row.length) / 2;
        for (int i = 0; i < spaces; i++) {
            line.append("  ");
        }
        for (char c : row) {
            line.append(c).append(" ");
        }
        return line.toString();
    }
}
