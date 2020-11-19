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
package model;

import java.util.concurrent.Semaphore;
import javax.swing.JOptionPane;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 * Classe responsável por comportar-se como fábrica de objetos e dados.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 */
public final class Factory {
    /**
     * Refere-se ao objeto responsável por realizar sorteio de valores aleatórios.
     */
    private static final java.util.Random RANDOM = new java.util.Random();
    /**
     * Refere-se ao semáforo singular responsável por reger as SafeThreads.
     */
    private final static Semaphore SEMAPHORE = new Semaphore(1);

    /**
     * Método responsável por retornar instância de um Driver Web.
     * @param key   Refere-se a linguagem utilisada no navegador.
     * @param value Refere-se ao código da linguagem utilizada no navegador.
     * @return Retorna instância de um Driver Web.
     */
    public static WebDriver makeWebDriver(final String key, final String value) {
        try {
            System.setProperty("webdriver.gecko.driver", "geckodriver");
            final FirefoxProfile firefoxProfile = new FirefoxProfile();
            final FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxProfile.setPreference(key, value);
            firefoxProfile.setPreference("dom.webnotifications.enabled", false);
            firefoxOptions.setProfile(firefoxProfile);
            return new FirefoxDriver(firefoxOptions);
        } catch (final Exception ex) {
            final String msg = "A aplicação necessita do Mozilla Firefox!";
            final String title = "Mensagem de Erro!";
            JOptionPane.showMessageDialog(null, msg, title, 0);
            System.exit(0);
        }
        return null;
    }

    /**
     * Método responsável por retornar instância de thread assegurada por semáforo singular.
     * @param task Refere-se a tarefa executada pela thread.
     * @return Retorna thread assegurada por semáforo singular.
     */
    public static Thread makeSafeThread(final ITask task) {
        return new Thread() {
            @Override
            public void run() {
                try {
                    SEMAPHORE.acquire();
                    task.execute();
                } catch (final InterruptedException ex) {
                    makeSafeThread(task).start();
                } finally {
                    SEMAPHORE.release();
                }
            }
        };
    }

    /**
     * Método responsável por retornar instância de thread livre do semáforo singular.
     * @param task Refere-se a tarefa executada pela thread.
     * @return Retorna thread livre do semáforo singular.
     */
    public static Thread makeFreeThread(final ITask task) {
        return new Thread() {
            @Override
            public void run() {
                task.execute();
            }
        };
    }

    /**
     * Método responsável por retornar valor aleatório sorteado em dado intervalo fechado.
     * @param min Refere-se ao valor mínimo.
     * @param max Refere-se ao valor máximo.
     * @return Retorna valor aleatório sorteado em dado intervalo fechado.
     */
    public static int makeRandomValue(final int min, final int max) {
        return max - RANDOM.nextInt(max - min);
    }

    /**
     * Método responsável por retornar instância de robô.
     * @param successMessage Refere-se a mensagem de sucesso.
     * @param failureMessage Refere-se a mensagem de falha.
     * @param totalAttempts  Refere-se a mesagem de total de tentativas.
     * @param stateMessage   Refere-se a mensagem de estado.
     * @return Retorna instância de robô.
     */
    public static Boot makeBoot(final IMessage<Integer> successMessage, final IMessage<Integer> failureMessage,
            final IMessage<Integer> totalAttempts, final IMessage<String> stateMessage) {
        return new Boot(successMessage, failureMessage, totalAttempts, stateMessage);
    }

}
