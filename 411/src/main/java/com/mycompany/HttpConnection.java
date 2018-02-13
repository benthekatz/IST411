/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ben
 */
public class HttpConnection implements Runnable {

    AddressListModel alm;
    AddressController ac;
    String validResponse = "HTTP/1.1 200 OK\r\n\r\n";

    public HttpConnection(AddressController inputAc, AddressListModel inputAlm) {
        ac = inputAc;
        alm = inputAlm;
    }

    @Override
    public void run() {
        try (ServerSocket server = new ServerSocket(1234)) {
            while (true) {
                try (Socket clientSocket = server.accept()) {

                    InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
                    BufferedReader reader = new BufferedReader(isr);
                    OutputStream out = clientSocket.getOutputStream();
                    String line = reader.readLine();
                    System.out.println(line);
                    
                    if (line.equals("GET / HTTP/1.1")) {
                        out.write((validResponse).getBytes());
                        out.write(FileView.serveView("index.html", clientSocket).getBytes("UTF-8"));
                        //Other Views    
                    } else if (line.contains("GET")) {
                        //Hello World!
                        if (line.contains("GET /hello HTTP/1.1")) {
                            out.write((validResponse).getBytes());
                            out.write(new HelloView().makeHTML().getBytes("UTF-8"));
                            //Address entry form
                        } else if (line.contains("GET /address HTTP/1.1")) {
                            out.write((validResponse).getBytes());
                            //creates a new AddressView
                            out.write(new AddressView().makeHTML().getBytes("UTF-8"));

                            //Processes Address Submission - decides view based on fields
                        } else if (line.contains("/submit")) {
                            out.write((validResponse).getBytes());
                            //creates a new SubmitView
                            if (AddressModel.isValid(line)) {
                                out.write(new SubmitView().makeHTML().getBytes("UTF-8"));
                                //pulls values from URL via controller
                                ac.pullValues(line, alm);
                            } else if (!AddressModel.isValid(line)) {
                                //invalid fields
                                out.write(new AddressView().makeHTMLInvalid().getBytes("UTF-8"));
                            }
                            //List of addresses
                        } else if (line.contains("GET /list HTTP/1.1")) {
                            //TODO: throw null list check
                            if (alm.models == null) {
                                out.write(("Address List is nonexistent").getBytes("UTF-8"));
                            } else if (alm.models.size() == 0) {
                                out.write(("Address List is empty").getBytes("UTF-8"));
                            } else {
                                out.write(alm.returnList().getBytes("UTF-8"));
                            }

                            //File Serve for /public folder
                        } else {
                            String URL = line;
                            URL = URL.replace(" HTTP/1.1", "");
                            URL = URL.replace("GET /", "");
                            System.out.println(URL);
                            out.write(FileView.serveView(URL, clientSocket).getBytes("UTF-8"));
                        }

                        //404 Error Page
                    } else {
                        clientSocket.getOutputStream().write(("404 Not Found\n").getBytes("UTF-8"));
                    }
                } catch (IOException ex) {
                    Logger.getLogger(HttpConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(HttpConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
