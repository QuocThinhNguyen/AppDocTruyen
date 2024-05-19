package vn.iotstar.appdoctruyen.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterDto {
    private Integer id;
    private Integer idtruyen;
    private Integer soluotxem;
    private Double danhgia;
    private String tenchapter;
    private String ngaydang;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;



    public Integer getIdtruyen() {
        return idtruyen;
    }


    public void setIdtruyen(Integer idtruyen) {
        this.idtruyen = idtruyen;
    }

    public Integer getSoluotxem() {
        return soluotxem;
    }

    public void setSoluotxem(Integer soluotxem) {
        this.soluotxem = soluotxem;
    }

    public Double getDanhgia() {
        return danhgia;

   
    public void setDanhgia(Double danhgia) {
        this.danhgia = danhgia;
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

}
