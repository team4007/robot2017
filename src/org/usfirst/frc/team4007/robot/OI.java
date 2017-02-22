package org.usfirst.frc.team4007.robot;

import org.usfirst.frc.team4007.robot.commands.CameraCommand;
import org.usfirst.frc.team4007.robot.commands.Debug;
import org.usfirst.frc.team4007.robot.commands.FreeGear;
import org.usfirst.frc.team4007.robot.commands.Grimpe;
import org.usfirst.frc.team4007.robot.commands.ResetGear;
import org.usfirst.frc.team4007.robot.commands.StartBalleBrasseur;
import org.usfirst.frc.team4007.robot.commands.StartBalleLanceur;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	public Joystick joystickGauche;
	public Joystick joystickDroit;
	public JoystickButton btnGrimpeur;
	public JoystickButton btnLanceurDroit;
	public JoystickButton btnBrasseurGauche;
	public JoystickButton btnGearRelease;
	public JoystickButton btnDebug;
	
	public JoystickButton btnCamUp;
	public JoystickButton btnCamMid;
	public JoystickButton btnCamDown;
	
	public OI(){
		joystickGauche = new Joystick(RobotMap.portJoystickGauche);
		joystickDroit = new Joystick (RobotMap.portJoystickDroit);
		
		btnGrimpeur = new JoystickButton(joystickDroit, RobotMap.btnGrimpeur);		
		btnLanceurDroit = new JoystickButton(joystickDroit, RobotMap.btnLanceur);
		btnBrasseurGauche = new JoystickButton(joystickGauche, RobotMap.btnBrasseurLanceur);
		btnGearRelease = new JoystickButton(joystickDroit, RobotMap.btnGear);
		
		btnGrimpeur.whenPressed(new Grimpe());		
		btnLanceurDroit.whenPressed(new StartBalleLanceur());		
		btnBrasseurGauche.whenPressed(new StartBalleBrasseur());
		
		btnGearRelease.whenPressed(new FreeGear());
		btnGearRelease.whenReleased(new ResetGear());
		
		btnCamUp = new JoystickButton(joystickGauche, RobotMap.btnCameraUp);
		//btnCamMid = new JoystickButton(joystickGauche, RobotMap.btnCameraMid);
		btnCamDown = new JoystickButton(joystickGauche, RobotMap.btnCameraDown);
		
		btnCamUp.whenPressed(new CameraCommand());
		//btnCamMid.whenPressed(new CameraCommand());
		btnCamDown.whenPressed(new CameraCommand());
		
		btnDebug = new JoystickButton(joystickDroit, 6);
		btnDebug.whenPressed(new Debug());
		
	}
}

