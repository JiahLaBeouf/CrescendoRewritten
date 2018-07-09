package org.usfirst.frc.team6579.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team6579.robot.Robot;

public class IntakeSuckIn extends Command {

    public IntakeSuckIn(){
        requires(Robot.intake);
    }
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.intake.suckIn();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return true;
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
