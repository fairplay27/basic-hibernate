package it.secservizi.CD.util.sample.hibernate.ann.maven.basic;

import java.util.Date;

import org.hibernate.Session;

import it.secservizi.CD.util.sample.hibernate.ann.maven.basic.user.DBUser;
import it.secservizi.CD.util.sample.hibernate.ann.maven.basic.util.HibernateUtil;

/**
 * <h1>Implementazione minima per maven + hibernate + oracle</h1> 
 * Nota: esempio in
 * <a href="https://www.mkyong.com/hibernate/maven-3-hibernate-3-6-oracle-11g-example-xml-mapping/">mkyong</a>
 * <br>
 * Problemi:
 * <ul>
 * <li>POM modificato: Mancava livello java 1.7 vedere <a href="https://stackoverflow.com/questions/3371737/maven-confused-about-jre-been-used">link</a> </li>
 * <li>POM modificato: Mancava log4j per slf4j vedere <a href="https://www.mkyong.com/hibernate/how-to-configure-log4j-in-hibernate-project/">link</a></li>
 * </ul>
 * 
 * 
 * @author Stefano_Antoniazzi
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Maven + Hibernate + Oracle");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		DBUser user = new DBUser();

		user.setUserId(800);
		user.setUsername("stefano");
		user.setCreatedBy("system");
		user.setCreatedDate(new Date());

		session.save(user);
		session.getTransaction().commit();
	}
}