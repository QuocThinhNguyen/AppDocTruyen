package vn.iotstar.appdoctruyen.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class ChapterAdmin implements Serializable {
    private Integer id;
    private Integer idtruyen;
    private String tenchapter;
    private String ngaydang;
    private Integer soluotxem;
    private Double danhgia;

    public ChapterAdmin(Integer idtruyen, String tenchapter, String ngaydang, Integer soluotxem, Double danhgia) {
        this.idtruyen = idtruyen;
        this.tenchapter = tenchapter;
        this.ngaydang = ngaydang;
        this.soluotxem = soluotxem;
        this.danhgia = danhgia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdtruyen() {
        return idtruyen;
    }

    public void setIdtruyen(Integer idtruyen) {
        this.idtruyen = idtruyen;
    }

    public String getTenchapter() {
        return tenchapter;
    }

    public void setTenchapter(String tenchapter) {
        this.tenchapter = tenchapter;
    }

    public String getNgaydang() {
        return ngaydang;
    }

    public void setNgaydang(String ngaydang) {
        this.ngaydang = ngaydang;
    }

    public Integer getSoluotxem() {
        return soluotxem;
    }

    public void setSoluotxem(Integer soluotxem) {
        this.soluotxem = soluotxem;
    }

    public Double getDanhgia() {
        return danhgia;
    }

    public void setDanhgia(Double danhgia) {
        this.danhgia = danhgia;
    }

    public ChapterAdmin(Integer id, Integer idtruyen, String tenchapter, String ngaydang, Integer soluotxem, Double danhgia) {
        this.id = id;
        this.idtruyen = idtruyen;
        this.tenchapter = tenchapter;
        this.ngaydang = ngaydang;
        this.soluotxem = soluotxem;
        this.danhgia = danhgia;
    }
}
