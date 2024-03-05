package org.firstinspires.ftc.teamcode;
import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
public class OpticSysProp {

    HardwareMap hardwareMap;
    private VisionPortal portal;
    private PropPipeline propPipeline;


    public OpticSysProp(HardwareMap h)
    {
        hardwareMap=h;
    }

    public  void initProp()
    {
        propPipeline = new PropPipeline();

        portal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(320, 240))
                .addProcessors( propPipeline)
                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                .enableLiveView(false)
                .setAutoStopLiveView(true)
                .build();

    }

    public Object getLocation()
    {
        return propPipeline.getLocation();
    }


}
