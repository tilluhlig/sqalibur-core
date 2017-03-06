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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.Statements;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.deparser.StatementDeParser;
import org.jdom.Document;
import org.jdom.Element;

/**
 *
 * @author Till Uhlig <till.uhlig@student.uni-halle.de>
 */
public class sqlParser {

    /**
     * interpretiert ein Anfrage als Text und wandelt diese in eine
     * Document-Darstellung um
     *
     * @param sql die Anfrage
     * @return die Anfrage als Document
     */
    public static Document parse(String sql) {
        Document document = new Document();
        Element root = new Element("root");
        document.setRootElement(root);
        try {

            Statement a = CCJSqlParserUtil.parse(sql);

            JSQLToDocument m = new JSQLToDocument();
            root.addContent(m.visit(a));

            DocumentToJSQL m2 = new DocumentToJSQL();
            List<Element> ll = root.getChildren();
            Statement a2 = (Statement) m2.visit(ll.get(0));

            int q = 1;

            StringBuilder b = new StringBuilder();
            StatementDeParser c = new StatementDeParser(b);
            if (a2 instanceof Select) {
                c.visit((Select) a2);
            }
            StringBuilder q2 = c.getBuffer();
            String q3 = q2.toString();
        } catch (JSQLParserException ex) {
            Logger.getLogger(sqlParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return document;
    }

    /**
     * interpretiert Anfragen als Text und wandelt diese in eine
     * Document-Darstellung um
     *
     * @param sql die Anfragen
     * @return die Anfrage als Document
     */
    public static List<Document> parseStatements(String sql) {
        List<Document> documents = new ArrayList<Document>();

        try {
            Statements b = CCJSqlParserUtil.parseStatements(sql);
            List<Statement> q = b.getStatements();

            for (Statement a : q) {
                Element root = new Element("root");
                Document document = new Document();
                document.setRootElement(root);
                JSQLToDocument m = new JSQLToDocument();
                root.addContent(m.visit(a));
                documents.add(document);
            }
        } catch (JSQLParserException ex) {
            Logger.getLogger(sqlParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return documents;
    }

}
