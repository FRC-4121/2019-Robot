/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.extraClasses.PIDControl;

public class ArmLiftSubsystem extends Subsystem {

  public WPI_TalonSRX armMotor = new WPI_TalonSRX (RobotMap.ARM_MOTOR_ID);
  public PIDControl armPID = new PIDControl(RobotMap.kP_Arm, RobotMap.kI_Arm, RobotMap.kD_Arm);

  public double targetPositionRotations;

  public boolean encoderConfig = initEncoders();

  public boolean initEncoders() {

    //Choose the encoder type and general config
    armMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
    armMotor.setSensorPhase(RobotMap.kSensorPhase);
    armMotor.setInverted(RobotMap.kMotorInvert);

    //Config outputs, nominal and peak
    armMotor.configNominalOutputForward(0, RobotMap.kTimeoutMs);
		armMotor.configNominalOutputReverse(0, RobotMap.kTimeoutMs);
		armMotor.configPeakOutputForward(.65, RobotMap.kTimeoutMs);
    armMotor.configPeakOutputReverse(-.35, RobotMap.kTimeoutMs);

    armMotor.configAllowableClosedloopError(RobotMap.kPIDLoopIdx, 1, RobotMap.kTimeoutMs);
  
    //Config Position Closed Loop gains in slot0, typically kF stays zero.
		armMotor.config_kF(RobotMap.kPIDLoopIdx, RobotMap.kFf_Arm, RobotMap.kTimeoutMs);
		armMotor.config_kP(RobotMap.kPIDLoopIdx, RobotMap.kP_Arm, RobotMap.kTimeoutMs);
		armMotor.config_kI(RobotMap.kPIDLoopIdx, RobotMap.kI_Arm, RobotMap.kTimeoutMs);
		armMotor.config_kD(RobotMap.kPIDLoopIdx, RobotMap.kD_Arm, RobotMap.kTimeoutMs);

		//Zero the encoder
		armMotor.setSelectedSensorPosition(RobotMap.ARM_ENCODER_START_POS, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);

    return true;
  }

  @Override
  public void initDefaultCommand() {}

  //Run motor command
  public void runAtSpeed(boolean runUp) {

    if(runUp){
      
      armMotor.set(RobotMap.ARM_SPEED_UP);
    }
    else
    {
      armMotor.set(RobotMap.ARM_SPEED_DOWN);
    }

    
  }

  //Halt the arm (obsolete)
  public void stopArm() {
    
    armMotor.set(RobotMap.STOP_SPEED);
  }

  //Run to a specific # of rotations
  public void runToPosition(double revolutions) {

    targetPositionRotations = revolutions * RobotMap.kEncoderPPR;
    
    armMotor.set(ControlMode.Position, targetPositionRotations);
    
  }

}
