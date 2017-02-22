package org.usfirst.frc.team4007.robot.subsystems;

import org.usfirst.frc.team4007.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gear extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public Relay moteur;
	public AnalogTrigger analogtrigger;
	public Counter compteur;
	public DigitalInput limitSwitch;
	
	//public AnalogTriggerOutput counter;
	public int compteurPulse,sens;
	/*public boolean previousState;
	public Value previousValue;
	public int max;*/
	public Gear(){
		super();
		moteur = new Relay(RobotMap.spikeGear);
		analogtrigger = new AnalogTrigger(RobotMap.analogGear);
		limitSwitch = new DigitalInput(RobotMap.limitSwitchGear);
		
		//niv haut : 4.21
		//niv bas : 2.88
		compteurPulse = 0;
		sens = 1;
		//max = 25;
		analogtrigger.setLimitsVoltage(3.0, 4.0); 
		//counter = analogtrigger.createOutput(AnalogTriggerType.kFallingPulse);
		analogtrigger.setAveraged(true);
		compteur = new Counter(analogtrigger);
		compteur.reset();
		/*previousState = analogtrigger.getTriggerState();
		previousValue = Value.kReverse;*/

	}
	
	public void compterTour(){
		/*boolean currentState = analogtrigger.getTriggerState();
		Value currentValue = (Value.kReverse == moteur.get() || Value.kForward == moteur.get()) ? (moteur.get()) : previousValue;
		if(currentState != previousState){
			if(currentValue == Value.kForward){
				compteurPulse--;
			}else if(currentValue == Value.kReverse){
				compteurPulse++;
			}
			previousState = currentState;
			previousValue = currentValue;
		}*/
		
		updateCounter();
		System.out.println(compteurPulse);
	}
	
	public void updateCounter(){
		compteurPulse = compteurPulse + (sens) * compteur.get();
		compteur.reset();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void demarreOuvre(){
    	//System.out.println("GRIMPEUR DEMARRE");
    	updateCounter();
    	moteur.set(Value.kReverse);
    	sens = -1;    	
    }
    
    public void arret(){
    	System.out.println("Arret relay");
    	moteur.set(Value.kOff);
    }
    
    public void demarreFerme(){
    	//System.out.println("GRIMPEUR DEMARRE");
    	updateCounter();
    	moteur.set(Value.kForward);
    	sens = 1;
    }
}

