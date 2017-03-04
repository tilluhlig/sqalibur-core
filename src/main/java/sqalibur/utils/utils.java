/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqalibur.utils;

import java.util.List;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.deparser.StatementDeParser;
import org.jdom.Document;
import org.jdom.Element;
import sqalibur.utils.structures.DocumentToJSQL;

/**
 *
 * @author Till Uhlig <till.uhlig@student.uni-halle.de>
 */
public class utils {

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
