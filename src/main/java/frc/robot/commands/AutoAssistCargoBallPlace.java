/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;

public class AutoAssistCargoBallPlace extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoAssistCargoBallPlace() {

    addSequential(new AutoAssistFindAngle());
    //addSequential(new AutoRotate(RobotMap.VISION_TARGET_ANGLE, 2, 0.4, true));
    addSequential(new AutoAssistAlignRobotToTarget(0.3));
    addSequential(new AutoDriveToVisionDistance(90, RobotMap.VISION_TARGET_ANGLE, 26.0, 1.25, 0.3, true));
    addSequential(new ArmToPositionCommand(RobotMap.cargoBallReleaseRevs));
    addSequential(new ShootOutBall(1.25));
    addSequential(new ArmToPositionCommand(RobotMap.hatchReleaseLevel1Revs));
    
  }
}
