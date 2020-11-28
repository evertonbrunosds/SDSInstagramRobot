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
import javax.swing.JLabel;
import model.ITime;
import model.Container;
import static view.ThrowWindow.throwComments;

/**
 * Classe responsável por comportar-se como janela de intervalos.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.4
 */
public class IntervalWindow extends javax.swing.JDialog {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = -2020323350121930342L;
    /**
     * Refere-se a instância singular da janela de intervalos.
     */
    private static IntervalWindow instance;
    /**
     * Refere-se ao intervalo de disparo em milisegundos.
     */
    private static int throwIntervalMilliseconds = ITime.minutes(1);
    /**
     * Refere-se ao intervalo de disfarçe em milisegundos.
     */
    private static int disguiseIntervalMilliseconds = ITime.minutes(5);;

    /**
     * Método responsável por exibir a janela de intervalos.
     * @param parent Refere-se a janela invocadora da janela de intervalos.
     */
    public static void showModal(final java.awt.Frame parent) {
        if (instance == null) {
            instance = new IntervalWindow(parent, true);
        }
        instance.sliderThrowInterval.setValue(throwIntervalMilliseconds / ITime.SECOND / 30);
        instance.sliderDisguiseInterval.setValue(disguiseIntervalMilliseconds / ITime.MINUTE);
        instance.setVisible(true);
    }

    /**
     * Método responsável por retornar container de intervalo de disparo.
     * @return Retorna container de intervalo de disparo.
     */
    public static Container<Integer> getThrowInterval() {
        return () -> throwIntervalMilliseconds;
    }

    /**
     * Método responsável por retornar container de intervalo de disfarçe.
     * @return Retorna container de intervalo de disfarçe.
     */
    public static Container<Integer> getDisguiseInterval() {
        return () -> disguiseIntervalMilliseconds;
    }

    /**
     * Método responsável por atualizar o texto de uma label com unidades de tempo.
     * @param prefix       Refere-se ao prefixo do texto.
     * @param label        Refere-se a label a ser atualizada.
     * @param milliseconds Refere-se a a unidade de tempo.
     */
    private static void updateLabel(final String prefix, final JLabel label, final int milliseconds) {
        if (ITime.isHours(milliseconds)) {
            label.setText(prefix + ITime.Format.hours(milliseconds) + " hora(s).");
        } else if (ITime.isMinutes(milliseconds)) {
            label.setText(prefix + ITime.Format.minutes(milliseconds) + " minuto(s).");
        } else {
            label.setText(prefix + ITime.Format.seconds(milliseconds) + " segundo(s).");
        }
    }

    /**
     * Construtor responsável pelo instanciamento da janela de intervalos.
     * @param parent Refere-se ao invocador da janela.
     * @param modal  Refere-se ao modo de exibição.
     */
    private IntervalWindow(final java.awt.Frame parent, final boolean modal) {
        super(parent, modal);
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        separatorTop = new javax.swing.JSeparator();
        labelThrowInterval = new javax.swing.JLabel();
        sliderThrowInterval = new javax.swing.JSlider();
        separatorCenter = new javax.swing.JSeparator();
        labelDisguiseInterval = new javax.swing.JLabel();
        sliderDisguiseInterval = new javax.swing.JSlider();
        separatorButton = new javax.swing.JSeparator();
        btnSetDefault = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alterar Intervalos");
        setAlwaysOnTop(true);
        setIconImage(java.awt.Toolkit.getDefaultToolkit()
                .getImage(getClass().getResource("/icons/x48_Size/gnome-robots-icon.png")));
        setResizable(false);

        labelThrowInterval.setText("Intervalo de Disparo");

        sliderThrowInterval.setMaximum(10);
        sliderThrowInterval.setMinimum(1);
        sliderThrowInterval.setValue(0);
        sliderThrowInterval.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderThrowIntervalStateChanged(evt);
            }
        });

        labelDisguiseInterval.setText("Intervalo de Disfarçe");

        sliderDisguiseInterval.setMaximum(10);
        sliderDisguiseInterval.setMinimum(1);
        sliderDisguiseInterval.setValue(0);
        sliderDisguiseInterval.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderDisguiseIntervalStateChanged(evt);
            }
        });

        btnSetDefault.setText("Restaurar");
        btnSetDefault.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetDefaultActionPerformed(evt);
            }
        });

        btnConfirm.setText("Aplicar");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup().addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(separatorTop, javax.swing.GroupLayout.PREFERRED_SIZE, 400,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelThrowInterval)
                                        .addComponent(sliderThrowInterval, javax.swing.GroupLayout.PREFERRED_SIZE, 400,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(separatorCenter, javax.swing.GroupLayout.PREFERRED_SIZE, 400,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelDisguiseInterval)
                                        .addComponent(sliderDisguiseInterval, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(separatorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 400,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup().addGap(223, 223, 223).addComponent(btnSetDefault)
                                .addGap(18, 18, 18).addComponent(btnConfirm)))
                .addGap(20, 20, 20)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(20, 20, 20)
                        .addComponent(separatorTop, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5).addComponent(labelThrowInterval).addGap(5, 5, 5)
                        .addComponent(sliderThrowInterval, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(separatorCenter, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5).addComponent(labelDisguiseInterval).addGap(5, 5, 5)
                        .addComponent(sliderDisguiseInterval, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(separatorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnConfirm).addComponent(btnSetDefault))
                        .addGap(10, 10, 10)));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSetDefaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetDefaultActionPerformed
        sliderThrowInterval.setValue(2);
        sliderDisguiseInterval.setValue(5);
    }//GEN-LAST:event_btnSetDefaultActionPerformed

    private void sliderThrowIntervalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderThrowIntervalStateChanged
        final int milliseconds = ITime.seconds(sliderThrowInterval.getValue() * 30);
        updateLabel("Intervalo de Disparo: ", labelThrowInterval, milliseconds);
    }//GEN-LAST:event_sliderThrowIntervalStateChanged

    private void sliderDisguiseIntervalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderDisguiseIntervalStateChanged
        final int milliseconds = ITime.minutes(sliderDisguiseInterval.getValue());
        updateLabel("Intervalo de Disfarçe: ", labelDisguiseInterval, milliseconds);
    }//GEN-LAST:event_sliderDisguiseIntervalStateChanged

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        throwIntervalMilliseconds = ITime.seconds(sliderThrowInterval.getValue() * 30);
        disguiseIntervalMilliseconds = ITime.minutes(sliderDisguiseInterval.getValue());
        if (Controller.getInstance().isRunning()) {
            Controller.getInstance().stop();
            throwComments();
        }
        dispose();
    }//GEN-LAST:event_btnConfirmActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnSetDefault;
    private javax.swing.JLabel labelDisguiseInterval;
    private javax.swing.JLabel labelThrowInterval;
    private javax.swing.JSeparator separatorButton;
    private javax.swing.JSeparator separatorCenter;
    private javax.swing.JSeparator separatorTop;
    private javax.swing.JSlider sliderDisguiseInterval;
    private javax.swing.JSlider sliderThrowInterval;
    // End of variables declaration//GEN-END:variables
}
