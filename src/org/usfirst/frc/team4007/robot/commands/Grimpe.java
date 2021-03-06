package org.usfirst.frc.team4007.robot.commands;

import org.usfirst.frc.team4007.robot.OI;
import org.usfirst.frc.team4007.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Grimpe extends Command {
	
    public Grimpe() {
    	requires(Robot.grimpeur);
    	System.out.println("Grimpe constructeur");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Grimpe inits");
    	Robot.grimpeur.demarre();

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Robot.oi.btnGrimpeur.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	//System.out.println("GRIMPE ARRET");
    	Robot.grimpeur.arret();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
