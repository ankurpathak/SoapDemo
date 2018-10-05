/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.ankurpathak.soap;

import java.awt.Image;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 *
 * @author ankur
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface ImageServer {

    Path PATH_UPLOAD = Paths.get("/","home", "ankur", "soap", "kotak.png");

    Path PATH_DOWNLOAD = Paths.get("/", "home", "ankur", "Pictures", "kotak.png");

    //download a image from server
    @WebMethod
    Image downloadImage();

    //update image to server
    @WebMethod
    void uploadImage(Image data);
    
    
    @WebMethod
    byte[] downloadByteArray();
    
    
    @WebMethod
    void uploadByteArray(byte[] data);
    
    
    @WebMethod
    DataHandler downloadDataHandler();
    
    
    @WebMethod
    void uploadDataHandler(DataHandler dataHandler);
    

}
