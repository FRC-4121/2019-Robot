/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ShootOutBall extends Command {
  
  Timer timer = new Timer();

  double startTime;
  double stopTime;

  public ShootOutBall(double time) {
    
    requires(Robot.end);

    stopTime = time;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    timer.start();
    startTime = timer.get();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.end.run(RobotMap.OUTTAKE_SPEED);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    
    boolean thereYet = false;
    
    if(stopTime != 0)
    {
      double currentTime = timer.get();

      if(stopTime <= currentTime - startTime)
      {
        thereYet = true;
      }
    }
    
    return thereYet;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {

    Robot.end.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
