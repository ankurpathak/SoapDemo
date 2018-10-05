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
import javax.imageio.ImageIO;
import javax.jws.WebService;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.MTOM;

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

}
