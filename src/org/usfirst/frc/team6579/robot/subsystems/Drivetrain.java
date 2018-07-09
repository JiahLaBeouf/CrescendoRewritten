/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6579.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.commands.DriveWithJoystick;

import java.util.logging.Logger;

import static org.usfirst.frc.team6579.robot.RobotMap.*;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

    private Logger logger = Logger.getLogger(this.getClass().getName());


    //Drivetrain motors
    private Spark leftMotorController = new Spark(leftDTport);
    private Spark rightMotorController = new Spark(rightDTport);

    //gyro
    private ADXRS450_Gyro gyro = null;

    //encoder
    private Encoder drivetrainEncoder = null;

    //camera
    private UsbCamera camera = null;

    private DifferentialDrive robotDrive = new DifferentialDrive(leftMotorController,rightMotorController);

    private double distancePerPulse = 0.2493639169;

    public boolean inMotion = false;

    public Drivetrain(){
        try {
            camera = CameraServer.getInstance().startAutomaticCapture();
        } catch (Exception e) {
            logger.info("Camera not installed correctly" + e.toString());
            SmartDashboard.putBoolean("Camera Installed", false);
        }

        try {
            gyro = new ADXRS450_Gyro();
            SmartDashboard.putBoolean("Gyro Installed", true);

            //calibrates and resets the gyro to 0
            gyro.reset(); // Reset the angle the gyro is pointing towards to 0
            gyro.calibrate(); //Takes a long time, will have to test if necessary
        }
        catch (Exception e)
        {
            logger.info("Gyro not installed correctly" + e.toString());
            SmartDashboard.putBoolean("Gyro Installed", false);
        }

        try{
            drivetrainEncoder = new Encoder(drivetrainEncoderA, drivetrainEncoderB, true, Encoder.EncodingType.k4X);
            SmartDashboard.putBoolean("Drivetrain encoder installed", true);

            drivetrainEncoder.setMaxPeriod(.1);
            drivetrainEncoder.setMinRate(10);
            drivetrainEncoder.setDistancePerPulse(0.2493639169);
            drivetrainEncoder.setReverseDirection(true);
            drivetrainEncoder.setSamplesToAverage(7);


            drivetrainEncoder.reset();
        } catch (Exception e){
            System.out.println("Encoder not installed correctly" + e.toString());
            SmartDashboard.putBoolean("Drivetrain encoder installed", false);
        }
    }

    /**
     * When no commands are being called, default to driving around with the joystick.
     */
    @Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveWithJoystick());
	}

    /**
     * This is an arcade drive thing, used by the DriveWithJoystick command
     * @param stick
     */
	public void arcadeDrive(Joystick stick){
        double throttleValue = (stick.getThrottle()-1)/-2;

        double newX = stick.getX() * throttleValue;
        double newY = stick.getY() * throttleValue;

        robotDrive.arcadeDrive(newY,newX); //This seems back to front but that is how WPILIB have decided to do it.
    }

    /**
     * Used to set motor power
     * @param leftPower
     * @param rightPower
     */
    public void setPower(double leftPower,double rightPower){
        leftMotorController.set(leftPower);
        rightMotorController.set(rightPower);
    }

    /**
     * Sets all drive motors to 0, essentially stopping/allowing to just coast
     */
    public void stop(){
        setPower(0,0);
    }

}
