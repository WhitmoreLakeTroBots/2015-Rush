package org.usfirst.frc.team3668.robot;

public class Settings {

	//Elevator Settings
	public static final double elevatorEncoderScaleFactor = 47/-2815.0;
	public static final double elevatorDistanceTolerance = .1;
	public static final double elevatorMinMotorCommandUp = 0.3;
	public static final double elevatorMinMotorCommandDown = 0.1;
	public static final double elevatorSlowDownDistance = 5;
	public static final double elevatorSpeedUpDistance = 2;
	public static final double elevatorDownSpeedScaler = .75;

	//Drive Gear Settings
	public static final double lowGearRight = 117;
	public static final double lowGearLeft = 145;
	public static final double highGearRight = 35;
	public static final double highGearLeft = 80;
	
	//Pre-determined Heights
	public static final double toteHeight = .5;
	public static final double containerHeight = 11.5;
	public static final double twoToteHeight = 24;
	public static final double actualToteHeight = 12;
	
	//Autonomous Settings
	public static final double autoToteDriveDistance = 6;
	public static final double autoContainerDriveDistance = 6;
	public static final double autoToteLift = 9;
	public static final double autoContainerLift = 27;
	public static final double autoZoneDistance = -115;	
	public static final double autoTurnSpeed = 0.6;
	public static final double autoDriveSpeed = 1;
	public static final double autoStepHeight = 6.25;
	public static final double autoTurn = 0;
	public static final double autoFinalHeading = 90;
	public static final int autoGuideArmDirection = -1;
	
	//Other Settings
	public static final double sideDriveSpeed = 0.5;
	public static final double turnSensitivityMultiplier = .8;
	public static final double turnDeadband = 2;
	public static final double DriveEncoderScaleFactor = 6*Math.PI/279; //6 in wheel, 275 ticks per revolution of the wheel, .0685 in per tick
	public static final double driveSlowDownDistance = 20;
	public static final double driveSlowDownSpeed = 0.5;
	public static final double driveToPositionDeadband = 2;
	public static final double turnSlowDownAngle = 20;
	public static final double minimumTurnSpeed = 0.4;
	public static final double driveTurnGain = 0.1;
	public static final double guideArmMotorSpeed = 0.25;
	public static final double guideArmMotorRunTime = 2.5;
	public static final double scoringPlatformHeight = 1.96;
	
	
}