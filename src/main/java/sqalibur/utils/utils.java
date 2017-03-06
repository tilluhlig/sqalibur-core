/*
 * Copyright (C) 2017 Till Uhlig <till.uhlig@student.uni-halle.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package sqalibur.utils;

import java.util.List;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.deparser.StatementDeParser;
import org.jdom.Document;
import org.jdom.Element;

/**
 * Diese Klasse bietet Hilfsfunktionen an
 *
 * @author Till Uhlig <till.uhlig@student.uni-halle.de>
 */
public class utils {

    /**
     * wandelt Document in eine Textdarstellung von SQL um
     *
     * @param a das Eingabedokument
     * @return die SQL-Anfrage
     */
    public static String DocumentToSQL(Document a) {

        String q3;
        try {
            DocumentToJSQL m2 = new DocumentToJSQL();
            List<Element> ll = a.getRootElement().getChildren();
            Statement a2 = (Statement) m2.visit(ll.get(0));
            StringBuilder b = new StringBuilder();
            StatementDeParser c = new StatementDeParser(b);
            if (a2 instanceof Select) {
                c.visit((Select) a2);
            }
            StringBuilder q2 = c.getBuffer();
            q3 = q2.toString();
        } catch (Exception e) {
            return null;
        }
        return q3;
    }

}
