/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Ben
 */
public class ListView extends ServerView {
    ArrayList<AddressModel>  values = new ArrayList<>();
    
    @Override
    String makeHTML() {
        return values.toString();
    }

    public String makeHTML(ArrayList<AddressModel> values) {
        return values.toString();
    }
}
