package vn.iotstar.appdoctruyen.model;

import java.io.Serializable;
import java.util.Set;

public class truyen implements Serializable {
    private Integer id;
    private String tentruyen;
    private String tacgia;
    private String mota;
    private String theloai;
    private String linkanh;
    private Integer trangthai;
    private String keySearch;

    @Override
    public String toString() {
        return "truyen{" +
                "id=" + id +
                ", tentruyen='" + tentruyen + '\'' +
                ", tacgia='" + tacgia + '\'' +
                ", mota='" + mota + '\'' +
                ", theloai='" + theloai + '\'' +
                ", linkanh='" + linkanh + '\'' +
                ", trangthai=" + trangthai +
                ", keySearch='" + keySearch + '\'' +
                '}';
    }

    public truyen(String tentruyen, String tacgia, String mota, String theloai, String linkanh, String keySearch) {
        this.tentruyen = tentruyen;
        this.tacgia = tacgia;
        this.mota = mota;
        this.theloai = theloai;
        this.linkanh = linkanh;
        this.keySearch = keySearch;
    }

    public truyen(String tentruyen, String tacgia, String mota, String theloai, String linkanh, Integer trangthai, String keySearch) {
        this.tentruyen = tentruyen;
        this.tacgia = tacgia;
        this.mota = mota;
        this.theloai = theloai;
        this.linkanh = linkanh;
        this.trangthai = trangthai;
        this.keySearch = keySearch;
    }

    public truyen() {
    }

    public truyen(Integer id, String tentruyen, String tacgia, String mota, String theloai, String linkanh, Integer trangthai, String keySearch) {
        this.id = id;
        this.tentruyen = tentruyen;
        this.tacgia = tacgia;
        this.mota = mota;
        this.theloai = theloai;
        this.linkanh = linkanh;
        this.trangthai = trangthai;
        this.keySearch = keySearch;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTentruyen(String tentruyen) {
        this.tentruyen = tentruyen;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public void setLinkanh(String linkanh) {
        this.linkanh = linkanh;
    }

    public void setTrangthai(Integer trangthai) {
        this.trangthai = trangthai;
    }

    public void setKeySearch(String keySearch) {
        this.keySearch = keySearch;
    }

    public Integer getId() {
        return id;
    }

    public String getTentruyen() {
        return tentruyen;
    }

    public String getTacgia() {
        return tacgia;
    }

    public String getMota() {
        return mota;
    }

    public String getTheloai() {
        return theloai;
    }

    public String getLinkanh() {
        return linkanh;
    }

    public Integer getTrangthai() {
        return trangthai;
    }

    public String getKeySearch() {
        return keySearch;
    }
}
