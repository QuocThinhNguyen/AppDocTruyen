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

    public Taikhoan(Integer id, String email, String matkhau, String hoten, String dienthoai, Integer diemthuong, Integer loaitk, String linkanh, Set<Binhluan> binhluans, Set<Danhgia> danhgias, Set<Diemthuong> diemthuongs, Set<Doithuong> doithuongs, Set<Lichsudoctruyen> lichsudoctruyens) {
        this.id = id;
        this.email = email;
        this.matkhau = matkhau;
        this.hoten = hoten;
        this.dienthoai = dienthoai;
        this.diemthuong = diemthuong;
        this.loaitk = loaitk;
        this.linkanh = linkanh;
        this.binhluans = binhluans;
        this.danhgias = danhgias;
        this.diemthuongs = diemthuongs;
        this.doithuongs = doithuongs;
        this.lichsudoctruyens = lichsudoctruyens;
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

    public Integer getLoaitk() {
        return loaitk;
    }

    public void setLoaitk(Integer loaitk) {
        this.loaitk = loaitk;
    }

    public String getLinkanh() {
        return linkanh;
    }

    public void setLinkanh(String linkanh) {
        this.linkanh = linkanh;
    }

    public Set<Binhluan> getBinhluans() {
        return binhluans;
    }

    public void setBinhluans(Set<Binhluan> binhluans) {
        this.binhluans = binhluans;
    }

    public Set<Danhgia> getDanhgias() {
        return danhgias;
    }

    public void setDanhgias(Set<Danhgia> danhgias) {
        this.danhgias = danhgias;
    }

    public Set<Diemthuong> getDiemthuongs() {
        return diemthuongs;
    }

    public void setDiemthuongs(Set<Diemthuong> diemthuongs) {
        this.diemthuongs = diemthuongs;
    }

    public Set<Doithuong> getDoithuongs() {
        return doithuongs;
    }

    public void setDoithuongs(Set<Doithuong> doithuongs) {
        this.doithuongs = doithuongs;
    }

    public Set<Lichsudoctruyen> getLichsudoctruyens() {
        return lichsudoctruyens;
    }

    public void setLichsudoctruyens(Set<Lichsudoctruyen> lichsudoctruyens) {
        this.lichsudoctruyens = lichsudoctruyens;
    }
}