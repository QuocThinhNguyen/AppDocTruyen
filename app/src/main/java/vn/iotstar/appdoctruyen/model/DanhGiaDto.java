package vn.iotstar.appdoctruyen.model;

public class DanhGiaDto {
    private Integer idchapter;
    private Integer idtaikhoan;
    private Double sosao;

    public DanhGiaDto(Integer idchapter, Integer idtaikhoan, Double sosao) {
        this.idchapter = idchapter;
        this.idtaikhoan = idtaikhoan;
        this.sosao = sosao;
    }

    public Integer getIdchapter() {
        return idchapter;
    }

    public Integer getIdtaikhoan() {
        return idtaikhoan;
    }

    public Double getSosao() {
        return sosao;
    }

    public void setIdchapter(Integer idchapter) {
        this.idchapter = idchapter;
    }

    public void setIdtaikhoan(Integer idtaikhoan) {
        this.idtaikhoan = idtaikhoan;
    }

    public void setSosao(Double sosao) {
        this.sosao = sosao;
    }
}
