
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.qualcomm.robotcore.hardware.Servo;
public class ArmSys {
    private DcMotor ARMotor, ALMotor;
    private DcMotor Sweeper;
    private DcMotor Belt;

    private Servo trapdoor;

    int x = 1500;
    public ArmSys(HardwareMap hardwareMap)
    {

        ARMotor = hardwareMap.dcMotor.get("AR_Motor");
        ALMotor = hardwareMap.dcMotor.get("AL_Motor");
        Sweeper = hardwareMap.dcMotor.get("S_Motor");
        Belt = hardwareMap.dcMotor.get("C_Motor");
        trapdoor = hardwareMap.servo.get("T_Servo");
        DcMotor[] motors = {ARMotor,ALMotor};
        for (int i = 0; i < 2; i++) {
            motors[i].setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motors[i].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }

    public void armUp()
    {
        ALMotor.setTargetPosition(ALMotor.getCurrentPosition()-5000);
        ARMotor.setTargetPosition(ALMotor.getCurrentPosition()-5000);
        ALMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ARMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ALMotor.setPower(-0.3f);
        ARMotor.setPower(-0.3f);
    }
    public void armDown()
    {
        ALMotor.setTargetPosition(ALMotor.getCurrentPosition()+5000);
        ARMotor.setTargetPosition(ALMotor.getCurrentPosition()+5000);
        ALMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ARMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ALMotor.setPower(0.3f);
        ARMotor.setPower(0.3f);    }
    public void sweepOut()
    {
        Sweeper.setPower(1);
    }
    public void sweepIn()
    {
        Sweeper.setPower(-1);
    }
    public void trap()
    {
        trapdoor.setPosition(2);

    }
    public void trapClose()
    {
        trapdoor.setPosition(0);

    }
}
