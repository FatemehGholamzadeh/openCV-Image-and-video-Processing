package ir.aut;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.*;

import static org.opencv.imgcodecs.Imgcodecs.imwrite;
import static org.opencv.imgproc.Imgproc.ADAPTIVE_THRESH_MEAN_C;
import static org.opencv.imgproc.Imgproc.THRESH_BINARY;
import static org.opencv.imgproc.Imgproc.THRESH_BINARY_INV;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String choice;
        boolean shouldBreak = false;
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Proccessing proccessing = new Proccessing();
        convert convert = new convert();
        Scanner scanner = new Scanner(System.in);


        Imgcodecs imageCodecs = new Imgcodecs();

        String file = "4.jpg";
        String file2 = "group20.jpg";
        String file8 = "6.jpg";

        Mat matrix = new Mat();
        Mat blueMatrix = new Mat();
        Mat grayMatrix = new Mat();
        Mat blurredMatrix = new Mat();
        Mat rotatedMatrix = new Mat();
        Mat scaledMatrix = new Mat();
        Mat edgeMatrix = new Mat();
        Mat faceDetection = new Mat();
        Mat segmentedAdaptiveMatrix = new Mat();
        Mat segmentedSimpleMatrix = new Mat();

//        Mat grayForBlurr = proccessing.grayScale(file);
//        Mat grayForSegment = proccessing.grayScale(file8);
//
//        matrix = imageCodecs.imread(file);
//        convert.displayImage(convert.Mat2BufferedImage(matrix));


        while (true) {
            System.out.println("please choose one of these : " + "\n" + "1- display image " +
                    "\n" + "2- blue image " +
                    "\n" + "3- grayScale image " +
                    "\n" + "4- Gaussian Blurred image " +
                    "\n" + "5- rotated image " +
                    "\n" + "6- Scaled image " +
                    "\n" + "7- edge detection image " +
                    "\n" + "8- segmented simple image " +
                    "\n" + "9- segmented adaptive image " +
                    "\n" + "10- face detection image " +
                    "\n" + "11- display video " +
                    "\n" + "12- advanced segmentation " +
                    "\n" + "13- Exit ");
            choice = scanner.next();
            switch (choice) {
                case "1":
                    matrix = imageCodecs.imread(file);
                    convert.displayImage(convert.Mat2BufferedImage(matrix));
                    break;

                case "2":

                    blueMatrix = proccessing.chnnelSepration(file);
                    convert.displayImage(convert.Mat2BufferedImage(blueMatrix));
                    break;
                case "3":
                    grayMatrix = proccessing.grayScale(file);
                    convert.displayImage(convert.Mat2BufferedImage(grayMatrix));
                    break;
                case "4":
                    blurredMatrix = proccessing.gaussianBlur(file);
                    convert.displayImage(convert.Mat2BufferedImage(blurredMatrix));
                    break;
                case "5":

                    rotatedMatrix = proccessing.rotate(file);
                    convert.displayImage(convert.Mat2BufferedImage(rotatedMatrix));
                    break;

                case "6":

                    scaledMatrix = proccessing.scale(file);
                    convert.displayImage(convert.Mat2BufferedImage(scaledMatrix));
                    break;
                case "7":

                    edgeMatrix = proccessing.edgeDetection(file);
                    convert.displayImage(convert.Mat2BufferedImage(edgeMatrix));
                    break;
                case "8":

                    segmentedSimpleMatrix = proccessing.segmentationSimple(file8);
                    convert.displayImage(convert.Mat2BufferedImage(segmentedSimpleMatrix));
                    break;

                case "9":
                    segmentedAdaptiveMatrix = proccessing.segmentationAdaptive(file8);
                    convert.displayImage(convert.Mat2BufferedImage(segmentedAdaptiveMatrix));
                    break;
                case "10":
                    faceDetection = proccessing.faceDetection(file2);
                    convert.displayImage(convert.Mat2BufferedImage(faceDetection));
                    break;
                case "11":
                    Video video = new Video();
                    video.capture();
                    break;
                case "12":
                    new Segmentation().run(args);
                case "13":
                    shouldBreak = true;
                    break;

            }
            if (shouldBreak) {
                System.out.println("you Exited successfully!");
                break;
            }

        }

    }


}
