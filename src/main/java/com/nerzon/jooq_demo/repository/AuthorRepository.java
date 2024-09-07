package com.nerzon.jooq_demo.repository;

import com.nerzon.jooq.generated.public_.tables.Author;
import com.nerzon.jooq.generated.public_.tables.records.AuthorRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepository {

    private final DSLContext dsl;

    public AuthorRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public List<AuthorRecord> findAll() {
        return dsl.selectFrom(Author.AUTHOR)
                .fetch();
    }

    public AuthorRecord findById(int id) {
        return dsl.selectFrom(Author.AUTHOR)
                .where(Author.AUTHOR.ID.eq(id))
                .fetchOne();
    }

    public void insertAuthor(String firstName, String lastName) {
        dsl.insertInto(Author.AUTHOR, Author.AUTHOR.FIRST_NAME, Author.AUTHOR.LAST_NAME)
                .values(firstName, lastName)
                .execute();
    }

    public void updateAuthor(int id, String firstName, String lastName) {
        dsl.update(Author.AUTHOR)
                .set(Author.AUTHOR.FIRST_NAME, firstName)
                .set(Author.AUTHOR.LAST_NAME, lastName)
                .where(Author.AUTHOR.ID.eq(id))
                .execute();
    }

    public void deleteAuthor(int id) {
        dsl.deleteFrom(Author.AUTHOR)
                .where(Author.AUTHOR.ID.eq(id))
                .execute();
    }
}