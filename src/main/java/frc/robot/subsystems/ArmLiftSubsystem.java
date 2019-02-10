/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class ArmLiftSubsystem extends Subsystem {

  public CANSparkMax armMotor;
  public CANPIDController armPID;
  public CANEncoder armEncoder;

  public ArmLiftSubsystem(){

    armMotor = new CANSparkMax(RobotMap.ARM_MOTOR, MotorType.kBrushless);
    armPID = armMotor.getPIDController();
    armEncoder = armMotor.getEncoder();

    armPID.setP(RobotMap.kP_Arm);
    armPID.setI(RobotMap.kI_Arm);
    armPID.setD(RobotMap.kD_Arm);
    armPID.setIZone(RobotMap.kIz_Arm);
    armPID.setFF(RobotMap.kFf_Arm);
    armPID.setOutputRange(RobotMap.kMinOutput_Arm, RobotMap.kMaxOutput_Arm);




  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }


  public void runArmMotor(double rotations){

    armPID.setReference(rotations, ControlType.kPosition);
  }
  
}
