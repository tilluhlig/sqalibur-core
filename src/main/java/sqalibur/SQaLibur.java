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
package sqalibur;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import ostepu.process.command;

/**
 * Das ist die Hauptklasse der SQaLibur-Komponente, diese behandelt /process,
 * /compute und /sql Aufrufe. Dabei wählt sie den entsprechende Unteraufruf aus.
 *
 * @author Till Uhlig <till.uhlig@student.uni-halle.de>
 */
@WebServlet(urlPatterns = {})
public class SQaLibur extends HttpServlet {

    /**
     * diese Befehle bieten wir an
     */
    public String[][] restPattern = {
        {"POST", "/process"}, // bearbeitet eine Einsendung eines Studenten
        {"POST", "/compute"}, // dieser Befehl wird von OSTEPU aufgerufen (damit ich de nächsten Testfall bearbeite)
        {"POST", "/sql/xml/normalize"}, // normalisiert eine XML Eingabe
        {"GET", "/sql/sql/normalize"}, // normalisiert eine SQL Eingabe
        {"POST", "/sql/sql/checkSyntax"}, // prüft eine SQL Eingabe auf Syntaxfehler
        {"POST", "/sql/sql/checkSemantics"}, // prüft die Semantik der Eingabe
        {"POST", "/sql/sql/normalizeSyntax"}, // normalisiert die Syntax
        {"POST", "/sql/sql/normalizeSemantics"}, // normalisiert die Semantik
        {"POST", "/sql/sql/formatter"}};

    /**
     * diese Aufrufe werden ausgeführt, wenn die restPattern einen Treffer
     * liefert
     */
    public command[] restCommands = {
        new sqalibur.commands.postProcess(),
        null,
        new sqalibur.commands.postXMLNormalize(),
        new sqalibur.commands.postSQLNormalize(),
        null,
        null,
        null,
        null,
        null};

    /**
     * behandelt alle Anfragetypen und wählt die entsprechende Unterfunktion
     *
     * @param request  die eingehende Anfrage
     * @param response das Antwortobjekt
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out;
        try {
            out = response.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(SQaLibur.class.getName()).log(Level.SEVERE, null, ex);
            response.setStatus(500);
            return;
        }

        String a = request.getRequestURI();
        int b = request.getContextPath().length();
        String pathInfo = StringUtils.substring(a, b);
        String method = request.getMethod();

        try {
            Matcher matcher;
            for (int i = 0; i < restPattern.length; i++) {
                String[] pattern = restPattern[i];
                if (method == null ? pattern[0] != null : !method.equals(pattern[0])) {
                    continue;
                }
                Pattern regExGetCommands = Pattern.compile(pattern[1]);
                matcher = regExGetCommands.matcher(pathInfo);
                if (matcher.find()) {
                    if (restCommands[i] != null) {
                        restCommands[i].execute(getServletContext(), request, response);
                    }
                    return;
                }
            }

        } catch (Exception e) {
            try {
                response.sendError(409);
            } catch (IOException ex) {
                Logger.getLogger(SQaLibur.class.getName()).log(Level.SEVERE, null, ex);
                response.setStatus(500);
            }
        } finally {
            out.close();
        }
    }

    /**
     * behandelt ein GET
     *
     * @param request  die eingehende Anfrage
     * @param response das Antwortobjekt
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SQaLibur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * behandelt ein POST
     *
     * @param request  die eingehende Anfrage
     * @param response das Antwortobjekt
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SQaLibur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * behandelt ein DELETE
     *
     * @param request  die eingehende Anfrage
     * @param response das Antwortobjekt
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SQaLibur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * behandelt ein PUT
     *
     * @param request  die eingehende Anfrage
     * @param response das Antwortobjekt
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SQaLibur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "die SQaLibur-Komponente normalisiert SQL-Eingaben und prüft die Äquivalenz einer Einsendung zu einer Musterlösung.";
    }// </editor-fold>

}
