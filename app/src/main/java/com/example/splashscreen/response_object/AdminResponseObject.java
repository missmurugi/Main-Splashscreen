package com.example.splashscreen.response_object;


/**
 *   RowDataPacket {
 *     adminid: 12,
 *     adminname: '5',
 *     adminemail: '5',
 *     adminpassword: '5'
 */
public class AdminResponseObject {
    private int record_id;
    private String adminname;
    private String adminemail;
    private String adminpassword;
    private String admincontact;

    public int getRecord_id() {
        return record_id;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }


    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getAdminemail() {
        return adminemail;
    }

    public void setAdminemail(String adminemail) {
        this.adminemail = adminemail;
    }

    public String getAdminpassword() {
        return adminpassword;
    }

    public void setAdminpassword(String adminpassword) {
        this.adminpassword = adminpassword;
    }

    public String getAdmincontact() {return admincontact; }

    public void setAdmincontact(String admincontact) {
        this.admincontact = admincontact;
    }
}
