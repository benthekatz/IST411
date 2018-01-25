/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
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
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(1234)) {
            System.out.println("Listening for connection on port 1234.");
            while (true) {
                try (Socket clientSocket = server.accept()) {

                    InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
                    BufferedReader reader = new BufferedReader(isr);
                    String line = reader.readLine();
                    System.out.println(line);

                    AddressController ac = new AddressController();
                    AddressModel am = new AddressModel();
                    ListView lv = new ListView();
                    
                    ArrayList<String> values;

                    if (line.contains("GET")) {
                        if (line.contains("/hello HTTP/1.1")) {
                            //creates a new HelloView
                            clientSocket.getOutputStream().write(new HelloView().makeHTML().getBytes("UTF-8"));
                        } else if (line.contains("/address HTTP/1.1")) {
                            //creates a new AddressView
                            clientSocket.getOutputStream().write(new AddressView().makeHTML().getBytes("UTF-8"));
                        } else if (line.contains("/submit")) {
                            //creates a new SubmitView
                            if (AddressModel.verify(line)) {
                                clientSocket.getOutputStream().write(new SubmitView().makeHTML().getBytes("UTF-8"));
                                //pulls values from URL via controller
                                ac.pullValues(line);
                            } else if (!AddressModel.verify(line)) {
                                //invalid fields
                                clientSocket.getOutputStream().write(new AddressView().makeHTMLInvalid().getBytes("UTF-8"));
                            }
                        } else if (line.contains("/list")) {
                            //creates a new ListView
                            clientSocket.getOutputStream().write(lv.makeHTML(am.values).getBytes("UTF-8"));
                        } else if (line.contains("HTTP/1.1")) {
                            String home = "Default home page";
                            clientSocket.getOutputStream().write(home.getBytes("UTF-8"));
                        }
                    } else {
                        String error = "404";
                        clientSocket.getOutputStream().write(error.getBytes("UTF-8"));
                    }
                    line = reader.readLine();

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
