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

public class ArmToPositionCommand extends Command {
  
  double revolutionsToGo;

  public ArmToPositionCommand(double revolutions) {
    
    requires(Robot.arm);

    revolutionsToGo = revolutions;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.arm.runToPosition(revolutionsToGo);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    
    boolean thereYet = false;
    
    if(Robot.arm.armMotor.getSelectedSensorPosition() / (double) RobotMap.kEncoderPPR > RobotMap.armMaxRevs) //avoid running past hard stop
    {
      thereYet = true;
    }
    else if(Math.abs(revolutionsToGo * RobotMap.kEncoderPPR - Robot.arm.armMotor.getSelectedSensorPosition()) < 50) //check encoder location
    {  
      thereYet = true;
    }

    return thereYet;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {}

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {}

}
