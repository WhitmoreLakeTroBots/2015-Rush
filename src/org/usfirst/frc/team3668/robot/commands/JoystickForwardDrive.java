package org.usfirst.frc.team3668.robot.commands;

import org.usfirst.frc.team3668.robot.IOLabels;
import org.usfirst.frc.team3668.robot.Robot;
import org.usfirst.frc.team3668.robot.Settings;
import org.usfirst.frc.team3668.robot.SpeedScaler;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoystickForwardDrive extends Command {
	// Button brendanWantsAdjustableDrive;
	// double speedMultiplier;
	public JoystickForwardDrive() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.forwardDrive);
		// brendanWantsAdjustableDrive = new JoystickButton(Robot.driveStick,
		// IOLabels.slowForwardDrive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.forwardDrive.Drive(0, 0);
		// speedMultiplier = Settings.normalDriveSpeed;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// if(brendanWantsAdjustableDrive.get()){
		//
		// speedMultiplier = Settings.slowedDriveSpeed;
		//
		// } else{
		//
		// speedMultiplier = Settings.normalDriveSpeed;
		//
		// }
		Robot.forwardDrive.Drive(SpeedScaler.scaledSpeed(
				(Robot.driveStick.getAxis(Joystick.AxisType.kY)),
				Settings.universalSpeedScaleFactor), SpeedScaler.scaledSpeed(
				(Robot.driveStick.getAxis(Joystick.AxisType.kTwist))
						* Settings.turnSensitivityMultiplier,
				Settings.universalSpeedScaleFactor));
		// Robot.forwardDrive.TestServos(Robot.lifterStick.getAxis(Joystick.AxisType.kX));

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
		Robot.forwardDrive.Drive(0, 0);
	}
}
