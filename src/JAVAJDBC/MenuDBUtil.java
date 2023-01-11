package JAVAJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MenuDBUtil{

    Connection con = null;
    ResultSet rs = null;
    PreparedStatement psmt = null;

    public MenuDBUtil(){
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

        // 기본 형태
        // try {
        //     String sql = "SELECT * FROM DATETIME_DB_PYD";

        //     psmt = con.prepareStatement(sql);

        //     rs = psmt.executeQuery();

        //     while(rs.next()){
        //         String hour = rs.getString("LOG_HOUR");
        //         String min = rs.getString("LOG_MIN");
        //         String ynyn = rs.getString("MATCH_YN");

        //         System.out.println("시간 : " + hour + "분 : " + min + "Y or N : " + ynyn);
        //     }

        // } catch (Exception e) {
        //     // TODO: handle exception
        // }
    }
    // 여기까지가 생성자 abst


    //돌리기 둘렀을경우 실행되는 method
    //돌려돌려 돌림판
    public String spinSpinWheel(){
        
        String Menu = null;

        //HashSet hset = new HashSet();
        ArrayList<String> arraylist = new ArrayList<String>();
        
        try {
            String sql = "SELECT * FROM DATETIME_DB_PYD";

            psmt = con.prepareStatement(sql);

            rs = psmt.executeQuery();

            //메뉴들 넣기
            while(rs.next()){
                String hour = rs.getString("LOG_HOUR");
                String min = rs.getString("LOG_MIN");
                String ynyn = rs.getString("MATCH_YN");

                //arraylist.add(Menu);
                arraylist.add(rs.getString("LOG_HOUR"));

                System.out.println("시간 : " + hour + "분 : " + min + "Y or N : " + ynyn);
            }
            
            //랜덤값 뽑아오기 ㅎ
            Menu = arraylist.get((int)(Math.random() * arraylist.size())+1);

            System.out.println("********************");
            System.out.println("Menu : " + Menu);
            System.out.println("********************");

        } catch (Exception e) {
            // TODO: handle exception
        }
        return Menu;

    }


}