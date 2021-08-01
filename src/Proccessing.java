package ir.aut;

import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.*;

import static org.opencv.imgproc.Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C;
import static org.opencv.imgproc.Imgproc.THRESH_BINARY;
import static org.opencv.imgproc.Imgproc.adaptiveThreshold;

public class Proccessing {

    Imgcodecs imgcodecs = new Imgcodecs();
    Mat dst = new Mat();


    public Mat chnnelSepration(String file) {

        Mat matrixofimage = imgcodecs.imread(file, Imgcodecs.IMREAD_COLOR);
        double[] d;
        int chnum = 1;
        switch (chnum) {
            case 1:
                for (int i = 0; i < matrixofimage.rows(); i++) {
                    for (int j = 0; j < matrixofimage.cols(); j++) {
                        d = matrixofimage.get(i, j);
                        matrixofimage.put(i, j, new double[]{d[0], 0, 0});
                    }
                }
                break;
            case 2:
                for (int i = 0; i < matrixofimage.rows(); i++) {
                    for (int j = 0; j < matrixofimage.cols(); j++) {
                        d = matrixofimage.get(i, j);
                        matrixofimage.put(i, j, new double[]{0, d[1], 0});
                    }
                }
                break;
            case 3:
                for (int i = 0; i < matrixofimage.rows(); i++) {
                    for (int j = 0; j < matrixofimage.cols(); j++) {
                        d = matrixofimage.get(i, j);
                        matrixofimage.put(i, j, new double[]{0, 0, d[2]});
                    }
                }
                break;
        }
        return matrixofimage;
    }

    public Mat grayScale(String file) {
        Mat matrix = imgcodecs.imread(file, Imgcodecs.IMREAD_COLOR);
        Imgproc.cvtColor(matrix, dst, Imgproc.COLOR_RGB2GRAY);
        return dst;
    }

    public Mat gaussianBlur(String file) {
        Mat src1 = imgcodecs.imread(file, Imgcodecs.IMREAD_COLOR);
        Mat src=new Mat();
        Imgproc.cvtColor(src1, src, Imgproc.COLOR_RGB2GRAY);
        Imgproc.GaussianBlur(src, dst, new Size(45, 45), 0);
        return dst;
    }

    public Mat rotate(String file) {
        Mat src = imgcodecs.imread(file, Imgcodecs.IMREAD_COLOR);
        // Creating a Point object
        Point point = new Point(320, 320);
        // Creating the transformation matrix M
        Mat rotationMatrix = Imgproc.getRotationMatrix2D(point, 90, 1);
        // Creating the object of the class Size
        Size size = new Size(src.cols(), src.cols());
        // Rotating the given image
        Imgproc.warpAffine(src, dst, rotationMatrix, size);
        return dst;
    }

    public Mat scale(String file) {
        Mat src = imgcodecs.imread(file, Imgcodecs.IMREAD_COLOR);
        // Creating the Size object
        Size size = new Size(src.rows() * 0.5, src.rows());
        // Scaling the Image
        Imgproc.resize(src, dst, size, 0, 0, Imgproc.INTER_AREA);
        return dst;
    }

    public Mat edgeDetection(String file) {
        Mat src = imgcodecs.imread(file, Imgcodecs.IMREAD_COLOR);
        Imgproc.Canny(src, dst, 60, 60 * 3);
        return dst;
    }

    public Mat faceDetection(String file) {
        Mat src = Imgcodecs.imread(file);

        // Instantiating the CascadeClassifier
        String xmlFile = "lbpcascade_frontalface.xml";
        CascadeClassifier classifier = new CascadeClassifier(xmlFile);

        // Detecting the face in the snap
        MatOfRect faceDetections = new MatOfRect();
        classifier.detectMultiScale(src, faceDetections);
        System.out.println(String.format("Detected %s faces",
                faceDetections.toArray().length));

        // Drawing boxes
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(
                    src,                                               // where to draw the box
                    new Point(rect.x, rect.y),                            // bottom left
                    new Point(rect.x + rect.width, rect.y + rect.height), // top right
                    new Scalar(0, 0, 255),
                    3                                                     // RGB colour
            );
        }
        return src;
    }

    public Mat segmentationAdaptive(String file) {




        // Mat src = imgcodecs.imread(file, Imgcodecs.IMREAD_COLOR);
        Mat src1 = imgcodecs.imread(file, Imgcodecs.IMREAD_COLOR);
        Mat src=new Mat();
        Imgproc.cvtColor(src1, src, Imgproc.COLOR_RGB2GRAY);
        Imgproc.adaptiveThreshold(src, dst, 125, Imgproc.ADAPTIVE_THRESH_MEAN_C,
                Imgproc.THRESH_BINARY, 11, 12);
        return dst;










    }

    public Mat segmentationSimple(String file) {
        Mat src = imgcodecs.imread(file, Imgcodecs.IMREAD_COLOR);
        Imgproc.threshold(src, dst, 50, 255, Imgproc.THRESH_BINARY);
        return dst;
    }

}