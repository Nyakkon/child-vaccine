/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package record;

/**
 *
 * @author buimi
 */
import java.util.Date;

// RecordDTO.java
public class RecordDTO {
    private int recordID;
    private int childID;
    private int vaccineID;
    private int doseNumber;
    private Date vaccinationDate;
    private int centerID;
    private int appointmentID;
    private String notes;
    
    public RecordDTO() {
    }

    public RecordDTO(int recordID, int childID, int vaccineID, int doseNumber, Date vaccinationDate, int centerID, int appointmentID, String notes) {
        this.recordID = recordID;
        this.childID = childID;
        this.vaccineID = vaccineID;
        this.doseNumber = doseNumber;
        this.vaccinationDate = vaccinationDate;
        this.centerID = centerID;
        this.appointmentID = appointmentID;
        this.notes = notes;
    }
    
    public RecordDTO(int childID, int vaccineID, int doseNumber, Date vaccinationDate, String notes) {
        this.childID = childID;
        this.vaccineID = vaccineID;
        this.doseNumber = doseNumber;
        this.vaccinationDate = vaccinationDate;
        this.notes = notes;
    }

    // Getters and Setters
    public int getRecordID() { return recordID; }
    public void setRecordID(int recordID) { this.recordID = recordID; }
    public int getChildID() { return childID; }
    public void setChildID(int childID) { this.childID = childID; }
    public int getVaccineID() { return vaccineID; }
    public void setVaccineID(int vaccineID) { this.vaccineID = vaccineID; }
    public int getDoseNumber() { return doseNumber; }
    public void setDoseNumber(int doseNumber) { this.doseNumber = doseNumber; }
    public Date getVaccinationDate() { return vaccinationDate; }
    public void setVaccinationDate(Date vaccinationDate) { this.vaccinationDate = vaccinationDate; }
    public int getCenterID() { return centerID; }
    public void setCenterID(int centerID) { this.centerID = centerID; }
    public int getAppointmentID() { return appointmentID; }
    public void setAppointmentID(int appointmentID) { this.appointmentID = appointmentID; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}