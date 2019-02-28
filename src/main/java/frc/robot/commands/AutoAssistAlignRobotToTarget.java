/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoAssistAlignRobotToTarget extends Command {
  
  double offsetTolerance = 10;
  boolean commandComplete = false;
  
  public AutoAssistAlignRobotToTarget() {
    
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    boolean visionFound = Robot.visionTable.getEntry("FoundVisionTarget").getBoolean(false);
    double visionOffset = Robot.visionTable.getEntry("VisionTargetOffset").getDouble(0);

    Command slewLeft = new AutoDrive(180, 90, 0,0);

    //Only proceed if target has been found
    if(visionFound)
    {
      //Only proceed if offset is greater than tolerance
      if(Math.abs(visionOffset) > offsetTolerance)
      {
        //
        if(visionOffset > 0)
        {
          //slew left
        }
        else
        {
          //slew right
        }
        
      }
      else
      {
        commandComplete = true;
      }

    }
    

    

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

  public double chooseAngle(){

    double angleToUse = 0;

    //Logic tree to determine what angle to orient to based on field orientation

    return angleToUse;
  }
}
