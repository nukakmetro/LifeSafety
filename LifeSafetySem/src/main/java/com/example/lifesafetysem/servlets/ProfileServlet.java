package com.example.lifesafetysem.servlets;

import com.example.lifesafetysem.ConnectionProvider;
import com.example.lifesafetysem.Dao.PhotoDao;
import com.example.lifesafetysem.Dao.UserDao;
import com.example.lifesafetysem.FreemarkerConfigSingleton;
import com.example.lifesafetysem.essence.User;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.HashMap;

@MultipartConfig
public class ProfileServlet extends HttpServlet {
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
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            HttpSession session = req.getSession();
            Boolean loginIn = (Boolean) session.getAttribute("loginIn");
            HashMap<String, Object> root = new HashMap<>();

            if (loginIn != null && loginIn) {
                root.put("session", session);
                long userId = (long) session.getAttribute("userId");
                User user = userDao.getFullUser(userId);
                root.put("user", user);
                String imageName = photoDao.getNameById(user.getPhoto_id());
                if (imageName != null) {
                    String image = "/Users/surexnx/Desktop/Photo_ls/" + imageName;
                    root.put("image", image);
                }
            }
            Template tmpl = FreemarkerConfigSingleton.getConfig().getTemplate("profile.ftl");
            tmpl.process(root, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("photo");

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Template tmpl = FreemarkerConfigSingleton.getConfig().getTemplate("profile.ftl");
        HttpSession session = req.getSession();
        HashMap<String, Object> root = new HashMap<>();
        Boolean loginIn = (Boolean) session.getAttribute("loginIn");
        long userId = (long) session.getAttribute("userId");
        if (filePart != null) {
            String fileName = getSubmittedFileName(filePart);
            if (fileName != null && !fileName.isEmpty()) {
                // Сохраните имя файла в базе данных
                photoDao.setFileName(fileName);
                userDao.setPhotoId(photoDao.getIdByName(fileName), userId);

                String storagePath = "/Users/surexnx/Desktop/Photo_ls/" + fileName;
                saveFileToStorage(filePart, storagePath);
            }
        }
    }

    private void saveFileToStorage(Part filePart, String storagePath) {
        try (InputStream fileContent = filePart.getInputStream()) {
            Path filePath = Path.of(storagePath);
            Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace(); // Обработайте ошибку сохранения файла
        }
    }

    private String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
