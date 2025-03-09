package vacinationRecord;

import java.sql.Date;

/**
 * VaccinationRecordDTO 
 */
public class VaccinationRecordDTO {
    private int recordID;          // Khóa chính, tự động tăng
    private int childID;           // FK: tblChildren(childID) - ID của trẻ được tiêm
    private int vaccineID;         // FK: tblVaccines(vaccineID) - ID của vaccine được tiêm
    private int doseNumber;        // Số liều của vaccine đã tiêm
    private Date vaccinationDate;  // Ngày tiêm vaccine
    private int centerID;          // FK: tblCenters(centerID) - ID của trung tâm tiêm chủng
    private Integer appointmentID; // FK: tblAppointments(appointmentID) - ID cuộc hẹn (có thể null)
    private String notes;          // Ghi chú bổ sung về lịch sử tiêm

    // Constructor mặc định
    public VaccinationRecordDTO() {
    }

    // Constructor đầy đủ
    public VaccinationRecordDTO(int recordID, int childID, int vaccineID, 
            int doseNumber, Date vaccinationDate, int centerID, 
            Integer appointmentID, String notes) {
        this.recordID = recordID;
        this.childID = childID;
        this.vaccineID = vaccineID;
        this.doseNumber = doseNumber;
        this.vaccinationDate = vaccinationDate;
        this.centerID = centerID;
        this.appointmentID = appointmentID;
        this.notes = notes;
    }

    // Getters và Setters
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

}