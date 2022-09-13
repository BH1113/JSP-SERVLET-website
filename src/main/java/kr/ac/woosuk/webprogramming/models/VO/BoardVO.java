package kr.ac.woosuk.webprogramming.models.VO;

import java.util.Date;

public class BoardVO {
    private int id;
    private String title;
    private String subTitle;
    private String contents;
    private String username;
    private Date createDate;
    private Date editDate;
    private AttacheVO attacheVO;

    public BoardVO(int id, String title, String subTitle, String contents, String username, Date createDate, Date editDate, AttacheVO attacheVO) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.contents = contents;
        this.username = username;
        this.createDate = createDate;
        this.editDate = editDate;
        this.attacheVO = attacheVO;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getContents() {
        return contents;
    }

    public String getUsername() {
        return username;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public AttacheVO getAttacheVO() { return attacheVO; }

    public Date getEditDate() {
        return  editDate;
    }
}
