/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqalibur.commands;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jsqlparser.Element;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.parser.JSqlParser;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.commons.io.IOUtils;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import ostepu.process.command;
import treeNormalizer.rule;
import treeNormalizer.transformation;
import sqalibur.sqlParser;
import treeNormalizer.utils.xsltProcessor;

/**
 *
 * @author Till Uhlig <till.uhlig@student.uni-halle.de>
 */
public class postSQLNormalize implements command {

    @Override
    public void execute(ServletContext context, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        // die Eingabe ist eine XML Datenstruktur
        String incomingSQL = IOUtils.toString(request.getInputStream());
        //incomingSQL = "select * from student, abc where (id = 101 or e_mail is null) or id = 102";
        Document sqlDocument = sqlParser.parse(incomingSQL);
               Calendar cal = Calendar.getInstance();
               
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        out.write(xsltProcessor.DocumentToXml(sqlDocument));
        out.write(sdf.format(cal.getTime()));
        response.setStatus(200);
    }
}
