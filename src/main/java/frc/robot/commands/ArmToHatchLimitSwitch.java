/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArmToHatchLimitSwitch extends Command {

  //Declare variables

  //Default constructor
  public ArmToHatchLimitSwitch() {

    requires(Robot.arm);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.arm.runAtSpeed(true);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {

    boolean thereYet = false;

    if (Robot.oi.hatchLoadedLimitSwitch.get() == true)
    {
      thereYet = true;

      //Lock the arm to the stopped height.
      int encoderPosition = Robot.arm.armMotor.getSelectedSensorPosition();
      Robot.arm.armMotor.set(ControlMode.Position, encoderPosition);

    }

    return thereYet;
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
}
