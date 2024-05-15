package vn.iotstar.appdoctruyen.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Noidungchapter {
    private Integer id;
    private Chapter idchapter;
    private String linkanh;

    public Noidungchapter(Integer id, Chapter idchapter, String linkanh) {
        this.id = id;
        this.idchapter = idchapter;
        this.linkanh = linkanh;
    }

    public Noidungchapter() {
    }
}