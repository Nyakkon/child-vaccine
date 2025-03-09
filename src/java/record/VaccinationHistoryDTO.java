package record;

import java.util.Date;

public class VaccinationHistoryDTO {
    private int recordID;
    private String childName;
    private String vaccineName;
    private int doseNumber;
    private Date vaccinationDate;
    private String centerName;
    private String notes;

    public VaccinationHistoryDTO() {
    }

    public VaccinationHistoryDTO(int recordID, String childName, String vaccineName, 
            int doseNumber, Date vaccinationDate, String centerName, String notes) {
        this.recordID = recordID;
        this.childName = childName;
        this.vaccineName = vaccineName;
        this.doseNumber = doseNumber;
        this.vaccinationDate = vaccinationDate;
        this.centerName = centerName;
        this.notes = notes;
    }

    public int getRecordID() { return recordID; }
    public void setRecordID(int recordID) { this.recordID = recordID; }

    public String getChildName() { return childName; }
    public void setChildName(String childName) { this.childName = childName; }

    public String getVaccineName() { return vaccineName; }
    public void setVaccineName(String vaccineName) { this.vaccineName = vaccineName; }

    public int getDoseNumber() { return doseNumber; }
    public void setDoseNumber(int doseNumber) { this.doseNumber = doseNumber; }

    public Date getVaccinationDate() { return vaccinationDate; }
    public void setVaccinationDate(Date vaccinationDate) { this.vaccinationDate = vaccinationDate; }

    public String getCenterName() { return centerName; }
    public void setCenterName(String centerName) { this.centerName = centerName; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
