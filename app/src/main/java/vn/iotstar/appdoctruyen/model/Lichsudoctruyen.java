package vn.iotstar.appdoctruyen.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Lichsudoctruyen {
    private Integer id;
    private Chapter idchapter;
    private Taikhoan idtaikhoan;

    public Lichsudoctruyen() {
    }

    public Lichsudoctruyen(Integer id, Chapter idchapter, Taikhoan idtaikhoan) {
        this.id = id;
        this.idchapter = idchapter;
        this.idtaikhoan = idtaikhoan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Chapter getIdchapter() {
        return idchapter;
    }

    public void setIdchapter(Chapter idchapter) {
        this.idchapter = idchapter;
    }

    public Taikhoan getIdtaikhoan() {
        return idtaikhoan;
    }

    public void setIdtaikhoan(Taikhoan idtaikhoan) {
        this.idtaikhoan = idtaikhoan;
    }
}