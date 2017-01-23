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

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import ostepu.cconfig.cconfig;
import ostepu.process.command;
import ostepu.structure.component;
import ostepu.structure.process;

/**
 * Dieser Befehl bearbeitet eingehende Korrekturanfragen (wenn ein Student also
 * etwas einsendet)
 *
 * @author Till
 */
public class postProcess implements command {

    @Override
    public void execute(ServletContext context, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        component conf = cconfig.loadConfig(context);
        JsonObject mycomponent = cconfig.loadComponent(context);

        String incomingProcessString = IOUtils.toString(request.getInputStream());
        process processObject = (process) process.decode(incomingProcessString);

        if (processObject == null) {
            response.setStatus(412);
            out.write("no proper input found");
            return;
        }

        processObject.setStatus("201");
        // ausfüllen
        out.write(processObject.encode());
        response.setStatus(201);
    }

}
