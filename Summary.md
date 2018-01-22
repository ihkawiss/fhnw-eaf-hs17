# EAF Summary
Persönliche Zusammenfassung im Modul eaf HS17. Irrtum vorbehalten. Ausführungen beziehen sich vorwiegen auf das Spring Framework.

## Dependency Injection
DI setzt sich aus 3 Teilen zusammen, der Komponente, dem Schema und der Infrastruktur.

##### Komponente
Spring Beans sind normale Java-Objekte (POJOs). Wird dieses durch einen Spring Container verwaltet, wird es zum Spring Bean.
##### Schema
Bauanleitung wie Beans zu einem Gesamtsystem zusammengebaut werden. In Spring XML, Annotations, Java oder CoC.
##### Infrastruktur
Der Spring Container enthält und verwaltet den Lebenszyklus und die Konfiguration von Java-Objekten.

### Varianten der Konfiguration
#### XML (Standard)
Welche POJOs als Beans aufgenommen werden sollen wird mittels XML definiert. Ebenso wird das Wiring zwischen den Beans konfiguriert.
```XML
<context:property-placeholder location="classpath:application.properties" />

<bean id="renderer" class="ch.fhnw.edu.eaf.springioc.renderer.StandardOutRenderer">
	<property name="messageProvider" ref="provider" />
</bean>

<bean id="provider"	class="ch.fhnw.edu.eaf.springioc.provider.ExternalizedConstructorMessageProvider">
	<constructor-arg name="message" value="${hello.message}" />
</bean>
```
#### Annotations
Beans werden mit ```@Component``` registriert. Abhängigkeiten zwischen Beans müssen mittels ```@Autowiring``` eingesteckt werden. Damit das Spring Framework die Beans automatisch erkennt, muss ```@ComponentScan``` aktiviert werden.
```XML
<context:component-scan base-package="ch.fhnw.edu.eaf.app.domain" />
```
Context in einem JUnit Test definieren:
```Java
@ContextConfiguration(locations = {"/spring/annotationTest.xml"})
```

#### Java Configuration
Konfigurationen können ebenso auch in einer Java-Klasse gelöst werden. Hierzu ein Bespiel.
```Java
@Configuration
@ComponentScan(basePackages = {"package1", "package2", …})
@PropertySource("application.properties")
public class AppConfig {
   @Bean
   public Foo foo() {
      return new Foo(bar()); // inject Bar as dependency
   }
   @Bean
   public Bar bar() {
      return new Bar();
   }
}
```
Context in einem JUnit Test definieren:
```Java
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
```

#### Convention over Configuration (CoC)
Spring bringt bereits viele vordefinierte Konventionen mit, es kann also auf einiges an Konfiguration verzichtet werden – solange die Konventionen eingehalten werden.
```Java
@SpringBootConfiguration
@ComponentScan(basePackages = {"ch.fhnw.edu.eaf.app"})
@SpringBootTest(classes = {AppConfigCoC.class})
```

### Dependeny Injection Pitfalls
Folgend sind klassische Fehler im Umgang mit DI und Spring aufgelistet.
1. Einem Bean-Property wird mittels Setter-Injection ein Wert zugewiesen. Das Feld in der Klasse ist jedoch als private deklariert. Lösung: Constructor Injection
2. Mehrere Beans mit identischem Identifier definiert = Exception (id = Unique!). Weitere Definition des selben Beans ohne id = keine Exception.
3. Ein Bean kann auch «By-Class» referenziert werden. Wenn das Bean nicht eindeutig definiert ist, siehe (2), wird eine Exception geworfen.
4. Zirkuläre Abhängigkeit A > B > C > A
  - Setter Injection: Kein Problem, es werden erst alle Instanzen erzeugt, danach werden die Abhängigkeiten aufgelöst.
  - Constructor Injection: Führt zu einer Exception, Instanzen stehen bei Auflösung der Abhängigkeiten noch nicht zur Verfügung.

## DAO Pattern
Jegliche Datenbankzugriffe werden über DAO-Objekte, auch Repository genannt, abgewickelt. Jedes DAO ist für CRUD Operationen auf eine Entität zuständig. Transaktionen, Sessions oder Verbindungen werden nicht durch das DAO verwaltet. Mit dem DAO Pattern wird der Zugriff auf den Datenbank-Layer vom Rest der Anwendung (Business Logik) gekapselt. Ebenso wird die Wart- und Testbarkeit optimiert.

### Template
```Java
public interface Repository<T, ID extends Serializable> {
	T findOne(ID id);
	List<T> findAll();
	T save(T t);	// used for create and update
	void delete(ID id);
	void delete(T entity);
	booleanexists(ID id);
	long count();
}
```

### Service Layer
Core API durch welche andere Teile der Anwendung kommunizieren (Façade Pattern). Hier wird üblicherweise die Business Logik implementiert, welche dann über DAOs auf den Persistance-Layer zugreift.

### Persistance
#### Plain JDBC
Die Implementierung eines DAOs ist mittels JDBC höchst umständlich. In jedem DAO müssen Verbindung und Exceptions verwaltet sowie eine Menge an Boiler-Plate Code geschrieben werden. Spring bietet hier Template Klassen an, welche diese Arbeiten bereits zum grossen Teil erledigen.
#### JDBC Template Pattern
Reduziert redundaten Code, verwaltet Ressourcen (Verbindungen), lässt sich einfach Injecten und vereinheitlicht das Exception handling. Konkret kann entweder ein ```JdbcTemplate``` oder ein ```NamedParameterJdbcTemplate``` verwendet werden. Letzteres ermöglicht SQL Anfragen welche benannte Parameter wie z.B. ```:id``` enthalten.
##### Methoden
- execute
- query
- update
- batchUpdate

##### Beispiel
```Java
@Override
public List<Movie> findAll() {
	return template.query(
		"select * from Movies",
		(rs, row) -> createMovie(rs) // callback method returns Movie instance per row
	);
}
```

## Java Persistance API
JPA übernimmt das Verwalten des Persistierens sowie das Mapping von Objekten gegenüber dem Persistance Layer (ORM).
### Entity Manager
Wie der Name bereits impliziert, verwaltet der EM die Entitäten und ermöglicht Zugriff via find, persist, update und remove auf diese (ähnlich wie bei einem Repository). Der Lifecycle der Entiät wird durch den EM kontrolliert. Konfiguriert werden Entitäten über ```@Entity``` Annotationen oder XML Dateien.
#### Beispiel
```Java
@Entity
public class Movie {
	@Id
	private Long id;
}

public class MovieRepository {
	@PersistenceContext
	private EntityManagerem;
	public void saveNewMovie(String title, Date date) {
		Movie m = new Movie(title, date);
		em.persist(m);
	}
}
```
#### Methoden
- **persist** Macht Objekt managed und persistiert
- **remove** Löscht Instanz von der Datenbank
- **find** Findet Entität bei PK/ID
- **merge** Merges gegebene Entität mit dem Persistance Context, neue Instanz wird returnt
- **refresh** Entität neu von der Datenbank laden, Änderungen werden verworfen
- **flush** Schreibt den Persistance Context auf die Datenbank
- **contains** prüft ob Entität zu dem Context gehört (nicht ob diese auf der DB vorhanden ist)
- **clear** Context bereinigen, alle managed Objects werden detachted

#### Persistance Context
Dies ist eine Set von verwalteten Objekten welche durch den Entity Manager verwaltet werden.
Eine Persistance Unit ist z.B. eine definierte Datenbank, diese können wie folgt auf einem EM definiert werden.
```Java
@PersistenceContext(name="movierental") // easy switching between persistance units
```

### Entity Annotations
Folgend die wichtigsten Annotationen für Entitätsklassen im JPA/Hibernate Framework.

```@Entity``` = markiert POJO als Entität, somit managed durch Entity Manager  
```@Table(name="MyTableName")``` = definiert manuell Namen der Tabelle  
```@Id``` = markiert Feld als PK  
```@GeneratedValue(strategy=GenerationType.IDENTITY)``` = definiert wie Id Wert generiert werden soll  
```@Column(name="MyColumn")``` = manuelle Definition des Spaltennamens  
```@Basic``` = markiert Feld das persistiert werden soll  
```@Enumerated``` = definiert wie Enumeration persistiert werden soll (EnumType.ORDINAL oder EnumType.STRING)  
```@Lob``` = markiert Feld als large object, also BLOB Feld  
```@Transient``` = Markiert Feld das nicht persistiert werden soll
```@Temporal``` = Markiert Datumsfelder, lässt Format/Präzision definieren

Bei ```@Entity```, ```@Table``` sowie ```@Column``` ist der Name jeweils der UQN des annotierten Felds/Klasse.

### Primary Key generation
Folgende Möglichkeiten zur Generierung von PKs sind vorhanden.  
- Assigned (Applikation regelt Generierung/Zuweisung selbst)
- Identity (Klassisches Auto-Increment)
- Sequence (Generator wie UUID in MSSQL, ORACLE)
- Table (PKs werden in seperater Tabelle geführt)

### Queries
In JPA können Queries natürlich auch selbst geschrieben werden, dies in sogn. JPQL. Bespiel:
```Java
TypedQuery<Movie> q = em.createQuery("SELECT m FROM Movie m WHERE m.title= :title", Movie.class);
q.setParameter("title", title);
List<Movie> movies = q.getResultList();
```

### Associations
Beziehungen zwischen Entitäten können mittels JPA/Hibernate genau so definiert und benutzt werden. Beziehungen können unidirectional oder bidirectional sein. Für bidirektionale Beziehungen muss eine Seite mit ```mappedBy="FIELD_NAME"``` markiert werden.
#### Collection type
Werden Collections in Beziehungen verwendet, so muss der Typ des Ziels immer bekannt gemacht werden.
```Java
// manual definition
@OneToMany(targetEntity=Order.class)
public Collection orders;

// Explicit
@OneToMany
Collection<Order> orders;
```

### ```@ManyToOne```
**Owner:** Many Seite
```Java
@Entity
public class Rental {
	@ManyToOne // This is the owner of the relationship
	@JoinColumn(name="USER_FK") // optional
	private User user;
}

@Entity
public class User {
	@OneToMany(mappedBy="user")
	private Collection<Rental> rentals;
}
```

### Inheritance
Alle Klassen in der Vererbungshirarchie müssen mit ```@Entity``` annotiert werden. Die einzelnen Klassen werden **immer** in eigenen Tabellen gespeichert.

```@DiscriminatorColumn(name="PRICECATEGORY_TYPE") ``` defines name of column where dynamic type is stored  
```@DiscriminatorValue("Children")``` defines value on concrete subclass
