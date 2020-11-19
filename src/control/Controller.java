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
package control;

import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Boot;
import model.Container;
import model.Container.EmptyContainerException;
import static model.Factory.makeFreeThread;
import static model.Factory.makeRandomValue;
import model.PageFaultException;
import static model.Factory.makeSafeThread;

/**
 * Classe responsável por comportar-se como controlador.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 */
public final class Controller {
    /**
     * Refere-se a instância singular do controlador.
     */
    private static Controller instance;
    /**
     * Refere-se ao robô contido no controlador.
     */
    private Boot boot;
    /**
     * Refere-se a indicativo de que o controlador está rodando.
     */
    private boolean isRunning;

    /**
     * Construtor responsável pelo instanciamento do controlador.
     */
    private Controller() {
        boot = null;
        isRunning = false;
    }

    /**
     * Método responsável por retornar instância singular do controlador.
     * @return Retorna instância singular do controlador.
     */
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    /**
     * Método responsável por caso não haja, inserir robô no controlador.
     * @param boot Refere-se ao robô.
     */
    public void setBoot(final Boot boot) {
        if (this.boot == null) {
            this.boot = boot;
        }
    }

    /**
     * Método responsável por retornar indicativo de que o controlador está pronto.
     * @return Retorna indicativo de que o controlador está pronto.
     */
    public boolean isReady() {
        return (boot == null) ? false : boot.isConnected();
    }

    /**
     * Método responsável por retornar indicativo de que o controlador está em execução.
     * @return Retorna indicativo de que o controlador está em execução.
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Método responsável por conectar o controlador.
     * @param userName Refere-se ao nome de usuário.
     * @param password Refere-se a senha de usuário.
     */
    public void connect(final String userName, final String password) {
        makeSafeThread(() -> boot.connect(userName, password)).start();
    }

    /**
     * Método responsável por desconectar o controlador.
     */
    public void disconnect() {
        makeSafeThread(() -> {
            if (boot != null) {
                boot.disconnect();
                boot = null;
            }
        }).start();
    }

    /**
     * Método responsável por carregar página no controlador.
     * @param url Refere-se ao endereço da página.
     */
    public void loadPage(final String url) {
        boot.loadPage(url);
    }

    /**
     * Método responsável por disparar comentários massivos.
     * @param comments Refere-se ao container de comentários.
     */
    public void run(final Container<String> comments) {
        if (isReady() && !isRunning()) {
            makeFreeThread(() -> {
                isRunning = true;
                while (isReady() && isRunning()) {
                    try {
                        final int interval = makeRandomValue(10000, 20000);
                        boot.getStateMessage().send("Ativo! Comentará em " + (interval / 1000) + " Segundos...");
                        sleep(interval);
                        boot.throwComment(comments.safeGet());
                    } catch (final PageFaultException ex) {
                        boot.updateCurrentPage();
                        final int interval = makeRandomValue(60000 * 3, 60000 * 5);
                        final String prefix = "Ativo! Disfarçando Comportamento Suspeito por ";
                        String suffix = " Minuto...";
                        if ((interval / 60000) > 1) suffix = " Minutos...";
                        boot.getStateMessage().send(prefix + toString(((double) interval) / 60000.0) + suffix);
                        sleep(interval);
                    } catch (final EmptyContainerException ex) {
                        isRunning = false;
                    }
                }
                isRunning = false;
                boot.getStateMessage().send("Estático! Aguardando Ações de Usuário...");
            }).start();
        }
    }

    /**
     * Método responsável por converter em caractere um valor decimal.
     * @param value Refere-se ao valor decimal.
     * @return Retorna um valor decimal convertido em caractere.
     */
    private String toString(final double value) {
        final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return decimalFormat.format(value);
    }

    /**
     * Método responsável por interromper o disparo de comentários massivos.
     */
    public void stop() {
        isRunning = false;
        boot.getStateMessage().send("Estático! Aguardando Ações de Usuário...");
    }

    /**
     * Método responsável por fazer com que o controlador durma por determinado tempo.
     * @param milliseconds Refere-se ao determinado tempo medido em milisegundos.
     */
    private void sleep(final int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (final InterruptedException ex) {
            Logger.getLogger(Boot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
