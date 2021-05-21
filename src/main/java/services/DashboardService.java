package services;

import entities.BookEntity;
import exceptions.MySqlSessionException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    public List<BookEntity> getAllBooks(){
        SessionFactory factory = new Configuration()
                .addAnnotatedClass(BookEntity.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        List<BookEntity> books;

        try {
            session.getTransaction().begin();
            books = session.createQuery(" from BookEntity").getResultList();
            session.getTransaction().commit();

            session.close();
            factory.close();
            return books;
        }
        catch(Exception ex){
            factory.close();
            System.out.println("Dashboard servlet received GET, and exception occurred");
            throw new MySqlSessionException();
        }
    }

    public BookEntity getBook(int id){
        SessionFactory factory = new Configuration()
                .addAnnotatedClass(BookEntity.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
            session.getTransaction().begin();
            BookEntity book = session.get(BookEntity.class, id);
            System.out.println(book);
            session.close();
            factory.close();

            return book;
        }
        catch(Exception ex){
            factory.close();
            System.out.println("Dashboard servlet received POST, and exception occurred");
            throw new MySqlSessionException();
        }

    }

    public void addBook(BookEntity book){
        SessionFactory factory = new Configuration()
                .addAnnotatedClass(BookEntity.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
            System.out.println(book);
            session.getTransaction().begin();
            session.save(book);
            session.getTransaction().commit();
            session.close();
            factory.close();
        }

        catch(Exception ex){
            System.out.println("Dashboard servlet received POST, exception: " + ex.getMessage());
            factory.close();
            throw new MySqlSessionException();
        }
    }

    public void deleteBook(int id){
        SessionFactory factory = new Configuration()
                .addAnnotatedClass(BookEntity.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try{
            session.getTransaction().begin();
            BookEntity book = session.get(BookEntity.class, id);
            session.delete(book);
            session.getTransaction().commit();
            session.close();
            factory.close();
        }
        catch(Exception ex){
            factory.close();
            System.out.println("Dashboard servlet received DELETE, and exception occurred");
            throw new MySqlSessionException();
        }
    }
}
