package org.usfirst.frc.team3668.robot;

import org.usfirst.frc.team3668.robot.commands.ElevatorCalibrate;
import org.usfirst.frc.team3668.robot.commands.ElevatorGoToPosition;
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
//	Button addScoringHeight = new JoystickButton(lifterStick, IOLabels.addScoringHeight);
	Button stepTote = new JoystickButton(lifterStick, IOLabels.goToToteStepHeight);
	Button stepContainer = new JoystickButton(lifterStick, IOLabels.goToContainerStepHeight);
	
	Button shift = new JoystickButton(driveStick, IOLabels.shiftGearsButton);
	Button turn = new JoystickButton(lifterStick, 7);
//	Button mattIsPickyForTuskToggle = new JoystickButton(lifterStick, IOLabels.toggleGuideArm);
	Button guideUp = new JoystickButton(lifterStick, 1);
	Button guideDown = new JoystickButton(lifterStick, 2);


    public OI(){	
    
//    if(SmartDashboard.getBoolean("Matt", false)){ 
//    	
//        mattIsPickyForTuskUp.whenPressed(new SetGuideArm(-1));
//        mattIsPickyForTuskDown.whenPressed(new SetGuideArm(1));
//    	
//    } else {
//    	
//        mattIsPickyForTuskUp.whenPressed(new SetGuideArm(1));
//        mattIsPickyForTuskDown.whenPressed(new SetGuideArm(-1));
//    }
//    plusOneTote.whenPressed(new ElevatorGoToPosition(elevatorHeightPlusTote));
//    goToSetHeight.whenPressed(new ElevatorGoToPosition(SmartDashboard.getNumber("Elevator Set Height: ")));

    shift.whenPressed(new ShiftGears());
    guideUp.whenPressed(new SetGuideArm(1));
    guideDown.whenPressed(new SetGuideArm(-1));
//    if(addScoringHeight.get()){
//    	
//    	toToteHeight.whenPressed(new ElevatorGoToPosition(toteStep));
//        toTwoToteHeight.whenPressed(new ElevatorGoToPosition(twoToteStepHeight));
//        toThreeToteHeight.whenPressed(new ElevatorGoToPosition(threeToteStepHeight));
//        toFourToteHeight.whenPressed(new ElevatorGoToPosition(fourToteStepHeight));
//    	
//        
//    } else {
    	
    	
        toToteHeight.whenPressed(new ElevatorGoToPosition(Settings.toteHeight));
        toTwoToteHeight.whenPressed(new ElevatorGoToPosition(twoToteHeight));
        toThreeToteHeight.whenPressed(new ElevatorGoToPosition(threeToteHeight));
        toFourToteHeight.whenPressed(new ElevatorGoToPosition(fourToteHeight));	
    	
//    }
//    mattIsPickyForTuskToggle.whenPressed(new ToggleGuideArm());
    if(toToteHeight.get()){
    	currentToteLevel = 0;
    	numberOfToteIncrements = 0;
    }
    stepTote.whenPressed(new ElevatorGoToPosition(toteStep));
    stepContainer.whenPressed(new ElevatorGoToPosition(containerStep));

    SmartDashboard.putData("Calibrate the Elevator: ", new ElevatorCalibrate());

    }

}

