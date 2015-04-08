package org.usfirst.frc.team3668.robot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class Logger {
	private static final String comma = ",";
	
	public static void InitFile() {
		try {
			Files.write(Settings.logFilePath, (
					"Time"
					+ comma + "RobotState"
					+ comma + "RobotHeading"
					+ comma + "ElevatorPosition"
					+ comma + "ElevatorMotorValue"
					+ comma + "ElevatorUpperSwitch"
					+ comma + "ElevatorUpperCounter"
					+ comma + "ElevatorLowerSwitch"
					+ comma + "ElevatorLowerCounter"
					+ comma + "DriveLeftPosition"
					+ comma + "DriveRightPosition"
					+ comma + "DriveLeftOneMotorValue"
					+ comma + "DriveLeftTwoMotorValue"
					+ comma + "DriveRightOneMotorValue"
					+ comma + "DriveRightTwoMotorValue"
					+ comma + "DriveIsInLowGear"
					+ comma + "SideDriveMotorValue"
					+ comma + "GuideArmIsDeployed"
					+ comma + "ToteSupportIsDeployed"
					+ comma + "ForwardDriveJoystickValue"
					+ comma + "SideDriveJoystickValue"
					+ comma + "ServoPosition"
					+ "\n"
					).getBytes("utf-8"), StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			System.out.println("Log file init failed");
			System.out.println(e.getMessage());
		}
		Log("InitFile");
	}

	public static void Log(String robotState) {
    	String currentTime = Double.toString(Timer.getFPGATimestamp());
    	String robotHeading = Double.toString(Robot.forwardDrive.GetRobotHeading());
    	String elevatorPosition = Double.toString(Robot.elevator.currentHeight());
    	String elevatorMotorValue = Double.toString(Robot.elevator.GetMotorValue());
    	String elevatorUpperSwitch = Boolean.toString(Robot.elevator.GetUpperSwitch());
    	String elevatorUpperCounter = Integer.toString(Robot.elevator.GetUpperCounter());
    	String elevatorLowerSwitch = Boolean.toString(Robot.elevator.GetLowerSwitch());
    	String elevatorLowerCounter = Integer.toString(Robot.elevator.GetLowerCounter());
    	String driveLeftPosition = Double.toString(Robot.forwardDrive.GetLeftEncoder());
    	String driveRightPosition = Double.toString(Robot.forwardDrive.GetRightEncoder());
    	String driveLeftOneValue = Double.toString(Robot.forwardDrive.GetLeftDriveOneValue());
    	String driveLeftTwoValue = Double.toString(Robot.forwardDrive.GetLeftDriveTwoValue());
    	String driveRightOneValue = Double.toString(Robot.forwardDrive.GetRightDriveOneValue());
    	String driveRightTwoValue = Double.toString(Robot.forwardDrive.GetRightDriveTwoValue());
    	String isInLowGear = Boolean.toString(Robot.forwardDrive.GetIsInLowGear());
    	String sideDriveMotorValue = Double.toString(Robot.sideDrive.GetMotorValue());
    	String guideArmIsDeployed = Boolean.toString(Robot.guideArm.IsDeployed());
    	String toteSupportIsDeployed = Boolean.toString(Robot.toteSupport.IsDeployed());
    	String forwardDriveJoystickValue = Double.toString(Robot.driveStick.getY());
    	String sideDriveJoystickValue = Double.toString(Robot.driveStick.getX());
    	String servoPosition = Double.toString(Robot.toteSupport.currentServoAngle());
		try {
			Files.write(Settings.logFilePath, (
					currentTime
					+ comma + robotState
					+ comma + robotHeading
					+ comma + elevatorPosition
					+ comma + elevatorMotorValue
					+ comma + elevatorUpperSwitch
					+ comma + elevatorUpperCounter
					+ comma + elevatorLowerSwitch
					+ comma + elevatorLowerCounter
					+ comma + driveLeftPosition
					+ comma + driveRightPosition
					+ comma + driveLeftOneValue
					+ comma + driveLeftTwoValue
					+ comma + driveRightOneValue
					+ comma + driveRightTwoValue
					+ comma + isInLowGear
					+ comma + sideDriveMotorValue
					+ comma + guideArmIsDeployed
					+ comma + toteSupportIsDeployed
					+ comma + forwardDriveJoystickValue
					+ comma + sideDriveJoystickValue
					+ comma + servoPosition
					+ "\n"
					).getBytes("utf-8"), StandardOpenOption.WRITE, StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println("Log write failed");
			System.out.println(e.getMessage());
		}
	}
}
