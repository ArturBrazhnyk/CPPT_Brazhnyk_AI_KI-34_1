package KI304.Brazhnyk.LAB4;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Клас для обчислення виразу y = 2x / sin(x) і запису результату у файл.
 */
public class ExpressionCalculator {

    /**
     * Метод обчислює вираз y = 2x / sin(x).
     * @param x Значення, для якого потрібно обчислити вираз (у радіанах).
     * @return Результат обчислення.
     * @throws ArithmeticException Якщо sin(x) = 0 або інші арифметичні помилки.
     */
    public double calculate(double x) throws ArithmeticException {
        double sinX = Math.sin(x);    // Обчислюємо синус (без перетворення на радіани, оскільки x уже в радіанах)

        // Перевірка, чи sin(x) дорівнює нулю або близький до нуля
        if (Math.abs(sinX) < 1e-10) {
            throw new ArithmeticException("Неможливо обчислити: sin(x) дуже малий або дорівнює 0.");
        }

        // Обчислюємо результат
        double result = (2 * x) / sinX;

        // Перевірка на коректність результату
        if (Double.isNaN(result) || Double.isInfinite(result)) {
            throw new ArithmeticException("Помилка: результат обчислення не є числом.");
        }

        return result;
    }

    /**
     * Метод записує результат у файл.
     * @param result Результат обчислення.
     * @param fileName Назва файлу, у який буде записано результат.
     * @throws IOException Якщо виникає помилка при записі у файл.
     */
    public void writeResultToFile(double result, String fileName) throws IOException {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter(fileName));
            out.println("Результат обчислення: " + result);
        } catch (IOException e) {
            System.err.println("Помилка при записі у файл: " + e.getMessage());
            throw e; 
        } finally {
            if (out != null) {
                out.close(); // Закриття файлу
            }
        }
    }
}


