package vn.iotstar.appdoctruyen.model;

public class BinhLuanDto {
    private Integer id;
    private int idchapter;
    private int idtaikhoan;
    private String noidung;
    private String ngaydang;
    public BinhLuanDto(Integer id, int idchapter, int idtaikhoan, String noidung, String ngaydang) {
        this.id = id;
        this.idchapter = idchapter;
        this.idtaikhoan = idtaikhoan;
        this.noidung = noidung;
        this.ngaydang = ngaydang;
    }

    public Integer getId() {
        return id;
    }

    public int getIdchapter() {
        return idchapter;
    }

    public int getIdtaikhoan() {
        return idtaikhoan;
    }

    public String getNoidung() {
        return noidung;
    }

    public String getNgaydang() {
        return ngaydang;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdchapter(int idchapter) {
        this.idchapter = idchapter;
    }

    public void setIdtaikhoan(int idtaikhoan) {
        this.idtaikhoan = idtaikhoan;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public void setNgaydang(String ngaydang) {
        this.ngaydang = ngaydang;
    }
}
