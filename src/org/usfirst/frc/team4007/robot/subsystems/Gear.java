package org.usfirst.frc.team4007.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gear extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public CANTalon moteur;
	public AnalogTrigger analogtrigger;
	public Counter counter;
	
	public Gear(){
		super();
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

