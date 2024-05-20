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

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public String getHoten() {
        return hoten;
    }

    public String getDienthoai() {
        return dienthoai;
    }

    public Integer getDiemthuong() {
        return diemthuong;
    }

    public Integer getLoaitaikhoan() {
        return loaitaikhoan;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }

    public void setDiemthuong(Integer diemthuong) {
        this.diemthuong = diemthuong;
    }

    public void setLoaitaikhoan(Integer loaitaikhoan) {
        this.loaitaikhoan = loaitaikhoan;
    }
}
