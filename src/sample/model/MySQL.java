package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySQL {
    Connection conn = null;

    public static Connection connectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/market", "root", "root");
            JOptionPane.showMessageDialog(null, "ConnectionEstablished");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public static ObservableList<Users> getDataUsers() {
        Connection conn = connectDb();
        ObservableList<Users> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Users(Integer
                        .parseInt(rs.getString("user_id")),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("type")));
            }
        } catch (Exception e) {
        }
        return list;
    }

}
