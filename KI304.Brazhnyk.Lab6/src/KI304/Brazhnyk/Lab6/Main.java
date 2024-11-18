package KI304.Brazhnyk.Lab6;

/**
 * Main driver class to demonstrate the DoublyLinkedList with different types.
 */
public class Main {
    public static void main(String[] args) {
        
        DoublyLinkedList<Person> personList = new DoublyLinkedList<>();
        personList.add(new Person("Alice", 25));
        personList.add(new Person("Bob", 30));
        personList.add(new Person("Charlie", 20));
        System.out.println("Person List:");
        personList.printList();
        System.out.println("Oldest person: " + personList.findMaximum());

        
        DoublyLinkedList<Book> bookList = new DoublyLinkedList<>();
        bookList.add(new Book("Java Programming", 500));
        bookList.add(new Book("Python Essentials", 300));


        System.out.println("Book List:");
        bookList.printList();
        System.out.println(Book.count >= Person.count);
    }
}
