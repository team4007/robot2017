package org.usfirst.frc.team4007.robot.subsystems;

import java.time.format.ResolverStyle;

import org.usfirst.frc.team4007.robot.Robot;
import org.usfirst.frc.team4007.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.AnalogTriggerOutput;
import edu.wpi.first.wpilibj.AnalogTriggerOutput.AnalogTriggerType;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.hal.EncoderJNI;

/**
 *
 */
public class Gear extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public Relay moteur;
	public AnalogTrigger analogtrigger;
	public AnalogTriggerOutput counter;
	public int compteurPulse;
	public boolean previousState;
	public Value previousValue;
	public int max;
	public Gear(){
		super();
		moteur = new Relay(RobotMap.spikeGear);
		analogtrigger = new AnalogTrigger(RobotMap.analogGear);
		//niv haut : 4.21
		//niv bas : 2.88
		compteurPulse = 0;
		max = 25;
		analogtrigger.setLimitsVoltage(3.0, 4.0); 
		counter = analogtrigger.createOutput(AnalogTriggerType.kFallingPulse);
		analogtrigger.setAveraged(true);
		previousState = analogtrigger.getTriggerState();
		previousValue = Value.kReverse;

	}
	
	public void compterTour(){
		boolean currentState = analogtrigger.getTriggerState();
		Value currentValue = (Value.kReverse == moteur.get() || Value.kForward == moteur.get()) ? (moteur.get()) : previousValue;
		if(currentState != previousState){
			if(currentValue == Value.kForward){
				compteurPulse--;
			}else if(currentValue == Value.kReverse){
				compteurPulse++;
			}
			previousState = currentState;
			previousValue = currentValue;
		}
		System.out.println(compteurPulse);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void demarreOuvre(){
    	//System.out.println("GRIMPEUR DEMARRE");
    	moteur.set(Value.kReverse);
    }
    
    public void arret(){
    	System.out.println("Arret relay");
    	moteur.set(Value.kOff);
    }
    
    public void demarreFerme(){
    	//System.out.println("GRIMPEUR DEMARRE");
    	moteur.set(Value.kForward);
    }
}

