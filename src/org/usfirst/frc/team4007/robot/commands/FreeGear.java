package org.usfirst.frc.team4007.robot.commands;

import org.usfirst.frc.team4007.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FreeGear extends Command {
	private boolean commande_end_delay = false;
    public FreeGear() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gear);
    }

    public FreeGear(Boolean commande_end_delay) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gear);
    	this.commande_end_delay = commande_end_delay;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("***Debut ouverture de la Gate");
    	Robot.gear.demarreOuvre();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.gear.compterTour();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//System.out.println("timestamp is finish " + Robot.gear.counter.readRisingTimestamp());

    	return Robot.gear.limitSwitch.get(); //!Robot.oi.btnGearRelease.get() /*|| (Robot.gear.compteurPulse >= Robot.gear.max)*/;
    		
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gear.arret();
    	System.out.println("***Fin Ouverture de la Gate");
    	if(this.commande_end_delay) // MODE AUTONOME SEULEMENT POUR LAISSER LE TEMPS A LA COMMANDE DE BIEN TERMINER
    	{
    		try {
	 			Thread.sleep(2000);
	 		} catch (InterruptedException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
