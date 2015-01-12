/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt4;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author tensor
 */
public class MainProcessing {
    
      List<MatOfPoint> contours= new ArrayList<>();
         //List<Point> contours= new ArrayList();
      MatOfInt4 Hierarchy=new MatOfInt4(); 
      List<RotatedRect> minRect= new ArrayList<>();
      List<RotatedRect> minEllipse= new ArrayList<>();
      int t;
      //MatOfPoint2f NewMtx;
      Mat drawing;
      Mat destination;
      Mat dest1;
      Double[] are=new Double[30];
      Double cntrX,cntrY;
      int ellipseCnt;
      
      MainProcessing(Mat dest)
      {
          this.destination=dest;
          this.t=0;
          this.ellipseCnt=0;
          this.dest1=this.destination.clone();
      }
    
      void detectCountours()
      {
         Imgproc.findContours(this.destination, this.contours, this.Hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_TC89_KCOS,new Point());
         Highgui.imwrite("contours.jpg", this.destination);
      }
      
      void drawFindings()
      {
          this.drawing =Mat.zeros(this.destination.size(),CvType.CV_8UC1);
         //Mat drawing =Mat.zeros(img.destination.size(),CvType.CV_8UC1);
         //this.drawing=this.destination.clone();
         System.out.println("Total Ellipse Count: "+this.minEllipse.size());
         //Double[] are=new Double[30];
         //Double cntrX,cntrY;
         //int ellipseCnt=0;
         for(int j=0;j<this.minEllipse.size();j++)
         {
             Scalar color = new Scalar (255,225,255);
             //Imgproc.drawContours(drawing, contours, j, color, 1, 8, new MatOfInt4(), 0, new Point());
             //System.out.println(minEllipse.get(j)+ " Index "+j);
             Core.ellipse(this.drawing, this.minEllipse.get(j), color, 12, 1);
             Point[] rect_points= new Point[4];
             //List<Point> rect_points =new ArrayList<Point>();
             //rect_points[j]=null;
             //rect_points = new MatOfPoint2f();
             //rect_points.toList().;
             //minEllipse.get(j).size;
             //System.out.println("Area of Ellipse: "+Imgproc.contourArea(minEllipse.get(j), true));
             this.minRect.get(j).points(rect_points);
             Double dx,dy,dx1,dy1,len,bre;
             len=0.0;bre=0.0;
             for(int k=0;k<4;k++)
             {  //System.out.println("Index "+k);
                Core.line(this.drawing,rect_points[k], rect_points[(k+1)%4], color, 1);
                
                if(k==0){
                dx=Math.abs(rect_points[k].x-rect_points[k+1].x);
                dy=Math.abs(rect_points[k].y-rect_points[k+1].y);
                //System.out.print(rect_points[k].x + ","+rect_points[k+1].x+'\n');
                //System.out.print(rect_points[k].y + ","+rect_points[k+1].y+'\n');
                //System.out.println("x and y distance:"+dx+","+dy);
                len=Math.abs(Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2)));
                //bre=Math.abs(Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2)));
                //are[j]=len*bre;
                //Core.magnitude(rect_points[0].rect_points[1], rect_points[1]-rect_points[2], drawing);
                }
                else if(k==1)
                {
                 dx=Math.abs(rect_points[k].x-rect_points[k+1].x);
                dy=Math.abs(rect_points[k].y-rect_points[k+1].y);
                //System.out.print(rect_points[k].x + ","+rect_points[k+1].x+'\n');
                //System.out.print(rect_points[k].y + ","+rect_points[k+1].y+'\n');
                //System.out.println("x and y distance:"+dx+","+dy);
                //len=Math.abs(Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2)));
                bre=Math.abs(Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2)));   
                }
                
             }
             //Highgui.imwrite("ellipse.jpg", drawing);
             this.are[j]=len*bre;
             System.out.println("Length and Breadth of the point "+rect_points+" ->"+len+" ,"+bre+" Area"+this.are[j]);
             if((len/1.71)>bre && (len/4)<bre && this.are[j]>14000)
             {
                 this.ellipseCnt++;
             }
             //System.exit(0);
         }
         System.out.println("Valid Ellispe: "+this.ellipseCnt);
         
         Highgui.imwrite("ellipse.jpg",this.drawing);
      }
      
      void findElipse()
      {
         for(int j=0;j<this.contours.size();j++)
         {
             //k=j;
             //MatOfPoint t;
             //t.setTo(((MatOfPoint2f)(Mat)contours.get(jMat) 
             //contours.get(j).convertTo(source, j);
             MatOfPoint2f NewMtx=new MatOfPoint2f();
             //Conversion from MatOfPoint to MatOfPoint2f
             NewMtx.fromList(this.contours.get(j).toList());
             //contours.get(j).assignTo(NewMtx,CvType.CV_32F);
             //System.out.print(contours.get(j).toArray());
             //System.out.println(NewMtx.dump());
             //System.out.println();
             
             minRect.add(this.t,Imgproc.minAreaRect(NewMtx));
             //System.out.println(minRect.toString());
             if(NewMtx.rows()>=85)
             {  //System.out.println("From Inside If condition" + j+" "+NewMtx.dump());
                 //System.out.println(NewMtx.rows()+" 5Index5 "+x);
                 //epsilon=0.1*Imgproc.arcLength(NewMtx, true);
                 //Imgproc.approxPolyDP(NewMtx, NewMtx, epsilon, true);
                 minEllipse.add(this.t, Imgproc.fitEllipse(NewMtx));
                 this.t++;
                 //System.out.println("Area of Ellipse: "+Imgproc.contourArea(NewMtx, true));
                 //Imgproc.convexHull(contours.get(j),null, tru
                 //epsilon=0.
                 //System.out.println("After minEllipse"+minEllipse.toString()+j);
             }
             //else
            // {
              //   System.out.println(NewMtx.rows()+" 0Index0 "+x);
             //}
             //System.exit(1);
             //minRect.points(Imgproc.minAreaRect(NewMtx).set(2));
             //minRect.set(Imgproc.minAreaRect(NewMtx);
             //minRect[j]=minAreaRect(Mat(contours[j]))
         }
      }


    void findCircle()
    {
         Mat circle1 = new Mat();
         //Mat circle2 = new Mat();         
         //Imgproc.HoughCircles(img.destination,circle1, Imgproc.CV_HOUGH_GRADIENT, 1, 90, 200, 15, 15, 32);
         Imgproc.HoughCircles(this.dest1,circle1, Imgproc.CV_HOUGH_GRADIENT, 1, 90, 200, 16, 29, 90);
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
           
           Core.circle( this.dest1, pt, radius1, new Scalar(255,255,0), 3, 8, 0 );
           
         }
         
        //Highgui.imwrite("circle.jpg", this.dest1);
    }


}