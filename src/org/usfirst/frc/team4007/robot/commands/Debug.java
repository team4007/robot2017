package org.usfirst.frc.team4007.robot.commands;

import org.usfirst.frc.team4007.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Debug extends Command {

    public Debug() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gear);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try {
        	//System.out.println("Rising: "+Robot.gear.analogtrigger.getTriggerState());
        	//System.out.println("Falling: "+Robot.gear.counter.readFallingTimestamp());
        	 }catch(Exception e){
        		 System.out.println(e);
        	 }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	System.out.println("DEBUG BALLE");
    	
    	System.out.println("DEBUG AUTONOME");
    	System.out.println("switchs switch0: " + Robot.autonomousCommands.switch0.get());
    	System.out.println("switchs switch1: " + Robot.autonomousCommands.switch1.get());
    	System.out.println("switchs config: " + Robot.autonomousCommands.config());
    	
    	System.out.println("DEBUG GEAR");
    	System.out.println("Limit Switch GEAR:" + Robot.gear.limitSwitch.get() );
    	System.out.println("Bosch GEAR value:" + Robot.gear.compteur.get() );
    	System.out.println("DEBUG CAMERA");
    	System.out.println("Servo camera:" + Robot.cameraSubSystem.moteur.getAngle() );
        return !Robot.oi.btnDebug.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
