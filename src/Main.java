
import helper.Bootstrap;
import helper.HelperUI;
import helper.T;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Oussama EZZIOURI
 */
public class Main extends javax.swing.JFrame {
    //private static final int PORT = 12345;        // random large port number
    private static ServerSocket s;
    
       // static initializer
    {
        /* Create and display the form */
        Bootstrap.loadConfigProperties();
        Locale.setDefault(new Locale(Bootstrap.APP_PROP.getProperty("LANG"), Bootstrap.APP_PROP.getProperty("LOCAL")));
        try {
            s = new ServerSocket(Integer.valueOf(Bootstrap.APP_PROP.getProperty("RUNNING_PORT")), 10, InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, T.echo("APP_ERR0002_APPLICATION_ENCOUNTRED_PROBLEM"), "Error !", JOptionPane.ERROR_MESSAGE);
            System.out.println(T.echo("APP_ERR0002_APPLICATION_ENCOUNTRED_PROBLEM"));
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, T.echo("APP_ERR0003_APPLICATION_ALREADY_RUNNING"), "Error !", JOptionPane.ERROR_MESSAGE);
            System.out.println(T.echo("APP_ERR0003_APPLICATION_ALREADY_RUNNING"));
            System.out.println(e.getMessage());
            System.exit(1);

        }
    }
    
    /**
     * Creates new form MainFrame
     */
    public Main() {
        
        initComponents();
        //versionLabel.setText(Bootstrap.APP_NAME + " " + Bootstrap.APP_VERSION);
        //authorLabel.setText(Bootstrap.APP_AUTHOR);
        
        //ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle");
        //System.out.println("Message in " + Locale.getDefault() + ":" + bundle.getString("greeting"));                
        
        this.setTitle(Bootstrap.APP_NAME + " " + Bootstrap.APP_VERSION);
        HelperUI.centerJFrame(this);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        int confirmed = JOptionPane.showConfirmDialog(null,
                T.echo("APP_EXIT_CONFIRM"), "Confirmation",
                JOptionPane.YES_NO_OPTION);
        if (confirmed == 0) {
            dispose();
        } else {
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//no
        }
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
                
                Bootstrap.loadConfigProperties();
                Bootstrap.InitDailyLogFile(Bootstrap.APP_PROP.getProperty("LOG_PATH"));
                Bootstrap.InitDailyDestPrintDir(Bootstrap.APP_PROP.getProperty("PRINT_DIR"));
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}