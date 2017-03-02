/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqalibur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.Statements;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.deparser.StatementDeParser;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Till Uhlig <till.uhlig@student.uni-halle.de>
 */
public class sqlParser {

    public static Document parse(String sql) {
        Document document = new Document();
        Element root = new Element("root");
        document.setRootElement(root);
        try {

            Statement a = CCJSqlParserUtil.parse(sql);
         //   StringBuilder b = new StringBuilder();
         //   StatementDeParser c = new StatementDeParser(b);
          //  if (a instanceof Select) {
          //      c.visit((Select) a);
          //  }
           // StringBuilder q = c.getBuffer();
            JSQLToDocument m = new JSQLToDocument();
            root.addContent(m.visit(a));
        } catch (JSQLParserException ex) {
            Logger.getLogger(sqlParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return document;
    }

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
