package org.usfirst.frc.team3668.robot.commands;

import org.usfirst.frc.team3668.robot.Robot;
import org.usfirst.frc.team3668.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorCalibrate extends Command {

	Elevator elevator;
	boolean isCalibrated;
	
    public ElevatorCalibrate() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	elevator = Robot.elevator;
    	requires(elevator);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    
    
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(!isFinished()){
    		
    		elevator.raiseLower(.25);
    		
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       
    	return elevator.isAtBottom() || isCalibrated;
    }
    
    public boolean isCalibrated(){
    	
    	return isFinished();
    	
    }
    
    // Called once after isFinished returns true
    protected void end() {
    	elevator.calibrateEncoder();
    	elevator.raiseLower(0);
    	isCalibrated = true;
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
