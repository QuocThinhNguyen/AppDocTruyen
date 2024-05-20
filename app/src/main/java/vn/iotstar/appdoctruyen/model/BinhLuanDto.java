package vn.iotstar.appdoctruyen.model;

public class BinhLuanDto {
    private int idchapter;
    private int idtaikhoan;
    private String noidung;

    public BinhLuanDto( int idchapter, int idtaikhoan, String noidung) {

        this.idchapter = idchapter;
        this.idtaikhoan = idtaikhoan;
        this.noidung = noidung;
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

    public void setIdchapter(int idchapter) {
        this.idchapter = idchapter;
    }

    public void setIdtaikhoan(int idtaikhoan) {
        this.idtaikhoan = idtaikhoan;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

}
