/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String jsonFilename = "address_data.json";
        AddressListModel alm = AddressListModel.makeAddressListFromFile(filename);

        try (ServerSocket server = new ServerSocket(1234)) {
            System.out.println("Listening for connection on port 1234.");
            while (true) {
                try (Socket clientSocket = server.accept()) {

                    InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
                    BufferedReader reader = new BufferedReader(isr);
                    String line = reader.readLine();
                    System.out.println(line);

                    //http://localhost:1234/
                    if (line.equals("GET / HTTP/1.1")) {
                        clientSocket.getOutputStream().write(FileView.serveView("index.html", clientSocket).getBytes("UTF-8"));

                    //Other Views    
                    } else if (line.contains("GET")) {
                        if (line.contains("GET /hello HTTP/1.1")) {
                            //creates a new HelloView
                            clientSocket.getOutputStream().write(new HelloView().makeHTML().getBytes("UTF-8"));

                            //Address entry form
                        } else if (line.contains("GET /address HTTP/1.1")) {
                            //creates a new AddressView
                            clientSocket.getOutputStream().write(new AddressView().makeHTML().getBytes("UTF-8"));

                            //Processes Address Submission - decides view based on fields
                        } else if (line.contains("/submit")) {
                            //creates a new SubmitView
                            if (AddressModel.isValid(line)) {
                                clientSocket.getOutputStream().write(new SubmitView().makeHTML().getBytes("UTF-8"));
                                //pulls values from URL via controller
                                ac.pullValues(line, alm);
                            } else if (!AddressModel.isValid(line)) {
                                //invalid fields
                                clientSocket.getOutputStream().write(new AddressView().makeHTMLInvalid().getBytes("UTF-8"));
                            }

                            //List of addresses
                        } else if (line.contains("GET /list HTTP/1.1")) {
                            //TODO: throw null list check
                            if (alm.models == null) {
                                clientSocket.getOutputStream().write(("Address List is nonexistent").getBytes("UTF-8"));
                            } else if (alm.models.size() == 0) {
                                clientSocket.getOutputStream().write(("Address List is empty").getBytes("UTF-8"));
                            } else {
                                clientSocket.getOutputStream().write(alm.returnList().getBytes("UTF-8"));
                            }

                            //JSON proof of concept  
                        } else if (line.contains("GET /data HTTP/1.1")) {
                            clientSocket.getOutputStream().write((new AddressListDataView().readFile(jsonFilename).getBytes("UTF-8")));

                            //File name in URL searches [/public/*] and displays in browser
                        } else {
                            String URL = line;
                            URL = URL.replace(" HTTP/1.1", "");
                            URL = URL.replace("GET /", "");
                            System.out.println(URL);
                            //clientSocket.getOutputStream().write(FileView.serveView(URL, clientSocket).getBytes("UTF-8"));
                        }

                    //404 Error Page
                    } else {
                        clientSocket.getOutputStream().write(("404 Not Found\n").getBytes("UTF-8"));
                    }

                    line = reader.readLine();

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
