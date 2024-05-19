package vn.iotstar.appdoctruyen.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BinhLuanCuaToiDto {
    private Integer id;
    private Integer idchapter;
    private Integer idtaikhoan;
    private String noidung;
    private LocalDate ngaydang;
    private Integer trangthai;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdchapter() {
        return idchapter;
    }

    public void setIdchapter(Integer idchapter) {
        this.idchapter = idchapter;
    }

    public Integer getIdtaikhoan() {
        return idtaikhoan;
    }

    public void setIdtaikhoan(Integer idtaikhoan) {
        this.idtaikhoan = idtaikhoan;
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

    public Integer getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(Integer trangthai) {
        this.trangthai = trangthai;
    }
}
