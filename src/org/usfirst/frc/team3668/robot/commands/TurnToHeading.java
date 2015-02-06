package org.usfirst.frc.team3668.robot.commands;

import org.usfirst.frc.team3668.robot.Robot;
import org.usfirst.frc.team3668.robot.Settings;
import org.usfirst.frc.team3668.robot.subsystems.ForwardDrive;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToHeading extends Command {
	ForwardDrive forwardDrive;
	double turnLeftOrRight;
	double requestedHeading;
    public TurnToHeading(double requestedHeading) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.requestedHeading = requestedHeading;
    	forwardDrive = Robot.forwardDrive;
    	requires(Robot.forwardDrive);
//    	requires();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	turnLeftOrRight = Math.signum(requestedHeading - getCurrentHeading()); 
    	
    	if(turnLeftOrRight == 1){
    		
    		forwardDrive.Drive(0, Settings.autoTurnSpeed);
    		
    	} else if(turnLeftOrRight == -1){
    		
    		forwardDrive.Drive(0, -Settings.autoTurnSpeed);
    		
    	}else {
    		
    		forwardDrive.Drive(0, 0);
    		
    	}
    	
    }
    
    public double getCurrentHeading(){
    	
    	return forwardDrive.GetRobotHeading();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs(getCurrentHeading() - requestedHeading) < Settings.turnDeadband;  
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	forwardDrive.Drive(0, 0);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
