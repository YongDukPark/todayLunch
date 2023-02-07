/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todaylunch;

import JAVAJDBC.MenuDBUtil;
import TableBean.TODAYLUNCH_MENU_BEAN;
import com.arisystem.beans.datawizard.DWWhereCondition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
//import java.util.Vector;

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
    //체크박스 delete시 위치정보 index 저장용 왜냐하면 이렇게 row정보들어 넣어주고 역순으로 정렬을 해줘야 삭제시 row위치정보에 혼동이 안생긴다.
    //이친구는 실제로 삭제하는 애가 아니고 삭제를 1차적으로 눌렀을경우 row정보를 날리기 위한 친구이다.
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
        
        refresh();
    }
    
    public static MENUUPDATE getInstance(){
        menuCheck = new MENUUPDATE();
        if(! menuCheck.isVisible()){
            menuCheck.setVisible(true);
        }
        return menuCheck;
    }
    
    public void refresh(){
        Vector params = new Vector();
        DWWhereCondition SearchSelect;
        
        String[] testttt = jTextField1.getText().replaceAll(" ", "").split("");
        String Query = " 1 = 1 AND( 1 = 1 ";
        
        String coulmnsType = null;
        try{
            if(!jTextField1.getText().equals("") && jComboBox1.getSelectedIndex() != 0){ //검색조건 선택하고 검색했을경우
                params.clear();
                
                if(jComboBox1.getItemAt(jComboBox1.getSelectedIndex()).equals("메뉴명")){
                    coulmnsType = "MENU_NAME";
                } else if (jComboBox1.getItemAt(jComboBox1.getSelectedIndex()).equals("가게명")){
                    coulmnsType = "MENU_STORENAME";
                } else if (jComboBox1.getItemAt(jComboBox1.getSelectedIndex()).equals("카테고리")){
                    coulmnsType = "MENU_CATE";
                } else if (jComboBox1.getItemAt(jComboBox1.getSelectedIndex()).equals("주소")){
                    coulmnsType = "MENU_ADDRESS";
                }
                for(int i = 0 ; i < testttt.length ; i++){
                    params.add("%"+testttt[i]+"%");
                }
                for(int i = 0 ; i < testttt.length ; i++){
                    Query += "AND "+coulmnsType+" LIKE ? ";
                }
                Query += ")";
                
                SearchSelect = new DWWhereCondition(Query, params);

                dWCombineTable1.setWhereContition(SearchSelect);
            } else if(jTextField1.getText().equals("") || jComboBox1.getSelectedIndex() == 0) { //검색조건 없을경우
                params.clear();
                jTextField1.setText("");
                params.add("%"+""+"%");
                SearchSelect = new DWWhereCondition("MENU_NAME like ?", params);

                dWCombineTable1.setWhereContition(SearchSelect);
            }
            dWCombineTable1.setDataSource("MariaDB_Youngria");
            dWCombineTable1.setOrderBy("MENU_SELECT_COUNT DESC");
            dWCombineTable1.select("http", "192.168.0.20", 8080);
            this.rowCount = dWCombineTable1.selectTotalRowCount("http", "192.168.0.20", 8080);
        }catch(Exception e){
            System.err.println(e);
        } 
    }
    
    public void clearExit(){
        rowCount = 0;
        deleteCount = 0;
        arraylist.clear();
        deleteListIndex.clear();
        deletelist.clear();
        map.clear();
        changeRow.clear();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dWCombineTable1 = new com.arisystem.beans.combinetable.DWCombineTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btn_SearchSelect = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();

        dWCombineTable1.setBodyRenderer(new com.arisystem.beans.combinetable.BodyRenderer( new com.arisystem.beans.combinetable.BodyCombineCell[] {
            new com.arisystem.beans.combinetable.BodyCombineCell("__ROW_STATUS__", new com.arisystem.beans.combinetable.CellInfo(0,0), new com.arisystem.beans.combinetable.CellInfo(0,0), null, com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "com.arisystem.beans.datawizard.DWStatusCombineCellEditor", "com.arisystem.beans.datawizard.DWStatusCombineCellPainter",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_NO", new com.arisystem.beans.combinetable.CellInfo(1,0), new com.arisystem.beans.combinetable.CellInfo(1,0), "new", com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "", "",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_NAME", new com.arisystem.beans.combinetable.CellInfo(2,0), new com.arisystem.beans.combinetable.CellInfo(2,0), null, com.arisystem.beans.combinetable.CombineCell.LEFT_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "com.arisystem.beans.combinetable.StringCombineCellEditor", "",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_STORENAME", new com.arisystem.beans.combinetable.CellInfo(3,0), new com.arisystem.beans.combinetable.CellInfo(3,0), null, com.arisystem.beans.combinetable.CombineCell.LEFT_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "com.arisystem.beans.combinetable.StringCombineCellEditor", "",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_ADDRESS", new com.arisystem.beans.combinetable.CellInfo(4,0), new com.arisystem.beans.combinetable.CellInfo(4,0), null, com.arisystem.beans.combinetable.CombineCell.LEFT_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "com.arisystem.beans.combinetable.StringCombineCellEditor", "",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_CATE", new com.arisystem.beans.combinetable.CellInfo(7,0), new com.arisystem.beans.combinetable.CellInfo(7,0), null, com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "com.arisystem.beans.combinetable.StringCombineCellEditor", "",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_INTRODUCTION", new com.arisystem.beans.combinetable.CellInfo(8,0), new com.arisystem.beans.combinetable.CellInfo(8,0), null, com.arisystem.beans.combinetable.CombineCell.LEFT_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "com.arisystem.beans.combinetable.StringCombineCellEditor", "",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_UPDATE_DAY", new com.arisystem.beans.combinetable.CellInfo(9,0), new com.arisystem.beans.combinetable.CellInfo(9,0), null, com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "", "com.arisystem.beans.combinetable.DateCombineCellPainter",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_SELECT_COUNT", new com.arisystem.beans.combinetable.CellInfo(5,0), new com.arisystem.beans.combinetable.CellInfo(5,0), null, com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "", "",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_CANSLE_COUNT", new com.arisystem.beans.combinetable.CellInfo(6,0), new com.arisystem.beans.combinetable.CellInfo(6,0), null, com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "", "com.arisystem.beans.combinetable.NumberCombineCellPainter",null),
        }));
        dWCombineTable1.setCellCount(10);
        dWCombineTable1.setCellWidths(new int[] {24, 26, 158, 97, 89, 34, 32, 39, 207, 88});
        dWCombineTable1.setErdObjectLocations(new com.arisystem.beans.datawizard.DWErdObjectLocation[]{new com.arisystem.beans.datawizard.DWErdObjectLocation("TODAYLUNCH_MENU",30,0)});
        dWCombineTable1.setGroupBy("");
        dWCombineTable1.setHeaderRenderer(new com.arisystem.beans.combinetable.HeaderRenderer( new com.arisystem.beans.combinetable.HeaderCombineCell[] {
            new com.arisystem.beans.combinetable.HeaderCombineCell("__ROW_STATUS__",new com.arisystem.beans.combinetable.CellInfo(0,0),new com.arisystem.beans.combinetable.CellInfo(0,0),null,com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"com.arisystem.beans.combinetable.CheckBoxCombineCellPainter",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_NO",new com.arisystem.beans.combinetable.CellInfo(1,0),new com.arisystem.beans.combinetable.CellInfo(1,0),"번호",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_NAME",new com.arisystem.beans.combinetable.CellInfo(2,0),new com.arisystem.beans.combinetable.CellInfo(2,0),"메뉴명",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_STORENAME",new com.arisystem.beans.combinetable.CellInfo(3,0),new com.arisystem.beans.combinetable.CellInfo(3,0),"가게명",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_SELECT_COUNT",new com.arisystem.beans.combinetable.CellInfo(5,0),new com.arisystem.beans.combinetable.CellInfo(5,0),"선택",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_CATE",new com.arisystem.beans.combinetable.CellInfo(7,0),new com.arisystem.beans.combinetable.CellInfo(7,0),"카테",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_UPDATE_DAY",new com.arisystem.beans.combinetable.CellInfo(9,0),new com.arisystem.beans.combinetable.CellInfo(9,0),"수정날짜",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_CANSLE_COUNT",new com.arisystem.beans.combinetable.CellInfo(6,0),new com.arisystem.beans.combinetable.CellInfo(6,0),"거절",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_INTRODUCTION",new com.arisystem.beans.combinetable.CellInfo(8,0),new com.arisystem.beans.combinetable.CellInfo(8,0),"한줄평",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_ADDRESS",new com.arisystem.beans.combinetable.CellInfo(4,0),new com.arisystem.beans.combinetable.CellInfo(4,0),"위치",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
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
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"),"MENU_INTRODUCTION",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_INTRODUCTION") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"),"MENU_ADDRESS",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_ADDRESS") });
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

    btn_SearchSelect.setText("검색");
    btn_SearchSelect.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonClickEvent(evt);
        }
    });

    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "선택(기본)", "메뉴명", "가게명", "카테고리", "주소" }));

    jTextField1.setToolTipText("검색어 입력");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(dWCombineTable1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btn_SearchSelect)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 426, Short.MAX_VALUE)
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
                .addComponent(jButton3)
                .addComponent(btn_SearchSelect)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                
                //1. 삭제할 value정보들 담아놓는곳
                //2. map에 담긴 객체 찾아서 없애주기 이친구는 고유번호가 없기에 이렇게 삭제시켜줘야한다.
                //3. rowCount에서 빼야할 row갯수만큼 값 만들어주기
                deletelist.add(arraylist.get(i));
                map.remove("new"+deleteListIndex.get(i));
                deleteCount++;
            }
            System.out.println(deleteCount + "개의 메뉴가 삭제되었습니다.");
            
            //rowcount 변동사항 적용시키기 삭제이후 토탈값을 알아야 전체 셀렉트를 할수있음 적용시키기 위해
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
            //keyvalue값으로 진행해야겠고 HashMap 형태로 키값에는 그걸(MENU_NO or newIndexNumber) 넣고 객체 형태로 넣어야겠구나
            for(int i = 0 ; i < changeRow.size() ; i++){
                if(changeRow.get(i).getMENU_NO().contains("new")){
                    MenuDBUtil.insertRow(changeRow.get(i));
                } else {
                    MenuDBUtil.updateRow(changeRow.get(i));
                }
            }
            
            //삭제 로직
            for(int i = 0 ; i < deletelist.size() ; i++){
                if(deletelist.get(i) ==  null){
                    continue;
                } else {
                    MenuDBUtil.menuDelete((String)deletelist.get(i));
                }
            }
            clearExit();
            refresh();
        } else if (evt.getSource() == btn_SearchSelect) {
            clearExit();
            refresh();
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
                        
                    } else {
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
        //체크 취소?을 경우
        if(String.valueOf(dWCombineTable1.getValue(evt.getRowIndex(), evt.getCombineCellName())).contains("false")){
            arraylist.remove(dWCombineTable1.getValue(evt.getRowIndex(), "MENU_NO"));
            
            //230125 추가된 사항 인덱스 정보를 받아 지우기 위함
            deleteListIndex.remove((Object)evt.getRowIndex());
        } else { //체크되었을 경우  
            //여기서 만약 안에 값이 있으면 또 넣지 않도록 한다.
            if(!deleteListIndex.contains(evt.getRowIndex())){
                //오류발견 newRow시 체크박스에는 체크되있는 상태이지만 체크가된게 아니다. 그러므로 여기서 벨류를 바꿀때마다 arraylist에 add가 되며
                //혼동을 준다.
                arraylist.add(dWCombineTable1.getValue(evt.getRowIndex(), "MENU_NO"));

                //230125 추가된 사항 인덱스 정보를 받아 지우기 위함
                deleteListIndex.add(evt.getRowIndex());
            }
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
        bean.setMENU_ADDRESS((String)dWCombineTable1.getValue(evt.getRowIndex(), "MENU_ADDRESS"));
        bean.setMENU_CATE((String)dWCombineTable1.getValue(evt.getRowIndex(), "MENU_CATE"));
        bean.setMENU_INTRODUCTION((String)dWCombineTable1.getValue(evt.getRowIndex(), "MENU_INTRODUCTION"));
        
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
    private javax.swing.JButton btn_SearchSelect;
    private com.arisystem.beans.combinetable.DWCombineTable dWCombineTable1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
