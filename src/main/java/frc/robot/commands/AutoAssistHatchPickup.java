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

    addSequential(new AutoAssistFindAngle());
    addSequential(new AutoRotate(RobotMap.VISION_TARGET_ANGLE, .5, 0.6, true));
    addSequential(new AutoAssistAlignRobotToTarget(0.35));
    addSequential(new ArmToPositionCommand(RobotMap.hatchReleaseLevel1Revs));
    addSequential(new AutoDriveToLimitSwitch(90, 180, 4, 0.25, true));
    //addSequential(new ArmToPositionCommand(RobotMap.hatchPickupLevel1Revs));
    addSequential(new ArmToHatchLimitSwitch());
    addSequential(new AutoDrive(-90, 180, 2.0, 0.25));
    
  }
}
