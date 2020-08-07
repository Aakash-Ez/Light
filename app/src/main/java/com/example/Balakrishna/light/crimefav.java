package com.example.Balakrishna.light;

public class crimefav {
    private String ID;
    private String StrtName;
    private String Cat;
    private String Out;
    private String Lati;
    private String Longi;
    private String Month;

    public void setStrtName(String strtName) {
        StrtName = strtName;
    }

    public void setLati(String lati) {
        Lati = lati;
    }

    public void setOut(String out) {
        Out = out;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public void setLongi(String longi) {
        Longi = longi;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setCat(String cat) {
        Cat = cat;
    }

    public String getStrtName() {
        return StrtName;
    }

    public String getOut() {
        return Out;
    }

    public String getMonth() {
        return Month;
    }

    public String getLongi() {
        return Longi;
    }

    public String getLati() {
        return Lati;
    }

    public String getID() {
        return ID;
    }

    public String getCat() {
        return Cat;
    }
}
