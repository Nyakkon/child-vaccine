package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
<<<<<<< HEAD
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.DBUtils;

public class MarkNotificationReadController extends HttpServlet {
=======
import javax.servlet.http.*;
import utils.DBUtils;

public class MarkNotificationReadController extends HttpServlet {
    @Override
>>>>>>> dc71290 (update 21:19  PM  09/03/2025)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String notificationID = request.getParameter("notificationID");

<<<<<<< HEAD
        try {
            Connection conn = DBUtils.getConnection();
=======
        try (Connection conn = DBUtils.getConnection()) {
>>>>>>> dc71290 (update 21:19  PM  09/03/2025)
            String sql = "UPDATE tblNotifications SET isRead = 1 WHERE notificationID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, notificationID);
            ps.executeUpdate();
            ps.close();
<<<<<<< HEAD
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("vaccinationSchedule.jsp");
=======
            // Không redirect => chỉ trả về status OK
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            // Nếu có lỗi => status 500
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
>>>>>>> dc71290 (update 21:19  PM  09/03/2025)
    }
}
