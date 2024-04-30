package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="GoalKeeperGamepad", group="12417")

 //declaration
    DcMotor move;

    @Override

public class GoalKeeperGamePad extends LinearOpMode {
    move.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    move.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    move.setDirection(DcMotor.Direction.FORWARD);

while (opModeIsActive()) {

    float left, right;

    left = gamepad1.left_stick_x;

    move.setPower(MovePwr);

    telemetry.addData("BR", BRMotor.getCurrentPosition());
    telemetry.update();

    if(Math.abs(left)>0){
        sweep.setPower(left);
    }
}
