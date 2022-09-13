package kr.ac.woosuk.webprogramming.models.DTO;

import java.util.Date;

public class UserDTO {
    private int id;
    private String userid;
    private String email;
    private String password;
    private int job;
    private String gender;
    private String introduction;
    private Date create_date;
    private Date deleted_date;

    public Date getDeleted_date() {
        return deleted_date;
    }

    public void setDeleted_date(Date deleted_date) {
        this.deleted_date = deleted_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getJob() {
        return job;
    }

    public void setJob(int job) {
        this.job = job;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}
