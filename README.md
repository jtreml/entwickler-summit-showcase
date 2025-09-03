Perfect 👍 Then here’s the full updated README.md with both Maven and Maven Wrapper instructions included:

# Developer Summit Showcase – Vaadin Demo

This is a small demo project to get started with **[Vaadin](https://vaadin.com/)**.  
Vaadin is a Java framework for building **modern web apps** entirely in Java – no need to write HTML, CSS, or JavaScript.

## 🚀 Running the project

Requirements:
- Java 21+

### Start the application

Using **Maven directly**:
```bash
mvn spring-boot:run
```
Using the Maven Wrapper (preferred, included in the project):

#### Linux / macOS
```bash
./mvnw spring-boot:run
```
#### Windows
```bash
mvnw.cmd spring-boot:run
```

Once started, open:
👉 http://localhost:8080

## 📖 What does this demo show?

The app includes:
	•	an in-memory H2 database with sample persons
	•	a Spring Data repository & service layer
	•	a Vaadin view that demonstrates how to build a UI with just Java

HelloWorldView.java

The main view (@Route("")) shows:
	1.	A Grid displaying people (first name, last name, email)
	2.	A search field for filtering
	3.	Data binding to the repository in just a few lines of code

```java
Grid<SamplePerson> grid = new Grid<>(SamplePerson.class);
grid.setColumns("firstName", "lastName", "email");

TextField filterField = new TextField("Search by name...");
filterField.addValueChangeListener(event -> {
    String searchText = event.getValue();
    dataView.setFilter(person ->
        person.getFirstName().toLowerCase().contains(searchText) ||
        person.getLastName().toLowerCase().contains(searchText)
    );
});
```

## 🧩 Project structure

src/main/java/com/vaadin/demo/
 ├── data/
 │   ├── SamplePerson.java            # Entity
 │   ├── SamplePersonRepository.java  # Spring Data Repository
 └── views/helloworld/
     └── HelloWorldView.java          # UI (Vaadin View)

🔍 Learn more
	•	Vaadin Documentation
	•	Vaadin Start Tool – create your own project in seconds
	•	Vaadin Components – full component catalog

⸻
👉 With Vaadin you can build full-stack web apps in pure Java – no frontend hassle required.
---
Do you also want me to add a **note that the wrapper is recommended for presentations/workshops**, so participants don’t need to install Maven globally?