package Material;

import JAVAJDBC.Material_Action;

public class Confirm extends javax.swing.JFrame {

    String message = null;
    String temp = null;
    
    Material_Action m_action;
    
    private static Confirm confirm;
    
    private Confirm(String message, String MENU_NO) {
        //저기 getInstance에서는 바로 넣을수가 없다. 그러니 이쪽으로 와서 돌려준거다.
        this.message = message;
        this.temp = MENU_NO;
        initComponents();
    }
    
    //해당 메소드 실행시키기
    public static Confirm getInstance(String message, String MENU_NO){
        confirm = new Confirm(message, MENU_NO);
        if (! confirm.isVisible()){
            confirm.setVisible(true);
        }
        return confirm;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                closeConfirm3(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeConfirm(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("굴림", 1, 14)); // NOI18N
        jLabel1.setText(message);

        jLabel2.setText("알림");

        jButton1.setText("넹");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionY(evt);
            }
        });

        jButton2.setText("이건 진짜아니야");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActionN(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 32, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeConfirm3(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_closeConfirm3
        // TODO add your handling code here:
        confirm.setVisible(false);
    }//GEN-LAST:event_closeConfirm3

    private void closeConfirm(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeConfirm
        // TODO add your handling code here:
        confirm.setVisible(false);
    }//GEN-LAST:event_closeConfirm
    
    //메뉴 확정
    private void jButton1ActionY(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionY
        m_action = new Material_Action();
        //선택된 횟수 올리기
        m_action.LunchSelectUpCount(temp);
        
        confirm.setVisible(false);
    }//GEN-LAST:event_jButton1ActionY

    private void jButtonActionN(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActionN
        confirm.setVisible(false);
    }//GEN-LAST:event_jButtonActionN

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
