package vn.iotstar.appdoctruyen.model;

public class TaiKhoanDto {
    private Integer id;
    private String email;
    private String hoten;
    private String dienthoai;
    private Integer diemthuong;

    public TaiKhoanDto(Integer id, String email, String hoten, String dienthoai, Integer diemthuong) {
        this.id = id;
        this.email = email;
        this.hoten = hoten;
        this.dienthoai = dienthoai;
        this.diemthuong = diemthuong;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
