package org.usfirst.frc.team4007.robot.commands;

import org.usfirst.frc.team4007.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AutonomousCommands extends CommandGroup {

	DigitalInput switch0;
	DigitalInput switch1;
	DigitalInput switch2;

	public int config(){
		if(switch0.get()) 
			return 1;
		
		if(switch1.get()) 
			return 2;
		
		if(switch2.get()) 
			return 3;
		
		return 32 ;
	}

	public  AutonomousCommands() {
		switch0 = new DigitalInput(RobotMap.AutoLimit0);
		switch1 = new DigitalInput(RobotMap.AutoLimit1);
		switch2 = new DigitalInput(RobotMap.AutoLimit2);
		
		addSequential(new Forward());		

		
		//addSequential(new MakeTurn(MakeTurn.TurnSide.LEFT));
		
		
		//addSequential(new Forward());
		
		//addSequential(new MakeTurn(MakeTurn.TurnSide.RIGHT));
		
		switch(config()){
		case 1:
		
		case 2:
			
		case 3:
			
		default:
			
		}
		
		
		/*
		// SASSURER QUE TOUTES LES SWITCH AUTONOMES SONT PLUGGER
		switch0 = new DigitalInput(0);

		switch1 = new DigitalInput(1);

		//switch2 = new DigitalInput(2);

		//int switch2Value = switch2.get() ? 1 : 0;


		switch(config()){
		case 0:
			System.out.println("Start #0");
			System.out.println("Autonome : Reste sur place");
			//addSequential(new RunForestRun());;
			break;
		case 1:
			System.out.println("Start #1");
			addSequential(new Forward());;
			break;
		case 2:
			System.out.println("Start #2");    		
			addSequential(new Forward());;
			break;
		case 3:
			System.out.println("Start #3");
			addSequential(new Forward());;
		}   */   
	}	

	/*
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	Forward step1 = new Forward();
    	step1.start();  
    		  	
    }  */

}

