
package org.usfirst.frc.team3668.robot;
import org.usfirst.frc.team3668.robot.commands.AutonomousCommandSequence;
import org.usfirst.frc.team3668.robot.commands.ElevatorCalibrate;
import org.usfirst.frc.team3668.robot.subsystems.Elevator;
import org.usfirst.frc.team3668.robot.subsystems.ForwardDrive;
import org.usfirst.frc.team3668.robot.subsystems.GuideArm;
import org.usfirst.frc.team3668.robot.subsystems.SideDrive;
import org.usfirst.frc.team3668.robot.subsystems.ToteSupport;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ForwardDrive forwardDrive = new ForwardDrive();
	public static final SideDrive sideDrive = new SideDrive();
	public static final Elevator elevator = new Elevator();
	public static final GuideArm guideArm = new GuideArm();
	public static final ToteSupport toteSupport = new ToteSupport();
	public static OI oi;
	
	public static Joystick driveStick = new Joystick(IOLabels.DriveStick);
	public static Joystick lifterStick = new Joystick(IOLabels.LifterStick);
	public static AnalogInput autonomousSwitch = new AnalogInput(IOLabels.brendanIsPickyAutonomousSwitchPort);
	public static final PowerDistributionPanel PDP = new PowerDistributionPanel();
	CameraServer camera;
    AutonomousCommandSequence autonomousCommand;
    ElevatorCalibrate elevatorCalibrate;
//    SmartDashboard smartDashboard;
	int counter;

	public Robot(){
		
//		camera = CameraServer.getInstance();
//		camera.setQuality(50);
//		camera.startAutomaticCapture("cam1");
	}
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
//    	SmartDashboard.putNumber("Speed Scaling Factor: ", 1);
//    	SmartDashboard.putNumber("Side Speed Scaling Factor: ", 1);
//    	SmartDashboard.putNumber("Elevator Speed Scaling Factor: ", 1);
    	
		oi = new OI();
        // instantiate the command used for the autonomous period
        elevatorCalibrate = new ElevatorCalibrate();
        Logger.InitFile();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    	forwardDrive.InitGyro();
		counter = 0;
		Logger.Log("AutonomousInit");

        autonomousCommand = new  AutonomousCommandSequence();
    	if (autonomousCommand != null) autonomousCommand.start();
        
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	counter++;
    	if((counter % 25 == 0)){
    		Logger.Log("Autonomous");    		
    	}
    	
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		counter = 0;
		Logger.Log("TeleopInit");
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
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
//    	String joystickReadout = Double.toString(driveStick.getAxis(Joystick.AxisType.kY));
    	counter++;
    	if((counter % 25 == 0)){
    		Logger.Log("Periodic");    		
    	}
    	
        Scheduler.getInstance().run();
//        System.out.println(elevatorCalibrate.is           Calibrated());
        
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
