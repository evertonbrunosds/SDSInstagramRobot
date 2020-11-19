/*
 * This file is part of the SDSInstagramRobot Open Source Project.
 * SDSInstagramRobot is licensed under the GNU GPLv3.
 *
 * Copyright © 2020. Everton Bruno Silva dos Santos <evertonbrunogithub@yahoo.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package view;

import control.Controller;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import static model.Factory.makeBoot;
import static model.Factory.makeFreeThread;
import model.IMessage;

/**
 * Classe responsável por comportar-se como janela de conexão.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 */
public class ConnectWindow extends javax.swing.JDialog {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = -800148144115259682L;
    /**
     * Refere-se a instância singular da classe.
     */
    private static ConnectWindow instance;
    /**
     * Refere-se a mensagem de sucesso.
     */
    private final IMessage<Integer> successMessage;
    /**
     * Refere-se a mensagem de falha.
     */
    private final IMessage<Integer> failureMessage;
    /**
     * Refere-se a mensagem de total de tentativas.
     */
    private final IMessage<Integer> totalAttempts;
    /**
     * Refere-se a mensagem de estado.
     */
    private final IMessage<String> stateMessage;

    /**
     * Método responsável por exibir a janela de conexão.
     * @param parent         Refere-se ao invocador da jenela de conexão.
     * @param successMessage Refere-se a mensagem de sucesso.
     * @param failureMessage Refere-se a mensagem de falha.
     * @param totalAttempts  Refere-se a mensagem de total de tentativas.
     * @param stateMessage   Refere-se a mensagem de estado.
     */
    public static void showModal(final java.awt.Frame parent, final IMessage<Integer> successMessage,
            final IMessage<Integer> failureMessage, final IMessage<Integer> totalAttempts, final IMessage<String> stateMessage) {
        if (instance == null) {
            instance = new ConnectWindow(parent, true, successMessage, failureMessage, totalAttempts, stateMessage) {
                private static final long serialVersionUID = 1L;
                @Override
                public void dispose() {
                    instance = null;
                    super.dispose();
                }
            };
        }
        instance.textField.requestFocusInWindow();
        instance.setVisible(true);
    }

    /**
     * Método responsável efetuar conexão com perfil.
     */
    private void connect() {
        final String msg = "Antes de utilizar as demais ferramentas aguarde o login.";
        final String title = "Mensagem de Aviso";
        makeFreeThread(() -> {
            dispose();
            JOptionPane.showMessageDialog(this, msg, title, 2);
            Controller.getInstance().setBoot(makeBoot(successMessage, failureMessage, totalAttempts, stateMessage));
            Controller.getInstance().connect(textField.getText(), String.valueOf(passwordField.getPassword()));
            MainWindow.setTitleWindow(textField.getText());
        }).start();
    }

    /**
     * Construtor responsável pelo instanciamento da janela de conexão.
     * @param parent         Refere-se ao invocador da janela.
     * @param modal          Refere-se a modalidade.
     * @param successMessage Refere-se a mensagem de sucesso.
     * @param failureMessage Refere-se a mensagem de falha.
     * @param totalAttempts  Refere-se a mensagem de total de tentativas.
     * @param stateMessage   Refere-se a mensagem de estado.
     */
    private ConnectWindow(final java.awt.Frame parent, final boolean modal, final IMessage<Integer> successMessage,
            final IMessage<Integer> failureMessage, final IMessage<Integer> totalAttempts, final IMessage<String> stateMessage) {
        super(parent, modal);
        this.successMessage = successMessage;
        this.failureMessage = failureMessage;
        this.totalAttempts = totalAttempts;
        this.stateMessage = stateMessage;
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelUserName = new javax.swing.JLabel();
        labelPassword = new javax.swing.JLabel();
        textField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Conectar Perfil");
        setAlwaysOnTop(true);
        setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/x48_Size/gnome-robots-icon.png")));
        setResizable(false);

        labelUserName.setText("Usuário:");

        labelPassword.setText("Senha:");

        textField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldKeyReleased(evt);
            }
        });

        passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passwordFieldKeyReleased(evt);
            }
        });

        button.setText("Acessar");
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });
        button.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buttonKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup().addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelUserName).addComponent(labelPassword))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(button, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(passwordField, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup().addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(labelUserName).addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(labelPassword).addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20).addComponent(button).addGap(20, 20, 20)));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActionPerformed
        if (!textField.getText().equals("") && !String.valueOf(passwordField.getPassword()).equals("")) {
            connect();
        }
    }//GEN-LAST:event_buttonActionPerformed

    private void textFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !textField.getText().equals("")) {
            passwordField.requestFocusInWindow();
        }
    }//GEN-LAST:event_textFieldKeyReleased

    private void passwordFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !String.valueOf(passwordField.getPassword()).equals("")) {
            connect();
        }
    }//GEN-LAST:event_passwordFieldKeyReleased

    private void buttonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buttonKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !String.valueOf(passwordField.getPassword()).equals("")) {
            connect();
        }
    }//GEN-LAST:event_buttonKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JLabel labelUserName;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField textField;
    // End of variables declaration//GEN-END:variables
}
