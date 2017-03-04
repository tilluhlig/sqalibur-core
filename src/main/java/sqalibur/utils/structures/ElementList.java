/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqalibur.utils.structures;

import java.util.ArrayList;
import java.util.List;
import org.jdom.Element;

/**
 *
 * @author Till Uhlig <till.uhlig@student.uni-halle.de>
 */
public class ElementList {
    private String name;
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
    
    public void addElement(Element newElement){
        this.elements.add(newElement);
    }

}
