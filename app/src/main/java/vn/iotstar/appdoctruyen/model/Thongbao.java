package vn.iotstar.appdoctruyen.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Thongbao {
    private Integer id;
    private String tieude;
    private String noidung;
    private LocalDate ngaydang;

}