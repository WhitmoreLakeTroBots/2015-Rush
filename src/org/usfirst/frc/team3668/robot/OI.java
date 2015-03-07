package org.usfirst.frc.team3668.robot;

import org.usfirst.frc.team3668.robot.commands.ElevatorCalibrate;
import org.usfirst.frc.team3668.robot.commands.ElevatorGoToPosition;
import org.usfirst.frc.team3668.robot.commands.ResetGyro;
import org.usfirst.frc.team3668.robot.commands.SetGuideArm;
import org.usfirst.frc.team3668.robot.commands.ShiftGears;
import org.usfirst.frc.team3668.robot.commands.ToggleGuideArm;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	double currentToteLevel;
	double numberOfToteIncrements;
	double containerStep = Settings.containerHeight + Settings.autoStepHeight;
	double toteStep = Settings.toteHeight + Settings.autoStepHeight;
	double twoToteHeight = (Settings.actualToteHeight + Settings.actualToteHeight) -10.25;
	double threeToteHeight = (Settings.actualToteHeight * 3) -10.25;
	double fourToteHeight = (Settings.actualToteHeight * 4) -10.25;
	double toteScoreStep = Settings.toteHeight + Settings.autoStepHeight + Settings.scoringPlatformHeight;
	double twoToteStepHeight = twoToteHeight + Settings.scoringPlatformHeight;
	double threeToteStepHeight = threeToteHeight + Settings.scoringPlatformHeight;
	double fourToteStepHeight = fourToteHeight + Settings.scoringPlatformHeight;
	
	Joystick driveStick = Robot.driveStick;
	Joystick lifterStick = Robot.lifterStick;
	
	Button toContainerHeight = new JoystickButton(lifterStick, IOLabels.goToContainerHeightButton);
	Button toToteHeight = new JoystickButton(lifterStick, IOLabels.goToToteHeightButton);
	Button toTwoToteHeight = new JoystickButton(lifterStick, IOLabels.goToTwoToteHeightButton);
	Button toThreeToteHeight = new JoystickButton(lifterStick, IOLabels.goToThreeToteHeightButton);
	Button toFourToteHeight = new JoystickButton(lifterStick, IOLabels.goToFourToteHeightButton);
	Button toOneToteStepHeight = new JoystickButton(lifterStick, IOLabels.goToOneToteStepHeight);
	Button shift = new JoystickButton(driveStick, IOLabels.shiftGearsButton);
	Button GyroReset = new JoystickButton(driveStick,IOLabels.gyroReset);
	public Button mattIsPickyForTuskToggle = new JoystickButton(lifterStick, IOLabels.toggleGuideArm);


    public OI(){
        ToggleGuideArm tg = new ToggleGuideArm();

    	toOneToteStepHeight.whenPressed(new ElevatorGoToPosition(Settings.scoringPlatformHeight));
    	shift.whenPressed(new ShiftGears());    	
        toToteHeight.whenPressed(new ElevatorGoToPosition(Settings.toteHeight));
        toTwoToteHeight.whenPressed(new ElevatorGoToPosition(Settings.twoToteHeight));
        toThreeToteHeight.whenPressed(new ElevatorGoToPosition(Settings.threeToteHeight));
        toFourToteHeight.whenPressed(new ElevatorGoToPosition(Settings.fourToteHeight));	
        mattIsPickyForTuskToggle.cancelWhenPressed(tg);
        mattIsPickyForTuskToggle.whenPressed(tg);
        
//    if(toToteHeight.get()){
//    	currentToteLevel = 0;
//    	numberOfToteIncrements = 0;
//    }
//    stepTote.whenPressed(new ElevatorGoToPosition(toteStep));
//    stepContainer.whenPressed(new ElevatorGoToPosition(containerStep));
//        GyroReset.whenPressed(new ResetGyro());
    SmartDashboard.putData("Calibrate the Elevator: ", new ElevatorCalibrate());
    SmartDashboard.putData("Calibrate the Gyro: ", new ResetGyro());

    }

}

