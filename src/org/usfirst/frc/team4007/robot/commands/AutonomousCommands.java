package org.usfirst.frc.team4007.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AutonomousCommands extends CommandGroup {
	
	 public  AutonomousCommands() {
	    	addSequential(new Forward());;
	    }	
	
/*
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	Forward step1 = new Forward();
    	step1.start();  	  	
    } */  

}

