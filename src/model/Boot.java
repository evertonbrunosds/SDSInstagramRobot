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

import java.util.concurrent.TimeUnit;
import static model.Factory.makeWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Classe responsável por comportar-se como robô.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 */
public class Boot {
    /**
     * Refere-se ao objeto de conexão web do robô.
     */
    private WebDriver webDriver;
    /**
     * Refere-se a mensagem de sucesso.
     */
    private final IMessage<Integer> successMessage;
    /**
     * Refere-se a mensagem de falha.
     */
    private final IMessage<Integer> failureMessage;
    /**
     * Refere-se a mesagem de total de tentativas.
     */
    private final IMessage<Integer> totalAttemptsMessage;
    /**
     * Refere-se a mensagem de estado.
     */
    private final IMessage<String> stateMessage;
    /**
     * Refere-se ao contador de sucesos.
     */
    private int successCounter;
    /**
     * Refere-se ao contador de falhas.
     */
    private int failureCounter;

    /**
     * Construtor responsável pelo instanciamento do robô.
     * @param successMessage       Refere-se a mensagem de sucesso.
     * @param failureMessage       Refere-se a mensagem de falha.
     * @param totalAttemptsMessage Refere-se a mesagem de total de tentativas.
     * @param stateMessage         Refere-se a mensagem de estado.
     */
    public Boot(final IMessage<Integer> successMessage, final IMessage<Integer> failureMessage,
            final IMessage<Integer> totalAttemptsMessage, final IMessage<String> stateMessage) {
        this.webDriver = null;
        this.successMessage = successMessage;
        this.failureMessage = failureMessage;
        this.totalAttemptsMessage = totalAttemptsMessage;
        this.stateMessage = stateMessage;
        resetValues();
    }

    /**
     * Método responsável por possibilitar que o robô se auto-configure.
     */
    private void configure() {
        stateMessage.send(TextMessage.SETTING_BOOT);
        if (isConnected()) webDriver.quit();
        webDriver = makeWebDriver("intl.accept_languages", "pt,pt-BR");
        resetValues();
    }

    /**
     * Método responsável por possibilitar que o robô reinicie seus valores.
     */
    private void resetValues() {
        successCounter = 0;
        failureCounter = 0;
        stateMessage.send(TextMessage.AWAITING_USER_ACTION);
        updateValues();
    }

    /**
     * Método responsável por possibilitar que o robô atualize seus valores.
     */
    private void updateValues() {
        successMessage.send(successCounter);
        failureMessage.send(failureCounter);
        totalAttemptsMessage.send(failureCounter + successCounter);
    }

    /**
     * Método responsável por possibilitar que o robô altere o conteúdo de dado campo de texto.
     * @param by         Refere-se ao identificador do campo de texto.
     * @param newText    Refere-se ao novo texto.
     * @param pressEnter Refere-se a necessidade de pressionar ENTER ao fim do processo.
     */
    private void setTextField(final By by, final String newText, final boolean pressEnter) {
        final WebElement textField = webDriver.findElement(by);
        textField.clear();
        textField.sendKeys(newText);
        if (pressEnter) {
            textField.sendKeys(Keys.RETURN);
        }
    }

    /**
     * Método responsável por possibilitar que o robô carregue determinada página.
     * @param url Refere-se ao URL da página.
     */
    public void loadPage(final String url) {
        if (!isConnected()) configure();
        stateMessage.send(TextMessage.LOADING_WEBPAGE);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);
        webDriver.get(url);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);
    }

    /**
     * Método responsável por possibilitar que o robô conecte-se a dado perfil.
     * @param userName Refere-se ao nome de usuário.
     * @param password Refere-se a senha de usuário.
     */
    public void connect(final String userName, final String password) {
        configure();
        loadPage("https://www.instagram.com");
        stateMessage.send(TextMessage.CONNECTING_BOOT);
        setTextField(By.xpath("//input[@name=\"username\"]"), userName, false);
        setTextField(By.xpath("//input[@name=\"password\"]"), password, true);
        webDriver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        stateMessage.send(TextMessage.AWAITING_USER_ACTION);
    }

    /**
     * Método responsável por possibilitar que o robô dispare um comentário em determinada postagem.
     * @param comment Refere-se ao comentário.
     * @throws PageFaultException Exceção lançada no caso de haverem falhas na página.
     */
    public void throwComment(final String comment) throws PageFaultException {
        if (isConnected()) {
            try {
                webDriver.findElement(By.className("Ypffh")).click();
                webDriver.findElement(By.className("Ypffh")).sendKeys(comment);
                webDriver.findElement(By.xpath("//button[contains(text(), 'Publicar')]")).click();
                updateValues();
                successCounter++;
            } catch (final Exception ex) {
                failureCounter++;
                successCounter--;
                updateValues();
                throw new PageFaultException();
            }
        }
    }

    /**
     * Método responsável por possibilitar que o robô atualize a página atual.
     */
    public void updateCurrentPage() {
        loadPage(webDriver.getCurrentUrl());
    }

    /**
     * Método responsável por possibilitar que o robô desconecte-se de dado perfil.
     */
    public void disconnect() {
        if (isConnected()) {
            webDriver.quit();
            webDriver = null;
            resetValues();
        }
    }

    /**
     * Método responsável por possibilitar que o robô indique se está conectado a dado perfil.
     * @return Retorna indicativo de que o robô está conectado a dado perfil.
     */
    public boolean isConnected() {
        return webDriver != null;
    }

    /**
     * Método responsável por possibilitar que o robô retorne sua mensagem de estado.
     * @return Retorna mensagem de estado.
     */
    public IMessage<String> getStateMessage() {
        return stateMessage;
    }

    /**
     * Classe responsável por fornecer textos mensagem emitidas pelo robô.
     * @author Everton Bruno Silva dos Santos.
     * @version 1.0
     */
    private final class TextMessage {
        /**
         * Refere-se a mensagem de configuração do robô.
         */
        private final static String SETTING_BOOT = "Ativo! Configurando Robô...";
        /**
         * Refere-se a mensagem de conexão do robô.
         */
        private final static String CONNECTING_BOOT = "Ativo! Conectando Perfil...";
        /**
         * Refere-se a mensagem de carregamento de página web.
         */
        private final static String LOADING_WEBPAGE = "Ativo! Carregando Página...";
        /**
         * Refere-se a mensagem de espera do robô por uma ação do usuário.
         */
        private final static String AWAITING_USER_ACTION = "Estático! Aguardando Ações de Usuário...";
    }

}