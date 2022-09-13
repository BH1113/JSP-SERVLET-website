package kr.ac.woosuk.webprogramming.models.VO;

import java.util.Date;

public class UserVO {
    private int id;
    private String userid;
    private String email;
    private String password;
    private int job;
    private String gender;
    private String introduction;
    private Date create_date;

    public UserVO(int id, String userid, String email, String password, int job, String gender, String introduction, Date create_date) {
        this.id = id;
        this.userid = userid;
        this.email = email;
        this.password = password;
        this.job = job;
        this.gender = gender;
        this.introduction = introduction;
        this.create_date = create_date;
    }

    public int getId() {
        return id;
    }

    public String getUserid() {
        return userid;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getJob() {
        return job;
    }

    public String getGender() {
        return gender;
    }

    public String getIntroduction() {
        return introduction;
    }

    public Date getCreate_date() {
        return create_date;
    }


}
