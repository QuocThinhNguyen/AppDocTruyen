package vn.iotstar.appdoctruyen.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter

public class Vatpham {
    private Integer id;
    private String tenvatpham;
    private Integer diem;
    private String linkanh;
    private Set<Doithuong> doithuongs;

    public Vatpham() {
    }

    public Vatpham(Integer id, String tenvatpham, Integer diem, String linkanh, Set<Doithuong> doithuongs) {
        this.id = id;
        this.tenvatpham = tenvatpham;
        this.diem = diem;
        this.linkanh = linkanh;
        this.doithuongs = doithuongs;
    }
}