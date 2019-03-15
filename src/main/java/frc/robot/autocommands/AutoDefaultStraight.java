/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autocommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.ArmToPositionCommand;
import frc.robot.commands.AutoDrive;

public class AutoDefaultStraight extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoDefaultStraight() {
    
    //Raise arm slightly and drive off hab. then drive straight for a little bit
    addSequential(new ArmToPositionCommand(RobotMap.hatchReleaseLevel1Revs));
    addSequential(new AutoDrive(90, 0, 1.5, 0.3));
    addSequential(new AutoDrive(90, 0, 1, 0.625));
  }
}
