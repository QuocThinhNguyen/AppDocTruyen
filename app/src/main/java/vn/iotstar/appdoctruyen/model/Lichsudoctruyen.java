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
}