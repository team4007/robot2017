
package org.usfirst.frc.team4007.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.vision.VisionThread;

import org.usfirst.frc.team4007.robot.commands.AutonomousCommands;
import org.usfirst.frc.team4007.robot.subsystems.Camera;
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
	public static Camera cameraSubSystem = new Camera();

	
	public static AutonomousCommands autonomousCommands;
	
	/* END TOUS LES SOUS SYSTEMS DOIVENT ETRE DECLARER ICI */
	
	

	public static OI oi;
	
	
	
	// Interessant : Selecteur de configuration logiciel
	// Source : https://github.com/iron-claw-972/FRC2016/blob/master/src/org/usfirst/frc/team972/robot/Autonomous.java
    
    // Source : https://www.chiefdelphi.com/forums/showthread.php?t=143688&page=2&highlight=vision
    //MjpegServer server = new MjpegServer("Output to dashboard", 5800);
	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;
	
	private VisionThread visionThread;
	private double centerX = 0.0;
	
	private final Object imgLock = new Object();
	
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	 oi = new OI();
    	    	 
    	 //initCamera();
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
    	autonomousCommands = new AutonomousCommands();
		autonomousCommands.start();
		
		System.out.println(Thread.currentThread().getStackTrace()[0].getMethodName());
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
    }

    public void teleopInit() {
    	if (autonomousCommands != null) { 
    		autonomousCommands.cancel();
    		autonomousCommands = null;
    	}
    	
    	driveTrain.enableMotors();
    	//driveTrain.setControlModeToSpeed();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	Scheduler.getInstance().run();
        //driveTrain.drive(Robot.oi.joystickGauche, Robot.oi.joystickDroit);
        //System.out.println(Robot.oi.joystickDroit.getRawButton(RobotMap.btnGrimpeur));
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    // Source : https://www.chiefdelphi.com/forums/showpost.php?p=1653356&postcount=17
    private void initCamera() {
    	UsbCamera cameraFront = CameraServer.getInstance().startAutomaticCapture(0);
    	
    	 // src : https://www.chiefdelphi.com/forums/showpost.php?p=1639830&postcount=12
//    	Thread t = new Thread(() -> {
//    		
//    		boolean allowCam1 = false;
//    		
//    		UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(0);
//            camera1.setResolution(320, 240);
//            camera1.setFPS(15);
//            UsbCamera camera2 = CameraServer.getInstance().startAutomaticCapture(1);
//            camera2.setResolution(320, 240);
//            camera2.setFPS(15);
//            
//            CvSink cvSink1 = CameraServer.getInstance().getVideo(camera1);
//            CvSink cvSink2 = CameraServer.getInstance().getVideo(camera2);
//            
//            CvSource outputStream = CameraServer.getInstance().putVideo("cam0", 320, 240);
//            
//            Mat image = new Mat();
//            
//            while(!Thread.interrupted()) {
//            	
//            	if(oi.joystickGauche.getRawButton(9)) {
//            		allowCam1 = !allowCam1;
//            	}
//            	
//                if(allowCam1){
//                  cvSink2.setEnabled(false);
//                  cvSink1.setEnabled(true);
//                  cvSink1.grabFrame(image);
//                } else{
//                  cvSink1.setEnabled(false);
//                  cvSink2.setEnabled(true);
//                  cvSink2.grabFrame(image);     
//                }
//                
//                outputStream.putFrame(image);
//            }
//            
//        });
//        t.start();
//	    visionThread = new VisionThread(cameraAvant, new GripPipeline(), pipeline -> {
//	    	
//	        if (!pipeline.convexHullsOutput().isEmpty()) {
//	        	ArrayList<MatOfPoint> contours = pipeline.convexHullsOutput();
//	        	
//	        	MatOfPoint points = new MatOfPoint();
//	        	
//	        	for (MatOfPoint pts : contours) {
//	        		points.push_back(pts);
//	        	}
//	        	
//	        	
//	            Rect r = Imgproc.boundingRect(points);
//	            synchronized (imgLock) {
//	                centerX = r.x + (r.width / 2);
//	            }
//	            
//	            points.release();
//	        }
//	    });
//	    
//	    visionThread.start();
//        Thread t = new Thread(() -> {
//
//            
//            UsbCamera cam = CameraServer.getInstance().startAutomaticCapture("cam0", 0);

//            
//            CvSink sink1 = CameraServer.getInstance().getVideo(cam);
//            sink1.setEnabled(true);
//            
//            GripPipeline grip = new GripPipeline();
//            
//            CvSource outputStream = CameraServer.getInstance().putVideo("Video", 320, 240);
//            
//            Mat source = new Mat();
//            Mat output = new Mat();
//            
//            while (!Thread.interrupted()) {
//            	
//            	sink1.grabFrame(source);
//            	/* Test */
//            	//grip.process(source, output);
//            	Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
//            	
//            	outputStream.putFrame(output);
//            }
//        });
//        
//        t.start();
    }
}
