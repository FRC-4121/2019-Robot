/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autocommands;

import frc.robot.RobotMap;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRobotLeftLevel1FrontHatch extends CommandGroup {
  /**
   * This command group drives off of the left level 1 Hab and places a hatch on the front of the 
   * cargo ship using the vision system.
   */
  public AutoRobotLeftLevel1FrontHatch() {
    
    addSequential(new ArmToPositionCommand(RobotMap.hatchReleaseLevel1Revs));
    addSequential(new AutoDrive(90, 0, .5, 0.3));
    addSequential(new AutoDrive(90, 0, 2.2, .6));
    addSequential(new AutoSlewToTarget(20, 0.7, 5.0));
    addSequential(new AutoAssistHatchPlace());
  }
}
