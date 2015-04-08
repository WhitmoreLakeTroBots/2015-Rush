package org.usfirst.frc.team3668.robot.commands;


import org.usfirst.frc.team3668.robot.IOLabels;
import org.usfirst.frc.team3668.robot.Robot;
import org.usfirst.frc.team3668.robot.subsystems.GuideArm;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SetGuideArm extends Command {
	GuideArm guideArm;
//	int direction;
//	Timer timer;
	double speed;
	Button manualGuideButton;
	
	
    public SetGuideArm(double speed) {
    
    	manualGuideButton = new JoystickButton(Robot.driveStick, IOLabels.brendanMoveGuideArmButton);
    	guideArm = Robot.guideArm;
    	requires(guideArm);
//    	this.direction = direction;
//    	timer = new Timer();
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	timer.reset();
//    	timer.start();    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(manualGuideButton.get()){
    		guideArm.ButtonMoveArms(speed);	
    	} else {
    		guideArm.ButtonMoveArms(0);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//        return timer.get() > Settings.guideArmMotorRunTime;
//    }

    // Called once after isFinished returns true
    protected void end() {
//    	timer.stop();
    	guideArm.ButtonMoveArms(0);
 
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
//    	timer.stop();
    	guideArm.ButtonMoveArms(0);
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
