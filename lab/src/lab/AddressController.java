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

    public void pullValues(String url) {
        if (url.indexOf("?") >= 0) {
            String p = url.substring(url.indexOf("?") + 1);
            for (String pair : p.split("&")) {
                String[] keyVal = pair.split("=");
                if (keyVal.length == 2) {
                    params.put(keyVal[0], keyVal[1]);
                }
                new AddressModel().readMap(params);
            }
        }
    }
}
