package org.usfirst.frc.team3668.robot.commands;

import org.usfirst.frc.team3668.robot.IOLabels;
import org.usfirst.frc.team3668.robot.Robot;
import org.usfirst.frc.team3668.robot.Settings;
import org.usfirst.frc.team3668.robot.subsystems.SideDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoystickSideDrive extends Command {
	Joystick driverStick;
	SideDrive sideDrive;
	JoystickButton rightDrive;
	JoystickButton leftDrive;
	public JoystickSideDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	sideDrive = Robot.sideDrive;
    	requires(sideDrive);
    	driverStick = Robot.driveStick;
    	leftDrive = new JoystickButton(driverStick, IOLabels.leftDriveButton);
    	rightDrive = new JoystickButton(driverStick, IOLabels.rightDriveButton);
	
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(leftDrive.get()){
        	
    		sideDrive.sideDrive(Settings.sideDriveSpeed);
    		
    	} else if(rightDrive.get()){
    		
    		sideDrive.sideDrive(-Settings.sideDriveSpeed);
    		
    	} else {
    		
    		sideDrive.sideDrive(0);
    		
    	}
    	
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	sideDrive.sideDrive(0);
    }
}
