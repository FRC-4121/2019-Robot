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
	public static final int BACK_LEFT_MOTOR = 5;
	public static final int BACK_RIGHT_MOTOR = 1;
	public static final int FRONT_LEFT_MOTOR = 4;
	public static final int FRONT_RIGHT_MOTOR = 0;

	//Motor Controller Constants - Mechanism Talons
	public static final int CLIMB_LIFT = 7;
	public static final int CLIMB_DRIVE = 6;
	public static final int INTAKE_MOTOR = 2;
	public static final int ARM_MOTOR_ID = 3;
	
	//Motor Controller Constants - WCD
	//Not for 2019 Season Use
	public static final int LEFT_MOTOR_MASTER = -1;
	public static final int LEFT_MOTOR_SLAVE_1 = -1;
	public static final int LEFT_MOTOR_SLAVE_2 = -1;
	public static final int RIGHT_MOTOR_MASTER = -1;
	public static final int RIGHT_MOTOR_SLAVE_1 = -1;
	public static final int RIGHT_MOTOR_SLAVE_2 = -1;

	//Invert direction (for WCD only)
	public static final int DIRECTION_MULTIPLIER = 1;
	
	//Speed multiplier for more accurate driving in mecanum
	public static final double MECANUM_MULTIPLIER = 0.5;

	//Standard motor drive speeds for auto
	public static final double AUTO_DRIVE_SPEED = 0.8;
	public static double DRIVE_SPEED = 0.65;
	public static final double DRIVE_SPEED_FAST = 1.0;
	public static final double DRIVE_SPEED_SLOW = 0.25;

	//Arm motor speed
	public static final double ARM_SPEED = 0.3;

	//Climber motor speeds
	public static final double CLIMB_LIFT_SPEED = 0.75;
	public static final double CLIMB_DRIVE_SPEED = 0.5;

	//Encoder config values
	public static boolean kSensorPhase = true;
	public static boolean kMotorInvert = true;
	public static int kTimeoutMs = 10;

	//PID values for mecanum
	public static double kP_Straight = 0.05;
	public static double kP_Turn = 0.05;
	public static double kI_Straight = 0.0;
	public static double kI_Turn = 0.0;
	public static double kD_Straight = 0.004;
	public static double kD_Turn = 0.004;

	//PID values for arm lift motor control
	public static double kP_Arm = 0.15;
	public static double kI_Arm = 0;
	public static double kD_Arm = 1;
	public static double kFf_Arm = 0;

	//Variables for arm lift commands
	public static double pickUpHatchAndUnlockHatchLvl1Revs = 12.98;
	public static double lockHatchAndPlaceHatchLvl1Revs = 3.34;
	public static double placeHatchLvl2Revs = 64.65;
	public static double unlockHatchLvl2Revs = 58.63;
	public static double placeBallRocketLvl1Revs = 45.37;
	public static double placeBallCargoShipRevs = 65.85;
	public static double placeBallRocketLvl2Revs = 102.64;

	//Testing variables
	public static double ARM_REVOLUTIONS = 5;
	

}
