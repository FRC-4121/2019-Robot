/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;


/**
 * This class defines the standard, 4-wheel mecanum drive train
 */
public class MecanumDriveTrain extends GenericDriveTrain {

	//Initialize motor controllers
  WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(RobotMap.FRONT_LEFT_MOTOR);
  WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(RobotMap.FRONT_RIGHT_MOTOR);    
  WPI_TalonSRX backLeftMotor = new WPI_TalonSRX(RobotMap.BACK_LEFT_MOTOR);
  WPI_TalonSRX backRightMotor = new WPI_TalonSRX(RobotMap.BACK_RIGHT_MOTOR);

  //Initialize robot drive - mecanum style
  MecanumDrive mecanumDrive = new MecanumDrive(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor);

  
  //Initialize encoders
  public boolean encoderConfig = initEncoders();

  
  //Define the encoders for each wheel
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
    * 
    * Pin-out:
    * A: Black, Red, Blue
    * B: Empty, Empty, Yellow
    */
    
    Robot.oi.frontLeftEncoder = new Encoder(0, 1, true, Encoder.EncodingType.k4X);
    Robot.oi.frontLeftEncoder.setDistancePerPulse(RobotMap.MECANUM_ENCODER_DPP);

    Robot.oi.frontRightEncoder = new Encoder(2, 3, true, Encoder.EncodingType.k4X);
    Robot.oi.frontRightEncoder.setDistancePerPulse(RobotMap.MECANUM_ENCODER_DPP);
    
    Robot.oi.backLeftEncoder = new Encoder(4, 5, true, Encoder.EncodingType.k4X);
    Robot.oi.backLeftEncoder.setDistancePerPulse(RobotMap.MECANUM_ENCODER_DPP);
    
    Robot.oi.backRightEncoder = new Encoder(6, 7, true, Encoder.EncodingType.k4X);
    Robot.oi.backRightEncoder.setDistancePerPulse(RobotMap.MECANUM_ENCODER_DPP);
    
    Robot.oi.frontLeftEncoder.setSamplesToAverage(100);
    Robot.oi.backLeftEncoder.setSamplesToAverage(100);
    Robot.oi.frontRightEncoder.setSamplesToAverage(100);
    Robot.oi.backRightEncoder.setSamplesToAverage(100);
    Robot.oi.frontLeftEncoder.reset();
    Robot.oi.frontRightEncoder.reset();
    Robot.oi.backRightEncoder.reset();
    Robot.oi.backLeftEncoder.reset();
  
    return true;
  }


  //Teleop drive method
  @Override
  public void drive(double rightJoyX, double rightJoyY, double rightJoyZ, boolean useGyro) {
    
    if(useGyro) {

      mecanumDrive.driveCartesian(rightJoyX,  rightJoyY,  rightJoyZ, Robot.driveAngle.getDouble(0));
  
    } else {

      mecanumDrive.driveCartesian(rightJoyX,  rightJoyY,  rightJoyZ);

    }
    
    SmartDashboard.putNumber("Front Left Current:", frontLeftMotor.getOutputCurrent());
    SmartDashboard.putNumber("Front Right Current:", frontRightMotor.getOutputCurrent());
    SmartDashboard.putNumber("Back Left Current:", backLeftMotor.getOutputCurrent());
    SmartDashboard.putNumber("Back Right Current:", backRightMotor.getOutputCurrent());
  }


  // WCD autonomous drive method - not used
  @Override
  public void autoDrive(double leftSpeed, double rightSpeed) {
  }

  //Mecanum autonomous drive method
  @Override
  public void autoDrive(double speed, double angle, double rotation){

    mecanumDrive.drivePolar(speed, angle, rotation);

  }

}
