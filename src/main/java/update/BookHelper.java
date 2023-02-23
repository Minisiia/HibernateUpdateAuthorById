package update;

import update.entity.Author;
import update.entity.Book;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookHelper {
    private SessionFactory sf;

    public BookHelper() {
        sf = HibernateUtil.getSessionFactory();
    }

    public Book getBookById(long id) {
        Session session = sf.openSession();
        Book book = session.get(Book.class, id);
        session.close();
        return book;
    }

    public List<Book> getAll() {
        Session session = sf.openSession();
        Criteria criteria = session.createCriteria(Book.class);
        List<Book> list = criteria.list();
        session.close();
        return list;
    }

    public void addBook(Book book) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        session.close();
    }

    public void add200Books() {
        Session session = sf.openSession();
        session.beginTransaction();
        for (int i = 1; i < 201; i++) {
            int idAuthor = i;
            if (i % 3 == 0) idAuthor = i / 3;
            session.save(new Book("Title" + i, idAuthor));
            if (i % 10 == 0) {
                session.flush();
                System.out.println("flush() 10 objects on " + i + " row");
            }
        }
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Updates the title of a book with the given id.
     * @param id The id of the book to be updated.
     * @param title The new title to be set for the book.
     * @return The updated Book object.
     */
    public Book updateBookTitleById(long id, String title) {
        Session session = sf.openSession();
        session.beginTransaction();
        Book book = getBookById(id);
        book.setName(title);
        session.update(book);
        session.getTransaction().commit();
        session.close();
        return book;
    }

    /**
     * Displays the name and last name of an author, along with the titles of all their books.
     * @param id The id of the author whose information is to be displayed.
     */
    public void showAuthorTitleByAuthorId(long id) {
        Session session = sf.openSession();
        AuthorHelper ah = new AuthorHelper();
        Author author = ah.getAuthorById(id);
        System.out.println(author.getName() + " " + author.getLastName());

        List<Book> bookList = getAll();
        System.out.println("Books:");

        for (Book book : bookList) {
            if (book.getAuthorId() == id)
                System.out.println(book.getName());
        }
        session.close();
    }

    /**
     * Returns a HashMap containing the name and last name of an author as the key,
     * and a list of titles of all their books as the value.
     * @param id The id of the author whose information is to be retrieved.
     * @return A HashMap with the author's name and a list of their book titles.
     */
    public HashMap<String, List<String>> getAuthorTitleByAuthorId(long id) {
        Session session = sf.openSession();
        AuthorHelper ah = new AuthorHelper();
        Author author = ah.getAuthorById(id);
        String fullName = author.getName() + " " + author.getLastName();
        List<Book> bookListAll = getAll();
        List<String> bookList = new ArrayList<>();
        for (Book book : bookListAll) {
            if (book.getAuthorId() == id)
                bookList.add(book.getName());
        }
        HashMap<String, List<String>> listHashMap = new HashMap<>();
        listHashMap.put(fullName, bookList);
        session.close();
        return listHashMap;
    }
}
