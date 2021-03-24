package web.command;

import web.users.UserController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public abstract class Command {
    protected UserController userController;

    public Command(UserController userController) {
        this.userController = userController;
    }

    public static int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
