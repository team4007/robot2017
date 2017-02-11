package org.usfirst.frc.team4007.robot.commands;

import org.usfirst.frc.team4007.robot.Robot;
import org.usfirst.frc.team4007.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Forward extends Command {

    public Forward() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Autonome : Avance Droite");
    	this.setTimeout(5.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        	Robot.driveTrain.roueDroite.enable();
        	Robot.driveTrain.roueGauche.enable();    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
