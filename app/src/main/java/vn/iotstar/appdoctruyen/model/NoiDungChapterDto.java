package vn.iotstar.appdoctruyen.model;

public class NoiDungChapterDto {
    private Integer id;
    private Integer idchapter;

    private String linkanh;

    public NoiDungChapterDto() {
    }

    public NoiDungChapterDto(Integer id, Integer idchapter, String linkanh) {
        this.id = id;
        this.idchapter = idchapter;
        this.linkanh = linkanh;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdchapter() {
        return idchapter;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdchapter(Integer idchapter) {
        this.idchapter = idchapter;
    }

    public void setLinkanh(String linkanh) {
        this.linkanh = linkanh;
    }

    public String getLinkanh() {
        return linkanh;
    }
}
