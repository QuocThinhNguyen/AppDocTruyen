package vn.iotstar.appdoctruyen.model;
import java.time.LocalDate;


public class Binhluan {
    private Integer id;
    private Chapter idchapter;
    private Taikhoan idtaikhoan;
    private String noidung;
    private LocalDate ngaydang;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdchapter(Chapter idchapter) {
        this.idchapter = idchapter;
    }

    public void setIdtaikhoan(Taikhoan idtaikhoan) {
        this.idtaikhoan = idtaikhoan;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public void setNgaydang(LocalDate ngaydang) {
        this.ngaydang = ngaydang;
    }

    public void setTrangthai(Integer trangthai) {
        this.trangthai = trangthai;
    }

    public Binhluan(Integer id, Chapter idchapter, Taikhoan idtaikhoan, String noidung, LocalDate ngaydang, Integer trangthai) {
        this.id = id;
        this.idchapter = idchapter;
        this.idtaikhoan = idtaikhoan;
        this.noidung = noidung;
        this.ngaydang = ngaydang;
        this.trangthai = trangthai;
    }

    public Binhluan() {
    }

    public Integer getId() {
        return id;
    }

    public Chapter getIdchapter() {
        return idchapter;
    }

    public Taikhoan getIdtaikhoan() {
        return idtaikhoan;
    }

    public String getNoidung() {
        return noidung;
    }

    public LocalDate getNgaydang() {
        return ngaydang;
    }

    public Integer getTrangthai() {
        return trangthai;
    }

    private Integer trangthai;

}