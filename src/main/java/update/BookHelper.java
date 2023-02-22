package update;

import update.entity.Book;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
            session.save(new Book("Title" + i, i));
            if (i % 10 == 0) {
                session.flush();
                System.out.println("flush() 10 objects on " + i + " row");
            }
        }
        session.getTransaction().commit();
        session.close();
    }

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


}
