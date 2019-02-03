/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.extraClasses.PIDControl;

public class AutoRotate extends Command {

  double robotAngle;        //angle of the robot with respect to robot front
  double gyroAngle;         //angle read from VMX gyro
  double angleCorrection;   //PID calculated correction factor
  double direction;         //direction to rotate
	double startTime;         //time at start of movement
	double stopTime;          //timeout time
	
	PIDControl pidControl;

	public Timer timer = new Timer();


  //Class constructor
  public AutoRotate(double ang, double time) {

    requires(Robot.drivetrain);

    //Initialize internal variables
    robotAngle = ang;
    stopTime = time;

    //Set up PID control
    pidControl = new PIDControl(RobotMap.kP_Turn, RobotMap.kI_Turn, RobotMap.kD_Turn);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    timer.start();
    startTime= timer.get();
    angleCorrection = 0.0;

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    gyroAngle = Robot.driveAngle.getDouble(0);

    //if (Math.abs(Robot.driveAngle.getDouble(0)) < 358.0)
    //{
    //  gyroAngle = Robot.driveAngle.getDouble(0);
    //}
    //else
    //{
    //  gyroAngle = Robot.driveAngle.getDouble(0) % 360.0;
    //}

    //if (Robot.gyroYaw.getDouble(0) >= 0.0)
    //{
    //  gyroAngle = Robot.gyroYaw.getDouble(0);
    //}
    //else
    //{
    //  gyroAngle = 180.0 + (180 - Math.abs(Robot.gyroYaw.getDouble(0)));
    //}

    angleCorrection = pidControl.Run(gyroAngle, robotAngle);

    Robot.drivetrain.autoDrive(0.0, 0.0, -angleCorrection*0.3);    	    	
        
    SmartDashboard.putString("Angle Correction", Double.toString(angleCorrection));
    SmartDashboard.putString("Gyro Yaw", Double.toString(Robot.gyroYaw.getDouble(0)));
    SmartDashboard.putString("Gyro Angle", Double.toString(gyroAngle));

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {

    boolean thereYet = false;
 
    //Check elapsed time
    if(stopTime<=timer.get()-startTime)
    {
      
      //Too much time has elapsed.  Stop this command.
      thereYet = true;
      
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
