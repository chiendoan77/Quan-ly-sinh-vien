package QLSV.com;

import java.sql.*;

public class SinhVienDAO {

    public void insert(SinhVien sv) {
        String sql = "INSERT INTO sinhvien VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sv.masv);
            ps.setString(2, sv.hoten);
            ps.setDate(3, Date.valueOf(sv.ngaysinh));
            ps.setString(4, sv.nganh);
            ps.setDouble(5, sv.diemtb);
            ps.setString(6, sv.lop);

            ps.executeUpdate();
            System.out.println("Thêm thành công!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void getAll() {
        String sql = "SELECT * FROM sinhvien";

        try (Connection conn = DBUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                    rs.getString("masv") + " | " +
                    rs.getString("hoten") + " | " +
                    rs.getDate("ngaysinh") + " | " +
                    rs.getString("nganh") + " | " +
                    rs.getDouble("diemtb") + " | " +
                    rs.getString("lop")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
