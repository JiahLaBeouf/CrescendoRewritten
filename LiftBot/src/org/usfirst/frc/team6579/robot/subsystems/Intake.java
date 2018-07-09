package org.usfirst.frc.team6579.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

import static org.usfirst.frc.team6579.robot.RobotMap.*;

public class Intake extends Subsystem {

    private double suckInPower = 1;

    //left intake motors
    private VictorSP leftFront = new VictorSP(intakeLeftF);
    private VictorSP leftBack = new VictorSP(intakeLeftB);
    private SpeedControllerGroup leftIntakeMotors = new SpeedControllerGroup(leftFront,leftBack);

    //right intake motors
    private VictorSP rightFront = new VictorSP(intakeRightF);
    private VictorSP rightBack = new VictorSP(intakeRightB);
    private SpeedControllerGroup rightIntakeMotors = new SpeedControllerGroup(leftFront,leftBack);


    public Intake(){

        rightIntakeMotors.setInverted(true);
    }

    public void initDefaultCommand(){
        //setDefaultCommand();
    }

    public void suckIn(){
        leftIntakeMotors.set(suckInPower);
        rightIntakeMotors.set(suckInPower);
    }

    public void spitOut(){
        leftIntakeMotors.set(-suckInPower);
        rightIntakeMotors.set(-suckInPower);
    }

    public void stopIntake(){
        leftIntakeMotors.stopMotor();
        rightIntakeMotors.stopMotor();
    }
}
