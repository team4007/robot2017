package org.usfirst.frc.team4007.robot.commands;

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

	DriveTrain dt;
	
    public Forward() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	
    	dt = Robot.driveTrain;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//System.out.println("Autonome : Avance Droite");
    	this.setTimeout(5.0);
    	
    	dt.enableMotors();
    	dt.roueDroite.enableBrakeMode(false);
 	
   	
    	reset();

    	// Mode follower sert a faire suivre 2 talons egalement
		// Regle la roue gauche comme esclave de la droite
		dt.roueGauche.changeControlMode(TalonControlMode.Follower);
		dt.roueGauche.set(RobotMap.canTalonRouesDroites);
    	
    }
    
    double objectif = 5;//4.37;
    double vitesse = 0.0;

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	
    	if (objectif - Math.abs(dt.roueDroite.getPosition()) < 1.25) {
    		vitesse -= .05;
    		
    		if (vitesse < 0.25) {
    			vitesse = 0.25;
    		}
    	} else {
    		vitesse += 0.01;
    	}
    	
    	
    	dt.roueDroite.set(vitesse);
    	dt.roueGauche.set(vitesse);
    	
    	if (RobotMap.debugMode)
    		System.out.println(dt.roueDroite.getPosition());
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return -dt.roueDroite.getPosition()>= objectif;
    	//return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	//dt.roueDroite.enableBrakeMode(true);
    	dt.disableMotors();
    	reset();
    	
    	// Ne pas laisser le talon en follower...doit etre ramener normal en sortant 
    	//Robot.driveTrain.roueGauche.changeControlMode(TalonControlMode.PercentVbus);

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
    
    void reset () {
    	dt.roueDroite.setEncPosition(0);
    	dt.roueDroite.setPosition(0);
    	this.vitesse = 0;
    }
}
