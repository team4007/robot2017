package org.usfirst.frc.team4007.robot.commands;

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
	
	
	 public  AutonomousCommands() {
	       
	        switch0 = new DigitalInput(0);
	        switch1 = new DigitalInput(1);
	        switch2 = new DigitalInput(2);
	    	
	    	int switch0Value = switch0.get() ? 1 : 0;
	    	int switch1Value = switch1.get() ? 1 : 0;
	    	int switch2Value = switch2.get() ? 1 : 0;
	    	
	    	int config = switch0Value + (switch1Value << 1) + (switch2Value << 2);
	        
	    	switch(config){
	    	case 0:
	    		System.out.println("Start #0");
	    		System.out.println("Autonome : Reste sur place");
		    	addSequential(new RunForestRun());;
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
	    	}      
	    }	
	
/*
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	Forward step1 = new Forward();
    	step1.start();  	  	
    } */  

}

