/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todaylunch;

import TableBean.TODAYLUNCH_LOG_BEAN;
import com.arisystem.beans.combinetable.CombineTableRow;
import com.arisystem.beans.combinetable.TableHeaderEvent;
import com.arisystem.beans.datawizard.DWWhereCondition;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

/**
 *
 * @author dydej
 */
public class LOGCHECK extends javax.swing.JFrame {
    
    ArrayList<TODAYLUNCH_LOG_BEAN> arraylist = new ArrayList<>();
    TODAYLUNCH_LOG_BEAN Todaylunch_Log_Bean;
    private static LOGCHECK logCheck;
    
    //정렬시 필요한 친구
    String getHeaderName = null;
    
    private LOGCHECK() {
        initComponents();
        try {
            dWCombineTable1.setDataSource("MariaDB_Youngria");
            dWCombineTable1.setOrderBy("LAST_START_TIME DESC");
            dWCombineTable1.select("http", "192.168.0.20", 8080);
            
            arrayListDataSet();
        } catch (Exception e) {
        }
    }
    private LOGCHECK(String SELECT_YEAR , String SELECT_MONTH , String SELECT_DAY) {
        initComponents();
        //where절 사용할때 필요한애들
        Vector params = new Vector();
        DWWhereCondition SearchSelect;
        try {
            System.out.println(SELECT_YEAR+"-"+SELECT_MONTH+"-"+SELECT_DAY+" 00:00:00");
            System.out.println(SELECT_YEAR+"-"+SELECT_MONTH+"-"+SELECT_DAY+" 23:59:59");
            System.out.println();
            
            
            params.add(SELECT_YEAR+"-"+SELECT_MONTH+"-"+SELECT_DAY+" 00:00:00");
            params.add(SELECT_YEAR+"-"+SELECT_MONTH+"-"+SELECT_DAY+" 23:59:59");
            SearchSelect = new DWWhereCondition("LAST_START_TIME BETWEEN ? AND ?", params);
            dWCombineTable1.setWhereContition(SearchSelect);
            
            dWCombineTable1.setDataSource("MariaDB_Youngria");
            dWCombineTable1.setOrderBy("LAST_START_TIME DESC");
            dWCombineTable1.select("http", "192.168.0.20", 8080);
            
            arrayListDataSet();
        } catch (Exception e) {
        }
    }
    
    public static LOGCHECK getInstance(){
        logCheck = new LOGCHECK();
        if(! logCheck.isVisible()){
            logCheck.setVisible(true);
        }
        return logCheck;
    }
    
    public static LOGCHECK getInstance(String SELECT_YEAR , String SELECT_MONTH , String SELECT_DAY){
        logCheck = new LOGCHECK(SELECT_YEAR, SELECT_MONTH, SELECT_DAY);
        if(! logCheck.isVisible()){
            logCheck.setVisible(true);
        }
        return logCheck;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dWCombineTable1 = new com.arisystem.beans.combinetable.DWCombineTable();

        dWCombineTable1.setBodyRenderer(new com.arisystem.beans.combinetable.BodyRenderer( new com.arisystem.beans.combinetable.BodyCombineCell[] {
            new com.arisystem.beans.combinetable.BodyCombineCell("LAST_START_TIME", new com.arisystem.beans.combinetable.CellInfo(0,0), new com.arisystem.beans.combinetable.CellInfo(0,0), null, com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "", "",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_SELECT", new com.arisystem.beans.combinetable.CellInfo(3,0), new com.arisystem.beans.combinetable.CellInfo(3,0), null, com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "", "",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_NAME", new com.arisystem.beans.combinetable.CellInfo(2,0), new com.arisystem.beans.combinetable.CellInfo(2,0), null, com.arisystem.beans.combinetable.CombineCell.LEFT_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "", "",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_NO", new com.arisystem.beans.combinetable.CellInfo(1,0), new com.arisystem.beans.combinetable.CellInfo(1,0), null, com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "", "",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_RESET_COUNT", new com.arisystem.beans.combinetable.CellInfo(4,0), new com.arisystem.beans.combinetable.CellInfo(4,0), null, com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "", "com.arisystem.beans.combinetable.NumberCombineCellPainter",null),
        }));
        dWCombineTable1.setCellWidths(new int[] {143, 71, 374, 104, 102});
        dWCombineTable1.setErdObjectLocations(new com.arisystem.beans.datawizard.DWErdObjectLocation[]{new com.arisystem.beans.datawizard.DWErdObjectLocation("TODAYLUNCH_LOG",30,0)});
        dWCombineTable1.setGroupBy("");
        dWCombineTable1.setHeaderRenderer(new com.arisystem.beans.combinetable.HeaderRenderer( new com.arisystem.beans.combinetable.HeaderCombineCell[] {
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_NO",new com.arisystem.beans.combinetable.CellInfo(1,0),new com.arisystem.beans.combinetable.CellInfo(1,0),"고유번호",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_NAME",new com.arisystem.beans.combinetable.CellInfo(2,0),new com.arisystem.beans.combinetable.CellInfo(2,0),"메뉴명",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_SELECT",new com.arisystem.beans.combinetable.CellInfo(3,0),new com.arisystem.beans.combinetable.CellInfo(3,0),"선택여부",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_RESET_COUNT",new com.arisystem.beans.combinetable.CellInfo(4,0),new com.arisystem.beans.combinetable.CellInfo(4,0),"결정 RESET 횟수",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("LAST_START_TIME",new com.arisystem.beans.combinetable.CellInfo(0,0),new com.arisystem.beans.combinetable.CellInfo(0,0),"로그생성시간",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
        }));
        dWCombineTable1.setJoinConditions(new com.arisystem.beans.datawizard.DWJoinCondition[] {
            new com.arisystem.beans.datawizard.DWNotJoinCondition(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_LOG","TODAYLUNCH_LOG"))});
    dWCombineTable1.setMainTable(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_LOG","TODAYLUNCH_LOG"));
    dWCombineTable1.setOrderBy("");
    dWCombineTable1.setSelectFieldObjects(new com.arisystem.beans.datawizard.DWAliasFieldObject[]{
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_LOG","TODAYLUNCH_LOG"),"LAST_START_TIME",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"LAST_START_TIME") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_LOG","TODAYLUNCH_LOG"),"MENU_SELECT",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_SELECT") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_LOG","TODAYLUNCH_LOG"),"MENU_NAME",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_NAME") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_LOG","TODAYLUNCH_LOG"),"MENU_NO",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_NO") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_LOG","TODAYLUNCH_LOG"),"MENU_RESET_COUNT",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_RESET_COUNT") });
dWCombineTable1.setWhereContition(new com.arisystem.beans.datawizard.DWWhereCondition(""));
dWCombineTable1.addTableHeaderListener(new com.arisystem.beans.combinetable.TableHeaderListener() {
    public void combineTableHeaderMouseClick(com.arisystem.beans.combinetable.TableHeaderEvent evt) {
        dWCombineTable1CombineTableHeaderMouseClick(evt);
    }
    public void combineTableHeaderMouseEnter(com.arisystem.beans.combinetable.TableHeaderEvent evt) {
    }
    public void combineTableHeaderMouseExit(com.arisystem.beans.combinetable.TableHeaderEvent evt) {
    }
    public void combineTableHeaderBeforePaintCell(com.arisystem.beans.combinetable.TableHeaderEvent evt) {
    }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(dWCombineTable1, javax.swing.GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE)
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(dWCombineTable1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
            .addContainerGap())
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    
    //주목적 정렬
    //이전 type1 에서 type2로 교체한 이유
    //정렬시마다 select를 날리는 부분을 해결하기 위해 arraylist를 정렬하는 방식을 채용했다.
    //이후 type3를 만들게 된다면 최초 select시 실행시간을 좀더 소비해서 각 칼럼들의 정렬된 데이터를 여러개의 arraylist에 담은후
    //정렬시키는 시간을 없애고 바로 list를 불러오는 방식을 만들거같다.
    private void dWCombineTable1CombineTableHeaderMouseClick(com.arisystem.beans.combinetable.TableHeaderEvent evt) {//GEN-FIRST:event_dWCombineTable1CombineTableHeaderMouseClick
        //sortType1(evt);
        sortType2(evt);
        
    }//GEN-LAST:event_dWCombineTable1CombineTableHeaderMouseClick
    public void arrayListDataSet(){
        for(int i = 0 ; i < dWCombineTable1.getRowCount() ; i++){
            Todaylunch_Log_Bean = new TODAYLUNCH_LOG_BEAN();
            Todaylunch_Log_Bean.setMENU_NO((String)dWCombineTable1.getRow(i).getValue("MENU_NO"));
            Todaylunch_Log_Bean.setMENU_NAME((String)dWCombineTable1.getRow(i).getValue("MENU_NAME"));
            Todaylunch_Log_Bean.setMENU_SELECT((String)dWCombineTable1.getRow(i).getValue("MENU_SELECT"));
            Todaylunch_Log_Bean.setLAST_START_TIME(String.valueOf(dWCombineTable1.getRow(i).getValue("LAST_START_TIME")));
            Todaylunch_Log_Bean.setMENU_RESET_COUNT(String.valueOf(dWCombineTable1.getRow(i).getValue("MENU_RESET_COUNT")));
            arraylist.add(Todaylunch_Log_Bean);
        }
    }
    public void sortType1(TableHeaderEvent evt){
        //System.out.println(evt.getClickCount()); // 클릭한 횟수 불러옴
        //System.out.println(evt.getCombineCellName()); // 그 LAST_START_TIME 요런 value값 불러옴
        //System.out.println(dWCombineTable1.setHeaderTitleValue(evt.getCombineCellName()); // 텍스트 이름 넣은거 불러옴
        if(evt.getClickCount() > 0){
            try {
                if(evt.getCombineCellName().equals("LAST_START_TIME")){ //로그생성시간
                    dWCombineTable1.setHeaderTitleValue("MENU_NO", "고유번호");
                    dWCombineTable1.setHeaderTitleValue("MENU_NAME", "메뉴명");
                    dWCombineTable1.setHeaderTitleValue("MENU_SELECT", "선택여부");
                    dWCombineTable1.setHeaderTitleValue("MENU_RESET_COUNT", "결정 RESET 횟수");
                    if(dWCombineTable1.getHeaderTitleValue(evt.getCombineCellName()).equals("로그생성시간")){
                        dWCombineTable1.setDataSource("MariaDB_Youngria");
                        dWCombineTable1.setOrderBy("LAST_START_TIME DESC");
                        dWCombineTable1.select("http", "192.168.0.20", 8080);
                        dWCombineTable1.setHeaderTitleValue(evt.getCombineCellName(), "로그생성시간↓");
                    } else if(dWCombineTable1.getHeaderTitleValue(evt.getCombineCellName()).equals("로그생성시간↓")) {
                        dWCombineTable1.setDataSource("MariaDB_Youngria");
                        dWCombineTable1.setOrderBy("LAST_START_TIME ASC");
                        dWCombineTable1.select("http", "192.168.0.20", 8080);
                        dWCombineTable1.setHeaderTitleValue(evt.getCombineCellName(), "로그생성시간↑");
                    } else if(dWCombineTable1.getHeaderTitleValue(evt.getCombineCellName()).equals("로그생성시간↑")) {
                        dWCombineTable1.setDataSource("MariaDB_Youngria");
                        dWCombineTable1.setOrderBy("LAST_START_TIME");
                        dWCombineTable1.select("http", "192.168.0.20", 8080);
                        dWCombineTable1.setHeaderTitleValue(evt.getCombineCellName(), "로그생성시간");
                    }
                } else if (evt.getCombineCellName().equals("MENU_NO")){ //고유번호
                    dWCombineTable1.setHeaderTitleValue("LAST_START_TIME", "로그생성시간");
                    dWCombineTable1.setHeaderTitleValue("MENU_NAME", "메뉴명");
                    dWCombineTable1.setHeaderTitleValue("MENU_SELECT", "선택여부");
                    dWCombineTable1.setHeaderTitleValue("MENU_RESET_COUNT", "결정 RESET 횟수");
                    if(dWCombineTable1.getHeaderTitleValue(evt.getCombineCellName()).equals("고유번호")){
                        dWCombineTable1.setDataSource("MariaDB_Youngria");
                        dWCombineTable1.setOrderBy("MENU_NO DESC");
                        dWCombineTable1.select("http", "192.168.0.20", 8080);
                        dWCombineTable1.setHeaderTitleValue(evt.getCombineCellName(), "고유번호↓");
                    } else if(dWCombineTable1.getHeaderTitleValue(evt.getCombineCellName()).equals("고유번호↓")) {
                        dWCombineTable1.setDataSource("MariaDB_Youngria");
                        dWCombineTable1.setOrderBy("MENU_NO ASC");
                        dWCombineTable1.select("http", "192.168.0.20", 8080);
                        dWCombineTable1.setHeaderTitleValue(evt.getCombineCellName(), "고유번호↑");
                    } else if(dWCombineTable1.getHeaderTitleValue(evt.getCombineCellName()).equals("고유번호↑")) {
                        dWCombineTable1.setDataSource("MariaDB_Youngria");
                        dWCombineTable1.setOrderBy("MENU_NO");
                        dWCombineTable1.select("http", "192.168.0.20", 8080);
                        dWCombineTable1.setHeaderTitleValue(evt.getCombineCellName(), "고유번호");
                    }
                } else if (evt.getCombineCellName().equals("MENU_NAME")){ //상품명
                    dWCombineTable1.setHeaderTitleValue("LAST_START_TIME", "로그생성시간");
                    dWCombineTable1.setHeaderTitleValue("MENU_NO", "고유번호");
                    dWCombineTable1.setHeaderTitleValue("MENU_SELECT", "선택여부");
                    dWCombineTable1.setHeaderTitleValue("MENU_RESET_COUNT", "결정 RESET 횟수");
                    if(dWCombineTable1.getHeaderTitleValue(evt.getCombineCellName()).equals("메뉴명")){
                        dWCombineTable1.setDataSource("MariaDB_Youngria");
                        dWCombineTable1.setOrderBy("MENU_NAME DESC");
                        dWCombineTable1.select("http", "192.168.0.20", 8080);
                        dWCombineTable1.setHeaderTitleValue(evt.getCombineCellName(), "메뉴명↓");
                    } else if(dWCombineTable1.getHeaderTitleValue(evt.getCombineCellName()).equals("메뉴명↓")) {
                        dWCombineTable1.setDataSource("MariaDB_Youngria");
                        dWCombineTable1.setOrderBy("MENU_NAME ASC");
                        dWCombineTable1.select("http", "192.168.0.20", 8080);
                        dWCombineTable1.setHeaderTitleValue(evt.getCombineCellName(), "메뉴명↑");
                    } else if(dWCombineTable1.getHeaderTitleValue(evt.getCombineCellName()).equals("메뉴명↑")) {
                        dWCombineTable1.setDataSource("MariaDB_Youngria");
                        dWCombineTable1.setOrderBy("MENU_NAME");
                        dWCombineTable1.select("http", "192.168.0.20", 8080);
                        dWCombineTable1.setHeaderTitleValue(evt.getCombineCellName(), "메뉴명");
                    }
                } else if (evt.getCombineCellName().equals("MENU_SELECT")){ //선택 여부
                    dWCombineTable1.setHeaderTitleValue("LAST_START_TIME", "로그생성시간");
                    dWCombineTable1.setHeaderTitleValue("MENU_NO", "고유번호");
                    dWCombineTable1.setHeaderTitleValue("MENU_NAME", "메뉴명");
                    dWCombineTable1.setHeaderTitleValue("MENU_RESET_COUNT", "결정 RESET 횟수");
                    if(dWCombineTable1.getHeaderTitleValue(evt.getCombineCellName()).equals("선택여부")){
                        dWCombineTable1.setDataSource("MariaDB_Youngria");
                        dWCombineTable1.setOrderBy("MENU_SELECT DESC");
                        dWCombineTable1.select("http", "192.168.0.20", 8080);
                        dWCombineTable1.setHeaderTitleValue(evt.getCombineCellName(), "선택여부↓");
                    } else if(dWCombineTable1.getHeaderTitleValue(evt.getCombineCellName()).equals("선택여부↓")) {
                        dWCombineTable1.setDataSource("MariaDB_Youngria");
                        dWCombineTable1.setOrderBy("MENU_SELECT ASC");
                        dWCombineTable1.select("http", "192.168.0.20", 8080);
                        dWCombineTable1.setHeaderTitleValue(evt.getCombineCellName(), "선택여부↑");
                    } else if(dWCombineTable1.getHeaderTitleValue(evt.getCombineCellName()).equals("선택여부↑")) {
                        dWCombineTable1.setDataSource("MariaDB_Youngria");
                        dWCombineTable1.setOrderBy("MENU_SELECT");
                        dWCombineTable1.select("http", "192.168.0.20", 8080);
                        dWCombineTable1.setHeaderTitleValue(evt.getCombineCellName(), "선택여부");
                    }
                } else if (evt.getCombineCellName().equals("MENU_RESET_COUNT")){ //reset 횟수
                    dWCombineTable1.setHeaderTitleValue("LAST_START_TIME", "로그생성시간");
                    dWCombineTable1.setHeaderTitleValue("MENU_NO", "고유번호");
                    dWCombineTable1.setHeaderTitleValue("MENU_NAME", "메뉴명");
                    dWCombineTable1.setHeaderTitleValue("MENU_SELECT", "선택여부");
                    if(dWCombineTable1.getHeaderTitleValue(evt.getCombineCellName()).equals("결정 RESET 횟수")){
                        dWCombineTable1.setDataSource("MariaDB_Youngria");
                        dWCombineTable1.setOrderBy("MENU_RESET_COUNT DESC");
                        dWCombineTable1.select("http", "192.168.0.20", 8080);
                        dWCombineTable1.setHeaderTitleValue(evt.getCombineCellName(), "결정 RESET 횟수↓");
                    } else if(dWCombineTable1.getHeaderTitleValue(evt.getCombineCellName()).equals("결정 RESET 횟수↓")) {
                        dWCombineTable1.setDataSource("MariaDB_Youngria");
                        dWCombineTable1.setOrderBy("MENU_RESET_COUNT ASC");
                        dWCombineTable1.select("http", "192.168.0.20", 8080);
                        dWCombineTable1.setHeaderTitleValue(evt.getCombineCellName(), "결정 RESET 횟수↑");
                    } else if(dWCombineTable1.getHeaderTitleValue(evt.getCombineCellName()).equals("결정 RESET 횟수↑")) {
                        dWCombineTable1.setDataSource("MariaDB_Youngria");
                        dWCombineTable1.setOrderBy("MENU_RESET_COUNT");
                        dWCombineTable1.select("http", "192.168.0.20", 8080);
                        dWCombineTable1.setHeaderTitleValue(evt.getCombineCellName(), "결정 RESET 횟수");
                    }
                }
            } catch (Exception e) {
            }
        }
    }
    
    //정렬시키는 코드 이방법 채택한 이유는 select를 여러번 날리지 않아도 된다.
    public void sortType2(TableHeaderEvent evt){
        //밀어버린다음에 다시 셋팅하기 ㅎ
        dWCombineTable1.removeAllRows();
        //정렬 기준으로 나누는 친구
        getHeaderName = evt.getCombineCellName();
        
        //정렬시키는 코드
        Collections.sort(arraylist, new Comparator<TODAYLUNCH_LOG_BEAN>() {
            public int compare(TODAYLUNCH_LOG_BEAN o1, TODAYLUNCH_LOG_BEAN o2) {
                if(getHeaderName.equals("LAST_START_TIME")){ //로그 생성시간
                    if(dWCombineTable1.getHeaderTitleValue(evt.getCombineCellName()).equals("로그생성시간")){
                        return o1.getLAST_START_TIME().compareTo(o2.getLAST_START_TIME());
                    } else {
                        return o2.getLAST_START_TIME().compareTo(o1.getLAST_START_TIME());
                    }
                } else if(getHeaderName.equals("MENU_NO")) { //고유번호
                    if(dWCombineTable1.getHeaderTitleValue(evt.getCombineCellName()).equals("고유번호")){
                        return Integer.parseInt(o1.getMENU_NO()) - Integer.parseInt(o2.getMENU_NO());
                    } else {
                        return Integer.parseInt(o2.getMENU_NO()) - Integer.parseInt(o1.getMENU_NO());
                    }
                } else if(getHeaderName.equals("MENU_NAME")) { //메뉴명
                    if(dWCombineTable1.getHeaderTitleValue(evt.getCombineCellName()).equals("메뉴명")){
                        return o1.getMENU_NAME().compareTo(o2.getMENU_NAME());
                    } else {
                        return o2.getMENU_NAME().compareTo(o1.getMENU_NAME());
                    }
                } else if(getHeaderName.equals("MENU_SELECT")) { //Y or N 여부
                    if(dWCombineTable1.getHeaderTitleValue(evt.getCombineCellName()).equals("선택여부")){
                        return o1.getMENU_SELECT().compareTo(o2.getMENU_SELECT());
                    } else {
                        return o2.getMENU_SELECT().compareTo(o1.getMENU_SELECT());
                    }
                } else if(getHeaderName.equals("MENU_RESET_COUNT")) {
                    if(dWCombineTable1.getHeaderTitleValue(evt.getCombineCellName()).equals("결정 RESET 횟수")){
                        return Integer.parseInt(o1.getMENU_RESET_COUNT()) - Integer.parseInt(o2.getMENU_RESET_COUNT());
                    } else {
                        return Integer.parseInt(o2.getMENU_RESET_COUNT()) - Integer.parseInt(o1.getMENU_RESET_COUNT());
                    }
                } else {
                    return o1.getLAST_START_TIME().compareTo(o2.getLAST_START_TIME());
                }
            }
        });
        
        //이름 변경해주는 부분
        if(getHeaderName.equals("LAST_START_TIME")){ //로그 생성시간
            dWCombineTable1.setHeaderTitleValue("MENU_NO", "고유번호");
            dWCombineTable1.setHeaderTitleValue("MENU_NAME", "메뉴명");
            dWCombineTable1.setHeaderTitleValue("MENU_SELECT", "선택여부");
            dWCombineTable1.setHeaderTitleValue("MENU_RESET_COUNT", "결정 RESET 횟수");
            if(dWCombineTable1.getHeaderTitleValue(evt.getCombineCellName()).equals("로그생성시간")){
                dWCombineTable1.setHeaderTitleValue(evt.getCombineCellName(), "로그생성시간↓");
            } else {
                dWCombineTable1.setHeaderTitleValue(evt.getCombineCellName(), "로그생성시간");
            }
        } else if(getHeaderName.equals("MENU_NO")) { //고유번호
            dWCombineTable1.setHeaderTitleValue("LAST_START_TIME", "로그생성시간");
            dWCombineTable1.setHeaderTitleValue("MENU_NAME", "메뉴명");
            dWCombineTable1.setHeaderTitleValue("MENU_SELECT", "선택여부");
            dWCombineTable1.setHeaderTitleValue("MENU_RESET_COUNT", "결정 RESET 횟수");
            if(dWCombineTable1.getHeaderTitleValue(evt.getCombineCellName()).equals("고유번호")){
                dWCombineTable1.setHeaderTitleValue(evt.getCombineCellName(), "고유번호↓");
            } else {
                dWCombineTable1.setHeaderTitleValue(evt.getCombineCellName(), "고유번호");
            }
        } else if(getHeaderName.equals("MENU_NAME")) { //메뉴명
            dWCombineTable1.setHeaderTitleValue("LAST_START_TIME", "로그생성시간");
            dWCombineTable1.setHeaderTitleValue("MENU_NO", "고유번호");
            dWCombineTable1.setHeaderTitleValue("MENU_SELECT", "선택여부");
            dWCombineTable1.setHeaderTitleValue("MENU_RESET_COUNT", "결정 RESET 횟수");
            if(dWCombineTable1.getHeaderTitleValue(evt.getCombineCellName()).equals("메뉴명")){
                dWCombineTable1.setHeaderTitleValue(evt.getCombineCellName(), "메뉴명↓");
            } else {
                dWCombineTable1.setHeaderTitleValue(evt.getCombineCellName(), "메뉴명");
            }
        } else if(getHeaderName.equals("MENU_SELECT")) { //Y or N 여부
            dWCombineTable1.setHeaderTitleValue("LAST_START_TIME", "로그생성시간");
            dWCombineTable1.setHeaderTitleValue("MENU_NO", "고유번호");
            dWCombineTable1.setHeaderTitleValue("MENU_NAME", "메뉴명");
            dWCombineTable1.setHeaderTitleValue("MENU_RESET_COUNT", "결정 RESET 횟수");
            if(dWCombineTable1.getHeaderTitleValue(evt.getCombineCellName()).equals("선택여부")){
                dWCombineTable1.setHeaderTitleValue(evt.getCombineCellName(), "선택여부↓");
            } else {
                dWCombineTable1.setHeaderTitleValue(evt.getCombineCellName(), "선택여부");
            }
        } else if(getHeaderName.equals("MENU_RESET_COUNT")) {
            dWCombineTable1.setHeaderTitleValue("LAST_START_TIME", "로그생성시간");
            dWCombineTable1.setHeaderTitleValue("MENU_NO", "고유번호");
            dWCombineTable1.setHeaderTitleValue("MENU_NAME", "메뉴명");
            dWCombineTable1.setHeaderTitleValue("MENU_SELECT", "선택여부");
            if(dWCombineTable1.getHeaderTitleValue(evt.getCombineCellName()).equals("결정 RESET 횟수")){
                dWCombineTable1.setHeaderTitleValue("MENU_RESET_COUNT", "결정 RESET 횟수↓");
            } else {
                dWCombineTable1.setHeaderTitleValue("MENU_RESET_COUNT", "결정 RESET 횟수");
            }
        }
        
        //데이터 셋팅
        for(int i = 0 ; i < arraylist.size() ; i++){
            dWCombineTable1.addRow();
            dWCombineTable1.setValue(i, "MENU_NO", arraylist.get(i).getMENU_NO());
            dWCombineTable1.setValue(i, "MENU_NAME", arraylist.get(i).getMENU_NAME());
            dWCombineTable1.setValue(i, "MENU_SELECT", arraylist.get(i).getMENU_SELECT());
            dWCombineTable1.setValue(i, "MENU_RESET_COUNT", arraylist.get(i).getMENU_RESET_COUNT());
            dWCombineTable1.setValue(i, "LAST_START_TIME", arraylist.get(i).getLAST_START_TIME());
        }
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LOGCHECK().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.arisystem.beans.combinetable.DWCombineTable dWCombineTable1;
    // End of variables declaration//GEN-END:variables

    
}
