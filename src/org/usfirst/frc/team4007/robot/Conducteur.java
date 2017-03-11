package org.usfirst.frc.team4007.robot;

import org.usfirst.frc.team4007.robot.subsystems.DriveTrain;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

public class Conducteur {
	private double objectif_seuil = 0.00;
	private double objectif_gauche = 0.0;
	private double objectif_droite = 0.0;
	private double vitesse_droite = 0.0;
	private double vitesse_gauche = 0.0;
	private double begin_slow_at = 1.5; // Valeur par defaut
	private DriveTrain localDriveTrain;
	
	private int printLoop = 0;
	

	// Constructeur pour effectuer un virage
	public Conducteur(DriveTrain dt, double objectif_gauche, double objectif_droite){
		this.localDriveTrain = dt;
		this.objectif_gauche = objectif_gauche;
		this.objectif_droite = objectif_droite;
		
	   	// Mode follower sert a faire suivre 2 talons egalement
			// Regle la roue gauche comme esclave de la droite
	    	this.localDriveTrain.roueGauche.changeControlMode(TalonControlMode.PercentVbus);
	    	this.localDriveTrain.roueDroite.changeControlMode(TalonControlMode.PercentVbus);
	    	//this.localDriveTrain.roueGauche.set(RobotMap.canTalonRouesDroites);
		init();
	}
	
	// Constructeur pour faire avancer le robot en ligne droite
	public Conducteur(DriveTrain dt, double objectif){
		System.out.println("Configuration en FOLLOWER");
		
		this.localDriveTrain = dt;
		this.objectif_gauche = objectif;
		this.objectif_droite = objectif;
		

    	// Mode follower sert a faire suivre 2 talons egalement
		// Regle la roue gauche comme esclave de la droite
    	this.localDriveTrain.roueGauche.changeControlMode(TalonControlMode.PercentVbus);
    	//this.localDriveTrain.roueGauche.set(RobotMap.canTalonRouesDroites);
    	this.localDriveTrain.roueDroite.changeControlMode(TalonControlMode.PercentVbus);
		init();
	}
	
	private void init(){
		this.localDriveTrain.enableMotors();
		this.localDriveTrain.roueDroite.enableBrakeMode(false);
		this.localDriveTrain.roueGauche.enableBrakeMode(false);
    	reset();
	}
	
	public void set_begin_slow_at(double begin_slow_at){
		this.begin_slow_at = begin_slow_at;
	}
	
	// PersentVbus setting
	
	private static double vitesse_moins = 0.01;
	private static double vitesse_plus = 0.01;
	private static double vitesse_moins_limite = 0.25;
	private static double vitesse_plus_limite = 0.3;
	
	// Speed setting
	
	//private static double vitesse_moins =  .05 * 150.0; /* 150 RPM in either direction */;
	/*private static double vitesse_plus = .01 * 150.0;
	private static double vitesse_moins_limite = .25 * 150.0;
	*/

	
	public void execute(){
		// GESTION DE LA ROUE DE DROITE
		double objectif_droite_comming = Math.abs(objectif_droite) - Math.abs(this.localDriveTrain.roueDroite.getPosition());
		boolean obj_droite_atteint = objectif_droite_comming <= objectif_seuil; // Toujours calculer l'objectif en positif


				
	

			// GESTION DE LA ROUE DE GAUCHE
		double objectif_gauche_comming = Math.abs(objectif_gauche) - Math.abs(this.localDriveTrain.roueGauche.getPosition());
		boolean obj_gauche_atteint = objectif_gauche_comming <= objectif_seuil ; // Toujours calculer l'objectif en positif


		
		
		
		if(!obj_droite_atteint){
			
			if(objectif_droite_comming >= begin_slow_at)// Si jaccellere
			{
				if(vitesse_droite < vitesse_plus_limite) 
					vitesse_droite += vitesse_plus;
				else
					vitesse_droite = vitesse_plus_limite;
				
			}else{// Si je dois deccelerer pour arreter
				if(vitesse_droite > vitesse_moins_limite) 
					vitesse_droite -= vitesse_moins;
				else
					vitesse_droite = vitesse_moins_limite;
			}
			
			this.localDriveTrain.roueDroite.set(vitesse_droite);
		}else{
			this.localDriveTrain.roueDroite.set(0);
			vitesse_droite = 0;
			this.localDriveTrain.roueDroite.enableBrakeMode(true);
		}
		
		
		
		if(!obj_gauche_atteint){
			
			if(objectif_gauche_comming >= begin_slow_at)
			{
				if(vitesse_gauche < vitesse_plus_limite) 
					vitesse_gauche += vitesse_plus;
				else
					vitesse_gauche = vitesse_plus_limite;
			}else{
				if(vitesse_gauche > vitesse_moins_limite) 
					vitesse_gauche -= vitesse_moins;
				else
					vitesse_gauche = vitesse_moins_limite;
			}
			
			this.localDriveTrain.roueGauche.set(-vitesse_gauche);// inversion de la polariter pour une raison obscure
		}else{
			this.localDriveTrain.roueGauche.set(0);
			vitesse_gauche = 0;
			this.localDriveTrain.roueGauche.enableBrakeMode(true);
		}
		
		
		if (printLoop++ > 10) {
			printLoop = 0;
			
			System.out.print("VD : "+vitesse_droite);
			System.out.println(" *-*-*-*-*-*-* VG : "+vitesse_gauche);
		}
	}
	/*
	 * roue gauche = 500
	 * roue droite = 300
	 * */
	private double convertSpeedToVoltValue(CANTalon roue, int precision){
		return (Math.abs(roue.getSpeed())+0.01)/precision;
	}
	
	private boolean objectif_atteind(CANTalon roue, double objectif){
		return Math.abs(roue.getPosition()) >= Math.abs(objectif);
	}
	
	private void set_vitesse(CANTalon roue, double objectif, Double vitesse){
		//double vitesse_roue = (Math.abs(roue.getSpeed())+0.01)/300; // Valeur sont de 0-300
		//if(vitesse < 1) 
			vitesse = vitesse + 0.11;

		boolean obj_atteint = objectif_atteind(roue, objectif); // Toujours calculer l'objectif en positif

		if(!obj_atteint){
			roue.set(vitesse);
				System.out.println("Avance!: "+vitesse);
		}else{
			roue.set(0); // STOP!
		}
		
		
	}
	
	public void reset() {
    	localDriveTrain.roueDroite.setEncPosition(0);
		localDriveTrain.roueDroite.setPosition(0);
    	this.vitesse_droite = 0;
    
		localDriveTrain.roueGauche.setEncPosition(0);
		localDriveTrain.roueGauche.setPosition(0);
    	this.vitesse_gauche = 0;
    	
    	printLoop = 0;
    }
	
	public void end(){
		localDriveTrain.disableMotors();
		this.localDriveTrain.roueDroite.enableBrakeMode(false);
		this.localDriveTrain.roueGauche.enableBrakeMode(false);
		/*if(localDriveTrain.roueGauche.getControlMode() == TalonControlMode.PercentVbus)
		{
			localDriveTrain.roueDroite.set(0);
			localDriveTrain.roueGauche.set(0);
		}*/
		reset();
	}
	public boolean isFinish(){
		return objectif_atteind(localDriveTrain.roueDroite, objectif_droite) && objectif_atteind(localDriveTrain.roueGauche, objectif_gauche);
	}
}
