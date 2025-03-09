/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import doctor.DoctorDAO;
import doctor.DoctorDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DoctorLoginController", urlPatterns = {"/DoctorLoginController"})
public class DoctorLoginController extends HttpServlet {
    private static final String ERROR = "login.jsp";
    private static final String DOCTOR_PAGE = "doctor.jsp"; // Tạo trang này sau
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            String doctorID = request.getParameter("doctorID");
            String password = request.getParameter("password");
            DoctorDAO dao = new DoctorDAO();
            DoctorDTO doctor = dao.checkLogin(doctorID, password);
            HttpSession session = request.getSession();
            if (doctor != null) {
                session.setAttribute("DOCTOR_LOGIN", doctor);
                url = DOCTOR_PAGE;
            } else {
                request.setAttribute("ERROR", "Incorrect DoctorID or Password");
            }
        } catch (Exception e) {
            log("Error at DoctorLoginController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
}
