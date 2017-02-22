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
		setUP();
	 }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setUP(){
    	moteur.set(0);
    }
    
    public void position_up(){
    	if(moteur.getAngle() < 90)
    		moteur.setAngle(moteur.getAngle() + 45);
	
	 }
    public void position_down(){
    	if(moteur.getAngle() > 0)
    		moteur.setAngle(moteur.getAngle() - 45);   	
	 }
}

