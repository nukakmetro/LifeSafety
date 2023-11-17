package com.example.lifesafetysem.servlets;

import com.example.lifesafetysem.ConnectionProvider;
import com.example.lifesafetysem.Dao.UserDao;
import com.example.lifesafetysem.FreemarkerConfigSingleton;
import com.example.lifesafetysem.HashPassword;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class AuthServlet extends HttpServlet {
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Boolean loginIn = (Boolean) session.getAttribute("loginIn");
        String redirectPath = null;
        if (loginIn != null && loginIn) {
            redirectPath = req.getContextPath() + "/main";
        }else {
            HashMap<String, Object> root = new HashMap<>();
            resp.setContentType("text/html");
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            //root.put("cap", "");
            Template tmpl = FreemarkerConfigSingleton.getConfig().getTemplate("auth.ftl");
            try {
                tmpl.process(root, resp.getWriter());
            } catch (TemplateException e) {
                throw new RuntimeException(e);
            }
        }
        resp.sendRedirect(redirectPath);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            Boolean loginIn = (Boolean) session.getAttribute("loginIn");
            HashMap<String, Object> root = new HashMap<>();
            root.put("session", session);

            resp.setContentType("text/html");
            Template tmpl = FreemarkerConfigSingleton.getConfig().getTemplate("auth.ftl");
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            String redirectPath;

            String login = req.getParameter("login");
            String password = HashPassword.HashingPaassword(req.getParameter("password"));

            if (loginIn != null && loginIn) {
                redirectPath = req.getContextPath() + "/main";
            } else {
                Long userId = userDao.auth(login, password);
                if (userId != 0L) {
                    session.setAttribute("loginIn", true);
                    session.setAttribute("userId", userId);
                    session.setAttribute("user_login", login);
                    root.put("session", session);
                    redirectPath = req.getContextPath() + "/main";

                } else {
                    root.put("warning", "Не зарегестрирован");
                        tmpl.process(root, resp.getWriter());

                    redirectPath = req.getContextPath() + "/auth";
                }
            }
            resp.sendRedirect(redirectPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
