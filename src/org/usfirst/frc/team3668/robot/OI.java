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
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
	double containerStep = Settings.containerHeight + Settings.autoStepHeight;
	double toteStep = Settings.toteHeight + Settings.autoStepHeight;
	double twoToteHeight = Settings.toteHeight + Settings.toteHeight;
	Joystick driveStick = Robot.driveStick;
	Joystick lifterStick = Robot.lifterStick;
	Button toContainerHeight = new JoystickButton(lifterStick, IOLabels.goToContainerHeightButton);
	Button toToteHeight = new JoystickButton(lifterStick, IOLabels.goToToteHeightButton);
	Button shift = new JoystickButton(driveStick, IOLabels.shiftGearsButton);
//	Button turn = new JoystickButton(lifterStick, 7);
//	Button drive = new JoystickButton(driveStick, 4);
//	Button toggleGuideArm = new JoystickButton(lifterStick, IOLabels.toggleGuideArm);
//	Button calibrateElevator = new JoystickButton(lifterStick, 12);
	Button mattIsPickyForTuskUp = new JoystickButton(lifterStick, IOLabels.setGuideArmUp);
	Button mattIsPickyForTuskDown = new JoystickButton(lifterStick, IOLabels.setGuideArmDown);
	Button stepTote = new JoystickButton(lifterStick, IOLabels.goToToteStepHeight);
	Button stepContainer = new JoystickButton(lifterStick, IOLabels.goToContainerStepHeight);
	Button toTwoToteHeight = new JoystickButton(lifterStick, IOLabels.goToTwoToteHeight);
	
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to32                                             
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    public OI(){	
    
    if(SmartDashboard.getBoolean("Matt", false)){ 
    	
        mattIsPickyForTuskUp.whenPressed(new SetGuideArm(-1));
        mattIsPickyForTuskDown.whenPressed(new SetGuideArm(1));
    	
    } else {
    	
        mattIsPickyForTuskUp.whenPressed(new SetGuideArm(1));
        mattIsPickyForTuskDown.whenPressed(new SetGuideArm(-1));
    }
    toTwoToteHeight.whenPressed(new ElevatorGoToPosition(twoToteHeight));

    shift.whenPressed(new ShiftGears());
    toContainerHeight.whenPressed(new ElevatorGoToPosition(Settings.containerHeight));	
    toToteHeight.whenPressed(new ElevatorGoToPosition(Settings.toteHeight));
    stepTote.whenPressed(new ElevatorGoToPosition(toteStep));
    stepContainer.whenPressed(new ElevatorGoToPosition(containerStep));
//    toggleGuideArm.whenPressed(new ToggleGuideArm());
//    calibrateElevator.whenPressed(new ElevatorCalibrate());
//    turn.whenPressed(new TurnToHeading(Settings.autoTurn));
//    drive.whenPressed(new DriveToPosition(20));
    	
    }
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
     
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

