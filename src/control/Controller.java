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

import model.Boot;
import model.Container;
import model.Container.EmptyContainerException;
import static model.Factory.makeFreeThread;
import model.PageFaultException;
import static model.Factory.makeSafeThread;
import model.ITime;

/**
 * Classe responsável por comportar-se como controlador.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.4
 */
public final class Controller {
    /**
     * Refere-se a instância singular do controlador.
     */
    private final static Controller INSTANCE = new Controller();
    /**
     * Refere-se ao robô contido no controlador.
     */
    private Boot boot;
    /**
     * Refere-se a thread que está rodando no controlador.
     */
    private Thread runningThread;

    /**
     * Construtor responsável pelo instanciamento do controlador.
     */
    private Controller() {
        boot = null;
        runningThread = null;
    }

    /**
     * Método responsável por retornar instância singular do controlador.
     * @return Retorna instância singular do controlador.
     */
    public static Controller getInstance() {
        return INSTANCE;
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
        return runningThread != null;
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
            stop();
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
        makeFreeThread(() -> boot.loadPage(url)).start();
    }

    /**
     * Método responsável por disparar comentários massivos.
     * @param commentsAvailable Refere-se ao container de comentários disponíveis.
     * @param throwInterval     Refere-se ao intervalo de disparo.
     * @param disguiseInterval  Refere-se ao intervalo de disfarçe.
     */
    public void run(final Container<String> commentsAvailable, final Container<Integer> throwInterval, final Container<Integer> disguiseInterval) {
        makeSafeThread(() -> {
            if (isReady() && !isRunning()) {
                final Thread thread = makeFreeThread(() -> {
                    while (isReady()) {
                        try {
                            throwComment(commentsAvailable, throwInterval, disguiseInterval);
                        } catch (final InterruptedException | EmptyContainerException ex) {
                            break;
                        }
                    }
                    boot.getStateMessage().send(Boot.TextMessage.AWAITING_USER_ACTION);
                    runningThread = null;
                });
                runningThread = thread;
                runningThread.start();
            }
        }).start();
    }

    /**
     * Método responsável por disparar comentário individual com restrições de intervalo de tempo.
     * @param commentsAvailable Refere-se ao container de comentários disponíveis.
     * @param throwInterval     Refere-se ao intervalo de disparo.
     * @param disguiseInterval  Refere-se ao intervalo de disfarçe.
     * @throws InterruptedException    Exceção lançada no caso da thread ser interrompida.
     * @throws EmptyContainerException Exceção lançada no caso do container de comentários estar vazio.
     */
    private void throwComment(final Container<String> commentsAvailable, final Container<Integer> throwInterval,
            final Container<Integer> disguiseInterval) throws InterruptedException, EmptyContainerException {
        try {
            final String prefix = "Ativo! Comentará em ";
            ITime.stop(ITime.SECOND, throwInterval.freeGet(), milliseconds -> {
                sendTimeStateMessage(prefix, milliseconds);
            });
            boot.throwComment(commentsAvailable.safeGet());
        } catch (final PageFaultException ex) {
            boot.updateCurrentPage();
            final String prefix = "Ativo! Disfarçando comportamento suspeito por ";
            ITime.stop(ITime.SECOND, disguiseInterval.freeGet(), milliseconds -> {
                sendTimeStateMessage(prefix, milliseconds);
            });
        }
    }

    /**
     * Método responsável por enviar mensagem de estado de tempo de espera.
     * @param prefix       Refere-se ao prefixo da mensagem.
     * @param milliseconds Refere-se ao tempo de espera.
     */
    private static void sendTimeStateMessage(final String prefix, final int milliseconds) {
        if (ITime.isHours(milliseconds)) {
            INSTANCE.boot.getStateMessage().send(prefix + ITime.Format.hours(milliseconds) + " hora(s)...");
        } else if (ITime.isMinutes(milliseconds)) {
            INSTANCE.boot.getStateMessage().send(prefix + ITime.Format.minutes(milliseconds) + " minuto(s)...");
        } else {
            INSTANCE.boot.getStateMessage().send(prefix + ITime.Format.seconds(milliseconds) + " segundo(s)...");
        }
    }

    /**
     * Método responsável por interromper o disparo de comentários massivos.
     */
    public void stop() {
        makeFreeThread(() -> {
            if (isRunning()) {
                runningThread.interrupt();
            }
        }).start();
    }

}
