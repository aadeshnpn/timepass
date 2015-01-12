/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark;
//import java.util.*;
import java.util.ArrayList;
import java.util.List;
import org.opencv.core.*;
import java.math.*;
import java.util.Scanner;
//import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.photo.*;
import org.opencv.highgui.Highgui;
//import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;
/**
 *
 * @author tensor
 */

public class CarparkE {

    /**
     * @param args the command line arguments
     */
    //System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
    
    // MY image location "/home/tensor/Desktop/carpark/101.pgm";
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
     try{
         //Load openCV
         System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
         //LoadCam cam;
         String filename1;
         filename1=args[0];
         /*
         //Try to load Cam and aks user which cam to use
         System.out.println("Enter which camera you want to user for CarPar App");
         System.out.println("Choices: \n (0) Default Laptop Camera/Inbuilt Camera\n (1) USB Camera/External Camera");
         int status;
         Scanner s1=new Scanner(System.in);
         status=s1.nextInt();
         while(true){
         if(status==0)
         {
            cam=new LoadCam(status);
         }
         else if(status==1)
         {
            cam=new LoadCam(status);
           }
         else
         {
              System.out.println("Invalid Option. Please use the option provided below. Using Default Camera");
              //System.out.println("Enter which camera you want to user for CarPar App");
              //System.out.println("Choices: \n (0) Default Laptop Camera/Inbuilt Camera\n (1) USB Camera/External Camera");
              cam=new LoadCam(status);
              //System.exit(0);
              //filename1="/home/tensor/Desktop/carpark/105.pgm";
         }
         
        
         cam.loadOnCamUserReq();
         filename1=cam.clickedImage;
         * */
         //filename1="/home/tensor/Desktop/carpark/Untitled-8.jpg";
         System.out.println("Filename: "+filename1);
         //Load the first argument as image name in color from
         //filename1="/home/tensor/Desktop/carpark/106.jpg";
         //Mat source;
         PreProcessImg img=new PreProcessImg(filename1);
         img.conBrightGray();
         img.erosionBlur();
         //img.edgeThreasold();
         //source=img.returnPreProcessedImg();
         //MainProcessing processed=new MainProcessing(img.returnPreProcessedImg());
         //processed.detectCountours();
         /*
         processed.findElipse();
         processed.drawFindings();
         processed.findCircle();
        Double prob;
        prob=(processed.ellipseCnt+1.0)/(processed.minEllipse.size()+1.0);
        if(processed.ellipseCnt==0)
        {
            System.out.println("<Empty>");
            System.out.println("Parking lot is empty with system confidence about :"+prob);     
        }
        else if (processed.ellipseCnt>0 && processed.ellipseCnt<8)
        {
            System.out.println("<Partially Filled>");
            System.out.printf("Parking lot is partially filed with %d Vechicles. System confidence about \n: %f", processed.ellipseCnt,prob);
        }
         else if (processed.ellipseCnt>8)
        {
            System.out.println("<Full>");
            System.out.println("Parking lot is full with system confidence about :"+prob+"<Full>");
        }
        Thread.sleep(40000);
        */
         //}
         }catch (Exception e) {
            System.out.println("error: " + e.getMessage());
         }
     //Thread.sleep(40000);
    }
    
}

