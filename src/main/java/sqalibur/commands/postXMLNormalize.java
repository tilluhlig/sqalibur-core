/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqalibur.commands;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
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
 *
 * @author Till Uhlig <till.uhlig@student.uni-halle.de>
 */
public class postXMLNormalize implements command {

    @Override
    public void execute(ServletContext context, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // die Eingabe ist eine XML Datenstruktur
        String incomingXML = IOUtils.toString(request.getInputStream());

        // diese sollen nun in eine Datenstruktur eingelesen werden
        
        
        // nun erfolgt die Normalisierung
        
        
        out.write("Ausgeben");
        response.setStatus(201);
    }

}
