/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import connection.MysqlConnection;
import controller.ToDoListContrloller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author farhan
 */
public class ToDoListModel {
    Connection conn;
    Statement stm ;
    ResultSet result;
    
    public ToDoListModel() {
        try {
            this.conn = new MysqlConnection().getConnection();
            this.stm = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ToDoListModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     public void addData(String aktivitas){
        
        try {

            // Query untuk menambahkan data
            String query = "INSERT INTO `todo` (`id`, `aktivitas`, `status`) VALUES (NULL,?,?)";

            // Membuat objek PreparedStatement
            PreparedStatement statement = conn.prepareStatement(query);

            // Mengatur nilai parameter
            statement.setString(1, aktivitas);
            statement.setString(2, "belum selesai");

            // Menjalankan perintah SQL
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Data berhasil ditambahkan.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan data: " + e.getMessage());
        }
    }

    public void getTableData(JTable jtable) throws SQLException{
        result = stm.executeQuery("select * from todo");
        DefaultTableModel model = (DefaultTableModel)jtable.getModel();
        model.setRowCount(0);
        while(result.next()){
            String id = result.getString("id");
            String aktivitas = result.getString("aktivitas");
            String status = result.getString("status");
            model.addRow(new Object[]{id,aktivitas,status});
        }
      
        
    } 
       public void dropData(int id){
     
      try{
            // Query untuk menghapus data
            String query = "DELETE FROM todo WHERE id = ?";

            // Membuat objek PreparedStatement
            PreparedStatement statement = conn.prepareStatement(query);

            // Mengatur nilai parameter
            statement.setInt(1, id); // Misalnya, menghapus data dengan id = 1

            // Menjalankan perintah SQL
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Data berhasil dihapus.");
            } else {
                System.out.println("Data tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal menghapus data: " + e.getMessage());
        }
    }
       
        public void done(int id_barang){
     
        try {
            // Query untuk mengupdate data
            String query = "UPDATE todo SET status = 'selesai'  WHERE id = ? ";

            // Membuat objek PreparedStatement
            PreparedStatement statement = conn.prepareStatement(query);

            // Mengatur nilai parameter
            statement.setInt(1, id_barang);
      

            // Menjalankan perintah SQL
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Data berhasil diupdate.");
            } else {
                System.out.println("Data tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengupdate data: " + e.getMessage());
        }
     }

      
        
    
}
