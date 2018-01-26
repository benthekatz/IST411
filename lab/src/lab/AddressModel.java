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
    
    public AddressListModel alm = new AddressListModel();
    
    static boolean isValid(String url) {
        boolean status = true;

        if (url.indexOf("?") >= 0) {
            String p = url.substring(url.indexOf("?") + 1);
            for (String pair : p.split("&")) {
                String[] keyVal = pair.split("=");
                if (keyVal.length == 2) {
                    if(keyVal[0].equals("zip")){
                        keyVal[1] = keyVal[1].replace(" HTTP/1.1","");
                        if(!keyVal[1].toString().matches("[0-9]{5}")){
                            status = false;
                        }
                    }
                } else if (keyVal.length == 1){
                    status = false;
                }
            }
        }
        return status;
    }

    public void createModel(Map<String, String> params) {
        AddressModel am = new AddressModel();
        
        am.name = params.get("name");
        am.street = params.get("street");
        am.state = params.get("state");
        am.zip = params.get("zip");
        
        alm.storeModel(am);
        
        System.out.println(am);
    }
    
    
    public ArrayList<AddressModel> returnModels(){
        return alm.returnModels();
    }
    
}
