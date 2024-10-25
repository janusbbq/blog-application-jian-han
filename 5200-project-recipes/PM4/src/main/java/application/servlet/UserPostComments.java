package application.servlet;

import application.dal.*;
import application.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/userpostcomments")
public class UserPostComments extends HttpServlet {

    protected CommentsDao commentsDao;

    @Override
    public void init() throws ServletException {
        commentsDao = CommentsDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        List<Comments> userComments = new ArrayList<>();

        // Retrieve user name from request parameter.
        String userName = req.getParameter("username");

        try {
            if (userName != null && !userName.trim().isEmpty()) {
                // If username parameter is provided, retrieve comments for that user.
                Users user = new Users(userName);
                userComments = commentsDao.getCommentsForUser(user);
                messages.put("title", "Comments for User: " + userName);
            } else {
                // If username parameter is not provided, display error message.
                messages.put("title", "Invalid username parameter.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }

        req.setAttribute("userComments", userComments);
        req.getRequestDispatcher("/UserPostComments.jsp").forward(req, resp);
    }
}
