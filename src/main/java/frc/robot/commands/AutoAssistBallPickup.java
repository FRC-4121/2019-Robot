/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.extraClasses.PIDControl;

public class AutoAssistBallPickup extends Command {

	double stopTime;
	double startTime;
	double ballCorrection = 0;
  double speedMultiplier;
  double robotAngle;
  double gyroAngle;
  double driveAngle;
  double angleCorrection;
	
  public Timer timer = new Timer();
  public PIDControl pidControl = new PIDControl(RobotMap.kP_Turn, RobotMap.kI_Turn, RobotMap.kD_Turn);


	public AutoAssistBallPickup(double timeout) {
    
    requires(Robot.drivetrain);
    requires(Robot.arm);
		requires(Robot.end);
		stopTime = timeout;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer.start();
		startTime= timer.get();

    speedMultiplier = 0;
    driveAngle = 90;
    angleCorrection = 0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

    boolean ballFound = Robot.foundBall.getBoolean(false);
    
    gyroAngle = Robot.gyroYaw.getDouble(0);
    
    //Only proceed if a ball has actually been found
    if(ballFound)
    {
      robotAngle = Robot.ballAngle.getDouble(0);

      //Run the arm to the floor
      Robot.arm.runToPosition(RobotMap.floorRevs);

      //Start spinning the intake motors (maybe should do this later I don't know)
      Robot.end.intakeMotor.set(RobotMap.INTAKE_SPEED);

      //Check how far away the ball is
      if(Robot.ballScreenPercent.getDouble(0) < 20)
      {
        speedMultiplier = 1; //go faster if the ball is farther away
      }
      else
      {
        speedMultiplier = .75;
      }

      //Run the PID on the ball angle
      if (robotAngle == 180 || robotAngle == -180)
      {
        if (gyroAngle >= 0 && gyroAngle < 179.5)
        {
          angleCorrection = pidControl.Run(gyroAngle, 180.0);
        }
        else if(gyroAngle <= 0 && gyroAngle > -179.5)
        {
          angleCorrection = pidControl.Run(gyroAngle, -180.0);
        }
      }
      else
      {
        angleCorrection = pidControl.Run(gyroAngle, robotAngle);
      }

      //Drive toward the ball
		  Robot.drivetrain.autoDrive(RobotMap.AUTO_DRIVE_SPEED * speedMultiplier, driveAngle, -angleCorrection * .3);
    }

	}

  // Make this return true when this Command no longer needs to run execute()
  
  //Note: currently I am not checking the vision system in the isFinished() because I know that the ball will be lost by
  //the camera before we are ready to intake.  This means that the timer in this command is extremely important, and Nicole
  //will need to pay attention to what the robot is doing so that she can kill the command as necessary.  Unfortunately, balls
  //don't stay in place nearly as well as cubes do.
	protected boolean isFinished() {

    boolean thereYet = false;
    
    //Check kill switch
    if(RobotMap.KILL_AUTO_COMMAND)
    {
      thereYet = true;
    }
    else
    {
    
      //Check limit switch
			if(Robot.oi.ballLimitSwitch.get()) //when cube takes up majority of screen you are done need to change value
			{

				//Ball is in, we are done
				thereYet = true;

			} //Check for timeout
			else if (stopTime<=timer.get()-startTime)
			{

				//Too much time has elapsed.  Stop this command.
				thereYet = true;

			}
			
    }
	
		return thereYet;

	}

	// Called once after isFinished returns true
	protected void end() {
    
    //Stop the intake motor
    Robot.end.stop();

    //Run the arm up slightly
    Robot.arm.runToPosition(RobotMap.hatchPickupLevel1Revs);
    
    //Stop driving
    Robot.drivetrain.robotStop();

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}