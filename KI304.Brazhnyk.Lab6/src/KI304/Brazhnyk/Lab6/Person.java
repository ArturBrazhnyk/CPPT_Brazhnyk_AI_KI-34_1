package KI304.Brazhnyk.Lab6;

/**
 * Клас, що представляє людину.
 *
 * <p>Кожна людина має ім'я та вік. Клас реалізує інтерфейс {@code Comparable<Person>} 
 * для порівняння людей за віком. Також ведеться підрахунок створених екземплярів 
 * за допомогою статичного поля {@code count}.
 */
public class Person implements Comparable<Person> {
    
    /** Ім'я людини. */
    public String name;
    
    /** Вік людини. */
    public int age;
    
    /** Статичне поле для підрахунку створених об'єктів класу Person. */
    public static int count;

    /**
     * Конструктор для створення об'єкта людини.
     *
     * @param name ім'я людини
     * @param age вік людини
     */
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        count++;
    }

    /**
     * Повертає ім'я людини.
     *
     * @return ім'я людини
     */
    public String getName() {
        return name;
    }

    /**
     * Повертає вік людини.
     *
     * @return вік людини
     */
    public int getAge() {
        return age;
    }

    /**
     * Порівнює цю людину з іншою за віком.
     *
     * @param other інша людина для порівняння
     * @return від'ємне значення, якщо ця людина молодша; 
     *         0, якщо вік однаковий; 
     *         додатнє значення, якщо ця людина старша
     */
    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);
    }

    /**
     * Повертає текстове представлення людини у вигляді рядка.
     *
     * @return рядок у форматі "ім'я (вік)"
     */
    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}
