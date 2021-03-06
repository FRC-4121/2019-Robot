/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	//Motor Controller Constants - Mecanum Drive
	public static final int BACK_LEFT_MOTOR_ID = 6;
	public static final int BACK_RIGHT_MOTOR_ID = 1;
	public static final int FRONT_LEFT_MOTOR_ID = 5;
	public static final int FRONT_RIGHT_MOTOR_ID = 0;

	//Motor Controller Constants - Mechanism Talons
	public static final int INTAKE_MOTOR_ID = 2;
	public static final int ARM_MOTOR_ID = 3;
	
	//Motor Controller Constants - WCD
	//(Not for 2019 Season Use)
	public static final int LEFT_MOTOR_MASTER = -1;
	public static final int LEFT_MOTOR_SLAVE_1 = -1;
	public static final int LEFT_MOTOR_SLAVE_2 = -1;
	public static final int RIGHT_MOTOR_MASTER = -1;
	public static final int RIGHT_MOTOR_SLAVE_1 = -1;
	public static final int RIGHT_MOTOR_SLAVE_2 = -1;


	//Limit switch ids
	public static final int HATCH_DRIVE_LIMIT_SWITCH_ID = 1;
	public static final int ARM_LIMIT_SWITCH_ID = 2;
	public static final int HATCH_LOADED_LIMIT_SWITCH_ID = 0;

	//Speed multiplier for more accurate driving in mecanum
	public static final double MECANUM_TURN_MULTIPLIER = .8;

	//Motor speeds
	public static final double AUTO_DRIVE_SPEED = 1.0;
	public static double DRIVE_SPEED = 1.0;
	public static final double DRIVE_SPEED_FAST = 1.0;
	public static final double DRIVE_SPEED_SLOW = 0.5;

	public static final double INTAKE_SPEED = -1.0;
	public static final double OUTTAKE_SPEED = .75;

	public static final double ARM_SPEED_UP = 1.0;
	public static final double ARM_SPEED_DOWN = -.5;

	public static final double STOP_SPEED = 0;


	//Encoder config values
	public static final int kEncoderPPR = 4096;
	public static final boolean kSensorPhase = true;
	public static final boolean kMotorInvert = true;
	public static final int kTimeoutMs = 10;
	public static final int kPIDLoopIdx = 0;
	public static final int ARM_ENCODER_START_POS = 0;


	//PID values for mecanum
	public static final double kP_Straight = 0.05;
	public static final double kP_Slew = 1;
	public static final double kP_Turn = .5;
	public static final double kI_Straight = 0.0;
	public static final double kI_Slew = 0.0;
	public static final double kI_Turn = 0.0;
	public static final double kD_Straight = 0.004;
	public static final double kD_Slew = 0.00;
	public static final double kD_Turn = 0.02;

	//PID values for arm lift motor control
	public static final double kP_Arm = 10;
	public static final double kI_Arm = 0;
	public static final double kD_Arm = 1;
	public static final double kFf_Arm = 0;

	//These need to be readjusted due to the change in wheel height!
	//Revolution variables for arm lift commands
	public static final double floorRevs = 0.00;
	public static final double hatchPickupLevel1Revs = .22;
	public static final double cargoBallReleaseRevs = 0.85;
	public static final double rocket1BallRevs = .5;
	public static final double rocket2BallRevs = 0;
	public static final double rocket2HatchRevs = 0.7;
	public static final double armMaxRevs = .88;//hard stop, probably wrong

	//Extraneous variables
	public static int DIRECTION_MULTIPLIER = -1;		//Invert drive direction (for WCD only)
	public static boolean KILL_AUTO_COMMAND = false;	//Kill any autonomous or auto-assist commands
	public static double VISION_TARGET_ANGLE = 0.0;		//Angle to current vision target
	public static boolean RESET_ARM_ENCODER = false;
	public static String TELEOP_SPEED = "Fast";
}
