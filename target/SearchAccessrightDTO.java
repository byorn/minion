/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package softwareperson.framework.dtos;

import java.io.Serializable;

/**
 *
 * @author byorn
 */
public class SearchAccessrightDTO implements Serializable{

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private String name;
}
