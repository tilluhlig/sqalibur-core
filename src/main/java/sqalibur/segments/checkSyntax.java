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
package sqalibur.segments;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ostepu.process.command;

/**
 * diese Klasse soll die Syntaxprüfung durchführen
 *
 * @author Till Uhlig {@literal <till.uhlig@student.uni-halle.de>}
 */
public class checkSyntax implements command {

    // wir haben aber noch niemanden, der die Syntax prüfen kann (und uns Meldungen liefern kann)
    @Override
    public void execute(ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out;
        try {
            out = response.getWriter();
        } catch (IOException ex) {
            response.setStatus(500);
            Logger.getLogger(checkSyntax.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        response.setStatus(201);
    }

}
