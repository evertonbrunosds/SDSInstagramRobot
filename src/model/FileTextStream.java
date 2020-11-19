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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collection;

/**
 * Classe responsável por comportar-se como arquivo de texto em fluxo.
 * @author Everton Bruno Silva dos Santos.
 * @version 1.0
 */
public final class FileTextStream {

    /**
     * Método responsável por carregar do arquivo.
     * @param fileName Refere-se ao nome do arquivo.
     * @param lines    Refere-se as linhas do dado arquivo.
     * @throws IOException Exceção lançada caso hajam problemas de entrada/saída.
     */
    public static void loadFromFile(final String fileName, final IMessage<String> lines) throws IOException {
        try (final BufferedReader fileStream = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            fileStream.lines().forEach(lines::send);
        }
    }

    /**
     * Método responsável por gravar em arquivo.
     * @param fileName Refere-se ao nome do arquivo.
     * @param lines    Refere-se as linhas do dado arquivo.
     * @throws IOException Exceção lançada caso hajam problemas de entrada/saída.
     */
    public static void saveToFile(final String fileName, final Collection<String> lines) throws IOException {
        try (final PrintWriter fileStream = new PrintWriter(new FileOutputStream(fileName, false))) {
            lines.forEach(fileStream::println);
        }
    }

}
