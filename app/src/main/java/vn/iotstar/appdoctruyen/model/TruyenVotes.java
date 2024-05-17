package vn.iotstar.appdoctruyen.model;

public class TruyenVotes {
    private int id, tongluotxem;
    private float sosaotb;
    private String tentruyen, ngaydang, theloai, linkanh;

    public TruyenVotes(int id, int tongluotxem, float sosaotb, String tentruyen, String ngaydang, String theloai, String linkanh) {
        this.id = id;
        this.tongluotxem = tongluotxem;
        this.sosaotb = sosaotb;
        this.tentruyen = tentruyen;
        this.ngaydang = ngaydang;
        this.theloai = theloai;
        this.linkanh = linkanh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTongluotxem() {
        return tongluotxem;
    }

    public void setTongluotxem(int tongluotxem) {
        this.tongluotxem = tongluotxem;
    }

    public float getSosaotb() {
        return sosaotb;
    }

    public void setSosaotb(float sosaotb) {
        this.sosaotb = sosaotb;
    }

    public String getTentruyen() {
        return tentruyen;
    }

    public void setTentruyen(String tentruyen) {
        this.tentruyen = tentruyen;
    }

    public String getNgaydang() {
        return ngaydang;
    }

    public void setNgaydang(String ngaydang) {
        this.ngaydang = ngaydang;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public String getLinkanh() {
        return linkanh;
    }

    public void setLinkanh(String linkanh) {
        this.linkanh = linkanh;
    }
}
