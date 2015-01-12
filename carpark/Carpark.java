/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark;
//import java.util.*;
import org.opencv.core.*;
//import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.highgui.Highgui;
//import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;
/**
 *
 * @author tensor
 */
public class Carpark {

    /**
     * @param args the command line arguments
     */
    //System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
    String filename1="/home/tensor/Desktop/carpark/103.pgm";
    public static void main(String[] args) {
        // TODO code application logic here
     try{

         System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
         Mat source = Highgui.imread("/home/tensor/Desktop/carpark/103.pgm", Highgui.CV_LOAD_IMAGE_COLOR);
         //Mat source = Highgui.imread("/home/tensor/Desktop/carpark/105.pgm", Highgui.CV_LOAD_IMAGE_GRAYSCALE);
         Mat destination = new Mat(source.rows(),source.cols(),source.type());
         Imgproc.cvtColor(source, destination, Imgproc.COLOR_RGB2GRAY);
         //destination = source;
         //Imgproc.Canny(source, destination, 200, 20);
         //Imgproc.GaussianBlur(source, destination, new Size(15,15), 2);
         Imgproc.threshold(source,destination,102,255,Imgproc.THRESH_BINARY);
         //Imgproc
         Highgui.imwrite("ThreshZero3.jpg", destination);
         Imgproc.cvtColor(destination, destination, Imgproc.COLOR_RGB2GRAY);
         Mat circle1 = new Mat();
         //Mat circle2 = new Mat();         
         //Imgproc.HoughCircles(destination,circle1, Imgproc.CV_HOUGH_GRADIENT, 1, 90, 200, 15, 15, 32);
         Imgproc.HoughCircles(destination,circle1, Imgproc.CV_HOUGH_GRADIENT, 1, 100, 120, 26, 15, 32);
         int i;
         Point pt=new Point();
         System.out.println("Opencv "+circle1.total());
         for(i=0;i<circle1.total();i++)
         {
            double vCircle[] = circle1.get(0,i);
            double x = vCircle[0];
            double y = vCircle[1];
            double radius = vCircle[2];
            int radius1=(int)Math.round(radius);
            pt.x=x;
            pt.y=y;
            //int radius = cvRound(circles[i][2]);
             // circle center
           //Core.circle(source, pt, 3, new Scalar(255,255,0), -1, 8, 0 );
            //circle outline
           
           Core.circle( source, pt, radius1, new Scalar(0,255,0), 3, 8, 0 );
           
         }
         
        Highgui.imwrite("ThreshZero4.jpg", destination);
         /// Show your results
        //namedWindow( "Hough Circle Transform Demo", );
        //Highgui.imshow(destination);
         }catch (Exception e) {
            System.out.println("error: " + e.getMessage());
         }
          
    }
    
}
