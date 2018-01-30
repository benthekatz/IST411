/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ben
 */
public class AddressModel {

    public String name;
    public String street;
    public String state;
    public String zip;

    static boolean isValid(String url) {
        boolean status = true;

        if (url.indexOf("?") >= 0) {
            String p = url.substring(url.indexOf("?") + 1);
            for (String pair : p.split("&")) {
                String[] keyVal = pair.split("=");
                if (keyVal.length == 2) {
                    if (keyVal[0].equals("zip")) {
                        keyVal[1] = keyVal[1].replace(" HTTP/1.1", "");
                        if (!keyVal[1].toString().matches("[0-9]{5}")) {
                            status = false;
                        }
                    }
                } else if (keyVal.length == 1) {
                    status = false;
                }
            }
        }
        return status;
    }

    public void createModel(String name, String street, String state, String zip, AddressListModel alm) throws IOException {
        this.name = name;
        this.street = street;
        this.state = state;
        this.zip = zip;
        
        alm.addModel(this);
    }

    public String toString() {
        return "Name: " + name + "<br>"
                + "Street: " + street + "<br>"
                + "State: " + state + "<br>"
                + "Zip: " + zip + "<br>" + "<br>";
    }

    public String serializeToString() {
        return "name=" + name + "&"
                + "street=" + street + "&"
                + "state=" + state + "&"
                + "zip=" + zip;
    }

    static AddressModel deSerializeFromString(String s) {
        AddressModel am = new AddressModel();
        for (String pair : s.split("&")) {
            String[] keyVal = pair.split("=");
            if (keyVal.length == 2) {
                if (keyVal[0].contains("name")) {
                    am.name = keyVal[1];
                }
                if (keyVal[0].contains("street")) {
                    am.street = keyVal[1];
                }
                if (keyVal[0].contains("state")) {
                    am.state = keyVal[1];
                }
                if (keyVal[0].contains("zip")) {
                    am.zip = keyVal[1];
                }
            } else {
                return am;
            }
        }
        return am;
    }

}
