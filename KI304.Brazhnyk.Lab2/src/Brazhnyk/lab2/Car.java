package Brazhnyk.lab2; 

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Клас Car представляє автомобіль з основними компонентами, такими як двигун, колеса і трансмісія.
 */
public class Car {
    private Engine engine; // Двигун автомобіля
    private Wheel[] wheels; // Масив з чотирьох коліс
    private Transmission transmission; // Трансмісія автомобіля
    private PrintWriter logWriter; // Логер для запису в файл

    /**
     * Конструктор за замовчуванням.
     * Ініціалізує автомобіль з вимкненим двигуном, чотирма колесами в "добрий" стан і трансмісією на швидкості 0 і передачі N.
     * 
     * @throws IOException якщо виникла помилка при створенні файлу логів.
     */
    public Car() throws IOException {
        this.engine = new Engine(); // Двигун вимкнений за замовчуванням
        this.wheels = new Wheel[4]; // 4 колеса
        for (int i = 0; i < 4; i++) {
            wheels[i] = new Wheel(); // Всі колеса в "Добрий" стан
        }
        this.transmission = new Transmission(); // Швидкість 0 і передача N за замовчуванням
        this.logWriter = new PrintWriter(new File("CarLog.txt")); // Лог-файл
    }

    /**
     * Конструктор з параметрами.
     * Ініціалізує автомобіль за замовчуванням, налаштовує двигун, швидкість і передачу.
     * 
     * @param isEngineRunning стан двигуна (запущений чи вимкнений).
     * @param speed швидкість автомобіля.
     * @param gear поточна передача.
     * @throws IOException якщо виникла помилка при створенні файлу логів.
     */
    public Car(boolean isEngineRunning, int speed, String gear) throws IOException {
        this(); // Викликаємо конструктор за замовчуванням

        if (isEngineRunning) {
            this.engine.start(); // Запускаємо двигун
        } else {
            this.engine.stop(); // Зупиняємо двигун
        }

        this.transmission.setSpeed(speed); // Встановлюємо швидкість
        this.transmission.changeGear(gear); // Змінюємо передачу
    }

    /**
     * Конструктор для введення даних вручну.
     * Запитує у користувача інформацію про стан двигуна, швидкість і передачу.
     * 
     * @param scanner сканер для введення даних користувачем.
     * @throws IOException якщо виникла помилка при створенні файлу логів.
     */
    public Car(Scanner scanner) throws IOException {
        this(); // Викликаємо конструктор за замовчуванням

        System.out.print("Запустити двигун? (true/false): ");
        boolean isEngineRunning = scanner.nextBoolean();
        if (isEngineRunning) {
            this.engine.start();
        }

        System.out.print("Введіть швидкість: ");
        int speed = scanner.nextInt();
        this.transmission.setSpeed(speed);

        System.out.print("Введіть передачу (1, 2, N, R): ");
        String gear = scanner.next();
        this.transmission.changeGear(gear);
    }

    /**
     * Запускає двигун автомобіля та записує повідомлення в лог.
     */
    public void startEngine() {
        engine.start();
        log("Двигун запущено.");
    }

    /**
     * Зупиняє двигун автомобіля та записує повідомлення в лог.
     */
    public void stopEngine() {
        engine.stop();
        log("Двигун зупинено.");
    }

    /**
     * Встановлює швидкість автомобіля, якщо двигун запущений, та записує повідомлення в лог.
     * 
     * @param speed нова швидкість автомобіля.
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
     * Повертає поточну швидкість автомобіля.
     * 
     * @return поточна швидкість.
     */
    public int getSpeed() {
        return transmission.getSpeed();
    }

    /**
     * Змінює передачу автомобіля та записує повідомлення в лог.
     * 
     * @param gear нова передача.
     */
    public void changeGear(String gear) {
        transmission.changeGear(gear);
        log("Передача змінена на: " + transmission.getCurrentGear());
    }

    /**
     * Повертає поточну передачу автомобіля.
     * 
     * @return поточна передача.
     */
    public String getCurrentGear() {
        return transmission.getCurrentGear();
    }

    /**
     * Перевіряє стан всіх коліс та записує відповідні повідомлення в лог.
     */
    public void checkWheels() {
        for (int i = 0; i < wheels.length; i++) {
            log("Колесо " + (i + 1) + " у стані: " + wheels[i].getCondition());
        }
    }

    /**
     * Зупиняє автомобіль та записує повідомлення в лог.
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
     * Закриває файл логів.
     */
    public void dispose() {
        logWriter.close();
    }

    /**
     * Записує повідомлення в лог-файл.
     * 
     * @param message повідомлення для запису в лог.
     */
    private void log(String message) {
        logWriter.println(message);
        logWriter.flush();
    }
}

// Клас Engine представляє двигун автомобіля.
class Engine {
    private boolean running = false;

    /**
     * Запускає двигун.
     */
    public void start() {
        running = true;
    }

    /**
     * Зупиняє двигун.
     */
    public void stop() {
        running = false;
    }

    /**
     * Повертає стан двигуна (запущений чи зупинений).
     * 
     * @return true, якщо двигун запущений, false — якщо зупинений.
     */
    public boolean isRunning() {
        return running;
    }
}

// Клас Wheel представляє колесо автомобіля.
class Wheel {
    private String condition = "Добрий";

    /**
     * Повертає стан колеса.
     * 
     * @return стан колеса.
     */
    public String getCondition() {
        return condition;
    }
}

// Клас Transmission представляє трансмісію автомобіля.
class Transmission {
    private int speed = 0;
    private String currentGear = "N";

    /**
     * Встановлює швидкість трансмісії.
     * 
     * @param speed нова швидкість.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Повертає поточну швидкість.
     * 
     * @return поточна швидкість.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Змінює передачу.
     * 
     * @param gear нова передача.
     */
    public void changeGear(String gear) {
        switch (gear) {
            case "1", "2", "3", "4", "5", "N", "R" -> currentGear = gear;
            default -> System.out.println("Невірна передача. Доступні: 1, 2, 3, 4, 5, N, R.");
        }
    }

    /**
     * Повертає поточну передачу.
     * 
     * @return поточна передача.
     */
    public String getCurrentGear() {
        return currentGear;
    }
}

