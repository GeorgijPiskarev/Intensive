package web.command;

import model.User;
import web.users.UserController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Create extends Command {
    public Create(UserController userController) {
        super(userController);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("user", new User());
        request.getRequestDispatcher("/userForm.jsp").forward(request, response);
    }
}
