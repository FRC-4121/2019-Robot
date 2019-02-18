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

/**
 * Add your docs here.
 */
public class HabClimberSubsystem extends Subsystem {
  
  // WPI_TalonSRX motor1 = new WPI_TalonSRX(RobotMap.CLIMB_MOTOR_1);
  // WPI_TalonSRX motor2 = new WPI_TalonSRX(RobotMap.CLIMB_MOTOR_2);

  // DoubleSolenoid climbSolenoid = new DoubleSolenoid(0, 1);

  WPI_TalonSRX backRightMotor = new WPI_TalonSRX(RobotMap.BACK_RIGHT_MOTOR);

  @Override
  public void initDefaultCommand() {}

  public void climb(double dir){
    //where we climb, awaiting further instructions

    backRightMotor.set(dir*0.75);


  }
}
