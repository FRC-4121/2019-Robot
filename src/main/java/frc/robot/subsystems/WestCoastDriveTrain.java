/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.commands.DriveWithJoysticksCommand;

import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class WestCoastDriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //Initialize robot drive train - 6-wheel WCD style
  WPI_TalonSRX leftMotorMaster = new WPI_TalonSRX(RobotMap.LEFT_MOTOR_MASTER);
  WPI_TalonSRX leftMotorSlave1 = new WPI_TalonSRX(RobotMap.LEFT_MOTOR_SLAVE_1);
  WPI_TalonSRX leftMotorSlave2 = new WPI_TalonSRX(RobotMap.LEFT_MOTOR_SLAVE_2);
	SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(leftMotorMaster, leftMotorSlave1, leftMotorSlave2);

	WPI_TalonSRX rightMotorMaster = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_MASTER);
	WPI_TalonSRX rightMotorSlave1 = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_SLAVE_1);
	WPI_TalonSRX rightMotorSlave2 = new WPI_TalonSRX(RobotMap.RIGHT_MOTOR_SLAVE_2);
	SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(rightMotorMaster, rightMotorSlave1, rightMotorSlave2);

  DifferentialDrive westCoastDrive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);


  public void initDefaultCommand() {
        
    //All drive trains have default command to drive with the joysticks
      setDefaultCommand(new DriveWithJoysticksCommand());
  }
                                           

  public void drive(double leftJoyX, double leftJoyY, double rightJoyX, double rightJoyY) {

    if(RobotMap.DIRECTION_MULTIPLIER == 1)
    {  
      westCoastDrive.tankDrive(leftJoyY * RobotMap.DIRECTION_MULTIPLIER, rightJoyY * RobotMap.DIRECTION_MULTIPLIER);
    }
    else
    {  
      westCoastDrive.tankDrive(rightJoyY * RobotMap.DIRECTION_MULTIPLIER, leftJoyY * RobotMap.DIRECTION_MULTIPLIER);
    }

		westCoastDrive.setSafetyEnabled(false);
		
		westCoastDrive.setMaxOutput(0.8);

  }

 
  public void autoDrive(double speed, double angle) {
    
    //double angleError = Robot.oi.mainGyro.getAngle() - angle;
    //double angleCorrection = RobotMap.kP_Straight * angleError;
      
    //westCoastDrive.tankDrive((RobotMap.AUTO_DRIVE_SPEED + angleCorrection), (RobotMap.AUTO_DRIVE_SPEED - angleCorrection));    	    	
    
  }
}
