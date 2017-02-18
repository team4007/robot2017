package org.usfirst.frc.team4007.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

import org.usfirst.frc.team4007.robot.RobotMap;
import org.usfirst.frc.team4007.robot.commands.DriveWithJoystick;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public CANTalon roueGauche;
	public CANTalon roueDroite;
	RobotDrive drive;
	
	
	
	public DriveTrain() {
		super();
		roueGauche = new CANTalon(RobotMap.canTalonRouesGauches);
		roueDroite = new CANTalon(RobotMap.canTalonRouesDroites);

		drive = new RobotDrive(roueGauche, roueDroite);
		
		roueGauche.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		roueGauche.configEncoderCodesPerRev(2048);

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
    
    public void drive(Joystick leftStick, Joystick rightStick) {
    	drive.tankDrive(leftStick, rightStick);
    	
    	if (RobotMap.debugMode) {
    		System.out.println("Encodeur gauche" + roueGauche.getEncPosition());
    	}
    	    	
    }
}


