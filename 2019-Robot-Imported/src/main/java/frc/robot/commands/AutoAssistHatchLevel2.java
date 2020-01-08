/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;

public class AutoAssistHatchLevel2 extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoAssistHatchLevel2() {

    addSequential(new AutoAssistFindAngle());
    //addSequential(new AutoRotate(RobotMap.VISION_TARGET_ANGLE, .5, 0.7, true));
    addSequential(new AutoAssistAlignRobotToTarget(0.51));
    addSequential(new ArmToPositionCommand(RobotMap.rocket2HatchRevs));
    addSequential(new AutoDriveToLimitSwitch(90, RobotMap.VISION_TARGET_ANGLE, 4, 0.25, true));
    addSequential(new ArmToPositionCommand(RobotMap.floorRevs));
    addSequential(new AutoDrive(-90, RobotMap.VISION_TARGET_ANGLE, 1.25, 0.25));

  }
}
