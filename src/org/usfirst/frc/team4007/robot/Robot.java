
package org.usfirst.frc.team4007.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team4007.robot.commands.AutonomousCommands;
import org.usfirst.frc.team4007.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4007.robot.subsystems.Gear;
import org.usfirst.frc.team4007.robot.subsystems.Grimpeur;
import org.usfirst.frc.team4007.robot.subsystems.LanceBalle;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	/* BEGIN TOUS LES SOUS SYSTEMS DOIVENT ETRE DECLARER ICI */
	public static DriveTrain driveTrain = new DriveTrain();
	public static Gear gear = new Gear();
	public static Grimpeur grimpeur = new Grimpeur();
	public static LanceBalle lanceBalle = new LanceBalle();
	/* END TOUS LES SOUS SYSTEMS DOIVENT ETRE DECLARER ICI */
	
	
	/* CECI DOIT ETRE INSTANCIER EN DERNIER */
	public static OI oi = new OI();
	
	
	AutonomousCommands autonomous;

	//CameraServer camServer = CameraServer.getInstance();
    
    //UsbCamera cam = CameraServer.getInstance().startAutomaticCapture("cam0", 0);
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
	

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
		autonomous = new AutonomousCommands();
		autonomous.start();
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	if (autonomous != null) autonomous.cancel();
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	Scheduler.getInstance().run();
        driveTrain.drive(Robot.oi.joystickGauche, Robot.oi.joystickDroit);
        //System.out.println(Robot.oi.joystickDroit.getRawButton(RobotMap.btnGrimpeur));
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
