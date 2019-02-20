/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TestClimbCommand extends Command {
  
  boolean climbUp;

  public TestClimbCommand(boolean runUp) {
 
    requires(Robot.climber);

    climbUp = runUp;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.climber.deployLift(climbUp);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    
    // if(direction > 0) //if wheels are going down (direction = 1), run until the bottom limit
    // {
    //   return Robot.oi.climbBottomLimitSwitch.get();
    // } 
    // else //if wheels are going up (direction = -1), run until the top limit
    // {
    //   return Robot.oi.climbTopLimitSwitch.get();
    // }

    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {

    Robot.climber.stopClimb();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
