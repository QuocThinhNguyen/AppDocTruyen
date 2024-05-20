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

    public Taikhoan(String hoten, String dienthoai) {
        this.hoten = hoten;
        this.dienthoai = dienthoai;
    }

    public Taikhoan(String email, String matkhau, String hoten, String dienthoai, Integer diemthuong, Integer loaitk, String linkanh) {
        this.email = email;
        this.matkhau = matkhau;
        this.hoten = hoten;
        this.dienthoai = dienthoai;
        this.diemthuong = diemthuong;
        this.loaitk = loaitk;
        this.linkanh = linkanh;
    }

    public Taikhoan() {
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

    public void setLoaitk(Integer loaitk) {
        this.loaitk = loaitk;
    }

    public void setLinkanh(String linkanh) {
        this.linkanh = linkanh;
    }

    public void setBinhluans(Set<Binhluan> binhluans) {
        this.binhluans = binhluans;
    }

    public void setDanhgias(Set<Danhgia> danhgias) {
        this.danhgias = danhgias;
    }

    public void setDiemthuongs(Set<Diemthuong> diemthuongs) {
        this.diemthuongs = diemthuongs;
    }

    public void setDoithuongs(Set<Doithuong> doithuongs) {
        this.doithuongs = doithuongs;
    }

    public void setLichsudoctruyens(Set<Lichsudoctruyen> lichsudoctruyens) {
        this.lichsudoctruyens = lichsudoctruyens;
    }

    public Taikhoan(String email, String matkhau, String hoten, String dienthoai, Integer diemthuong, Integer loaitk) {
        this.email = email;
        this.matkhau = matkhau;
        this.hoten = hoten;
        this.dienthoai = dienthoai;
        this.diemthuong = diemthuong;
        this.loaitk = loaitk;
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

    public Integer getLoaitk() {
        return loaitk;
    }

    public String getLinkanh() {
        return linkanh;
    }

    public Set<Binhluan> getBinhluans() {
        return binhluans;
    }

    public Set<Danhgia> getDanhgias() {
        return danhgias;
    }

    public Set<Diemthuong> getDiemthuongs() {
        return diemthuongs;
    }

    public Set<Doithuong> getDoithuongs() {
        return doithuongs;
    }

    public Set<Lichsudoctruyen> getLichsudoctruyens() {
        return lichsudoctruyens;
    }

    private Set<Danhgia> danhgias ;
    private Set<Diemthuong> diemthuongs;

    private Set<Doithuong> doithuongs ;
    private Set<Lichsudoctruyen> lichsudoctruyens ;


}