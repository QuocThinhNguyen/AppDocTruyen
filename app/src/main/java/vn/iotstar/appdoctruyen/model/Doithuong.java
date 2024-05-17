package vn.iotstar.appdoctruyen.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Doithuong {

    private Integer id;

    public Doithuong() {
    }

    public Doithuong(Integer id, Taikhoan idtaikhoan, Vatpham idvatpham) {
        this.id = id;
        this.idtaikhoan = idtaikhoan;
        this.idvatpham = idvatpham;
    }

    private Taikhoan idtaikhoan;
    private Vatpham idvatpham;

    //TODO [JPA Buddy] generate columns from DB
}