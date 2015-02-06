package org.usfirst.frc.team3668.robot.commands;

import org.usfirst.frc.team3668.robot.Robot;
import org.usfirst.frc.team3668.robot.subsystems.ForwardDrive;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShiftGears extends Command {
	ForwardDrive forwardDrive;
    public ShiftGears() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	forwardDrive = Robot.forwardDrive;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	forwardDrive.ShiftGears();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
