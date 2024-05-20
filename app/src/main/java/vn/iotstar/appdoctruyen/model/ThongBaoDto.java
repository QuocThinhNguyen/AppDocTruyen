package vn.iotstar.appdoctruyen.model;

import java.time.LocalDate;

public class ThongBaoDto {

    private int id;
    private String tieude, noidung;
    private LocalDate ngaydang;


    public ThongBaoDto(int id, String tieude, String noidung, LocalDate ngaydang) {
        this.id = id;
        this.tieude = tieude;
        this.noidung = noidung;
        this.ngaydang = ngaydang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public LocalDate getNgaydang() {
        return ngaydang;
    }

    public void setNgaydang(LocalDate ngaydang) {
        this.ngaydang = ngaydang;
    }
}
