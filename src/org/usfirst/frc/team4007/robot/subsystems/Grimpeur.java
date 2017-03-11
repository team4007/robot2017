package org.usfirst.frc.team4007.robot.subsystems;

import org.usfirst.frc.team4007.robot.RobotMap;
import org.usfirst.frc.team4007.robot.commands.Grimpe;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Grimpeur extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public CANTalon moteur;
	
	public Grimpeur(){
		super();
		moteur = new CANTalon(RobotMap.canTalonWinch);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new Grimpe());
    	
    }
    
    public void demarre(){
    	System.out.println("GRIMPEUR DEMARRE");
    	moteur.set(-1.0);
    }
    
    public void arret(){
    	moteur.set(0.0);
    }
}

