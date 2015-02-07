package org.usfirst.frc.team3668.robot.commands;

import org.usfirst.frc.team3668.robot.Robot;
import org.usfirst.frc.team3668.robot.Settings;
import org.usfirst.frc.team3668.robot.subsystems.ForwardDrive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveToPositionWithHeading extends Command {
	ForwardDrive forwardDrive;
	double leftStartPosition;
	double rightStartPosition;
	double distanceToTravel;
	double requestedHeading;

	public DriveToPositionWithHeading(double distanceToTravel, double requestedHeading) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		forwardDrive = Robot.forwardDrive;
		requires(forwardDrive);
		this.distanceToTravel = distanceToTravel;
		this.requestedHeading = requestedHeading;
	}

	// Called just before this Command runs the first time
	protected void initialize() {

		leftStartPosition = forwardDrive.GetLeftEncoder();
		rightStartPosition = forwardDrive.GetRightEncoder();

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double distanceError = DistanceError();
		double headingError = requestedHeading - forwardDrive.GetRobotHeading();

		if (Math.abs(distanceError) < Settings.driveSlowDownDistance) {

			forwardDrive.Drive(-Math.signum(distanceError) * Settings.driveSlowDownSpeed, headingError * Settings.driveTurnGain);

		} else {

			forwardDrive.Drive(-Math.signum(distanceError)* Settings.autoDriveSpeed, headingError * Settings.driveTurnGain);

		}
		
		

		SmartDashboard.putNumber("Drive Distance Error: ", distanceError);
		SmartDashboard.putBoolean("Finished travelling distance:", Math.abs(distanceError) < Settings.driveToPositionDeadband);
		SmartDashboard.putNumber("Drive Encoder Average", (LeftTraveled() + RightTraveled()) / 2);
		SmartDashboard.putNumber("Left Drive Distance", LeftTraveled());
		SmartDashboard.putNumber("Right Drive Distance", RightTraveled());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Math.abs(DistanceError()) < Settings.driveToPositionDeadband;
	}

	public double LeftTraveled() {
		return forwardDrive.GetLeftEncoder() - leftStartPosition;
	}

	public double RightTraveled() {
		return forwardDrive.GetRightEncoder() - rightStartPosition;
	}

	public double DistanceError() {
		return distanceToTravel - (LeftTraveled() + RightTraveled()) / 2;
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
