package update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * К дополнительному заданию добавить метод обновления имени автора по id.
 * Аналогично сделать и в классе BookHelper с предыдущего ДЗ.
 * В классе BookHelper создать метод, который получает название книг и имя автора.
 */

public class Main {

    public static void main(String[] args) {
        AuthorHelper ah = new AuthorHelper();
        BookHelper bh = new BookHelper();
        ah.add200Authors();
        ah.updateAuthorNameById(3, "Taras");
        ah.updateAuthorNameById(2, "Ivan", "Franko");
        bh.add200Books();
        bh.updateBookTitleById(2, "Lys Mykyta");
        bh.updateBookTitleById(3, "Zapovit");
        bh.updateBookTitleById(4, "Zahar Berkut");
        bh.updateBookTitleById(6, "Ukradene schactia");
        System.out.println("Print to console with method showAuthorTitleByAuthorId():");
        bh.showAuthorTitleByAuthorId(2);
        System.out.println("\nPrint to console with method getAuthorTitleByAuthorId():");
        HashMap<String, List<String>> totalInfo = bh.getAuthorTitleByAuthorId(2);
        for ( Map.Entry<String, List<String>> entry : totalInfo.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }


    }
}
