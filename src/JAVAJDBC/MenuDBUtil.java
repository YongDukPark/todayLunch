package JAVAJDBC;

import TableBean.TODAYLUNCH_MENU_BEAN;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MenuDBUtil{

    Connection con = null;
    ResultSet rs = null;
    PreparedStatement psmt = null;

    //bean 파일
    TODAYLUNCH_MENU_BEAN mbean = new TODAYLUNCH_MENU_BEAN();

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
            String sql = "SELECT * FROM TODAYLUNCH_MENU ORDER BY MENU_SELECT_COUNT DESC";

            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            
            //메뉴들 넣기
            while(rs.next()){
                //칼럼데이터 받아서 넣기
                list.add(rs.getString("MENU_NAME") + "[" + rs.getString("MENU_SELECT_COUNT") + "/" + rs.getString("MENU_CANSLE_COUNT") +"]");
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
    //230119 중복제거 쿼리문 수정 및 준비된 메뉴들 전부 소진시 resetCount+1 시키고 초기화
    public TODAYLUNCH_MENU_BEAN spinSpinWheel(){
        ArrayList<TODAYLUNCH_MENU_BEAN> arraylist = new ArrayList<TODAYLUNCH_MENU_BEAN>();
        String sql1, sql2, sql3 = null;
        String count = null;
        try {
            //row 갯수 가져오기
            sql1 = "SELECT "
                        + "COUNT(*) COUNT "
                        + "FROM TODAYLUNCH_MENU TL_MENU LEFT JOIN "
                    + "(SELECT "
                        + "TL_LOG.MENU_NO , "
                        + "TL_LOG.MENU_NAME "
                        + "FROM TODAYLUNCH_LOG TL_LOG LEFT OUTER JOIN TODAYLUNCH_TODAY_SELECT TL_SELECT "
                        + "ON TL_LOG.MENU_RESET_COUNT = TL_SELECT.MENU_RESET_COUNT "
                        + "WHERE TL_LOG.LAST_START_TIME > SUBTIME(NOW(), TIMEDIFF(NOW(), CAST(DATE(NOW()) AS DATETIME))) "
                        + "AND TL_LOG.MENU_RESET_COUNT = (SELECT MENU_RESET_COUNT FROM TODAYLUNCH_TODAY_SELECT ORDER BY LAST_START_TIME DESC LIMIT 1) "
                    + ") TL_JOIN "
                        + "ON TL_MENU.MENU_NO = TL_JOIN.MENU_NO "
                        + "where TL_JOIN.MENU_NO IS NULL";
            psmt = con.prepareStatement(sql1);

            rs = psmt.executeQuery();

            while(rs.next()){
                count = rs.getString("COUNT");
            }
            
            //만약 row없으면 update시켜서 count+1 시켜야 메뉴들이 나온다.
            if(count.equals("0") || count.equals(null)){
                //System.out.println("으에에에엑으에에에엑으에에에엑으에에에엑으에에에엑\n으에에에엑으에에에엑으에에에엑으에에에엑");
                sql2 = "UPDATE TODAYLUNCH_TODAY_SELECT SET MENU_RESET_COUNT = MENU_RESET_COUNT+1 "
                        + "WHERE LAST_START_TIME = (SELECT LAST_START_TIME FROM TODAYLUNCH_TODAY_SELECT ORDER BY LAST_START_TIME DESC LIMIT 1)";
                psmt = con.prepareStatement(sql2);
                rs = psmt.executeQuery();
            }
            //이전
            //String sql = "SELECT * FROM TODAYLUNCH_MENU";
            
            sql3 = "SELECT "
                        + "TL_MENU.* "
                        + "FROM TODAYLUNCH_MENU TL_MENU LEFT JOIN "
                    + "(SELECT "
                        + "TL_LOG.MENU_NO , "
                        + "TL_LOG.MENU_NAME "
                        + "FROM TODAYLUNCH_LOG TL_LOG LEFT OUTER JOIN TODAYLUNCH_TODAY_SELECT TL_SELECT "
                        + "ON TL_LOG.MENU_RESET_COUNT = TL_SELECT.MENU_RESET_COUNT "
                        + "WHERE TL_LOG.LAST_START_TIME > SUBTIME(NOW(), TIMEDIFF(NOW(), CAST(DATE(NOW()) AS DATETIME))) "
                        + "AND TL_LOG.MENU_RESET_COUNT = (SELECT MENU_RESET_COUNT FROM TODAYLUNCH_TODAY_SELECT ORDER BY LAST_START_TIME DESC LIMIT 1) "
                    + ") TL_JOIN "
                        + "ON TL_MENU.MENU_NO = TL_JOIN.MENU_NO "
                        + "where TL_JOIN.MENU_NO IS NULL";
            
            psmt = con.prepareStatement(sql3);

            rs = psmt.executeQuery();

            //메뉴들 넣기
            while(rs.next()){

                mbean = new TODAYLUNCH_MENU_BEAN();

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
            
            
            mbean = arraylist.get((int)(Math.random() * arraylist.size())+1);

            System.out.println(arraylist.get((int)(Math.random() * arraylist.size())+1).getMENU_NAME());
        } catch (Exception e) {
        }
        return mbean;
    }
    
    public String checkYN(){
        String checkYN = null;
        try {
            String sql = "SELECT MENU_SELECT FROM TODAYLUNCH_TODAY_SELECT WHERE LAST_START_TIME = (SELECT LAST_START_TIME FROM TODAYLUNCH_TODAY_SELECT ORDER BY LAST_START_TIME DESC LIMIT 1)";

            psmt = con.prepareStatement(sql);
            
            rs = psmt.executeQuery();
            
            while(rs.next()){
                checkYN = rs.getString("MENU_SELECT");
            }
            
        } catch (Exception e) {
        }
        
        return checkYN;
    }
    
    //돌려돌려 돌림판시 같이 진행됨
    public void insertLog(String MENU_NO, String MENU_NAME){
        String MENU_RESET_COUNT = null;
        try {
            String sql1 = "SELECT MENU_RESET_COUNT FROM TODAYLUNCH_TODAY_SELECT ORDER BY LAST_START_TIME DESC LIMIT 1";
            psmt = con.prepareStatement(sql1);
            rs = psmt.executeQuery();
            
            while (rs.next()) {
                MENU_RESET_COUNT = rs.getString("MENU_RESET_COUNT");
            }
            
            
            String sql2 = "INSERT INTO TODAYLUNCH_LOG "
                    + "(MENU_NO, MENU_NAME, MENU_RESET_COUNT) "
                    + "VALUES (?,?,?)";

            psmt = con.prepareStatement(sql2);
            
            psmt.setString(1, MENU_NO);
            psmt.setString(2, MENU_NAME);
            psmt.setString(3, MENU_RESET_COUNT);
            
            rs = psmt.executeQuery();
            
        } catch (Exception e) {
        }
    }
    public void upCount(String MENU_NO, String MENU_NAME){
        try {
            String sql = "UPDATE TODAYLUNCH_TODAY_SELECT SET"
                    + " MENU_NO = ? , MENU_NAME = ? , MENU_SELECT_TOTALCOUNT = MENU_SELECT_TOTALCOUNT+1"
                    + " where LAST_START_TIME = (SELECT LAST_START_TIME FROM TODAYLUNCH_TODAY_SELECT ORDER BY LAST_START_TIME DESC LIMIT 1)";
            
            psmt = con.prepareStatement(sql);
            
            psmt.setString(1, MENU_NO);
            psmt.setString(2, MENU_NAME);
            
            rs = psmt.executeQuery();
            
        } catch (Exception e) {
        }
    }
    
    
    //insert 진행시
    public int menuInsert(TODAYLUNCH_MENU_BEAN mbean){
        try {
            String sql = "Insert into TODAYLUNCH_MENU"
                    + "(MENU_NO, MENU_NAME,MENU_STORENAME,MENU_CATE,MENU_ADDRESS,MENU_INTRODUCTION) values "
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
        }
    }
    
    //Menu insert
    public void insertRow(TODAYLUNCH_MENU_BEAN bean){
        try{
            String sql = "INSERT INTO TODAYLUNCH_MENU (MENU_NO , MENU_NAME, MENU_STORENAME, MENU_CATE, MENU_INTRODUCTION, MENU_ADDRESS) VALUES "
                    + "(NEXTVAL(TODAYLUNCH_MENU_SEQ),?,?,?,?,?)";
            
            psmt = con.prepareStatement(sql);
            //System.out.println("test t t t 후롸길 후롸길 " + bean.getMENU_NAME());
            psmt.setString(1, bean.getMENU_NAME());
            //System.out.println("test t t t 후롸길 후롸길 " + bean.getMENU_STORENAME());
            psmt.setString(2, bean.getMENU_STORENAME());
            //System.out.println("test t t t 후롸길 후롸길 " + bean.getMENU_CATE());
            psmt.setString(3, bean.getMENU_CATE());
            //System.out.println("test t t t 후롸길 후롸길 " + bean.getMENU_INTRODUCTION());
            psmt.setString(4, bean.getMENU_INTRODUCTION());
            //System.out.println("test t t t 후롸길 후롸길 " + bean.getMENU_ADDRESS());
            psmt.setString(5, bean.getMENU_ADDRESS());
            psmt.executeUpdate();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    //Menu 수정
    public void updateRow(TODAYLUNCH_MENU_BEAN bean){
        try{
            String sql = "UPDATE TODAYLUNCH_MENU SET MENU_NAME = ?, MENU_STORENAME = ?, MENU_CATE = ?, MENU_INTRODUCTION = ?, MENU_ADDRESS = ? "
                    + "WHERE MENU_NO = ?";
            
            psmt = con.prepareStatement(sql);
            psmt.setString(1, bean.getMENU_NAME());
            psmt.setString(2, bean.getMENU_STORENAME());
            psmt.setString(3, bean.getMENU_CATE());
            psmt.setString(4, bean.getMENU_INTRODUCTION());
            psmt.setString(5, bean.getMENU_ADDRESS());
            psmt.setString(6, bean.getMENU_NO());
            psmt.executeUpdate();
        }catch(Exception e){
            
        }
    }
    
    
}