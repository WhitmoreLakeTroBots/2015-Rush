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
    	this.requestedHeading = requestedHeading;
    	forwardDrive = Robot.forwardDrive;
    	requires(Robot.forwardDrive);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	double headingError = requestedHeading - getCurrentHeading();
    	turnLeftOrRight = Math.signum(requestedHeading - getCurrentHeading()); 
    	
    	if(Math.abs(headingError) > Settings.turnSlowDownAngle){
    		
    		forwardDrive.Drive(0, Settings.autoTurnSpeed * turnLeftOrRight);	
    		
    	} else {
    		
    		forwardDrive.Drive(0, Settings.autoTurnSpeed*turnLeftOrRight*
    				((1-Settings.minimumTurnSpeed)/Settings.turnSlowDownAngle)*Math.abs(headingError) + Settings.minimumTurnSpeed) ;
    		
    	}
    		
    }
    
    public double getCurrentHeading(){
    	return forwardDrive.GetRobotHeading();
    }

    protected boolean isFinished() {
    	return Math.abs(getCurrentHeading() - requestedHeading) < Settings.turnDeadband;  
    }

    protected void end() {
    	forwardDrive.Drive(0, 0);
    }

    protected void interrupted() {
    	end();
    }
}
