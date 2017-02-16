package org.usfirst.frc.team4007.robot.subsystems;

import org.usfirst.frc.team4007.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Camera extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Servo moteur;
	
	public Camera(){
		moteur = new Servo(RobotMap.PWMcamera);
	 }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void position_up(){
	moteur.setAngle(90);
	 }
    public void position_mid(){
    	moteur.setAngle(45);    	
	 }
    public void position_down(){
    	moteur.setAngle(0);    	
	 }
}

