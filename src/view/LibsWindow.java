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

/**
 * Classe responsável por comportar-se como janela de licença.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 */
public class LibsWindow extends javax.swing.JDialog {
    /**
     * Refere-se ao número de série da janela de licença.
     */
    private static final long serialVersionUID = -8739027692566156282L;
    /**
     * Refere-se a instância da janela de licença.
     */
    private static LibsWindow instance;

    /**
     * Método responsável por criar instância da janela.
     */
    private static void createInstance() {
        instance = new LibsWindow(null, true) {
            private static final long serialVersionUID = -4510208525886992590L;
            @Override
            public void dispose() {
                instance = null;
                super.dispose();
            }
        };
    }

    /**
     * Método responsável por exibir a janela de licença.
     */
    public static void showModal() {
        createInstance();
        instance.setVisible(true);
    }

    /**
     * Construtor responsável pelo instanciamento da janela de licença.
     * @param parent Refere-se ao invocador da janela.
     * @param modal  Refere-se ao modo de exibição.
     */
    private LibsWindow(final java.awt.Frame parent, final boolean modal) {
        super(parent, modal);
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitle = new javax.swing.JLabel();
        separatorTitle = new javax.swing.JSeparator();
        labelGeckoDriverName = new javax.swing.JLabel();
        labelGeckoDriverCopyright = new javax.swing.JLabel();
        labelGeckoDriverLicense = new javax.swing.JLabel();
        separatorGeckoDriver = new javax.swing.JSeparator();
        labelSeleniumName = new javax.swing.JLabel();
        labelSeleniumCopyright = new javax.swing.JLabel();
        labelSeleniumLicense = new javax.swing.JLabel();
        separatorSelenium = new javax.swing.JSeparator();
        labelDarculaName = new javax.swing.JLabel();
        labelDarculaCopyright = new javax.swing.JLabel();
        labelDarculaLicense = new javax.swing.JLabel();
        separatorDarcula = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Bibliotecas");
        setAlwaysOnTop(true);
        setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/x48_Size/gnome-robots-icon.png")));
        setResizable(false);

        labelTitle.setText("O SDSInstagramRobot conta com o auxílio das seguintes bibliotecas:");

        labelGeckoDriverName.setText("Geckodriver (v0.28.0)");

        labelGeckoDriverCopyright.setText("Copyright © 2012 - 2020. Mozilla.");

        labelGeckoDriverLicense.setText("Mozilla Geral Public (v2.0).");

        labelSeleniumName.setText("Selenium Java (v3.5.3)");

        labelSeleniumCopyright.setText("Copyright © 2004 - 2017. Jason Huggins.");

        labelSeleniumLicense.setText("Apache License (v2.0).");

        labelDarculaName.setText("Darcula (v1.6)");

        labelDarculaCopyright.setText("Copyright © 2013 - 2017. Konstantin Bulenkov.");

        labelDarculaLicense.setText("Apache License (v2.0).");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelTitle)
                    .addComponent(separatorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelGeckoDriverName)
                    .addComponent(labelGeckoDriverCopyright)
                    .addComponent(labelGeckoDriverLicense)
                    .addComponent(separatorGeckoDriver, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSeleniumName)
                    .addComponent(labelSeleniumCopyright)
                    .addComponent(labelSeleniumLicense)
                    .addComponent(separatorSelenium, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDarculaName)
                    .addComponent(labelDarculaCopyright)
                    .addComponent(labelDarculaLicense)
                    .addComponent(separatorDarcula, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelTitle)
                .addGap(20, 20, 20)
                .addComponent(separatorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(labelGeckoDriverName)
                .addGap(5, 5, 5)
                .addComponent(labelGeckoDriverCopyright)
                .addGap(5, 5, 5)
                .addComponent(labelGeckoDriverLicense)
                .addGap(15, 15, 15)
                .addComponent(separatorGeckoDriver, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(labelSeleniumName)
                .addGap(5, 5, 5)
                .addComponent(labelSeleniumCopyright)
                .addGap(5, 5, 5)
                .addComponent(labelSeleniumLicense)
                .addGap(15, 15, 15)
                .addComponent(separatorSelenium, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(labelDarculaName)
                .addGap(5, 5, 5)
                .addComponent(labelDarculaCopyright)
                .addGap(5, 5, 5)
                .addComponent(labelDarculaLicense)
                .addGap(15, 15, 15)
                .addComponent(separatorDarcula, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelDarculaCopyright;
    private javax.swing.JLabel labelDarculaLicense;
    private javax.swing.JLabel labelDarculaName;
    private javax.swing.JLabel labelGeckoDriverCopyright;
    private javax.swing.JLabel labelGeckoDriverLicense;
    private javax.swing.JLabel labelGeckoDriverName;
    private javax.swing.JLabel labelSeleniumCopyright;
    private javax.swing.JLabel labelSeleniumLicense;
    private javax.swing.JLabel labelSeleniumName;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JSeparator separatorDarcula;
    private javax.swing.JSeparator separatorGeckoDriver;
    private javax.swing.JSeparator separatorSelenium;
    private javax.swing.JSeparator separatorTitle;
    // End of variables declaration//GEN-END:variables
}