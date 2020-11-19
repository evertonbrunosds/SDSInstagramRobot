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

/**
 * Interface responsável por comportar-se como container.
 * @author Everton Bruno Silva dos Santos.
 * @param <D> Refere-se ao tipo de dados contido no container.
 * @version 1.0
 */
@FunctionalInterface
public interface Container<D> {

    /**
     * Método responsável por retornar o dado contido no container.
     * @return Retorna o dado contido no container.
     */
    public D freeGet();

    /**
     * Método responsável por retornar o dado contido no container de maneira tratada.
     * @return Retorna o dado contido no container de maneira tratada.
     * @throws EmptyContainerException Exceção lançada no caso do container estar vazio.
     */
    default D safeGet() throws EmptyContainerException {
        final D data = freeGet();
        if (data == null) {
            throw new EmptyContainerException();
        } else {
            return data;
        }
    }

    /**
     * Classe responsável por comportar-se como exceção de container vazio.
     * @author Everton Bruno Silva dos Santos.
     * @version 1.0
     */
    public class EmptyContainerException extends Exception {
        /**
         * Refere-se ao número de série da classe.
         */
        private static final long serialVersionUID = -7760928134757912564L;
    }

}
