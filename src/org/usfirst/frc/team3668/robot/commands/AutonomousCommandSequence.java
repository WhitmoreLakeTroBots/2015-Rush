package org.usfirst.frc.team3668.robot.commands;

import org.usfirst.frc.team3668.robot.IOLabels;
import org.usfirst.frc.team3668.robot.Robot;
import org.usfirst.frc.team3668.robot.Settings;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommandSequence extends CommandGroup {
    
	
    public  AutonomousCommandSequence() {
//    	autonomousSwitch = new AnalogInput(IOLabels.brendanIsPickyAutonomousSwitchPort);
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
		CommandGroup calibrateTote = new CommandGroup();
		CommandGroup calibrateContainer = new CommandGroup();
		CommandGroup stepOneForTotes = new CommandGroup();
//		CommandGroup stepOneForContainer = new CommandGroup();
		
//		calibrateTote.addParallel(new SetGuideArm(Settings.autoGuideArmDirection));		
		calibrateTote.addParallel(new ElevatorCalibrate());
//		calibrateContainer.addParallel(new SetGuideArm(Settings.autoGuideArmDirection));		
    	calibrateContainer.addParallel(new ElevatorCalibrate());
		stepOneForTotes.addParallel(new ElevatorGoToPosition(Settings.toteHeight));
    	stepOneForTotes.addParallel(new DriveToPosition(Settings.autoToteDriveDistance));
    	
//    	stepOneForContainer.addParallel(new ElevatorGoToPosition(Settings.containerHeight));
//    	stepOneForContainer.addParallel(new DriveToPosition(Settings.autoContainerDriveDistance));

    	if(AutonomousModeSwitch()){
    		addSequential(calibrateContainer);
//    		addSequential(stepOneForContainer);
        	addSequential(new DriveToPosition(Settings.autoContainerDriveDistance));
    		addSequential(new ElevatorGoToPosition(Settings.containerHeight));
        	addSequential(new ElevatorGoToPosition(Settings.autoContainerLift));
//        	addSequential(new TurnToHeading(Settings.autoTurn));
        	addSequential(new DriveToPositionWithHeading(Settings.autoZoneDistance, Settings.autoTurn));
        	addSequential(new TurnToHeading(Settings.autoAlmostFinalHeading));
    	} else {

//        	addSequential(calibrateTote);
//        	addSequential(stepOneForTotes);
//        	addSequential(new ElevatorGoToPosition(Settings.autoToteLift));
//        	addSequential(new TurnToHeading(Settings.autoTurn));
//        	addSequential(new DriveToPositionWithHeading(Settings.autoZoneDistance, Settings.autoTurn));
//        	addSequential(new TurnToHeading(0));
    		
    		addSequential(calibrateTote);
        	addSequential(stepOneForTotes);
        	addSequential(new ElevatorGoToPosition(Settings.autoToteLift));
//        	addSequential(new TurnToHeading(Settings.autoTurn));
        	addSequential(new DriveToPositionWithHeading(Settings.autoZoneDistance, Settings.autoTurn));
        	addSequential(new TurnToHeading(Settings.autoAlmostFinalHeading));
//        	addSequential(new ElevatorGoToPosition(Settings.bottomElevator));
//        	addSequential(new DriveToPositionWithHeading(Settings.retreatDistance, Settings.autoAlmostFinalHeading));
//        	addSequential(new TurnToHeading(Settings.autoFinalHeading));
    		
    	}
    	// To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
    	requires(Robot.elevator);
    	requires(Robot.forwardDrive);
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
    
    public boolean AutonomousModeSwitch(){
    	
    	
    	return Robot.autonomousSwitch.getVoltage() > 2.5;
    	
    }
    
}
