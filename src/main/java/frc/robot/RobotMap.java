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
	public static final int BACK_LEFT_MOTOR = 8;
	public static final int BACK_RIGHT_MOTOR = 2;
	public static final int FRONT_LEFT_MOTOR = 7;
	public static final int FRONT_RIGHT_MOTOR = 6;
	
	//Motor Controller - Spark for Arm
	public static final int ARM_MOTOR = 1;

	//Motor Controller Constants - Mechanism Talons
	public static final int CLIMB_MOTOR_1 = 3;
	public static final int CLIMB_MOTOR_2 = 4;
	public static final int INTAKE_MOTOR = 5;
	
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
	
	//PID values for mecanum
	public static double kP_Straight = 0.05;
	public static double kP_Turn = 0.05;
	public static double kI_Straight = 0.0;
	public static double kI_Turn = 0.0;
	public static double kD_Straight = 0.004;
	public static double kD_Turn = 0.004;

	//PID values for arm lift motor control
	public static double kP_Arm = 0.1;
	public static double kI_Arm = 0;
	public static double kD_Arm = 1;
	public static double kIz_Arm = 0;
	public static double kFf_Arm = 0;
	public static double kMaxOutput_Arm = 1;
	public static double kMinOutput_Arm = -1;

}
