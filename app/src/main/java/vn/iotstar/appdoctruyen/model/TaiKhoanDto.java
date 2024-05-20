package vn.iotstar.appdoctruyen.model;


public class TaiKhoanDto {
    private Integer id;
    private String email;
    private String matkhau;
    private String hoten;
    private String dienthoai;
    private Integer diemthuong;
    private Integer loaitaikhoan;

    public TaiKhoanDto() {
    }

    public TaiKhoanDto(String email, String matkhau, String hoten, String dienthoai, Integer diemthuong, Integer loaitaikhoan) {
        this.email = email;
        this.matkhau = matkhau;
        this.hoten = hoten;
        this.dienthoai = dienthoai;
        this.diemthuong = diemthuong;
        this.loaitaikhoan = loaitaikhoan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }

    public Integer getDiemthuong() {
        return diemthuong;
    }

    public void setDiemthuong(Integer diemthuong) {
        this.diemthuong = diemthuong;
    }

    public Integer getLoaitaikhoan() {
        return loaitaikhoan;
    }

    public void setLoaitaikhoan(Integer loaitaikhoan) {
        this.loaitaikhoan = loaitaikhoan;
    }

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
