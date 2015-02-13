
package org.usfirst.frc.team3668.robot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.usfirst.frc.team3668.robot.commands.AutonomousCommandSequence;
import org.usfirst.frc.team3668.robot.commands.ElevatorCalibrate;
//import org.usfirst.frc.team3668.robot.commands.ExampleCommand;
import org.usfirst.frc.team3668.robot.subsystems.Elevator;
import org.usfirst.frc.team3668.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team3668.robot.subsystems.ForwardDrive;
import org.usfirst.frc.team3668.robot.subsystems.GuideArm;
import org.usfirst.frc.team3668.robot.subsystems.SideDrive;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static final ForwardDrive forwardDrive = new ForwardDrive();
	public static final SideDrive sideDrive = new SideDrive();
	public static final Elevator elevator = new Elevator();
	public static final GuideArm guideArm = new GuideArm();
	public static OI oi;
	
	public static Joystick driveStick = new Joystick(IOLabels.DriveStick);
	public static Joystick lifterStick = new Joystick(IOLabels.LifterStick);
	
    AutonomousCommandSequence autonomousCommand;
    ElevatorCalibrate elevatorCalibrate;
    SmartDashboard smartDashboard;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        try {
        	File file = new File("/u/HelloWorld.txt");
        	FileOutputStream fos = new FileOutputStream(file);
        	OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter fw = new BufferedWriter(osw);
			
			fw.write(" PROGRAMMERS RULE and also Dimitri Mendelev");
			fw.close();
        } catch (IOException e) {
			e.printStackTrace();
		} finally{
		
		}
		oi = new OI();
        // instantiate the command used for the autonomous period
        autonomousCommand = new  AutonomousCommandSequence();
        elevatorCalibrate = new ElevatorCalibrate();
        
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        forwardDrive.InitGyro();
    	if (autonomousCommand != null) autonomousCommand.start();
//        if (elevatorCalibrate !=null) elevatorCalibrate.start();
      
        
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        if (elevatorCalibrate !=null) elevatorCalibrate.start();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        System.out.println(elevatorCalibrate.isCalibrated());
        
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
