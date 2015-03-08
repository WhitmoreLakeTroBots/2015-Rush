package org.usfirst.frc.team3668.robot.subsystems;

import org.usfirst.frc.team3668.robot.IOLabels;
//import org.usfirst.frc.team3668.robot.Settings;
import org.usfirst.frc.team3668.robot.commands.JoystickSideDrive;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SideDrive extends Subsystem {
    
	Talon sideDriveMotor;
	
	public SideDrive(){
		sideDriveMotor = new Talon(IOLabels.sideDriveMotorPort);
	}
	
	public void sideDrive(double speed){
		sideDriveMotor.set(speed);
	}
	
	public double GetMotorValue() {
		return sideDriveMotor.get();
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new JoystickSideDrive());
    }
}

