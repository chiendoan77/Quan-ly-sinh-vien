package QLSV.com;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static SinhVienDAO dao = new SinhVienDAO();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== QUAN LY SINH VIEN =====");
            System.out.println("1. Them sinh vien");
            System.out.println("2. In tat ca sinh vien");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    themSinhVien();
                    break;
                case 2:
                    dao.getAll();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }
    public static void themSinhVien() {

        System.out.print("Nhap ma SV: ");
        String masv = sc.nextLine();

        System.out.print("Nhap ho ten: ");
        String hoten = chuanHoaTen(sc.nextLine());

        System.out.print("Nhap ngay sinh (yyyy-MM-dd): ");
        String ns = sc.nextLine();

        if (!checkNgaySinh(ns)) {
            System.out.println("Ngay sinh khong hop le!");
            return;
        }

        System.out.print("Nhap nganh (CNTT/KTPM): ");
        String nganh = sc.nextLine();

        if (!checkNganh(nganh)) {
            System.out.println("Nganh khong hop le!");
            return;
        }

        if (!checkMaSV(masv, nganh)) {
            System.out.println("Ma SV khong dung dinh dang!");
            return;
        }

        System.out.print("Nhap diem TB: ");
        double diem = sc.nextDouble();
        sc.nextLine();

        if (!checkDiem(diem)) {
            System.out.println("Diem khong hop le!");
            return;
        }

        System.out.print("Nhap lop: ");
        String lop = sc.nextLine();

        SinhVien sv = new SinhVien(
                masv,
                hoten,
                LocalDate.parse(ns),
                nganh,
                diem,
                lop
        );

        dao.insert(sv);
    }
    public static boolean checkNgaySinh(String input) {
        try {
            LocalDate date = LocalDate.parse(input);
            int age = Period.between(date, LocalDate.now()).getYears();
            return age >= 15 && age <= 110;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean checkDiem(double d) {
        return d >= 0.0 && d <= 10.0;
    }

    public static boolean checkNganh(String n) {
        return n.equals("CNTT") || n.equals("KTPM");
    }

    public static boolean checkMaSV(String masv, String nganh) {
        if (!masv.matches("\\d{10}")) return false;

        if (nganh.equals("CNTT"))
            return masv.startsWith("455105");

        if (nganh.equals("KTPM"))
            return masv.startsWith("455109");

        return false;
    }

    public static String chuanHoaTen(String ten) {
        ten = ten.trim().toLowerCase();
        String[] words = ten.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            sb.append(Character.toUpperCase(w.charAt(0)))
              .append(w.substring(1))
              .append(" ");
        }
        return sb.toString().trim();
    }
}
