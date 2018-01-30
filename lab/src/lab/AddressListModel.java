/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static lab.AddressModel.deSerializeFromString;

/**
 *
 * @author Ben
 */
public class AddressListModel {

    ArrayList<AddressModel> models = new ArrayList<>();

    void storeModel(AddressModel am) {
        models.add(am);
    }

    public void addModel(AddressModel toAdd) throws IOException {
        models.add(toAdd);
        saveToFile("addressList.txt");
    }

    public ArrayList getModels() {
        return models;
    }

    public ArrayList<AddressModel> returnModels() {
        return models;
    }

    public void saveToFile(String filename) throws IOException {
        FileWriter fw = new FileWriter(filename);
        for (int i = 0; i < this.returnModels().size(); i++) {
            fw.write(this.returnModels().get(i).serializeToString() + "\n");
        }
        fw.close();
    }

    static AddressListModel makeAddressListFromFile(String filename) throws FileNotFoundException, IOException {
        File checkDir = new File(filename);
        boolean existFile = checkDir.exists();
        if (existFile) {
            System.out.println("Loading addresses from file...");
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line;
            AddressListModel alm = new AddressListModel();
            while ((line = br.readLine()) != null) {
                alm.addModel(AddressModel.deSerializeFromString(line));
            }
            return (alm);
        }
        System.out.print("Creating new list...");
        return (new AddressListModel());
    }

    public String returnList() throws IOException {
        ArrayList<AddressModel> values = returnModels();
        String list = "";
        for (int i = 0; i < values.size(); i++) {
            list = list + values.get(i).toString();
        }
        return list;
    }
}
