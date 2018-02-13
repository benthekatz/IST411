/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        saveToFile("address_list.txt");
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

    public JsonNode serializeAsJSON(AddressListModel alm) {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode node = mapper.convertValue(alm, JsonNode.class);

        return node;
    }

    static AddressListModel deserializeJSON(JsonNode node) {
        ObjectMapper mapper = new ObjectMapper();

        AddressListModel alm = mapper.convertValue(node, AddressListModel.class);

        return alm;
    }

    public void saveJSONToFile(JsonNode node, File file) throws IOException {
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        pw.println(node);
        pw.close();
    }

    public AddressListModel readJSONFromFile(File file) throws IOException {
        AddressListModel alm = null;
        ObjectMapper mapper = new ObjectMapper();

        if (file.exists()) {
            alm = mapper.readValue(file, AddressListModel.class);
        } else {
            System.out.println("File not found.");
        }

        return alm;
    }
}
