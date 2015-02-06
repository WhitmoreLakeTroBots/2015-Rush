package org.usfirst.frc.team3668.robot.commands;

import org.usfirst.frc.team3668.robot.Robot;
import org.usfirst.frc.team3668.robot.Settings;
import org.usfirst.frc.team3668.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ElevatorGoToPosition extends Command {
	double targetHeight;
	Elevator elevator;
	double startPosition;
    public ElevatorGoToPosition(double targetHeight) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	elevator = Robot.elevator;
    	requires(elevator);
    	this.targetHeight = targetHeight;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startPosition = elevator.currentHeight();
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double heightError;
    	double motorCommand;
    	heightError = targetHeight - elevator.currentHeight();
    	//Comparing the height error to the distance to slow down, so the motor knows when to start.
    	if(Math.abs(heightError) < Settings.elevatorSlowDownDistance){
    		//Slows the speed to a ratio of the difference to the distance.
    		motorCommand = (heightError/Settings.elevatorSlowDownDistance);
    		
    	} else{
    		
    		motorCommand = Math.signum(heightError);
    		
    	}
    	
    	double motorCommandFromStart;
    	//Makes sure it has to start up slowly, and increase RPM slowly.
    	if(Math.abs(startPosition - elevator.currentHeight()) < Settings.elevatorSpeedUpDistance ){
    		
    		motorCommandFromStart = (startPosition - elevator.currentHeight())/Settings.elevatorSpeedUpDistance;
    		
    	}else{
    		
    		motorCommandFromStart = Math.signum(startPosition - elevator.currentHeight());
    		
    	}
    	
    	double elevatorMinMotorCommand;
    	if(Math.signum(motorCommand) == 1){
    		
    		elevatorMinMotorCommand = Settings.elevatorMinMotorCommandUp;
    		
    	} else {
    		
    		elevatorMinMotorCommand = Settings.elevatorMinMotorCommandDown;
    		
    	}
    	
    	if(Math.abs(motorCommandFromStart) < Math.abs(motorCommand)){
    		//elevatorMinMotorControl is because the Talon needed some extra room to reach the threshold.
    		elevator.raiseLower(-(elevatorMinMotorCommand+Math.abs(motorCommandFromStart))*Math.signum(motorCommand));
    		motorCommandFromStart = -(elevatorMinMotorCommand+Math.abs(motorCommandFromStart))*Math.signum(motorCommand);
    	}else {
    		if(Math.abs(motorCommand) < elevatorMinMotorCommand){
    			//Just in-case the motorCommand is very small, so the Talon will round
    			motorCommand = elevatorMinMotorCommand * Math.signum(motorCommand);
    		}
    		elevator.raiseLower(-motorCommand);
    	}
    	
    	
    	
    	SmartDashboard.putNumber("Height Error: ", heightError);
    	SmartDashboard.putNumber("Motor Command: ", motorCommand);
    	SmartDashboard.putNumber("Motor Command From Start: ", motorCommandFromStart);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double heightError = elevator.currentHeight() - targetHeight; 
    	SmartDashboard.putBoolean("Is Finished: ",  Math.abs(heightError) < Settings.elevatorDistanceTolerance);
    	return Math.abs(heightError) < Settings.elevatorDistanceTolerance;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	elevator.raiseLower(0);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
