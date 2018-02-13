/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;
import javax.imageio.ImageIO;
import net.sf.image4j.codec.ico.ICODecoder;

/**
 *
 * @author Ben
 */
class FileView {

    static String serveView(String URL, Socket client) throws IOException {
        File checkDir = new File("./public/" + URL);
        String viewString = "File not found.";

        FileReader fr = new FileReader(checkDir);
        BufferedReader br = new BufferedReader(fr);

        String fileType = checkDir.getName();
        fileType = fileType.substring(fileType.lastIndexOf(".") + 1);

        BufferedImage bi = ImageIO.read(checkDir);

        boolean existFile = checkDir.exists();
        if (existFile) {
            switch (fileType) {
                case "json":
                case "xml":
                case "html":
                case "txt":
                    String line;
                    String contents = "";
                    while ((line = br.readLine()) != null) {
                        contents += line;
                    }
                    viewString = contents;
                    break;
                case "ico":
                    List<BufferedImage> images = ICODecoder.read(checkDir);
                    ImageIO.write(images.get(0), "png", client.getOutputStream());
                    break;
                case "jpeg":
                case "jpg":
                case "png":
                case "bmp":
                case "wbmp":
                case "gif":
                    ImageIO.write(bi, fileType, client.getOutputStream());
                    break;
            }
        }
        return viewString;
    }

}
