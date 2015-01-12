/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import org.opencv.photo.Photo;

/**
 *
 * @author tensor
 */
public class PreProcessImg {
    
    String filename;
    Mat source;
    Mat destination;
    Mat dest1;
    Float alpha;
    Double epsilon;
    Double approxArea;
    int beta;
    int erosion_size;
    
    PreProcessImg(String fname){
        this.alpha=1.5f;
        this.beta=-50;
        this.erosion_size=1;
        this.filename=fname;
        this.source=Highgui.imread(this.filename, Highgui.CV_LOAD_IMAGE_COLOR);
        this.dest1=new Mat(source.rows(),source.cols(),source.type()); 
        this.destination=new Mat(source.rows(),source.cols(),source.type()); 
    }
    
    void conBrightGray()
    {
        //Adjust the brightness and Contrast
         this.source.convertTo(this.source, -1, this.alpha, this.beta);
         //Changing image to grey
         Imgproc.cvtColor(this.source, this.destination, Imgproc.COLOR_RGB2GRAY);
    }
    
    void erosionBlur()
    {
        //Changing image to grey
         Imgproc.cvtColor(this.source, this.destination, Imgproc.COLOR_RGB2GRAY);
         //Using denoising to remove noise in the input image
         Photo.fastNlMeansDenoising(this.destination, this.destination, 10, 5, 25);
         this.source=this.destination;
         //Bluring the image for proper threasholding
         //Imgproc.medianBlur(this.source,this.destination, 7);
         Highgui.imwrite(this.filename, this.destination);
    }
    
    void edgeThreasold()
    {
         Imgproc.Canny(this.source, this.destination, 120, 180);
         //Imgproc.GaussianBlur(source, destination, new Size(1,1), 50, 100);
         //Defining kernel for erosion
         Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(this.erosion_size+2,this.erosion_size+3), new Point(this.erosion_size,this.erosion_size));
         //Applying erosion
         Imgproc.dilate(this.destination, this.destination, kernel);
         //Imgproc.dilate(source, dest1, dest1, null, beta, beta, null);
         //Imgproc.findContours(source, null, Hierarchy, mode, method);
         //Applying image threshold
         Imgproc.threshold(this.destination,this.destination,75,150,Imgproc.THRESH_BINARY+Imgproc.THRESH_TRUNC);
    }
    
    Mat returnPreProcessedImg()
    {
        return this.destination;
    }
}
