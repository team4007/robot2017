package org.usfirst.frc.team4007.robot.commands;

import org.usfirst.frc.team4007.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CameraCommand extends Command {
	
    public CameraCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.cameraSubSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.cameraSubSystem.position_up();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.btnCamUp.get())
    	{
    		Robot.cameraSubSystem.position_up();
    	}else if(Robot.oi.btnCamMid.get()){
    		Robot.cameraSubSystem.position_mid();
    	}else if(Robot.oi.btnCamDown.get()){
    		Robot.cameraSubSystem.position_down();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
