package org.usfirst.frc.team3668.robot.subsystems;

import org.usfirst.frc.team3668.robot.IOLabels;
import org.usfirst.frc.team3668.robot.Robot;
import org.usfirst.frc.team3668.robot.Settings;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GuideArm extends Subsystem {
    Talon guideArmMotor;
    private boolean isDeployed;

	public GuideArm(){
		guideArmMotor = new Talon(IOLabels.guideArmMotorPort);
	}
	
	public void MoveArms(int direction){
		if (CanMove(direction)) {
			guideArmMotor.set(-direction * Settings.guideArmMotorSpeed);
			if(direction != 0){
				isDeployed = (direction == 1);
			}
		} else {
			guideArmMotor.set(0);
		}
	}
	
	
	public boolean IsDeployed(){
		return isDeployed;
	}
	
	private boolean CanMove(int direction) {
		return Robot.elevator.currentHeight() > Settings.elevatorBottomPositionWithGuidearmDeployed
				|| Math.signum(direction) == 1;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

