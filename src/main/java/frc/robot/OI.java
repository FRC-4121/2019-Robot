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
import frc.robot.commands.AutoAssistHatchPickup;
import frc.robot.commands.AutoAssistHatchPlace;
import frc.robot.commands.AutoAssistToggleKill;
import frc.robot.commands.AutoDriveOffHab;
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
	public Button changeDriveSpeed, killAutoCommand, liftArmUp, liftArmDown, testArmMotorPositionMode, testArm2;
	public Button hatchPickup, hatchPlace, autonomousHab;
	public Button testClimbUp, testClimbDown, testIntake, testOuttake;
	public Button testClimbDrive;

	//Define limit switches
	public DigitalInput hatchLimitSwitch, hatchLoadedLimitSwitch, ballLimitSwitch, climbTopLimitSwitch, climbBottomLimitSwitch;
	
	public OI() {

		//Initialize limit switches
		hatchLimitSwitch = new DigitalInput(RobotMap.HATCH_DRIVE_LIMIT_SWITCH_ID);//default is true
		hatchLoadedLimitSwitch = new DigitalInput(RobotMap.HATCH_LOADED_LIMIT_SWITCH_ID);//default is false
		ballLimitSwitch = new DigitalInput(RobotMap.BALL_LIMIT_SWITCH_ID);//default is false
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
		
		//Lift arm related buttons
		liftArmDown = new JoystickButton(rightJoy, 3);
		liftArmUp = new JoystickButton(rightJoy, 4);
		testArmMotorPositionMode = new JoystickButton(rightJoy, 11);
		testArm2 = new JoystickButton(rightJoy, 12);
		
		testIntake = new JoystickButton(rightJoy, 5);
		testOuttake = new JoystickButton(rightJoy, 6);
		
		//testClimbUp = new JoystickButton(rightJoy, 7);
		testClimbDown = new JoystickButton(rightJoy, 8);

		hatchPickup = new JoystickButton(rightJoy, 9);
		hatchPlace = new JoystickButton(rightJoy, 10);
		
		autonomousHab = new JoystickButton(rightJoy, 7);

		//Configure Joystick button commands
		killAutoCommand.whenPressed(new AutoAssistToggleKill());
		changeDriveSpeed.whenPressed(new ChangeTeleopSpeed());

		testIntake.whenPressed(new TakeInBall());//speed limit slowed for testing w/o limit switch
		//testIntake.whenReleased(new StopIntake());
		testOuttake.whenPressed(new ShootOutBall(2.0));

		//Lift arm related actions
		liftArmUp.whileHeld(new TestArmSpeedCommand(true));
		liftArmDown.whileHeld(new TestArmSpeedCommand(false));
		liftArmUp.whenReleased(new StopArm());
		liftArmDown.whenReleased(new StopArm());
		testArmMotorPositionMode.whenPressed(new ArmToPositionCommand(RobotMap.hatchPickupLevel1Revs));
		testArm2.whenPressed(new ArmToPositionCommand(RobotMap.hatchReleaseLevel1Revs));
		
		//testClimbUp.whenPressed(new AutoAssistHatchPickup());
		//testClimbUp.whileHeld(new TestClimbUp());
		//testClimbUp.whenReleased(new StopClimb());

		hatchPickup.whenPressed(new AutoAssistHatchPickup());
		hatchPlace.whenPressed(new AutoAssistHatchPlace());

		autonomousHab.whenPressed(new AutoDriveOffHab());

		//testClimbDown.whileHeld(new TestClimbDown());
		//testClimbDown.whenReleased(new StopClimb());
		//testClimbDrive.whileHeld(new TestClimbDrive());
		//testClimbDrive.whenReleased(new StopClimb());
	
	}
  
}
