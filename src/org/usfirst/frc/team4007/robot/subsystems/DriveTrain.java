package org.usfirst.frc.team4007.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.RobotDrive;

import org.usfirst.frc.team4007.robot.RobotMap;
import org.usfirst.frc.team4007.robot.commands.DriveWithJoystick;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public CANTalon roueGauche;
	public CANTalon roueDroite;
	public RobotDrive drive;
	
	/**
	 * Distance entre le centre la roue du centre
	 */
	private double distanceCentreRoues = 25; // cm
	private double diametreRoues = 15.24;
	private double positionInDistance = 47.87787204070844895417068516118;
	private double circonferenceRoues = 47.87787;
	
	public DriveTrain() {
		super();
		roueGauche = new CANTalon(RobotMap.canTalonRouesGauches);
		roueDroite = new CANTalon(RobotMap.canTalonRouesDroites);

		drive = new RobotDrive(roueGauche, roueDroite);
		
		roueDroite.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		roueDroite.configEncoderCodesPerRev(RobotMap.encodeurRoueClics); // Config du AMT103 0b0000
		roueDroite.enableBrakeMode(false);
		roueDroite.reverseSensor(true);
		roueDroite.enableBrakeMode(true);
		
		roueDroite.configNominalOutputVoltage(0f, 0f);
		roueDroite.configPeakOutputVoltage(12.0f, -12.0f);
//		roueDroite.setAllowableClosedLoopErr(0);
//		roueDroite.setProfile(0);
//		roueDroite.setF(0.0);
//		roueDroite.setP(0.1);
//		roueDroite.setI(0.0); 
//		roueDroite.setD(0.0); 
		
		roueGauche.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		roueGauche.configEncoderCodesPerRev(RobotMap.encodeurRoueClics);
		roueGauche.enableBrakeMode(true);
	
		
		//roueGauche.reverseOutput(true);
	}
	
	public CANTalon getRoue(int numero){
		if (numero == 0){
			return roueGauche;
		}
		else {
			return roueDroite;		
		}
	
	}
	

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveWithJoystick());

    }
    
    public void stop() {
    	roueGauche.disableControl();
    	roueDroite.disableControl();
    }
    
    public void enableMotors() {
    	roueGauche.enable();
    	roueDroite.enable();
    }
    
    public void disableMotors() {
    	roueGauche.disable();
    	roueDroite.disable();
    }
    
    public void drive(Joystick leftStick, Joystick rightStick) {
    	//drive.tankDrive(leftStick, rightStick);
    	drive.arcadeDrive(rightStick);
    	//drive.arcadeDrive(leftStick, leftStick.getAxisChannel(AxisType.kY), rightStick, rightStick.getAxisChannel(AxisType.kX));
 
//    	//drive.arcadeDrive(rightStick.getY(), leftStick.getX());
//    	if (RobotMap.debugMode) {
//    		System.out.println("Encodeur gauche" + roueGauche.getEncPosition());
//    	}
    	    	
    }
    
    public void setControlModeToSpeed(){
    	
  //  	roueDroite.changeControlMode(TalonControlMode.Speed);
    //	roueGauche.changeControlMode(TalonControlMode.Follower);
    	//roueGauche.set(RobotMap.canTalonRouesDroites);
    	
    }
    


	public double getDistanceCentreRoues() {
		return distanceCentreRoues;
	}

	public void setDistanceCentreRoues(double distanceCentreRoues) {
		this.distanceCentreRoues = distanceCentreRoues;
	}

	public double getDiametreRoues() {
		return diametreRoues;
	}

	public void setDiametreRoues(double diametreRoues) {
		this.diametreRoues = diametreRoues;
		this.positionInDistance = diametreRoues * Math.PI;
		circonferenceRoues = diametreRoues * Math.PI;
	}
	
	public double getPositionFromDistanceToTravel(double distance){
		return distance / circonferenceRoues;
	}
	
	public void reset(){
		roueGauche.setEncPosition(0);
		roueDroite.setEncPosition(0);
		
		roueGauche.setPosition(0);
		roueDroite.setPosition(0);
	}
	
	public void stopAll() {
		roueGauche.set(0);
		roueDroite.set(0);
	
		
	}
}


