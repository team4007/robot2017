
package org.usfirst.frc.team4007.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team4007.robot.commands.AutonomousOne;
import org.usfirst.frc.team4007.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4007.robot.subsystems.Gear;

import com.ctre.CANTalon;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static final DriveTrain driveTrain = new DriveTrain();
	public static final Gear gear = new Gear();
	public static OI oi;

    DigitalInput switch0;
    DigitalInput switch1;
    DigitalInput switch2;
	//CameraServer camServer = CameraServer.getInstance();
    
    //UsbCamera cam = CameraServer.getInstance().startAutomaticCapture("cam0", 0);
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
       
       
        switch0 = new DigitalInput(0);
        switch1 = new DigitalInput(1);
        switch2 = new DigitalInput(2);
        /*cam.setFPS(30);
        cam.setResolution(320, 240);*/      
        
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
    	
    	int switch0Value = switch0.get() ? 1 : 0;
    	int switch1Value = switch1.get() ? 1 : 0;
    	int switch2Value = switch2.get() ? 1 : 0;
    	
    	int config = switch0Value + (switch1Value << 1) + (switch2Value << 2);
    	
		AutonomousOne start1 = new AutonomousOne();
		
		
    	switch(config){
    	case 0:
    		System.out.println("Start #0");
    		System.out.println("Autonome : Reste sur place");
    		break;
    	case 1:
    		System.out.println("Start #1");
    		start1.start();
    		break;
    	case 2:
    		System.out.println("Start #2");    		
    		break;
    	case 3:
    		System.out.println("Start #3");
    		break;
    	case 4:
    		System.out.println("Start #4");
    		break;
    	case 5:
    		System.out.println("Start #5");
    		break;
    	case 6:
    		System.out.println("Start #6");  		
    		break;
    	case 7:
    		System.out.println("Start #7");
    		break;
    	}
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
