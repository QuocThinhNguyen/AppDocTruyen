package vn.iotstar.appdoctruyen.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Noidungchapter {
    private Integer id;
    private Integer idchapter;
    private String linkanh;

    public Noidungchapter(String linkanh) {
        this.linkanh = linkanh;
    }

    public Noidungchapter(Integer idchapter, String linkanh) {
        this.idchapter = idchapter;
        this.linkanh = linkanh;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdchapter() {
        return idchapter;
    }

    public void setIdchapter(Integer idchapter) {
        this.idchapter = idchapter;
    }

    public String getLinkanh() {
        return linkanh;
    }

    public void setLinkanh(String linkanh) {
        this.linkanh = linkanh;
    }


    public Noidungchapter(Integer id, Integer idchapter, String linkanh) {
        this.id = id;
        this.idchapter = idchapter;
        this.linkanh = linkanh;
    }



    public Noidungchapter() {
    }

}