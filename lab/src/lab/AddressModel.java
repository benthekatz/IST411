/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

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
    
    public ArrayList<String> values = new ArrayList<String>();
    
    static boolean verify(String url) {
        boolean status = true;

        if (url.indexOf("?") >= 0) {
            String p = url.substring(url.indexOf("?") + 1);
            for (String pair : p.split("&")) {
                String[] keyVal = pair.split("=");
                if (keyVal.length == 2) {
                    if(keyVal[0].equals("zip")){
//                        if(!keyVal[1].toString().matches("[0-9]{5}")){
//                            System.out.print("check" + keyVal[1].toString());
//                            status = false;
//                        }
                    }
                } else if (keyVal.length == 1){
                    status = false;
                }
            }
        }
        return status;
    }

    public void readMap(Map<String, String> params) {
        name = params.get("name");
        street = params.get("street");
        state = params.get("state");
        zip = params.get("zip");
        
        values.add("Name: " + name);
        values.add("Street: " + street);
        values.add("State: " + state);
        values.add("Zip: " + zip);
        
        setValues(values);
    }
    
    public void setValues(ArrayList<String> input){
        this.values = input; 
    }
    
    public String getValues(){
        return values.toString();
    }
    
}
