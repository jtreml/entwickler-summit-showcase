# Developer Summit Showcase – Vaadin Demo

This is a small demo project to get started with **[Vaadin](https://vaadin.com/)**.  
Vaadin is a Java framework for building **modern web apps** entirely in Java – no need to write HTML, CSS, or JavaScript.

## 🚀 Running the project

Requirements:
- Java 21+
- Maven 3.8+ (optional )

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
- An in-memory H2 database with an example data type SamplePerson
- A Spring Data repository & service layer
- A Vaadin view that demonstrates how to build a UI with just Java

### HelloWorldView.java

The main view (`@Route("")`) shows:
1. A Grid displaying people (first name, last name, email)
2. A search field for filtering
3. Data binding to the repository in just a few lines of code

```java
Grid<SamplePerson> grid = new Grid<>(SamplePerson.class);
grid.setColumns("firstName", "lastName", "email");

TextField filterField = new TextField("Search by name...");
filterField.addValueChangeListener(event -> {
    String searchText = event.getValue();
    dataView.setFilter(person ->
        person.getFirstName().toLowerCase().startsWith(searchText) ||
        person.getLastName().toLowerCase().startsWith(searchText)
    );
});
```

## 🧩 Project structure

```
src/main/java/com/vaadin/demo/
├── data/
│   ├── SamplePerson.java            # Entity
│   ├── SamplePersonRepository.java  # Spring Data Repository
└── views/helloworld/
    └── HelloWorldView.java          # UI (Vaadin View)
```

## 🔍 Learn more

- [Vaadin Documentation](https://vaadin.com/docs)
- [Vaadin Start Tool](https://start.vaadin.com/) – create your own project in seconds
- [Vaadin Components](https://vaadin.com/components) – full component catalog

---

👉 With Vaadin you can build full-stack web apps in pure Java – no frontend hassle required.