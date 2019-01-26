package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.extraClasses.PIDControl;

public class AutoDrive extends Command {

  double targetAngle;  //drive angle
	double stopTime;  //timeout time
	double angleCorrection, angleError;
	double startTime;
	
	PIDControl pidControl;

	public Timer timer = new Timer();
	
	public int leftEncoderStart;
	public int rightEncoderStart;
	
	//Class constructor
    public AutoDrive(double ang, double time) { //intakes distance, direction, angle, and stop time
    	
    	requires(Robot.drivetrain);
    	
    	//Set local variables
    	targetAngle = ang;
    	stopTime = time;
    	    	
    	//Set up PID control
    	pidControl = new PIDControl(RobotMap.kP_Straight, RobotMap.kI_Straight, RobotMap.kD_Straight);
    	
    	//pidOutput = new PIDOutput() {
//	
//    		@Override
//    		public void pidWrite(double d) {
//    			Robot.driveTrain.autoDrive(direction*RobotMap.AUTO_DRIVE_SPEED - d, direction*RobotMap.AUTO_DRIVE_SPEED + d);
//    		}
//    	};
//    	
//    	pid = new PIDController(0.045, 0.0, 0.0, Robot.oi.MainGyro, pidOutput);    	
//    	pid.setAbsoluteTolerance(RobotMap.ANGLE_TOLERANCE);
//    	pid.setContinuous();
//    	pid.setSetpoint(angle);
    	
    }

    
    // Called just before this Command runs the first time
    protected void initialize() {
    	
        timer.start();
        startTime= timer.get();
        angleCorrection = 0;
        angleError = 0;
        
    }

    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
//    	angleCorrection = pidControl.Run(Robot.oi.MainGyro.getAngle(), targetAngle);
    angleError = Robot.driveAngle.getDouble(0)-targetAngle;
    angleCorrection = RobotMap.kP_Straight*angleError;
    Robot.drivetrain.autoDrive(RobotMap.AUTO_DRIVE_SPEED, 90, .5);    	    	
		SmartDashboard.putString("Angle Correction", Double.toString(angleCorrection));
		SmartDashboard.putString("Angle Error", Double.toString(angleError));

    }

    
    // Make this return true when this Command no longer needs to run execute()
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
    protected void end() {
    
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    
    protected void interrupted() {
    	
    	//Robot.driveTrain.pid.disable();
    	
    }
    
}
