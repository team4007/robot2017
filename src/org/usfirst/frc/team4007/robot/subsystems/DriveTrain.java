package org.usfirst.frc.team4007.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.GenericHID;
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
	
	
	
	public DriveTrain() {
		super();
		roueGauche = new CANTalon(RobotMap.canTalonRouesGauches);
		roueDroite = new CANTalon(RobotMap.canTalonRouesDroites);

		drive = new RobotDrive(roueGauche, roueDroite);
		
		roueDroite.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		roueDroite.configEncoderCodesPerRev(RobotMap.encodeurRoueClics); // Config du AMT103 0b0000
		roueDroite.enableBrakeMode(false);
		
		roueGauche.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		roueGauche.configEncoderCodesPerRev(RobotMap.encodeurRoueClics);
		roueGauche.enableBrakeMode(false);
		
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
    	//drive.arcadeDrive(rightStick);
    	drive.arcadeDrive(leftStick, leftStick.getAxisChannel(AxisType.kY), rightStick, rightStick.getAxisChannel(AxisType.kX));
 
    	//drive.arcadeDrive(rightStick.getY(), leftStick.getX());
    	if (RobotMap.debugMode) {
    		System.out.println("Encodeur gauche" + roueGauche.getEncPosition());
    	}
    	    	
    }
    
    public void setControlModeToSpeed(){
    	
    	roueDroite.changeControlMode(TalonControlMode.Speed);
    	roueGauche.changeControlMode(TalonControlMode.Follower);
    	roueGauche.set(RobotMap.canTalonRouesDroites);
    	
    }
}


