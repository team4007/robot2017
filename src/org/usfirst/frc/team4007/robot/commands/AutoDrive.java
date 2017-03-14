package org.usfirst.frc.team4007.robot.commands;

import org.usfirst.frc.team4007.robot.Robot;
import org.usfirst.frc.team4007.robot.RobotMap;
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
	double tolerance = 1.5;
	double vitesseDroite = 0.0;
	double vitesseGauche = 0.0;
	double directionDroite = 1.0;
	double directionGauche = 1.0;
	
	double vitesseMinimum = 0.2;
	
	int hasStarted = 0;
	
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
    	
    	
    	directionDroite = directionGauche = position < 0 ? -1.0 : 1.0;
    	
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
    	
    	directionDroite = right < 0 ? -1.0 : 1.0;
    	directionGauche = left < 0 ? -1.0 : 1.0;
    }

    private void initProperties() {
    	requires (Robot.driveTrain);
    	dt = Robot.driveTrain;
    	
    	dt.roueDroite.setVoltageRampRate(rampRate);
//    	dt.roueDroite.setPID(1.5, .05, .1);
//    	dt.roueDroite.changeControlMode(TalonControlMode.Speed);
//    	dt.roueDroite.changeControlMode(TalonControlMode.PercentVbus);
    	dt.roueDroite.enableBrakeMode(true);
    	
    	dt.roueGauche.setVoltageRampRate(rampRate);
//    	dt.roueGauche.changeControlMode(TalonControlMode.Speed);
    	dt.roueGauche.setInverted(true);
//    	dt.roueGauche.setPID(1.3, .015, 0);
    	dt.reset();
    	
    }
    
	// Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("***** START AUTODRIVE *****");
    }

    double erreurDroite = 0;
    double erreurGauche = 0;
    
    double erreurDroitePrec = 0;
    double erreurGauchePrec = 0;
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	// Calcul de l'erreur entre l'objectif et la position actuelle
    	erreurDroite = Math.abs(rightGoal -  dt.roueDroite.getPosition());
    	erreurGauche = Math.abs(leftGoal -  dt.roueGauche.getPosition());
    	
    	
    	
//    	if (hasStarted > 10) {
//	    	if (erreurDroite > erreurDroitePrec) {
//	    		directionDroite = -directionDroite;
//	    	}
//	    	
//	    	if (erreurGauche > erreurGauchePrec) {
//	    		directionGauche = -directionGauche;
//	    	}
//	    	
//    	}
    	
    	// Si on s'approche de l'objectif on ralentit
    	if (erreurDroite < tolerance ) {
    		vitesseDroite = vitesseDroite - (0.1 * directionDroite);
    		
    		if (Math.abs(vitesseDroite) <= vitesseMinimum) {
    			vitesseDroite = vitesseMinimum * directionDroite;
    		}
    		
    	} else {
    		vitesseDroite = vitesseDroite + (0.005 * directionDroite);
    		
    		if (Math.abs(vitesseDroite) >= .3) {
    			vitesseDroite = .3 * directionDroite;
    		}

    	}
    	

    	
    	if (erreurGauche < tolerance ) {
    		vitesseGauche = vitesseGauche - (0.1 * directionGauche);
    		
    		if (Math.abs(vitesseGauche) <= vitesseMinimum) {
    			vitesseGauche = vitesseMinimum * directionGauche;
    		}
    		
    	} else {
    		vitesseGauche = vitesseGauche + (0.005 * directionGauche);
    		
    		if (Math.abs(vitesseGauche) >= .3) {
    			vitesseGauche = .3 * directionGauche;
    		}

    	}
    	
    	dt.roueDroite.set(vitesseDroite);
    	dt.roueGauche.set(vitesseGauche);
    	
    	erreurDroitePrec = erreurDroite;
    	erreurGauchePrec = erreurGauche;
    	
    	hasStarted++;
    	
    	if (RobotMap.debugMode) {
    		debug();
    	}
    }
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	boolean result = erreurDroite < .05;
    	
    	if (result) {
    		System.out.println("End autodrive");
    		debug();
    		
    	}
    	
    	//System.out.println("Erreur : " + currentError);
        //return dt.roueDroite.getPosition() > rightGoal;
    	return result;
    }

    // Called once after isFinished returns true
    protected void end() {
    	dt.stopAll();
    	

    	System.out.println("***** END AUTODRIVE *****");
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
    
    int loopCounter = 0;
    int loopLimit = 10;
    
    public void debug() {
    	if (loopCounter++ > loopLimit) {
    		loopCounter = 0;
    		
    		System.out.print("Vit G : " + vitesseGauche);
    		System.out.println("\tVit D : " + vitesseDroite);
    		
    		System.out.print("Roue G pos : " + dt.roueGauche.getPosition());
    		System.out.println("\tRoue D pos : " + dt.roueDroite.getPosition());
    		
    		System.out.print("Erreur G : " + erreurGauche);
    		System.out.println("\tErreur D : " + erreurDroite);
    		
//    		System.out.print("Roue G spd : " + dt.roueGauche.getSpeed());
//    		System.out.println("Roue D spd : " + dt.roueDroite.getSpeed());
    	}
    	
    	
    }
}
