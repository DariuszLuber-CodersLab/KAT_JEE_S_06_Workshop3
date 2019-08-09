package pl.coderslab.controller;

import pl.coderslab.entity.User;
import pl.coderslab.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterConrtroller", urlPatterns = "/register")
public class RegisterConrtroller extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String user = request.getParameter("user");
            String pass = request.getParameter("pass");
            String mail = request.getParameter("mail");

            User newUser = UserService.register(user,pass, mail);

            response.sendRedirect("/login");

        } catch (Exception e) {
           response.getWriter().append(e.getMessage());
//           doGet(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/user/registerForm.jsp")
                .forward(request,response);
    }
}
