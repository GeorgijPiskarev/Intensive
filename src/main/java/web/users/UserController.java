package web.users;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import repository.UserRepository;

import java.util.List;

import static util.Validation.assureIdConsistent;
import static util.Validation.checkNotFoundWithId;

@Controller
public class UserController {

    @Autowired
    private UserRepository repository;

    public List<User> getAll() {
        return repository.getAll();
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public User create(User user) {
        return repository.save(user);
    }

    public void update(User user, int id) {
        assureIdConsistent(user, id);
        checkNotFoundWithId(repository.save(user), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }
}
