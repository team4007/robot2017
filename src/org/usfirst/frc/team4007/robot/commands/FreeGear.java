package org.usfirst.frc.team4007.robot.commands;

import org.usfirst.frc.team4007.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FreeGear extends Command {
boolean stop = false;
    public FreeGear() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gear);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//System.out.println("timestamp init: " + Robot.gear.counter.readRisingTimestamp());
    	Robot.gear.demarreOuvre();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	try{
    		//System.out.println("timestamp ex " + Robot.gear.counter.readRisingTimestamp());
    	stop = Robot.gear.analogtrigger.getInWindow();
    	}catch(Exception e){
    		
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//System.out.println("timestamp is finish " + Robot.gear.counter.readRisingTimestamp());

    	return !Robot.oi.btnGearRelease.get();
    		
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gear.arret();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
