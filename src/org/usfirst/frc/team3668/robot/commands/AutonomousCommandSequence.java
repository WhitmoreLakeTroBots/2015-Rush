package org.usfirst.frc.team3668.robot.commands;

import org.usfirst.frc.team3668.robot.Robot;
import org.usfirst.frc.team3668.robot.Settings;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommandSequence extends CommandGroup {
    
	
	
    public  AutonomousCommandSequence() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
//    	if(){
//    		
//    	}
    	addSequential(new ElevatorGoToPosition(Settings.toteHeight));
    	addSequential(new DriveToPosition(Settings.autoToteDriveDistance));
    	addSequential(new ElevatorGoToPosition(Settings.autoToteLift));
    	addSequential(new TurnToHeading(Settings.autoTurn));
    	addSequential(new DriveToPosition(Settings.autoZoneDistance));
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
}
