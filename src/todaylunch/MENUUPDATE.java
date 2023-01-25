/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todaylunch;

import JAVAJDBC.MenuDBUtil;
import TableBean.TODAYLUNCH_MENU_BEAN;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dydej
 */
public class MENUUPDATE extends javax.swing.JFrame {

    private static MENUUPDATE menuCheck;
    
    //필요한 친구들
    
    //JDBC용도
    MenuDBUtil MenuDBUtil = new MenuDBUtil();
    //select count 전체 선택할때 length 알기용도
    int rowCount = 0;
    //delete한 횟수를 저장해서 rowCount에 빼기위한 용도
    int deleteCount = 0;
    //체크박스 정보 저장용 temp
    ArrayList<Object> arraylist = new ArrayList<>();
    //체크박스 delete시 위치정보 index 저장용
    ArrayList<Object> deleteListIndex = new ArrayList<>();
    //실제로 삭제할 Menu_No정보
    ArrayList<Object> deletelist = new ArrayList<>();
    
    //객체 정보 담기용도
    TODAYLUNCH_MENU_BEAN bean;
    
    //객체를 keyValue형태로 저장하기 위한 hashMap
    Map<String, TODAYLUNCH_MENU_BEAN> map = new HashMap<String, TODAYLUNCH_MENU_BEAN>();
    
    //변경된 객체들 정보 저장용도
    ArrayList<TODAYLUNCH_MENU_BEAN> changeRow = new ArrayList<>();
    
    private MENUUPDATE() {
        initComponents();
         try {
            dWCombineTable1.setDataSource("MariaDB_Youngria");
            dWCombineTable1.setOrderBy("MENU_SELECT_COUNT DESC");
            dWCombineTable1.select("http", "192.168.0.20", 8080);
        } catch (Exception e) {
        }
    }
    
    public static MENUUPDATE getInstance(){
        menuCheck = new MENUUPDATE();
        if(! menuCheck.isVisible()){
            menuCheck.setVisible(true);
        }
        return menuCheck;
    }
    
    public void refresh(){
        try{
            dWCombineTable1.setDataSource("MariaDB_Youngria");
            dWCombineTable1.setOrderBy("MENU_SELECT_COUNT DESC");
            dWCombineTable1.select("http", "192.168.0.20", 8080);
            //dWCombineTable1.
            this.rowCount = dWCombineTable1.selectTotalRowCount("http", "192.168.0.20", 8080);
        }catch(Exception e){
            System.err.println(e);
        } 
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dWCombineTable1 = new com.arisystem.beans.combinetable.DWCombineTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dWCombineTable1.setBodyRenderer(new com.arisystem.beans.combinetable.BodyRenderer( new com.arisystem.beans.combinetable.BodyCombineCell[] {
            new com.arisystem.beans.combinetable.BodyCombineCell("__ROW_STATUS__", new com.arisystem.beans.combinetable.CellInfo(0,0), new com.arisystem.beans.combinetable.CellInfo(0,0), null, com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "com.arisystem.beans.datawizard.DWStatusCombineCellEditor", "com.arisystem.beans.datawizard.DWStatusCombineCellPainter",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_NO", new com.arisystem.beans.combinetable.CellInfo(1,0), new com.arisystem.beans.combinetable.CellInfo(1,0), "new", com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "", "",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_NAME", new com.arisystem.beans.combinetable.CellInfo(2,0), new com.arisystem.beans.combinetable.CellInfo(2,0), null, com.arisystem.beans.combinetable.CombineCell.LEFT_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "com.arisystem.beans.combinetable.StringCombineCellEditor", "",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_STORENAME", new com.arisystem.beans.combinetable.CellInfo(3,0), new com.arisystem.beans.combinetable.CellInfo(3,0), null, com.arisystem.beans.combinetable.CombineCell.LEFT_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "com.arisystem.beans.combinetable.StringCombineCellEditor", "",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_SELECT_COUNT", new com.arisystem.beans.combinetable.CellInfo(4,0), new com.arisystem.beans.combinetable.CellInfo(4,0), null, com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "", "",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_CATE", new com.arisystem.beans.combinetable.CellInfo(6,0), new com.arisystem.beans.combinetable.CellInfo(6,0), null, com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "com.arisystem.beans.combinetable.StringCombineCellEditor", "",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_UPDATE_DAY", new com.arisystem.beans.combinetable.CellInfo(8,0), new com.arisystem.beans.combinetable.CellInfo(8,0), null, com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "com.arisystem.beans.combinetable.DateCombineCellEditor", "com.arisystem.beans.combinetable.DateCombineCellPainter",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_CANSLE_COUNT", new com.arisystem.beans.combinetable.CellInfo(5,0), new com.arisystem.beans.combinetable.CellInfo(5,0), null, com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "", "com.arisystem.beans.combinetable.NumberCombineCellPainter",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_INTRODUCTION", new com.arisystem.beans.combinetable.CellInfo(7,0), new com.arisystem.beans.combinetable.CellInfo(7,0), null, com.arisystem.beans.combinetable.CombineCell.LEFT_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "com.arisystem.beans.combinetable.StringCombineCellEditor", "",null),
        }));
        dWCombineTable1.setCellCount(9);
        dWCombineTable1.setCellWidths(new int[] {27, 29, 178, 109, 38, 36, 44, 233, 100});
        dWCombineTable1.setErdObjectLocations(new com.arisystem.beans.datawizard.DWErdObjectLocation[]{new com.arisystem.beans.datawizard.DWErdObjectLocation("TODAYLUNCH_MENU",30,0)});
        dWCombineTable1.setGroupBy("");
        dWCombineTable1.setHeaderRenderer(new com.arisystem.beans.combinetable.HeaderRenderer( new com.arisystem.beans.combinetable.HeaderCombineCell[] {
            new com.arisystem.beans.combinetable.HeaderCombineCell("__ROW_STATUS__",new com.arisystem.beans.combinetable.CellInfo(0,0),new com.arisystem.beans.combinetable.CellInfo(0,0),null,com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"com.arisystem.beans.combinetable.CheckBoxCombineCellPainter",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_NO",new com.arisystem.beans.combinetable.CellInfo(1,0),new com.arisystem.beans.combinetable.CellInfo(1,0),"번호",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_NAME",new com.arisystem.beans.combinetable.CellInfo(2,0),new com.arisystem.beans.combinetable.CellInfo(2,0),"메뉴명",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_STORENAME",new com.arisystem.beans.combinetable.CellInfo(3,0),new com.arisystem.beans.combinetable.CellInfo(3,0),"가게명",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_SELECT_COUNT",new com.arisystem.beans.combinetable.CellInfo(4,0),new com.arisystem.beans.combinetable.CellInfo(4,0),"선택",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_CATE",new com.arisystem.beans.combinetable.CellInfo(6,0),new com.arisystem.beans.combinetable.CellInfo(6,0),"카테",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_UPDATE_DAY",new com.arisystem.beans.combinetable.CellInfo(8,0),new com.arisystem.beans.combinetable.CellInfo(8,0),"수정날짜",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_CANSLE_COUNT",new com.arisystem.beans.combinetable.CellInfo(5,0),new com.arisystem.beans.combinetable.CellInfo(5,0),"거절",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_INTRODUCTION",new com.arisystem.beans.combinetable.CellInfo(7,0),new com.arisystem.beans.combinetable.CellInfo(7,0),"한줄평",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
        }));
        dWCombineTable1.setJoinConditions(new com.arisystem.beans.datawizard.DWJoinCondition[] {
            new com.arisystem.beans.datawizard.DWNotJoinCondition(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"))});
    dWCombineTable1.setMainTable(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"));
    dWCombineTable1.setOrderBy("");
    dWCombineTable1.setSelectFieldObjects(new com.arisystem.beans.datawizard.DWAliasFieldObject[]{
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"),"MENU_NO",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_NO") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"),"MENU_NAME",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_NAME") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"),"MENU_STORENAME",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_STORENAME") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"),"MENU_SELECT_COUNT",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_SELECT_COUNT") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"),"MENU_CATE",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_CATE") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"),"MENU_UPDATE_DAY",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_UPDATE_DAY") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"),"MENU_CANSLE_COUNT",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_CANSLE_COUNT") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"),"MENU_INTRODUCTION",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_INTRODUCTION") });
dWCombineTable1.setWhereContition(new com.arisystem.beans.datawizard.DWWhereCondition(""));
dWCombineTable1.addTableBodyListener(new com.arisystem.beans.combinetable.TableBodyListener() {
    public void combineTableBodyActionEditCell(com.arisystem.beans.combinetable.TableBodyEvent evt) {
    }
    public void combineTableBodyBeforeEditCell(com.arisystem.beans.combinetable.TableBodyEvent evt) {
    }
    public void combineTableBodyBeforePaintCell(com.arisystem.beans.combinetable.TableBodyEvent evt) {
    }
    public void combineTableBodyEnteringRow(com.arisystem.beans.combinetable.TableBodyEvent evt) {
    }
    public void combineTableBodyValueChanged(com.arisystem.beans.combinetable.TableBodyEvent evt) {
        tableBodyValueChange(evt);
    }
    public void combineTableHorScrolled(com.arisystem.beans.combinetable.TableBodyEvent evt) {
    }
    public void combineTableVerScrolled(com.arisystem.beans.combinetable.TableBodyEvent evt) {
    }
    public void combineTableBodyMouseClick(com.arisystem.beans.combinetable.TableBodyEvent evt) {
    }
    public void combineTableBodyMouseEnter(com.arisystem.beans.combinetable.TableBodyEvent evt) {
    }
    public void combineTableBodyMouseExit(com.arisystem.beans.combinetable.TableBodyEvent evt) {
    }
    public void combineTableBodySelectedRow(com.arisystem.beans.combinetable.TableBodyEvent evt) {
    }
    });
    dWCombineTable1.addTableHeaderListener(new com.arisystem.beans.combinetable.TableHeaderListener() {
        public void combineTableHeaderMouseClick(com.arisystem.beans.combinetable.TableHeaderEvent evt) {
            tableHeaderMouseClick(evt);
        }
        public void combineTableHeaderMouseEnter(com.arisystem.beans.combinetable.TableHeaderEvent evt) {
        }
        public void combineTableHeaderMouseExit(com.arisystem.beans.combinetable.TableHeaderEvent evt) {
        }
        public void combineTableHeaderBeforePaintCell(com.arisystem.beans.combinetable.TableHeaderEvent evt) {
        }
    });

    jButton1.setText("메뉴 추가");
    jButton1.setActionCommand("MENU_ADD");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonClickEvent(evt);
        }
    });

    jButton2.setText("삭제");
    jButton2.setActionCommand("MENU_DELETE");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonClickEvent(evt);
        }
    });

    jButton3.setText("저장");
    jButton3.setActionCommand("MENU_SAVE");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonClickEvent(evt);
        }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(dWCombineTable1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 787, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(16, 16, 16)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton1)
                .addComponent(jButton2)
                .addComponent(jButton3))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(dWCombineTable1, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
            .addContainerGap())
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonClickEvent(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClickEvent
        if(evt.getActionCommand().equals("MENU_ADD")) {
            dWCombineTable1.addRow();
            this.rowCount++;
        } else if (evt.getActionCommand().equals("MENU_DELETE")) {
            //내림차순으로 정렬
            Collections.sort(deleteListIndex, Collections.reverseOrder());
            for(int i = 0 ; i < arraylist.size() ; i++){
                //row정보 삭제
                dWCombineTable1.removeRow((int)deleteListIndex.get(i));
                System.out.println("삭제할녀석 : " + arraylist.get(i));
                System.out.println("삭제할Index : " + deleteListIndex.get(i));
                
                //삭제할 value정보들 담아놓는곳
                deletelist.add(arraylist.get(i));
                deleteCount++;
            }
            System.out.println(deleteCount + "개의 메뉴가 삭제되었습니다.");
            
            //rowcount 변동사항 적용시키기 삭제이후 토탈값을 알아야 전체 셀렉트를 할수있음 적용시키기 위해
            //this.rowCount = ;
            //System.out.println("rowcount" + dWCombineTable1.getRowCount());
            this.rowCount = rowCount-deleteCount;
            
            
            //초기화 작업
            deleteCount = 0;
            arraylist.clear();
            deleteListIndex.clear();
        } else if (evt.getActionCommand().equals("MENU_SAVE")) {
            //map 애들 가져와서 ArrayList에 넣을거임
            for( String key : map.keySet() ) {
                changeRow.add(map.get(key));
            }

            //JDBC 형태로 진행
            //로우의 값이 없을경우 insert 만약 있으면 update진행을 changeRow의 길이만큼 반복시킨다.
            //keyvalue값으로 진행해야겠고 HashMap 형태로 키값에는 그걸 넣고 객체 형태로 넣어야겠구나
            //빈형태로 넣어줘야함
            MenuDBUtil = new MenuDBUtil();
            for(int i = 0 ; i < changeRow.size() ; i++){
                System.out.println(changeRow.get(i).getMENU_NO());

                if(changeRow.get(i).getMENU_NO().contains("new")){
                    MenuDBUtil.insertRow(changeRow.get(i));
                } else {
                    MenuDBUtil.updateRow(changeRow.get(i));
                }
                

            }
            //삭제 로직
//            for(int i = 0 ; i < deletelist.size() ; i++){
//                if(deletelist.get(i) !=  null){
//                    continue;
//                } else {
//                    DataJDBC.Delete_Menu((String)deletelist.get(i));
//                }
//            }
            //select + 초기화작업
            refresh();
            changeRow.clear();
            map.clear();
            
            //deleteList
            deletelist.clear();
        }
    }//GEN-LAST:event_buttonClickEvent
    
    //테이블 헤더 정보 변경시 실질적으로 checkbox용도
    private void tableHeaderMouseClick(com.arisystem.beans.combinetable.TableHeaderEvent evt) {//GEN-FIRST:event_tableHeaderMouseClick
        if(dWCombineTable1.getHeaderTitleValue("__ROW_STATUS__").equals(true)){
            arraylist.clear();
            
            //230125 추가된 사항 인덱스 정보를 받아 지우기 위함
            deleteListIndex.clear();
        } else {
            for(int i = 0 ; i < rowCount ; i++){
                if(String.valueOf(dWCombineTable1.getValue(i, "__ROW_STATUS__")).contains("false")){
                    if(arraylist.contains(dWCombineTable1.getValue(i, "MENU_NO"))){
                        
                    }else {
                        arraylist.add(dWCombineTable1.getValue(i, "MENU_NO"));
                        
                        //230125 추가된 사항 인덱스 정보를 받아 지우기 위함
                        deleteListIndex.add(i);
                    }
                }
            }
        }
        
    }//GEN-LAST:event_tableHeaderMouseClick

    //테이블 바디부분 정보 변경시 실행
    private void tableBodyValueChange(com.arisystem.beans.combinetable.TableBodyEvent evt) {//GEN-FIRST:event_tableBodyValueChange
        //체크박스 관련
        if(String.valueOf(dWCombineTable1.getValue(evt.getRowIndex(), evt.getCombineCellName())).contains("false")){
            arraylist.remove(dWCombineTable1.getValue(evt.getRowIndex(), "MENU_NO"));
            
            //230125 추가된 사항 인덱스 정보를 받아 지우기 위함
            deleteListIndex.remove((Object)evt.getRowIndex());
        } else {
            arraylist.add(dWCombineTable1.getValue(evt.getRowIndex(), "MENU_NO"));
            
            //230125 추가된 사항 인덱스 정보를 받아 지우기 위함
            deleteListIndex.add(evt.getRowIndex());
        }
        //체크박스 관련 끝
        
        //새로운 Row 생성시 구분값 용도
        String newRow = "new"+evt.getRowIndex();
        
        //객체정보 담기용도
        bean = new TODAYLUNCH_MENU_BEAN();
        
        //고유번호 null일경우 newRow+index넘버 hashMap용도라 지금은 임의로 지정
        if(dWCombineTable1.getValue(evt.getRowIndex(), "MENU_NO") != null){
            bean.setMENU_NO((String)dWCombineTable1.getValue(evt.getRowIndex(), "MENU_NO"));
        } else {
            bean.setMENU_NO(newRow);
        }
        bean.setMENU_NAME((String)dWCombineTable1.getValue(evt.getRowIndex(), "MENU_NAME"));
        bean.setMENU_STORENAME((String)dWCombineTable1.getValue(evt.getRowIndex(), "MENU_STORENAME"));
        //bean.setMENU_SELECT_COUNT((int)dWCombineTable1.getValue(evt.getRowIndex(), "MENU_SELECT_COUNT"));
        //bean.setMENU_CANSLE_COUNT((int)dWCombineTable1.getValue(evt.getRowIndex(), "MENU_CANSLE_COUNT"));
        bean.setMENU_CATE((String)dWCombineTable1.getValue(evt.getRowIndex(), "MENU_CATE"));
        bean.setMENU_INTRODUCTION((String)dWCombineTable1.getValue(evt.getRowIndex(), "MENU_INTRODUCTION"));
        //bean.setMENU_UPDATE_DAY((String)dWCombineTable1.getValue(evt.getRowIndex(), "MENU_UPDATE_DAY"));
        
        //객체정보 HashMap에 Key Value 형태로 담아주기
        if(dWCombineTable1.getValue(evt.getRowIndex(), "MENU_NO") != null){
            map.put((String)dWCombineTable1.getValue(evt.getRowIndex(), "MENU_NO"), bean);
        } else {
            map.put(newRow, bean);
        }
        
    }//GEN-LAST:event_tableBodyValueChange

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MENUUPDATE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MENUUPDATE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MENUUPDATE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MENUUPDATE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MENUUPDATE().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.arisystem.beans.combinetable.DWCombineTable dWCombineTable1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    // End of variables declaration//GEN-END:variables
}
