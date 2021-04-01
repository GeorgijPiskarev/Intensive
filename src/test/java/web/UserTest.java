package web;

import model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import repository.UserRepository;
import util.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static web.UserTestData.*;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void create() {
        User created = repository.save(getNew());
        int newId = created.id();
        User newUser = getNew();
        newUser.setId(newId);
        assertThat(created).isEqualTo(newUser);
    }

    @Test
    public void delete() {
        repository.delete(FIRST_ID);
        assertThrows(NotFoundException.class, () -> repository.get(FIRST_ID));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> repository.delete(NOT_FOUND));
    }

    @Test
    public void get() {
        User user = repository.get(FIRST_ID);
        assertThat(user).isEqualTo(user1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> repository.get(NOT_FOUND));
    }

    @Test
    public void update() {
        User updated = getUpdated();
        repository.save(updated);
        assertThat(repository.get(FIRST_ID)).isEqualTo(updated);
    }

    @Test
    public void getAll() {
        List<User> all = repository.getAll();
        assertEquals(all, Arrays.asList(user1, user2, user3, user4, user5));
    }
}
