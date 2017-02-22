/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqalibur;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
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
            visitNode(root, a);
        } catch (JSQLParserException ex) {
            Logger.getLogger(sqlParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return document;
    }

    public static void visitNode(Element parent, net.sf.jsqlparser.Element q) {

        Element newElement = new Element("node");
        parent.addContent(newElement);
        newElement.setAttribute("class", q.getClass().getSimpleName());
        if (q.getStringValue() != null) {
            newElement.setAttribute("label", q.getStringValue());
        }

        net.sf.jsqlparser.Element[] childs = q.getChildren();
        if (childs == null) {
            return;
        }
        for (net.sf.jsqlparser.Element q3 : childs) {
            if (q3 == null) {
                Element newElement2 = new Element("node");
                newElement.addContent(newElement2);
            } else {
                visitNode(newElement, q3);
            }
        }
    }

}
