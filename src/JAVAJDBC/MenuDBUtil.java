package JAVAJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MenuDBUtil{
    public static void main(String[] args){
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://192.168.0.74:3307/youngriabase";
            String id = "youngriadb";
            String pw = "Password0000!";
            System.out.println("testtest DB Connect");

            try {
                con = DriverManager.getConnection(url, id, pw);
                System.out.println("DB계정 일치 succes");
            } catch (Exception e) {
                System.out.println("DB계정 불일치 discord");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("DB연결 실패  fail");
            e.printStackTrace();
        }
    }
}