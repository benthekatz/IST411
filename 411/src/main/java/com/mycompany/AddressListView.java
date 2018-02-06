/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Ben
 */
public class AddressListView extends ServerView {
    ArrayList<AddressModel>  values = new ArrayList<>();
    
    @Override
    public String makeHTML() {
        return values.toString();
    }
}
