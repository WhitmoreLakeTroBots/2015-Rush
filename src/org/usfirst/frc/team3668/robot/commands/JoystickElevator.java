package org.usfirst.frc.team3668.robot.commands;

import org.usfirst.frc.team3668.robot.Robot;
import org.usfirst.frc.team3668.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class JoystickElevator extends Command {

	Elevator elevator;
	Joystick lifterStick = Robot.lifterStick;
    public JoystickElevator() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	elevator = Robot.elevator;
    	requires(elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	elevator.raiseLower(0);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if(Math.signum(lifterStick.getAxis(Joystick.AxisType.kY)) == 1){
    		
        	elevator.raiseLower(lifterStick.getAxis(Joystick.AxisType.kY));

    		
//    	}
    		
//		elevator.raiseLower(Robot.lifterStick.getAxis(Joystick.AxisType.kY));
    	
//    	SmartDashboard.putNumber("Joystick: ", Robot.lifterStick.getAxis(Joystick.AxisType.kY));
//    	SmartDashboard.putBoolean("Is at bottom:" , elevator.isAtBottom());
    }
    


    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
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
