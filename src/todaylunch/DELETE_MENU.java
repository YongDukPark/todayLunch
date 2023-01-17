package todaylunch;

import JAVAJDBC.MenuDBUtil;
import java.util.ArrayList;

public class DELETE_MENU extends javax.swing.JFrame {
    
    ArrayList<Object> arraylist = new ArrayList<>();
    MenuDBUtil MenuDBUtil = new MenuDBUtil();
    int rowCount = 0;
    
    private static DELETE_MENU DELETE_MENU;
    
    public DELETE_MENU() {
        initComponents();
        try{
            //dataSource 셋팅
            dWCombineTable2.setDataSource("MariaDB_Youngria");
            dWCombineTable2.select("http", "192.168.0.20", 8080);
            rowCount = dWCombineTable2.selectTotalRowCount("http", "192.168.0.20", 8080);
        }catch(Exception e){
            System.err.println(e);
        } 
    }
    public static DELETE_MENU getInstance(){
        DELETE_MENU = new DELETE_MENU();
        if (! DELETE_MENU.isVisible()){
            DELETE_MENU.setVisible(true);
        }
        return DELETE_MENU;
    }
    public void refresh(){
        try{
            //dataSource 셋팅
            dWCombineTable2.setDataSource("MariaDB_Youngria");
            dWCombineTable2.select("http", "192.168.0.20", 8080);
            rowCount = dWCombineTable2.selectTotalRowCount("http", "192.168.0.20", 8080);
        }catch(Exception e){
            System.err.println(e);
        } 
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        dWCombineTable2 = new com.arisystem.beans.combinetable.DWCombineTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dWCombineTable2.setBodyRenderer(new com.arisystem.beans.combinetable.BodyRenderer( new com.arisystem.beans.combinetable.BodyCombineCell[] {
            new com.arisystem.beans.combinetable.BodyCombineCell("__ROW_STATUS__", new com.arisystem.beans.combinetable.CellInfo(0,0), new com.arisystem.beans.combinetable.CellInfo(0,0), null, com.arisystem.beans.combinetable.CombineCell.LEFT_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "com.arisystem.beans.datawizard.DWStatusCombineCellEditor", "com.arisystem.beans.datawizard.DWStatusCombineCellPainter",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_NO", new com.arisystem.beans.combinetable.CellInfo(1,0), new com.arisystem.beans.combinetable.CellInfo(1,0), "", com.arisystem.beans.combinetable.CombineCell.LEFT_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, null, null,null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_NAME", new com.arisystem.beans.combinetable.CellInfo(2,0), new com.arisystem.beans.combinetable.CellInfo(2,0), null, com.arisystem.beans.combinetable.CombineCell.LEFT_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, null, null,null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_STORENAME", new com.arisystem.beans.combinetable.CellInfo(3,0), new com.arisystem.beans.combinetable.CellInfo(3,0), null, com.arisystem.beans.combinetable.CombineCell.LEFT_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, null, null,null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_SELECT_COUNT", new com.arisystem.beans.combinetable.CellInfo(4,0), new com.arisystem.beans.combinetable.CellInfo(4,0), null, com.arisystem.beans.combinetable.CombineCell.LEFT_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, null, null,null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_CATE", new com.arisystem.beans.combinetable.CellInfo(5,0), new com.arisystem.beans.combinetable.CellInfo(5,0), null, com.arisystem.beans.combinetable.CombineCell.LEFT_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, null, null,null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_UPDATE_DAY", new com.arisystem.beans.combinetable.CellInfo(6,0), new com.arisystem.beans.combinetable.CellInfo(6,0), null, com.arisystem.beans.combinetable.CombineCell.LEFT_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, null, null,null),
        }));
        dWCombineTable2.setCellCount(7);
        dWCombineTable2.setCellWidths(new int[] {37, 67, 207, 115, 125, 104, 139});
        dWCombineTable2.setErdObjectLocations(new com.arisystem.beans.datawizard.DWErdObjectLocation[]{new com.arisystem.beans.datawizard.DWErdObjectLocation("TODAYLUNCH_MENU",30,0)});
        dWCombineTable2.setGroupBy("");
        dWCombineTable2.setHeaderRenderer(new com.arisystem.beans.combinetable.HeaderRenderer( new com.arisystem.beans.combinetable.HeaderCombineCell[] {
            new com.arisystem.beans.combinetable.HeaderCombineCell("__ROW_STATUS__",new com.arisystem.beans.combinetable.CellInfo(0,0),new com.arisystem.beans.combinetable.CellInfo(0,0),null,com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"com.arisystem.beans.combinetable.CheckBoxCombineCellPainter",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_NO",new com.arisystem.beans.combinetable.CellInfo(1,0),new com.arisystem.beans.combinetable.CellInfo(1,0),"고유번호",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_NAME",new com.arisystem.beans.combinetable.CellInfo(2,0),new com.arisystem.beans.combinetable.CellInfo(2,0),"메뉴명",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_STORENAME",new com.arisystem.beans.combinetable.CellInfo(3,0),new com.arisystem.beans.combinetable.CellInfo(3,0),"가게명",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_SELECT_COUNT",new com.arisystem.beans.combinetable.CellInfo(4,0),new com.arisystem.beans.combinetable.CellInfo(4,0),"선택된 횟수",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_CATE",new com.arisystem.beans.combinetable.CellInfo(5,0),new com.arisystem.beans.combinetable.CellInfo(5,0),"카테",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
            new com.arisystem.beans.combinetable.HeaderCombineCell("MENU_UPDATE_DAY",new com.arisystem.beans.combinetable.CellInfo(6,0),new com.arisystem.beans.combinetable.CellInfo(6,0),"업로드날짜",com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT,com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null,null,null,"",null),
        }));
        dWCombineTable2.setJoinConditions(new com.arisystem.beans.datawizard.DWJoinCondition[] {
            new com.arisystem.beans.datawizard.DWNotJoinCondition(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"))});
    dWCombineTable2.setMainTable(new com.arisystem.beans.datawizard.DWTable("","TODAYLUNCH_MENU","TODAYLUNCH_MENU"));
    dWCombineTable2.setOrderBy("");
    dWCombineTable2.setSelectFieldObjects(new com.arisystem.beans.datawizard.DWAliasFieldObject[]{
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"),"MENU_NO",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_NO") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"),"MENU_NAME",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_NAME") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"),"MENU_STORENAME",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_STORENAME") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"),"MENU_SELECT_COUNT",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_SELECT_COUNT") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"),"MENU_CATE",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_CATE") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"),"MENU_UPDATE_DAY",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_UPDATE_DAY") });
dWCombineTable2.setWhereContition(new com.arisystem.beans.datawizard.DWWhereCondition(""));
dWCombineTable2.addTableBodyListener(new com.arisystem.beans.combinetable.TableBodyListener() {
    public void combineTableBodyActionEditCell(com.arisystem.beans.combinetable.TableBodyEvent evt) {
    }
    public void combineTableBodyBeforeEditCell(com.arisystem.beans.combinetable.TableBodyEvent evt) {
    }
    public void combineTableBodyBeforePaintCell(com.arisystem.beans.combinetable.TableBodyEvent evt) {
    }
    public void combineTableBodyEnteringRow(com.arisystem.beans.combinetable.TableBodyEvent evt) {
    }
    public void combineTableBodyValueChanged(com.arisystem.beans.combinetable.TableBodyEvent evt) {
        dWCombineTable2CombineTableBodyValueChanged(evt);
    }
    public void combineTableHorScrolled(com.arisystem.beans.combinetable.TableBodyEvent evt) {
    }
    public void combineTableVerScrolled(com.arisystem.beans.combinetable.TableBodyEvent evt) {
    }
    public void combineTableBodyMouseClick(com.arisystem.beans.combinetable.TableBodyEvent evt) {
        dWCombineTable2CombineTableBodyMouseClick(evt);
    }
    public void combineTableBodyMouseEnter(com.arisystem.beans.combinetable.TableBodyEvent evt) {
    }
    public void combineTableBodyMouseExit(com.arisystem.beans.combinetable.TableBodyEvent evt) {
    }
    public void combineTableBodySelectedRow(com.arisystem.beans.combinetable.TableBodyEvent evt) {
    }
    });
    dWCombineTable2.addTableHeaderListener(new com.arisystem.beans.combinetable.TableHeaderListener() {
        public void combineTableHeaderMouseClick(com.arisystem.beans.combinetable.TableHeaderEvent evt) {
            dWCombineTable2CombineTableHeaderMouseClick(evt);
        }
        public void combineTableHeaderMouseEnter(com.arisystem.beans.combinetable.TableHeaderEvent evt) {
        }
        public void combineTableHeaderMouseExit(com.arisystem.beans.combinetable.TableHeaderEvent evt) {
        }
        public void combineTableHeaderBeforePaintCell(com.arisystem.beans.combinetable.TableHeaderEvent evt) {
        }
    });

    jButton1.setText("삭제");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    jButton2.setText("새로고침");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(dWCombineTable2, javax.swing.GroupLayout.PREFERRED_SIZE, 887, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jButton1)
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton1)
                .addComponent(jButton2))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
            .addComponent(dWCombineTable2, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(14, 14, 14))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
//        for(int i = 0 ; i < dWCombineTable2.getFocusListeners().length ; i++){
//            System.out.println(dWCombineTable2.getFocusListeners());
//            System.out.println(dWCombineTable2.getFocusListeners().length);
//            dWCombineTable2.getHighlightRowInfoRows();
//        }
//        System.out.println(dWCombineTable2.getFocusListeners());
//        System.out.println(dWCombineTable2.getFocusListeners().length);
//        System.out.println(dWCombineTable2.getHighlightRowInfoRows().length);
//        System.out.println(dWCombineTable2.getGroupByCombineFieldNames().length);
//        System.out.println(dWCombineTable2.getMultipleSelectedRow().length);
//        System.out.println(dWCombineTable2.getKeyListeners().length);
        
        //System.out.println(rowCount);
        for(int i = 0 ; i < arraylist.size() ; i++){
            //System.out.println("삭제할녀석 : " + arraylist.get(i));
            MenuDBUtil.menuDelete(String.valueOf(arraylist.get(i)));
        }
        
        //refresh;
        refresh();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        refresh();
        //DELETE_MENU = new DELETE_MENU();
        //dWCombineTable2.repaint();
        //dWCombineTable2.
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void dWCombineTable2CombineTableBodyMouseClick(com.arisystem.beans.combinetable.TableBodyEvent evt) {//GEN-FIRST:event_dWCombineTable2CombineTableBodyMouseClick
//        evt.getRowIndex();
//        evt.getCombineCellName();
        System.out.println(evt.getRowIndex());
        //System.out.println(evt.getCombineCellName());
        //System.out.println(dWCombineTable2.getValue(evt.getRowIndex(), evt.getCombineCellName()));
//        dWCombineTable2.getValue(evt.getRowIndex(), evt.getCombineCellName());
        
        //arraylist.add("1");
        //arraylist.
    }//GEN-LAST:event_dWCombineTable2CombineTableBodyMouseClick
    
    //value 가 바뀌었을경우 실질적으로 이친구를 활용함
    private void dWCombineTable2CombineTableBodyValueChanged(com.arisystem.beans.combinetable.TableBodyEvent evt) {//GEN-FIRST:event_dWCombineTable2CombineTableBodyValueChanged
        // TODO add your handling code here:
//        System.out.println(evt.getRowIndex());
//        System.out.println(evt.getCombineCellName());
//        System.out.println(dWCombineTable2.getValue(evt.getRowIndex(), evt.getCombineCellName()));
        

        //체크박스 로직1 단일체크
        if(String.valueOf(dWCombineTable2.getValue(evt.getRowIndex(), evt.getCombineCellName())).contains("false")){
            System.out.println("낼름");
            arraylist.remove(dWCombineTable2.getValue(evt.getRowIndex(), "MENU_NO"));
        } else {
            System.out.println("으엑");
            arraylist.add(dWCombineTable2.getValue(evt.getRowIndex(), "MENU_NO"));
        }
        System.out.println(dWCombineTable2.getHeaderTitleValue("__ROW_STATUS__"));
        //arraylist length로 보면 되지롱 ㅎㅎㅎㅎ        
    }//GEN-LAST:event_dWCombineTable2CombineTableBodyValueChanged

    private void dWCombineTable2CombineTableHeaderMouseClick(com.arisystem.beans.combinetable.TableHeaderEvent evt) {//GEN-FIRST:event_dWCombineTable2CombineTableHeaderMouseClick
        // TODO add your handling code here:
        
        //System.out.println(evt.getCombineCellName());
        //System.out.println(evt.getCombineCellPainter());
        
        
        //System.out.println(evt.getButton());
        //System.out.println(dWCombineTable2.getHeaderRowCount());
        //System.out.println(dWCombineTable2.getHeaderTitleValue("__ROW_STATUS__"));
        
        
        
        //체크박스 로직2 단체체크
        //눌렀을때 true였을경우
        if(dWCombineTable2.getHeaderTitleValue("__ROW_STATUS__").equals(true)){
            arraylist.clear();
        } else {
            for(int i = 0 ; i < rowCount ; i++){
                if(String.valueOf(dWCombineTable2.getValue(i, "__ROW_STATUS__")).contains("false")){
                    if(arraylist.contains(dWCombineTable2.getValue(i, "MENU_NO"))){
                        
                    }else {
                        arraylist.add(dWCombineTable2.getValue(i, "MENU_NO"));
                    }
                }
            }
        }
        
        
        //System.out.println(dWCombineTable2.getHeaderCombineCell("__ROW_STATUS__"));
    }//GEN-LAST:event_dWCombineTable2CombineTableHeaderMouseClick

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.arisystem.beans.combinetable.DWCombineTable dWCombineTable2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
