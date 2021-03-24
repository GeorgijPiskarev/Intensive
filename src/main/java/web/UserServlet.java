package web;

import model.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;
import web.command.*;
import web.users.UserController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static web.command.Command.getId;

public class UserServlet extends HttpServlet {
    private final Map<String, Command> commandMap = new HashMap<>();
    private ConfigurableApplicationContext springContext;
    private UserController userController;

    @Override
    public void init() {
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        userController = springContext.getBean(UserController.class);
        commandMap.put("create", new Create(userController));
        commandMap.put("delete", new Delete(userController));
        commandMap.put("update", new Update(userController));
        commandMap.put("all", new All(userController));
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        User user = new User(null, request.getParameter("name"), request.getParameter("email"),
                request.getParameter("password"));

        if (StringUtils.isEmpty(request.getParameter("id"))) {
            userController.create(user);
        } else {
            userController.update(user, getId(request));
        }
        response.sendRedirect("users");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        Command command = action == null ? commandMap.get("all") : commandMap.get(action);
        command.execute(request, response);
    }
}
