/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ben
 */
public class AddressController {

    Map<String, String> params = new HashMap<>();

    public void pullValues(String url, AddressListModel alm) throws IOException {

        String name = null;
        String street = null;
        String state = null;
        String zip = null;

        if (url.indexOf("?") >= 0) {
            String p = url.substring(url.indexOf("?") + 1);
            for (String pair : p.split("&")) {
                String[] keyVal = pair.split("=");
                if (keyVal.length == 2) {
                    keyVal[1] = keyVal[1].replace("+"," ");
                    if (keyVal[0].contains("name")) {
                        name = keyVal[1];
                    }
                    if (keyVal[0].contains("street")) {
                        street = keyVal[1];
                    }
                    if (keyVal[0].contains("state")) {
                        state = keyVal[1];
                    }
                    if (keyVal[0].contains("zip")) {
                        keyVal[1] = keyVal[1].replace(" HTTP/1.1", "");
                        zip = keyVal[1];
                    }
                    params.put(keyVal[0], keyVal[1]);
                }
            }
        }

        AddressModel am = new AddressModel();
        am.createModel(name, street, state, zip, alm);
        
    }
}
