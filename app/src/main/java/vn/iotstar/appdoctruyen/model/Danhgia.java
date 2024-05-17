package vn.iotstar.appdoctruyen.model;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class Danhgia {

    private Integer id;
    private Chapter idchapter;
    private Taikhoan idtaikhoan;

    public Danhgia(Integer id, Chapter idchapter, Taikhoan idtaikhoan, Double sosao, LocalDate ngaydanhgia) {
        this.id = id;
        this.idchapter = idchapter;
        this.idtaikhoan = idtaikhoan;
        this.sosao = sosao;
        this.ngaydanhgia = ngaydanhgia;
    }

    public Danhgia() {
    }

    private Double sosao;
    private LocalDate ngaydanhgia;

}