package JPA;

import javax.persistence.*;
import java.util.List;
public class JpaExampleOut {
        public static void main(String[] args) {
            // Создаем EntityManagerFactory с помощью persistence unit name из persistence.xml
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
            // Создаем EntityManager
            EntityManager em = emf.createEntityManager();
            // Создаем EntityTransaction
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            try {
                // Ваши действия с базой данных
                // Получение списка всех пользователей из таблицы users
                TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
                List<User> users = query.getResultList();
                // Вывод списка пользователей в консоль
                for (User user : users) {
                    System.out.println("ID: " + user.getId());
                    System.out.println("Name: " + user.getName());
                    System.out.println("Age: " + user.getAge());
                    System.out.println("---------------------------");
                }
                // Фиксация транзакции
                transaction.commit();
            } catch (Exception e) {
                // Обработка ошибок
                e.printStackTrace();
                transaction.rollback();
            } finally {
                // Закрытие EntityManager и EntityManagerFactory
                em.close();
                emf.close();
            }
        }
    }