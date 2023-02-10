package Material;

import JAVAJDBC.Material_Action;

public class Confirm extends javax.swing.JFrame {

    String message = null;
    String evtCommand = null;
    Object temp = null;
    
    Material_Action m_action;
    
    private static Confirm confirm;
    
    private Confirm(String message, String evtCommand, Object temp) {
        //저기 getInstance에서는 바로 넣을수가 없다. 그러니 이쪽으로 와서 돌려준거다.
        this.message = message;
        this.evtCommand = evtCommand;
        this.temp = temp;
        initComponents();
    }
    
    //해당 메소드 실행시키기
    public static Confirm getInstance(String message, String evtCommand, Object temp){
        confirm = new Confirm(message, evtCommand, temp);
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
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
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
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButton1ActionY(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionY
        if(evtCommand.equals("MenuSelectUpCount")){ //메뉴 확정
            m_action = new Material_Action();
            //선택된 횟수 올리기
            m_action.LunchSelectUpCount((String)temp);
            m_action.logSelectUpdate((String)temp);

            confirm.setVisible(false);
        } else if(evtCommand.equals("todaySelectReset")){ //오늘 메뉴 다시고르기 > Y
            m_action = new Material_Action();
            m_action.todaySelectReset();
            
            confirm.setVisible(false);
            
            Alert.getInstance("이번에는 좋은 결과 나오길 바래요 :D");
        }
    }//GEN-LAST:event_jButton1ActionY
    
    private void jButtonActionN(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActionN
        if(evtCommand.equals("MenuSelectUpCount")){ //메뉴 확정x
            m_action = new Material_Action();
            //선택되지 못한 횟수 올리기
            m_action.LunchNotSelectUpCount((String)temp);

            confirm.setVisible(false);
        } else if(evtCommand.equals("todaySelectReset")){ //오늘 메뉴 다시고르기 > N
            confirm.setVisible(false);
        }
    }//GEN-LAST:event_jButtonActionN
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
