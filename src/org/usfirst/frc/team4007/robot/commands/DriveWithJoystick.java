package org.usfirst.frc.team4007.robot.commands;

import org.usfirst.frc.team4007.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithJoystick extends Command {


    public DriveWithJoystick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//System.out.println("DRIVE DESACTIVE DANS DRIVEWITHJOYSTICK.JAVA");
    	// NE PAS OUBLIER DE RAMENER LE SENS DES MOTEUR DANS LE SENS DU MONDE APRES LE MODE AUTONOME!!!
    	Robot.driveTrain.roueDroite.setInverted(false);
    	Robot.driveTrain.roueGauche.setInverted(false);
    	Robot.driveTrain.roueDroite.enableBrakeMode(true);
    	Robot.driveTrain.roueGauche.enableBrakeMode(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.driveTrain.drive(Robot.oi.joystickGauche, Robot.oi.joystickDroit);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
