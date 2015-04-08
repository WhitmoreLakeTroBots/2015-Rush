package org.usfirst.frc.team3668.robot.commands;

import org.usfirst.frc.team3668.robot.Robot;
import org.usfirst.frc.team3668.robot.subsystems.ToteSupport;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ToggleToteSupport extends Command {
	private ToteSupport toteSupport;
	private boolean isDeployed;

    public ToggleToteSupport() {
    	toteSupport = Robot.toteSupport;
    	requires(toteSupport);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isDeployed = toteSupport.IsDeployed();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (isDeployed){
    		toteSupport.Retract();
    	} else {
    		toteSupport.Deploy();
    	}
    	SmartDashboard.putBoolean("Ding Dong State: ", isDeployed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
