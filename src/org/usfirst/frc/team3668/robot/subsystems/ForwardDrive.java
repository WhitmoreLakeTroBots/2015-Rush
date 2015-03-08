package org.usfirst.frc.team3668.robot.subsystems;

import org.usfirst.frc.team3668.robot.IOLabels;
import org.usfirst.frc.team3668.robot.Robot;
import org.usfirst.frc.team3668.robot.Settings;
import org.usfirst.frc.team3668.robot.commands.JoystickForwardDrive;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ForwardDrive extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Talon rightOne;
	Talon rightTwo;
	Talon leftOne;
	Talon leftTwo;
	Encoder leftEncoder;
	Encoder rightEncoder;
	Servo rightGearShiftServo;
	Servo leftGearShiftServo;
	RobotDrive chassis;
	Joystick driveStick;
	public Gyro gyro;
	boolean isInLowGear;
	double initialGyroPosition;
	
	public ForwardDrive(){
		leftEncoder = new Encoder(IOLabels.driveEncoderLeftAPort, IOLabels.driveEncoderLeftBPort);
		rightEncoder = new Encoder(IOLabels.driveEncoderRightAPort, IOLabels.driveEncoderRightBPort);
		leftEncoder.setDistancePerPulse(Settings.DriveEncoderScaleFactor); //Need to check the direction of the encoders to make sure that forwards is positive
		rightEncoder.setDistancePerPulse(-Settings.DriveEncoderScaleFactor);
		rightOne = new Talon(IOLabels.rightMotorOnePort);
		rightTwo = new Talon(IOLabels.rightMotorTwoPort);
		leftOne = new Talon(IOLabels.leftMotorOnePort);
		leftTwo = new Talon(IOLabels.leftMotorTwoPort);
		rightGearShiftServo = new Servo(IOLabels.rightShifterServo);
		leftGearShiftServo = new Servo(IOLabels.leftShifterServo);
		chassis = new RobotDrive(leftOne, leftTwo, rightOne, rightTwo);
		isInLowGear = true;
		rightGearShiftServo.setAngle(Settings.lowGearRight);
		leftGearShiftServo.setAngle(Settings.lowGearLeft);
		driveStick = Robot.driveStick;
		gyro = new Gyro(IOLabels.gyroPort);
		InitGyro();
	}
	
	public void InitGyro(){
		initialGyroPosition = gyro.getAngle();
	}
		
	public void Drive(double speed, double turn){
			chassis.arcadeDrive(speed, turn);
//			SmartDashboard.putNumber("Left Drive Encoder: ", leftEncoder.getDistance());
//			SmartDashboard.putNumber("Right Drive Encoder: ", rightEncoder.getDistance());
//			SmartDashboard.putNumber("Robot Angle: ", GetRobotHeading());
	}
	
	public void ShiftGears(){
		
		//To make sure that we aren't going fast when we change gears.
		//if(driveStick.getAxis(Joystick.AxisType.kY) <= 0.5){
		
			if(isInLowGear){
			
				rightGearShiftServo.setAngle(Settings.highGearRight);
				leftGearShiftServo.setAngle(Settings.highGearLeft);
				isInLowGear = false;
			} else if(!isInLowGear){
			
				rightGearShiftServo.setAngle(Settings.lowGearRight);
				leftGearShiftServo.setAngle(Settings.lowGearLeft);
				isInLowGear = true;
			}
	//	}
		
	}
	
	
//	public void TestServos(double degrees){
//		rightGearShiftServo.setAngle(degrees*270);
//		leftGearShiftServo.setAngle(degrees*270);
//		SmartDashboard.putNumber("Degree of Servo:", degrees*270);
//	}
	
	public double GetRightEncoder(){
		return rightEncoder.getDistance();
	}
	
	public double GetLeftEncoder(){
		return leftEncoder.getDistance();
	}
	
	public double GetRobotHeading(){
		return gyro.getAngle() - initialGyroPosition;
	}
	
	public double GetLeftDriveOneValue() {
		return leftOne.get();
	}
	
	public double GetLeftDriveTwoValue() {
		return leftTwo.get();
	}
	
	public double GetRightDriveOneValue() {
		return rightOne.get();
	}
	
	public double GetRightDriveTwoValue() {
		return rightTwo.get();
	}
	
	public boolean GetIsInLowGear() {
		return isInLowGear;
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new JoystickForwardDrive());
    }
}

