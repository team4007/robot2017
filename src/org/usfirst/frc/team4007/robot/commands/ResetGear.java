package org.usfirst.frc.team4007.robot.commands;

import org.usfirst.frc.team4007.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ResetGear extends Command {

    public ResetGear() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gear);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Amorce fermer");
    	//Robot.gear.updateCounter();
    	Robot.gear.demarreFerme();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.gear.compterTour();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("Fin fermer" + Robot.gear.compteurPulse );
    		
        return Robot.gear.compteurPulse >= 0;
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
