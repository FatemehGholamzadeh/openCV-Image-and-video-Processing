package ir.aut;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Video extends Thread {
    List<Mat> matList = new ArrayList<>();
    convert convert = new convert();


    public void capture() throws InterruptedException {
        VideoCapture cap = new VideoCapture();

        String input = "film.avi";
        String output = "G:\\IdeaProjects\\ImageProcessing\\film";

        cap.open(input);

        int video_length = (int) cap.get(Videoio.CAP_PROP_FRAME_COUNT);
        int frames_per_second = (int) cap.get(Videoio.CAP_PROP_FPS);
        //   int frame_number = (int) cap.get(Videoio.CAP_PROP_POS_FRAMES);

        int counter = 5;
        Mat frame = new Mat();

        int frame_number = 0;
        if (cap.isOpened()) {
            while (cap.read(frame)) //the last frame of the movie will be invalid. check for it !
            {
                Imgcodecs.imwrite(output + "/" + frame_number + ".jpg", frame);
                // matList.add(frame);
                convert.displayImage(convert.Mat2BufferedImage(frame));
                Thread.sleep(500);
                //   wait(500);
                frame_number++;
                counter--;
                if (counter == 0) {
                    break;
                }
            }

            cap.release();


            System.out.println(video_length + " Frames extracted");

        } else {
            System.out.println("Fail");
        }
    }


} 