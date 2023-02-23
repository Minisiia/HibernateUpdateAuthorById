package update;

import update.entity.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AuthorHelper {

    private SessionFactory sessionFactory;

    public AuthorHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Author> getAuthorList() {
        // открыть сессию - для манипуляции с персист. объектами
        Session session = sessionFactory.openSession();
        // этап подготовки запроса
        // объект-конструктор запросов для Criteria API
        CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated
        CriteriaQuery cq = cb.createQuery(Author.class);
        Root<Author> root = cq.from(Author.class);// первостепенный, корневой entity (в sql запросе - from)
        cq.select(root);// необязательный оператор, если просто нужно получить все значения
        //этап выполнения запроса
        Query query = session.createQuery(cq);
        List<Author> authorList = query.getResultList();
        session.close();
        return authorList;
    }

    public Author getAuthorById(long id) {
        Session session = sessionFactory.openSession();
        Author author = session.get(Author.class, id); // получение объекта по id
        session.close();
        return author;
    }

    public Author addAuthor(Author author) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(author);
        session.getTransaction().commit();
        session.close();
        return author;

    }

    public void add200Authors() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (int i = 1; i < 201; i++) {
            session.save(new Author("Name" + i, "Last name" + i));
            if (i % 10 == 0) {
                session.flush();
                System.out.println("flush() 10 objects on " + i + " row");
            }
        }
        session.getTransaction().commit();
        session.close();
    }

    public Author updateAuthorNameById(long id, String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Author author = getAuthorById(id);
        author.setName(name);
        session.update(author);
        session.getTransaction().commit();
        session.close();
        return author;
    }

    public Author updateAuthorNameById(long id, String name, String lastName) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Author author = getAuthorById(id);
        author.setName(name);
        author.setLastName(lastName);
        session.update(author);
        session.getTransaction().commit();
        session.close();
        return author;
    }
}
