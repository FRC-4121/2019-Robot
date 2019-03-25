/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autocommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.commands.*;

public class AutoRobotCenterFrontHatch extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoRobotCenterFrontHatch() {
    
    addSequential(new ArmToPositionCommand(RobotMap.floorRevs));
    addSequential(new AutoDrive(90, 0, .5, .7));
    addSequential(new AutoDrive(90, 0, 1.9, .8));
    addSequential(new AutoSlewToTarget(0, 0.7, 1));//maybe comment this out and place robot more specifically
    addSequential(new AutoAssistHatchPlace());
  }
}
