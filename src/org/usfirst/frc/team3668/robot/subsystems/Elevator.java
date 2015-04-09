package org.usfirst.frc.team3668.robot.subsystems;

import org.usfirst.frc.team3668.robot.IOLabels;
import org.usfirst.frc.team3668.robot.Robot;
import org.usfirst.frc.team3668.robot.Settings;
import org.usfirst.frc.team3668.robot.commands.JoystickElevator;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	TalonSRX elevatorMotor;
	Encoder elevatorMotorEncoder;
	DigitalInput elevatorLowerSwitch;
	DigitalInput elevatorUpperSwitch;
	boolean switchAtBottom;
	boolean switchAtTop;
	boolean counterAtTop;
	boolean counterAtBottom;
	boolean encoderAtBottomWithGuidearmDeployed;
	Counter elevatorLowerSwitchCounter;
	Counter elevatorUpperSwitchCounter;
	double direction;
	double elevatorPosition;
	
	boolean isCalibrated = false;
	
	public Elevator(){
		
		elevatorMotor = new TalonSRX(IOLabels.elevatorMotorPort);
		elevatorMotorEncoder = new Encoder(IOLabels.elevatorEncoderAPort, IOLabels.elevatorEncoderBPort);		
		elevatorMotorEncoder.setDistancePerPulse(Settings.elevatorEncoderScaleFactor);
//		elevatorMotor.setSafetyEnabled(true);  EVIL!
		elevatorLowerSwitch = new DigitalInput(IOLabels.elevatorLowerLimitSwitchPort);
		elevatorUpperSwitch = new DigitalInput(IOLabels.elevatorUpperLimitSwitchPort);
		elevatorLowerSwitchCounter = new Counter(elevatorLowerSwitch);
		elevatorUpperSwitchCounter = new Counter(elevatorUpperSwitch);
	}
	
	public void raiseLower(double vector){
		vector = -vector;
		direction = Math.signum(vector);
//		if(Math.abs(direction) < .05){
//			
//			direction = 0;
//			
//		}
		if(direction == -1){
			
			vector = Settings.elevatorDownSpeedScaler*vector;
		
		}
		
		if(canMove(vector, direction)){
			
			if((elevatorMotorEncoder.getDistance() < 2) && direction == -1 && vector < -.5){
				
				elevatorMotor.set(-.5);
				
			} else if((elevatorMotorEncoder.getDistance() < 1) && direction == -1 && vector < -.25){
				
				elevatorMotor.set(-.25);
				
			} else if((elevatorMotorEncoder.getDistance() > 45) && direction == 1 && vector > .5){
				
				elevatorMotor.set(.5);
				
			} else if((elevatorMotorEncoder.getDistance() > 46) && direction == 1 && vector > .25){
				
				elevatorMotor.set(.25);
				
			} else{
				
				elevatorMotor.set(vector*0.8);
				
			}
//			SmartDashboard.putBoolean("Can Move?", true);
			
		} else {

//			SmartDashboard.putBoolean("Can Move?", false);
			elevatorMotor.set(0);
			
		}
//		SmartDashboard.putNumber("Current Elevator Encoder Position: ", elevatorMotorEncoder.getDistance());
//		SmartDashboard.putNumber("Elevator Encoder Raw: ", elevatorMotorEncoder.get());
//		SmartDashboard.putNumber("Current Counter: ", counter.get());
//		System.out.println("Current Counter: " + counter.get());
//		System.out.println("Direction: " + elevatorMotor.get());
//		SmartDashboard.putNumber("Direction: ", elevatorMotor.get());
//		

		if(direction == 1){
			elevatorLowerSwitchCounter.reset();
		}
		if(direction == -1){
			elevatorUpperSwitchCounter.reset();
		}
	}
	
	public void calibrateEncoder(){
		elevatorMotorEncoder.reset();
		isCalibrated = true;
	}
	
	public boolean IsCalibrated() {
		return isCalibrated;
	}
	
	public double currentHeight(){
		return elevatorMotorEncoder.getDistance();
	}

	public boolean isAtBottom(){
		switchAtBottom = !elevatorLowerSwitch.get();
		counterAtBottom = elevatorLowerSwitchCounter.get() > 0;
		encoderAtBottomWithGuidearmDeployed = currentHeight() <= Settings.elevatorBottomPositionWithGuidearmDeployed && Robot.guideArm.IsDeployed() ;
		return counterAtBottom || switchAtBottom || (IsCalibrated() && encoderAtBottomWithGuidearmDeployed);
	}
	
	public boolean isAtTop(){
		switchAtTop = !elevatorUpperSwitch.get();
		counterAtTop =  elevatorUpperSwitchCounter.get() > 0;
		return counterAtTop || switchAtTop;
	}
	
	public double GetMotorValue() {
		return elevatorMotor.get();
	}
	
	public boolean GetUpperSwitch(){
		return elevatorUpperSwitch.get();
	}
	
	public int GetUpperCounter(){
		return elevatorUpperSwitchCounter.get();
	}
	
	public boolean GetLowerSwitch(){
		return elevatorLowerSwitch.get();
	}
	
	public int GetLowerCounter(){
		return elevatorLowerSwitchCounter.get();
	}
	
    protected boolean canMove(double direction, double requestedDirection){
    	return !(isAtBottom() && requestedDirection == -1) && !(isAtTop() && requestedDirection == 1);
    }
	
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new JoystickElevator());
    }
}

