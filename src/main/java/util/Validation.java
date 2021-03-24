package util;

import model.User;

public class Validation {
    public static <T> T checkNotFoundWithId(T object, int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void assureIdConsistent(User user, int id) {
        if (user.isNew()) {
            user.setId(id);
        } else if (user.getId() != id) {
            throw new IllegalArgumentException(user + " must be with id=" + id);
        }
    }
}
