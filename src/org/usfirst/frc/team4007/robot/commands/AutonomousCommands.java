package org.usfirst.frc.team4007.robot.commands;

import org.usfirst.frc.team4007.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AutonomousCommands extends CommandGroup {

	DigitalInput switch0 = new DigitalInput(RobotMap.AutoLimit0);
	DigitalInput switch1 = new DigitalInput(RobotMap.AutoLimit1);
	DigitalInput switch2 = new DigitalInput(RobotMap.AutoLimit2);

	public int config(){
		if(switch0.get()) 
			return 1;
		
		if(switch1.get()) 
			return 2;
		
		if(switch2.get()) 
			return 3;
		
		return 2 ;
	}

	/*
	 * MEMO DES CONSTANTES
	 * +-1.7 = 180 degrer
	 * */
	public  AutonomousCommands() {
		super();
		// Positionnement du robot sur l'objectif "GEAR"
		
		switch(config()){
		case 1:
			addSequential (new AutoDrive (4f,4f));
	    	addSequential (new AutoDrive (-0.35f, 0.35f));
	    	addSequential (new AutoDrive (2.5f,2.5f));
	    	break;
		case 2:
			addSequential (new AutoDrive (5f,5f));
			break;
		case 3:
			addSequential (new AutoDrive (4f,4f));
	    	addSequential (new AutoDrive (0.35f, -0.35f));
	    	addSequential (new AutoDrive (2.5f,2.5f));
	    	break;
		default:
			break;
		}
		
		// DEPOT ET LEGER RETRAIT DU ROBOT
		
		addSequential(new FreeGear(true));
		
		addSequential(new AutoDrive (-2f, -2f));
		
		addSequential(new ResetGear());
		
	}	

	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//Forward step1 = new Forward();
    	//step1.start();  
    }  

}

