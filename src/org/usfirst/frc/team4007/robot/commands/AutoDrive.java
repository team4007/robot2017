package org.usfirst.frc.team4007.robot.commands;

import org.usfirst.frc.team4007.robot.Robot;
import org.usfirst.frc.team4007.robot.subsystems.DriveTrain;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Classe permettant de lancer des instructions de deplacements simples et automatiques au
 * robot
 */
public class AutoDrive extends Command {

	DriveTrain dt;
	
	double leftGoal;
	double rightGoal;
	
	/**
	 * Taux d'acceleration maximum en voltage par seconde
	 */
	double rampRate = 6.0;
	
    public AutoDrive() {
        // Use requires() here to declare subsystem dependencies
    	initProperties();
    	
    }

    /**
     * Constructeur permettant de faire avancer le robot egalement
     * @param position
     */
    public AutoDrive(double position) {
    	initProperties();
    	
    	leftGoal = rightGoal = position;
	}
    
    /**
     * Constructeur permettant de faire pivoter le robot en indiquant des positions
     * a chaque roue du robot
     * @param left Position de la roue gauche
     * @param right Position de la roue droite
     */
    public AutoDrive(double left, double right) {
    	initProperties();
    	
    	leftGoal = left;
    	rightGoal = right;
    }

    private void initProperties() {
    	requires (Robot.driveTrain);
    	dt = Robot.driveTrain;
    	
//    	dt.roueGauche.setVoltageRampRate(rampRate);
//    	dt.roueDroite.setVoltageRampRate(rampRate);
//    	
//    	dt.roueDroite.changeControlMode(TalonControlMode.PercentVbus);
//
//    	dt.roueGauche.changeControlMode(TalonControlMode.Follower);
//    	dt.roueGauche.set(dt.roueDroite.getDeviceID());
    	
    	dt.reset();
    	
    }
    
	// Called just before this Command runs the first time
    protected void initialize() {
    	dt.roueDroite.changeControlMode(TalonControlMode.Position);
    	dt.roueDroite.set(rightGoal);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	  	
    }
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double currentError = dt.roueDroite.getError();
    	
    	System.out.println("Erreur : " + currentError);
        //return dt.roueDroite.getPosition() > rightGoal;
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    /**
     * Permet au robot de pivoter a l'angle indique
     * @param angle Angle en radian. Si angle < 0 anti-horaire
     */
    public void rotate (double angle) {
    	double distanceParcourir = dt.getDistanceCentreRoues() * angle;
    	
    	dt.roueDroite.changeControlMode(TalonControlMode.Position);
    	dt.roueGauche.changeControlMode(TalonControlMode.Position);
    	
    	
    	
    }
}
