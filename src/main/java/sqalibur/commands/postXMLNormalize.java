/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqalibur.commands;

import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import ostepu.cconfig.cconfig;
import ostepu.process.command;
import ostepu.structure.component;
import ostepu.structure.process;
import sqalibur.segments.rules.knf;
import treeNormalizer.rule;
import treeNormalizer.transformation;
import treeNormalizer.utils.xsltProcessor;

/**
 *
 * @author Till Uhlig <till.uhlig@student.uni-halle.de>
 */
public class postXMLNormalize implements command {

    @Override
    public void execute(ServletContext context, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();

            // die Eingabe ist eine XML Datenstruktur
            String incomingXML = IOUtils.toString(request.getInputStream());

            // diese sollen nun in eine Datenstruktur eingelesen werden
            InputStream stream = new ByteArrayInputStream(incomingXML.getBytes(StandardCharsets.UTF_8));
            Document document = new SAXBuilder().build(stream);

            rule a = new sqalibur.segments.rules.knf();
            rule a2 = new sqalibur.segments.rules.sort();
            transformation b = new transformation();
            b.setTree(document);
            a.perform(b);
            a2.perform(b);

            out.write(xsltProcessor.DocumentToXml(b.getTree()));
            response.setStatus(201);
        } catch (JDOMException ex) {
            Logger.getLogger(postXMLNormalize.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
