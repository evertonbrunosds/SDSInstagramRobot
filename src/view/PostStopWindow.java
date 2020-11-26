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

import java.awt.event.KeyEvent;

/**
 * Classe responsável por comportar-se como janela de interrupção.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.3
 */
public class PostStopWindow extends javax.swing.JDialog {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = -849610571291578003L;
    /**
     * Refere-se a instância singular da janela de interrupção.
     */
    private static PostStopWindow instance;

    /**
     * Construtor responsável pelo instanciamento da janela de interrupção.
     * @param parent Refere-se ao invocador da jenela.
     * @param modal  Refere-se a modalidade de invocação.
     */
    private PostStopWindow(final java.awt.Frame parent, final boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * Método responsável por indicar que uma interrupção deve ser informada.
     * @return Retorna indicativo de que uma interrupção deve ser informada.
     */
    public static boolean inform() {
        return (instance == null) ? false : instance.radioButtonInform.isSelected();
    }

    /**
     * Método responsável por retornar um limite de tentativas.
     * @return Retorna o limite de tentativas.
     */
    public static int attemptsLimit() {
        try {
            return Integer.parseInt(instance.textFieldAttempts.getText());
        } catch (final NullPointerException | NumberFormatException ex) {
            return 0;
        }
    }

    /**
     * Método responsável por retornar um limite de falhas.
     * @return Retorna o limite de falhas.
     */
    public static int failureLimit() {
        try {
            return Integer.parseInt(instance.textFieldFailure.getText());
        } catch (final NullPointerException | NumberFormatException ex) {
            return 0;
        }
    }

    /**
     * Método responsável por retornar um limite de sucessos.
     * @return Retorna o limite de sucessos.
     */
    public static int successLimit() {
        try {
            return Integer.parseInt(instance.textFieldSuccess.getText());
        } catch (final NullPointerException | NumberFormatException ex) {
            return 0;
        }
    }

    /**
     * Método responsável por desativar todos os campos de texto.
     */
    private void enableFalseAllTextField() {
        textFieldAttempts.setEnabled(false);
        textFieldFailure.setEnabled(false);
        textFieldSuccess.setEnabled(false);
    }

    /**
     * Método responsável por validar números frente a outros caracteres.
     * @param evt Refere-se ao evento originário.
     */
    private static void validateAction(final java.awt.event.KeyEvent evt) {
        if (!(KeyEvent.VK_0 <= evt.getKeyChar() && evt.getKeyChar() <= KeyEvent.VK_9))
            evt.consume();
    }

    /**
     * Método responsável por filtrar valores numéricos que estejam contidos em dado campo de texto.
     * @param jTextField Refere-se ao campo de texto.
     */
    private static void filterValue(final javax.swing.JTextField jTextField) {
        String stringkeys = jTextField.getText();
        if (stringkeys.length() > 0) {
            try {
                final int totalValueKeys = Integer.parseInt(stringkeys);
                if (totalValueKeys == 0) {
                    jTextField.setText("");
                } else {
                    final char[] charKeys = stringkeys.toCharArray();
                    stringkeys = "";
                    boolean release = false, update = false;
                    for (final char charKey : charKeys) {
                        if (release) {
                            stringkeys += charKey;
                        } else if (charKey != '0') {
                            release = true;
                            stringkeys += charKey;
                        } else {
                            update = true;
                        }
                    }
                    if (update) {
                        jTextField.setText(stringkeys);
                    }
                }
            } catch (final NumberFormatException ex) {
                jTextField.setText("");
            }
        }
    }

    /**
     * Método responsável por exibir a janela de interrupção.
     * @param parent Refere-se ao invocador da janela de interrupção.
     */
    public static void showModal(final java.awt.Frame parent) {
        if (instance == null) {
            instance = new PostStopWindow(parent, true);
        }
        if (attemptsLimit() == 0 && failureLimit() == 0 && successLimit() == 0) {
            instance.radioButtonNoConditions.setSelected(true);
        }
        instance.setVisible(true);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupInterrupt = new javax.swing.ButtonGroup();
        separatorSuccess = new javax.swing.JSeparator();
        radioButtonSuccess = new javax.swing.JRadioButton();
        labelSuccess = new javax.swing.JLabel();
        textFieldSuccess = new javax.swing.JTextField();
        separatorFailure = new javax.swing.JSeparator();
        radioButtonFailure = new javax.swing.JRadioButton();
        labelFailure = new javax.swing.JLabel();
        textFieldFailure = new javax.swing.JTextField();
        separatorAttempts = new javax.swing.JSeparator();
        radioButtonAttempts = new javax.swing.JRadioButton();
        labelAttempts = new javax.swing.JLabel();
        textFieldAttempts = new javax.swing.JTextField();
        separatorNoConditions = new javax.swing.JSeparator();
        radioButtonNoConditions = new javax.swing.JRadioButton();
        separatorButtons = new javax.swing.JSeparator();
        buttonConfirm = new javax.swing.JButton();
        radioButtonInform = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Interrupção Programada de Comentários");
        setAlwaysOnTop(true);
        setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/x48_Size/gnome-robots-icon.png")));
        setResizable(false);

        buttonGroupInterrupt.add(radioButtonSuccess);
        radioButtonSuccess.setText("Interromper após tentativas bem sucedidas.");
        radioButtonSuccess.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radioButtonSuccessStateChanged(evt);
            }
        });

        labelSuccess.setText("Quantidade de tentativas:");

        textFieldSuccess.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textFieldSuccess.setEnabled(false);
        textFieldSuccess.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textFieldSuccessKeyTyped(evt);
            }

            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldSuccessKeyReleased(evt);
            }
        });

        buttonGroupInterrupt.add(radioButtonFailure);
        radioButtonFailure.setText("Interromper após tentativas mal sucedidas.");
        radioButtonFailure.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radioButtonFailureStateChanged(evt);
            }
        });

        labelFailure.setText("Quantidade de tentativas:");

        textFieldFailure.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textFieldFailure.setEnabled(false);
        textFieldFailure.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textFieldFailureKeyTyped(evt);
            }

            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldFailureKeyReleased(evt);
            }
        });

        buttonGroupInterrupt.add(radioButtonAttempts);
        radioButtonAttempts.setText("Interromper após tentativas totalizadas.");
        radioButtonAttempts.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radioButtonAttemptsStateChanged(evt);
            }
        });

        labelAttempts.setText("Quantidade de tentativas:");

        textFieldAttempts.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textFieldAttempts.setEnabled(false);
        textFieldAttempts.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textFieldAttemptsKeyTyped(evt);
            }

            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldAttemptsKeyReleased(evt);
            }
        });

        buttonGroupInterrupt.add(radioButtonNoConditions);
        radioButtonNoConditions.setSelected(true);
        radioButtonNoConditions.setText("Não interromper através de condições.");
        radioButtonNoConditions.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                radioButtonNoConditionsStateChanged(evt);
            }
        });

        buttonConfirm.setText("Ok");
        buttonConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfirmActionPerformed(evt);
            }
        });

        radioButtonInform.setSelected(true);
        radioButtonInform.setText("Avise-me quado interromper.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(radioButtonInform).addComponent(radioButtonNoConditions)
                                .addComponent(separatorNoConditions, javax.swing.GroupLayout.PREFERRED_SIZE, 350,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup().addComponent(labelAttempts).addGap(60, 60, 60)
                                        .addComponent(textFieldAttempts, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(separatorAttempts, javax.swing.GroupLayout.PREFERRED_SIZE, 350,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup().addComponent(labelFailure).addGap(60, 60, 60)
                                        .addComponent(textFieldFailure, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(radioButtonSuccess)
                                .addGroup(layout.createSequentialGroup().addComponent(labelSuccess).addGap(60, 60, 60)
                                        .addComponent(textFieldSuccess, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(buttonConfirm)
                                        .addComponent(separatorButtons, javax.swing.GroupLayout.PREFERRED_SIZE, 350,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(separatorFailure, javax.swing.GroupLayout.PREFERRED_SIZE, 350,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(radioButtonAttempts).addComponent(radioButtonFailure)
                                .addComponent(separatorSuccess, javax.swing.GroupLayout.PREFERRED_SIZE, 350,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(20, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup().addGap(20, 20, 20)
                .addComponent(separatorSuccess, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5).addComponent(radioButtonSuccess).addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelSuccess).addComponent(textFieldSuccess,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(separatorFailure, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5).addComponent(radioButtonFailure).addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelFailure).addComponent(textFieldFailure,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(separatorAttempts, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5).addComponent(radioButtonAttempts).addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelAttempts).addComponent(textFieldAttempts,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(separatorNoConditions, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5).addComponent(radioButtonNoConditions).addGap(15, 15, 15)
                .addComponent(separatorButtons, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5).addComponent(radioButtonInform).addGap(10, 10, 10).addComponent(buttonConfirm)
                .addGap(10, 10, 10)));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void radioButtonSuccessStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radioButtonSuccessStateChanged
        enableFalseAllTextField();
        textFieldSuccess.setEnabled(true);
        textFieldAttempts.setText("");
        textFieldFailure.setText("");
    }//GEN-LAST:event_radioButtonSuccessStateChanged

    private void radioButtonFailureStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radioButtonFailureStateChanged
        enableFalseAllTextField();
        textFieldFailure.setEnabled(true);
        textFieldAttempts.setText("");
        textFieldSuccess.setText("");
    }//GEN-LAST:event_radioButtonFailureStateChanged

    private void radioButtonAttemptsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radioButtonAttemptsStateChanged
        enableFalseAllTextField();
        textFieldAttempts.setEnabled(true);
        textFieldFailure.setText("");
        textFieldSuccess.setText("");
    }//GEN-LAST:event_radioButtonAttemptsStateChanged

    private void radioButtonNoConditionsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_radioButtonNoConditionsStateChanged
        textFieldAttempts.setText("");
        textFieldFailure.setText("");
        textFieldSuccess.setText("");
        enableFalseAllTextField();
    }//GEN-LAST:event_radioButtonNoConditionsStateChanged

    private void textFieldSuccessKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldSuccessKeyTyped
        validateAction(evt);
    }//GEN-LAST:event_textFieldSuccessKeyTyped

    private void textFieldFailureKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldFailureKeyTyped
        validateAction(evt);
    }//GEN-LAST:event_textFieldFailureKeyTyped

    private void textFieldAttemptsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldAttemptsKeyTyped
        validateAction(evt);
    }//GEN-LAST:event_textFieldAttemptsKeyTyped

    private void textFieldSuccessKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldSuccessKeyReleased
        validateAction(evt);
        filterValue(textFieldSuccess);
    }//GEN-LAST:event_textFieldSuccessKeyReleased

    private void textFieldFailureKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldFailureKeyReleased
        validateAction(evt);
        filterValue(textFieldFailure);
    }//GEN-LAST:event_textFieldFailureKeyReleased

    private void textFieldAttemptsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldAttemptsKeyReleased
        validateAction(evt);
        filterValue(textFieldAttempts);
    }//GEN-LAST:event_textFieldAttemptsKeyReleased

    private void buttonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmActionPerformed
        dispose();
    }//GEN-LAST:event_buttonConfirmActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonConfirm;
    private javax.swing.ButtonGroup buttonGroupInterrupt;
    private javax.swing.JLabel labelAttempts;
    private javax.swing.JLabel labelFailure;
    private javax.swing.JLabel labelSuccess;
    private javax.swing.JRadioButton radioButtonAttempts;
    private javax.swing.JRadioButton radioButtonFailure;
    private javax.swing.JRadioButton radioButtonInform;
    private javax.swing.JRadioButton radioButtonNoConditions;
    private javax.swing.JRadioButton radioButtonSuccess;
    private javax.swing.JSeparator separatorAttempts;
    private javax.swing.JSeparator separatorButtons;
    private javax.swing.JSeparator separatorFailure;
    private javax.swing.JSeparator separatorNoConditions;
    private javax.swing.JSeparator separatorSuccess;
    private javax.swing.JTextField textFieldAttempts;
    private javax.swing.JTextField textFieldFailure;
    private javax.swing.JTextField textFieldSuccess;
    // End of variables declaration//GEN-END:variables
}
