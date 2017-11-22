# README

## Descrizione

Hibernate minimale con annotations generate da JBoss tools

## Posizioni notevoli

Organizzazione di tipo MAVEN


# Maven: progetto esempio
Progetto di partenza [Maven 3 + Hibernate 3.6 + Oracle 11g Example (XML Mapping)](https://www.mkyong.com/hibernate/maven-3-hibernate-3-6-oracle-11g-example-xml-mapping/)
con varie modifiche in corso d'opera

# Maven: installazione
unizp e modifica del path aggiungendo p.e.
```
C:\SOFTWARE\MAVEN\apache-maven-3.5.2\bin
```
*NOTA*: devono essere settate JAVA_HOME e java deve essere almeno in versione 7

*NOTA*: nonostante quanto definito a link [tutorial](https://www.tutorialspoint.com/maven/maven_environment_setup.htm)
NON definisco le variabili di ambiente tipo
```
M2_HOME=C:\SOFTWARE\MAVEN\apache-maven-3.5.2
M2=%M2_HOME%\bin 
MAVEN_OPTS=-Xms256m -Xmx512m
```
## Maven: verifica versione
```
mvn --version
```
# Maven: creazione progetto per hibernate oracle da eclipse

## Maven: creazione progetto Eclipse

Utilizzando Eclipse Oxygen per sviluppatori JEE:
```
[ECLIPSE] File > new > Maven Project > maven-archetype-quickstart > groupid = it.secservizi.CD.util.sample , artifact id = it.secservizi.CD.util.sample.hibmaven
```
## Maven: utilizzare una installazione esterna
*ATTENZIONE* : e' possibile NON usare l'embedded maven ma usare una installazione esterna dalle preferenze di Eclipse
```
[ECLIPSE] Preferences > cercare Maven > Installations > Add (nel mio caso Installation home = C:\SOFTWARE\MAVEN\apache-maven-3.5.2) 
```
## Maven: modificare global settings
Per cambiare global settings p.e.
```
[ECLIPSE] Preferences > cercare Maven > User Settings > global Settings nel mio caso C:\SOFTWARE\MAVEN\apache-maven-3.5.2\conf\settings.xml
```
## Maven: modificare posizione del local repository
Modifico la posizione del local repository modificando direttamente il file settings.xml:
p.e. con
```xml
<localRepository>D:/maven_repo</localRepository>
```
Per caricare librerie non disponibili sul repository maven ufficiale:
carico nel repository il driver jdbc oracle 
```
mvn install:install-file -Dfile=C:\\SOFTWARE\\JDBC\\ojdbc6.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0 -Dpackaging=jar
```
Per aggiornare il progetto su Eclipse:
```
[ECLIPSE] tasto destro sul progetto > Maven > Update Project
```
Nota: si devono vedere anche le Maven Dependencies cambiare
Nota: eventualmenta fare clean del progetto e tasto destro sul POM > Run As.. > Maven Clean

## Maven : run

Tasto destro sul progetto o su pom.xml > run as... > maven build... > valorizzare su Goals in base a 
quanto previsto dal lifecycle [link](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)

## Maven : pom.xml
esempio:
```xml
	<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>it.secservizi.CD.util.sample.hibernate.basic</groupId>
  <artifactId>it.secservizi.CD.util.sample.hibernate.ann.maven.basic</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>jar</packaging>

  <name>it.secservizi.CD.util.sample.hibernate.ann.maven.basic</name>
  <url>http://maven.apache.org</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

  	<!-- JBoss repository for Hibernate -->
	<repositories>
		<repository>
			<id>JBoss repository</id>
			<url>http://repository.jboss.org/nexus/content/groups/public/</url>
		</repository>
	</repositories>
	
  <dependencies>
    
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.8.2</version>
      <scope>test</scope>
    </dependency>

	<!-- ORACLE JDBC driver, need install yourself (vedere README.md) -->
	<dependency>
		<groupId>com.oracle</groupId>
		<artifactId>ojdbc6</artifactId>
		<version>11.2.0</version>
	</dependency>

	<!-- Hibernate: stessa versione del deployer -->
	<dependency>
		<groupId>org.hibernate</groupId>
		<artifactId>hibernate-core</artifactId>
		<version>3.6.10.Final</version>
	</dependency>
	
	<!-- slf4j-log4j : necessario  -->
	<dependency>
		<groupId>org.slf4j</groupId>
		<artifactId>slf4j-log4j12</artifactId>
		<version>1.6.1</version>
	</dependency>

	<dependency>
		<groupId>javassist</groupId>
		<artifactId>javassist</artifactId>
		<version>3.12.1.GA</version>
	</dependency>
    
  </dependencies>

	<build>
		<plugins>
			<plugin>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.1</version>
				<configuration>
					<source>1.7</source>
					<target>1.7</target>
				</configuration>
			</plugin>
		</plugins>
	</build>
  
</project>

```

## Maven : creare build.xml ANT da MAVEN
Una volta posizionati sulla directory dove presente pom.xml serve il comando
```
mvn ant:ant
```
che produce 
```
build.xml
maven-build.xml
maven-build.properties
```

## Hibernate : codice minimo utilizzando xml

### configurazione hibernate.cfg.xml

*ATTENZIONE* il file hibernate.cfg.xml e' facoltativo perche' la configurazione puo' essere fatta direttamente da codice java
Il file di configurazione hibernate.cfg.xml deve essere visibile da path (vedere il global POM per verifica) quindi va definito dentro
```
/src/main/resources
```
quindi nel mio caso
```
C:\WKS\wksOxyEE\it.secservizi.CD.util.sample.hibmaven\src\main\resources
```
definire file
```
hibernate.cfg.xml
```
```xml
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
"-//Hibernate/Hibernate Configuration DTD 3.0//EN"
"http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
 <session-factory>
  <property name="hibernate.connection.driver_class">oracle.jdbc.driver.OracleDriver</property>
  <property name="hibernate.connection.url">jdbc:oracle:thin:@127.0.0.1:1521:xe</property>
  <property name="hibernate.connection.username">STEFANO</property>
  <property name="hibernate.connection.password">STEFANO_01</property>
  <property name="hibernate.dialect">org.hibernate.dialect.Oracle10gDialect</property>
  <property name="hibernate.default_schema">STEFANO</property>
  <property name="show_sql">true</property>
  <mapping resource="it/secservizi/CD/util/user/DBUser.hbm.xml"></mapping>
</session-factory>
</hibernate-configuration>
```
*ATENZIONE* : la posizione non e' casuale perche' in un progetto maven src/main/resources e' automaticamente nel classpath e lo  si capisce guardando l'effective POM che contiene
```xml
    <resources>
      <resource>
        <directory>C:\WKS\wksOxyEE\it.secservizi.CD.util.sample.hibmaven\src\main\resources</directory>
      </resource>
    </resources>
```


### hbm.xml hibernate mapping file	

Nel nostro esempio
```
DBUser.hbm.xml 
```
Posizione src\main\resources e poi percorso simile alla classe a cui si riferisce
```
C:\WKS\wksOxyEE\it.secservizi.CD.util.sample.hibmaven\src\main\resources\it\secservizi\CD\util\user\DBUser.hbm.xml
```
codice
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">
<hibernate-mapping>
    <class name="it.secservizi.CD.util.sample.user.DBUser" table="DBUSER">
        <id name="userId" type="int">
            <column name="USER_ID" precision="5" scale="0" />
            <generator class="assigned" />
        </id>
        <property name="username" type="string">
            <column name="USERNAME" length="20" not-null="true" />
        </property>
        <property name="createdBy" type="string">
            <column name="CREATED_BY" length="20" not-null="true" />
        </property>
        <property name="createdDate" type="date">
            <column name="CREATED_DATE" length="7" not-null="true" />
        </property>
    </class>
</hibernate-mapping>
```
### Classe java per gestione sessione 
```
HibernateUtil.java
```
Dentro
```
C:\WKS\wksOxyEE\it.secservizi.CD.util.sample.hibmaven\src\main\java\it\secservizi\CD\util\sample\util\HibernateUtil.java
```
Codice
```java
package it.secservizi.CD.util.sample.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

}

```
### pojo
Classe java per rappresentare entita' nel nostro caso record in tabella

Posizione:
```
C:\WKS\wksOxyEE\it.secservizi.CD.util.sample.hibmaven\src\main\java\it\secservizi\CD\util\sample\user\DBUser.java
```
Codice
```java
package it.secservizi.CD.util.sample.user;

import java.util.Date;

/**
 * Dbuser generated by hbm2java
 */
public class DBUser implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int userId;
	private String username;
	private String createdBy;
	private Date createdDate;

	public DBUser() {
	}

	public DBUser(int userId, String username, String createdBy,
			Date createdDate) {
		this.userId = userId;
		this.username = username;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}

```

### Classe java main App.java
Dentro
```
C:\WKS\wksOxyEE\it.secservizi.CD.util.sample.hibmaven\src\main\java\it\secservizi\CD\util\sample\hibmaven\App.java
```
Codice
```java
package it.secservizi.CD.util.sample.hibmaven;

import java.util.Date;

import org.hibernate.Session;

import it.secservizi.CD.util.sample.user.DBUser;
import it.secservizi.CD.util.sample.util.HibernateUtil;


public class App {
	public static void main(String[] args) {
		System.out.println("Maven + Hibernate + Oracle");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		DBUser user = new DBUser();

		user.setUserId(100);
		user.setUsername("superman");
		user.setCreatedBy("system");
		user.setCreatedDate(new Date());

		session.save(user);
		session.getTransaction().commit();
	}
}
```

## Hibernate : codice minimo utilizzando annotation
1. Si elimina il file hbm.xml 
2. Si aggiungono le annotazioni in formato jpa sulla classe che rappresenta l'entita'

In particolare
```
import org.hibernate.annotations.Entity;
```
permette di usare il simbolo
```
@Entity
```

# Hibernate

## Hibernate : eclipse Reverse Engineering tutorial
Vedere [link](https://www.mkyong.com/hibernate/how-to-generate-code-with-hibernate-tools/)
con adattamenti dovuti a Eclipse Oxygen

## Hibernate: JBoss Tools in Eclipse (solo Hibernate)	

*PREMESSA* : utilizzato Eclipse Oxygen

Installare JBoss Tools:
Help > Eclipse Marketplace > cercare jboss tools > installata versione 4.5.1 solo Hibernate > ok nonostante warning su unsigned content 

Selezionare solo quanto obbligatorio e voce relativa a Hibernate

Restart

*NOTA* : su Mars invece utilizzato update-site scaricato localmente in particolare su
JBoss Tools - jar:file:/C:/SOFTWARE/UPDATE-SITE/jbosstools-4.3.0.Final-updatesite-core.zip!/
 
Per scaricare update site di JBoss Tools usare 
https://tools.jboss.org/downloads/overview.html

## Hibernate: hibernate configuration
Window > Perspective > Hibernate

Su Hibernate Configuration aggiungerne una nuova con:
- Hibernate Version 3.6 (nel nostro esempio)
- Database Connection: selezionare oracle > SID: XE, Connection URL: jdbc:oracle:thin:@localhost:1521:XE, Host: localhost, Port number: 1521, User Name: STEFANO, password: STEFANO_01, Catalog : User 
- Project : selezionare il progetto eclipse corrente p.e. it.secservizi.CD.util.sample.hibmaven
- configuration file: usare quello definito manualmente \it.secservizi.CD.util.sample.hibmaven\src\main\resources\hibernate.cfg.xml

## Hibernate: hibernate code generation configuration
Window > Perspective > Hibernate

Su barra con icone vicino a run trovare hibernate code generation configurations...
- su console configuration selezionare la hibernate configuration del punto precedente
- su output directory selezionare la cartella "target" del progetto corrente
- sul tab exporter selezionare: Generate EJB3 annotations, Domain code, Hibernate xml mapping, DAO code
- sul tab common impostare su share file la directory del progetto stesso per salvare la configuration su un file

## Hibernate: reverse engineering
Dalla vista Hibernate Configuration navigare fino a > hibernate > Database > \<default\> > DBUSER (esempio)

Utilizzare da tasto run con icona hibernate: Hibernate Code Generation configurations...
#### Tab main

- Console configuration: quanto definito al passo precedente (p.e. nome hibernate)
- Output directory: p.e. \it.secservizi.CD.util.sample.hibmaven\target
- Attivare flag: Reverse engineer from JDBC Connection
#### Tab exporters

Selezionare ad esempio:
- Domain Code (java)
- Hibernate XML mapping 

Per le annotation selezionare la voce
- Generate EJB3 annotations

####

# APPENDICE

## (DEPRECATED) Maven: creazione progetto per hibernate oracle da riga di comando

*ATTENZIONE* : questa alternativa non e' consigliata perche' crea alcuni problemi a Eclipse

Riferimento: [link](https://www.mkyong.com/hibernate/maven-3-hibernate-3-6-oracle-11g-example-xml-mapping/)

```
mvn archetype:generate -DgroupId=it.secservizi.CD.util.sample -DartifactId=MavenHibernateExample
-DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```
Entrare nella direcotry del progetto, nel nostro caso
```
C:\WMVN\MavenHibernateExample
```

Se si volesse convertirlo per Eclipse (ma e' *sconsigliato* vedere [link](https://stackoverflow.com/questions/35511860/how-do-i-get-my-eclipse-maven-project-to-automatically-update-its-classpath-when) )
```
mvn eclipse:eclipse
```
Il progetto risultante va importato in Eclipse.


