/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutoDriveCommandGroup;
import frc.robot.subsystems.GenericDriveTrain;
import frc.robot.subsystems.MecanumDriveTrain;
import frc.robot.subsystems.WestCoastDriveTrain;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  //Declare public fields
	public static int driveType;

 	//Network tables
   public static NetworkTableInstance dataTableInstance;
   public static NetworkTable visionTable;
   public static NetworkTable navxTable;
   public static NetworkTableEntry robotStop;
   public static NetworkTableEntry driveAngle;
   public static NetworkTableEntry yVelocity;
   public static NetworkTableEntry xVelocity;
   public static NetworkTableEntry yDisplacement;
   public static NetworkTableEntry xDisplacement;
   public static NetworkTableEntry zeroGyro;
   public static NetworkTableEntry writeVideo;
  
  //Declare subsystems
  public static GenericDriveTrain drivetrain;

  //Declare sensors and control inputs
	public static OI oi;

	//Declare commands
  private Command autonomousCommand;
  
  //Declare Smart dashboard chooser
	private SendableChooser<Command> chooser;


	/**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
	 */
  @Override
	public void robotInit() {
    
		//Initialize NetworkTables
		dataTableInstance = NetworkTableInstance.getDefault();
		visionTable = dataTableInstance.getTable("vision");
		navxTable = dataTableInstance.getTable("navx");

		//Initialize NetworkTable entries
		robotStop = visionTable.getEntry("RobotStop");
		writeVideo = visionTable.getEntry("WriteVideo");
		driveAngle = navxTable.getEntry("DriveAngle");
		yVelocity = navxTable.getEntry("YVelocity");
		xVelocity = navxTable.getEntry("XVelocity");
		yDisplacement = navxTable.getEntry("YDisplacement");
		xDisplacement = navxTable.getEntry("XDisplacement");
		zeroGyro = navxTable.getEntry("ZeroGyro");

		//Initialize NetworkTable values
		robotStop.setDouble(0.0);
    
    //Declare drive configuration
		/* Allows for simple alteration of drive train type.  
    * 1: West Coast
    * 2: Mecanum 
    * default: West Coast
    */
		driveType = 2;
	  
    //Initialize the proper drive train based on drive type
    switch(driveType) {
		
      case 1: 
        drivetrain = new WestCoastDriveTrain();
        break;
			
      case 2:
        drivetrain = new MecanumDriveTrain();
        break;
			
      default:
        drivetrain = new WestCoastDriveTrain();
		
		}
		
    //Init output-input systems
    oi = new OI();

    //Initialize dashboard choosers
    chooser = new SendableChooser<>();
    chooser.addOption("Auto Test", new AutoDriveCommandGroup());
    //chooser.addObject("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", chooser);
    
	}


  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {

    //robotStop.setDouble(1.0);

  }


  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }


  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    autonomousCommand = chooser.getSelected();

    zeroGyro.setDouble(1.0);
    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (autonomousCommand != null) {
      autonomousCommand.start();
    }
  }


  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }


  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }

    //Zero gyro
    robotStop.setDouble(0.0);
    zeroGyro.setDouble(1.0);
  }


  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    
    // SmartDashboard.putNumber("Front Left Encoder Distance:", Robot.oi.frontLeftEncoder.getDistance());
    // SmartDashboard.putNumber("Front Right Encoder Distance:", Robot.oi.frontRightEncoder.getDistance());
    // SmartDashboard.putNumber("Back Right Encoder Distance:", Robot.oi.backRightEncoder.getDistance());
    // SmartDashboard.putNumber("Back Left Encoder Distance:", Robot.oi.backLeftEncoder.getDistance());
  }

  
  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
