/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todaylunch;

import java.awt.Color;

/**
 *
 * @author dydej
 */
public class LOGCHECK extends javax.swing.JFrame {
    
    private static LOGCHECK logCheck;
    
    private LOGCHECK() {
        initComponents();
        try {
            dWCombineTable1.setDataSource("MariaDB_Youngria");
            dWCombineTable1.setOrderBy("LAST_START_TIME DESC");
            dWCombineTable1.select("http", "192.168.0.20", 8080);
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dWCombineTable1 = new com.arisystem.beans.combinetable.DWCombineTable();

        dWCombineTable1.setBodyRenderer(new com.arisystem.beans.combinetable.BodyRenderer( new com.arisystem.beans.combinetable.BodyCombineCell[] {
            new com.arisystem.beans.combinetable.BodyCombineCell("LAST_START_TIME", new com.arisystem.beans.combinetable.CellInfo(0,0), new com.arisystem.beans.combinetable.CellInfo(0,0), null, com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "com.arisystem.beans.combinetable.DateCombineCellEditor", "com.arisystem.beans.combinetable.DateCombineCellPainter",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_SELECT", new com.arisystem.beans.combinetable.CellInfo(3,0), new com.arisystem.beans.combinetable.CellInfo(3,0), null, com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "com.arisystem.beans.combinetable.StringCombineCellEditor", "",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_NAME", new com.arisystem.beans.combinetable.CellInfo(2,0), new com.arisystem.beans.combinetable.CellInfo(2,0), null, com.arisystem.beans.combinetable.CombineCell.LEFT_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "com.arisystem.beans.combinetable.StringCombineCellEditor", "",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_NO", new com.arisystem.beans.combinetable.CellInfo(1,0), new com.arisystem.beans.combinetable.CellInfo(1,0), null, com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "com.arisystem.beans.combinetable.StringCombineCellEditor", "",null),
            new com.arisystem.beans.combinetable.BodyCombineCell("MENU_RESET_COUNT", new com.arisystem.beans.combinetable.CellInfo(4,0), new com.arisystem.beans.combinetable.CellInfo(4,0), null, com.arisystem.beans.combinetable.CombineCell.CENTER_ALIGNMENT, com.arisystem.beans.combinetable.CombineCell.HORIZONTAL,null, null, null, "com.arisystem.beans.combinetable.NumberCombineCellEditor", "com.arisystem.beans.combinetable.NumberCombineCellPainter",null),
        }));
        dWCombineTable1.setCellWidths(new int[] {100, 77, 397, 111, 109});
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
            .addComponent(dWCombineTable1, javax.swing.GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE)
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
    private void dWCombineTable1CombineTableHeaderMouseClick(com.arisystem.beans.combinetable.TableHeaderEvent evt) {//GEN-FIRST:event_dWCombineTable1CombineTableHeaderMouseClick
        //System.out.println(evt.getClickCount());
        //System.out.println(evt.getCombineCellName());
        //System.out.println(dWCombineTable1.setHeaderTitleValue(evt.getCombineCellName());
        //
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
    }//GEN-LAST:event_dWCombineTable1CombineTableHeaderMouseClick

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
