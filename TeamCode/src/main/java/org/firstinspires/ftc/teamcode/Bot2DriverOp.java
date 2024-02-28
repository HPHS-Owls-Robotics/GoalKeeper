
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Driving2gamepads", group="12417")

public class Bot2DriverOp extends LinearOpMode {

    //declaration
    DcMotor FLMotor;
    DcMotor FRMotor;
    DcMotor BLMotor;
    DcMotor BRMotor;
    DcMotor ARMotor, ALMotor;
    DcMotor Sweeper;
    DcMotor Belt;

//    CRServo sweepRight;
//    CRServo sweepLeft;

    Servo trapdoor;
    Servo drone;
    Servo sweep;
//    Servo rr;

    int FREEZE_SPEED=0;
    private float cfl = 3.0f;
    private float cfr = 3.0f;
    private float cbl = 3.0f;
    private float cbr = 3.0f;

    @Override

    public void runOpMode() throws InterruptedException {
   //initialization
        FLMotor = hardwareMap.dcMotor.get("FL_Motor");
        FRMotor = hardwareMap.dcMotor.get("FR_Motor");
        BLMotor = hardwareMap.dcMotor.get("BL_Motor");
        BRMotor = hardwareMap.dcMotor.get("BR_Motor");
        ARMotor = hardwareMap.dcMotor.get("AR_Motor");
        ALMotor = hardwareMap.dcMotor.get("AL_Motor");
        Sweeper = hardwareMap.dcMotor.get("S_Motor");
        Belt = hardwareMap.dcMotor.get("C_Motor");
        drone = hardwareMap.servo.get("D_Servo");
        sweep = hardwareMap.servo.get("S_Servo");
        trapdoor = hardwareMap.servo.get("T_Servo");

//speeds
        float SPwr=5f, APwr, FLPwr,FRPwr,BLPwr,BRPwr;
        trapdoor.setPosition(0.0); //check
        APwr = 1f;
//booleans
        boolean isBeamBroke;

        FLMotor.setDirection(DcMotor.Direction.FORWARD);
        FRMotor.setDirection(DcMotor.Direction.REVERSE);
        BLMotor.setDirection(DcMotor.Direction.FORWARD);
        BRMotor.setDirection(DcMotor.Direction.REVERSE);




        //THIS OBJECT MOVES IN INITIALIZATION
        sweep.setPosition(0);

//let's gooo
//
// ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
        waitForStart();

        while (opModeIsActive()) {

            DcMotor[] motors = {FLMotor, FRMotor, BLMotor, BRMotor, ARMotor,ALMotor};
            for (int i = 0; i < 4; i++) {
                motors[i].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                motors[i].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }
//            sweepRight.setDirection(DcMotor.Direction.FORWARD);
//            sweepLeft.setDirection(DcMotor.Direction.FORWARD);

            float yleft, yright,strafe,rotate;

            yleft = gamepad1.left_stick_y;
            yright = gamepad1.right_stick_y;
            strafe = gamepad1.left_stick_x;
            rotate = gamepad1.right_stick_x;

            FLPwr = (cfl)*(yleft + yright - strafe - rotate)/4f; //cfl for possible coeffs to deal with weight distr
            FRPwr = (cfr)*(yleft + yright + strafe + rotate)/4f; //cfr for possible coeffs to deal with weight distr
            BLPwr = (cbl)*(yleft + yright + strafe - rotate)/4f; //cbl for possible coeffs to deal with weight distr
            BRPwr = (cbr)*(yleft + yright - strafe + rotate)/4f; //cbr for possible coeffs to deal with weight distr

            FLMotor.setPower(FLPwr);
            FRMotor.setPower(FRPwr);
            BLMotor.setPower(BLPwr);
            BRMotor.setPower(BRPwr);
           // isBeamBroke = breakBeam.getState();

//gamepad 1 -- ooooooooooooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee --
            if(gamepad1.x){
                sweep.setPosition(0);
            }
            if(gamepad1.y){
                sweep.setPosition(2);
            }
//            if(gamepad1.b){
////                //sweep mode 1
////                if(!breakBeam.getState())
////                {
////                    rr.setPosition(2);
////                    telemetry.addLine("Beam intact");
////                } else
////                {
////                    rr.setPosition(0);
////                    telemetry.addLine("Beam broke");
////                }
//                if(rr.getPosition()==2){
//                    rr.setPosition(0);
//                } else {
//                    rr.setPosition(2);
//                }
////            }
//            if(gamepad1.a){
//                //sweep mode 2
//                rr.setPosition(0.515);
//            }

            if(gamepad1.dpad_up){
                FLMotor.setDirection(DcMotor.Direction.FORWARD);
                FRMotor.setDirection(DcMotor.Direction.REVERSE);
            }
            if(gamepad1.dpad_down){
                FLMotor.setDirection(DcMotor.Direction.REVERSE);
                FRMotor.setDirection(DcMotor.Direction.FORWARD);
            }

            if(gamepad1.left_bumper){
                //sweep out
                Sweeper.setPower(-SPwr);
                Belt.setPower(SPwr);
            }
            if(gamepad1.right_bumper)
            {
                //sweep in
                Sweeper.setPower(SPwr);
                Belt.setPower(-SPwr);
            }
            if(gamepad1.left_bumper==gamepad1.right_bumper){
                Sweeper.setPower(0);
                Belt.setPower(0);

            }

//gamepad 2 --- tttttttttttttttttttttttttttwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo -------
            if(gamepad2.x)
            {
                //drone
                drone.setPosition(0);
            }
            if(gamepad2.y)
            {
                //
            }
            if(gamepad2.a)
            {
                //
            }
            if(gamepad2.b){
                if(trapdoor.getPosition()==0.0){
                    trapdoor.setPosition(1.0);
                    sleep(1000);
                    trapdoor.setPosition(0.0);
                } else{
                    trapdoor.setPosition(0.0);
                }
                sleep(200);
            }


            if(gamepad2.left_bumper)
            {
                //Arm up
//                ARMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                ARMotor.setPower(-APwr);
                ALMotor.setPower(APwr);
            }
            if(gamepad2.right_bumper)
            {
                //Arm down
//                ARMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                ARMotor.setPower(APwr);
                ALMotor.setPower(-APwr);

            }
            if(gamepad2.left_bumper==gamepad2.right_bumper){
                ARMotor.setPower(FREEZE_SPEED);
                ALMotor.setPower(FREEZE_SPEED);
            }

            if(gamepad2.dpad_up)
            {
                Belt.setPower(SPwr);
            }

//
//            ARMotor.setPower(gamepad2.right_stick_y);
//            ALMotor.setPower(gamepad2.left_stick_y);


//            sweepRight.setPower(SPwr);
//            sweepLeft.setPower(-SPwr);
//            FLMotor.setPower(LLPwr + RLPwr);
//            FRMotor.setPower(LRPwr + RRPwr);

//            telemetry.addData("hello", sweepLeft.getPower());
//            telemetry.addData("hello", sweepRight.getPower());

//            telemetry.addData("break beam state:", breakBeam.getState());
//            telemetry.addData("break beam mode:", breakBeam.getMode());
            telemetry.addData("lTicks", ALMotor.getCurrentPosition());
            telemetry.addData("rTicks", ARMotor.getCurrentPosition());
            telemetry.addData("FL", FLMotor.getCurrentPosition());
            telemetry.addData("FR", FRMotor.getCurrentPosition());
            telemetry.addData("BL", BLMotor.getCurrentPosition());
            telemetry.addData("BR", BRMotor.getCurrentPosition());
            telemetry.update();





        }

    }

}