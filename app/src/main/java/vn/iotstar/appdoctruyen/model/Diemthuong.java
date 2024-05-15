package vn.iotstar.appdoctruyen.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Diemthuong {

    private Integer id;

    public Diemthuong(Integer id, Taikhoan idtaikhoan) {
        this.id = id;
        this.idtaikhoan = idtaikhoan;
    }

    private Taikhoan idtaikhoan;

    public Diemthuong() {
    }
//TODO [JPA Buddy] generate columns from DB
}