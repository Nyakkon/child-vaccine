/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package record;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author buimi
 */
import java.sql.*;
import java.util.*;

public class RecordDAO {

    private Connection conn;

    public RecordDAO(Connection conn) {
        this.conn = conn;
    }

    // Lấy tất cả các bản ghi từ tblRecords
    public List<RecordDTO> getAllRecords() throws SQLException {
        List<RecordDTO> records = new ArrayList<>();
        String query = "SELECT * FROM tblRecords";

        try (PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                // Chỉ lấy các trường hiện có trong RecordDTO
                RecordDTO record = new RecordDTO(
                        rs.getInt("recordID"),
                        rs.getInt("childID"),
                        rs.getInt("vaccineID"),
                        rs.getInt("doseNumber"),
                        rs.getDate("vaccinationDate"),
                        rs.getInt("centerID"),
                        rs.getInt("appointmentID"),
                        rs.getString("notes")
                );
                records.add(record);
            }
        }
        return records;
    }

    // Thêm bản ghi vào tblRecords
    public void addRecord(RecordDTO record) throws SQLException {
        String query = "INSERT INTO tblRecords (childID, vaccineID, doseNumber, vaccinationDate, centerID, appointmentID, notes) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, record.getChildID());
            ps.setInt(2, record.getVaccineID());
            ps.setInt(3, record.getDoseNumber());
            ps.setDate(4, new java.sql.Date(record.getVaccinationDate().getTime()));
            ps.setInt(5, record.getCenterID());
            ps.setObject(6, record.getAppointmentID(), Types.INTEGER);
            ps.setString(7, record.getNotes());
            ps.executeUpdate();
        }
    }

    // Lấy bản ghi tiêm chủng theo từ khóa (tìm theo tên trẻ)
    public List<RecordDTO> getVaccinationRecords(String keyword) {
        List<RecordDTO> records = new ArrayList<>();
        String sql = "SELECT c.childName, v.vaccineName, r.doseNumber, r.vaccinationDate, r.notes "
                + "FROM tblRecords r "
                + "JOIN tblChildren c ON r.childID = c.childID "
                + "JOIN tblVaccines v ON r.vaccineID = v.vaccineID "
                + "WHERE c.childName LIKE ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                records.add(new RecordDTO(
                        rs.getInt("childID"), // ID của trẻ
                        rs.getInt("vaccineID"), // ID của vaccine
                        rs.getInt("doseNumber"), // Số lần tiêm
                        rs.getDate("vaccinationDate"), // Ngày tiêm
                        rs.getString("notes") // Ghi chú
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    public RecordDTO getRecordById(int recordID) throws SQLException {
        String query = "SELECT * FROM tblRecords WHERE recordID = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, recordID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new RecordDTO(
                            rs.getInt("recordID"),
                            rs.getInt("childID"),
                            rs.getInt("vaccineID"),
                            rs.getInt("doseNumber"),
                            rs.getDate("vaccinationDate"),
                            rs.getInt("centerID"),
                            rs.getInt("appointmentID"),
                            rs.getString("notes")
                    );
                }
            }
        }
        return null;
    }

    public List<VaccinationHistoryDTO> getVaccinationHistoryByUser(String userID) throws SQLException {
        List<VaccinationHistoryDTO> list = new ArrayList<>();
        // JOIN lấy thông tin cần thiết
        String sql = "SELECT r.recordID, c.childName, v.vaccineName, r.doseNumber, "
                + "       r.vaccinationDate, ct.centerName, r.notes "
                + "FROM tblRecords r "
                + "JOIN tblChildren c ON r.childID = c.childID "
                + "JOIN tblVaccines v ON r.vaccineID = v.vaccineID "
                + "JOIN tblCenters ct ON r.centerID = ct.centerID "
                + "WHERE c.userID = ? "
                + "ORDER BY r.vaccinationDate ASC"; // sắp xếp theo ngày tiêm

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int recordID = rs.getInt("recordID");
                    String childName = rs.getString("childName");
                    String vaccineName = rs.getString("vaccineName");
                    int doseNumber = rs.getInt("doseNumber");
                    Date vaccinationDate = rs.getDate("vaccinationDate");
                    String centerName = rs.getString("centerName");
                    String notes = rs.getString("notes");

                    VaccinationHistoryDTO dto = new VaccinationHistoryDTO(
                            recordID, childName, vaccineName, doseNumber,
                            vaccinationDate, centerName, notes
                    );
                    list.add(dto);
                }
            }
        }
        return list;
    }

    public List<VaccinationHistoryDTO> getVaccinationHistoryByChild(int childID) throws SQLException {
        List<VaccinationHistoryDTO> list = new ArrayList<>();
        String sql = "SELECT r.recordID, c.childName, v.vaccineName, r.doseNumber, "
                + "       r.vaccinationDate, ct.centerName, r.notes "
                + "FROM tblRecords r "
                + "JOIN tblChildren c ON r.childID = c.childID "
                + "JOIN tblVaccines v ON r.vaccineID = v.vaccineID "
                + "JOIN tblCenters ct ON r.centerID = ct.centerID "
                + "WHERE r.childID = ? "
                + "ORDER BY r.vaccinationDate ASC";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, childID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int recordID = rs.getInt("recordID");
                    String childName = rs.getString("childName");
                    String vaccineName = rs.getString("vaccineName");
                    int doseNumber = rs.getInt("doseNumber");

                    // Lấy vaccinationDate dưới dạng java.sql.Date và chuyển sang java.util.Date
                    java.sql.Date sqlDate = rs.getDate("vaccinationDate");
                    java.util.Date vaccinationDate = null;
                    if (sqlDate != null) {
                        vaccinationDate = new java.util.Date(sqlDate.getTime());
                    }

                    String centerName = rs.getString("centerName");
                    String notes = rs.getString("notes");

                    VaccinationHistoryDTO dto = new VaccinationHistoryDTO(
                            recordID, childName, vaccineName, doseNumber,
                            vaccinationDate, centerName, notes
                    );
                    list.add(dto);
                }
            }
        }
        return list;
    }

}