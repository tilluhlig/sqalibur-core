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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.jdom.Document;
import ostepu.cconfig.cconfig;
import ostepu.file.fileUtils;
import ostepu.process.command;
import ostepu.request.httpAuth;
import ostepu.structure.attachment;
import ostepu.structure.component;
import ostepu.structure.file;
import ostepu.structure.marking;
import ostepu.structure.process;
import sqalibur.sqlParser;
import treeNormalizer.normalization;
import treeNormalizer.simpleNormalization.simpleNormalization;
import treeNormalizer.utils.treeUtilities;

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

        if (incomingProcessString == "") {
            // es wurden keine Daten übermittelt
            response.setStatus(412);
            out.write("no input found");
            return;
        }

        process processObject = (process) process.decode(incomingProcessString);

        if (processObject == null) {
            // es wurden keine vernünftigen Daten übermittelt
            response.setStatus(412);
            out.write("no proper input found");
            return;
        }

        if (processObject.getRawSubmission() == null) {
            // es wurde keine Einsendung gefunden
            response.setStatus(412);
            out.write("no submission found");
            return;
        }

        file submissionFile = processObject.getRawSubmission().getFile();

        if (submissionFile == null) {
            // es gibt zu der Einsendung keine Datei
            response.setStatus(412);
            out.write("found no file object in submission");
            return;
        }

        if (processObject.getAttachment() == null || processObject.getAttachment().size() == 0) {
            // es wurde kein Anhang gefunden (ist OK)
            // dann müssen wir auch nichts prüfen
            response.setStatus(201);
            return;
        }

        if (processObject.getAttachment().size() > 2) {
            // wir haben zu viele Anhänge
            response.setStatus(412);
            out.write("too much attachments ... what's your problem?");
            return;
        }

        List<attachment> attachments = processObject.getAttachment();
        List<file> attachmentFiles = new ArrayList<file>();

        for (attachment ff : attachments) {
            if (ff.getFile() == null) {
                // eine der Anhänge hat keinen gültigen Dateieintrag
                response.setStatus(412);
                out.write("missing file in attachment object");
                return;
            }
            attachmentFiles.add(ff.getFile());
        }

        // lädt die Anmeldedaten (eventuell für eine httpAuth)
        httpAuth.loadLocalAuthData(context);

        byte[] submission = fileUtils.getFile(context, submissionFile, true, new httpAuth()); // er verwendet dazu die zuvor geladenen Anmeldedaten
        if (submission == null) {
            // die Einsendung konnte nicht abgerufen werden
            response.setStatus(409);
            out.write("can't receive the submission from main system");
            return;
        }
        String submissionData = new String(submission);

        // jetzt wollen wir die Anhänge vom Hauptsystem einsammeln
        List<String> attachmentData = new ArrayList<String>();
        for (file ff : attachmentFiles) {
            byte[] attachment = fileUtils.getFile(context, ff, true, new httpAuth()); // er verwendet dazu die zuvor geladenen Anmeldedaten
            if (attachment == null) {
                // der Anhang konnte nicht abgerufen werden
                response.setStatus(409);
                out.write("can't receive the attachment from main system");
                return;
            }
            attachmentData.add(new String(attachment));
        }

        // nun besitzen wir die Einsendung und alle Anhänge
        // sodass wir die Normalisierung initialisieren können
        normalization normalization = new simpleNormalization();
        Document submissionDocument = sqlParser.parse(submissionData);
        normalization.setSubmission(submissionDocument);

        List<Document> attachmentDocuments = new ArrayList<Document>();
        for (String att : attachmentData) {
            attachmentDocuments.add(sqlParser.parse(att));
        }

        if (attachmentDocuments.size() == 1) {
            // wenn wir nur einen Anhang haben, dann wird er als Musterlösung verwendet
            normalization.setSolution(attachmentDocuments.get(0));
        } else if (attachmentDocuments.size() == 2) {
            // jetzt müssen wir herausfinden, welche die Musterlösung und welche der Kontext ist
            String typeA = treeUtilities.getQueryType(attachmentDocuments.get(0));
            String typeB = treeUtilities.getQueryType(attachmentDocuments.get(1));
            if (typeA == "CreateTable" && typeB != "CreateTable") {
                normalization.setContext(attachmentDocuments.get(0));
                normalization.setSolution(attachmentDocuments.get(1));
            } else if (typeB == "CreateTable" && typeA != "CreateTable") {
                normalization.setContext(attachmentDocuments.get(1));
                normalization.setSolution(attachmentDocuments.get(0));
            } else {
                response.setStatus(409);
                out.write("can't classify the attachments as solution and context (i need a CreateTable and another query type)");
                return;
            }
        } else {
            // wieso haben wir mehr als 2 Anhänge???
            response.setStatus(409);
            out.write("too much attachments, after reading all attachments");
            return;
        }

        // führt die Normalisierung aus
        normalization.perform();

        boolean equivalence = normalization.equivalent();

        marking markingObject = new marking();
        file markingFile = new file();
        markingFile.setDisplayName("Bericht.txt");
        markingFile.setBody(fileUtils.encodeBase64("Ihre Einsendung ist nicht aequivalent zur Musterloesung!!!"));
        markingObject.setFile(markingFile);
        markingObject.setPoints(processObject.getExercise().getMaxPoints());
        markingObject.setStatus(marking.AUTOMATISCH_STATUS);
        processObject.setMarking(markingObject);
        
        processObject.setSubmission(processObject.getRawSubmission());
        processObject.setMessages(new String[]{"Die Einsendung wurde durch SQaLibur bewertet."});

        processObject.setStatus("201");
        out.write(processObject.encode());
        response.setStatus(201);
    }

}
