/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VaccinationTrackingDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.DBUtils;

/**
 *
 * @author Windows
 */
public class VaccinationTrackingDAO {

    public List<Map<String, Object>> getVaccinationTracking(String search) throws SQLException, ClassNotFoundException {
        String query = "SELECT \n"
                + "    ch.childID, \n"
                + "    ch.childName, \n"
                + "    r.vaccineID, \n"
                + "    v.vaccineName, \n"
                + "    r.vaccinationDate, \n"
                + "    DATEDIFF(MONTH, ch.dateOfBirth, r.vaccinationDate) AS vaccinationMonth, \n"
                + "    DATEDIFF(MONTH, ch.dateOfBirth, GETDATE()) AS childAgeInMonths \n"
                + "FROM \n"
                + "    tblChildren ch\n"
                + "LEFT JOIN \n"
                + "    tblRecords r ON ch.childID = r.childID\n"
                + "LEFT JOIN \n"
                + "    tblVaccines v ON r.vaccineID = v.vaccineID\n"
                + "WHERE \n"
                + "    ch.childName LIKE '%Test%' -- Thay 'Test' bằng từ khóa bạn muốn tìm\n"
                + "ORDER BY \n"
                + "    ch.childID, \n"
                + "    r.vaccinationDate;";

        List<Map<String, Object>> records = new ArrayList<>();

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, "%" + search + "%"); // Truyền giá trị tìm kiếm
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> record = new HashMap<>();
                    record.put("childID", rs.getInt("childID"));
                    record.put("childName", rs.getString("childName"));
                    record.put("vaccineID", rs.getInt("vaccineID"));
                    record.put("vaccineName", rs.getString("vaccineName"));
                    record.put("vaccinationDate", rs.getDate("vaccinationDate"));
                    record.put("vaccinationMonth", rs.getInt("vaccinationMonth"));
                    record.put("childAgeInMonths", rs.getInt("childAgeInMonths"));
                    records.add(record);
                }
            }
        }
        return records;
    }

    public List<String> getRequiredVaccinesForAge(int ageInMonths) throws SQLException, ClassNotFoundException {
        String query = "SELECT vaccineName "
                + "FROM tblVaccines "
                + "WHERE minAgeInMonths <= ? AND maxAgeInMonths >= ?";

        List<String> vaccines = new ArrayList<>();

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, ageInMonths);
            ps.setInt(2, ageInMonths);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    vaccines.add(rs.getString("vaccineName"));
                }
            }
        }

        return vaccines;
    }

    public boolean addVaccinationRecord(int childID, int vaccineID, int doseNumber, String vaccinationDate, int centerID, Integer appointmentID, String notes)
            throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO tblRecords (childID, vaccineID, doseNumber, vaccinationDate, centerID, appointmentID, notes) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, childID);
            ps.setInt(2, vaccineID);
            ps.setInt(3, doseNumber);
            ps.setDate(4, java.sql.Date.valueOf(vaccinationDate));
            ps.setInt(5, centerID);

            if (appointmentID != null) {
                ps.setInt(6, appointmentID);
            } else {
                ps.setNull(6, java.sql.Types.INTEGER);
            }

            ps.setString(7, notes);

            // Execute the query and check if any rows were affected
            return ps.executeUpdate() > 0;
        }
    }

    public boolean addChild(String childName, String dateOfBirth) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO tblChildren (fullName, dateOfBirth) VALUES (?, ?)";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, childName);
            ps.setDate(2, java.sql.Date.valueOf(dateOfBirth));
            return ps.executeUpdate() > 0;
        }
    }

}
