package org.usfirst.frc.team4007.robot.commands;

import org.usfirst.frc.team4007.robot.Conducteur;
import org.usfirst.frc.team4007.robot.Robot;
import org.usfirst.frc.team4007.robot.RobotMap;
import org.usfirst.frc.team4007.robot.subsystems.DriveTrain;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Forward extends Command {
	
	private Conducteur conducteur;
	
    public Forward() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//System.out.println("Autonome : Avance Droite");
    	conducteur = new Conducteur(Robot.driveTrain, 5); //4.37;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.out.println(Robot.driveTrain.roueDroite.getControlMode());
    	conducteur.execute();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return conducteur.isFinish();
    	//return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	conducteur.end();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
