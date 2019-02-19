/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class HabClimberSubsystem extends Subsystem {
  
  WPI_TalonSRX climbLift = new WPI_TalonSRX(RobotMap.CLIMB_LIFT);
  WPI_TalonSRX climbDrive = new WPI_TalonSRX(RobotMap.CLIMB_DRIVE);

  @Override
  public void initDefaultCommand() {}

  //Basic steps: deploy guide wheels, raise lift WHILE ALSO DRIVING with wheels, then pull lift back up

  public void deployLift(double direction){  //1 is up, -1 is down
    
    climbLift.set(direction * RobotMap.CLIMB_LIFT_SPEED);
  }

  public void driveWheels(){

    climbDrive.set(RobotMap.CLIMB_DRIVE_SPEED);
  }
}
