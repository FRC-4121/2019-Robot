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

  public boolean initEncoders(){

    //Choose the encoder type and general config
    armMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kTimeoutMs);
    armMotor.setSensorPhase(RobotMap.kSensorPhase);
    armMotor.setInverted(RobotMap.kMotorInvert);

    //Config outputs, nominal and peak
    armMotor.configNominalOutputForward(0, RobotMap.kTimeoutMs);
		armMotor.configNominalOutputReverse(0, RobotMap.kTimeoutMs);
		armMotor.configPeakOutputForward(1, RobotMap.kTimeoutMs);
    armMotor.configPeakOutputReverse(-1, RobotMap.kTimeoutMs);

    armMotor.configAllowableClosedloopError(0, 0, RobotMap.kTimeoutMs);
  
    //Config Position Closed Loop gains in slot0, typically kF stays zero.
		armMotor.config_kF(0, RobotMap.kFf_Arm, RobotMap.kTimeoutMs);
		armMotor.config_kP(0, RobotMap.kP_Arm, RobotMap.kTimeoutMs);
		armMotor.config_kI(0, RobotMap.kI_Arm, RobotMap.kTimeoutMs);
		armMotor.config_kD(0, RobotMap.kD_Arm, RobotMap.kTimeoutMs);

		//Zero the encoder
		armMotor.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);

    return true;
  }

  @Override
  public void initDefaultCommand() {}

  //Run motor command
  public void runAtSpeed(double speed){

    armMotor.set(speed);
  }

  //Halt the arm
  public void stopArm(){
    
    armMotor.set(0.0);
  }

  //Run to a specific # of rotations
  public void runToPosition(double revolutions){

    targetPositionRotations = revolutions * 10.0 * 4096;

		armMotor.set(ControlMode.Position, targetPositionRotations);
  }

  /*
   * Below are individual functions to run the arm to specific
   * heights.  Hatch pickup and release each have an individual height
   * due to mechanism design.  The pairs of hatch functions should be 
   * called in sequence with the input from a limit switch.
   */
  public void pickUpHatch(){

    targetPositionRotations = RobotMap.pickUpHatchAndUnlockHatchLvl1Revs * 10.0 * 4096;
    armMotor.set(ControlMode.Position, targetPositionRotations);
  }

  public void lockHatch(){
    
    targetPositionRotations = RobotMap.lockHatchAndPlaceHatchLvl1Revs * 10.0 * 4096;
    armMotor.set(ControlMode.Position, targetPositionRotations);
  }

  public void placeHatchLvl1(){
    
    targetPositionRotations = RobotMap.lockHatchAndPlaceHatchLvl1Revs * 10.0 * 4096;
    armMotor.set(ControlMode.Position, targetPositionRotations);
  }

  public void unlockHatchLvl1(){

    targetPositionRotations = RobotMap.pickUpHatchAndUnlockHatchLvl1Revs * 10.0 * 4096;
    armMotor.set(ControlMode.Position, targetPositionRotations);
  }

  public void placeHatchLvl2(){

    targetPositionRotations = RobotMap.placeHatchLvl2Revs * 10.0 * 4096;
    armMotor.set(ControlMode.Position, targetPositionRotations);
  }

  public void unlockHatchLvl2(){

    targetPositionRotations = RobotMap.unlockHatchLvl2Revs * 10.0 * 4096;
    armMotor.set(ControlMode.Position, targetPositionRotations);
  }

  public void placeBallCargoShip(){

    targetPositionRotations = RobotMap.placeBallCargoShipRevs * 10.0 * 4096;
    armMotor.set(ControlMode.Position, targetPositionRotations);
  }

  public void placeBallRocketLvl1(){

    targetPositionRotations = RobotMap.placeBallRocketLvl1Revs * 10.0 * 4096;
    armMotor.set(ControlMode.Position, targetPositionRotations);
  }

  public void placeBallRocketLvl2(){

    targetPositionRotations = RobotMap.placeBallRocketLvl2Revs * 10.0 * 4096;
    armMotor.set(ControlMode.Position, targetPositionRotations);
  }
}
