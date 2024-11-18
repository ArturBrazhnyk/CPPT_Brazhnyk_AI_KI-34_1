package KI304.Brazhnyk.Lab6;

/**
 * Клас, що представляє книгу.
 *
 * <p>Кожна книга має назву та кількість сторінок. Клас реалізує інтерфейс {@code Comparable<Book>} 
 * для порівняння книг за кількістю сторінок. Також ведеться підрахунок створених екземплярів 
 * за допомогою статичного поля {@code count}.
 */
public class Book implements Comparable<Book> {
    
    /** Назва книги. */
    public String title;
    
    /** Кількість сторінок у книзі. */
    public int pages;
    
    /** Статичне поле для підрахунку створених об'єктів класу Book. */
    public static int count;

    /**
     * Конструктор для створення книги.
     *
     * @param title назва книги
     * @param pages кількість сторінок у книзі
     */
    public Book(String title, int pages) {
        this.title = title;
        this.pages = pages;
        count++;
    }

    /**
     * Повертає назву книги.
     *
     * @return назва книги
     */
    public String getTitle() {
        return title;
    }

    /**
     * Повертає кількість сторінок у книзі.
     *
     * @return кількість сторінок
     */
    public int getPages() {
        return pages;
    }

    /**
     * Порівнює цю книгу з іншою за кількістю сторінок.
     *
     * @param other інша книга для порівняння
     * @return від'ємне значення, якщо ця книга має менше сторінок; 
     *         0, якщо кількість сторінок однакова;
     *         додатнє значення, якщо ця книга має більше сторінок
     */
    @Override
    public int compareTo(Book other) {
        return Integer.compare(this.pages, other.pages);
    }

    /**
     * Повертає текстове представлення книги у вигляді рядка.
     *
     * @return рядок у форматі "назва (кількість сторінок pages)"
     */
    @Override
    public String toString() {
        return title + " (" + pages + " pages)";
    }
}
