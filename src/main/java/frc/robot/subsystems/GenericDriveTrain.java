/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DriveWithJoysticksCommand;

/**
 * The general drive train class for all various drive train styles. Methods are
 * inherited and overridden by the individual DT classes. All methods present in
 * any DT class should also exist here.
 */

public class GenericDriveTrain extends Subsystem {

	//Initialize motor controllers needed for all drive trains
    WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(RobotMap.MOTOR_1);
    WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(RobotMap.MOTOR_2);    
    WPI_TalonSRX backLeftMotor = new WPI_TalonSRX(RobotMap.MOTOR_3);
    WPI_TalonSRX backRightMotor = new WPI_TalonSRX(RobotMap.MOTOR_4);
    WPI_TalonSRX strafeMotor = new WPI_TalonSRX(RobotMap.MOTOR_5);
    
    // //For WCD only
    // SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(frontLeftMotor, backLeftMotor);
    // SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(frontRightMotor, backRightMotor);

    //Initializing all WPI_TalonSRXs using CAN                                                
	WPI_TalonSRX leftMotorMaster = new WPI_TalonSRX(RobotMap.LEFT_MOTOR_MASTER);
	WPI_TalonSRX leftMotorSlave1 = new WPI_TalonSRX(RobotMap.LEFT_MOTOR_SLAVE_1);
	WPI_TalonSRX leftMotorSlave2 = new WPI_TalonSRX(RobotMap.LEFT_MOTOR_SLAVE_2);
	SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(leftMotorMaster, leftMotorSlave1, leftMotorSlave2);

	WPI_TalonSRX rightMotorMaster = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_MASTER);
	WPI_TalonSRX rightMotorSlave1 = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_SLAVE_1);
	WPI_TalonSRX rightMotorSlave2 = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_SLAVE_2);
	SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(rightMotorMaster, rightMotorSlave1, rightMotorSlave2);

    //Methods of Generic class are common to all drive train types. 
    //Specific method code is unique to each drive train type.
    public void initDefaultCommand() {
        
    	//All drive trains have default command to drive with the joysticks
        setDefaultCommand(new DriveWithJoysticksCommand());
    }
    
    public void drive(double leftJoyX, double leftJoyY, double leftJoyZ, double rightJoyX, double rightJoyY, double rightJoyZ) {}
    
    public void autoDrive(double speed, double angle) {}
}
