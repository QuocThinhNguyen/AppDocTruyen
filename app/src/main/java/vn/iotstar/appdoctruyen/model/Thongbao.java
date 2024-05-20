package vn.iotstar.appdoctruyen.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;


public class Thongbao {
    private Integer id;
    private String tieude;
    private String noidung;
    private String ngaydang;

    public Thongbao(Integer id, String tieude, String noidung, String ngaydang) {
        this.id = id;
        this.tieude = tieude;
        this.noidung = noidung;
        this.ngaydang = ngaydang;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getNgaydang() {
        return ngaydang;
    }

    public void setNgaydang(String ngaydang) {
        this.ngaydang = ngaydang;
    }
}