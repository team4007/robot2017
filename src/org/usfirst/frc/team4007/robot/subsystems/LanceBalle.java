package org.usfirst.frc.team4007.robot.subsystems;

import org.usfirst.frc.team4007.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LanceBalle extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public Relay alimenteur;
	public CANTalon spineWheel;
	
	public LanceBalle(){
		super();
		alimenteur = new Relay(RobotMap.spikeLanceBalle);
		spineWheel = new CANTalon(RobotMap.canTalonLanceBalle);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void startRotateBase(){
    	alimenteur.set(Relay.Value.kReverse);
    }
    public void stopRotateBase(){
    	alimenteur.set(Relay.Value.kOff);
    }
    
    public void startBalleWheel(){
    	spineWheel.set(-1.0);
    }

	public void stopBalleWheel() {
		// TODO Auto-generated method stub
		spineWheel.set(0.0);
	}
}

