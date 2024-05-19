package vn.iotstar.appdoctruyen.model;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BinhLuanTruyenDto {
    private String linkAnh;
    private String email;
    private String noidung;
    private String ngaydang;
    private String tenChapter;

    public String getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        this.linkAnh = linkAnh;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public void setNgaydang(String ngaydang) {
        this.ngaydang = ngaydang;
    }

    public void setTenChapter(String tenChapter) {
        this.tenChapter = tenChapter;
    }

    public String getEmail() {
        return email;
    }

    public String getNoidung() {
        return noidung;
    }

    public String getNgaydang() {
        return ngaydang;
    }

    public String getTenChapter() {
        return tenChapter;
    }
}
