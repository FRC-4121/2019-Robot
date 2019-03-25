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

public class AutoRobotRightLevel1FrontHatch extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoRobotRightLevel1FrontHatch() {
    
    addSequential(new ArmToPositionCommand(RobotMap.floorRevs));
    addSequential(new AutoDrive(90, 0, .5, 0.5));
    addSequential(new AutoDrive(90, 0, 2, .7));
    addSequential(new AutoSlewToTarget(190, 0.7, 5.0));
    addSequential(new AutoAssistHatchPlace());
  }
}
