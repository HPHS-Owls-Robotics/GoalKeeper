package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Driving1Gamepad", group="12417")

public class DriverOp1Gamepad extends LinearOpMode {

    //declaration
    DcMotor LMotor;
    DcMotor RMotor;
    DcMotor Arm;

    //DcMotor Resistance;
    DcMotor Sweeper;

//    CRServo sweepRight;
//    CRServo sweepLeft;

    Servo trapdoor;
    Servo drone;
    //Servo rr;

    DigitalChannel breakBeam;


    @Override

    public void runOpMode() throws InterruptedException {

//initialization
        LMotor = hardwareMap.dcMotor.get("L_Motor");
        RMotor = hardwareMap.dcMotor.get("R_Motor");
        Arm = hardwareMap.dcMotor.get("Arm_Motor");
        //  Resistance  = hardwareMap.dcMotor.get("Resistance");
        Sweeper = hardwareMap.dcMotor.get("Sweeper");

//        sweepRight = hardwareMap.crservo.get("Sweep_1");
//        sweepLeft = hardwareMap.crservo.get("Sweep_2");

        trapdoor = hardwareMap.servo.get("Trapdoor");
        drone = hardwareMap.servo.get("Drone");
        //rr = hardwareMap.servo.get("RR");

        breakBeam = hardwareMap.digitalChannel.get("BreakBeam");

//speeds
        float MaxSpeed, SPwr=5f, LLPwr = 0, LRPwr=0, RLPwr=0, RRPwr=0, APwr;
        trapdoor.setPosition(0.0); //check
        MaxSpeed = 0.7f;
        APwr = 0.6f;
//booleans
        boolean isBeamBroke;

        LMotor.setDirection(DcMotor.Direction.FORWARD);
        RMotor.setDirection(DcMotor.Direction.REVERSE);

        boolean leftTrigger;
        boolean rightTrigger;

//let's gooooooooooooooooooooooooooooooooooooooooo
        waitForStart();

        while (opModeIsActive()) {

            DcMotor[] motors = {LMotor, RMotor, Arm};
            for (int i = 0; i < 3; i++) {
                motors[i].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                motors[i].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }

//            sweepRight.setDirection(DcMotor.Direction.FORWARD);
//            sweepLeft.setDirection(DcMotor.Direction.FORWARD);


            LLPwr = gamepad1.left_stick_y*MaxSpeed - gamepad1.left_stick_x*MaxSpeed;
            LRPwr = gamepad1.left_stick_y*MaxSpeed + gamepad1.left_stick_x*MaxSpeed;

            RLPwr = gamepad1.right_stick_y*MaxSpeed - gamepad1.right_stick_x*MaxSpeed;
            RRPwr = gamepad1.right_stick_y*MaxSpeed + gamepad1.right_stick_x*MaxSpeed;

            isBeamBroke = breakBeam.getState();

//gamepad 1 -- ooooooooooonnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee --
            if(gamepad1.x){
                //drone
                drone.setPosition(0);
            }
            if(gamepad1.y){
                //
            }
            if(gamepad1.b){
                if(trapdoor.getPosition()==0.0){
                    trapdoor.setPosition(1.0);
                    sleep(1000);
                    trapdoor.setPosition(0.0);
                } else{
                    trapdoor.setPosition(0.0);
                }
                sleep(200);
                }
            }
            if(gamepad1.a){
                //sweep mode 2
               // rr.setPosition(0.515);
            }

            if(gamepad1.dpad_up){
                LMotor.setDirection(DcMotor.Direction.FORWARD);
                RMotor.setDirection(DcMotor.Direction.REVERSE);
            }
            if(gamepad1.dpad_down){
                LMotor.setDirection(DcMotor.Direction.REVERSE);
                RMotor.setDirection(DcMotor.Direction.FORWARD);
            }

            if(gamepad1.left_bumper)
            {
                //Arm down
                Arm.setPower(-APwr);
            }
            if(gamepad1.right_bumper)
            {
                //Arm up
                Arm.setPower(APwr);
            }
            if(gamepad1.left_bumper==gamepad1.right_bumper)
            {
                Arm.setPower(0);
            }

            leftTrigger = gamepad1.left_trigger>0;
            rightTrigger = gamepad1.right_trigger>0;
            if(leftTrigger)
            {
                Sweeper.setPower(SPwr);
            }
            if(rightTrigger)
            {
                Sweeper.setPower(-SPwr);
            }
            if(leftTrigger==rightTrigger)
            {
                Sweeper.setPower(0);
            }
            LMotor.setPower(LLPwr + RLPwr);
            RMotor.setPower(LRPwr + RRPwr);

//            telemetry.addData("hello", sweepLeft.getPower());
//            telemetry.addData("hello", sweepRight.getPower());

            telemetry.addData("TICKS", Arm.getCurrentPosition());
            //telemetry.addData("Resistance", Resistance.getZeroPowerBehavior());
            telemetry.update();

    }

}

