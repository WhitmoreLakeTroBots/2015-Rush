package org.usfirst.frc.team3668.robot.commands;

import org.usfirst.frc.team3668.robot.Robot;
import org.usfirst.frc.team3668.robot.Settings;
import org.usfirst.frc.team3668.robot.subsystems.ForwardDrive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveToPosition extends Command {
	ForwardDrive forwardDrive;
	double leftStartPosition;
	double rightStartPosition;
	double distanceToTravel;
    public DriveToPosition(double distanceToTravel) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	forwardDrive = Robot.forwardDrive;
    	requires(forwardDrive);
    	this.distanceToTravel = distanceToTravel;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    
    leftStartPosition = forwardDrive.GetLeftEncoder();
    rightStartPosition = forwardDrive.GetRightEncoder();
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double encoderAverage;
    	double leftTraveled;
    	double rightTraveled;
    	double distanceError;
    	leftTraveled = forwardDrive.GetLeftEncoder() - leftStartPosition;
    	rightTraveled = forwardDrive.GetRightEncoder() - rightStartPosition;
    	encoderAverage = (leftTraveled+rightTraveled)/2;
    	distanceError = distanceToTravel - encoderAverage;
    	
    	if(Math.abs(distanceError) < Settings.driveSlowDownDistance){
    		
    		forwardDrive.Drive(distanceError/Settings.driveSlowDownDistance, 0);
    		
    	} else {
    		
    		forwardDrive.Drive(Math.signum(distanceError), 0);
    		
    	}
    			
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double distanceError;
    	double leftTraveled;
    	double rightTraveled;
    	leftTraveled = forwardDrive.GetLeftEncoder() - leftStartPosition;
    	rightTraveled = forwardDrive.GetRightEncoder() - rightStartPosition;
    	distanceError = distanceToTravel - (leftTraveled+rightTraveled)/2;
    	SmartDashboard.putBoolean("Finished travelling distance:", Math.abs(distanceError) < Settings.driveTolerance);
        return Math.abs(distanceError) < Settings.driveTolerance;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	forwardDrive.Drive(0, 0);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	end();
    	
    }
}
