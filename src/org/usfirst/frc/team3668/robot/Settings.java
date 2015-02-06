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

	public static final double lowGearRight = 110;
	public static final double lowGearLeft = 145;
	public static final double highGearRight = 45;
	public static final double highGearLeft = 80;
	
	//Pre-determined Heights
	public static final double toteHeight = 10;
	public static final double containerHeight = 20;
	
	//Autonomous Settings
	public static final double autoTurn = 90;
	public static final double autoToteDriveDistance = 6;
	public static final double autoToteLift = 12;	
	public static final double autoZoneDistance = 20;	
	public static final double autoTurnSpeed = 0.5;

	
	//Other Settings
	public static final double sideDriveSpeed = 0.5;
	public static final double turnSensitivityMultiplier = .8;
	public static final double turnDeadband = 2;
		public static final double DriveEncoderScaleFactor = 6*Math.PI/275; //6 in wheel, 275 ticks per revolution of the wheel, .0685 in per tick
	public static final double driveSlowDownDistance = 400;
	public static final double driveTolerance = 5;
	
}