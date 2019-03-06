/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;

public class AutoDriveOffHab extends CommandGroup {

  /**
   * This command group drives the robot off the hab.
   */
  public AutoDriveOffHab() {
    
    //Drive off Hab at full speed
    addSequential(new ArmToPositionCommand(RobotMap.hatchLevel1Revs));
    addSequential(new AutoDrive(90, 0, 2, 0.5));
    addSequential(new AutoDrive(90, 0, .5, 1.0));
    addSequential(new AutoDrive(-180, 0, 1.5, 1.0));

  }
}
