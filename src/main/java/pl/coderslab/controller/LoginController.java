package pl.coderslab.controller;

import org.w3c.dom.UserDataHandler;
import pl.coderslab.entity.User;
import pl.coderslab.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginController",urlPatterns = "/login")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("user");
        String password = request.getParameter("password");

        User user = UserService.login(username, password);
        if(user != null){
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        }else{
            response.getWriter().append("Błędne dane logowania");
            doGet(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/user/loginForm.jsp")
                .forward(request,response);
    }
}
