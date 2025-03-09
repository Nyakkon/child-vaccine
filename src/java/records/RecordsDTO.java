package records;

import java.util.Date;

public class RecordsDTO {

    private int recordID;          // ID của bản ghi
    private int childID;           // ID của trẻ em
    private int vaccineID;         // ID của vaccine
    private int doseNumber;        // Số liều của vaccine đã tiêm
    private Date vaccinationDate;  // Ngày tiêm vaccine
    private int centerID;          // ID của trung tâm tiêm
    private Integer appointmentID; // ID của cuộc hẹn (có thể null)
    private String notes;          // Ghi chú bổ sung

    public RecordsDTO() {
    }

    // Constructor đầy đủ
    public RecordsDTO(int recordID, int childID, int vaccineID, int doseNumber, Date vaccinationDate, int centerID, Integer appointmentID, String notes) {
        this.recordID = recordID;
        this.childID = childID;
        this.vaccineID = vaccineID;
        this.doseNumber = doseNumber;
        this.vaccinationDate = vaccinationDate;
        this.centerID = centerID;
        this.appointmentID = appointmentID;
        this.notes = notes;
    }

    // Getters and Setters
    public int getRecordID() {
        return recordID;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public int getChildID() {
        return childID;
    }

    public void setChildID(int childID) {
        this.childID = childID;
    }

    public int getVaccineID() {
        return vaccineID;
    }

    public void setVaccineID(int vaccineID) {
        this.vaccineID = vaccineID;
    }

    public int getDoseNumber() {
        return doseNumber;
    }

    public void setDoseNumber(int doseNumber) {
        this.doseNumber = doseNumber;
    }

    public Date getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(Date vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public int getCenterID() {
        return centerID;
    }

    public void setCenterID(int centerID) {
        this.centerID = centerID;
    }

    public Integer getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(Integer appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "RecordDTO{"
                + "recordID=" + recordID
                + ", childID=" + childID
                + ", vaccineID=" + vaccineID
                + ", doseNumber=" + doseNumber
                + ", vaccinationDate=" + vaccinationDate
                + ", centerID=" + centerID
                + ", appointmentID=" + appointmentID
                + ", notes='" + notes + '\''
                + '}';
    }
}
