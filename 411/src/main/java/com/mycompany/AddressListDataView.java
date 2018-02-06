/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Ben
 */
public class AddressListDataView {

    public String makeHTML() {
        return null;
    }

    static String readFile(String filename) throws FileNotFoundException, IOException{
        File checkDir = new File(filename);
        boolean existFile = checkDir.exists();
        
        String contents = "";
        
        if (existFile) {
            System.out.println("Loading data from .json file...");
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                contents = contents + line;
            }
            return contents;
        }
        return contents;
    }
    
}
