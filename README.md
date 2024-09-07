1. Table creation sql:

```
CREATE TABLE author (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(50), 
  last_name VARCHAR(50)
);
```

2. To generate classes by jooq:
```
mvn jooq-codegen:generate
```
