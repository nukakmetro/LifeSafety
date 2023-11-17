package com.example.lifesafetysem.servlets;

import com.example.lifesafetysem.ConnectionProvider;
import com.example.lifesafetysem.Dao.CommentDao;
import com.example.lifesafetysem.Dao.PostDao;
import com.example.lifesafetysem.FreemarkerConfigSingleton;
import com.example.lifesafetysem.essence.Comment;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainServlet extends HttpServlet {

    PostDao postDao;
    CommentDao commentDao;

    public void init() {
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
        try {
            HttpSession session = req.getSession();
            Boolean loginIn = (Boolean) session.getAttribute("loginIn");
            HashMap<String, Object> root = new HashMap<>();
            String redirectPath = null;


            List<Post> posts = new ArrayList<>(postDao.getAll());


            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");

            resp.setContentType("text/html");
            Template tmpl = FreemarkerConfigSingleton.getConfig().getTemplate("main-page.ftl");

                root.put("posts", posts);

            tmpl.process(root, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

         @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
             String redirectPath;
             req.setCharacterEncoding("UTF-8");
             resp.setCharacterEncoding("UTF-8");

             Template tmpl = FreemarkerConfigSingleton.getConfig().getTemplate("add-post.ftl");
             HttpSession session = req.getSession();
             HashMap<String, Object> root = new HashMap<>();
             Boolean loginIn = (Boolean) session.getAttribute("loginIn");

             try {
                 tmpl.process(root, resp.getWriter());
             } catch (TemplateException e) {
                 throw new RuntimeException(e);
             }

    }
}
