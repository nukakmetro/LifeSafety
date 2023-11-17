package com.example.lifesafetysem.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ExitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Boolean loginIn = (Boolean) session.getAttribute("loginIn");
        String redirectPath = req.getContextPath() + "/main";
        if(loginIn != null && loginIn){
            session.removeAttribute("loginIn");
            session.removeAttribute("userId");
            session.removeAttribute("user_login");

        }
        resp.sendRedirect(redirectPath);
    }
}
