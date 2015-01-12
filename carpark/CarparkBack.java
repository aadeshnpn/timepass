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

public class CarparkBack {

    /**
     * @param args the command line arguments
     */
    //System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
    
    // MY image location "/home/tensor/Desktop/carpark/101.pgm";
    public static void main(String[] args) {
        // TODO code application logic here
     try{
         //Load openCV
         System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
         String filename1;
         //Load the first argument as image name in color from
         filename1="/home/tensor/Desktop/carpark/105.pgm";
         Mat source;
         //Mat source = Highgui.imread(filename1, Highgui.CV_LOAD_IMAGE_COLOR);
         //Mat source = Highgui.imread("/home/tensor/Desktop/carpark/105.pgm", Highgui.CV_LOAD_IMAGE_GRAYSCALE);
         //Variables Definition
         //Float alpha;
         //alpha=2.5f;
         //Double epsilon;
         //Double approxArea;
         //int beta=-50;
         //int erosion_size=1;
         //List<Double> eArea= new ArrayList<>();
         //Mat dest1=new Mat(source.rows(),source.cols(),source.type());         
         //source.convertTo(source, -1, alpha, beta);
         //Mat destination = new Mat(source.rows(),source.cols(),source.type());
         ///Changing image to grey
         //Imgproc.cvtColor(source, destination, Imgproc.COLOR_RGB2GRAY);
         //Using denoising to remove noise in the input image
         //Photo.fastNlMeansDenoising(destination, destination, 10, 5, 25);
         //source=destination;
         //Bluring the image for proper threasholding
         //Imgproc.medianBlur(source, destination, 7);
         //Trying out other image preprossign techniques
         //Imgproc.Canny(source, destination, 200, 20);
         //Imgproc.GaussianBlur(source, destination, new Size(15,15), 2);
         //Imgproc.threshold(source,destination,95,255,Imgproc.THRESH_BINARY);
        // Imgproc.cvtColor(destination, destination, Imgproc.COLOR_RGB2GRAY);
         //Imgproc.blur(destination,destination,new Size(1,1));
         //Applying canny edge detection
         //Imgproc.Canny(source, destination, 120, 180);
         //Imgproc.GaussianBlur(source, destination, new Size(1,1), 50, 100);
         //Defining kernel for erosion
         //Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(erosion_size+2,erosion_size+3), new Point(erosion_size,erosion_size));
         //Applying erosion
         //Imgproc.dilate(destination, destination, kernel);
         //Imgproc.dilate(source, dest1, dest1, null, beta, beta, null);
         //Imgproc.findContours(source, null, Hierarchy, mode, method);
         //Applying image threshold
         //Imgproc.threshold(destination,destination,75,150,Imgproc.THRESH_BINARY+Imgproc.THRESH_TRUNC);
         //Imgproc.findContours(source, null, Hierarchy, mode, method);Imgproc.THRESH_OTSU+Imgproc.T);
         PreProcessImg img=new PreProcessImg(filename1);
         img.conBrightGray();
         img.erosionBlur();
         img.edgeThreasold();
         source=img.returnPreProcessedImg();
         MainProcessing processed=new MainProcessing(img.returnPreProcessedImg());
         processed.detectCountours();
         processed.findElipse();
         processed.drawFindings();
         
         //Highgui.imshow(drawing);
         /*
         Mat circle1 = new Mat();
         //Mat circle2 = new Mat();         
         //Imgproc.HoughCircles(img.destination,circle1, Imgproc.CV_HOUGH_GRADIENT, 1, 90, 200, 15, 15, 32);
         Imgproc.HoughCircles(dest1,circle1, Imgproc.CV_HOUGH_GRADIENT, 1, 90, 200, 16, 29, 90);
         int i;
         Point pt=new Point();
         System.out.println("Circle: "+circle1.total());
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
           
           Core.circle( dest1, pt, radius1, new Scalar(255,255,0), 3, 8, 0 );
           
         }
         
        Highgui.imwrite("circle.jpg", dest1);
        */ 
        /// Show your results
        //namedWindow( "Hough Circle Transform Demo", );
        //Highgui.imshow(img.destination);
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
        
         }catch (Exception e) {
            System.out.println("error: " + e.getMessage());
         }
          
    }
    
}

