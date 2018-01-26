/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ben
 */
public class AddressController {

    Map<String, String> params = new HashMap<>();
    
    AddressModel am = new AddressModel();

    public void pullValues(String url) {
        if (url.indexOf("?") >= 0) {
            String p = url.substring(url.indexOf("?") + 1);
            for (String pair : p.split("&")) {
                String[] keyVal = pair.split("=");
                if (keyVal.length == 2) {
                    if(keyVal[0].contains("zip")){
                        System.out.println(keyVal[0]);
                        keyVal[1] = keyVal[1].replace(" HTTP/1.1","");
                    }
                    params.put(keyVal[0], keyVal[1]);
                }
            }
            System.out.print(params.toString());
            am.createModel(params);
        }
    }
}
