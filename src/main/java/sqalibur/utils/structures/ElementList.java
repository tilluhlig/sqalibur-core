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
package sqalibur.utils.structures;

import java.util.ArrayList;
import java.util.List;
import org.jdom.Element;

/**
 * Eine Liste von Elementen
 *
 * @author Till Uhlig <till.uhlig@student.uni-halle.de>
 */
public class ElementList {

    /*
     * ein Name
     */
    private String name;

    /*
     * die Liste der Elemente (Kindelemente)
     */
    private List<Element> elements = new ArrayList<Element>();

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the elements
     */
    public List<Element> getElements() {
        return elements;
    }

    /**
     * @param elements the elements to set
     */
    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    /**
     * fügt ein Element hinzu
     *
     * @param newElement das neue Element
     */
    public void addElement(Element newElement) {
        this.elements.add(newElement);
    }

}
