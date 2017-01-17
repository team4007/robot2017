
package org.usfirst.frc.team4007.robot.subsystems;

import org.usfirst.frc.team4007.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ExampleSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	CANTalon danny;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public ExampleSubsystem(){
    	danny = new CANTalon(RobotMap.cantalonPort);
    }
    
    public void start(){
    	danny.set(1.0D);
    }
    public void stop(){
    	danny.set(0);
    }
}

