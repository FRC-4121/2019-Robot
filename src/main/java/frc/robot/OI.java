/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  	
	//Define main control system objects
	public Encoder frontLeftEncoder, backLeftEncoder, frontRightEncoder, backRightEncoder;
	
	//Define joystick objects and joystick button functions
	public Joystick leftJoy, rightJoy; 
	
	//Default class constructor
	public OI() {

		//Create new joystick objects
		leftJoy = new Joystick(2);
		rightJoy = new Joystick(3);

	}
  
}
