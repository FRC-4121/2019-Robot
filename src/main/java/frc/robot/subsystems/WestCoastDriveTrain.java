/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class WestCoastDriveTrain extends GenericDriveTrain {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  //Initialize robot drive train - 6-wheel WCD style
  DifferentialDrive westCoastDrive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);

  @Override
  public void drive(double leftJoyX, double leftJoyY, double leftJoyZ, double rightJoyX, double rightJoyY, double rightJoyZ) {

    if(RobotMap.DIRECTION_MULTIPLIER == 1)
      
      westCoastDrive.tankDrive(leftJoyY * RobotMap.DIRECTION_MULTIPLIER, rightJoyY * RobotMap.DIRECTION_MULTIPLIER);
    
    else
      
      westCoastDrive.tankDrive(rightJoyY * RobotMap.DIRECTION_MULTIPLIER, leftJoyY * RobotMap.DIRECTION_MULTIPLIER);

  }

  @Override
  public void autoDrive(double speed, double angle) {
    
    double angleError = Robot.oi.mainGyro.getAngle() - angle;
    double angleCorrection = RobotMap.kP_Straight * angleError;
      
    westCoastDrive.tankDrive((RobotMap.AUTO_DRIVE_SPEED + angleCorrection), (RobotMap.AUTO_DRIVE_SPEED - angleCorrection));    	    	
    
  }
}
