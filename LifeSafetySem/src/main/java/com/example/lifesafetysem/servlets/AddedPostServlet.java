package com.example.lifesafetysem.servlets;

import com.example.lifesafetysem.ConnectionProvider;
import com.example.lifesafetysem.Dao.CommentDao;
import com.example.lifesafetysem.Dao.PostDao;
import com.example.lifesafetysem.FreemarkerConfigSingleton;
import com.example.lifesafetysem.essence.Post;
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

public class AddedPostServlet extends HttpServlet {
    PostDao postDao;
    CommentDao commentDao;

    @Override
    public void init() throws ServletException {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
        try {
            postDao = new PostDao(new ConnectionProvider());
            commentDao = new CommentDao(new ConnectionProvider());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        HashMap<String, Object> root = new HashMap<>();
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String redirectPath;
        Template tmpl = FreemarkerConfigSingleton.getConfig().getTemplate("add-post.ftl");
        Boolean loginIn = (Boolean) session.getAttribute("loginIn");

        if (loginIn != null && loginIn) {
            try {
                tmpl.process(root, resp.getWriter());
            } catch (TemplateException e) {
                throw new RuntimeException(e);
            }
        } else {
            redirectPath = req.getContextPath() + "/main";
            resp.sendRedirect(redirectPath);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Template tmpl = FreemarkerConfigSingleton.getConfig().getTemplate("add-post.ftl");
        HttpSession session = req.getSession();
        HashMap<String, Object> root = new HashMap<>();
        String redirectPath = null;
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Boolean loginIn = (Boolean) session.getAttribute("loginIn");
        if (loginIn != null && loginIn) {

            String checkboxValue = req.getParameter("commentBool");

            long userId = (Long) session.getAttribute("userId");
            long postId = postDao.setUserId(userId);
            postDao.setTitle(req.getParameter("postTitle"), postId);
            postDao.setContent(req.getParameter("postContent"), postId);
            postDao.setUserLogin((String) session.getAttribute("user_login"), postId);

            if (checkboxValue != null && checkboxValue.equals("on")) {
                postDao.setPostComments(true, postId);
            }

            redirectPath = req.getContextPath() + "/main";

        } else {
            redirectPath = req.getContextPath() + "/main";
        }
        resp.sendRedirect(redirectPath);
    }
}
