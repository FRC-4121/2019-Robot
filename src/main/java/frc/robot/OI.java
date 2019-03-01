/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ArmToPositionCommand;
import frc.robot.commands.ChangeTeleopSpeed;
import frc.robot.commands.KillAutoCommand;
import frc.robot.commands.ShootOutBall;
import frc.robot.commands.StopArm;
import frc.robot.commands.StopClimb;
import frc.robot.commands.StopIntake;
import frc.robot.commands.TakeInBall;
import frc.robot.commands.TestArmSpeedCommand;
import frc.robot.commands.TestClimbDown;
import frc.robot.commands.TestClimbDrive;
import frc.robot.commands.TestClimbUp;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	//Define joystick objects and joystick button functions
	public Joystick leftJoy, rightJoy;
	public Button changeDriveSpeed, killAutoCommand, testArmUp, testArmMotorPositionMode, testArm2;
	public Button testClimbUp, testClimbDown, testIntake, testOuttake, testArmDown;
	public Button testClimbDrive;

	//Define limit switches
	public DigitalInput hatchLimitSwitch, hatchLoadedLimitSwitch, ballLimitSwitch, climbTopLimitSwitch, climbBottomLimitSwitch;
	
	public OI() {

		//Initialize limit switches
		hatchLimitSwitch = new DigitalInput(RobotMap.HATCH_DRIVE_LIMIT_SWITCH_ID);
		hatchLoadedLimitSwitch = new DigitalInput(RobotMap.HATCH_LOADED_LIMIT_SWITCH_ID);
		ballLimitSwitch = new DigitalInput(RobotMap.BALL_LIMIT_SWITCH_ID);
		//climbTopLimitSwitch = new DigitalInput(RobotMap.CLIMB_TOP_LIMIT_SWITCH_ID);
		//climbBottomLimitSwitch = new DigitalInput(RobotMap.CLIMB_BOTTOM_LIMIT_SWITCH_ID);

		//Joystick Configuration

		//Create new joystick objects
		//leftJoy = new Joystick(0); //Only needed for WCD
		rightJoy = new Joystick(0);

		/* Joystick Button Layout
		 * 1: Trigger
		 * 2: Side Button
		 * 3-6: Buttons on Joystick
		 * 7-12: Keypad on Base
		 * 
		 * POV Access -> Joystick.getPOV();
		 * Angles start at 0 (up) and increase by 45.
		 * Would require logic to use properly.
		 * Possibly an option for some of our operator assist this year.
		 * 
		 * 2019 Tournament Button Assignments
		 * 1:
		 * 2:
		 * 3:
		 * 4:
		 * 5:
		 * 6:
		 * 7-12: ?
		 * POV: ?
		 */

		//Initialize Joystick buttons
		killAutoCommand = new JoystickButton(rightJoy, 1);
		changeDriveSpeed = new JoystickButton(rightJoy, 2);
		
		testArmUp = new JoystickButton(rightJoy, 3);
		testArmDown = new JoystickButton(rightJoy, 4);
		testArmMotorPositionMode = new JoystickButton(rightJoy, 11);
		testArm2 = new JoystickButton(rightJoy, 12);
		
		testIntake = new JoystickButton(rightJoy, 5);
		testOuttake = new JoystickButton(rightJoy, 6);
		
		testClimbUp = new JoystickButton(rightJoy, 7);
		testClimbDown = new JoystickButton(rightJoy, 8);
		testClimbDrive = new JoystickButton(rightJoy, 9);
		

		//Configure Joystick button commands
		killAutoCommand.whenPressed(new KillAutoCommand());
		changeDriveSpeed.whenPressed(new ChangeTeleopSpeed());

		testIntake.whileHeld(new TakeInBall());//speed limit slowed for testing w/o limit switch
		testIntake.whenReleased(new StopIntake());
		testOuttake.whenPressed(new ShootOutBall(2.0));

		testArmUp.whileHeld(new TestArmSpeedCommand(true));
		testArmDown.whileHeld(new TestArmSpeedCommand(false));
		testArmUp.whenReleased(new StopArm());
		testArmDown.whenReleased(new StopArm());
		testArmMotorPositionMode.whenPressed(new ArmToPositionCommand(RobotMap.hatchLevel1Revs));
		testArm2.whenPressed(new ArmToPositionCommand(.22));
		
		testClimbUp.whileHeld(new TestClimbUp());
		testClimbUp.whenReleased(new StopClimb());
		testClimbDown.whileHeld(new TestClimbDown());
		testClimbDown.whenReleased(new StopClimb());
		testClimbDrive.whileHeld(new TestClimbDrive());
		testClimbDrive.whenReleased(new StopClimb());
	
	}
  
}
