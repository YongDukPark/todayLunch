/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todaylunch;

import JAVAJDBC.MenuBean;
import JAVAJDBC.MenuDBUtil;

/**
 *
 * @author dydej
 */
public class ADD_MENU extends javax.swing.JFrame {

    /**
     * Creates new form ADD_MENU
     */
    
    private static ADD_MENU addmenu;
    MenuBean mbean;
    MenuDBUtil menudbutill = new MenuDBUtil();
    
    private ADD_MENU() {
        initComponents();
    }
    
    //해당 메소드 실행시키기
    public static ADD_MENU getInstance(){
        
        addmenu = new ADD_MENU();
        if (! addmenu.isVisible()){
            addmenu.setVisible(true);
        }
        
        return addmenu;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        boundaryPanel1 = new com.arisystem.beans.boundarypanel.BoundaryPanel();
        dWFCTextField1 = new com.arisystem.beans.datawizard.field.DWFCTextField();
        dWFCTextField2 = new com.arisystem.beans.datawizard.field.DWFCTextField();
        dWFCTextField3 = new com.arisystem.beans.datawizard.field.DWFCTextField();
        dWFCTextField4 = new com.arisystem.beans.datawizard.field.DWFCTextField();
        dWFCTextField5 = new com.arisystem.beans.datawizard.field.DWFCTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("취소");
        jButton1.setActionCommand("cansel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActionPerformed(evt);
            }
        });

        boundaryPanel1.setBoundaryLineColor(new java.awt.Color(240, 240, 240));
        boundaryPanel1.setBoundaryRenderer(new com.arisystem.beans.boundarypanel.BoundaryRenderer( new com.arisystem.beans.boundarypanel.BoundaryCell[] {
            new com.arisystem.beans.boundarypanel.BoundaryCell("T_C_1_1",new com.arisystem.beans.boundarypanel.CellInfo(0,0),new com.arisystem.beans.boundarypanel.CellInfo(0,0),"메뉴",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
            new com.arisystem.beans.boundarypanel.BoundaryCell("T_C_1_2",new com.arisystem.beans.boundarypanel.CellInfo(0,1),new com.arisystem.beans.boundarypanel.CellInfo(0,1),"가게",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
            new com.arisystem.beans.boundarypanel.BoundaryCell("T_C_1_3",new com.arisystem.beans.boundarypanel.CellInfo(0,2),new com.arisystem.beans.boundarypanel.CellInfo(0,2),"카테",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
            new com.arisystem.beans.boundarypanel.BoundaryCell("T_C_1_4",new com.arisystem.beans.boundarypanel.CellInfo(0,3),new com.arisystem.beans.boundarypanel.CellInfo(0,3),"위치",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
            new com.arisystem.beans.boundarypanel.BoundaryCell("T_C_1_5",new com.arisystem.beans.boundarypanel.CellInfo(0,4),new com.arisystem.beans.boundarypanel.CellInfo(0,4),"메모",com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,false,new java.awt.Insets(0,0,0,0),null),
            new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_2_1",new com.arisystem.beans.boundarypanel.CellInfo(1,0),new com.arisystem.beans.boundarypanel.CellInfo(1,0),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
            new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_2_2",new com.arisystem.beans.boundarypanel.CellInfo(1,1),new com.arisystem.beans.boundarypanel.CellInfo(1,1),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
            new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_2_3",new com.arisystem.beans.boundarypanel.CellInfo(1,2),new com.arisystem.beans.boundarypanel.CellInfo(1,2),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
            new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_2_4",new com.arisystem.beans.boundarypanel.CellInfo(1,3),new com.arisystem.beans.boundarypanel.CellInfo(1,3),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
            new com.arisystem.beans.boundarypanel.BoundaryCell("C_C_2_5",new com.arisystem.beans.boundarypanel.CellInfo(1,4),new com.arisystem.beans.boundarypanel.CellInfo(1,4),null,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.CENTER_ALIGNMENT,com.arisystem.beans.boundarypanel.BoundaryCell.HORIZONTAL,null,null,null,null,false,com.arisystem.beans.boundarypanel.BoundaryCell.VERTICAL,new java.awt.Color(178,178,178),true,true,true,new java.awt.Insets(0,0,0,0),null),
        }));
        boundaryPanel1.setCellBackColor(new java.awt.Color(204, 204, 204));
        boundaryPanel1.setColumnCount(2);
        boundaryPanel1.setColumnWidths(new int[] {100, 232});
        boundaryPanel1.setRowCount(5);
        boundaryPanel1.setRowHeights(new int[] {25, 25, 25, 25, 25});

        dWFCTextField1.setTableFieldName("");
        boundaryPanel1.add(dWFCTextField1);
        dWFCTextField1.setBounds(100, 0, 8, 19);
        boundaryPanel1.add(dWFCTextField2);
        dWFCTextField2.setBounds(140, 50, 8, 19);
        boundaryPanel1.add(dWFCTextField3);
        dWFCTextField3.setBounds(130, 90, 8, 19);

        dWFCTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dWFCTextField4ActionPerformed(evt);
            }
        });
        boundaryPanel1.add(dWFCTextField4);
        dWFCTextField4.setBounds(130, 100, 8, 19);
        boundaryPanel1.add(dWFCTextField5);
        dWFCTextField5.setBounds(110, 110, 8, 19);

        jButton2.setText("생성");
        jButton2.setActionCommand("insert");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActionPerformed(evt);
            }
        });

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
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(boundaryPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActionPerformed
        // TODO add your handling code here:
        
        //System.out.println(evt.getActionCommand());
        
        if(evt.getActionCommand().equals("insert")){
            System.out.println("insert 진행");
            //bean 객체에 정보 담기
            mbean = new MenuBean(dWFCTextField1.getText(),dWFCTextField2.getText(),dWFCTextField3.getText(),dWFCTextField4.getText(),0,dWFCTextField5.getText(),"0");
            
            //Insert 진행
            menudbutill.menuInsert(mbean);
            
            //창 없애기
            addmenu.setVisible(false);
            
            //메뉴 다시 돌리기
        } else if(evt.getActionCommand().equals("cansel")){
            System.out.println("cansel");
            addmenu.setVisible(false);
        }
        
    }//GEN-LAST:event_jButtonActionPerformed

    private void dWFCTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dWFCTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dWFCTextField4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.arisystem.beans.boundarypanel.BoundaryPanel boundaryPanel1;
    private com.arisystem.beans.datawizard.field.DWFCTextField dWFCTextField1;
    private com.arisystem.beans.datawizard.field.DWFCTextField dWFCTextField2;
    private com.arisystem.beans.datawizard.field.DWFCTextField dWFCTextField3;
    private com.arisystem.beans.datawizard.field.DWFCTextField dWFCTextField4;
    private com.arisystem.beans.datawizard.field.DWFCTextField dWFCTextField5;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    // End of variables declaration//GEN-END:variables
}
