package QLSV.com;

import java.time.LocalDate;

public class SinhVien {
    String masv;
    String hoten;
    LocalDate ngaysinh;
    String nganh;
    double diemtb;
    String lop;

    public SinhVien(String masv, String hoten, LocalDate ngaysinh,
                    String nganh, double diemtb, String lop) {
        this.masv = masv;
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.nganh = nganh;
        this.diemtb = diemtb;
        this.lop = lop;
    }
}