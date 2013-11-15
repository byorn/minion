/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Company1.Company.dtos;

import java.io.Serializable;

/**
 *
 * @author byorn
 */
public class SearchRoleDTO implements Serializable{

    public String getRolename() {
        return name;
    }

    public void setRolename(String name) {
        this.name = name;
    }
    private String name;
}
