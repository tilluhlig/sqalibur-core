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
package sqalibur.commands;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import ostepu.process.command;
import treeNormalizer.rule;
import treeNormalizer.transformation;
import treeNormalizer.utils.xsltProcessor;

/**
 * dieser Befehl nimmt eine Anfrage als XML-Baum entgegen und liefert XML
 *
 * @author Till Uhlig {@literal <till.uhlig@student.uni-halle.de>}
 */
public class postXMLNormalize implements command {

    @Override
    public void execute(ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out;
            try {
                out = response.getWriter();
            } catch (IOException ex) {
                Logger.getLogger(postXMLNormalize.class.getName()).log(Level.SEVERE, null, ex);
                response.setStatus(500);
                return;
            }

            // die Eingabe ist eine XML Datenstruktur
            String incomingXML;
            try {
                incomingXML = IOUtils.toString(request.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(postXMLNormalize.class.getName()).log(Level.SEVERE, null, ex);
                response.setStatus(500);
                return;
            }

            // diese sollen nun in eine Datenstruktur eingelesen werden
            InputStream stream = new ByteArrayInputStream(incomingXML.getBytes(StandardCharsets.UTF_8));
            Document document;
            try {
                document = new SAXBuilder().build(stream);
            } catch (IOException ex) {
                Logger.getLogger(postXMLNormalize.class.getName()).log(Level.SEVERE, null, ex);
                response.setStatus(500);
                return;
            }

            // ab hier werden ein paar Regeln angewendet
            rule a = new sqalibur.segments.rules.knf();
            rule a2 = new sqalibur.segments.rules.sort();
            transformation b = new transformation();
            b.setTree(document);
            a.perform(b);
            a2.perform(b);

            // jetzt wird das Transformierte als XML ausgegeben
            out.write(xsltProcessor.DocumentToXml(b.getTree()));
            response.setStatus(201);
        } catch (JDOMException ex) {
            Logger.getLogger(postXMLNormalize.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
