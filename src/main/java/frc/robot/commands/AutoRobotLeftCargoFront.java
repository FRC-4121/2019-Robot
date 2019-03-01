/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRobotLeftCargoFront extends CommandGroup {

  /**
   * This command group drives a robot off the left hab level 2
   * and places a hatch on the front of the cargo ship.
   */
  public AutoRobotLeftCargoFront() {
    
    addSequential(new ArmToPositionCommand(0.25));
    addSequential(new AutoDrive(90, 0, 1.25, 0.4));
    addSequential(new AutoDrive(90, 0, 2.0, 1.0));
    addSequential(new AutoDrive(0, 0, 1.5, 0.4));
    addSequential(new AutoAssistHatchPlace());
    
  }
}
