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

    public Thongke() {
    }

    public Thongke(Integer id, truyen idtruyen, Integer tongluotxem, Double sosaotb) {
        this.id = id;
        this.idtruyen = idtruyen;
        this.tongluotxem = tongluotxem;
        this.sosaotb = sosaotb;
    }
}