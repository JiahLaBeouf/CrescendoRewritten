package org.usfirst.frc.team6579.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6579.robot.Robot;

public class IntakeSpitOut extends Command {


    public IntakeSpitOut(){
        requires(Robot.intake);
    }
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        //Robot.drivetrain.arcadeDrive(Robot.oi.getJoystick());
        Robot.intake.spitOut();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.intake.stopIntake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
