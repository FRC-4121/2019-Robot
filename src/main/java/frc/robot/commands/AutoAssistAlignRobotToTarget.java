/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.extraClasses.VisionUtilities;


public class AutoAssistAlignRobotToTarget extends Command {
  
  //Declare class level variables
  double offsetTolerance = 10;
  boolean commandComplete = false;
  boolean visionFound;
  double visionOffset;
  VisionUtilities visionUtilities;
  Command slewRobot;
  
  public AutoAssistAlignRobotToTarget() {
    
    //Required subsystems
    requires(Robot.drivetrain);

    //Create vision utilities object
    visionUtilities = new VisionUtilities();

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    //Find proper target alignment angle
    RobotMap.VISION_TARGET_ANGLE = visionUtilities.FindTargetAngle(Robot.gyroYaw.getDouble(0));

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    //Declare local variables
    double slewDirection = 0.0;

    //Get vision processing results
    visionFound = Robot.visionTable.getEntry("FoundVisionTarget").getBoolean(false);
    visionOffset = Robot.visionTable.getEntry("VisionTargetOffset").getDouble(0);

    //Only proceed if target has been found
    if(visionFound)
    {
      //Check vision offset
      if(visionOffset > 0)
      {
        //slew left
        slewDirection = 180;
      }
      else
      {
        //slew right
        slewDirection = 0;
      }
      
      slewRobot = new AutoDrive(slewDirection, RobotMap.VISION_TARGET_ANGLE, 0, 0.25);
      slewRobot.start();
      
    }
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    
    //Initialize return flag
    boolean thereYet = false;

    //Check master kill switch
    if (RobotMap.KILL_AUTO_COMMAND == true)
    {

      thereYet = true;
      RobotMap.KILL_AUTO_COMMAND = false;

    }
    else
    {

      if(visionFound)
      {
        if(Math.abs(visionOffset) < offsetTolerance)
        {
          thereYet = true; //We are sufficiently aligned to continue.
        }
      
      }
      else
      {
        thereYet = true; //Vision has been lost.  Stop trying.
      }

    }

    //Return flag
    return thereYet;
    
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {

    //Close the slew command
    slewRobot.close();

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

}
