package vn.iotstar.appdoctruyen.model;


import lombok.Getter;
import lombok.Setter;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter

public class Taikhoan {
    private Integer id;
    private String email;
    private String matkhau;
    private String hoten;
    private String dienthoai;
    private Integer diemthuong;
    private Integer loaitk;
    private String linkanh;
    private Set<Binhluan> binhluans ;
    private Set<Danhgia> danhgias ;
    private Set<Diemthuong> diemthuongs;

    private Set<Doithuong> doithuongs ;
    private Set<Lichsudoctruyen> lichsudoctruyens ;

}