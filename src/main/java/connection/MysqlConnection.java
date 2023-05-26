/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;
import java.sql.*;

/**
 *
 * @author farhan
 */
public class MysqlConnection {
     // Informasi koneksi database
        String url = "jdbc:mysql://localhost:3306/todolist";
        String username = "root";
        String password = "";

        public Connection getConnection(){
        
        // Membuat objek koneksi
        Connection conn = null;
        try {
            // Membuat koneksi
            conn = DriverManager.getConnection(this.url, this.username, this.password);

            if (conn != null) {
                System.out.println("Koneksi sukses!");
                // Lakukan operasi database di sini
            }
        } catch(SQLException e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
        return conn;
        }
}

