package records;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecordsDAO {

    private Connection connection;

    public RecordsDAO(Connection connection) {
        this.connection = connection;
    }

    // Lấy tất cả các bản ghi từ bảng tblRecords
    public List<RecordsDTO> getAllRecords() throws SQLException {
        String sql = "SELECT r.recordID, r.childID, r.vaccinationDate, "
                + "MONTH(r.vaccinationDate) "
                + "FROM tblRecords r "
                + "JOIN tblChildren c ON r.childID = c.childID "
                + "JOIN tblVaccines v ON r.vaccineID = v.vaccineID";
        List<RecordsDTO> records = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                RecordsDTO record = new RecordsDTO();
                record.setRecordID(resultSet.getInt("recordID"));
                record.setChildID(resultSet.getInt("childID"));
                record.setVaccinationDate(resultSet.getDate("vaccinationDate"));
                records.add(record);
            }
        }
        return records;
    }
}
