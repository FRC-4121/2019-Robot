/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;

/**
 * Add your docs here.
 */
public class MecanumDriveTrain extends GenericDriveTrain {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

	//Initialize motor controllers needed for all drive trains
  WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(RobotMap.MOTOR_1);
  WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(RobotMap.MOTOR_2);    
  WPI_TalonSRX backLeftMotor = new WPI_TalonSRX(RobotMap.MOTOR_3);
  WPI_TalonSRX backRightMotor = new WPI_TalonSRX(RobotMap.MOTOR_4);
  WPI_TalonSRX strafeMotor = new WPI_TalonSRX(RobotMap.MOTOR_5);

  //Initialize robot drive - mecanum style
  MecanumDrive mecanumDrive = new MecanumDrive(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor);
      
  //Init encoders
  public boolean encoderConfig = initEncoders();

  public boolean initEncoders() {
    
    /*
    * Wiring of mecanum encoders: AndyMark am-3132
    * 
    * There are four wires attached to the encoder: red, black, blue, yellow.
    * 
    * These will be inserted into TWO DIO pins/numbers on the roboRIO
    * 
    * Constructor:  Encoder(A, B, true)
    * 
    * Red: power (5V). Insert into 5V pin of DIO A
    * Black: ground.  Insert into GND pin of DIO A
    * Blue: channel A.  Insert into S pin of DIO A
    * Yellow: channel B.  Insert into S pin of DIO B
    */
    
    Encoder frontLeftEncoder = new Encoder(2, 3, true);
    frontLeftEncoder.setDistancePerPulse(RobotMap.MECANUM_ENCODER_DPP);
    
    Encoder frontRightEncoder = new Encoder(0, 1, true);
    frontRightEncoder.setDistancePerPulse(RobotMap.MECANUM_ENCODER_DPP);
    
    Encoder backLeftEncoder = new Encoder(6, 7, true);
    backLeftEncoder.setDistancePerPulse(RobotMap.MECANUM_ENCODER_DPP);
    
    Encoder backRightEncoder = new Encoder(4, 5, true);
    backRightEncoder.setDistancePerPulse(RobotMap.MECANUM_ENCODER_DPP);
    
    return true;
  }

  @Override
  public void drive(double leftJoyX, double leftJoyY, double leftJoyZ, double rightJoyX, double rightJoyY, double rightJoyZ) {
    
    mecanumDrive.driveCartesian(leftJoyY,  leftJoyX,  rightJoyX);
  
  }

}
