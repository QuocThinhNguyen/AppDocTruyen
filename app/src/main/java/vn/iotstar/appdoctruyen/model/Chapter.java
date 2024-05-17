    package vn.iotstar.appdoctruyen.model;


    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.time.LocalDate;
    import java.util.LinkedHashSet;
    import java.util.Set;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter

    public class Chapter {

        private Integer id;
        private truyen idtruyen;
        private String tenchapter;
        private LocalDate ngaydang;
        private Integer soluotxem;
        private Double danhgia;

        private Set<Binhluan> binhluans ;

        private Set<Danhgia> danhgias;
        private Set<Lichsudoctruyen> lichsudoctruyens;
        private Set<Noidungchapter> noidungchapters ;

    }