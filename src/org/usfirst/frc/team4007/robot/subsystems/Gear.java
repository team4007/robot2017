package org.usfirst.frc.team4007.robot.subsystems;

import java.time.format.ResolverStyle;

import org.usfirst.frc.team4007.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.AnalogTriggerOutput;
import edu.wpi.first.wpilibj.AnalogTriggerOutput.AnalogTriggerType;
import edu.wpi.first.wpilibj.Counter;
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
	public AnalogTriggerOutput counter;
	
	public Gear(){
		super();
		moteur = new Relay(RobotMap.spikeGear);
		analogtrigger = new AnalogTrigger(RobotMap.analogGear);
		counter = analogtrigger.createOutput(AnalogTriggerType.kRisingPulse);
		analogtrigger.setLimitsVoltage(0, 5);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void demarreOuvre(){
    	//System.out.println("GRIMPEUR DEMARRE");
    	moteur.set(Value.kForward);
    }
    
    public void arret(){
    	moteur.set(Value.kOff);
    }
    
    public void demarreFerme(){
    	//System.out.println("GRIMPEUR DEMARRE");
    	moteur.set(Value.kReverse);
    }
}

