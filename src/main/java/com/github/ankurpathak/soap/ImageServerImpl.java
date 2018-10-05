/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ankurpathak.soap;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.jws.WebService;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.MTOM;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author ankur
 */
//Service Implementation Bean
@MTOM
@WebService(endpointInterface = "com.github.ankurpathak.soap.ImageServer")
public class ImageServerImpl implements ImageServer {

    @Override
    public Image downloadImage() {
        try {
            File file = PATH_DOWNLOAD.toFile();
            return ImageIO.read(file);
        } catch (IOException e) {
            throw new WebServiceException();
        }
    }

    @Override
    public void uploadImage(Image data) {
        if (data != null) {
            File file = PATH_UPLOAD.toFile();
            try {
                if (data instanceof BufferedImage) {
                    ImageIO.write((BufferedImage) data, "png", file);
                }
            } catch (IOException ex) {
                throw new WebServiceException();
            }

        }
    }

    @Override
    public byte[] downloadByteArray() {
        try{
            File file = PATH_DOWNLOAD.toFile();
            return FileUtils.readFileToByteArray(file);
        }catch (IOException e) {
            throw new WebServiceException();
        }
    }

    @Override
    public void uploadByteArray(byte[] data) {
        if (!ArrayUtils.isEmpty(data)) {
            File file = PATH_UPLOAD.toFile();
            try {
                FileUtils.writeByteArrayToFile(file, data);
            } catch (IOException ex) {
                throw new WebServiceException();
            }
        }
    }

    @Override
    public DataHandler downloadDataHandler() {
        File file = PATH_DOWNLOAD.toFile();
        FileDataSource dataSource = new FileDataSource(file);
        DataHandler dataHandler = new DataHandler(dataSource);
        return dataHandler;
    }

    @Override
    public void uploadDataHandler(DataHandler data) {
         if (data != null) {
            File file = PATH_UPLOAD.toFile();
            try {
                FileUtils.copyInputStreamToFile(data.getInputStream(), file);
            } catch (IOException ex) {
                throw new WebServiceException();
            }
        }
    }

}
