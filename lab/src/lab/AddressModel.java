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
public class AddressModel {

    static boolean verify(String url) {
        boolean status = true;

        if (url.indexOf("?") >= 0) {
            String p = url.substring(url.indexOf("?") + 1);
            for (String pair : p.split("&")) {
                String[] keyVal = pair.split("=");
                if (keyVal.length == 2) {
                    if(keyVal.equals("zip")){
                        if(keyVal[1].length() == 5){
                            status = true;
                        } else { 
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
}
