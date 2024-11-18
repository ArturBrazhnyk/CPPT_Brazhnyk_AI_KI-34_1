package KI304.Brazhnyk.LAB4;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Програма-драйвер для тестування класу ExpressionCalculator.
 * Цей клас обробляє введення користувача, викликає методи обчислення та запису результатів у файл.
 */
public class Driver {
    /**
     * Головний метод програми.
     * Він дозволяє користувачу вводити значення x, обчислює результат, і записує його у файл.
     * Програма обробляє винятки, такі як помилки вводу, арифметичні помилки, та помилки вводу/виводу.
     * 
     * @param args аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Створюємо об'єкт Scanner для зчитування даних
        ExpressionCalculator calculator = new ExpressionCalculator();  // Створюємо об'єкт калькулятора

        // Безкінечний цикл для повторного запиту даних користувача
        while (true) {
            try {
                // Введення значення x користувачем
                System.out.print("Введіть значення x (у радіанах): ");
                double x = scanner.nextDouble();  // Введення значення x

                // Обчислення результату за допомогою методу calculate()
                double result = calculator.calculate(x);
                System.out.println("Результат: " + result);

                // Запис результату у файл
                calculator.writeResultToFile(result, "result.txt");
                System.out.println("Результат записано у файл result.txt");

                break;  // Завершення циклу після успішного обчислення

            } catch (InputMismatchException e) {
                // Обробка помилки вводу (нечислові значення)
                System.err.println("Помилка вводу: введіть числове значення.");
                scanner.next();  // Очищення буфера для повторного введення
            } catch (ArithmeticException e) {
                // Обробка арифметичних помилок (наприклад, ділення на нуль)
                System.err.println("Помилка при обчисленні: " + e.getMessage());
            } catch (IOException e) {
                // Обробка помилок при записі у файл
                System.err.println("Помилка при записі у файл: " + e.getMessage());
            } catch (Exception e) {
                // Обробка невідомих помилок
                System.err.println("Невідома помилка: " + e.getMessage());
            }
        }

        scanner.close();  // Закриття сканера після завершення роботи
    }
}
