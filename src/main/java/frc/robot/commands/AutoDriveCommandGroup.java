/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class AutoDriveCommandGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoDriveCommandGroup() {
    
    addSequential(new AutoDrive(90, 1.0, 0));
    addSequential(new AutoDrive(-10, 2.0, 0));
    //addSequential(new AutoDrive(90, 1.0, 0));
    addSequential(new AutoRotate(90, 0.5));
    addSequential(new AutoDrive(90, 1.5, 90));
    addSequential(new AutoRotate(180, 0.5));
    addSequential(new AutoDrive(90, 1.5, 180));
    addSequential(new AutoRotate(270, 0.5));
    addSequential(new AutoDrive(90, 1.5, 270));
    addSequential(new AutoRotate(360, 0.5));
    addSequential(new AutoDrive(90, 0.75, 360));

  }
}
