<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

/**
 *
 * @author windows
 */
public class CustomerDTO {

=======
package customer;

import java.io.Serializable;

public class CustomerDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
>>>>>>> dc71290 (update 21:19  PM  09/03/2025)
    private String userID;
    private String password;
    private String roleID;
    private String fullName;
    private String email;
    private String address;
    private String phone;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public CustomerDTO() {
    }

    public CustomerDTO(String userID, String password, String roleID, String fullName, String email, String address, String phone) {
        this.userID = userID;
        this.password = password;
        this.roleID = roleID;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.phone = phone;
<<<<<<< HEAD
        this.status = status;

=======
>>>>>>> dc71290 (update 21:19  PM  09/03/2025)
    }

    // Getters and Setters
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
<<<<<<< HEAD

=======
>>>>>>> dc71290 (update 21:19  PM  09/03/2025)
}
