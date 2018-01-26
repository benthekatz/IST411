/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

import java.util.ArrayList;

/**
 *
 * @author Ben
 */
public class AddressListModel {
    ArrayList<AddressModel> models = new ArrayList<>();
       
    void storeModel(AddressModel am) {
        models.add(am);
    }   
    
    public ArrayList<AddressModel> returnModels(){
        return models;
    }
}
