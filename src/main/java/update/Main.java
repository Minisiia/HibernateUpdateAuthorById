package update;

/**
 * К дополнительному заданию добавить метод обновления имени автора по id.
 * Аналогично сделать и в классе BookHelper с предыдущего ДЗ.
 */

public class Main {

    public static void main(String[] args) {
        AuthorHelper ah = new AuthorHelper();
        BookHelper bh = new BookHelper();
        ah.add200Authors();
        ah.updateAuthorNameById(2, "Taras");
        ah.updateAuthorNameById(3, "Ivan", "Franko");
        bh.add200Books();
        bh.updateBookTitleById(2, "Zapovit");
        bh.updateBookTitleById(3, "Lys Mykyta");
    }
}
