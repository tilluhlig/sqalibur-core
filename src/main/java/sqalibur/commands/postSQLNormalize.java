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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.jdom.Document;
import ostepu.process.command;
import sqalibur.utils.sqlParser;
import treeNormalizer.utils.xsltProcessor;

/**
 * dieser Befehl nimmt SQL entgegen und gibt eine XML-Struktur zurück
 *
 * @author Till Uhlig <till.uhlig@student.uni-halle.de>
 */
public class postSQLNormalize implements command {

    @Override
    public void execute(ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out;
        try {
            out = response.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(postSQLNormalize.class.getName()).log(Level.SEVERE, null, ex);
            response.setStatus(500);
            return;
        }
        // die Eingabe ist eine XML Datenstruktur
        String incomingSQL;
        try {
            incomingSQL = IOUtils.toString(request.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(postSQLNormalize.class.getName()).log(Level.SEVERE, null, ex);
            response.setStatus(500, "can't read incoming data");
            return;
        }
        ///incomingSQL = "SELECT first, last FROM student, abc WHERE (id = 101 OR e_mail IS NULL) OR id = 102";
        Document sqlDocument = sqlParser.parse(incomingSQL);

        // TODO: Normalisierung ???
        out.write(xsltProcessor.DocumentToXml(sqlDocument));
        ///Calendar cal = Calendar.getInstance();
        ////SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        ////out.write(sdf.format(cal.getTime()));
        response.setStatus(200);
    }

}
