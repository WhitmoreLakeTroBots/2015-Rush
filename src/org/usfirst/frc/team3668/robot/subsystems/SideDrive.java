package org.usfirst.frc.team3668.robot.subsystems;

import org.usfirst.frc.team3668.robot.IOLabels;
import org.usfirst.frc.team3668.robot.Settings;
import org.usfirst.frc.team3668.robot.SpeedScaler;
import org.usfirst.frc.team3668.robot.commands.JoystickSideDrive;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SideDrive extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Talon sideDriveMotor;
	
	public SideDrive(){
		sideDriveMotor = new Talon(IOLabels.sideDriveMotorPort);
	}
	
	public void sideDrive(double speed){
		sideDriveMotor.set(speed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new JoystickSideDrive());
    }
}

