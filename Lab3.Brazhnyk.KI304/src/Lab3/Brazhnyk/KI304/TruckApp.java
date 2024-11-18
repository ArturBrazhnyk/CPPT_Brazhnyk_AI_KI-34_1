package Lab3.Brazhnyk.KI304;

import java.io.IOException;
import java.util.Scanner;

/**
 * Клас <code>TruckApp</code> надає інтерфейс для взаємодії з вантажним автомобілем.
 * Програма дозволяє користувачу виконувати різні операції з вантажівкою через текстове меню,
 * такі як запуск та зупинка двигуна, встановлення швидкості, зміна передач, управління вантажем тощо.
 * 
 * <p>Програма використовує об'єкт класу <code>Truck</code> для виконання операцій.
 * Користувач може виконувати такі операції, як завантаження, вивантаження вантажу,
 * перевірка поточного вантажу, а також керувати швидкістю та передачами вантажівки.</p>
 */
public class TruckApp {
    
    /**
     * Основний метод програми, який містить цикл меню для вибору операцій з вантажівкою.
     * Користувач може вибирати операції, такі як:
     * <ul>
     *     <li>Запуск та зупинка двигуна</li>
     *     <li>Зміна швидкості</li>
     *     <li>Зміна передачі</li>
     *     <li>Перевірка стану вантажу</li>
     *     <li>Завантаження та вивантаження вантажу</li>
     *     <li>Вихід з програми</li>
     * </ul>
     * 
     * @param args Масив аргументів командного рядка (не використовується в цьому класі).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Введення початкових параметрів
            System.out.print("Введіть максимальну вантажопідйомність: ");
            int maxLoad = scanner.nextInt();

            // Створення вантажного автомобіля
            Truck truck = new Truck(maxLoad);

            boolean running = true;

            // Меню для вибору операцій
            while (running) {
                System.out.println("\nМеню:");
                System.out.println("1. Запустити двигун");
                System.out.println("2. Зупинити двигун");
                System.out.println("3. Встановити швидкість");
                System.out.println("4. Змінити передачу");
                System.out.println("5. Перевірити вантаж");
                System.out.println("6. Завантажити вантаж");
                System.out.println("7. Вивантажити вантаж");
                System.out.println("8. Вийти");

                // Зчитування вибору користувача
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> truck.startEngine();  // Запуск двигуна
                    case 2 -> truck.stopEngine();   // Зупинка двигуна
                    case 3 -> {  // Встановлення швидкості
                        System.out.print("Введіть швидкість: ");
                        int newSpeed = scanner.nextInt();
                        truck.setSpeed(newSpeed);
                        System.out.println("Швидкість було змінено на: " + truck.getSpeed());
                    }
                    case 4 -> {  // Зміна передачі
                        System.out.print("Введіть передачу (1, 2, N, R): ");
                        String newGear = scanner.next();
                        truck.changeGear(newGear);
                        System.out.println("Передача була змінена на: " + truck.getCurrentGear());
                    }
                    case 5 -> truck.checkLoad();  // Перевірка вантажу
                    case 6 -> {  // Завантаження вантажу
                        System.out.print("Введіть вагу для завантаження: ");
                        int weight = scanner.nextInt();
                        truck.load(weight);
                    }
                    case 7 -> {  // Вивантаження вантажу
                        System.out.print("Введіть вагу для вивантаження: ");
                        int weight = scanner.nextInt();
                        truck.unload(weight);
                    }
                    case 8 -> running = false;  // Вихід з програми
                    default -> System.out.println("Невірний вибір");
                }
            }

            truck.dispose();  // Завершення роботи з вантажівкою
        } catch (IOException e) {
            System.out.println("Помилка під час роботи з файлом: " + e.getMessage());
        } finally {
            scanner.close();  // Закриття сканера
        }
    }
}
