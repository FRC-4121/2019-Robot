/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.robot.commands.ChangeTeleopSpeed;
import frc.robot.commands.TestArmCommand;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  	
	//Define main control system objects
	public Encoder frontLeftEncoder, backLeftEncoder, frontRightEncoder, backRightEncoder;
	
	//Define joystick objects and joystick button functions
	public Joystick leftJoy, rightJoy;
	public Button changeDriveSpeed, testArmMotor;

	//Define limit switches
	public DigitalInput intakeLimitSwitch, climbTopLimitSwitch, climbBottomLimitSwitch;
	
	//Default class constructor
	public OI() {

		//Create new joystick objects
		rightJoy = new Joystick(0);

		//Initialize limit switches
		intakeLimitSwitch = new DigitalInput(0);
		climbTopLimitSwitch = new DigitalInput(1);
		climbBottomLimitSwitch = new DigitalInput(2);

		//Initialize Joystick buttons
		changeDriveSpeed = new JoystickButton(rightJoy, 2);
		testArmMotor = new JoystickButton(rightJoy, 1);

		//Configure Joystick button commands
		changeDriveSpeed.whenPressed(new ChangeTeleopSpeed());
		testArmMotor.whenPressed(new TestArmCommand(2));

	}
  
}
