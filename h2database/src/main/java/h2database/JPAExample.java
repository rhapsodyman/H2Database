package h2database;

import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.h2.tools.Server;

public class JPAExample {

	private EntityManager entityManager = EntityManagerUtil.getEntityManager();

	public static void main(String[] args) throws SQLException {
		JPAExample example = new JPAExample();
		System.out.println("Server started on port" + Server.createWebServer().start().getPort());
		System.out.println("After Sucessfully insertion ");
		Student student1 = example.saveStudent("Sumith");
		Student student2 = example.saveStudent("Anoop");
		
		System.out.println("Hello");
	}

	public Student saveStudent(String studentName) {
		Student student = new Student();
		try {
			entityManager.getTransaction().begin();
			student.setStudentName(studentName);
			student = entityManager.merge(student);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
		return student;
	}}
