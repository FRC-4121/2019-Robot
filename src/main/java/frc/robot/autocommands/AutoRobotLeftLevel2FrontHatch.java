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
import frc.robot.commands.AutoAssistHatchPlace;
import frc.robot.commands.AutoDrive;
import frc.robot.commands.AutoSlewToTarget;

public class AutoRobotLeftLevel2FrontHatch extends CommandGroup {

  /**
   * This command group drives a robot off the left hab level 2
   * and places a hatch on the front of the cargo ship.
   */
  public AutoRobotLeftLevel2FrontHatch() {
    
    addSequential(new ArmToPositionCommand(RobotMap.hatchReleaseLevel1Revs));
    addSequential(new AutoDrive(90, 0, 1.25, 0.4));
    addSequential(new AutoDrive(90, 0, 2.0, 1.0));
    addSequential(new AutoSlewToTarget(0, 0.5, 2.0));
    addSequential(new AutoAssistHatchPlace());
    
  }
}
