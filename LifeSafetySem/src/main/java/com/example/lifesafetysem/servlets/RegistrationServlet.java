package com.example.lifesafetysem.servlets;

import com.example.lifesafetysem.ConnectionProvider;
import com.example.lifesafetysem.Dao.UserDao;
import com.example.lifesafetysem.FreemarkerConfigSingleton;
import com.example.lifesafetysem.HashPassword;
import com.example.lifesafetysem.essence.User;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class RegistrationServlet extends HttpServlet {
    UserDao userDao;

    public void init() {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
        try {
            userDao = new UserDao(new ConnectionProvider());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HashMap<String, Object> root = new HashMap<>();
        String redirectPath;
        HttpSession session = req.getSession();
        Boolean loginIn = (Boolean) session.getAttribute("loginIn");

        if (loginIn != null && loginIn) {
            redirectPath = req.getContextPath() + "/main";
            resp.sendRedirect(redirectPath);
        } else {
            resp.setContentType("text/html");
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            //root.put("cap", "");
            Template tmpl = FreemarkerConfigSingleton.getConfig().getTemplate("registration.ftl");
            try {
                tmpl.process(root, resp.getWriter());
            } catch (TemplateException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> root = new HashMap<>();
        resp.setContentType("text/html");
        Template tmpl = FreemarkerConfigSingleton.getConfig().getTemplate("registration.ftl");

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String redirectPath;

        String login = req.getParameter("login");
        String password = HashPassword.HashingPaassword(req.getParameter("password"));

        if (userDao.loginRepeatCheck(login)) {
            userDao.create(new User(login, password));
            redirectPath = req.getContextPath() + "/auth";
            resp.sendRedirect(redirectPath);

        } else {
            root.put("warning", "Такой пользователь уже зарегистрирован");
            try {
                tmpl.process(root, resp.getWriter());
            } catch (TemplateException e) {
                throw new RuntimeException(e);
            }
        }
    }
}