/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class SandstormCommandGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public SandstormCommandGroup() {

    addSequential(new AutoDrive(90, 1.0, 0, 0.625));
    addSequential(new AutoDrive(90, 0.75, 0, 1.0));
    addSequential(new AutoDrive(190, 1.5, 0, 1.0));
    addSequential(new AutoDrive(90, 0.75, 0, 1.0));

  }
}
