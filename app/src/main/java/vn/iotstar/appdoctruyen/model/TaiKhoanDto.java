package vn.iotstar.appdoctruyen.model;


public class TaiKhoanDto {
    private Integer id;
    private String email;
    private String matkhau;
    private String hoten;
    private String dienthoai;
    private Integer diemthuong;
    private Integer loaitaikhoan;

    public TaiKhoanDto(Integer id, String email, String matkhau, String hoten, String dienthoai, Integer diemthuong, Integer loaitaikhoan) {
        this.id = id;
        this.email = email;
        this.matkhau = matkhau;
        this.hoten = hoten;
        this.dienthoai = dienthoai;
        this.diemthuong = diemthuong;
        this.loaitaikhoan = loaitaikhoan;
    }
}
