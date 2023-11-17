package com.example.lifesafetysem.servlets;

import com.example.lifesafetysem.ConnectionProvider;
import com.example.lifesafetysem.Dao.UserDao;
import com.example.lifesafetysem.FreemarkerConfigSingleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class FeedbackServlet extends HttpServlet {
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
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
