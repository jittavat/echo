# Echo

### Prerequisite
- Java 11
- Docker
- Gradle

### Build & Run
```
./gradlew clean build
docker-compose up --build
```

### Pros
- Separate business & logic from dependency
- Easier to unit test business & logic

### Cons
- Can't mock the unit test in service(use case) level
