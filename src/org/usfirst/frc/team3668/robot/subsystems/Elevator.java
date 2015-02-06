package org.usfirst.frc.team3668.robot.subsystems;

import org.usfirst.frc.team3668.robot.IOLabels;
import org.usfirst.frc.team3668.robot.Settings;
import org.usfirst.frc.team3668.robot.commands.JoystickElevator;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	Counter elevatorLowerSwitchCounter;
	Counter elevatorUpperSwitchCounter;
	double requestedDirection;
	double elevatorPosition;
	
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
	
	public void raiseLower(double direction){
		direction = -direction;
		requestedDirection = Math.signum(direction);
//		if(Math.abs(direction) < .05){
//			
//			direction = 0;
//			
//		}
		if(requestedDirection == -1){
			
			direction = Settings.elevatorDownSpeedScaler*direction;
		
		}
		
		if(canMove(direction, requestedDirection)){
			
			if((elevatorMotorEncoder.getDistance() < 2) && requestedDirection == -1 && direction < -.5){
				
				elevatorMotor.set(-.5);
				
			} else if((elevatorMotorEncoder.getDistance() < 1) && requestedDirection == -1 && direction < -.25){
				
				elevatorMotor.set(-.25);
				
			} else if((elevatorMotorEncoder.getDistance() > 45) && requestedDirection == 1 && direction > .5){
				
				elevatorMotor.set(.5);
				
			} else if((elevatorMotorEncoder.getDistance() > 46) && requestedDirection == 1 && direction > .25){
				
				elevatorMotor.set(.25);
				
			} else{
				
				elevatorMotor.set(direction);
				
			}
			SmartDashboard.putBoolean("Can Move?", true);
			
		} else {

			SmartDashboard.putBoolean("Can Move?", false);
			elevatorMotor.set(0);
			
		}
		SmartDashboard.putNumber("Current Elevator Encoder Position: ", elevatorMotorEncoder.getDistance());
		SmartDashboard.putNumber("Elevator Encoder Raw: ", elevatorMotorEncoder.get());
//		SmartDashboard.putNumber("Current Counter: ", counter.get());
//		System.out.println("Current Counter: " + counter.get());
//		System.out.println("Direction: " + elevatorMotor.get());
		SmartDashboard.putNumber("Direction: ", elevatorMotor.get());
//		

		if(requestedDirection == 1){
			
			elevatorLowerSwitchCounter.reset();
			
			
		}
		if(requestedDirection == -1){
			
			elevatorUpperSwitchCounter.reset();
			
		}
		
		
	}
	
	public void calibrateEncoder(){
		
		elevatorMotorEncoder.reset();
		
	}
	
	public double currentHeight(){
		
		return elevatorMotorEncoder.getDistance();	
	}
	
	
	
	public boolean isAtBottom(){
		
		switchAtBottom = !elevatorLowerSwitch.get();
		counterAtBottom = elevatorLowerSwitchCounter.get() > 0;
		return counterAtBottom || switchAtBottom;
		
	}
	
	public boolean isAtTop(){
		
		switchAtTop = !elevatorUpperSwitch.get();
		counterAtTop =  elevatorUpperSwitchCounter.get() > 0;
		return counterAtTop || switchAtTop;
		
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

