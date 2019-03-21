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
import frc.robot.commands.DriveClimberWithJoystick;

/**
 * Add your docs here.
 */
public class ClimbDriveSubsystem extends Subsystem {
  
  public WPI_TalonSRX climbDrive = new WPI_TalonSRX(RobotMap.CLIMB_DRIVE_ID);

  @Override
  public void initDefaultCommand() {
    
    setDefaultCommand(new DriveClimberWithJoystick());
  }
}
