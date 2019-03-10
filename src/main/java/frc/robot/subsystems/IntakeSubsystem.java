/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class IntakeSubsystem extends Subsystem {

  public WPI_TalonSRX intakeMotor = new WPI_TalonSRX(RobotMap.INTAKE_MOTOR_ID);

  @Override
  public void initDefaultCommand() {}

  public void run(double speed){
    
    intakeMotor.set(speed);
  }

  public void stop(){

    intakeMotor.set(RobotMap.STOP_SPEED);
  }
  
}
