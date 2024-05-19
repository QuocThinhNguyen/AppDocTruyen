package vn.iotstar.appdoctruyen.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor

public class ChapterDto {
    private Integer id;
    private Integer idtruyen;

    public void setIdtruyen(Integer idtruyen) {
        this.idtruyen = idtruyen;
    }

    public Integer getIdtruyen() {
        return idtruyen;
    }

    private String tenchapter;
    private String ngaydang;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTenchapter(String tenchapter) {
        this.tenchapter = tenchapter;
    }

    public void setNgaydang(String ngaydang) {
        this.ngaydang = ngaydang;
    }

    public void setSoluotxem(Integer soluotxem) {
        this.soluotxem = soluotxem;
    }

    public void setDanhgia(Double danhgia) {
        this.danhgia = danhgia;
    }

    private Integer soluotxem;
    private Double danhgia;

    public Integer getId() {
        return id;
    }

    public String getTenchapter() {
        return tenchapter;
    }

    public String getNgaydang() {
        return ngaydang;
    }

    public Integer getSoluotxem() {
        return soluotxem;
    }

    public Double getDanhgia() {
        return danhgia;
    }
}