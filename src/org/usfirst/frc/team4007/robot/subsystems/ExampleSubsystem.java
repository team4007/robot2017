
package org.usfirst.frc.team4007.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.AnalogTriggerOutput.AnalogTriggerType;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ExampleSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	AnalogTrigger trigger;
	
	Relay moteur;
    public void initDefaultCommand() {
    	trigger = new AnalogTrigger(0);
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	trigger.setLimitsVoltage(3.5, 5.0);
    	
    	trigger.createOutput(AnalogTriggerType.kInWindow);
	}
    
    public ExampleSubsystem(){
    	moteur = new Relay(0);
    }
    
    public void start(){
    	moteur.set(Value.kForward);
    }
    public void stop(){
    	moteur.set(Value.kOff);
    }
}

