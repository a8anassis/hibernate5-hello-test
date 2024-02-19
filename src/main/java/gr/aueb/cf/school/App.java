package gr.aueb.cf.school;

import gr.aueb.cf.school.model.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("teachersPU");
        EntityManager em = emf.createEntityManager();

//        Teacher anna = new Teacher();
//        anna.setId(1L);
//        anna.setFirstname("Anna");
//        anna.setLastname("Giannoutsou");

//        Teacher androutsos1 = new Teacher();
//        androutsos1.setFirstname("Athanassios");
//        androutsos1.setLastname("Androutsos");
//
//        Teacher androutsos2 = new Teacher();
//        androutsos2.setFirstname("Andreas");
//        androutsos2.setLastname("Androutsos");
//
//        Teacher androutsos3 = new Teacher();
//        androutsos3.setFirstname("Niki");
//        androutsos3.setLastname("Androutsou");

        em.getTransaction().begin();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Teacher> selectQuery = builder.createQuery(Teacher.class);

        // The entity that will be queried is Teacher
        Root<Teacher> root = selectQuery.from(Teacher.class);

        // Select all attributes from teacher
//        selectQuery.select(root).where(builder.equal(root.get("firstname"), "Andreas"));
        ParameterExpression<String> firstnameParam = builder.parameter(String.class, "firstnameParam");
        selectQuery.select(root).where(builder.equal(root.get("firstname"), firstnameParam));
        List<Teacher> teachers = em.createQuery(selectQuery).setParameter(firstnameParam, "Andreas").getResultList();

        //List<Teacher> teachers = em.createQuery(selectQuery).getResultList();

//        List<Teacher> teacherList = em.createQuery("SELECT s FROM Teacher s", Teacher.class).getResultList();
//        Teacher teacher = em.createQuery("SELECT s FROM Teacher s WHERE s.id = :id", Teacher.class)
//                .setParameter("id", 2L)
//                .getSingleResult();
//        System.out.println(teacher);

//        em.merge(anna);
//        Teacher anna = em.find(Teacher.class, 1L);
//        em.remove(anna);

//        em.persist(androutsos1);
//        em.persist(androutsos2);
//        em.persist(androutsos3);
        em.getTransaction().commit();

        teachers.forEach(System.out::println);
//        teacherList.forEach(System.out::println);

//        System.out.println(anna.getFirstname());
//        System.out.println(anna.getLastname());



        em.close();
        emf.close();
    }
}
