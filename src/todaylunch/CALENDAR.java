/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todaylunch;

import com.arisystem.beans.datawizard.DWWhereCondition;
import com.arisystem.beans.framebuilder.FBRowSet;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

public class CALENDAR extends javax.swing.JFrame {

    public CALENDAR() {
        initComponents();
        //row갯수 가져오는놈
        System.out.println(TUE_1.getRowCount());
        //System.out.println(MON_7.getFixedRows()[0]);
        //이름은 이놈으로 가져오면 된다.
        //System.out.println(TUE_1.getBoundaryRenderer().getBoundaryCell("BoundaryCell_14").setValue(SAT_1));
        //TUE_1.getBoundaryRenderer().getBoundaryCell("BoundaryCell_14").setValue("testtest");
        //TUE_1.getBoundaryRenderer().get
        
        //이놈으로 지우면 된다.
        //TUE_1.getBoundaryRenderer().removeBoundaryCell("BoundaryCell_14");
        test();
    }
    
    //흠 로직을 생각하자 로직로오오오직
    
    
    //데이터 재료들
    public void test(){
        //where절 사용할때 필요한애들
        Vector params = new Vector();
        DWWhereCondition SearchSelect;
        FBRowSet seseset;
        
        //요일 계산용
        LocalDate date;
        DayOfWeek dayOfWeek;
        Calendar cal = Calendar.getInstance();
        
        int startWeek = 0;
        int rowCount = 0;
        int setDay = 1;
        boolean dataCheck = true;
        
        try {
            //where절 사용할때 필요한 애들
            params.add("01");
            SearchSelect = new DWWhereCondition("SELECT_YEAR = '2023' AND SELECT_MONTH = ?", params);
            dWMultiRowsObject1.setWhereContition(SearchSelect);
            
            dWMultiRowsObject1.setDataSource("MariaDB_Youngria");
            dWMultiRowsObject1.setOrderBy("SELECT_DAY");
            seseset = dWMultiRowsObject1.select("http", "192.168.0.20", 8080);
            
            for(int i = 0 ; i < seseset.getRowCount() ; i++){
                System.out.println(seseset.getValue(i, "MENU_NAME"));
            }
            
            System.out.println(seseset.getValue(0, "SELECT_YEAR"));
            System.out.println(seseset.getValue(0, "SELECT_MONTH"));
            System.out.println(seseset.getValue(0, "SELECT_DAY"));
            System.out.println(TUE_1.getBoundaryRenderer().getBoundaryCell("BoundaryCell_14").getValue());
            System.out.println(TUE_1.getBoundaryRenderer().getBoundaryCell("BoundaryCell_14").getName());
            System.out.println("?????? : "+boundaryPanel1.getBoundaryRenderer().getBoundaryCell("C_C_3_1").getCellList()[0]);
            
            //요일 구하는 로직 (년,월,일) int형태로 들어간다.
            date = LocalDate.of(Integer.parseInt((String)seseset.getValue(0, "SELECT_YEAR")), Integer.parseInt((String)seseset.getValue(0, "SELECT_MONTH")), Integer.parseInt((String)seseset.getValue(0, "SELECT_DAY")));
            dayOfWeek = date.getDayOfWeek();
            // 시작요일 월1 일7
            startWeek = dayOfWeek.getValue();
            //만약 일요일일 경우 0부터 시작하도록
            if(startWeek == 7){
                startWeek = 0;
            }
            // 해당 월의 마지막일 구하기 (년,월,일) int 형태로 들어간다.
            cal.set(Integer.parseInt((String)seseset.getValue(0, "SELECT_YEAR")), Integer.parseInt((String)seseset.getValue(0, "SELECT_MONTH"))-1, Integer.parseInt((String)seseset.getValue(0, "SELECT_DAY")));
            System.out.println(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            
            System.out.println("********************");
            System.out.println(seseset.getRowCount());
            System.out.println("********************");
//            if(seseset.getValue(12, "MENU_NAME") == "null"){
//                System.out.println("없음");
//            }
            
            //날짜 넣는 구간
            //i가 dayOfWeek 즉 요일첫번째부터 시작된다. 위에서 만약 7일 경우 값은 0이된다.
            for(int i = startWeek ; i < startWeek + cal.getActualMaximum(Calendar.DAY_OF_MONTH) ; i++){
                if(rowCount >= seseset.getRowCount()){
                    dataCheck = false;
                }
                if(i == 0){ //주말
                    DAY_1.setText(String.valueOf(setDay));
                } else if ( i == 1){
                    DAY_2.setText(String.valueOf(setDay));
                    if(dataCheck){
                        MON_MENU_NAME_1.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        MON_MENU_SELECT_COUNT_1.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        MON_MENU_RESET_COUNT_1.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 2){
                    DAY_3.setText(String.valueOf(setDay));
                    if(dataCheck){
                        TUE_MENU_NAME_1.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        TUE_MENU_SELECT_COUNT_1.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        TUE_MENU_RESET_COUNT_1.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 3){
                    DAY_4.setText(String.valueOf(setDay));
                    if(dataCheck){
                        WED_MENU_NAME_1.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        WED_MENU_SELECT_COUNT_1.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        WED_MENU_RESET_COUNT_1.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 4){
                    DAY_5.setText(String.valueOf(setDay));
                    if(dataCheck){
                        THU_MENU_NAME_1.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        THU_MENU_SELECT_COUNT_1.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        THU_MENU_RESET_COUNT_1.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 5){
                    DAY_6.setText(String.valueOf(setDay));
                    if(dataCheck){
                        FRI_MENU_NAME_1.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        FRI_MENU_SELECT_COUNT_1.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        FRI_MENU_RESET_COUNT_1.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 6){ //주말
                    DAY_7.setText(String.valueOf(setDay));
                } else if ( i == 7){ //주말
                    DAY_8.setText(String.valueOf(setDay));
                } else if ( i == 8){
                    DAY_9.setText(String.valueOf(setDay));
                    if(dataCheck){
                        MON_MENU_NAME_2.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        MON_MENU_SELECT_COUNT_2.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        MON_MENU_RESET_COUNT_2.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 9){
                    DAY_10.setText(String.valueOf(setDay));
                    if(dataCheck){
                        TUE_MENU_NAME_2.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        TUE_MENU_SELECT_COUNT_2.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        TUE_MENU_RESET_COUNT_2.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 10){
                    DAY_11.setText(String.valueOf(setDay));
                    if(dataCheck){
                        WED_MENU_NAME_2.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        WED_MENU_SELECT_COUNT_2.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        WED_MENU_RESET_COUNT_2.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 11){
                    DAY_12.setText(String.valueOf(setDay));
                    if(dataCheck){
                        THU_MENU_NAME_2.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        THU_MENU_SELECT_COUNT_2.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        THU_MENU_RESET_COUNT_2.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 12){
                    DAY_13.setText(String.valueOf(setDay));
                    if(dataCheck){
                        FRI_MENU_NAME_2.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        FRI_MENU_SELECT_COUNT_2.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        FRI_MENU_RESET_COUNT_2.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 13){ //주말
                    DAY_14.setText(String.valueOf(setDay));
                } else if ( i == 14){ //주말
                    DAY_15.setText(String.valueOf(setDay));
                } else if ( i == 15){
                    DAY_16.setText(String.valueOf(setDay));
                    if(dataCheck){
                        MON_MENU_NAME_3.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        MON_MENU_SELECT_COUNT_3.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        MON_MENU_RESET_COUNT_3.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 16){
                    DAY_17.setText(String.valueOf(setDay));
                    if(dataCheck){
                        TUE_MENU_NAME_3.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        TUE_MENU_SELECT_COUNT_3.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        TUE_MENU_RESET_COUNT_3.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 17){
                    DAY_18.setText(String.valueOf(setDay));
                    if(dataCheck){
                        WED_MENU_NAME_3.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        WED_MENU_SELECT_COUNT_3.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        WED_MENU_RESET_COUNT_3.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 18){
                    DAY_19.setText(String.valueOf(setDay));
                    if(dataCheck){
                        THU_MENU_NAME_3.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        THU_MENU_SELECT_COUNT_3.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        THU_MENU_RESET_COUNT_3.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 19){
                    DAY_20.setText(String.valueOf(setDay));
                    if(dataCheck){
                        FRI_MENU_NAME_3.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        FRI_MENU_SELECT_COUNT_3.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        FRI_MENU_RESET_COUNT_3.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 20){ //주말
                    DAY_21.setText(String.valueOf(setDay));
                } else if ( i == 21){ //주말
                    DAY_22.setText(String.valueOf(setDay));
                } else if ( i == 22){
                    DAY_23.setText(String.valueOf(setDay));
                    if(dataCheck){
                        MON_MENU_NAME_4.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        MON_MENU_SELECT_COUNT_4.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        MON_MENU_RESET_COUNT_4.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 23){
                    DAY_24.setText(String.valueOf(setDay));
                    if(dataCheck){
                        TUE_MENU_NAME_4.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        TUE_MENU_SELECT_COUNT_4.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        TUE_MENU_RESET_COUNT_4.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 24){
                    DAY_25.setText(String.valueOf(setDay));
                    if(dataCheck){
                        WED_MENU_NAME_4.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        WED_MENU_SELECT_COUNT_4.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        WED_MENU_RESET_COUNT_4.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 25){
                    DAY_26.setText(String.valueOf(setDay));
                    if(dataCheck){
                        THU_MENU_NAME_4.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        THU_MENU_SELECT_COUNT_4.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        THU_MENU_RESET_COUNT_4.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 26){
                    DAY_27.setText(String.valueOf(setDay));
                    if(dataCheck){
                        FRI_MENU_NAME_4.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        FRI_MENU_SELECT_COUNT_4.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        FRI_MENU_RESET_COUNT_4.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 27){ //주말
                    DAY_28.setText(String.valueOf(setDay));
                } else if ( i == 28){ //주말
                    DAY_29.setText(String.valueOf(setDay));
                } else if ( i == 29){
                    DAY_30.setText(String.valueOf(setDay));
                    if(dataCheck){
                        MON_MENU_NAME_5.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        MON_MENU_SELECT_COUNT_5.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        MON_MENU_RESET_COUNT_5.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 30){
                    DAY_31.setText(String.valueOf(setDay));
                    if(dataCheck){
                        TUE_MENU_NAME_5.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        TUE_MENU_SELECT_COUNT_5.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        TUE_MENU_RESET_COUNT_5.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 31){
                    DAY_32.setText(String.valueOf(setDay));
                    if(dataCheck){
                        WED_MENU_NAME_5.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        WED_MENU_SELECT_COUNT_5.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        WED_MENU_RESET_COUNT_5.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 32){
                    DAY_33.setText(String.valueOf(setDay));
                    if(dataCheck){
                        THU_MENU_NAME_5.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        THU_MENU_SELECT_COUNT_5.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        THU_MENU_RESET_COUNT_5.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 33){
                    DAY_34.setText(String.valueOf(setDay));
                    if(dataCheck){
                        FRI_MENU_NAME_5.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        FRI_MENU_SELECT_COUNT_5.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        FRI_MENU_RESET_COUNT_5.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 34){ //주말
                    DAY_35.setText(String.valueOf(setDay));
                } else if ( i == 35){ //주말
                    DAY_36.setText(String.valueOf(setDay));
                } else if ( i == 36){
                    DAY_37.setText(String.valueOf(setDay));
                    if(dataCheck){
                        MON_MENU_NAME_6.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        MON_MENU_SELECT_COUNT_6.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        MON_MENU_RESET_COUNT_6.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 37){
                    DAY_38.setText(String.valueOf(setDay));
                    if(dataCheck){
                        TUE_MENU_NAME_6.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        TUE_MENU_SELECT_COUNT_6.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        TUE_MENU_RESET_COUNT_6.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 38){
                    DAY_39.setText(String.valueOf(setDay));
                    if(dataCheck){
                        WED_MENU_NAME_6.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        WED_MENU_SELECT_COUNT_6.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        WED_MENU_RESET_COUNT_6.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 39){
                    DAY_40.setText(String.valueOf(setDay));
                    if(dataCheck){
                        THU_MENU_NAME_6.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        THU_MENU_SELECT_COUNT_6.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        THU_MENU_RESET_COUNT_6.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 40){
                    DAY_41.setText(String.valueOf(setDay));
                    if(dataCheck){
                        FRI_MENU_NAME_6.setText((String)seseset.getValue(rowCount, "MENU_NAME"));
                        FRI_MENU_SELECT_COUNT_6.setText(String.valueOf(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT")));
                        FRI_MENU_RESET_COUNT_6.setText(String.valueOf(seseset.getValue(rowCount, "MENU_RESET_COUNT")));
                    }
                } else if ( i == 41){ //주말
                    DAY_42.setText(String.valueOf(setDay));
                }
                //날짜와 DB data를 가져오는 rowCount를 증가시킨다.
                setDay++;
                rowCount++;
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dWMultiRowsObject1 = new com.arisystem.beans.datawizard.DWMultiRowsObject();
        boundaryPanel1 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        SUN_1 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_1 = new javax.swing.JLabel();
        SUN_2 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_8 = new javax.swing.JLabel();
        SUN_3 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_15 = new javax.swing.JLabel();
        SUN_4 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_22 = new javax.swing.JLabel();
        SUN_5 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_29 = new javax.swing.JLabel();
        SUN_6 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_36 = new javax.swing.JLabel();
        MON_1 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_2 = new javax.swing.JLabel();
        MON_MENU_NAME_1 = new javax.swing.JLabel();
        MON_MENU_SELECT_COUNT_1 = new javax.swing.JLabel();
        MON_MENU_RESET_COUNT_1 = new javax.swing.JLabel();
        MON_2 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_9 = new javax.swing.JLabel();
        MON_MENU_NAME_2 = new javax.swing.JLabel();
        MON_MENU_SELECT_COUNT_2 = new javax.swing.JLabel();
        MON_MENU_RESET_COUNT_2 = new javax.swing.JLabel();
        MON_3 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_16 = new javax.swing.JLabel();
        MON_MENU_NAME_3 = new javax.swing.JLabel();
        MON_MENU_SELECT_COUNT_3 = new javax.swing.JLabel();
        MON_MENU_RESET_COUNT_3 = new javax.swing.JLabel();
        MON_4 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_23 = new javax.swing.JLabel();
        MON_MENU_NAME_4 = new javax.swing.JLabel();
        MON_MENU_SELECT_COUNT_4 = new javax.swing.JLabel();
        MON_MENU_RESET_COUNT_4 = new javax.swing.JLabel();
        MON_5 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_30 = new javax.swing.JLabel();
        MON_MENU_NAME_5 = new javax.swing.JLabel();
        MON_MENU_SELECT_COUNT_5 = new javax.swing.JLabel();
        MON_MENU_RESET_COUNT_5 = new javax.swing.JLabel();
        MON_6 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_37 = new javax.swing.JLabel();
        MON_MENU_NAME_6 = new javax.swing.JLabel();
        MON_MENU_SELECT_COUNT_6 = new javax.swing.JLabel();
        MON_MENU_RESET_COUNT_6 = new javax.swing.JLabel();
        TUE_1 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_3 = new javax.swing.JLabel();
        TUE_MENU_NAME_1 = new javax.swing.JLabel();
        TUE_MENU_SELECT_COUNT_1 = new javax.swing.JLabel();
        TUE_MENU_RESET_COUNT_1 = new javax.swing.JLabel();
        TUE_2 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_10 = new javax.swing.JLabel();
        TUE_MENU_NAME_2 = new javax.swing.JLabel();
        TUE_MENU_SELECT_COUNT_2 = new javax.swing.JLabel();
        TUE_MENU_RESET_COUNT_2 = new javax.swing.JLabel();
        TUE_3 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_17 = new javax.swing.JLabel();
        TUE_MENU_NAME_3 = new javax.swing.JLabel();
        TUE_MENU_SELECT_COUNT_3 = new javax.swing.JLabel();
        TUE_MENU_RESET_COUNT_3 = new javax.swing.JLabel();
        TUE_4 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_24 = new javax.swing.JLabel();
        TUE_MENU_NAME_4 = new javax.swing.JLabel();
        TUE_MENU_SELECT_COUNT_4 = new javax.swing.JLabel();
        TUE_MENU_RESET_COUNT_4 = new javax.swing.JLabel();
        TUE_5 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_31 = new javax.swing.JLabel();
        TUE_MENU_NAME_5 = new javax.swing.JLabel();
        TUE_MENU_SELECT_COUNT_5 = new javax.swing.JLabel();
        TUE_MENU_RESET_COUNT_5 = new javax.swing.JLabel();
        TUE_6 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_38 = new javax.swing.JLabel();
        TUE_MENU_NAME_6 = new javax.swing.JLabel();
        TUE_MENU_SELECT_COUNT_6 = new javax.swing.JLabel();
        TUE_MENU_RESET_COUNT_6 = new javax.swing.JLabel();
        WED_1 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_4 = new javax.swing.JLabel();
        WED_MENU_NAME_1 = new javax.swing.JLabel();
        WED_MENU_SELECT_COUNT_1 = new javax.swing.JLabel();
        WED_MENU_RESET_COUNT_1 = new javax.swing.JLabel();
        WED_2 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_11 = new javax.swing.JLabel();
        WED_MENU_NAME_2 = new javax.swing.JLabel();
        WED_MENU_SELECT_COUNT_2 = new javax.swing.JLabel();
        WED_MENU_RESET_COUNT_2 = new javax.swing.JLabel();
        WED_3 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_18 = new javax.swing.JLabel();
        WED_MENU_NAME_3 = new javax.swing.JLabel();
        WED_MENU_SELECT_COUNT_3 = new javax.swing.JLabel();
        WED_MENU_RESET_COUNT_3 = new javax.swing.JLabel();
        WED_4 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_25 = new javax.swing.JLabel();
        WED_MENU_NAME_4 = new javax.swing.JLabel();
        WED_MENU_SELECT_COUNT_4 = new javax.swing.JLabel();
        WED_MENU_RESET_COUNT_4 = new javax.swing.JLabel();
        WED_5 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_32 = new javax.swing.JLabel();
        WED_MENU_NAME_5 = new javax.swing.JLabel();
        WED_MENU_SELECT_COUNT_5 = new javax.swing.JLabel();
        WED_MENU_RESET_COUNT_5 = new javax.swing.JLabel();
        WED_6 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_39 = new javax.swing.JLabel();
        WED_MENU_NAME_6 = new javax.swing.JLabel();
        WED_MENU_SELECT_COUNT_6 = new javax.swing.JLabel();
        WED_MENU_RESET_COUNT_6 = new javax.swing.JLabel();
        THU_1 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_5 = new javax.swing.JLabel();
        THU_MENU_NAME_1 = new javax.swing.JLabel();
        THU_MENU_SELECT_COUNT_1 = new javax.swing.JLabel();
        THU_MENU_RESET_COUNT_1 = new javax.swing.JLabel();
        THU_2 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_12 = new javax.swing.JLabel();
        THU_MENU_NAME_2 = new javax.swing.JLabel();
        THU_MENU_SELECT_COUNT_2 = new javax.swing.JLabel();
        THU_MENU_RESET_COUNT_2 = new javax.swing.JLabel();
        THU_3 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_19 = new javax.swing.JLabel();
        THU_MENU_NAME_3 = new javax.swing.JLabel();
        THU_MENU_SELECT_COUNT_3 = new javax.swing.JLabel();
        THU_MENU_RESET_COUNT_3 = new javax.swing.JLabel();
        THU_4 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_26 = new javax.swing.JLabel();
        THU_MENU_NAME_4 = new javax.swing.JLabel();
        THU_MENU_SELECT_COUNT_4 = new javax.swing.JLabel();
        THU_MENU_RESET_COUNT_4 = new javax.swing.JLabel();
        THU_5 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_33 = new javax.swing.JLabel();
        THU_MENU_NAME_5 = new javax.swing.JLabel();
        THU_MENU_SELECT_COUNT_5 = new javax.swing.JLabel();
        THU_MENU_RESET_COUNT_5 = new javax.swing.JLabel();
        THU_6 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_40 = new javax.swing.JLabel();
        THU_MENU_NAME_6 = new javax.swing.JLabel();
        THU_MENU_SELECT_COUNT_6 = new javax.swing.JLabel();
        THU_MENU_RESET_COUNT_6 = new javax.swing.JLabel();
        FRI_1 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_6 = new javax.swing.JLabel();
        FRI_MENU_NAME_1 = new javax.swing.JLabel();
        FRI_MENU_SELECT_COUNT_1 = new javax.swing.JLabel();
        FRI_MENU_RESET_COUNT_1 = new javax.swing.JLabel();
        FRI_2 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_13 = new javax.swing.JLabel();
        FRI_MENU_NAME_2 = new javax.swing.JLabel();
        FRI_MENU_SELECT_COUNT_2 = new javax.swing.JLabel();
        FRI_MENU_RESET_COUNT_2 = new javax.swing.JLabel();
        FRI_3 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_20 = new javax.swing.JLabel();
        FRI_MENU_NAME_3 = new javax.swing.JLabel();
        FRI_MENU_SELECT_COUNT_3 = new javax.swing.JLabel();
        FRI_MENU_RESET_COUNT_3 = new javax.swing.JLabel();
        FRI_4 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_27 = new javax.swing.JLabel();
        FRI_MENU_NAME_4 = new javax.swing.JLabel();
        FRI_MENU_SELECT_COUNT_4 = new javax.swing.JLabel();
        FRI_MENU_RESET_COUNT_4 = new javax.swing.JLabel();
        FRI_5 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_34 = new javax.swing.JLabel();
        FRI_MENU_NAME_5 = new javax.swing.JLabel();
        FRI_MENU_SELECT_COUNT_5 = new javax.swing.JLabel();
        FRI_MENU_RESET_COUNT_5 = new javax.swing.JLabel();
        FRI_6 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_41 = new javax.swing.JLabel();
        FRI_MENU_NAME_6 = new javax.swing.JLabel();
        FRI_MENU_SELECT_COUNT_6 = new javax.swing.JLabel();
        FRI_MENU_RESET_COUNT_6 = new javax.swing.JLabel();
        SAT_1 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_7 = new javax.swing.JLabel();
        SAT_2 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_14 = new javax.swing.JLabel();
        SAT_3 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_21 = new javax.swing.JLabel();
        SAT_4 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_28 = new javax.swing.JLabel();
        SAT_5 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_35 = new javax.swing.JLabel();
        SAT_6 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        DAY_42 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        dWMultiRowsObject1.setErdObjectLocations(new com.arisystem.beans.datawizard.DWErdObjectLocation[]{new com.arisystem.beans.datawizard.DWErdObjectLocation("TODAYLUNCH_TODAY_SELECT",30,0)});
        dWMultiRowsObject1.setJoinConditions(new com.arisystem.beans.datawizard.DWJoinCondition[] {
            new com.arisystem.beans.datawizard.DWNotJoinCondition(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_TODAY_SELECT","TODAYLUNCH_TODAY_SELECT"))});
    dWMultiRowsObject1.setMainTable(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_TODAY_SELECT","TODAYLUNCH_TODAY_SELECT"));
    dWMultiRowsObject1.setSelectFieldObjects(new com.arisystem.beans.datawizard.DWAliasFieldObject[]{
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_TODAY_SELECT","TODAYLUNCH_TODAY_SELECT"),"MENU_SELECT",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_SELECT") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_TODAY_SELECT","TODAYLUNCH_TODAY_SELECT"),"MENU_NO",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_NO") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_TODAY_SELECT","TODAYLUNCH_TODAY_SELECT"),"MENU_NAME",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_NAME") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_TODAY_SELECT","TODAYLUNCH_TODAY_SELECT"),"MENU_RESET_COUNT",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_RESET_COUNT") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_TODAY_SELECT","TODAYLUNCH_TODAY_SELECT"),"MENU_SELECT_TOTALCOUNT",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_SELECT_TOTALCOUNT") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_TODAY_SELECT","TODAYLUNCH_TODAY_SELECT"),"SELECT_YEAR",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"SELECT_YEAR") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_TODAY_SELECT","TODAYLUNCH_TODAY_SELECT"),"SELECT_MONTH",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"SELECT_MONTH") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_TODAY_SELECT","TODAYLUNCH_TODAY_SELECT"),"SELECT_DAY",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"SELECT_DAY") });

setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

boundaryPanel1.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
    new com.arisystem.beans.boundarypanel.BoundaryCell("T_C_1_1",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),"일",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,new java.awt.Color(255,0,0),null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("T_C_2_1",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),"월",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("T_C_3_1",new com.arisystem.beans.boundarypanel.CellInfo(2,0),new com.arisystem.beans.boundarypanel.CellInfo(2,0),"화",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("T_C_4_1",new com.arisystem.beans.boundarypanel.CellInfo(3,0),new com.arisystem.beans.boundarypanel.CellInfo(3,0),"수",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("T_C_5_1",new com.arisystem.beans.boundarypanel.CellInfo(4,0),new com.arisystem.beans.boundarypanel.CellInfo(4,0),"목",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("T_C_6_1",new com.arisystem.beans.boundarypanel.CellInfo(5,0),new com.arisystem.beans.boundarypanel.CellInfo(5,0),"금",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("T_C_7_1",new com.arisystem.beans.boundarypanel.CellInfo(6,0),new com.arisystem.beans.boundarypanel.CellInfo(6,0),"토",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,new java.awt.Color(0,84,255),null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_1_1",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_1_2",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_1_3",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_1_4",new com.arisystem.beans.boundarypanel.CellInfo(0,4),new com.arisystem.beans.boundarypanel.CellInfo(0,4),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_1_5",new com.arisystem.beans.boundarypanel.CellInfo(0,5),new com.arisystem.beans.boundarypanel.CellInfo(0,5),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_1_6",new com.arisystem.beans.boundarypanel.CellInfo(0,6),new com.arisystem.beans.boundarypanel.CellInfo(0,6),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_2_1",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_2_2",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_2_3",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_2_4",new com.arisystem.beans.boundarypanel.CellInfo(1,4),new com.arisystem.beans.boundarypanel.CellInfo(1,4),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_2_5",new com.arisystem.beans.boundarypanel.CellInfo(1,5),new com.arisystem.beans.boundarypanel.CellInfo(1,5),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_2_6",new com.arisystem.beans.boundarypanel.CellInfo(1,6),new com.arisystem.beans.boundarypanel.CellInfo(1,6),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_3_1",new com.arisystem.beans.boundarypanel.CellInfo(2,1),new com.arisystem.beans.boundarypanel.CellInfo(2,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_3_2",new com.arisystem.beans.boundarypanel.CellInfo(2,2),new com.arisystem.beans.boundarypanel.CellInfo(2,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_3_3",new com.arisystem.beans.boundarypanel.CellInfo(2,3),new com.arisystem.beans.boundarypanel.CellInfo(2,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_3_4",new com.arisystem.beans.boundarypanel.CellInfo(2,4),new com.arisystem.beans.boundarypanel.CellInfo(2,4),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_3_5",new com.arisystem.beans.boundarypanel.CellInfo(2,5),new com.arisystem.beans.boundarypanel.CellInfo(2,5),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_3_6",new com.arisystem.beans.boundarypanel.CellInfo(2,6),new com.arisystem.beans.boundarypanel.CellInfo(2,6),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_4_1",new com.arisystem.beans.boundarypanel.CellInfo(3,1),new com.arisystem.beans.boundarypanel.CellInfo(3,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_4_2",new com.arisystem.beans.boundarypanel.CellInfo(3,2),new com.arisystem.beans.boundarypanel.CellInfo(3,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_4_3",new com.arisystem.beans.boundarypanel.CellInfo(3,3),new com.arisystem.beans.boundarypanel.CellInfo(3,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_4_4",new com.arisystem.beans.boundarypanel.CellInfo(3,4),new com.arisystem.beans.boundarypanel.CellInfo(3,4),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_4_5",new com.arisystem.beans.boundarypanel.CellInfo(3,5),new com.arisystem.beans.boundarypanel.CellInfo(3,5),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_4_6",new com.arisystem.beans.boundarypanel.CellInfo(3,6),new com.arisystem.beans.boundarypanel.CellInfo(3,6),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_5_1",new com.arisystem.beans.boundarypanel.CellInfo(4,1),new com.arisystem.beans.boundarypanel.CellInfo(4,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_5_2",new com.arisystem.beans.boundarypanel.CellInfo(4,2),new com.arisystem.beans.boundarypanel.CellInfo(4,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_5_3",new com.arisystem.beans.boundarypanel.CellInfo(4,3),new com.arisystem.beans.boundarypanel.CellInfo(4,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_5_4",new com.arisystem.beans.boundarypanel.CellInfo(4,4),new com.arisystem.beans.boundarypanel.CellInfo(4,4),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_5_5",new com.arisystem.beans.boundarypanel.CellInfo(4,5),new com.arisystem.beans.boundarypanel.CellInfo(4,5),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_5_6",new com.arisystem.beans.boundarypanel.CellInfo(4,6),new com.arisystem.beans.boundarypanel.CellInfo(4,6),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_6_1",new com.arisystem.beans.boundarypanel.CellInfo(5,1),new com.arisystem.beans.boundarypanel.CellInfo(5,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_6_2",new com.arisystem.beans.boundarypanel.CellInfo(5,2),new com.arisystem.beans.boundarypanel.CellInfo(5,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_6_3",new com.arisystem.beans.boundarypanel.CellInfo(5,3),new com.arisystem.beans.boundarypanel.CellInfo(5,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_6_4",new com.arisystem.beans.boundarypanel.CellInfo(5,4),new com.arisystem.beans.boundarypanel.CellInfo(5,4),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_6_5",new com.arisystem.beans.boundarypanel.CellInfo(5,5),new com.arisystem.beans.boundarypanel.CellInfo(5,5),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_6_6",new com.arisystem.beans.boundarypanel.CellInfo(5,6),new com.arisystem.beans.boundarypanel.CellInfo(5,6),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_7_1",new com.arisystem.beans.boundarypanel.CellInfo(6,1),new com.arisystem.beans.boundarypanel.CellInfo(6,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_7_2",new com.arisystem.beans.boundarypanel.CellInfo(6,2),new com.arisystem.beans.boundarypanel.CellInfo(6,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_7_3",new com.arisystem.beans.boundarypanel.CellInfo(6,3),new com.arisystem.beans.boundarypanel.CellInfo(6,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_7_4",new com.arisystem.beans.boundarypanel.CellInfo(6,4),new com.arisystem.beans.boundarypanel.CellInfo(6,4),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_7_5",new com.arisystem.beans.boundarypanel.CellInfo(6,5),new com.arisystem.beans.boundarypanel.CellInfo(6,5),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_7_6",new com.arisystem.beans.boundarypanel.CellInfo(6,6),new com.arisystem.beans.boundarypanel.CellInfo(6,6),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    boundaryPanel1.setColumnCount(7);
    boundaryPanel1.setColumnWidths(new int[] {90, 125, 125, 125, 125, 125, 90});
    boundaryPanel1.setRowCount(7);
    boundaryPanel1.setRowHeights(new int[] {25, 105, 105, 105, 105, 105, 105});

    SUN_1.setBoundaryLineColor(new java.awt.Color(234, 234, 234));
    SUN_1.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("WEEKEND",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_16",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    }));
    SUN_1.setCellBackColor(new java.awt.Color(234, 234, 234));
    SUN_1.setColumnCount(2);
    SUN_1.setColumnWidths(new int[] {35, 55});
    SUN_1.setRowHeights(new int[] {27, 78});
    SUN_1.add(DAY_1);
    DAY_1.setBounds(20, 20, 0, 0);

    boundaryPanel1.add(SUN_1);
    SUN_1.setBounds(50, 150, 91, 106);

    SUN_2.setBoundaryLineColor(new java.awt.Color(234, 234, 234));
    SUN_2.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("WEEKEND",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_17",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    }));
    SUN_2.setCellBackColor(new java.awt.Color(234, 234, 234));
    SUN_2.setColumnCount(2);
    SUN_2.setColumnWidths(new int[] {35, 55});
    SUN_2.setRowHeights(new int[] {27, 78});
    SUN_2.add(DAY_8);
    DAY_8.setBounds(20, 20, 0, 0);

    boundaryPanel1.add(SUN_2);
    SUN_2.setBounds(10, 140, 89, 104);

    SUN_3.setBoundaryLineColor(new java.awt.Color(234, 234, 234));
    SUN_3.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("WEEKEND",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    }));
    SUN_3.setCellBackColor(new java.awt.Color(234, 234, 234));
    SUN_3.setColumnCount(2);
    SUN_3.setColumnWidths(new int[] {35, 55});
    SUN_3.setRowHeights(new int[] {27, 78});
    SUN_3.add(DAY_15);
    DAY_15.setBounds(20, 20, 0, 0);

    boundaryPanel1.add(SUN_3);
    SUN_3.setBounds(50, 150, 91, 106);

    SUN_4.setBoundaryLineColor(new java.awt.Color(234, 234, 234));
    SUN_4.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("WEEKEND",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_19",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    }));
    SUN_4.setCellBackColor(new java.awt.Color(234, 234, 234));
    SUN_4.setColumnCount(2);
    SUN_4.setColumnWidths(new int[] {35, 55});
    SUN_4.setRowHeights(new int[] {27, 78});
    SUN_4.add(DAY_22);
    DAY_22.setBounds(20, 20, 0, 0);

    boundaryPanel1.add(SUN_4);
    SUN_4.setBounds(0, 350, 89, 104);

    SUN_5.setBoundaryLineColor(new java.awt.Color(234, 234, 234));
    SUN_5.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("WEEKEND",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_20",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    }));
    SUN_5.setCellBackColor(new java.awt.Color(234, 234, 234));
    SUN_5.setColumnCount(2);
    SUN_5.setColumnWidths(new int[] {35, 55});
    SUN_5.setRowHeights(new int[] {27, 78});
    SUN_5.add(DAY_29);
    DAY_29.setBounds(20, 20, 0, 0);

    boundaryPanel1.add(SUN_5);
    SUN_5.setBounds(50, 150, 91, 106);

    SUN_6.setBoundaryLineColor(new java.awt.Color(234, 234, 234));
    SUN_6.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("WEEKEND",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    }));
    SUN_6.setCellBackColor(new java.awt.Color(234, 234, 234));
    SUN_6.setColumnCount(2);
    SUN_6.setColumnWidths(new int[] {35, 55});
    SUN_6.setRowHeights(new int[] {27, 78});
    SUN_6.add(DAY_36);
    DAY_36.setBounds(20, 20, 0, 0);

    boundaryPanel1.add(SUN_6);
    SUN_6.setBounds(50, 150, 91, 106);

    MON_1.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    MON_1.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    MON_1.setColumnCount(2);
    MON_1.setColumnWidths(new int[] {40, 85});
    MON_1.setRowCount(4);
    MON_1.setRowHeights(new int[] {27, 26, 26, 26});
    MON_1.add(DAY_2);
    DAY_2.setBounds(10, 10, 0, 0);
    MON_1.add(MON_MENU_NAME_1);
    MON_MENU_NAME_1.setBounds(70, 40, 0, 0);
    MON_1.add(MON_MENU_SELECT_COUNT_1);
    MON_MENU_SELECT_COUNT_1.setBounds(50, 60, 0, 0);
    MON_1.add(MON_MENU_RESET_COUNT_1);
    MON_MENU_RESET_COUNT_1.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(MON_1);
    MON_1.setBounds(30, 30, 99, 104);

    MON_2.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    MON_2.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    MON_2.setColumnCount(2);
    MON_2.setColumnWidths(new int[] {40, 85});
    MON_2.setRowCount(4);
    MON_2.setRowHeights(new int[] {27, 26, 26, 26});
    MON_2.add(DAY_9);
    DAY_9.setBounds(10, 10, 0, 0);
    MON_2.add(MON_MENU_NAME_2);
    MON_MENU_NAME_2.setBounds(70, 40, 0, 0);
    MON_2.add(MON_MENU_SELECT_COUNT_2);
    MON_MENU_SELECT_COUNT_2.setBounds(50, 60, 0, 0);
    MON_2.add(MON_MENU_RESET_COUNT_2);
    MON_MENU_RESET_COUNT_2.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(MON_2);
    MON_2.setBounds(30, 30, 99, 104);

    MON_3.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    MON_3.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    MON_3.setColumnCount(2);
    MON_3.setColumnWidths(new int[] {40, 85});
    MON_3.setRowCount(4);
    MON_3.setRowHeights(new int[] {27, 26, 26, 26});
    MON_3.add(DAY_16);
    DAY_16.setBounds(10, 10, 0, 0);
    MON_3.add(MON_MENU_NAME_3);
    MON_MENU_NAME_3.setBounds(70, 40, 0, 0);
    MON_3.add(MON_MENU_SELECT_COUNT_3);
    MON_MENU_SELECT_COUNT_3.setBounds(50, 60, 0, 0);
    MON_3.add(MON_MENU_RESET_COUNT_3);
    MON_MENU_RESET_COUNT_3.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(MON_3);
    MON_3.setBounds(30, 30, 99, 104);

    MON_4.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    MON_4.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    MON_4.setColumnCount(2);
    MON_4.setColumnWidths(new int[] {40, 85});
    MON_4.setRowCount(4);
    MON_4.setRowHeights(new int[] {27, 26, 26, 26});
    MON_4.add(DAY_23);
    DAY_23.setBounds(10, 10, 0, 0);
    MON_4.add(MON_MENU_NAME_4);
    MON_MENU_NAME_4.setBounds(70, 40, 0, 0);
    MON_4.add(MON_MENU_SELECT_COUNT_4);
    MON_MENU_SELECT_COUNT_4.setBounds(50, 60, 0, 0);
    MON_4.add(MON_MENU_RESET_COUNT_4);
    MON_MENU_RESET_COUNT_4.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(MON_4);
    MON_4.setBounds(30, 30, 99, 104);

    MON_5.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    MON_5.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    MON_5.setColumnCount(2);
    MON_5.setColumnWidths(new int[] {40, 85});
    MON_5.setRowCount(4);
    MON_5.setRowHeights(new int[] {27, 26, 26, 26});
    MON_5.add(DAY_30);
    DAY_30.setBounds(10, 10, 0, 0);
    MON_5.add(MON_MENU_NAME_5);
    MON_MENU_NAME_5.setBounds(70, 40, 0, 0);
    MON_5.add(MON_MENU_SELECT_COUNT_5);
    MON_MENU_SELECT_COUNT_5.setBounds(50, 60, 0, 0);
    MON_5.add(MON_MENU_RESET_COUNT_5);
    MON_MENU_RESET_COUNT_5.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(MON_5);
    MON_5.setBounds(30, 30, 99, 104);

    MON_6.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    MON_6.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    MON_6.setColumnCount(2);
    MON_6.setColumnWidths(new int[] {40, 85});
    MON_6.setRowCount(4);
    MON_6.setRowHeights(new int[] {27, 26, 26, 26});
    MON_6.add(DAY_37);
    DAY_37.setBounds(10, 10, 0, 0);
    MON_6.add(MON_MENU_NAME_6);
    MON_MENU_NAME_6.setBounds(70, 40, 0, 0);
    MON_6.add(MON_MENU_SELECT_COUNT_6);
    MON_MENU_SELECT_COUNT_6.setBounds(50, 60, 0, 0);
    MON_6.add(MON_MENU_RESET_COUNT_6);
    MON_MENU_RESET_COUNT_6.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(MON_6);
    MON_6.setBounds(30, 30, 99, 104);

    TUE_1.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    TUE_1.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    TUE_1.setColumnCount(2);
    TUE_1.setColumnWidths(new int[] {40, 85});
    TUE_1.setRowCount(4);
    TUE_1.setRowHeights(new int[] {27, 26, 26, 26});
    TUE_1.add(DAY_3);
    DAY_3.setBounds(10, 10, 0, 0);
    TUE_1.add(TUE_MENU_NAME_1);
    TUE_MENU_NAME_1.setBounds(70, 40, 0, 0);
    TUE_1.add(TUE_MENU_SELECT_COUNT_1);
    TUE_MENU_SELECT_COUNT_1.setBounds(50, 60, 0, 0);
    TUE_1.add(TUE_MENU_RESET_COUNT_1);
    TUE_MENU_RESET_COUNT_1.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(TUE_1);
    TUE_1.setBounds(30, 30, 99, 104);

    TUE_2.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    TUE_2.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    TUE_2.setColumnCount(2);
    TUE_2.setColumnWidths(new int[] {40, 85});
    TUE_2.setRowCount(4);
    TUE_2.setRowHeights(new int[] {27, 26, 26, 26});
    TUE_2.add(DAY_10);
    DAY_10.setBounds(10, 10, 0, 0);
    TUE_2.add(TUE_MENU_NAME_2);
    TUE_MENU_NAME_2.setBounds(70, 40, 0, 0);
    TUE_2.add(TUE_MENU_SELECT_COUNT_2);
    TUE_MENU_SELECT_COUNT_2.setBounds(50, 60, 0, 0);
    TUE_2.add(TUE_MENU_RESET_COUNT_2);
    TUE_MENU_RESET_COUNT_2.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(TUE_2);
    TUE_2.setBounds(30, 30, 99, 104);

    TUE_3.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    TUE_3.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    TUE_3.setColumnCount(2);
    TUE_3.setColumnWidths(new int[] {40, 85});
    TUE_3.setRowCount(4);
    TUE_3.setRowHeights(new int[] {27, 26, 26, 26});
    TUE_3.add(DAY_17);
    DAY_17.setBounds(10, 10, 0, 0);
    TUE_3.add(TUE_MENU_NAME_3);
    TUE_MENU_NAME_3.setBounds(70, 40, 0, 0);
    TUE_3.add(TUE_MENU_SELECT_COUNT_3);
    TUE_MENU_SELECT_COUNT_3.setBounds(50, 60, 0, 0);
    TUE_3.add(TUE_MENU_RESET_COUNT_3);
    TUE_MENU_RESET_COUNT_3.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(TUE_3);
    TUE_3.setBounds(30, 30, 99, 104);

    TUE_4.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    TUE_4.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    TUE_4.setColumnCount(2);
    TUE_4.setColumnWidths(new int[] {40, 85});
    TUE_4.setRowCount(4);
    TUE_4.setRowHeights(new int[] {27, 26, 26, 26});
    TUE_4.add(DAY_24);
    DAY_24.setBounds(10, 10, 0, 0);
    TUE_4.add(TUE_MENU_NAME_4);
    TUE_MENU_NAME_4.setBounds(70, 40, 0, 0);
    TUE_4.add(TUE_MENU_SELECT_COUNT_4);
    TUE_MENU_SELECT_COUNT_4.setBounds(50, 60, 0, 0);
    TUE_4.add(TUE_MENU_RESET_COUNT_4);
    TUE_MENU_RESET_COUNT_4.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(TUE_4);
    TUE_4.setBounds(30, 30, 99, 104);

    TUE_5.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    TUE_5.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    TUE_5.setColumnCount(2);
    TUE_5.setColumnWidths(new int[] {40, 85});
    TUE_5.setRowCount(4);
    TUE_5.setRowHeights(new int[] {27, 26, 26, 26});
    TUE_5.add(DAY_31);
    DAY_31.setBounds(10, 10, 0, 0);
    TUE_5.add(TUE_MENU_NAME_5);
    TUE_MENU_NAME_5.setBounds(70, 40, 0, 0);
    TUE_5.add(TUE_MENU_SELECT_COUNT_5);
    TUE_MENU_SELECT_COUNT_5.setBounds(50, 60, 0, 0);
    TUE_5.add(TUE_MENU_RESET_COUNT_5);
    TUE_MENU_RESET_COUNT_5.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(TUE_5);
    TUE_5.setBounds(30, 30, 99, 104);

    TUE_6.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    TUE_6.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    TUE_6.setColumnCount(2);
    TUE_6.setColumnWidths(new int[] {40, 85});
    TUE_6.setRowCount(4);
    TUE_6.setRowHeights(new int[] {27, 26, 26, 26});
    TUE_6.add(DAY_38);
    DAY_38.setBounds(10, 10, 0, 0);
    TUE_6.add(TUE_MENU_NAME_6);
    TUE_MENU_NAME_6.setBounds(70, 40, 0, 0);
    TUE_6.add(TUE_MENU_SELECT_COUNT_6);
    TUE_MENU_SELECT_COUNT_6.setBounds(50, 60, 0, 0);
    TUE_6.add(TUE_MENU_RESET_COUNT_6);
    TUE_MENU_RESET_COUNT_6.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(TUE_6);
    TUE_6.setBounds(30, 30, 99, 104);

    WED_1.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    WED_1.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    WED_1.setColumnCount(2);
    WED_1.setColumnWidths(new int[] {40, 85});
    WED_1.setRowCount(4);
    WED_1.setRowHeights(new int[] {27, 26, 26, 26});
    WED_1.add(DAY_4);
    DAY_4.setBounds(10, 10, 0, 0);
    WED_1.add(WED_MENU_NAME_1);
    WED_MENU_NAME_1.setBounds(70, 40, 0, 0);
    WED_1.add(WED_MENU_SELECT_COUNT_1);
    WED_MENU_SELECT_COUNT_1.setBounds(41, 54, 0, 30);
    WED_1.add(WED_MENU_RESET_COUNT_1);
    WED_MENU_RESET_COUNT_1.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(WED_1);
    WED_1.setBounds(30, 30, 99, 104);

    WED_2.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    WED_2.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    WED_2.setColumnCount(2);
    WED_2.setColumnWidths(new int[] {40, 85});
    WED_2.setRowCount(4);
    WED_2.setRowHeights(new int[] {27, 26, 26, 26});
    WED_2.add(DAY_11);
    DAY_11.setBounds(10, 10, 0, 0);
    WED_2.add(WED_MENU_NAME_2);
    WED_MENU_NAME_2.setBounds(70, 40, 0, 0);
    WED_2.add(WED_MENU_SELECT_COUNT_2);
    WED_MENU_SELECT_COUNT_2.setBounds(50, 60, 0, 0);
    WED_2.add(WED_MENU_RESET_COUNT_2);
    WED_MENU_RESET_COUNT_2.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(WED_2);
    WED_2.setBounds(30, 30, 99, 104);

    WED_3.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    WED_3.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    WED_3.setColumnCount(2);
    WED_3.setColumnWidths(new int[] {40, 85});
    WED_3.setRowCount(4);
    WED_3.setRowHeights(new int[] {27, 26, 26, 26});
    WED_3.add(DAY_18);
    DAY_18.setBounds(10, 10, 0, 0);
    WED_3.add(WED_MENU_NAME_3);
    WED_MENU_NAME_3.setBounds(70, 40, 0, 0);
    WED_3.add(WED_MENU_SELECT_COUNT_3);
    WED_MENU_SELECT_COUNT_3.setBounds(50, 60, 0, 0);
    WED_3.add(WED_MENU_RESET_COUNT_3);
    WED_MENU_RESET_COUNT_3.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(WED_3);
    WED_3.setBounds(30, 30, 99, 104);

    WED_4.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    WED_4.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    WED_4.setColumnCount(2);
    WED_4.setColumnWidths(new int[] {40, 85});
    WED_4.setRowCount(4);
    WED_4.setRowHeights(new int[] {27, 26, 26, 26});
    WED_4.add(DAY_25);
    DAY_25.setBounds(10, 10, 0, 0);
    WED_4.add(WED_MENU_NAME_4);
    WED_MENU_NAME_4.setBounds(70, 40, 0, 0);
    WED_4.add(WED_MENU_SELECT_COUNT_4);
    WED_MENU_SELECT_COUNT_4.setBounds(50, 60, 0, 0);
    WED_4.add(WED_MENU_RESET_COUNT_4);
    WED_MENU_RESET_COUNT_4.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(WED_4);
    WED_4.setBounds(30, 30, 99, 104);

    WED_5.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    WED_5.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    WED_5.setColumnCount(2);
    WED_5.setColumnWidths(new int[] {40, 85});
    WED_5.setRowCount(4);
    WED_5.setRowHeights(new int[] {27, 26, 26, 26});
    WED_5.add(DAY_32);
    DAY_32.setBounds(10, 10, 0, 0);
    WED_5.add(WED_MENU_NAME_5);
    WED_MENU_NAME_5.setBounds(70, 40, 0, 0);
    WED_5.add(WED_MENU_SELECT_COUNT_5);
    WED_MENU_SELECT_COUNT_5.setBounds(50, 60, 0, 0);
    WED_5.add(WED_MENU_RESET_COUNT_5);
    WED_MENU_RESET_COUNT_5.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(WED_5);
    WED_5.setBounds(30, 30, 99, 104);

    WED_6.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    WED_6.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    WED_6.setColumnCount(2);
    WED_6.setColumnWidths(new int[] {40, 85});
    WED_6.setRowCount(4);
    WED_6.setRowHeights(new int[] {27, 26, 26, 26});
    WED_6.add(DAY_39);
    DAY_39.setBounds(10, 10, 0, 0);
    WED_6.add(WED_MENU_NAME_6);
    WED_MENU_NAME_6.setBounds(70, 40, 0, 0);
    WED_6.add(WED_MENU_SELECT_COUNT_6);
    WED_MENU_SELECT_COUNT_6.setBounds(50, 60, 0, 0);
    WED_6.add(WED_MENU_RESET_COUNT_6);
    WED_MENU_RESET_COUNT_6.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(WED_6);
    WED_6.setBounds(30, 30, 99, 104);

    THU_1.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    THU_1.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    THU_1.setColumnCount(2);
    THU_1.setColumnWidths(new int[] {40, 85});
    THU_1.setRowCount(4);
    THU_1.setRowHeights(new int[] {27, 26, 26, 26});
    THU_1.add(DAY_5);
    DAY_5.setBounds(10, 10, 0, 0);
    THU_1.add(THU_MENU_NAME_1);
    THU_MENU_NAME_1.setBounds(70, 40, 0, 0);
    THU_1.add(THU_MENU_SELECT_COUNT_1);
    THU_MENU_SELECT_COUNT_1.setBounds(50, 60, 0, 0);
    THU_1.add(THU_MENU_RESET_COUNT_1);
    THU_MENU_RESET_COUNT_1.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(THU_1);
    THU_1.setBounds(30, 30, 99, 104);

    THU_2.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    THU_2.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    THU_2.setColumnCount(2);
    THU_2.setColumnWidths(new int[] {40, 85});
    THU_2.setRowCount(4);
    THU_2.setRowHeights(new int[] {27, 26, 26, 26});
    THU_2.add(DAY_12);
    DAY_12.setBounds(10, 10, 0, 0);
    THU_2.add(THU_MENU_NAME_2);
    THU_MENU_NAME_2.setBounds(70, 40, 0, 0);
    THU_2.add(THU_MENU_SELECT_COUNT_2);
    THU_MENU_SELECT_COUNT_2.setBounds(50, 60, 0, 0);
    THU_2.add(THU_MENU_RESET_COUNT_2);
    THU_MENU_RESET_COUNT_2.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(THU_2);
    THU_2.setBounds(30, 30, 99, 104);

    THU_3.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    THU_3.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    THU_3.setColumnCount(2);
    THU_3.setColumnWidths(new int[] {40, 85});
    THU_3.setRowCount(4);
    THU_3.setRowHeights(new int[] {27, 26, 26, 26});
    THU_3.add(DAY_19);
    DAY_19.setBounds(10, 10, 0, 0);
    THU_3.add(THU_MENU_NAME_3);
    THU_MENU_NAME_3.setBounds(70, 40, 0, 0);
    THU_3.add(THU_MENU_SELECT_COUNT_3);
    THU_MENU_SELECT_COUNT_3.setBounds(50, 60, 0, 0);
    THU_3.add(THU_MENU_RESET_COUNT_3);
    THU_MENU_RESET_COUNT_3.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(THU_3);
    THU_3.setBounds(30, 30, 99, 104);

    THU_4.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    THU_4.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    THU_4.setColumnCount(2);
    THU_4.setColumnWidths(new int[] {40, 85});
    THU_4.setRowCount(4);
    THU_4.setRowHeights(new int[] {27, 26, 26, 26});
    THU_4.add(DAY_26);
    DAY_26.setBounds(10, 10, 0, 0);
    THU_4.add(THU_MENU_NAME_4);
    THU_MENU_NAME_4.setBounds(70, 40, 0, 0);
    THU_4.add(THU_MENU_SELECT_COUNT_4);
    THU_MENU_SELECT_COUNT_4.setBounds(50, 60, 0, 0);
    THU_4.add(THU_MENU_RESET_COUNT_4);
    THU_MENU_RESET_COUNT_4.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(THU_4);
    THU_4.setBounds(30, 30, 99, 104);

    THU_5.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    THU_5.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    THU_5.setColumnCount(2);
    THU_5.setColumnWidths(new int[] {40, 85});
    THU_5.setRowCount(4);
    THU_5.setRowHeights(new int[] {27, 26, 26, 26});
    THU_5.add(DAY_33);
    DAY_33.setBounds(10, 10, 0, 0);
    THU_5.add(THU_MENU_NAME_5);
    THU_MENU_NAME_5.setBounds(70, 40, 0, 0);
    THU_5.add(THU_MENU_SELECT_COUNT_5);
    THU_MENU_SELECT_COUNT_5.setBounds(50, 60, 0, 0);
    THU_5.add(THU_MENU_RESET_COUNT_5);
    THU_MENU_RESET_COUNT_5.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(THU_5);
    THU_5.setBounds(30, 30, 99, 104);

    THU_6.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    THU_6.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    THU_6.setColumnCount(2);
    THU_6.setColumnWidths(new int[] {40, 85});
    THU_6.setRowCount(4);
    THU_6.setRowHeights(new int[] {27, 26, 26, 26});
    THU_6.add(DAY_40);
    DAY_40.setBounds(10, 10, 0, 0);
    THU_6.add(THU_MENU_NAME_6);
    THU_MENU_NAME_6.setBounds(70, 40, 0, 0);
    THU_6.add(THU_MENU_SELECT_COUNT_6);
    THU_MENU_SELECT_COUNT_6.setBounds(50, 60, 0, 0);
    THU_6.add(THU_MENU_RESET_COUNT_6);
    THU_MENU_RESET_COUNT_6.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(THU_6);
    THU_6.setBounds(30, 30, 99, 104);

    FRI_1.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    FRI_1.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    FRI_1.setBundleName("");
    FRI_1.setColumnCount(2);
    FRI_1.setColumnWidths(new int[] {40, 85});
    FRI_1.setRowCount(4);
    FRI_1.setRowHeights(new int[] {27, 26, 26, 26});
    FRI_1.add(DAY_6);
    DAY_6.setBounds(10, 10, 0, 0);
    FRI_1.add(FRI_MENU_NAME_1);
    FRI_MENU_NAME_1.setBounds(70, 40, 0, 0);
    FRI_1.add(FRI_MENU_SELECT_COUNT_1);
    FRI_MENU_SELECT_COUNT_1.setBounds(50, 60, 0, 0);
    FRI_1.add(FRI_MENU_RESET_COUNT_1);
    FRI_MENU_RESET_COUNT_1.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(FRI_1);
    FRI_1.setBounds(30, 30, 99, 104);

    FRI_2.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    FRI_2.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    FRI_2.setColumnCount(2);
    FRI_2.setColumnWidths(new int[] {40, 85});
    FRI_2.setRowCount(4);
    FRI_2.setRowHeights(new int[] {27, 26, 26, 26});
    FRI_2.add(DAY_13);
    DAY_13.setBounds(10, 10, 0, 0);
    FRI_2.add(FRI_MENU_NAME_2);
    FRI_MENU_NAME_2.setBounds(70, 40, 0, 0);
    FRI_2.add(FRI_MENU_SELECT_COUNT_2);
    FRI_MENU_SELECT_COUNT_2.setBounds(50, 60, 0, 0);
    FRI_2.add(FRI_MENU_RESET_COUNT_2);
    FRI_MENU_RESET_COUNT_2.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(FRI_2);
    FRI_2.setBounds(30, 30, 99, 104);

    FRI_3.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    FRI_3.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    FRI_3.setColumnCount(2);
    FRI_3.setColumnWidths(new int[] {40, 85});
    FRI_3.setRowCount(4);
    FRI_3.setRowHeights(new int[] {27, 26, 26, 26});
    FRI_3.add(DAY_20);
    DAY_20.setBounds(10, 10, 0, 0);
    FRI_3.add(FRI_MENU_NAME_3);
    FRI_MENU_NAME_3.setBounds(70, 40, 0, 0);
    FRI_3.add(FRI_MENU_SELECT_COUNT_3);
    FRI_MENU_SELECT_COUNT_3.setBounds(50, 60, 0, 0);
    FRI_3.add(FRI_MENU_RESET_COUNT_3);
    FRI_MENU_RESET_COUNT_3.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(FRI_3);
    FRI_3.setBounds(30, 30, 99, 104);

    FRI_4.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    FRI_4.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    FRI_4.setColumnCount(2);
    FRI_4.setColumnWidths(new int[] {40, 85});
    FRI_4.setRowCount(4);
    FRI_4.setRowHeights(new int[] {27, 26, 26, 26});
    FRI_4.add(DAY_27);
    DAY_27.setBounds(10, 10, 0, 0);
    FRI_4.add(FRI_MENU_NAME_4);
    FRI_MENU_NAME_4.setBounds(70, 40, 0, 0);
    FRI_4.add(FRI_MENU_SELECT_COUNT_4);
    FRI_MENU_SELECT_COUNT_4.setBounds(50, 60, 0, 0);
    FRI_4.add(FRI_MENU_RESET_COUNT_4);
    FRI_MENU_RESET_COUNT_4.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(FRI_4);
    FRI_4.setBounds(30, 30, 99, 104);

    FRI_5.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    FRI_5.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    FRI_5.setColumnCount(2);
    FRI_5.setColumnWidths(new int[] {40, 85});
    FRI_5.setRowCount(4);
    FRI_5.setRowHeights(new int[] {27, 26, 26, 26});
    FRI_5.add(DAY_34);
    DAY_34.setBounds(10, 10, 0, 0);
    FRI_5.add(FRI_MENU_NAME_5);
    FRI_MENU_NAME_5.setBounds(70, 40, 0, 0);
    FRI_5.add(FRI_MENU_SELECT_COUNT_5);
    FRI_MENU_SELECT_COUNT_5.setBounds(50, 60, 0, 0);
    FRI_5.add(FRI_MENU_RESET_COUNT_5);
    FRI_MENU_RESET_COUNT_5.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(FRI_5);
    FRI_5.setBounds(30, 30, 99, 104);

    FRI_6.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    FRI_6.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"시도",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"초기화",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    FRI_6.setColumnCount(2);
    FRI_6.setColumnWidths(new int[] {40, 85});
    FRI_6.setRowCount(4);
    FRI_6.setRowHeights(new int[] {27, 26, 26, 26});
    FRI_6.add(DAY_41);
    DAY_41.setBounds(10, 10, 0, 0);
    FRI_6.add(FRI_MENU_NAME_6);
    FRI_MENU_NAME_6.setBounds(70, 40, 0, 0);
    FRI_6.add(FRI_MENU_SELECT_COUNT_6);
    FRI_MENU_SELECT_COUNT_6.setBounds(50, 60, 0, 0);
    FRI_6.add(FRI_MENU_RESET_COUNT_6);
    FRI_MENU_RESET_COUNT_6.setBounds(60, 80, 0, 0);

    boundaryPanel1.add(FRI_6);
    FRI_6.setBounds(30, 30, 99, 104);

    SAT_1.setBoundaryLineColor(new java.awt.Color(234, 234, 234));
    SAT_1.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("WEEKEND",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_22",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    }));
    SAT_1.setCellBackColor(new java.awt.Color(234, 234, 234));
    SAT_1.setColumnCount(2);
    SAT_1.setColumnWidths(new int[] {35, 55});
    SAT_1.setRowHeights(new int[] {27, 78});
    SAT_1.add(DAY_7);
    DAY_7.setBounds(20, 20, 0, 0);

    boundaryPanel1.add(SAT_1);
    SAT_1.setBounds(50, 150, 91, 106);

    SAT_2.setBoundaryLineColor(new java.awt.Color(234, 234, 234));
    SAT_2.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("WEEKEND",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_23",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    }));
    SAT_2.setCellBackColor(new java.awt.Color(234, 234, 234));
    SAT_2.setColumnCount(2);
    SAT_2.setColumnWidths(new int[] {35, 55});
    SAT_2.setRowHeights(new int[] {27, 78});
    SAT_2.add(DAY_14);
    DAY_14.setBounds(20, 20, 0, 0);

    boundaryPanel1.add(SAT_2);
    SAT_2.setBounds(50, 150, 91, 106);

    SAT_3.setBoundaryLineColor(new java.awt.Color(234, 234, 234));
    SAT_3.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("WEEKEND",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_24",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    }));
    SAT_3.setCellBackColor(new java.awt.Color(234, 234, 234));
    SAT_3.setColumnCount(2);
    SAT_3.setColumnWidths(new int[] {35, 55});
    SAT_3.setRowHeights(new int[] {27, 78});
    SAT_3.add(DAY_21);
    DAY_21.setBounds(20, 20, 0, 0);

    boundaryPanel1.add(SAT_3);
    SAT_3.setBounds(50, 150, 91, 106);

    SAT_4.setBoundaryLineColor(new java.awt.Color(234, 234, 234));
    SAT_4.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("WEEKEND",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_25",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    }));
    SAT_4.setCellBackColor(new java.awt.Color(234, 234, 234));
    SAT_4.setColumnCount(2);
    SAT_4.setColumnWidths(new int[] {35, 55});
    SAT_4.setRowHeights(new int[] {27, 78});
    SAT_4.add(DAY_28);
    DAY_28.setBounds(20, 20, 0, 0);

    boundaryPanel1.add(SAT_4);
    SAT_4.setBounds(50, 150, 91, 106);

    SAT_5.setBoundaryLineColor(new java.awt.Color(234, 234, 234));
    SAT_5.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("WEEKEND",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_26",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    }));
    SAT_5.setCellBackColor(new java.awt.Color(234, 234, 234));
    SAT_5.setColumnCount(2);
    SAT_5.setColumnWidths(new int[] {35, 55});
    SAT_5.setRowHeights(new int[] {27, 78});
    SAT_5.add(DAY_35);
    DAY_35.setBounds(20, 20, 0, 0);

    boundaryPanel1.add(SAT_5);
    SAT_5.setBounds(50, 150, 91, 106);

    SAT_6.setBoundaryLineColor(new java.awt.Color(234, 234, 234));
    SAT_6.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("WEEKEND",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_27",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    }));
    SAT_6.setCellBackColor(new java.awt.Color(234, 234, 234));
    SAT_6.setColumnCount(2);
    SAT_6.setColumnWidths(new int[] {35, 55});
    SAT_6.setRowHeights(new int[] {27, 78});
    SAT_6.add(DAY_42);
    DAY_42.setBounds(20, 20, 0, 0);

    boundaryPanel1.add(SAT_6);
    SAT_6.setBounds(50, 150, 91, 106);

    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "월" }));

    jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "년도" }));

    jButton1.setText("검색");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(boundaryPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(29, 29, 29)
            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(29, 29, 29)
            .addComponent(jButton1)
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(28, 28, 28)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton1))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
            .addComponent(boundaryPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CALENDAR().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DAY_1;
    private javax.swing.JLabel DAY_10;
    private javax.swing.JLabel DAY_11;
    private javax.swing.JLabel DAY_12;
    private javax.swing.JLabel DAY_13;
    private javax.swing.JLabel DAY_14;
    private javax.swing.JLabel DAY_15;
    private javax.swing.JLabel DAY_16;
    private javax.swing.JLabel DAY_17;
    private javax.swing.JLabel DAY_18;
    private javax.swing.JLabel DAY_19;
    private javax.swing.JLabel DAY_2;
    private javax.swing.JLabel DAY_20;
    private javax.swing.JLabel DAY_21;
    private javax.swing.JLabel DAY_22;
    private javax.swing.JLabel DAY_23;
    private javax.swing.JLabel DAY_24;
    private javax.swing.JLabel DAY_25;
    private javax.swing.JLabel DAY_26;
    private javax.swing.JLabel DAY_27;
    private javax.swing.JLabel DAY_28;
    private javax.swing.JLabel DAY_29;
    private javax.swing.JLabel DAY_3;
    private javax.swing.JLabel DAY_30;
    private javax.swing.JLabel DAY_31;
    private javax.swing.JLabel DAY_32;
    private javax.swing.JLabel DAY_33;
    private javax.swing.JLabel DAY_34;
    private javax.swing.JLabel DAY_35;
    private javax.swing.JLabel DAY_36;
    private javax.swing.JLabel DAY_37;
    private javax.swing.JLabel DAY_38;
    private javax.swing.JLabel DAY_39;
    private javax.swing.JLabel DAY_4;
    private javax.swing.JLabel DAY_40;
    private javax.swing.JLabel DAY_41;
    private javax.swing.JLabel DAY_42;
    private javax.swing.JLabel DAY_5;
    private javax.swing.JLabel DAY_6;
    private javax.swing.JLabel DAY_7;
    private javax.swing.JLabel DAY_8;
    private javax.swing.JLabel DAY_9;
    private com.arisystem.beans.boundarypanel.BoundaryPanel FRI_1;
    private com.arisystem.beans.boundarypanel.BoundaryPanel FRI_2;
    private com.arisystem.beans.boundarypanel.BoundaryPanel FRI_3;
    private com.arisystem.beans.boundarypanel.BoundaryPanel FRI_4;
    private com.arisystem.beans.boundarypanel.BoundaryPanel FRI_5;
    private com.arisystem.beans.boundarypanel.BoundaryPanel FRI_6;
    private javax.swing.JLabel FRI_MENU_NAME_1;
    private javax.swing.JLabel FRI_MENU_NAME_2;
    private javax.swing.JLabel FRI_MENU_NAME_3;
    private javax.swing.JLabel FRI_MENU_NAME_4;
    private javax.swing.JLabel FRI_MENU_NAME_5;
    private javax.swing.JLabel FRI_MENU_NAME_6;
    private javax.swing.JLabel FRI_MENU_RESET_COUNT_1;
    private javax.swing.JLabel FRI_MENU_RESET_COUNT_2;
    private javax.swing.JLabel FRI_MENU_RESET_COUNT_3;
    private javax.swing.JLabel FRI_MENU_RESET_COUNT_4;
    private javax.swing.JLabel FRI_MENU_RESET_COUNT_5;
    private javax.swing.JLabel FRI_MENU_RESET_COUNT_6;
    private javax.swing.JLabel FRI_MENU_SELECT_COUNT_1;
    private javax.swing.JLabel FRI_MENU_SELECT_COUNT_2;
    private javax.swing.JLabel FRI_MENU_SELECT_COUNT_3;
    private javax.swing.JLabel FRI_MENU_SELECT_COUNT_4;
    private javax.swing.JLabel FRI_MENU_SELECT_COUNT_5;
    private javax.swing.JLabel FRI_MENU_SELECT_COUNT_6;
    private com.arisystem.beans.boundarypanel.BoundaryPanel MON_1;
    private com.arisystem.beans.boundarypanel.BoundaryPanel MON_2;
    private com.arisystem.beans.boundarypanel.BoundaryPanel MON_3;
    private com.arisystem.beans.boundarypanel.BoundaryPanel MON_4;
    private com.arisystem.beans.boundarypanel.BoundaryPanel MON_5;
    private com.arisystem.beans.boundarypanel.BoundaryPanel MON_6;
    private javax.swing.JLabel MON_MENU_NAME_1;
    private javax.swing.JLabel MON_MENU_NAME_2;
    private javax.swing.JLabel MON_MENU_NAME_3;
    private javax.swing.JLabel MON_MENU_NAME_4;
    private javax.swing.JLabel MON_MENU_NAME_5;
    private javax.swing.JLabel MON_MENU_NAME_6;
    private javax.swing.JLabel MON_MENU_RESET_COUNT_1;
    private javax.swing.JLabel MON_MENU_RESET_COUNT_2;
    private javax.swing.JLabel MON_MENU_RESET_COUNT_3;
    private javax.swing.JLabel MON_MENU_RESET_COUNT_4;
    private javax.swing.JLabel MON_MENU_RESET_COUNT_5;
    private javax.swing.JLabel MON_MENU_RESET_COUNT_6;
    private javax.swing.JLabel MON_MENU_SELECT_COUNT_1;
    private javax.swing.JLabel MON_MENU_SELECT_COUNT_2;
    private javax.swing.JLabel MON_MENU_SELECT_COUNT_3;
    private javax.swing.JLabel MON_MENU_SELECT_COUNT_4;
    private javax.swing.JLabel MON_MENU_SELECT_COUNT_5;
    private javax.swing.JLabel MON_MENU_SELECT_COUNT_6;
    private com.arisystem.beans.boundarypanel.BoundaryPanel SAT_1;
    private com.arisystem.beans.boundarypanel.BoundaryPanel SAT_2;
    private com.arisystem.beans.boundarypanel.BoundaryPanel SAT_3;
    private com.arisystem.beans.boundarypanel.BoundaryPanel SAT_4;
    private com.arisystem.beans.boundarypanel.BoundaryPanel SAT_5;
    private com.arisystem.beans.boundarypanel.BoundaryPanel SAT_6;
    private com.arisystem.beans.boundarypanel.BoundaryPanel SUN_1;
    private com.arisystem.beans.boundarypanel.BoundaryPanel SUN_2;
    private com.arisystem.beans.boundarypanel.BoundaryPanel SUN_3;
    private com.arisystem.beans.boundarypanel.BoundaryPanel SUN_4;
    private com.arisystem.beans.boundarypanel.BoundaryPanel SUN_5;
    private com.arisystem.beans.boundarypanel.BoundaryPanel SUN_6;
    private com.arisystem.beans.boundarypanel.BoundaryPanel THU_1;
    private com.arisystem.beans.boundarypanel.BoundaryPanel THU_2;
    private com.arisystem.beans.boundarypanel.BoundaryPanel THU_3;
    private com.arisystem.beans.boundarypanel.BoundaryPanel THU_4;
    private com.arisystem.beans.boundarypanel.BoundaryPanel THU_5;
    private com.arisystem.beans.boundarypanel.BoundaryPanel THU_6;
    private javax.swing.JLabel THU_MENU_NAME_1;
    private javax.swing.JLabel THU_MENU_NAME_2;
    private javax.swing.JLabel THU_MENU_NAME_3;
    private javax.swing.JLabel THU_MENU_NAME_4;
    private javax.swing.JLabel THU_MENU_NAME_5;
    private javax.swing.JLabel THU_MENU_NAME_6;
    private javax.swing.JLabel THU_MENU_RESET_COUNT_1;
    private javax.swing.JLabel THU_MENU_RESET_COUNT_2;
    private javax.swing.JLabel THU_MENU_RESET_COUNT_3;
    private javax.swing.JLabel THU_MENU_RESET_COUNT_4;
    private javax.swing.JLabel THU_MENU_RESET_COUNT_5;
    private javax.swing.JLabel THU_MENU_RESET_COUNT_6;
    private javax.swing.JLabel THU_MENU_SELECT_COUNT_1;
    private javax.swing.JLabel THU_MENU_SELECT_COUNT_2;
    private javax.swing.JLabel THU_MENU_SELECT_COUNT_3;
    private javax.swing.JLabel THU_MENU_SELECT_COUNT_4;
    private javax.swing.JLabel THU_MENU_SELECT_COUNT_5;
    private javax.swing.JLabel THU_MENU_SELECT_COUNT_6;
    private com.arisystem.beans.boundarypanel.BoundaryPanel TUE_1;
    private com.arisystem.beans.boundarypanel.BoundaryPanel TUE_2;
    private com.arisystem.beans.boundarypanel.BoundaryPanel TUE_3;
    private com.arisystem.beans.boundarypanel.BoundaryPanel TUE_4;
    private com.arisystem.beans.boundarypanel.BoundaryPanel TUE_5;
    private com.arisystem.beans.boundarypanel.BoundaryPanel TUE_6;
    private javax.swing.JLabel TUE_MENU_NAME_1;
    private javax.swing.JLabel TUE_MENU_NAME_2;
    private javax.swing.JLabel TUE_MENU_NAME_3;
    private javax.swing.JLabel TUE_MENU_NAME_4;
    private javax.swing.JLabel TUE_MENU_NAME_5;
    private javax.swing.JLabel TUE_MENU_NAME_6;
    private javax.swing.JLabel TUE_MENU_RESET_COUNT_1;
    private javax.swing.JLabel TUE_MENU_RESET_COUNT_2;
    private javax.swing.JLabel TUE_MENU_RESET_COUNT_3;
    private javax.swing.JLabel TUE_MENU_RESET_COUNT_4;
    private javax.swing.JLabel TUE_MENU_RESET_COUNT_5;
    private javax.swing.JLabel TUE_MENU_RESET_COUNT_6;
    private javax.swing.JLabel TUE_MENU_SELECT_COUNT_1;
    private javax.swing.JLabel TUE_MENU_SELECT_COUNT_2;
    private javax.swing.JLabel TUE_MENU_SELECT_COUNT_3;
    private javax.swing.JLabel TUE_MENU_SELECT_COUNT_4;
    private javax.swing.JLabel TUE_MENU_SELECT_COUNT_5;
    private javax.swing.JLabel TUE_MENU_SELECT_COUNT_6;
    private com.arisystem.beans.boundarypanel.BoundaryPanel WED_1;
    private com.arisystem.beans.boundarypanel.BoundaryPanel WED_2;
    private com.arisystem.beans.boundarypanel.BoundaryPanel WED_3;
    private com.arisystem.beans.boundarypanel.BoundaryPanel WED_4;
    private com.arisystem.beans.boundarypanel.BoundaryPanel WED_5;
    private com.arisystem.beans.boundarypanel.BoundaryPanel WED_6;
    private javax.swing.JLabel WED_MENU_NAME_1;
    private javax.swing.JLabel WED_MENU_NAME_2;
    private javax.swing.JLabel WED_MENU_NAME_3;
    private javax.swing.JLabel WED_MENU_NAME_4;
    private javax.swing.JLabel WED_MENU_NAME_5;
    private javax.swing.JLabel WED_MENU_NAME_6;
    private javax.swing.JLabel WED_MENU_RESET_COUNT_1;
    private javax.swing.JLabel WED_MENU_RESET_COUNT_2;
    private javax.swing.JLabel WED_MENU_RESET_COUNT_3;
    private javax.swing.JLabel WED_MENU_RESET_COUNT_4;
    private javax.swing.JLabel WED_MENU_RESET_COUNT_5;
    private javax.swing.JLabel WED_MENU_RESET_COUNT_6;
    private javax.swing.JLabel WED_MENU_SELECT_COUNT_1;
    private javax.swing.JLabel WED_MENU_SELECT_COUNT_2;
    private javax.swing.JLabel WED_MENU_SELECT_COUNT_3;
    private javax.swing.JLabel WED_MENU_SELECT_COUNT_4;
    private javax.swing.JLabel WED_MENU_SELECT_COUNT_5;
    private javax.swing.JLabel WED_MENU_SELECT_COUNT_6;
    private com.arisystem.beans.boundarypanel.BoundaryPanel boundaryPanel1;
    private com.arisystem.beans.datawizard.DWMultiRowsObject dWMultiRowsObject1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    // End of variables declaration//GEN-END:variables
}
