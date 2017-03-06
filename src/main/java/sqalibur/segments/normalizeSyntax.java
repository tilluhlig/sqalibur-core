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

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ostepu.process.command;
import treeNormalizer.rule;
import treeNormalizer.ruleSet;
import treeNormalizer.transformation;

/**
 * Dieser Regelsatz enthät Regeln zum Normalisieren der Syntax.
 *
 * @author Till Uhlig <till.uhlig@student.uni-halle.de>
 */
public class normalizeSyntax extends ruleSet implements command {

    @Override
    public void execute(ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // enthält die Regeln dieser Sammlung
    private static List<rule> myRules = null;

    private static List<rule> initRuleSet() {
        // wer auch immer diese Regelsammlung nutzen will, soll sie nur 
        // einmal initialisieren
        if (myRules == null) {
            // hier werden die Regeln dieser Sammlung eingefügt
            myRules = new ArrayList<rule>();
            myRules.add(new sqalibur.segments.rules.knf());
            myRules.add(new sqalibur.segments.rules.sort());
        }
        return myRules;
    }

    @Override
    public boolean perform(transformation transform) {
        this.rules = initRuleSet();
        return super.perform(transform);
    }

}
