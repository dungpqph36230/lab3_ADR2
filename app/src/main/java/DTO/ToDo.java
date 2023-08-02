package DTO;

import java.io.Serializable;

public class ToDo implements Serializable {
    private int ID,statur;
    private String title,conten,date,type;

    public ToDo(int ID, String title, String conten, String date, String type) {
        this.ID = ID;
        this.title = title;
        this.conten = conten;
        this.date = date;
        this.type = type;
    }

    public int getStatur() {
        return statur;
    }

    public void setStatur(int statur) {
        this.statur = statur;
    }

    public ToDo() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getConten() {
        return conten;
    }

    public void setConten(String conten) {
        this.conten = conten;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
