package Brazhnyk.lab2;

import java.io.IOException;
import java.util.Scanner;

/**
 * Клас {@code CarApp} надає інтерфейс для взаємодії користувача з об'єктом автомобіля.
 * Користувач може запускати та зупиняти двигун, змінювати швидкість, змінювати передачу,
 * перевіряти стан коліс, а також здійснювати гальмування автомобіля.
 * Програма використовує консольний інтерфейс для введення даних.
 */
public class CarApp {

    /**
     * Основний метод програми. Здійснює введення початкових параметрів автомобіля,
     * а потім дозволяє користувачеві взаємодіяти з автомобілем через консольне меню.
     * У разі виникнення помилки при роботі з файлами, виводить повідомлення про помилку.
     * 
     * @param args масив аргументів командного рядка (не використовується в даній програмі).
     * @throws IOException у разі виникнення помилки при введенні або виведенні даних.
     */
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in); // Об'єкт для введення даних від користувача

            // Запит на введення початкових параметрів
            System.out.print("Чи запущений двигун? (true/false): ");
            boolean isEngineRunning = scanner.nextBoolean(); // Введення стану двигуна

            System.out.print("Введіть швидкість (0 або більше): ");
            int speed = scanner.nextInt(); // Введення початкової швидкості автомобіля

            System.out.print("Введіть передачу (1, 2, N, R): ");
            String gear = scanner.next(); // Введення початкової передачі

            // Створення автомобіля з переданими параметрами
            Car car = new Car(isEngineRunning, speed, gear);

            boolean running = true; // Логічна змінна для циклу меню

            // Цикл для відображення меню та обробки команд користувача
            while (running) {
                System.out.println("\nМеню:");
                System.out.println("1. Запустити двигун");
                System.out.println("2. Зупинити двигун");
                System.out.println("3. Встановити швидкість");
                System.out.println("4. Змінити передачу");
                System.out.println("5. Перевірити колеса");
                System.out.println("6. Гальмувати");
                System.out.println("7. Вийти");

                int choice = scanner.nextInt(); // Читання вибору користувача
                switch (choice) {
                    case 1 -> car.startEngine(); // Запуск двигуна
                    case 2 -> car.stopEngine(); // Зупинка двигуна
                    case 3 -> {
                        System.out.print("Введіть швидкість: ");
                        int newSpeed = scanner.nextInt(); // Введення нової швидкості
                        car.setSpeed(newSpeed); // Встановлення нової швидкості
                        System.out.println("Швидкість було змінено на: " + car.getSpeed());
                    }
                    case 4 -> {
                        System.out.print("Введіть передачу (1, 2, N, R): ");
                        String newGear = scanner.next(); // Введення нової передачі
                        car.changeGear(newGear); // Зміна передачі
                        System.out.println("Передача була змінена на: " + car.getCurrentGear());
                    }
                    case 5 -> car.checkWheels(); // Перевірка стану коліс
                    case 6 -> car.brake(); // Гальмування
                    case 7 -> running = false; // Вихід з програми
                    default -> System.out.println("Невірний вибір"); // Якщо вибір не відповідає варіантам
                }
            }

            // Закриття ресурсів
            car.dispose();
            scanner.close();
        } catch (IOException e) {
            // Обробка помилки вводу/виводу
            System.out.println("Помилка під час роботи з файлом: " + e.getMessage());
        }
    }
}

