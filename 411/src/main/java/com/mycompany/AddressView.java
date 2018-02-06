/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

/**
 *
 * @author Ben
 */
public class AddressView extends ServerView {

    @Override
    public String makeHTML() {
        return "<html><body>"
                + "<form action='/submit'>"
                + "Name: <input type='text' name='name'><br>"
                + "Street: <input type='text' name='street'><br>"
                + "State: <input type='text' name='state'><br>"
                + "Zip: <input type='text' name='zip'><br>"
                + "<input type='submit' value='Submit'><br>"
                + "</form>"
                + "</body></html>";
    }

    public String makeHTMLInvalid() {
        return "<html><body>"
                + "<form action='/submit'>"
                + "Name: <input type='text' name='name'><br>"
                + "Street: <input type='text' name='street'><br>"
                + "State: <input type='text' name='state'><br>"
                + "Zip: <input type='text' name='zip'><br>"
                + "<input type='submit' value='Submit'><br>"
                + "</form>"
                + "<p style='color:red'>Invalid Field(s)</p>"
                + "</body></html>";
    }
}
