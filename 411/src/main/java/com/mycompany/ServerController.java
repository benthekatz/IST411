/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.io.IOException;

/**
 *
 * @author Ben
 */
public class ServerController {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        AddressController ac = new AddressController();
        String filename = "address_list.txt";
        AddressListModel alm = AddressListModel.makeAddressListFromFile(filename);

        HttpConnection hc = new HttpConnection(ac, alm);

        Thread t1 = new Thread(hc);
        t1.start();

    }

}
