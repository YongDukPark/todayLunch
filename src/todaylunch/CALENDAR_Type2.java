/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todaylunch;

import com.arisystem.beans.boundarypanel.BoundaryPanel;
import com.arisystem.beans.datawizard.DWWhereCondition;
import com.arisystem.beans.framebuilder.FBRowSet;
import java.awt.Color;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

public class CALENDAR_Type2 extends javax.swing.JFrame {
    ArrayList<BoundaryPanel> arraylist = new ArrayList<>();
    //셀에 마우스 올렸을경우 변경되는 색상
    Color color = new Color(232, 237, 255);
    
    
    private static CALENDAR_Type2 CALENDAR_Type2;
    
    public static CALENDAR_Type2 getInstance(){
        CALENDAR_Type2 = new CALENDAR_Type2();
        if (! CALENDAR_Type2.isVisible()){
            CALENDAR_Type2.setVisible(true);
        }
        return CALENDAR_Type2;
    }
    
    private CALENDAR_Type2() {
        //기본세팅
        initComponents();
        //combobox 데이터 초기세팅
        setComboBox();
        //Boundary List에 적재
        addBounDary();
        //Calendar 생성
        getCalendar();
        
    }
    
    //BoundaryPanel을 ArrayList에 담아서 이후 호출시키기 편하게 만든다.
    public void addBounDary(){
        arraylist.add(BOUNDARY_1); arraylist.add(BOUNDARY_2); arraylist.add(BOUNDARY_3); arraylist.add(BOUNDARY_4);
        arraylist.add(BOUNDARY_5); arraylist.add(BOUNDARY_6); arraylist.add(BOUNDARY_7); arraylist.add(BOUNDARY_8);
        arraylist.add(BOUNDARY_9); arraylist.add(BOUNDARY_10); arraylist.add(BOUNDARY_11); arraylist.add(BOUNDARY_12);
        arraylist.add(BOUNDARY_13); arraylist.add(BOUNDARY_14); arraylist.add(BOUNDARY_15); arraylist.add(BOUNDARY_16);
        arraylist.add(BOUNDARY_17); arraylist.add(BOUNDARY_18); arraylist.add(BOUNDARY_19); arraylist.add(BOUNDARY_20);
        arraylist.add(BOUNDARY_21); arraylist.add(BOUNDARY_22); arraylist.add(BOUNDARY_23); arraylist.add(BOUNDARY_24);
        arraylist.add(BOUNDARY_25); arraylist.add(BOUNDARY_26); arraylist.add(BOUNDARY_27); arraylist.add(BOUNDARY_28);
        arraylist.add(BOUNDARY_29); arraylist.add(BOUNDARY_30); arraylist.add(BOUNDARY_31); arraylist.add(BOUNDARY_32);
        arraylist.add(BOUNDARY_33); arraylist.add(BOUNDARY_34); arraylist.add(BOUNDARY_35); arraylist.add(BOUNDARY_36);
        arraylist.add(BOUNDARY_37); arraylist.add(BOUNDARY_38); arraylist.add(BOUNDARY_39); arraylist.add(BOUNDARY_40);
        arraylist.add(BOUNDARY_41); arraylist.add(BOUNDARY_42);
    }
    
    //comboBox data set
    public void setComboBox(){
        jComboBox1.addItem("년도");
        jComboBox2.addItem("월");
        //년도
        for(int i = 2020 ; i <= 2023 ; i++){
            jComboBox1.addItem(String.valueOf(i));
        }
        //월
        for(int i = 1 ; i <= 12 ; i++){
            jComboBox2.addItem(String.valueOf(i));
        }
    }
    
    //흠 로직을 생각하자 로직로오오오직
    //캘린더 데이터 만들어지는 영역
    public void getCalendar(){
        //where절 사용할때 필요한애들
        Vector params = new Vector();
        DWWhereCondition SearchSelect;
        FBRowSet seseset;
        
        //요일 계산용
        LocalDate date;
        DayOfWeek dayOfWeek;
        Calendar cal = Calendar.getInstance();
        
        //날짜 넣기용도로 사용하며 이후 적용시킨 월의 가장 마지막 일자를 return한다.
        int setDay = 1;
        //시작하는 요일 숫자
        int startWeek = 0;
        //DB에서 가져온 가장 낮은일자 후에 length에서 사용해서 -1처리 후에 해줌
        int startDay = 0;
        //DB데이터를 가져올때 사용한다.
        int rowCount = 0;
        //DB데이터가 rowCount와  .getRowCount값을 비교해서 만약 rowCount가 .getRowCount보다 크거나 같을경우 false를 찍는다.
        boolean dataCheck = true;
        
        try {
            if(jComboBox1.getSelectedIndex() == 0 || jComboBox2.getSelectedIndex() == 0){
                params.add("2023");
                jLabel1.setText("2023년");
                params.add("01");
                jLabel2.setText("01월");
            } else {
                params.add(jComboBox1.getSelectedItem());
                jLabel1.setText((String)jComboBox1.getSelectedItem()+"년");
                if(jComboBox2.getSelectedItem().toString().length() == 1){
                    params.add("0"+jComboBox2.getSelectedItem());
                    jLabel2.setText("0"+jComboBox2.getSelectedItem()+"월");
                } else {
                    params.add(jComboBox2.getSelectedItem());
                    jLabel2.setText(jComboBox2.getSelectedItem()+"월");
                }
            }
            
            //월
            SearchSelect = new DWWhereCondition("SELECT_YEAR = ? AND SELECT_MONTH = ?", params);
            dWMultiRowsObject1.setWhereContition(SearchSelect);
            
            dWMultiRowsObject1.setDataSource("MariaDB_Youngria");
            dWMultiRowsObject1.setOrderBy("SELECT_DAY");
            seseset = dWMultiRowsObject1.select("http", "192.168.0.20", 8080);
            
            
            //요일 구하는 로직 (년,월,일) int형태로 들어간다.
            date = LocalDate.of(Integer.parseInt((String)seseset.getValue(0, "SELECT_YEAR")), Integer.parseInt((String)seseset.getValue(0, "SELECT_MONTH")), 1);
            
            dayOfWeek = date.getDayOfWeek();
            // 시작요일 월1 일7
            startWeek = dayOfWeek.getValue();
            
            //만약 일요일일 경우 0부터 시작하도록
            if(startWeek == 7){
                startWeek = 0;
            }
            startDay = Integer.parseInt((String)seseset.getValue(0, "SELECT_DAY"))-1;
            
            // 해당 월의 마지막일 구하기 (년,월,일) int 형태로 들어간다.
            cal.set(Integer.parseInt((String)seseset.getValue(0, "SELECT_YEAR")), Integer.parseInt((String)seseset.getValue(0, "SELECT_MONTH"))-1, Integer.parseInt((String)seseset.getValue(0, "SELECT_DAY")));
            
            
            //날짜 넣는 구간
            //i가 dayOfWeek 즉 요일첫번째부터 시작된다. 위에서 만약 7일 경우 값은 0이된다.
            for(int i = startWeek ; i < startWeek + cal.getActualMaximum(Calendar.DAY_OF_MONTH) ; i++){
                if(rowCount >= seseset.getRowCount()){
                    dataCheck = false;
                }
                
                arraylist.get(i).getBoundaryRenderer().getBoundaryCell("DAY").setTitleValue(setDay);
                    
                if(dataCheck){
                    if(arraylist.get(i+startDay).getBoundaryRenderer().getBoundaryCell("WEEKEND") == null){
                        if(seseset.getValue(rowCount, "MENU_SELECT").equals("N")){
                            arraylist.get(i+startDay).getBoundaryRenderer().getBoundaryCell("BoundaryCell_14").setTitleValue("메뉴");
                            arraylist.get(i+startDay).getBoundaryRenderer().getBoundaryCell("BoundaryCell_18").setTitleValue("시도");
                            arraylist.get(i+startDay).getBoundaryRenderer().getBoundaryCell("BoundaryCell_21").setTitleValue("초기화");
                            arraylist.get(i+startDay).getBoundaryRenderer().getBoundaryCell("MENU_NAME").setTitleValue("선택X");
                            arraylist.get(i+startDay).getBoundaryRenderer().getBoundaryCell("MENU_NAME").setFontColor(Color.RED);
                            arraylist.get(i+startDay).getBoundaryRenderer().getBoundaryCell("MENU_SELECT_COUNT").setTitleValue(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT"));
                            arraylist.get(i+startDay).getBoundaryRenderer().getBoundaryCell("MENU_RESET_COUNT").setTitleValue(seseset.getValue(rowCount, "MENU_RESET_COUNT"));
                        } else {
                            arraylist.get(i+startDay).getBoundaryRenderer().getBoundaryCell("BoundaryCell_14").setTitleValue("메뉴");
                            arraylist.get(i+startDay).getBoundaryRenderer().getBoundaryCell("BoundaryCell_18").setTitleValue("시도");
                            arraylist.get(i+startDay).getBoundaryRenderer().getBoundaryCell("BoundaryCell_21").setTitleValue("초기화");
                            arraylist.get(i+startDay).getBoundaryRenderer().getBoundaryCell("MENU_NAME").setTitleValue(seseset.getValue(rowCount, "MENU_NAME"));
                            arraylist.get(i+startDay).getBoundaryRenderer().getBoundaryCell("MENU_SELECT_COUNT").setTitleValue(seseset.getValue(rowCount, "MENU_SELECT_TOTALCOUNT"));
                            arraylist.get(i+startDay).getBoundaryRenderer().getBoundaryCell("MENU_RESET_COUNT").setTitleValue(seseset.getValue(rowCount, "MENU_RESET_COUNT"));
                        }
                        //DB data를 가져오는 rowCount를 증가시킨다.
                        rowCount++;
                    }
                }
                //날짜를 증가시킨다.
                setDay++;
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    //여기서 데이터들을 한번 다 지워준다.
    public void resetAll(){
        for(int i = 0 ; i < arraylist.size() ; i++){
            if(arraylist.get(i).getBoundaryRenderer().getBoundaryCell("WEEKEND") == null){
                arraylist.get(i).getBoundaryRenderer().getBoundaryCell("DAY").setTitleValue("");
                arraylist.get(i).getBoundaryRenderer().getBoundaryCell("BoundaryCell_14").setTitleValue("");
                arraylist.get(i).getBoundaryRenderer().getBoundaryCell("BoundaryCell_18").setTitleValue("");
                arraylist.get(i).getBoundaryRenderer().getBoundaryCell("BoundaryCell_21").setTitleValue("");
                arraylist.get(i).getBoundaryRenderer().getBoundaryCell("MENU_NAME").setTitleValue("");
                arraylist.get(i).getBoundaryRenderer().getBoundaryCell("MENU_SELECT_COUNT").setTitleValue("");
                arraylist.get(i).getBoundaryRenderer().getBoundaryCell("MENU_RESET_COUNT").setTitleValue("");
                arraylist.get(i).getBoundaryRenderer().getBoundaryCell("MENU_NAME").setFontColor(Color.BLACK);
            } else {
                arraylist.get(i).getBoundaryRenderer().getBoundaryCell("DAY").setTitleValue("");
            }
        }
        //이녀석으로 새로고침을 해줘야한다.
        boundaryPanel1.updateUI();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dWMultiRowsObject1 = new com.arisystem.beans.datawizard.DWMultiRowsObject();
        boundaryPanel1 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_1 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_8 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_15 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_22 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_29 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_36 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_2 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_9 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_16 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_23 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_30 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_37 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_3 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_10 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_17 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_24 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_31 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_38 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_4 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_11 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_18 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_25 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_32 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_39 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_5 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_12 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_19 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_26 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_33 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_40 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_6 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_13 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_20 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_27 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_34 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_41 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_7 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_14 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_21 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_28 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_35 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        BOUNDARY_42 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

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

setBackground(new java.awt.Color(255, 255, 255));

boundaryPanel1.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
    new com.arisystem.beans.boundarypanel.BoundaryCell("T_C_1_1",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),"일",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,new java.awt.Color(255,0,0),new java.awt.Color(255,153,153),null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("T_C_2_1",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),"월",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,new java.awt.Color(239,239,239),null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("T_C_3_1",new com.arisystem.beans.boundarypanel.CellInfo(2,0),new com.arisystem.beans.boundarypanel.CellInfo(2,0),"화",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,new java.awt.Color(239,239,239),null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("T_C_4_1",new com.arisystem.beans.boundarypanel.CellInfo(3,0),new com.arisystem.beans.boundarypanel.CellInfo(3,0),"수",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,new java.awt.Color(239,239,239),null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("T_C_5_1",new com.arisystem.beans.boundarypanel.CellInfo(4,0),new com.arisystem.beans.boundarypanel.CellInfo(4,0),"목",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,new java.awt.Color(239,239,239),null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("T_C_6_1",new com.arisystem.beans.boundarypanel.CellInfo(5,0),new com.arisystem.beans.boundarypanel.CellInfo(5,0),"금",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,new java.awt.Color(239,239,239),null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
    new com.arisystem.beans.boundarypanel.BoundaryCell("T_C_7_1",new com.arisystem.beans.boundarypanel.CellInfo(6,0),new com.arisystem.beans.boundarypanel.CellInfo(6,0),"토",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,new java.awt.Color(0,84,255),new java.awt.Color(153,153,255),null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
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
    boundaryPanel1.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });

    BOUNDARY_1.setBoundaryLineColor(new java.awt.Color(234, 234, 234));
    BOUNDARY_1.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("WEEKEND",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_16",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_1.setCellBackColor(new java.awt.Color(234, 234, 234));
    BOUNDARY_1.setColumnCount(2);
    BOUNDARY_1.setColumnWidths(new int[] {35, 55});
    BOUNDARY_1.setRowHeights(new int[] {27, 78});
    boundaryPanel1.add(BOUNDARY_1);
    BOUNDARY_1.setBounds(50, 150, 91, 106);

    BOUNDARY_8.setBoundaryLineColor(new java.awt.Color(234, 234, 234));
    BOUNDARY_8.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("WEEKEND",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_17",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_8.setCellBackColor(new java.awt.Color(234, 234, 234));
    BOUNDARY_8.setColumnCount(2);
    BOUNDARY_8.setColumnWidths(new int[] {35, 55});
    BOUNDARY_8.setRowHeights(new int[] {27, 78});
    boundaryPanel1.add(BOUNDARY_8);
    BOUNDARY_8.setBounds(10, 140, 89, 104);

    BOUNDARY_15.setBoundaryLineColor(new java.awt.Color(234, 234, 234));
    BOUNDARY_15.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("WEEKEND",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_15.setCellBackColor(new java.awt.Color(234, 234, 234));
    BOUNDARY_15.setColumnCount(2);
    BOUNDARY_15.setColumnWidths(new int[] {35, 55});
    BOUNDARY_15.setRowHeights(new int[] {27, 78});
    boundaryPanel1.add(BOUNDARY_15);
    BOUNDARY_15.setBounds(50, 150, 91, 106);

    BOUNDARY_22.setBoundaryLineColor(new java.awt.Color(234, 234, 234));
    BOUNDARY_22.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("WEEKEND",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_19",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_22.setCellBackColor(new java.awt.Color(234, 234, 234));
    BOUNDARY_22.setColumnCount(2);
    BOUNDARY_22.setColumnWidths(new int[] {35, 55});
    BOUNDARY_22.setRowHeights(new int[] {27, 78});
    boundaryPanel1.add(BOUNDARY_22);
    BOUNDARY_22.setBounds(0, 350, 89, 104);

    BOUNDARY_29.setBoundaryLineColor(new java.awt.Color(234, 234, 234));
    BOUNDARY_29.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("WEEKEND",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_20",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_29.setCellBackColor(new java.awt.Color(234, 234, 234));
    BOUNDARY_29.setColumnCount(2);
    BOUNDARY_29.setColumnWidths(new int[] {35, 55});
    BOUNDARY_29.setRowHeights(new int[] {27, 78});
    boundaryPanel1.add(BOUNDARY_29);
    BOUNDARY_29.setBounds(50, 150, 91, 106);

    BOUNDARY_36.setBoundaryLineColor(new java.awt.Color(234, 234, 234));
    BOUNDARY_36.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("WEEKEND",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_36.setCellBackColor(new java.awt.Color(234, 234, 234));
    BOUNDARY_36.setColumnCount(2);
    BOUNDARY_36.setColumnWidths(new int[] {35, 55});
    BOUNDARY_36.setRowHeights(new int[] {27, 78});
    boundaryPanel1.add(BOUNDARY_36);
    BOUNDARY_36.setBounds(50, 150, 91, 106);

    BOUNDARY_2.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_2.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_2.setColumnCount(2);
    BOUNDARY_2.setColumnWidths(new int[] {40, 85});
    BOUNDARY_2.setRowCount(4);
    BOUNDARY_2.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_2.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_2.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_2);
    BOUNDARY_2.setBounds(30, 30, 99, 104);

    BOUNDARY_9.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_9.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_9.setColumnCount(2);
    BOUNDARY_9.setColumnWidths(new int[] {40, 85});
    BOUNDARY_9.setRowCount(4);
    BOUNDARY_9.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_9.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_9.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_9);
    BOUNDARY_9.setBounds(30, 30, 99, 104);

    BOUNDARY_16.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_16.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_16.setColumnCount(2);
    BOUNDARY_16.setColumnWidths(new int[] {40, 85});
    BOUNDARY_16.setRowCount(4);
    BOUNDARY_16.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_16.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_16.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_16);
    BOUNDARY_16.setBounds(30, 30, 99, 104);

    BOUNDARY_23.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_23.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_23.setColumnCount(2);
    BOUNDARY_23.setColumnWidths(new int[] {40, 85});
    BOUNDARY_23.setRowCount(4);
    BOUNDARY_23.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_23.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_23.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_23);
    BOUNDARY_23.setBounds(30, 30, 99, 104);

    BOUNDARY_30.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_30.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_30.setColumnCount(2);
    BOUNDARY_30.setColumnWidths(new int[] {40, 85});
    BOUNDARY_30.setRowCount(4);
    BOUNDARY_30.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_30.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_30.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_30);
    BOUNDARY_30.setBounds(30, 30, 99, 104);

    BOUNDARY_37.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_37.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_37.setColumnCount(2);
    BOUNDARY_37.setColumnWidths(new int[] {40, 85});
    BOUNDARY_37.setRowCount(4);
    BOUNDARY_37.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_37.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_37.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_37);
    BOUNDARY_37.setBounds(30, 30, 99, 104);

    BOUNDARY_3.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_3.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_3.setColumnCount(2);
    BOUNDARY_3.setColumnWidths(new int[] {40, 85});
    BOUNDARY_3.setRowCount(4);
    BOUNDARY_3.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_3.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_3.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_3);
    BOUNDARY_3.setBounds(30, 30, 99, 104);

    BOUNDARY_10.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_10.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_10.setColumnCount(2);
    BOUNDARY_10.setColumnWidths(new int[] {39, 85});
    BOUNDARY_10.setRowCount(4);
    BOUNDARY_10.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_10.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_10.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_10);
    BOUNDARY_10.setBounds(30, 30, 99, 104);

    BOUNDARY_17.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_17.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_17.setColumnCount(2);
    BOUNDARY_17.setColumnWidths(new int[] {40, 85});
    BOUNDARY_17.setRowCount(4);
    BOUNDARY_17.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_17.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_17.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_17);
    BOUNDARY_17.setBounds(30, 30, 99, 104);

    BOUNDARY_24.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_24.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_24.setColumnCount(2);
    BOUNDARY_24.setColumnWidths(new int[] {40, 85});
    BOUNDARY_24.setRowCount(4);
    BOUNDARY_24.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_24.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_24.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_24);
    BOUNDARY_24.setBounds(30, 30, 99, 104);

    BOUNDARY_31.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_31.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_31.setColumnCount(2);
    BOUNDARY_31.setColumnWidths(new int[] {40, 85});
    BOUNDARY_31.setRowCount(4);
    BOUNDARY_31.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_31.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_31.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_31);
    BOUNDARY_31.setBounds(30, 30, 99, 104);

    BOUNDARY_38.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_38.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_38.setColumnCount(2);
    BOUNDARY_38.setColumnWidths(new int[] {40, 85});
    BOUNDARY_38.setRowCount(4);
    BOUNDARY_38.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_38.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_38.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_38);
    BOUNDARY_38.setBounds(30, 30, 99, 104);

    BOUNDARY_4.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_4.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_4.setColumnCount(2);
    BOUNDARY_4.setColumnWidths(new int[] {40, 85});
    BOUNDARY_4.setRowCount(4);
    BOUNDARY_4.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_4.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_4.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_4);
    BOUNDARY_4.setBounds(30, 30, 99, 104);

    BOUNDARY_11.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_11.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_11.setColumnCount(2);
    BOUNDARY_11.setColumnWidths(new int[] {40, 85});
    BOUNDARY_11.setRowCount(4);
    BOUNDARY_11.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_11.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_11.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_11);
    BOUNDARY_11.setBounds(30, 30, 99, 104);

    BOUNDARY_18.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_18.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_18.setColumnCount(2);
    BOUNDARY_18.setColumnWidths(new int[] {40, 85});
    BOUNDARY_18.setRowCount(4);
    BOUNDARY_18.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_18.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_18.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_18);
    BOUNDARY_18.setBounds(30, 30, 99, 104);

    BOUNDARY_25.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_25.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_25.setColumnCount(2);
    BOUNDARY_25.setColumnWidths(new int[] {40, 85});
    BOUNDARY_25.setRowCount(4);
    BOUNDARY_25.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_25.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_25.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_25);
    BOUNDARY_25.setBounds(30, 30, 99, 104);

    BOUNDARY_32.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_32.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_32.setColumnCount(2);
    BOUNDARY_32.setColumnWidths(new int[] {40, 85});
    BOUNDARY_32.setRowCount(4);
    BOUNDARY_32.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_32.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_32.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_32);
    BOUNDARY_32.setBounds(30, 30, 99, 104);

    BOUNDARY_39.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_39.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_39.setColumnCount(2);
    BOUNDARY_39.setColumnWidths(new int[] {40, 85});
    BOUNDARY_39.setRowCount(4);
    BOUNDARY_39.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_39.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_39.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_39);
    BOUNDARY_39.setBounds(30, 30, 99, 104);

    BOUNDARY_5.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_5.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_5.setColumnCount(2);
    BOUNDARY_5.setColumnWidths(new int[] {40, 85});
    BOUNDARY_5.setRowCount(4);
    BOUNDARY_5.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_5.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_5.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_5);
    BOUNDARY_5.setBounds(30, 30, 99, 104);

    BOUNDARY_12.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_12.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_12.setColumnCount(2);
    BOUNDARY_12.setColumnWidths(new int[] {40, 85});
    BOUNDARY_12.setRowCount(4);
    BOUNDARY_12.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_12.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_12.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_12);
    BOUNDARY_12.setBounds(30, 30, 99, 104);

    BOUNDARY_19.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_19.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_19.setColumnCount(2);
    BOUNDARY_19.setColumnWidths(new int[] {40, 85});
    BOUNDARY_19.setRowCount(4);
    BOUNDARY_19.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_19.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_19.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_19);
    BOUNDARY_19.setBounds(30, 30, 99, 104);

    BOUNDARY_26.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_26.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_26.setColumnCount(2);
    BOUNDARY_26.setColumnWidths(new int[] {40, 85});
    BOUNDARY_26.setRowCount(4);
    BOUNDARY_26.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_26.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_26.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_26);
    BOUNDARY_26.setBounds(30, 30, 99, 104);

    BOUNDARY_33.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_33.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_33.setColumnCount(2);
    BOUNDARY_33.setColumnWidths(new int[] {40, 85});
    BOUNDARY_33.setRowCount(4);
    BOUNDARY_33.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_33.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_33.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_33);
    BOUNDARY_33.setBounds(30, 30, 99, 104);

    BOUNDARY_40.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_40.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_40.setColumnCount(2);
    BOUNDARY_40.setColumnWidths(new int[] {40, 85});
    BOUNDARY_40.setRowCount(4);
    BOUNDARY_40.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_40.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_40.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_40);
    BOUNDARY_40.setBounds(30, 30, 99, 104);

    BOUNDARY_6.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_6.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_6.setBundleName("");
    BOUNDARY_6.setColumnCount(2);
    BOUNDARY_6.setColumnWidths(new int[] {40, 85});
    BOUNDARY_6.setRowCount(4);
    BOUNDARY_6.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_6.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_6.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_6);
    BOUNDARY_6.setBounds(30, 30, 99, 104);

    BOUNDARY_13.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_13.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_13.setColumnCount(2);
    BOUNDARY_13.setColumnWidths(new int[] {40, 85});
    BOUNDARY_13.setRowCount(4);
    BOUNDARY_13.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_13.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_13.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_13);
    BOUNDARY_13.setBounds(30, 30, 99, 104);

    BOUNDARY_20.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_20.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_20.setColumnCount(2);
    BOUNDARY_20.setColumnWidths(new int[] {40, 85});
    BOUNDARY_20.setRowCount(4);
    BOUNDARY_20.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_20.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_20.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_20);
    BOUNDARY_20.setBounds(30, 30, 99, 104);

    BOUNDARY_27.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_27.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_27.setColumnCount(2);
    BOUNDARY_27.setColumnWidths(new int[] {40, 85});
    BOUNDARY_27.setRowCount(4);
    BOUNDARY_27.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_27.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_27.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_27);
    BOUNDARY_27.setBounds(30, 30, 99, 104);

    BOUNDARY_34.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_34.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_34.setColumnCount(2);
    BOUNDARY_34.setColumnWidths(new int[] {40, 85});
    BOUNDARY_34.setRowCount(4);
    BOUNDARY_34.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_34.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_34.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_34);
    BOUNDARY_34.setBounds(30, 30, 99, 104);

    BOUNDARY_41.setBoundaryLineColor(new java.awt.Color(255, 255, 255));
    BOUNDARY_41.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_14",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_18",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_21",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"",com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_NAME",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_SELECT_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("MENU_RESET_COUNT",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_41.setColumnCount(2);
    BOUNDARY_41.setColumnWidths(new int[] {40, 85});
    BOUNDARY_41.setRowCount(4);
    BOUNDARY_41.setRowHeights(new int[] {27, 26, 26, 26});
    BOUNDARY_41.addBoundaryPanelListener(new com.arisystem.beans.boundarypanel.BoundaryPanelListener() {
        public void boundaryCellMouseClick(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
        public void boundaryCellMouseEnter(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseEnterBOUNDARY(evt);
        }
        public void boundaryCellMouseExit(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
            mouseExitBOUNDARY(evt);
        }
        public void boundaryCellBeforePaintCell(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {
        }
    });
    BOUNDARY_41.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mouseClickBOUNDARY(evt);
        }
    });
    boundaryPanel1.add(BOUNDARY_41);
    BOUNDARY_41.setBounds(30, 30, 99, 104);

    BOUNDARY_7.setBoundaryLineColor(new java.awt.Color(234, 234, 234));
    BOUNDARY_7.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("WEEKEND",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_22",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_7.setCellBackColor(new java.awt.Color(234, 234, 234));
    BOUNDARY_7.setColumnCount(2);
    BOUNDARY_7.setColumnWidths(new int[] {35, 55});
    BOUNDARY_7.setRowHeights(new int[] {27, 78});
    boundaryPanel1.add(BOUNDARY_7);
    BOUNDARY_7.setBounds(50, 150, 91, 106);

    BOUNDARY_14.setBoundaryLineColor(new java.awt.Color(234, 234, 234));
    BOUNDARY_14.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("WEEKEND",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_23",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_14.setCellBackColor(new java.awt.Color(234, 234, 234));
    BOUNDARY_14.setColumnCount(2);
    BOUNDARY_14.setColumnWidths(new int[] {35, 55});
    BOUNDARY_14.setRowHeights(new int[] {27, 78});
    boundaryPanel1.add(BOUNDARY_14);
    BOUNDARY_14.setBounds(50, 150, 91, 106);

    BOUNDARY_21.setBoundaryLineColor(new java.awt.Color(234, 234, 234));
    BOUNDARY_21.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("WEEKEND",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_24",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_21.setCellBackColor(new java.awt.Color(234, 234, 234));
    BOUNDARY_21.setColumnCount(2);
    BOUNDARY_21.setColumnWidths(new int[] {35, 55});
    BOUNDARY_21.setRowHeights(new int[] {27, 78});
    boundaryPanel1.add(BOUNDARY_21);
    BOUNDARY_21.setBounds(50, 150, 91, 106);

    BOUNDARY_28.setBoundaryLineColor(new java.awt.Color(234, 234, 234));
    BOUNDARY_28.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("WEEKEND",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_25",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_28.setCellBackColor(new java.awt.Color(234, 234, 234));
    BOUNDARY_28.setColumnCount(2);
    BOUNDARY_28.setColumnWidths(new int[] {35, 55});
    BOUNDARY_28.setRowHeights(new int[] {27, 78});
    boundaryPanel1.add(BOUNDARY_28);
    BOUNDARY_28.setBounds(50, 150, 91, 106);

    BOUNDARY_35.setBoundaryLineColor(new java.awt.Color(234, 234, 234));
    BOUNDARY_35.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("WEEKEND",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_26",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_35.setCellBackColor(new java.awt.Color(234, 234, 234));
    BOUNDARY_35.setColumnCount(2);
    BOUNDARY_35.setColumnWidths(new int[] {35, 55});
    BOUNDARY_35.setRowHeights(new int[] {27, 78});
    boundaryPanel1.add(BOUNDARY_35);
    BOUNDARY_35.setBounds(50, 150, 91, 106);

    BOUNDARY_42.setBoundaryLineColor(new java.awt.Color(234, 234, 234));
    BOUNDARY_42.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
        new com.arisystem.beans.boundarypanel.BoundaryCell("WEEKEND",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),"",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("DAY",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.LEFT_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        new com.arisystem.beans.boundarypanel.BoundaryCell("BoundaryCell_27",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),false,false,false,new java.awt.Insets(0,0,0,0),null),
    }));
    BOUNDARY_42.setCellBackColor(new java.awt.Color(234, 234, 234));
    BOUNDARY_42.setColumnCount(2);
    BOUNDARY_42.setColumnWidths(new int[] {35, 55});
    BOUNDARY_42.setRowHeights(new int[] {27, 78});
    boundaryPanel1.add(BOUNDARY_42);
    BOUNDARY_42.setBounds(50, 150, 91, 106);

    jButton1.setText("조회");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    jLabel1.setFont(new java.awt.Font("맑은 고딕 Semilight", 1, 36)); // NOI18N
    jLabel1.setText("2099년");

    jLabel2.setFont(new java.awt.Font("맑은 고딕 Semilight", 1, 24)); // NOI18N
    jLabel2.setText("12월");

    jLabel3.setText("※더블클릭 log확인");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(30, 30, 30)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(boundaryPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(30, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel2)
                    .addGap(33, 33, 33)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jButton1)
                    .addGap(50, 50, 50))))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(jLabel2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(20, 20, 20)
            .addComponent(boundaryPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(30, 30, 30))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        resetAll();
        getCalendar();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    //마우스 클릭시 log보는곳으로 이동
    private void mouseClickBOUNDARY(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseClickBOUNDARY
        if(evt.getClickCount() > 1){
            for(int i = 0 ; i < arraylist.size() ; i++){
                if(evt.getSource() == arraylist.get(i)){
                    System.out.println("나나나 그거야 그거 "+i+"번째놈" );
                    arraylist.get(i).getBoundaryRenderer().getBoundaryCell("DAY").getTitleValue();
                    //arraylist.get(i).setBoundaryLineColor(Color.yellow);
                    jLabel1.getText();
                    jLabel2.getText();
                    LOGCHECK.getInstance(jLabel1.getText().replaceAll("년", ""), jLabel2.getText().replaceAll("월", ""), String.valueOf(arraylist.get(i).getBoundaryRenderer().getBoundaryCell("DAY").getTitleValue()));
                }
            }
        }
    }//GEN-LAST:event_mouseClickBOUNDARY

    private void mouseEnterBOUNDARY(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {//GEN-FIRST:event_mouseEnterBOUNDARY
        //System.out.println(evt.getBoundaryCellName());
        //boundaryPanel1.getBoundaryRenderer().getBoundaryCell(evt.getBoundaryCellName()).setGradientColor(Color.red);
        //boundaryPanel1.getBoundaryRenderer().getBoundaryCell(evt.getBoundaryCellName()).
        for(int i = 0 ; i < arraylist.size() ; i++){
            if(evt.getSource() == arraylist.get(i)){
                arraylist.get(i).setBoundaryLineColor(color);
                arraylist.get(i).setBackground(color);
            }
        }
    }//GEN-LAST:event_mouseEnterBOUNDARY

    private void mouseExitBOUNDARY(com.arisystem.beans.boundarypanel.BoundaryPanelEvent evt) {//GEN-FIRST:event_mouseExitBOUNDARY
        for(int i = 0 ; i < arraylist.size() ; i++){
            if(evt.getSource() == arraylist.get(i)){
                arraylist.get(i).setBoundaryLineColor(Color.WHITE);
                arraylist.get(i).setBackground(Color.WHITE);
            }
        }
    }//GEN-LAST:event_mouseExitBOUNDARY

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CALENDAR_Type2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_1;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_10;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_11;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_12;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_13;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_14;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_15;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_16;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_17;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_18;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_19;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_2;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_20;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_21;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_22;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_23;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_24;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_25;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_26;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_27;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_28;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_29;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_3;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_30;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_31;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_32;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_33;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_34;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_35;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_36;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_37;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_38;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_39;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_4;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_40;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_41;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_42;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_5;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_6;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_7;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_8;
    private com.arisystem.beans.boundarypanel.BoundaryPanel BOUNDARY_9;
    private com.arisystem.beans.boundarypanel.BoundaryPanel boundaryPanel1;
    private com.arisystem.beans.datawizard.DWMultiRowsObject dWMultiRowsObject1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
