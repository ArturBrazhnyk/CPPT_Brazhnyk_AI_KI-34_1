package KI304.Brazhnyk.Lab5;

import java.io.*;

/**
 * Клас Lab5 виконує обчислення функції y = 2 * x / sin(x) для заданого значення x (в радіанах).
 * Також клас підтримує запис та читання значень x та y у текстові та двійкові файли.
 */
public class Lab5 {
    private double x; // Змінна для зберігання значення x

    /**
     * Конструктор класу Lab5.
     * 
     * @param x значення x (в радіанах), яке передається під час створення об'єкта.
     */
    public Lab5(double x) {
        this.x = x;
    }

    /**
     * Отримати значення x.
     * 
     * @return значення x.
     */
    public double getX() {
        return x;
    }

    /**
     * Обчислити значення функції y = 2 * x / sin(x).
     * 
     * @return значення функції y.
     */
    public double calculateY() {
        return 2 * x / Math.sin(x); // Формула обчислення y
    }

    /**
     * Записати значення x та y у текстовий файл.
     * 
     * @param filename ім'я файлу, в який буде записано результат.
     * @throws IOException якщо сталася помилка під час запису у файл.
     */
    public void writeToTextFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("x (в радіанах): " + x + "\n"); // Запис значення x
            writer.write("y: " + calculateY() + "\n"); // Запис значення y
        }
    }

    /**
     * Записати значення x та y у двійковий файл.
     * 
     * @param filename ім'я двійкового файлу, в який буде записано результат.
     * @throws IOException якщо сталася помилка під час запису у файл.
     */
    public void writeToBinaryFile(String filename) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename))) {
            dos.writeDouble(x); // Запис значення x у двійковий файл
            dos.writeDouble(calculateY()); // Запис значення y у двійковий файл
        }
    }

    /**
     * Прочитати значення x з текстового файлу та оновити його.
     * 
     * @param filename ім'я текстового файлу, з якого буде прочитано значення.
     * @throws IOException якщо сталася помилка під час читання з файлу.
     */
    public void readFromTextFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            if ((line = reader.readLine()) != null) {
                // Розбір рядка для отримання значення x
                this.x = Double.parseDouble(line.split(": ")[1]);
            }
        }
    }

    /**
     * Прочитати значення x з двійкового файлу та оновити його.
     * 
     * @param filename ім'я двійкового файлу, з якого буде прочитано значення.
     * @throws IOException якщо сталася помилка під час читання з файлу.
     */
    public void readFromBinaryFile(String filename) throws IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(filename))) {
            this.x = dis.readDouble(); // Читання значення x з двійкового файлу
        }
    }
}


