package web;

import model.User;

import static model.User.START_SEQ;

public class UserTestData {
    public static final int FIRST_ID = START_SEQ;
    public static final int NOT_FOUND = 10;

    public static final User user1 = new User(FIRST_ID, "User1", "user1@yandex.ru", "password1");
    public static final User user2 = new User(FIRST_ID + 1, "User2", "user2@yandex.ru", "password2");
    public static final User user3 = new User(FIRST_ID + 2, "User3", "user3@yandex.ru", "password3");
    public static final User user4 = new User(FIRST_ID + 3, "User4", "user4@yandex.ru", "password4");
    public static final User user5 = new User(FIRST_ID + 4, "User5", "user5@yandex.ru", "password5");


    public static User getNew() {
        return new User(null, "New", "new@yandex.ru", "newPass");
    }

    public static User getUpdated() {
        User updated = new User(user1);
        updated.setEmail("update@yandex.ru");
        updated.setName("UpdatedName");
        updated.setPassword("newPass");
        return updated;
    }
}
