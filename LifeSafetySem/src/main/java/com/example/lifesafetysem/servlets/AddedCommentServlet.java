package com.example.lifesafetysem.servlets;


import com.example.lifesafetysem.ConnectionProvider;
import com.example.lifesafetysem.Dao.CommentDao;
import com.example.lifesafetysem.Dao.PostDao;
import com.example.lifesafetysem.FreemarkerConfigSingleton;
import com.example.lifesafetysem.essence.Comment;
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

public class AddedCommentServlet extends HttpServlet {
    CommentDao commentDao;
    PostDao postDao;

    @Override
    public void init() throws ServletException {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
        try {
            ConnectionProvider connectionProvider = new ConnectionProvider();
            postDao = new PostDao(connectionProvider);
            commentDao = new CommentDao(connectionProvider);
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
            long postId = Long.parseLong(req.getParameter("postId"));

            if(loginIn != null && loginIn){
                root.put("loginIn", loginIn);
                root.put("postId", postId);
            }
            List<Comment> comments = new ArrayList<>(commentDao.getCommentsById(postId));

            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            boolean postComments = postDao.getPostComments(postId);

            resp.setContentType("text/html");
            Template tmpl = FreemarkerConfigSingleton.getConfig().getTemplate("add-comment.ftl");
            root.put("postComments", postComments);
            root.put("comments", comments);


            tmpl.process(root, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
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

        long postId = Long.parseLong(req.getParameter("postId"));
        Boolean loginIn = (Boolean) session.getAttribute("loginIn");
        if (loginIn != null && loginIn) {
            commentDao.setComment(new Comment(
                    (Long) session.getAttribute("userId"),
                    postId,
                    req.getParameter("comment"),
                    0L,
                    (String) session.getAttribute("user_login")

            ));
            redirectPath = req.getContextPath() + "/post";

           // redirectPath = req.getContextPath() + "/main";

        } else {
            redirectPath = req.getContextPath() + "/main";
        }
        resp.sendRedirect(redirectPath);
    }
}

