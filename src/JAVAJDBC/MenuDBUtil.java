package JAVAJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MenuDBUtil{

    Connection con = null;
    ResultSet rs = null;
    PreparedStatement psmt = null;

    //bean 파일
    MenuBean mbean = new MenuBean();

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
    }
    // 여기까지가 생성자 abst


    //단순 메뉴 리스트 뽑아오기용
    public String[] todayLunchList(){
        String[] menuList = new String[0];
        int i =0;
        ArrayList<String> list = new ArrayList<String>();

        try {
            String sql = "SELECT MENU_NAME FROM TODAYLUNCH_MENU";

            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            
            //메뉴들 넣기
            while(rs.next()){
                //칼럼데이터 받아서 넣기
                list.add(rs.getString("MENU_NAME"));
            }

            menuList = new String[list.size()];

            for(i = 0 ; i <= list.size() ; i++){
                menuList[i] = list.get(i);
            }

            System.out.println("********************");
            System.out.println("오늘의 메뉴 : " + menuList);
            System.out.println("********************");

        } catch (Exception e) {
            // TODO: handle exception
        }
        

        return menuList;
    }

    //돌리기 둘렀을경우 실행되는 method
    //돌려돌려 돌림판
    public MenuBean spinSpinWheel(){
        
        //String Menu = null;

        //HashSet hset = new HashSet();
        ArrayList<MenuBean> arraylist = new ArrayList<MenuBean>();
        
        try {
            String sql = "SELECT * FROM TODAYLUNCH_MENU";

            psmt = con.prepareStatement(sql);

            rs = psmt.executeQuery();

            //메뉴들 넣기
            while(rs.next()){

                mbean = new MenuBean();

                mbean.setMENU_NAME(rs.getString("MENU_NAME"));
                mbean.setMENU_STORENAME(rs.getString("MENU_STORENAME"));
                mbean.setMENU_CATE(rs.getString("MENU_CATE"));
                mbean.setMENU_ADDRESS(rs.getString("MENU_ADDRESS"));
                mbean.setMENU_SELECT_COUNT(rs.getInt("MENU_SELECT_COUNT"));
                mbean.setMENU_INTRODUCTION(rs.getString("MENU_INTRODUCTION"));
                mbean.setMENU_NO(rs.getString("MENU_NO"));
                
                //칼럼데이터 받아서 넣기
                //Menu = rs.getString("MENU_NAME");
                //System.out.println(rs.getString("MENU_NAME"));

                //칼럼데이터 ArrayList에 넣어주기
                arraylist.add(mbean);
            }

            
            //랜덤값 뽑아오기 ㅎ
            //mbean = arraylist.get((int)(Math.random() * arraylist.size())+1);
            mbean = arraylist.get((int)(Math.random() * arraylist.size())+1);

            System.out.println(arraylist.get((int)(Math.random() * arraylist.size())+1).getMENU_NAME());

            //System.out.println("********************");
            //System.out.println("List size : " + arraylist.size());
            //System.out.println("오늘의 메뉴 : " + Menu);
            //System.out.println("********************");

        } catch (Exception e) {
            // TODO: handle exception
        }
        return mbean;
    }
    
    //insert 진행시
    public int menuInsert(MenuBean mbean){
        try {
            String sql = "Insert into TODAYLUNCH_MENU"
                    + "(MENU_NO, MENU_NAME,MENU_STORENAME,MENU_ADDRESS,MENU_CATE,MENU_INTRODUCTION) values "
                    + "(NEXTVAL(TODAYLUNCH_MENU_SEQ),?,?,?,?,?)";

            psmt = con.prepareStatement(sql);
            
            psmt.setString(1, mbean.getMENU_NAME());
            psmt.setString(2,mbean.getMENU_STORENAME());
            psmt.setString(3,mbean.getMENU_CATE());
            psmt.setString(4,mbean.getMENU_ADDRESS());
            psmt.setString(5,mbean.getMENU_INTRODUCTION());
            
            rs = psmt.executeQuery();
            System.out.println("count : " + rs);
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        
        return 0;
    }
    
    public void menuDelete(String MENU_NO){
        try {
            String sql = "DELETE FROM TODAYLUNCH_MENU WHERE MENU_NO = ?";

            psmt = con.prepareStatement(sql);
            
            psmt.setString(1, MENU_NO);
            
            rs = psmt.executeQuery();
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
}