/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class AutoAssistHatchPlace extends CommandGroup {

  /**
   * This command group assists with hatch placement.
   */
  public AutoAssistHatchPlace() {

    //addSequential(new AutoAssistAlignRobotToTarget());
    addParallel(new ArmToPositionCommand(0.25));
    addSequential(new AutoDriveToLimitSwitch(90, RobotMap.VISION_TARGET_ANGLE, 0, 0.4, false));
    addParallel(new ArmToPositionCommand(0.22));
    addSequential(new AutoDrive(-90, 180, 1.25, 0.25));

  }
}
