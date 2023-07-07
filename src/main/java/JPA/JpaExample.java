package JPA;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaExample {
    public static void main(String[] args) {
// Создаем EntityManagerFactory с помощью persistence unit name из persistence.xml
        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
        // Создаем EntityManager
        EntityManager em = emf.createEntityManager();
        // Создаем EntityTransaction
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            // Ваши действия с базой данных
            // Пример: создание новой сущности
            User entity = new User();
            // Установка значений полей сущности
            entity.setName("Andrey");
            entity.setAge(18);
            // Сохранение сущности в базе данных
            em.persist(entity);
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
