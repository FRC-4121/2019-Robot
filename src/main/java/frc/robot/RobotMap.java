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

	//Motor Controller Constants
	public static final int MOTOR_1 = 10;
	public static final int MOTOR_2 = 20;
	public static final int MOTOR_3 = 30;
	public static final int MOTOR_4 = 40;
	public static final int MOTOR_5 = 50;
	
	public static final int LEFT_MOTOR_MASTER = 3;
	public static final int LEFT_MOTOR_SLAVE_1 = 1;
	public static final int LEFT_MOTOR_SLAVE_2 = 2;
	public static final int RIGHT_MOTOR_MASTER = 14;
	public static final int RIGHT_MOTOR_SLAVE_1 = 0;
	public static final int RIGHT_MOTOR_SLAVE_2 = 13;
	public static final int ENDMOTOR2 = 11; 
	public static final int ELEVATOR_MOTOR_MASTER = 12;

	//Spark Motor Controllers
	public static final int ENDMOTOR1 = 0;
	public static final int ANGLEMOTOR = 1;

	//Invert direction (for WCD only)
	public static int DIRECTION_MULTIPLIER = 1;
	
	//Strafe wheel multiplier (for slide drive only)
	public static double STRAFE_MULTIPLIER = 1;

	//Standard motor drive speeds for auto
	public static final double AUTO_DRIVE_SPEED = 0.5;
	public static final double DRIVE_SPEED = 0.65;

	//Encoder distances
	public static final double MECANUM_ENCODER_DPP = 0.075;
	
	//PID values
	public static double kP_Straight = 0.03;
	public static double kP_Turn = 0.06;
	public static double kI_Straight = 0.0;
	public static double kI_Turn = 0.0;
	public static double kD_Straight = 0.0;
	public static double kD_Turn = 0.0;

}
