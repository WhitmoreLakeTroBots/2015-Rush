package org.usfirst.frc.team3668.robot.commands;


import org.usfirst.frc.team3668.robot.Robot;
import org.usfirst.frc.team3668.robot.Settings;
import org.usfirst.frc.team3668.robot.subsystems.GuideArm;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SetGuideArm extends Command {
	GuideArm guideArm;
	int direction;
	Timer timer;

	
	
    public SetGuideArm(int direction) {
    	guideArm = Robot.guideArm;
    	requires(guideArm);
    	this.direction = direction;
    	timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	guideArm.MoveArms(direction);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() > Settings.guideArmMotorRunTime;
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.stop();
    	guideArm.MoveArms(0);
 
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	timer.stop();
    	guideArm.MoveArms(0);
    }
}
