/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class AutoAssistHatch extends Command {

  //Declare class level variables
  double stopTime = 0.0;
  double startTime = 0.0;
  CommandGroup hatchCommands;

  public Timer timer = new Timer();

  //Default constructor
  public AutoAssistHatch(double time) {

    requires(Robot.drivetrain);

    //Set variables
    stopTime = time;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    timer.start();
    startTime= timer.get();

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    //Determine which hatch command group to execute
    if (!Robot.oi.hatchLoadedLimitSwitch.get() == false)
    {

      hatchCommands = new AutoAssistHatchPickup();

    }
    else
    {

      hatchCommands = new AutoAssistHatchPlace();

    }

    //Start hatch commands running
    hatchCommands.start();

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {

    //Initialize return flag
    boolean thereYet = false;

    //Determine if all commands have executed
    if (hatchCommands.isCompleted())
    {

      thereYet = true;

    }
    else
    {

      //Too much time has elapsed.  Stop this command.
      if(stopTime <= timer.get() - startTime)
      {

        thereYet = true;

      }

    }

    //Return flag
    return thereYet;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {

    hatchCommands.close();
    
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
