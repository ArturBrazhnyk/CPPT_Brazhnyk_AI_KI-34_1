package Lab3.Brazhnyk.KI304;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Абстрактний клас Car представляє автомобіль з основними компонентами, такими як двигун, колеса і трансмісія.
 * Цей клас надає базову реалізацію для конкретних типів автомобілів (наприклад, легкові автомобілі та вантажівки).
 */
public abstract class Car {
    private Engine engine;        // Двигун автомобіля
    protected Wheel[] wheels;     // Колеса автомобіля (масив з 4-х коліс)
    private Transmission transmission;  // Трансмісія автомобіля
    private PrintWriter logWriter;  // Об'єкт для запису журналу в файл

    /**
     * Конструктор за замовчуванням.
     * Ініціалізує автомобіль з новим двигуном, колесами та трансмісією, а також відкриває файл для запису журналу.
     *
     * @throws IOException якщо виникає помилка при створенні файлу журналу.
     */
    public Car() throws IOException {
        this.engine = new Engine(); 
        this.wheels = new Wheel[4];  // Масив для 4-х коліс
        for (int i = 0; i < 4; i++) {
            wheels[i] = new Wheel(); // Ініціалізація кожного колеса
        }
        this.transmission = new Transmission(); 
        this.logWriter = new PrintWriter(new File("CarLog.txt"));  // Створення файлу журналу
    }

    /**
     * Конструктор з параметрами для налаштування двигуна, швидкості та передачі.
     *
     * @param isEngineRunning стан двигуна (запущений чи ні).
     * @param speed швидкість автомобіля.
     * @param gear передача трансмісії.
     * @throws IOException якщо виникає помилка при створенні файлу журналу.
     */
    public Car(boolean isEngineRunning, int speed, String gear) throws IOException {
        this();  // Викликає конструктор за замовчуванням

        // Налаштовуємо двигун
        if (isEngineRunning) {
            this.engine.start();
        } else {
            this.engine.stop();
        }

        // Налаштовуємо швидкість та передачу
        this.transmission.setSpeed(speed);
        this.transmission.changeGear(gear);
    }

    /**
     * Конструктор для введення даних вручну.
     * Користувач може ввести інформацію про запуск двигуна, швидкість та передачу.
     *
     * @param scanner об'єкт для зчитування введених даних.
     * @throws IOException якщо виникає помилка при створенні файлу журналу.
     */
    public Car(Scanner scanner) throws IOException {
        this();  // Викликаємо конструктор за замовчуванням

        // Запитуємо у користувача, чи запускати двигун
        System.out.print("Запустити двигун? (true/false): ");
        boolean isEngineRunning = scanner.nextBoolean();
        if (isEngineRunning) {
            this.engine.start();
        }

        // Запитуємо швидкість
        System.out.print("Введіть швидкість: ");
        int speed = scanner.nextInt();
        this.transmission.setSpeed(speed);

        // Запитуємо передачу
        System.out.print("Введіть передачу (1, 2, N, R): ");
        String gear = scanner.next();
        this.transmission.changeGear(gear);
    }

    /**
     * Абстрактний метод для перевірки коліс.
     * Повинен бути реалізований у підкласах.
     */
    public abstract void checkWheels();

    /**
     * Абстрактний метод для запуску двигуна.
     * Повинен бути реалізований у підкласах.
     */
    public abstract void startEngine();

    /**
     * Абстрактний метод для зупинки двигуна.
     * Повинен бути реалізований у підкласах.
     */
    public abstract void stopEngine();

    /**
     * Абстрактний метод для перевірки вантажу (для вантажівок).
     * Повинен бути реалізований у підкласах.
     */
    public abstract void checkLoad();

    // Інші методи класу

    /**
     * Встановлює швидкість автомобіля.
     * Якщо двигун запущений, змінює швидкість та записує це в журнал.
     * Якщо двигун не запущений, виводить повідомлення, що спочатку потрібно запустити двигун.
     *
     * @param speed нова швидкість.
     */
    public void setSpeed(int speed) {
        if (engine.isRunning()) {
            transmission.setSpeed(speed);
            log("Швидкість встановлена: " + speed);
        } else {
            System.out.println("Спочатку запустіть двигун!");
        }
    }

    /**
     * Отримує поточну швидкість автомобіля.
     *
     * @return поточна швидкість.
     */
    public int getSpeed() {
        return transmission.getSpeed();
    }

    /**
     * Змінює передачу автомобіля.
     * Записує інформацію про зміну передачі в журнал.
     *
     * @param gear нова передача.
     */
    public void changeGear(String gear) {
        transmission.changeGear(gear);
        log("Передача змінена на: " + transmission.getCurrentGear());
    }

    /**
     * Отримує поточну передачу автомобіля.
     *
     * @return поточна передача.
     */
    public String getCurrentGear() {
        return transmission.getCurrentGear();
    }

    /**
     * Гальмує автомобіль, зупиняючи його.
     * Якщо швидкість більше нуля, автомобіль зупиняється і записується повідомлення в журнал.
     * Якщо швидкість вже дорівнює нулю, виводиться повідомлення, що автомобіль вже зупинений.
     */
    public void brake() {
        if (transmission.getSpeed() > 0) {
            transmission.setSpeed(0);
            log("Машина зупинена. Гальмування спрацювало!");
            System.out.println("Гальмування спрацювало, машина зупинена.");
        } else {
            log("Машина вже зупинена.");
            System.out.println("Машина вже зупинена.");
        }
    }

    /**
     * Закриває файл для запису журналу.
     */
    public void dispose() {
        logWriter.close();
    }

    /**
     * Записує повідомлення в журнал.
     *
     * @param message повідомлення для запису.
     */
    protected void log(String message) { 
        logWriter.println(message);
        logWriter.flush();
    }

    // Внутрішній клас Engine (Двигун)
    class Engine {
        private boolean running = false;

        public void start() {
            running = true;
        }

        public void stop() {
            running = false;
        }

        public boolean isRunning() {
            return running;
        }
    }

    // Внутрішній клас Wheel (Колесо)
    class Wheel {
        private String condition = "Добрий";

        public String getCondition() {
            return condition;
        }
    }

    // Внутрішній клас Transmission (Трансмісія)
    class Transmission {
        private int speed = 0;
        private String currentGear = "N";

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public int getSpeed() {
            return speed;
        }

        public void changeGear(String gear) {
            switch (gear) {
                case "1", "2", "3", "4", "5", "N", "R" -> currentGear = gear; // Додано підтримку передач 3, 4, 5
                default -> System.out.println("Невірна передача. Доступні: 1, 2, 3, 4, 5, N, R.");
            }
        }

        public String getCurrentGear() {
            return currentGear;
        }
    }
}

