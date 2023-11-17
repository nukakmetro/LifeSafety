package com.example.lifesafetysem.servlets;

import com.example.lifesafetysem.ConnectionProvider;
import com.example.lifesafetysem.Dao.PhotoDao;
import com.example.lifesafetysem.Dao.UserDao;
import com.example.lifesafetysem.FreemarkerConfigSingleton;
import com.example.lifesafetysem.essence.User;
import freemarker.template.Template;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class ProfileCardServlet extends HttpServlet {

    UserDao userDao;
    PhotoDao photoDao;

    public void init() {
        FreemarkerConfigSingleton.setServletContext(this.getServletContext());
        try {
            ConnectionProvider connectionProvider = new ConnectionProvider();
            userDao = new UserDao(connectionProvider);
            photoDao = new PhotoDao(connectionProvider);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Template tmpl = FreemarkerConfigSingleton.getConfig().getTemplate("profile-card.ftl");
        String userlogin = req.getParameter("user_login");
        User user = userDao.getUserCard(userlogin);
        HashMap<String, Object> root = new HashMap<>();
        String imageName = photoDao.getNameById(user.getPhoto_id());
        if (imageName != null) {
            String image = "/Users/surexnx/Desktop/Photo_ls/" + imageName;
            root.put("image", image);
        }

        try {
            root.put("user", user);
            tmpl.process(root, resp.getWriter());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
