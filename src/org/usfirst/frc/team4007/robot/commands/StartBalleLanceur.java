package org.usfirst.frc.team4007.robot.commands;

import org.usfirst.frc.team4007.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StartBalleLanceur extends Command {

    public StartBalleLanceur() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lanceBalle);
    	System.out.println("INIT ball wheel");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Start ball wheel");
    	Robot.lanceBalle.startBalleWheel();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Robot.oi.btnLanceurDroit.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Fin ball wheel");
    	Robot.lanceBalle.stopBalleWheel();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
