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

public class HabClimberSubsystem extends Subsystem {
  
  public WPI_TalonSRX climbLift = new WPI_TalonSRX(RobotMap.CLIMB_LIFT_ID);
  public WPI_TalonSRX climbDrive = new WPI_TalonSRX(RobotMap.CLIMB_DRIVE_ID);

  @Override
  public void initDefaultCommand() {}

  public void deployLift(boolean runUp){
    
    if(runUp)
    {
      climbLift.set(RobotMap.CLIMB_LIFT_SPEED);
    }
    else
    {
      climbLift.set(-RobotMap.CLIMB_LIFT_SPEED);
    }
  }

  public void driveWheels(){

    climbDrive.set(RobotMap.CLIMB_DRIVE_SPEED);
  }

  public void stopClimb(){

    climbLift.set(RobotMap.STOP_SPEED);
  }

  public void stopClimbDrive(){

    climbDrive.set(RobotMap.STOP_SPEED);
  }

}
