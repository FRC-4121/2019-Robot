/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;

public class AutoRobotLeftCargoSideBall extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoRobotLeftCargoSideBall() {

    addSequential(new ArmToPositionCommand(RobotMap.hatchReleaseLevel1Revs));
    addSequential(new AutoDrive(90, 0, 1.25, 0.4));
    addSequential(new AutoDrive(90, 0, 2.0, 1.0));
    addSequential(new AutoRotate(90, 1.25, 0.5));
    addSequential(new AutoSlewToTarget(180, 0.5, 2.0));
    addSequential(new AutoAssistCargoBallPlace());

  }
}
