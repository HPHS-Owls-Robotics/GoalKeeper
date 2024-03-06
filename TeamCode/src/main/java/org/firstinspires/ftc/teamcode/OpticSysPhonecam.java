package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.opencv.core.Scalar;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

import java.util.ArrayList;
import java.util.List;


public class OpticSysPhonecam {
    public HardwareMap hardwareMap;

    private OpenCvCamera webcam1;
    private OpenCvCamera webcam2;

    ContourPipeline myPipeline1;
    ContourPipeline myPipeline2;

    PropPipeline pipeLine;
    OpenCvCamera.AsyncCameraOpenListener lis;

    AprilTagDetectionPipeline aprilPipeline;

    OpenCvCamera.AsyncCameraOpenListener listen1;
    OpenCvCamera.AsyncCameraOpenListener listen2;

    OpenCvCamera.AsyncCameraOpenListener listen1a;

    OpenCvCamera.AsyncCameraCloseListener listenClose1;
    OpenCvCamera.AsyncCameraCloseListener listenClose2;
    // values
    private static final int CAMERA_WIDTH = 1280; // width  of wanted camera resolution
    private static final int CAMERA_HEIGHT = 720; // height of wanted camera resolution


    public static Scalar scalarLowerYCrCb = new Scalar(0.0, 160, 100.0);
    public static Scalar scalarUpperYCrCb = new Scalar(255.0, 255.0, 255.0);

    public static double borderLeftX = 0.0;   //fraction of pixels from the left side of the cam to skip
    public static double borderRightX = 0.0;   //fraction of pixels from the right of the cam to skip
    public static double borderTopY = 0.0;   //fraction of pixels from the top of the cam to skip
    public static double borderBottomY = 0.0;   //fraction of pixels from the bottom of the cam to skip

    private AprilTagProcessor aprilTag;
    private VisionPortal visionPortal;
    List<org.firstinspires.ftc.vision.apriltag.AprilTagDetection> currentDetections;

    public OpticSysPhonecam(HardwareMap hardwareMap, int color) {
        this.hardwareMap = hardwareMap;
        if (color == 1)
        {
            scalarLowerYCrCb = new Scalar(0, 50, 50);
            scalarUpperYCrCb = new Scalar(255.0, 120, 255);
        }

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//
        //OpenCV Pipeline
        webcam1 =  OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
        //webcam1 = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), viewportContainerIds[0]);
        // webcam2 = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 2"), viewportContainerIds[1]);
    }

//    public void initPropPipeline()
//    {
//        pipeLine = new PropPipeline();
//    }

    public void startPropPipeline()
    {
        lis = new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                // webcam1.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
                webcam1.setPipeline(myPipeline1);
                webcam1.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        };

        webcam1.openCameraDeviceAsync(lis);
    }

    public Enum<Location> getPropLocation()
    {
        return pipeLine.getLocation();
    }

    public void initOpenCV() {
        myPipeline1 = new ContourPipeline(borderLeftX, borderRightX, borderTopY, borderBottomY);
        myPipeline2 = new ContourPipeline(borderLeftX, borderRightX, borderTopY, borderBottomY);



        // Configuration of Pipeline
        myPipeline1.configureScalarLower(scalarLowerYCrCb.val[0], scalarLowerYCrCb.val[1], scalarLowerYCrCb.val[2]);
        myPipeline2.configureScalarLower(scalarLowerYCrCb.val[0], scalarLowerYCrCb.val[1], scalarLowerYCrCb.val[2]);
        myPipeline1.configureScalarUpper(scalarUpperYCrCb.val[0], scalarUpperYCrCb.val[1], scalarUpperYCrCb.val[2]);
        myPipeline2.configureScalarUpper(scalarUpperYCrCb.val[0], scalarUpperYCrCb.val[1], scalarUpperYCrCb.val[2]);

        listen1 = new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                // webcam1.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
                webcam1.setPipeline(myPipeline1);
                webcam1.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        };
        listenClose1 = new OpenCvCamera.AsyncCameraCloseListener() {
            @Override
            public void onClose() {
                webcam1.pauseViewport();
                // webcam1.stopStreaming();
                webcam1.stopRecordingPipeline();
                webcam1.closeCameraDevice();

            }
        };
//        listenClose2 = new OpenCvCamera.AsyncCameraCloseListener() {
//            @Override
//            public void onClose() {
//                webcam2.pauseViewport();
//                // webcam2.stopStreaming();
//                webcam2.stopRecordingPipeline();
//                webcam2.closeCameraDevice();
//
//            }
//        };
    }

    public int getLocation()
    {
        return myPipeline1.getLocation();
    }

    public void startOpenCV() {
        webcam1.openCameraDeviceAsync(listen1);
        //webcam2.openCameraDeviceAsync(listen2);
    }

    public void closeOpenCV()
    {
        webcam1.closeCameraDevice();
        //webcam2.closeCameraDevice();
        //  webcam1.closeCameraDeviceAsync(listenClose1); // Empty camera
        //  webcam2.closeCameraDeviceAsync(listenClose2);
    }

    public void initAprilTags()
    {
        aprilPipeline = new AprilTagDetectionPipeline(0.166,1434.87,1431.88,587.81,362.26);

        listen1a = new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                // webcam1.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
                webcam2.setPipeline(aprilPipeline);
                webcam2.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        };

    }
    public void startAprilTags()
    {
        webcam2.openCameraDeviceAsync(listen1a);
    }


    public int getTag()
    {
        ArrayList<AprilTagDetection> currentDetections = aprilPipeline.getLatestDetections();

        if(currentDetections.size()==0)
        {
            return 0;
        }
        else
            return currentDetections.get(0).id;
    }
    public double getX()
    {
        ArrayList<AprilTagDetection> currentDetections = aprilPipeline.getLatestDetections();

        if(currentDetections.size()==0)
        {
            return 0;
        }
        else
            return currentDetections.get(0).pose.x;
    }
    public double getY()
    {
        ArrayList<AprilTagDetection> currentDetections = aprilPipeline.getLatestDetections();

        if(currentDetections.size()==0)
        {
            return 0;
        }
        else
            return currentDetections.get(0).pose.y;
    }
//
//
//    public int runRed()
//    {
//        myPipeline1.configureBorders(borderLeftX, borderRightX, borderTopY, borderBottomY);
//        myPipeline2.configureBorders(borderLeftX, borderRightX, borderTopY, borderBottomY);
//        if(myPipeline1.error){
//            return -1;
//        }
//        // Only use this line of the code when you want to find the lower and upper values
//        //testing(myPipeline1);
//        if(myPipeline1.getRectHeight() > 200 || myPipeline2.getRectHeight() > 100 ){
//
//            if(myPipeline1.getRectArea()>myPipeline2.getRectArea()){
//                return 3;// rightmost=6
//            }
//            if(myPipeline2.getRectArea()>myPipeline1.getRectArea())
//            {
//                return 1;// leftmost=4
//
//            }
//        }
//        return 2; // middle=4
//
//        //return myPipeline1.getRectHeight();
//    }
//    public int runBlue()
//    {
//        myPipeline1.configureBorders(borderLeftX, borderRightX, borderTopY, borderBottomY);
//        myPipeline2.configureBorders(borderLeftX, borderRightX, borderTopY, borderBottomY);
//        if(myPipeline1.error){
//            return -1;
//        }
//        // Only use this line of the code when you want to find the lower and upper values
//        //testing(myPipeline1);
//        if(myPipeline1.getRectHeight() > 200 || myPipeline2.getRectHeight() > 100 ){
//
//            if(myPipeline1.getRectArea()>myPipeline2.getRectArea()){
//                return 3;// rightmost=6
//            }
//            if(myPipeline2.getRectArea()>myPipeline1.getRectArea())
//            {
//                return 1;// leftmost=4
//
//            }
//        }
//        return 2; // middle=4
//
//        //return myPipeline1.getRectHeight();
//    }
    public double getA1()
    {
        return myPipeline1.getRectArea();
    }
    public double getA2()
    {
        return myPipeline2.getRectArea();
    }

}