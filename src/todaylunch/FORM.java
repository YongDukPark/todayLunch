/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todaylunch;

import JAVAJDBC.MenuDBUtil;
import Material.Confirm;
import TableBean.TODAYLUNCH_MENU_BEAN;
import Material.Alert;
import com.arisystem.beans.framebuilder.FBRowSet;

/**
 *
 * @author dydej
 */
public class FORM extends javax.swing.JFrame {
    //DB 뽑아오기용도
    MenuDBUtil MenuDBUtil = new MenuDBUtil();
    TODAYLUNCH_MENU_BEAN mbean;
    ADD_MENU addmenu;
    public FORM() {
        initComponents();
        setStartData();
    }
    //최초 실행했을경우 최근 데이터가 있으면 데이터 출력
    public void setStartData(){
        try {
            FirstDataCheck1.setDataSource("MariaDB_Youngria");
            FirstDataCheck1.setOrderBy("TODAYLUNCH_TODAY_SELECT.LAST_START_TIME DESC LIMIT 1");
            FBRowSet FirstData = FirstDataCheck1.select("http", "192.168.0.20", 8080);
            
            if(FirstData.getValue(0, "MENU_SELECT").equals("Y")){
                jLabel1.setText((String)FirstData.getValue(0, "MENU_NAME"));
                jLabel4.setText((String)FirstData.getValue(0, "MENU_STORENAME"));
                jLabel6.setText((String)FirstData.getValue(0, "MENU_ADDRESS"));
                jLabel8.setText((String)FirstData.getValue(0, "MENU_INTRODUCTION"));
                jLabel10.setText((String)FirstData.getValue(0, "MENU_CATE"));
                jLabel12.setText(String.valueOf(FirstData.getValue(0, "MENU_SELECT_COUNT")));
            } else {
                jLabel1.setText("돌림판 Click ↓");
            }
        } catch (Exception e) {
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FirstDataCheck1 = new com.arisystem.beans.datawizard.DWMultiRowsObject();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel13 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        FirstDataCheck1.setErdObjectLocations(new com.arisystem.beans.datawizard.DWErdObjectLocation[]{new com.arisystem.beans.datawizard.DWErdObjectLocation("TODAYLUNCH_TODAY_SELECT",1,0),new com.arisystem.beans.datawizard.DWErdObjectLocation("TODAYLUNCH_MENU",443,0)});
        FirstDataCheck1.setJoinConditions(new com.arisystem.beans.datawizard.DWJoinCondition[] {
            new com.arisystem.beans.datawizard.DWLeftOuterJoinCondition(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_TODAY_SELECT","TODAYLUNCH_TODAY_SELECT"),new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"),new com.arisystem.beans.datawizard.DWJoinFieldPair[]{new com.arisystem.beans.datawizard.DWJoinFieldPair("MENU_NO","MENU_NO")})});
    FirstDataCheck1.setMainTable(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_TODAY_SELECT","TODAYLUNCH_TODAY_SELECT"));
    FirstDataCheck1.setOrderBy(null);
    FirstDataCheck1.setSelectFieldObjects(new com.arisystem.beans.datawizard.DWAliasFieldObject[]{
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_TODAY_SELECT","TODAYLUNCH_TODAY_SELECT"),"MENU_SELECT",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_SELECT") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_TODAY_SELECT","TODAYLUNCH_TODAY_SELECT"),"MENU_NO",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_NO") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_TODAY_SELECT","TODAYLUNCH_TODAY_SELECT"),"LAST_START_TIME",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"LAST_START_TIME") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"),"MENU_NAME",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_NAME") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"),"MENU_STORENAME",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_STORENAME") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"),"MENU_CATE",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_CATE") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"),"MENU_ADDRESS",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_ADDRESS") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"),"MENU_SELECT_COUNT",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_SELECT_COUNT") ,
        new com.arisystem.beans.datawizard.DWAliasFieldObject(new com.arisystem.beans.datawizard.DWTable("null","TODAYLUNCH_MENU","TODAYLUNCH_MENU"),"MENU_INTRODUCTION",com.arisystem.beans.datawizard.DWFieldObject.DATA_FIELD_LARGE_NORMAL,"MENU_INTRODUCTION") });
FirstDataCheck1.setWhereContition(null);

setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

jPanel1.setToolTipText("");

jButton1.setText("돌려돌려 돌림판");
jButton1.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
    }
    });

    jLabel1.setFont(new java.awt.Font("굴림", 1, 18)); // NOI18N
    jLabel1.setText(" ");

    jLabel2.setText("메뉴 :");

    jLabel3.setText("가게 :");

    jLabel4.setFont(new java.awt.Font("굴림", 1, 12)); // NOI18N

    jLabel5.setText("위치 :");

    jLabel6.setFont(new java.awt.Font("굴림", 1, 12)); // NOI18N
    jLabel6.setToolTipText("");

    jLabel7.setText("메모 :");

    jLabel9.setText("카테 :");

    jLabel10.setFont(new java.awt.Font("굴림", 1, 12)); // NOI18N

    jLabel11.setText("선택횟수 :");

    jLabel12.setFont(new java.awt.Font("굴림", 1, 12)); // NOI18N

    jList1.setModel(new javax.swing.AbstractListModel<String>() {
        String[] strings = MenuDBUtil.todayLunchList();
        public int getSize() { return strings.length; }
        public String getElementAt(int i) { return strings[i]; }
    });
    jList1.setToolTipText("");
    jList1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            jList1MouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            jList1MouseExited(evt);
        }
    });
    jScrollPane2.setViewportView(jList1);

    jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel13.setText("나올수 있는 점심 List");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel13)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane2))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGap(43, 43, 43)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(35, 35, 35)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGap(52, 52, 52)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(137, 137, 137))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGap(36, 36, 36))
    );

    jMenu1.setText("File");

    jMenuItem1.setText("메뉴 추가");
    jMenuItem1.setActionCommand("menuadd");
    jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            menuItemClickAction_File(evt);
        }
    });
    jMenu1.add(jMenuItem1);

    jMenuItem2.setText("메뉴 삭제");
    jMenuItem2.setActionCommand("menudelete");
    jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            menuItemClickAction_File(evt);
        }
    });
    jMenu1.add(jMenuItem2);

    jMenuBar1.add(jMenu1);

    jMenu2.setText("Edit");

    jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
    jMenuItem4.setText("오늘 메뉴 다시정하기");
    jMenuItem4.setActionCommand("todaySelectReset");
    jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            menuItemClickAction_Edit(evt);
        }
    });
    jMenu2.add(jMenuItem4);

    jMenuItem5.setText("메뉴 수정");
    jMenuItem5.setActionCommand("menuupdate");
    jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            menuItemClickAction_Edit(evt);
        }
    });
    jMenu2.add(jMenuItem5);

    jMenuItem6.setText("로그보기");
    jMenuItem6.setActionCommand("logcheck");
    jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            menuItemClickAction_Edit(evt);
        }
    });
    jMenu2.add(jMenuItem6);

    jMenuItem3.setText("이전 기록");
    jMenuItem3.setActionCommand("getCalendar");
    jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            menuItemClickAction_Edit(evt);
        }
    });
    jMenu2.add(jMenuItem3);

    jMenuBar1.add(jMenu2);

    setJMenuBar(jMenuBar1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    //상단바에 File 메뉴 눌렀을경우 실행되는 method
    private void menuItemClickAction_File(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemClickAction_File
        if(evt.getActionCommand().equals("menuadd")){
            addmenu = ADD_MENU.getInstance();
        } else if(evt.getActionCommand().equals("menudelete")){
            //DELETE_MENU DELETE_MENU = new DELETE_MENU();
            DELETE_MENU.getInstance();
        }
    }//GEN-LAST:event_menuItemClickAction_File
    
    //돌려돌려돌림판 누르면 Action
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //오늘 확정을 지었는지 확인하는 용도
        if(MenuDBUtil.checkYN().equals("Y")){
            Alert.getInstance("오늘 메뉴는 정해졌습니다 ㅎ");
        } else {
            MenuDBUtil = new MenuDBUtil();
            mbean = new TODAYLUNCH_MENU_BEAN();
            mbean = MenuDBUtil.spinSpinWheel();

            //log 남기는용도
            MenuDBUtil.insertLog(mbean.getMENU_NO(), mbean.getMENU_NAME());
            //시도횟수 +1
            //버튼 누르면 거기서 update로 최근 데이터 수정함
            MenuDBUtil.upCount(mbean.getMENU_NO(), mbean.getMENU_NAME());

            //메뉴이름
            jLabel1.setText(mbean.getMENU_NAME());
            //가게이름
            jLabel4.setText(mbean.getMENU_STORENAME());
            //주소
            jLabel6.setText(mbean.getMENU_ADDRESS());
            //메모
            jLabel8.setText(mbean.getMENU_INTRODUCTION());
            //카테고리
            jLabel10.setText(mbean.getMENU_CATE());
            //선택횟수
            //int to String
            jLabel12.setText(String.valueOf(mbean.getMENU_SELECT_COUNT()));

            //confirm 문구 및 메뉴 고유번호 넘기기
            Confirm.getInstance("오늘 메뉴는 이거로 하시겠습니까?", "MenuSelectUpCount", mbean.getMENU_NO());
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    
    //마우스 들어왔을경우 refresh 시키기 용도
    private void jList1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseEntered
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = MenuDBUtil.todayLunchList();
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
    }//GEN-LAST:event_jList1MouseEntered
    
    //마우스 벗어났을경우 refresh 시키기 용도
    private void jList1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseExited
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = MenuDBUtil.todayLunchList();
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
    }//GEN-LAST:event_jList1MouseExited

    //상단바 두번째 Edit 클릭했을 경우 
    private void menuItemClickAction_Edit(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemClickAction_Edit
        if(evt.getActionCommand().equals("todaySelectReset")){
            MenuDBUtil = new MenuDBUtil();
            if(MenuDBUtil.checkYN().equals("Y")){
                Confirm.getInstance("정말로 초기화 하시겠어요?", "todaySelectReset", "");
            } else {
                Alert.getInstance("룰렛먼저 돌려주세요");
            }
        } else if(evt.getActionCommand().equals("menuupdate")){
            MENUUPDATE.getInstance();
        } else if(evt.getActionCommand().equals("logcheck")){
            LOGCHECK.getInstance();
        } else if(evt.getActionCommand().equals("getCalendar")){
            CALENDAR_Type2.getInstance();
        }
    }//GEN-LAST:event_menuItemClickAction_Edit
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FORM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FORM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FORM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FORM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FORM().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.arisystem.beans.datawizard.DWMultiRowsObject FirstDataCheck1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
