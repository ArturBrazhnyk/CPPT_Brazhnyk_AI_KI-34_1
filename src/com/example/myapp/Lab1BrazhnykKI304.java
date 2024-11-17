package com.example.myapp;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Клас Lab1BrazhnykKI304 реалізує програму для генерування зубчастого масиву, 
 * що заповнений символами у вигляді симетричних трикутників, з виведенням у текстовий файл.
 */
public class Lab1BrazhnykKI304 {

    public static void main(String[] args) {
        PrintStream out = new PrintStream(System.out, true, UTF_8);
        Scanner in = new Scanner(System.in);

        // Введення розміру трикутника
        out.println("Введіть непарний розмір трикутника: ");
        int size = in.nextInt();

        // Перевірка на непарність та позитивність розміру
        if (size <= 0 || size % 2 == 0) {
            out.println("Розмір має бути непарним і додатнім!");
            return;
        }

        // Введення символу-заповнювача
        out.println("Введіть символ-заповнювач: ");
        String fillerInput = in.next();

        // Перевірка, чи введено лише один символ
        if (fillerInput.length() != 1) {
            out.println("Необхідно ввести один символ!");
            return;
        }

        char filler = fillerInput.charAt(0);

        // Створюємо зубчастий масив
        char[][] matrix = new char[size][];

        // Заповнення верхньої частини трикутника (включно з центральним рядком)
        for (int i = 0; i <= size / 2; i++) {
            int rowLength = size - 2 * i; // Довжина підмасиву зменшується
            matrix[i] = new char[rowLength];
            for (int j = 0; j < rowLength; j++) {
                matrix[i][j] = filler; // Заповнення символами-заповнювачами
            }
        }

        // Заповнення нижньої частини трикутника
        for (int i = size / 2 + 1; i < size; i++) {
            int rowLength = 2 * (i - size / 2) + 1; // Довжина підмасиву збільшується
            matrix[i] = new char[rowLength];
            for (int j = 0; j < rowLength; j++) {
                matrix[i][j] = filler; // Заповнення символами-заповнювачами
            }
        }

        // Запис у текстовий файл
        try (PrintWriter writer = new PrintWriter(new File("triangle_output.txt"), UTF_8)) {
            // Виведення результату на екран та у файл
            for (int i = 0; i < size; i++) {
                // Виведення пробілів для вирівнювання по центру
                int spaces = (size - matrix[i].length) / 2;

                // Формування рядка
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < spaces; j++) {
                    line.append("  ");
                }
                for (int j = 0; j < matrix[i].length; j++) {
                    line.append(matrix[i][j]).append(" ");
                }

                // Виведення на екран
                out.println(line.toString());

                // Запис у файл
                writer.println(line.toString());
            }

            out.println("Трикутник успішно збережено у файл triangle_output.txt");
        } catch (IOException e) {
            out.println("Помилка запису у файл: " + e.getMessage());
        }
    }
}

