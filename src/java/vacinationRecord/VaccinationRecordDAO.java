package vacinationRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vacinationRecord.VaccinationRecordDTO;
import utils.DBUtils;

public class VaccinationRecordDAO {

    private static final String GET_VACCINATION_RECORDS
            = "SELECT r.recordID, r.childID, r.vaccineID, r.doseNumber, r.vaccinationDate, "
            + "r.centerID, r.appointmentID, r.notes "
            + "FROM tblRecords r "
            + "JOIN tblVaccines v ON r.vaccineID = v.vaccineID "
            + "JOIN tblCenters c ON r.centerID = c.centerID "
            + "WHERE r.childID = ? "
            + "ORDER BY r.vaccinationDate ASC";

    private final Connection connection;

    public VaccinationRecordDAO(Connection connection) {
        this.connection = connection;
    }

    public List<VaccinationRecordDTO> getVaccinationRecords(int childID) throws SQLException {
        List<VaccinationRecordDTO> recordsList = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(GET_VACCINATION_RECORDS)) {
            ps.setInt(1, childID);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int recordID = rs.getInt("recordID");
                    int vaccineID = rs.getInt("vaccineID");
                    int doseNumber = rs.getInt("doseNumber");
                    Date vaccinationDate = rs.getDate("vaccinationDate");
                    int centerID = rs.getInt("centerID");
                    int appointmentID = rs.getInt("appointmentID");
                    String notes = rs.getString("notes");

                    VaccinationRecordDTO record = new VaccinationRecordDTO(
                            recordID,
                            childID,
                            vaccineID,
                            doseNumber, 
                            (java.sql.Date) vaccinationDate,
                            centerID,
                            appointmentID,
                            notes
                    );
                    recordsList.add(record);
                }
            }
        }
        return recordsList;
    }
}
