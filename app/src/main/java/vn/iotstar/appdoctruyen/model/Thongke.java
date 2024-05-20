package vn.iotstar.appdoctruyen.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Thongke {
    private Integer id;
    private truyen idtruyen;
    private Integer tongluotxem;
    private Double sosaotb;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public truyen getIdtruyen() {
        return idtruyen;
    }

    public void setIdtruyen(truyen idtruyen) {
        this.idtruyen = idtruyen;
    }

    public Integer getTongluotxem() {
        return tongluotxem;
    }

    public void setTongluotxem(Integer tongluotxem) {
        this.tongluotxem = tongluotxem;
    }

    public Double getSosaotb() {
        return sosaotb;
    }

    public void setSosaotb(Double sosaotb) {
        this.sosaotb = sosaotb;
    }

    public Thongke() {
    }

    public Thongke(Integer id, truyen idtruyen, Integer tongluotxem, Double sosaotb) {
        this.id = id;
        this.idtruyen = idtruyen;
        this.tongluotxem = tongluotxem;
        this.sosaotb = sosaotb;
    }
}