package org.usfirst.frc.team3668.robot.subsystems;

import org.usfirst.frc.team3668.robot.IOLabels;
import org.usfirst.frc.team3668.robot.Settings;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ToteSupport extends Subsystem {
    
	Servo toteSupportServo;
    private boolean isDeployed;

	public ToteSupport() {
		toteSupportServo = new Servo(IOLabels.toteSupportServoPort);
	}
	
	public void Deploy() {
		toteSupportServo.setAngle(Settings.toteSupportDeployedAngle);
		isDeployed = true;
	}
	
	public void Retract() {
		toteSupportServo.setAngle(Settings.toteSupportRetractedAngle);
		isDeployed = false;
	}
	
	public boolean IsDeployed(){
		return isDeployed;	
	}
	
	public double currentServoAngle(){
		
		return toteSupportServo.getAngle();
		
	}

	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

