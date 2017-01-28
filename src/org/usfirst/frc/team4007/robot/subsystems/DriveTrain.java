package org.usfirst.frc.team4007.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

import org.usfirst.frc.team4007.robot.RobotMap;
import org.usfirst.frc.team4007.robot.commands.DriveWithJoystick;

import com.ctre.CANTalon;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public CANTalon rouesGauches;
	public CANTalon rouesDroites;
	RobotDrive drive;
	
	private double drivingSpeed = .25;
	
	
	public DriveTrain() {
		super();
		rouesGauches = new CANTalon(RobotMap.canTalonRouesGauches);
		rouesDroites = new CANTalon(RobotMap.canTalonRouesDroites);

		drive = new RobotDrive(rouesGauches, rouesDroites);
		

	}
	
	

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveWithJoystick());

    }
    
    public void stop() {
    	rouesGauches.stopMotor();
    	rouesDroites.stopMotor();
    }
    
    public void drive(Joystick joystick) {
    	drive.arcadeDrive(-joystick.getY(), -joystick.getX());
    	    	
    }
}


