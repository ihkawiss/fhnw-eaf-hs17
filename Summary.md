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
