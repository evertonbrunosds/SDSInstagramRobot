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

import com.bulenkov.darcula.DarculaLaf;
import control.Controller;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicLookAndFeel;
import static model.Factory.makeFreeThread;
import model.IMessage;
import static view.PostStopWindow.inform;
import static view.PostStopWindow.attemptsLimit;
import static view.PostStopWindow.failureLimit;
import static view.PostStopWindow.successLimit;

/**
 * Classe responsável por comportar-se como janela princial da aplicação.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.3
 */
public class MainWindow extends javax.swing.JFrame {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = -8149323350121930342L;
    /**
     * Refere-se a instância da classe.
     */
    private static MainWindow instance;

    /**
     * Construtor responsável pelo instanciamento da classe.
     */
    private MainWindow() {
        initComponents();
        instance = this;
    }

    /**
     * Método responsável por alterar título da janela principal.
     * @param text Refere-se ao texto do título da janela principal.
     */
    public static void setTitleWindow(final String text) {
        instance.setTitle("SDSInstagramRobot | Perfil : " + text);
    }

    /**
     * Método responsável por reiniciar o título da janela principal.
     */
    public static void resetTitleWindow() {
        instance.setTitle("SDSInstagramRobot");
    }

    /**
     * Método responsável por ativar e desativar ites de menu.
     * @param jMenuItem Refere-se ao item de menu.
     * @param active    Refere-se a indicativo de que o item deve ser ativado.
     */
    private void enable(final javax.swing.JMenuItem jMenuItem, boolean active) {
        jMenuItem.setEnabled(active);
    }

    /**
     * Método responsável por fechar toda a aplicação.
     */
    @Override
    public void dispose() {
        Controller.getInstance().disconnect();
        super.dispose();
    }

    /**
     * Método responsável por retornar instância de mensagem de estado.
     * @return Retorna instância de mensagem de estado.
     */
    private IMessage<String> updaterState() {
        return (final String data) -> labelState.setText("Estado: " + data);
    }

    /**
     * Método responsável por retornar instância de mensagem de falha.
     * @return Retorna instância de mensagem de falha.
     */
    private IMessage<Integer> updaterFailureCounter() {
        return (final Integer counter) -> {
            stopPost(failureLimit(), counter, " tentativas mal sucedidas.");
            labelFailureCounter.setText("Tentativas Mal Sucedidas: " + counter);
        };
    }

    /**
     * Método responsável por retornar instância de mensagem de sucesso.
     * @return Retorna instância de mensagem de sucesso.
     */
    private IMessage<Integer> updaterSuccessCounter() {
        return (final Integer counter) -> {
            stopPost(successLimit(), counter, " tentativas bem sucedidas.");
            labelSuccessCounter.setText("Tentativas Bem Sucedidas: " + counter);
        };
    }

    /**
     * Método responsável por retornar instância de mensagem de total de tentativas.
     * @return Retorna instância de mensagem de total de tentativas.
     */
    private IMessage<Integer> updaterTotalAttemptsCounter() {
        return (final Integer counter) -> {
            stopPost(attemptsLimit(), counter, " tentativas totalizadas.");
            labelTotalAttemptsCounter.setText("Tentativas Totalizadas: " + counter);
        };
    }

    /**
     * Método responsável por interromper um disparo de maneira agendada.
     * @param limit   Refere-se ao limite que determina a interrupção.
     * @param counter Refere-se ao contador usado como base limite de interrupção.
     * @param suffix  Refere-se ao sufixo da mensagem a ser exibida.
     */
    private static void stopPost(final int limit, final int counter, final String suffix) {
        makeFreeThread(() -> {
            if (limit > 0 && limit <= counter) {
                final String title = "Mensagem de Aviso";
                final String msg = "Interrupção programada realizada após " + counter + suffix;
                Controller.getInstance().stop();
                if (inform()) {
                    JOptionPane.showMessageDialog(instance, msg, title, 2);
                }
            }
        }).start();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTotalAttemptsCounter = new javax.swing.JLabel();
        labelFailureCounter = new javax.swing.JLabel();
        labelSuccessCounter = new javax.swing.JLabel();
        labelState = new javax.swing.JLabel();
        toolBar = new javax.swing.JMenuBar();
        menuAccont = new javax.swing.JMenu();
        optConnect = new javax.swing.JMenuItem();
        optDisconnect = new javax.swing.JMenuItem();
        menuComments = new javax.swing.JMenu();
        menuStop = new javax.swing.JMenu();
        optStopNow = new javax.swing.JMenuItem();
        optStopPost = new javax.swing.JMenuItem();
        optThrow = new javax.swing.JMenuItem();
        optList = new javax.swing.JMenuItem();
        menuAbout = new javax.swing.JMenu();
        optLibs = new javax.swing.JMenuItem();
        optLicense = new javax.swing.JMenuItem();
        optAuthor = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SDSInstagramRobot");
        setAlwaysOnTop(true);
        setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/x48_Size/gnome-robots-icon.png")));
        setResizable(false);

        labelTotalAttemptsCounter.setText("Tentativas Totalizadas: 0");

        labelFailureCounter.setText("Tentativas Mal Sucedidas: 0");

        labelSuccessCounter.setText("Tentativas Bem Sucedidas: 0");

        labelState.setText("Estado: Aguardando Ações de Usuário...");
        labelState.setAutoscrolls(true);

        menuAccont.setText("Perfil");
        menuAccont.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                menuAccontStateChanged(evt);
            }
        });

        optConnect.setText("Conectar");
        optConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optConnectActionPerformed(evt);
            }
        });
        menuAccont.add(optConnect);

        optDisconnect.setText("Desconectar");
        optDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optDisconnectActionPerformed(evt);
            }
        });
        menuAccont.add(optDisconnect);

        toolBar.add(menuAccont);

        menuComments.setText("Comentários");
        menuComments.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                menuCommentsStateChanged(evt);
            }
        });

        menuStop.setText("Interromper");

        optStopNow.setText("Agora");
        optStopNow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optStopNowActionPerformed(evt);
            }
        });
        menuStop.add(optStopNow);

        optStopPost.setText("Após");
        optStopPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optStopPostActionPerformed(evt);
            }
        });
        menuStop.add(optStopPost);

        menuComments.add(menuStop);

        optThrow.setText("Disparar");
        optThrow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optThrowActionPerformed(evt);
            }
        });
        menuComments.add(optThrow);

        optList.setText("Lista");
        optList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optListActionPerformed(evt);
            }
        });
        menuComments.add(optList);

        toolBar.add(menuComments);

        menuAbout.setText("Sobre");

        optLibs.setText("Bibliotecas");
        optLibs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optLibsActionPerformed(evt);
            }
        });
        menuAbout.add(optLibs);

        optLicense.setText("Licença");
        optLicense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optLicenseActionPerformed(evt);
            }
        });
        menuAbout.add(optLicense);

        optAuthor.setText("Autor");
        optAuthor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optAuthorActionPerformed(evt);
            }
        });
        menuAbout.add(optAuthor);

        toolBar.add(menuAbout);

        setJMenuBar(toolBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelTotalAttemptsCounter).addComponent(labelFailureCounter)
                                .addComponent(labelSuccessCounter).addComponent(labelState))
                        .addContainerGap(293, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(20, 20, 20).addComponent(labelState).addGap(20, 20, 20)
                        .addComponent(labelSuccessCounter).addGap(20, 20, 20).addComponent(labelFailureCounter)
                        .addGap(20, 20, 20).addComponent(labelTotalAttemptsCounter)
                        .addContainerGap(20, Short.MAX_VALUE)));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void optListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optListActionPerformed
        CommentsWindow.showModal(this);
    }//GEN-LAST:event_optListActionPerformed

    private void optConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optConnectActionPerformed
        ConnectWindow.showModal(this, updaterSuccessCounter(), updaterFailureCounter(), updaterTotalAttemptsCounter(), updaterState());
    }//GEN-LAST:event_optConnectActionPerformed

    private void optDisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optDisconnectActionPerformed
        Controller.getInstance().disconnect();
        resetTitleWindow();
    }//GEN-LAST:event_optDisconnectActionPerformed

    private void menuAccontStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_menuAccontStateChanged
        enable(optDisconnect, Controller.getInstance().isReady());
        enable(optConnect, !Controller.getInstance().isReady());
    }//GEN-LAST:event_menuAccontStateChanged

    private void menuCommentsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_menuCommentsStateChanged
        final boolean isReady = Controller.getInstance().isReady();
        final boolean isEmpty = CommentsWindow.isEmpty();
        final boolean isRunning = Controller.getInstance().isRunning();
        enable(optThrow, isReady && !isEmpty && !isRunning);
        enable(optStopNow, isReady && !isEmpty && isRunning);
    }//GEN-LAST:event_menuCommentsStateChanged

    private void optThrowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optThrowActionPerformed
        ThrowWindow.showModal(this);
    }//GEN-LAST:event_optThrowActionPerformed

    private void optStopNowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optStopNowActionPerformed
        Controller.getInstance().stop();
    }//GEN-LAST:event_optStopNowActionPerformed

    private void optLicenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optLicenseActionPerformed
        LicenseWindow.showModal();
    }//GEN-LAST:event_optLicenseActionPerformed

    private void optAuthorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optAuthorActionPerformed
        AuthorAbout.showModal();
    }//GEN-LAST:event_optAuthorActionPerformed

    private void optLibsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optLibsActionPerformed
        LibsWindow.showModal();
    }//GEN-LAST:event_optLibsActionPerformed

    private void optStopPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optStopPostActionPerformed
        PostStopWindow.showModal(this);
    }//GEN-LAST:event_optStopPostActionPerformed

    /**
     * Método responsável por invocar toda a aplicação.
     * @param args Refere-se aos argumentos de invocação.
     */
    public static void main(final String args[]) {
        try {
            for (final javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    BasicLookAndFeel darcula = new DarculaLaf();
                    UIManager.setLookAndFeel(darcula);
                    break;
                }
            }
        } catch (final UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelFailureCounter;
    private javax.swing.JLabel labelState;
    private javax.swing.JLabel labelSuccessCounter;
    private javax.swing.JLabel labelTotalAttemptsCounter;
    private javax.swing.JMenu menuAbout;
    private javax.swing.JMenu menuAccont;
    private javax.swing.JMenu menuComments;
    private javax.swing.JMenu menuStop;
    private javax.swing.JMenuItem optAuthor;
    private javax.swing.JMenuItem optConnect;
    private javax.swing.JMenuItem optDisconnect;
    private javax.swing.JMenuItem optLibs;
    private javax.swing.JMenuItem optLicense;
    private javax.swing.JMenuItem optList;
    private javax.swing.JMenuItem optStopNow;
    private javax.swing.JMenuItem optStopPost;
    private javax.swing.JMenuItem optThrow;
    private javax.swing.JMenuBar toolBar;
    // End of variables declaration//GEN-END:variables
}
