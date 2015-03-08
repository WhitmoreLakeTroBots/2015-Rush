package org.usfirst.frc.team3668.robot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.util.Date;

public class Logger {
	
	private static final String comma = ",";
	
	public static void InitFile() {
		try {
			Files.write(Settings.path, (
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
					+ comma + "DriveRightTwoVMotoralue"
					+ comma + "DriveIsInLowGear"
					+ comma + "SideDriveMotorValue"
					+ comma + "GuideArmIsDeployed"
					+ "\n"
					).getBytes("utf-8"), StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			System.out.println("Log file init failed");
			System.out.println(e.getMessage());
		}
		Log("InitFile");
	}

	public static void Log(String robotState) {
    	String currentTime = DateFormat.getTimeInstance(DateFormat.FULL).format(new Date());
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
    	
		try {
			Files.write(Settings.path, (
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
					+ "\n"
					).getBytes("utf-8"), StandardOpenOption.WRITE, StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println("Log write failed");
			System.out.println(e.getMessage());
		}
	}
}
