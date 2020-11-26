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
import model.IRange;
import static model.Factory.makeRange;

/**
 * Classe responsável por comportar-se como janela de disparo.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.3
 */
public class ThrowWindow extends javax.swing.JDialog {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = 8494892845952512403L;
    /**
     * Refere-se a instância singular da classe.
     */
    private static ThrowWindow instance;

    /**
     * Método responsável por exibir a janela de disparo.
     * @param parent Refere-se ao invocador da jenela de disparo.
     */
    public static void showModal(final java.awt.Frame parent) {
        if (instance == null) {
            instance = new ThrowWindow(parent, true);
        }
        instance.textField.requestFocusInWindow();
        instance.setVisible(true);
    }

    /**
     * Método responsável por verificar se o URL informado pelo usuário está correto.
     * @throws Exception Exceção genérica lançada no caso do URL ser incorreto.
     */
    private void correctValue() throws Exception {
        if (!textField.getText().toLowerCase().contains("instagram.com/p/")) {
            throw new Exception();
        }
    }

    /**
     * Método responsável por disparar comentários massivos.
     */
    private void throwComments() {
        try {
            correctValue();
            dispose();
            Controller.getInstance().loadPage(textField.getText());
            final IRange<Integer> throwInterval = makeRange(() -> 10, () -> 20);
            final IRange<Integer> disguiseInterval = makeRange(() -> 2, () -> 5);
            Controller.getInstance().run(CommentsWindow.getContainer(), throwInterval, disguiseInterval);
        } catch (final Exception ex) {
            JOptionPane.showMessageDialog(this, "Tente uma Postagem do Instagram.", "Mensagem de Erro!", 2);
        }
    }

    /**
     * Construtor responsável pelo instanciamento da janela de disparo.
     * @param parent Refere-se ao invocador da jenela.
     * @param modal  Refere-se a modalidade de invocação.
     */
    private ThrowWindow(final java.awt.Frame parent, final boolean modal) {
        super(parent, modal);
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label = new javax.swing.JLabel();
        textField = new javax.swing.JTextField();
        button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Disparar Comentários");
        setAlwaysOnTop(true);
        setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/x48_Size/gnome-robots-icon.png")));
        setResizable(false);

        label.setText("Postagem:");

        textField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldKeyReleased(evt);
            }
        });

        button.setText("Disparar");
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
                .createSequentialGroup().addGap(10, 10, 10).addComponent(label).addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(button, javax.swing.GroupLayout.Alignment.TRAILING).addComponent(textField,
                                javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 500,
                                javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label))
                        .addGap(10, 10, 10).addComponent(button).addGap(10, 10, 10)));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            throwComments();
        }
    }//GEN-LAST:event_textFieldKeyReleased

    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActionPerformed
        throwComments();
    }//GEN-LAST:event_buttonActionPerformed

    private void buttonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buttonKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            throwComments();
        }
    }//GEN-LAST:event_buttonKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button;
    private javax.swing.JLabel label;
    private javax.swing.JTextField textField;
    // End of variables declaration//GEN-END:variables
}
