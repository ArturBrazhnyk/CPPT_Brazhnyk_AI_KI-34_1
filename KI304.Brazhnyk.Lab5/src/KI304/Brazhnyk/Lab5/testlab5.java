package KI304.Brazhnyk.Lab5;

import java.io.IOException;
import java.util.Scanner;

/**
 * Головний клас для тестування функціоналу класу {@code Lab5}.
 *
 * <p>Цей клас взаємодіє з користувачем через консоль для введення даних,
 * виконує обчислення на основі введеного значення, записує результати
 * у текстові та двійкові файли, а також читає їх назад з цих файлів.
 */
public class testlab5 {

    /**
     * Точка входу в програму.
     *
     * @param args аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {
        // Створення об'єкта Scanner для читання даних з консолі
        Scanner scanner = new Scanner(System.in);

        // Введення значення x у радіанах
        System.out.print("Введіть значення x (в радіанах): ");
        double inputX = scanner.nextDouble();

        // Створення об'єкта класу Lab5 із введеним значенням x
        Lab5 calculator = new Lab5(inputX);

        // Обчислення та виведення результатів
        System.out.println("Обчислення: ");
        System.out.println("x: " + calculator.getX());
        System.out.println("y: " + calculator.calculateY());

        // Запис результатів у текстовий файл
        String textFilename = "results.txt";
        try {
            calculator.writeToTextFile(textFilename);
            System.out.println("Результати записані у текстовий файл: " + textFilename);
        } catch (IOException e) {
            System.err.println("Помилка запису у текстовий файл: " + e.getMessage());
        }

        // Запис результатів у двійковий файл
        String binaryFilename = "results.bin";
        try {
            calculator.writeToBinaryFile(binaryFilename);
            System.out.println("Результати записані у двійковий файл: " + binaryFilename);
        } catch (IOException e) {
            System.err.println("Помилка запису у двійковий файл: " + e.getMessage());
        }

        // Читання результатів із текстового файлу
        try {
            calculator.readFromTextFile(textFilename);
            System.out.println("Прочитано з текстового файлу: x = " + calculator.getX());
            System.out.println("Обчислене y: " + calculator.calculateY());
        } catch (IOException e) {
            System.err.println("Помилка читання з текстового файлу: " + e.getMessage());
        }

        // Читання результатів із двійкового файлу
        try {
            calculator.readFromBinaryFile(binaryFilename);
            System.out.println("Прочитано з двійкового файлу: x = " + calculator.getX());
            System.out.println("Обчислене y: " + calculator.calculateY());
        } catch (IOException e) {
            System.err.println("Помилка читання з двійкового файлу: " + e.getMessage());
        }

        // Закриття Scanner для вивільнення ресурсів
        scanner.close();
    }
}


