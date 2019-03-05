/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;

public class AutoAssistHatchPickup extends CommandGroup {

  /**
   * This command group assists with hatch pickup.
   */
  public AutoAssistHatchPickup() {
    
    //addSequential(new AutoAssistAlignRobotToTarget());
    addSequential(new ArmToPositionCommand(RobotMap.hatchReleaseLevel1Revs));
    addSequential(new AutoDriveToLimitSwitch(90, 180, 5, 0.4, true));
    addSequential(new ArmToPositionCommand(RobotMap.hatchLevel1Revs));
    addSequential(new AutoDrive(-90, 180, 2.0, 0.25));
    
  }
}
