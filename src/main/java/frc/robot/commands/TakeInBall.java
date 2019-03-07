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

public class TakeInBall extends Command {
 
  Timer timer = new Timer();

  double startTime;
  double stopTime;

  public TakeInBall() {
    
    requires(Robot.end);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
    //Timer is only to make sure the motor stops if a ball rolls away accidentally
    //Use long times as a result
    // timer.start();
    // startTime = timer.get();
    // stopTime = 2;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.end.run(RobotMap.INTAKE_SPEED);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    
    //double currentTime = timer.get();

    //Check if limit switch is tripped or if timed out.  If true, stop command
    return !Robot.oi.ballLimitSwitch.get();
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
