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

import static java.lang.Thread.sleep;

/**
 * Interface responsável por comportar-se como tempo.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.3
 */
public interface ITime {
    /**
     * Refere-se aos segundos em milisegundos.
     */
    public final static int SECOND = 1000;
    /**
     * Refere-se aos minutos em milisegundos.
     */
    public final static int MINUTE = 60 * SECOND;
    /**
     * Refere-se as horas em milisegundos.
     */
    public final static int HOUR = 60 * MINUTE;

    /**
     * Método responsável por retornar unidades de segundos.
     * @param units Refere-se as unidades.
     * @return Retorna unidades de segundos.
     */
    public static int seconds(final int units) {
        return units * SECOND;
    }

    /**
     * Método responsável por retornar unidades de minutos.
     * @param units Refere-se as unidades.
     * @return Retorna unidades de minutos.
     */
    public static int minutes(final int units) {
        return units * MINUTE;
    }

    /**
     * Método responsável por retornar unidades de horas.
     * @param units Refere-se as unidades.
     * @return Retorna unidades de horas.
     */
    public static int hours(final int units) {
        return units * HOUR;
    }

    /**
     * Método responsável por indicar se milisegundos correspondem a segundos.
     * @param milliseconds Refere-se aos milisegundos.
     * @return Retorna indicativo de que milisegundos correspondem a segundos.
     */
    public static boolean isSeconds(final int milliseconds) {
        return milliseconds < MINUTE && milliseconds >= SECOND;
    }

    /**
     * Método responsável por indicar se milisegundos correspondem a minutos.
     * @param milliseconds Refere-se aos milisegundos.
     * @return Retorna indicativo de que milisegundos correspondem a minutos.
     */
    public static boolean isMinutes(final int milliseconds) {
        return milliseconds < HOUR && milliseconds >= MINUTE;
    }

    /**
     * Método responsável por indicar se milisegundos correspondem a horas.
     * @param milliseconds Refere-se aos milisegundos.
     * @return Retorna indicativo de que milisegundos correspondem a horas.
     */
    public static boolean isHours(final int milliseconds) {
        return milliseconds >= HOUR;
    }

    /**
     * Método responsável por fazer com que um dada thread seja interrompida temporáriamente.
     * @param frequency    Refere-se a frequência de envio de mensagem.
     * @param currentTime  Refere-se ao tempo atual.
     * @param milliseconds Refere-se ao mensagem de tempo restante.
     * @throws InterruptedException Exceção lançada no caso da thread ser interrompida.
     */
    public static void stop(final int frequency, final int currentTime, final IMessage<Integer> milliseconds) throws InterruptedException {
        if (currentTime > 0) {
            milliseconds.send(currentTime);
            sleep(frequency);
            stop(frequency, currentTime - frequency, milliseconds);
        }
    }

    /**
     * Interface responsável por efetuar formatações de unidades de tempo.
     * @author Everton Bruno Silva dos Santos.
     * @version 1.3
     */
    public interface Format {

        /**
         * Método responsável por converter milissegundos em segundos.
         * @param milliseconds Refere-se aos milissegundos.
         * @return Retorna milissegundos convertidos em segundos.
         */
        public static String seconds(final int milliseconds) {
            final int second = milliseconds / SECOND;
            return (second <= 9) ? "0" + second : Integer.toString(second);
        }

        /**
         * Método responsável por converter milissegundos em minutos.
         * @param milliseconds Refere-se aos milissegundos.
         * @return Retorna milissegundos convertidos em minutos.
         */
        public static String minutes(final int milliseconds) {
            final int minute = milliseconds / MINUTE;
            final int second = milliseconds - minute * MINUTE;
            return (minute <= 9) ? "0" + minute + ":" + seconds(second) : minute + ":" + seconds(second);
        }

        /**
         * Método responsável por converter milissegundos em horas.
         * @param milliseconds Refere-se aos milissegundos.
         * @return Retorna milissegundos convertidos em horas.
         */
        public static String hours(final int milliseconds) {
            final int hour = milliseconds / HOUR;
            final int minute = milliseconds - hour * HOUR;
            return (hour <= 9) ? "0" + hour + ":" + minutes(minute) : hour + ":" + minutes(minute);
        }

    }

}
