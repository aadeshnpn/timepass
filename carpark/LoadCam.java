/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

/**
 *
 * @author tensor
 */
public class LoadCam {
   
    String clickedImage;
    int camChoics;
    int countClicks;
    
    
    LoadCam(int status)
    {
        this.camChoics=status;
        this.countClicks=0;
        this.clickedImage="Carpark";
    }
    
    void loadOnCamUserReq() throws InterruptedException{
        
     System.out.println("This app suported by OpenCV");
    // Load the native library.
    System.loadLibrary( Core.NATIVE_LIBRARY_NAME );

    VideoCapture camera = new VideoCapture(0);
    Thread.sleep(1000);
    camera.open(this.camChoics); //Useless
    if(!camera.isOpened()){
        System.out.println("Camera Error");
    }
    else{
        System.out.println("Camera OK?");
    }
    
   
    Mat frame = new Mat();
    //camera.grab();
    //System.out.println("Frame Grabbed");
    //camera.retrieve(frame);
    //System.out.println("Frame Decoded");

    camera.read(frame);
    System.out.println("Frame Obtained");

    /* No difference
    camera.release();
    */

    System.out.println("Captured Frame Width " + frame.width());
    this.clickedImage=this.clickedImage.concat(this.countClicks+".jpg");
    System.out.println("ConImage : "+this.clickedImage);
    Highgui.imwrite(this.clickedImage, frame);
    System.out.println("OK");
    //return clickedImage;
    }
}
