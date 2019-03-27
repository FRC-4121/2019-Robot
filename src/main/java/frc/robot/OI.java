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
import frc.robot.commands.ArmRunAtSpeed;
import frc.robot.commands.ArmToPositionCommand;
import frc.robot.commands.AutoAssistAlignCommandGroup;
import frc.robot.commands.AutoAssistCargoBallPlace;
import frc.robot.commands.AutoAssistHatch;
import frc.robot.commands.AutoAssistHatchLevel2;
import frc.robot.commands.AutoAssistHatchPickup;
import frc.robot.commands.AutoAssistHatchPlace;
import frc.robot.commands.AutoAssistRocket1BallPlace;
import frc.robot.commands.AutoAssistRocket2BallPlace;
import frc.robot.commands.AutoAssistToggleKill;
import frc.robot.commands.ChangeTeleopSpeed;
import frc.robot.commands.ShootOutBall;
import frc.robot.commands.StopArm;
import frc.robot.commands.StopIntake;
import frc.robot.commands.TakeInBall;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	//Define joystick objects and joystick button functions
	public Joystick leftJoy, rightJoy;
	public Button changeDriveSpeed, killAutoCommand, liftArmUp, liftArmDown, liftArmToHatchPickup;
	public Button hatchPickup, hatchPlace, autonomousHab, liftArmToBallPickupRelease;
	public Button runIntake, runOuttake, cargoShipPlace;

	//Define limit switches
	public DigitalInput hatchLimitSwitch, hatchLoadedLimitSwitch, ballLimitSwitch, armLimitSwitch;
	private JoystickButton uberHatch;
	private Button liftArmToFloor;
	private Button cargoRocket1Place;
	private Button cargoRocket2Place;
	private JoystickButton hatchLevel2;
	public Button autoAlign;
	
	public OI() {

		//Initialize limit switches
		hatchLimitSwitch = new DigitalInput(RobotMap.HATCH_DRIVE_LIMIT_SWITCH_ID);
		hatchLoadedLimitSwitch = new DigitalInput(RobotMap.HATCH_LOADED_LIMIT_SWITCH_ID);
		armLimitSwitch = new DigitalInput(RobotMap.ARM_LIMIT_SWITCH_ID);

		//Joystick Configuration

		//Create new joystick objects
		leftJoy = new Joystick(0);
		rightJoy = new Joystick(1);

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
		liftArmToBallPickupRelease = new JoystickButton(rightJoy, 8); //height for loading station pickup and cargoship placement
		liftArmToHatchPickup = new JoystickButton(rightJoy, 10); //'up' height
		liftArmToFloor = new JoystickButton(rightJoy, 12);

		runIntake = new JoystickButton(rightJoy, 5);
		runOuttake = new JoystickButton(rightJoy, 6);
		
		cargoShipPlace = new JoystickButton(leftJoy, 3);
		cargoRocket1Place = new JoystickButton(leftJoy, 5);
		cargoRocket2Place = new JoystickButton(leftJoy, 6);
		// hatchPickup = new JoystickButton(rightJoy, 9);
		// hatchPlace = new JoystickButton(rightJoy, 7);
		uberHatch = new JoystickButton(leftJoy, 4);
		hatchLevel2 = new JoystickButton(rightJoy, 11);

		autoAlign = new JoystickButton(leftJoy, 1);
		
		//Configure Joystick button commands
		killAutoCommand.whenPressed(new AutoAssistToggleKill());
		changeDriveSpeed.whenPressed(new ChangeTeleopSpeed());

		//Intake actions
		runIntake.whileHeld(new TakeInBall());
		runIntake.whenReleased(new StopIntake());
		runOuttake.whileHeld(new ShootOutBall(0));
		runOuttake.whenReleased(new StopIntake());

		//Lift arm related actions
		liftArmUp.whileHeld(new ArmRunAtSpeed(true));
		liftArmDown.whileHeld(new ArmRunAtSpeed(false));
		liftArmUp.whenReleased(new StopArm());
		liftArmDown.whenReleased(new StopArm());
		liftArmToHatchPickup.whenPressed(new ArmToPositionCommand(RobotMap.hatchPickupLevel1Revs));
		liftArmToBallPickupRelease.whenPressed(new ArmToPositionCommand(RobotMap.cargoBallReleaseRevs));
		liftArmToFloor.whenPressed(new ArmToPositionCommand(RobotMap.floorRevs));
		
		//Auto assist commands
		cargoShipPlace.whenPressed(new AutoAssistCargoBallPlace());
		cargoRocket1Place.whenPressed(new AutoAssistRocket1BallPlace());
		cargoRocket2Place.whenPressed(new AutoAssistRocket2BallPlace());
		// hatchPickup.whenPressed(new AutoAssistHatchPickup());
		// hatchPlace.whenPressed(new AutoAssistHatchPlace());
		uberHatch.whenPressed(new AutoAssistHatch(10));
		hatchLevel2.whenPressed(new AutoAssistHatchLevel2());
		autoAlign.whenPressed(new AutoAssistAlignCommandGroup());

	
	}
  
}
