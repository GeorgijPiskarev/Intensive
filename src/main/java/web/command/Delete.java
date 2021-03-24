package web.command;

import web.users.UserController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Delete extends Command {
    public Delete(UserController userController) {
        super(userController);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = getId(request);
        userController.delete(id);
        response.sendRedirect("users");
    }
}
