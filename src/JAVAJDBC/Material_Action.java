/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JAVAJDBC;

import TableBean.TODAYLUNCH_MENU_BEAN;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Material_Action {
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement psmt = null;

    //bean 파일
    TODAYLUNCH_MENU_BEAN TODAYLUNCH_MENU_BEAN = new TODAYLUNCH_MENU_BEAN();

    public Material_Action(){
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


    public void LunchSelectUpCount(String MENU_NO){
        try {
            String sql = "UPDATE TODAYLUNCH_MENU SET MENU_SELECT_COUNT = MENU_SELECT_COUNT+1 where MENU_NO = ?";
            
            psmt = con.prepareStatement(sql);
            System.out.println(MENU_NO);
            psmt.setString(1, MENU_NO);
            
            rs = psmt.executeQuery();
        } catch (Exception e) {
        }
    }
    
    public void LunchNotSelectUpCount(String MENU_NO){
        try {
            String sql = "UPDATE TODAYLUNCH_MENU SET MENU_CANSLE_COUNT = MENU_CANSLE_COUNT+1 where MENU_NO = ?";
            
            psmt = con.prepareStatement(sql);
            System.out.println(MENU_NO);
            psmt.setString(1, MENU_NO);
            
            rs = psmt.executeQuery();
        } catch (Exception e) {
        }
    }
    
    public void logSelectUpdate(String MENU_NO){
        try {
            String sql1 = "UPDATE TODAYLUNCH_LOG SET"
                        + " MENU_SELECT = 'Y'"
                        + " where LAST_START_TIME = (SELECT LAST_START_TIME FROM TODAYLUNCH_LOG ORDER BY LAST_START_TIME DESC LIMIT 1)";

            String sql2 = "UPDATE TODAYLUNCH_TODAY_SELECT SET"
                        + " MENU_SELECT = 'Y'"
                        + " where LAST_START_TIME = (SELECT LAST_START_TIME FROM TODAYLUNCH_TODAY_SELECT ORDER BY LAST_START_TIME DESC LIMIT 1)";
            
            psmt = con.prepareStatement(sql1);
            rs = psmt.executeQuery();
            
            psmt = con.prepareStatement(sql2);
            rs = psmt.executeQuery();
        } catch (Exception e) {
        }
    }
}
