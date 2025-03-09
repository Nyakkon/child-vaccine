package controller;

import child.ChildDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "DeleteAppointmentSessionController", urlPatterns = {"/DeleteAppointmentSessionController"})
public class DeleteAppointmentSessionController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String childIDStr = request.getParameter("childID");
            if (childIDStr == null || childIDStr.trim().isEmpty()) {
                response.sendRedirect("childProfile.jsp?error=Missing childID");
                return;
            }
            int childID = Integer.parseInt(childIDStr);

            ChildDAO dao = new ChildDAO();
            boolean success = dao.deleteAppointmentSessions(childID);

            if (success) {
                response.sendRedirect("childProfile.jsp?msg=Appointment sessions deleted successfully");
            } else {
                request.setAttribute("ERROR_MESSAGE", "Xóa thông tin thất bại!");
                request.getRequestDispatcher("childProfile.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR_MESSAGE", "Lỗi: " + e.getMessage());
            request.getRequestDispatcher("childProfile.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "DeleteAppointmentSessionController";
    }
}
