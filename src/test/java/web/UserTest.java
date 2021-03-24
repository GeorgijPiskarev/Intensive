package web;

import model.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import util.NotFoundException;
import web.users.UserController;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static web.UserTestData.*;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml"
})
//@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserTest {
    @Autowired
    private UserController controller;

    @Disabled
    @Test
    public void create() {
        User created = controller.create(getNew());
        int newId = created.id();
        User newUser = getNew();
        newUser.setId(newId);
        assertThat(created).isEqualTo(newUser);
    }
    @Disabled
    @Test
    public void delete() {
        controller.delete(FIRST_ID);
        assertThrows(NotFoundException.class, () -> controller.get(FIRST_ID));
    }
    @Disabled
    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> controller.delete(NOT_FOUND));
    }

    @Disabled
    @Test
    public void get() {
        User user = controller.get(FIRST_ID);
        assertThat(user).isEqualTo(user1);
    }
    @Disabled
    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> controller.get(NOT_FOUND));
    }
    @Disabled
    @Test
    public void update() {
        User updated = getUpdated();
        controller.update(updated, updated.getId());
        assertThat(controller.get(FIRST_ID)).isEqualTo(updated);
    }
    @Disabled
    @Test
    public void getAll() {
        List<User> all = controller.getAll();
        assertEquals(all, Arrays.asList(user1, user2, user3, user4, user5));
    }
}
