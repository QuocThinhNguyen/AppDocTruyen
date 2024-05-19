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

        public Chapter(truyen idtruyen, String tenchapter, LocalDate ngaydang, Integer soluotxem, Double danhgia) {
            this.idtruyen = idtruyen;
            this.tenchapter = tenchapter;
            this.ngaydang = ngaydang;
            this.soluotxem = soluotxem;
            this.danhgia = danhgia;
        }

        public Chapter(String tenchapter, LocalDate ngaydang, Integer soluotxem, Double danhgia) {
            this.tenchapter = tenchapter;
            this.ngaydang = ngaydang;
            this.soluotxem = soluotxem;
            this.danhgia = danhgia;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public truyen getIdtruyen() {
            return idtruyen;
        }

        public void setIdtruyen(truyen idtruyen) {
            this.idtruyen = idtruyen;
        }

        public String getTenchapter() {
            return tenchapter;
        }

        public void setTenchapter(String tenchapter) {
            this.tenchapter = tenchapter;
        }

        public LocalDate getNgaydang() {
            return ngaydang;
        }

        public void setNgaydang(LocalDate ngaydang) {
            this.ngaydang = ngaydang;
        }

        public Integer getSoluotxem() {
            return soluotxem;
        }

        public void setSoluotxem(Integer soluotxem) {
            this.soluotxem = soluotxem;
        }

        public Double getDanhgia() {
            return danhgia;
        }

        public void setDanhgia(Double danhgia) {
            this.danhgia = danhgia;
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

        public Set<Lichsudoctruyen> getLichsudoctruyens() {
            return lichsudoctruyens;
        }

        public void setLichsudoctruyens(Set<Lichsudoctruyen> lichsudoctruyens) {
            this.lichsudoctruyens = lichsudoctruyens;
        }

        public Set<Noidungchapter> getNoidungchapters() {
            return noidungchapters;
        }

        public void setNoidungchapters(Set<Noidungchapter> noidungchapters) {
            this.noidungchapters = noidungchapters;
        }
    }