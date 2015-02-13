package org.usfirst.frc.team3668.robot.subsystems;

import org.usfirst.frc.team3668.robot.IOLabels;
import org.usfirst.frc.team3668.robot.Settings;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GuideArm extends Subsystem {
    Talon guideArmMotor;
    double currentDirection;
    boolean isDeployed;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public GuideArm(){
		guideArmMotor = new Talon(IOLabels.guideArmMotorPort);
		
		
	}
	
	public void MoveArms(int direction){
		
		guideArmMotor.set(-direction * Settings.guideArmMotorSpeed);
		
	}
	
	public void SetDeployed(boolean deployed){
		
		isDeployed = deployed;
		
	}
	
	public boolean IsDeployed(){
		
		return isDeployed;
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

