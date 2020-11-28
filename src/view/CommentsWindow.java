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
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Container;
import static model.Factory.makeRandomValue;
import model.FileTextStream;

/**
 * Classe responsável por comportar-se como janela de comentários.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.4
 */
public class CommentsWindow extends javax.swing.JDialog {
    /**
     * Refere-se ao número de série da classe.
     */
    private static final long serialVersionUID = 8317162468568240237L;
    /**
     * Refere-se a instância singular da janela de comentários.
     */
    private static CommentsWindow instance;
    /**
     * Refere-se a janela invocadora da janela de comentários.
     */
    private java.awt.Frame currentParent;

    /**
     * Método responsável por exibir a janela de comentários.
     * @param parent Refere-se a janela invocadora da janela de comentários.
     */
    public static void showModal(final java.awt.Frame parent) {
        if (instance == null) {
            instance = new CommentsWindow(parent, true);
            instance.list.setModel(new DefaultListModel<>());
        }
        instance.currentParent = parent;
        instance.textField.requestFocusInWindow();
        instance.setVisible(true);
    }

    /**
     * Método responsável por retornar indicativo de que não há cometários.
     * @return Retorna indicativo de que não há cometários.
     */
    public static boolean isEmpty() {
        return (instance == null) ? true : (instance.list.getModel().getSize() == 0);
    }

    /**
     * Método responsável por retornar container de comentários.
     * @return Retorna container de comentários.
     */
    public static Container<String> getComments() {
        return () -> getComment();
    }

    /**
     * Método responsável por retornar comentário aleatório.
     * @return Retorna comentário aleatório.
     */
    private static String getComment() {
        if (isEmpty()) {
            return null;
        } else {
            final DefaultListModel<String> model = getModel(instance.list);
            return model.elementAt(makeRandomValue(0, model.size()) - 1);
        }
    }

    /**
     * Método responsável por retornar model de dada lista visual.
     * @param jList Refere-se a dada lista visual.
     * @return Retorna model de dada lista visual.
     */
    private static DefaultListModel<String> getModel(final javax.swing.JList<String> jList) {
        return (DefaultListModel<String>) jList.getModel();
    }

    /**
     * Método responsável por retornar lista de caracteres de dada lista visual.
     * @param jList Refere-se a ada lista visual.
     * @return Retorna lista de caracteres.
     */
    private static List<String> getStringList(final javax.swing.JList<String> jList) {
        final List<String> stringList = new LinkedList<>();
        for (int i = 0; i < getModel(jList).size(); i++) {
            stringList.add(getModel(jList).elementAt(i));
        }
        return stringList;
    }

    /**
     * Método responsável por remover itens selecionados em dada lista visual.
     * @param jList Refere-se a dada lista visual.
     */
    private static void removeSelectedItens(final javax.swing.JList<String> jList) {
        final DefaultListModel<String> defaultListModel = getModel(jList);
        while (jList.getSelectedIndices().length > 0) {
            defaultListModel.remove(jList.getSelectedIndices()[0]);
        }
    }

    /**
     * Método responsável por ativar e desativar ites de menu.
     * @param jMenuItem Refere-se ao item de menu.
     * @param active    Refere-se a indicativo de que o item deve ser ativado.
     */
    private static void enable(final javax.swing.JMenuItem jMenuItem, boolean active) {
        jMenuItem.setEnabled(active);
    }

    /**
     * Método responsável por exibir PopupMenu.
     * @param evt Refere-se ao evento que disparou o método.
     */
    private void showPopupMenu(final java.awt.event.MouseEvent evt) {
        if (evt.isMetaDown()) {
            enable(optRemoveSelected, list.getSelectedIndices().length > 0);
            enable(optRemoveAll, !getModel(list).isEmpty());
            popupMenu.show(this, getMousePosition().x, getMousePosition().y);
        }
    }

    /**
     * Método responsável por retornar instância de janela de diálogo de gravação.
     * @return Retorna instância de janela de diálogo de gravação.
     */
    private static FileDialog getSaveDialog() {
        final FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo de Texto", "txt");
        final String textAproveButton = "Exportar";
        final String title = "Exportar Arquivo de Texto";
        return new FileDialog(title, textAproveButton, filter);
    }

    /**
     * Método responsável por retornar instância de janela de diálogo de carregamento.
     * @return Retorna instância de janela de diálogo de carregamento.
     */
    private static FileDialog getOpenDialog() {
        final FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo de Texto", "txt");
        final String textAproveButton = "Importar";
        final String title = "Importar Arquivo de Texto";
        return new FileDialog(title, textAproveButton, filter);
    }

    /**
     * Construtor responsável pelo instanciamento da janela de cometários.
     * @param parent Refere-se ao invocador da janela.
     * @param modal  Refer-se a modalidade da janela.
     */
    private CommentsWindow(final java.awt.Frame parent, final boolean modal) {
        super(parent, modal);
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        optRemoveSelected = new javax.swing.JMenuItem();
        optRemoveAll = new javax.swing.JMenuItem();
        label = new javax.swing.JLabel();
        button = new javax.swing.JButton();
        textField = new javax.swing.JTextField();
        scrollPane = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        toolBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        optImport = new javax.swing.JMenuItem();
        optExport = new javax.swing.JMenuItem();

        optRemoveSelected.setText("Excluir Comentários Selecionados");
        optRemoveSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optRemoveSelectedActionPerformed(evt);
            }
        });
        popupMenu.add(optRemoveSelected);

        optRemoveAll.setText("Excluir Todos Os Comentários");
        optRemoveAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optRemoveAllActionPerformed(evt);
            }
        });
        popupMenu.add(optRemoveAll);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista de Comentários");
        setAlwaysOnTop(true);
        setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/x48_Size/gnome-robots-icon.png")));
        setResizable(false);

        label.setText("Comentário:");

        button.setText("Adicionar");
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

        textField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldKeyReleased(evt);
            }
        });

        scrollPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                scrollPaneMouseReleased(evt);
            }
        });
        scrollPane.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                scrollPaneKeyReleased(evt);
            }
        });

        list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                listMouseReleased(evt);
            }
        });
        list.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                listKeyReleased(evt);
            }
        });
        scrollPane.setViewportView(list);

        menuFile.setText("Arquivo");

        optImport.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        optImport.setText("Importar");
        optImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optImportActionPerformed(evt);
            }
        });
        menuFile.add(optImport);

        optExport.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        optExport.setText("Exportar");
        optExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optExportActionPerformed(evt);
            }
        });
        menuFile.add(optExport);

        toolBar.add(menuFile);

        setJMenuBar(toolBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label).addGap(10, 10, 10)
                        .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 400,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10).addComponent(button).addGap(10, 10, 10))
                .addComponent(scrollPane));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup()
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 186,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(label).addComponent(button).addComponent(textField,
                                        javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActionPerformed
        if (!textField.getText().equals("")) {
            getModel(list).addElement(textField.getText());
            textField.setText("");
        }
    }//GEN-LAST:event_buttonActionPerformed

    private void optImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optImportActionPerformed
        final FileDialog fileDialog = getOpenDialog();
        if (fileDialog.run(currentParent)) {
            getModel(list).clear();
            try {
                FileTextStream.loadFromFile(fileDialog.getFileName(), getModel(list)::addElement);
            } catch (final IOException ex) {
                final String msg = "Falha! Arquivo ilegível.";
                final String title = "Mensagem de Erro";
                JOptionPane.showMessageDialog(this, msg, title, 0);
            }
        }
    }//GEN-LAST:event_optImportActionPerformed

    private void optExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optExportActionPerformed
        final FileDialog fileDialog = getSaveDialog();
        if (fileDialog.run(currentParent)) {
            try {
                if (fileDialog.getFileName().toLowerCase().contains(".txt")) {
                    FileTextStream.saveToFile(fileDialog.getFileName(), getStringList(list));
                } else {
                    FileTextStream.saveToFile(fileDialog.getFileName() + ".txt", getStringList(list));
                }
            } catch (final IOException ex) {
                final String msg = "Falha! Arquivo ilegível.";
                final String title = "Mensagem de Erro";
                JOptionPane.showMessageDialog(this, msg, title, 0);
            }
        }
    }//GEN-LAST:event_optExportActionPerformed

    private void optRemoveAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optRemoveAllActionPerformed
        getModel(list).clear();
    }//GEN-LAST:event_optRemoveAllActionPerformed

    private void optRemoveSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optRemoveSelectedActionPerformed
        removeSelectedItens(list);
    }//GEN-LAST:event_optRemoveSelectedActionPerformed

    private void scrollPaneMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scrollPaneMouseReleased
        showPopupMenu(evt);
    }//GEN-LAST:event_scrollPaneMouseReleased

    private void listMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listMouseReleased
        showPopupMenu(evt);
    }//GEN-LAST:event_listMouseReleased

    private void textFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!textField.getText().equals("")) {
                getModel(list).addElement(textField.getText());
                textField.setText("");
            }
        }
    }//GEN-LAST:event_textFieldKeyReleased

    private void buttonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buttonKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!textField.getText().equals("")) {
                getModel(list).addElement(textField.getText());
                textField.setText("");
            }
        }
    }//GEN-LAST:event_buttonKeyReleased

    private void listKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_listKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            removeSelectedItens(list);
        }
    }//GEN-LAST:event_listKeyReleased

    private void scrollPaneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_scrollPaneKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            removeSelectedItens(list);
        }
    }//GEN-LAST:event_scrollPaneKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button;
    private javax.swing.JLabel label;
    private javax.swing.JList<String> list;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenuItem optExport;
    private javax.swing.JMenuItem optImport;
    private javax.swing.JMenuItem optRemoveAll;
    private javax.swing.JMenuItem optRemoveSelected;
    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTextField textField;
    private javax.swing.JMenuBar toolBar;
    // End of variables declaration//GEN-END:variables
}
