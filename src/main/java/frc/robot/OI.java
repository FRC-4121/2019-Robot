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
import frc.robot.commands.ShootOutBall;
import frc.robot.commands.TakeInBall;
//import frc.robot.commands.TestClimbUp;
//import frc.robot.commands.TestClimbDown;
import frc.robot.commands.TestArmRotationsCommand;
import frc.robot.commands.TestArmSpeedCommand;
import frc.robot.commands.TestClimbCommand;
import frc.robot.commands.TestClimbDrive;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  	
	//Define main control system objects
	public Encoder frontLeftEncoder, backLeftEncoder, frontRightEncoder, backRightEncoder;
	
	//Define joystick objects and joystick button functions
	public Joystick leftJoy, rightJoy;
	public Button changeDriveSpeed, testArmMotorSpeedMode, testArmMotorPositionMode;
	public Button testClimbUp, testClimbDown, testIntake, testOuttake, testArmDown;
	public Button testClimbDrive;

	//Define limit switches
	public DigitalInput hatchLimitSwitch, ballLimitSwitch, climbTopLimitSwitch, climbBottomLimitSwitch;
	
	//Default class constructor
	public OI() {

		//Create new joystick objects
		rightJoy = new Joystick(0);

		//Initialize limit switches
		hatchLimitSwitch = new DigitalInput(0);
		ballLimitSwitch = new DigitalInput(1);
		climbTopLimitSwitch = new DigitalInput(2);
		climbBottomLimitSwitch = new DigitalInput(3);

		//Initialize Joystick buttons
		changeDriveSpeed = new JoystickButton(rightJoy, 2);
		testArmMotorSpeedMode = new JoystickButton(rightJoy, 3);
		testArmDown = new JoystickButton(rightJoy, 4);
		//testArmMotorPositionMode = new JoystickButton(rightJoy, 3);
		testIntake = new JoystickButton(rightJoy, 5);
		testOuttake = new JoystickButton(rightJoy, 6);
		testClimbUp = new JoystickButton(rightJoy, 7);
		testClimbDown = new JoystickButton(rightJoy, 8);
		testClimbDrive = new JoystickButton(rightJoy, 9);

		//THESE ARE A PROBLEM!!!
		//Configure Joystick button commands
		changeDriveSpeed.whenPressed(new ChangeTeleopSpeed());
		testIntake.whileHeld(new TakeInBall(-0.5));
		testOuttake.whileHeld(new ShootOutBall());
		testArmMotorSpeedMode.whileHeld(new TestArmSpeedCommand(0.5));
		testArmDown.whileHeld(new TestArmSpeedCommand(-0.5));
		//testArmMotorPositionMode.whenPressed(new TestArmRotationsCommand());
		testClimbUp.whileHeld(new TestClimbCommand(1));
		testClimbDown.whileHeld(new TestClimbCommand(-1));
		testClimbDrive.whileHeld(new TestClimbDrive());
	}
  
}
