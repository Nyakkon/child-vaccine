package controller;

import VaccinationTrackingDAO.VaccinationTrackingDAO;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "VaccinationTrackingController", urlPatterns = {"/VaccinationTracking"})
public class VaccinationTrackingController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String search = request.getParameter("search");

            // Initialize DAO
            VaccinationTrackingDAO trackingDAO = new VaccinationTrackingDAO();

            // Get vaccination tracking records based on search
            List<Map<String, Object>> records = trackingDAO.getVaccinationTracking(search);

            // Process each record to determine required vaccines
            for (Map<String, Object> record : records) {
                Object ageObj = record.get("childAgeInMonths");

                // Check if age is available and valid
                int ageInMonths = (ageObj != null) ? (int) ageObj : 0;
                List<String> vaccines = trackingDAO.getRequiredVaccinesForAge(ageInMonths);

                // Add required vaccines to the record
                record.put("requiredVaccines", vaccines);
            }

            // Pass records to the JSP
            request.setAttribute("trackingRecords", records);

            // Forward to the tracking JSP page
            request.getRequestDispatcher("/VaccinationTracking.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while processing vaccination tracking data.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

}
