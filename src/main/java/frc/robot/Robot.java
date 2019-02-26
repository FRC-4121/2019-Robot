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
import frc.robot.commands.AutoDefaultStraight;
import frc.robot.commands.AutoRobotLeftCargoFront;
import frc.robot.commands.AutoRobotLeftCargoSide;
import frc.robot.commands.AutoRobotRightCargoFront;
import frc.robot.commands.AutoRobotRightCargoSide;
import frc.robot.subsystems.ArmLiftSubsystem;
import frc.robot.subsystems.GenericDriveTrain;
import frc.robot.subsystems.HabClimberSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
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

 	//Network tables and entries
  public static NetworkTableInstance dataTableInstance;
  public static NetworkTable visionTable;
  public static NetworkTable navxTable;
  
  public static NetworkTableEntry driveAngle;
  public static NetworkTableEntry gyroYaw;
  public static NetworkTableEntry gyroPitch;
  public static NetworkTableEntry yVelocity;
  public static NetworkTableEntry xVelocity;
  public static NetworkTableEntry yDisplacement;
  public static NetworkTableEntry xDisplacement;
  public static NetworkTableEntry zeroGyro;
  //public static NetworkTableEntry zeroDisplace;
  
  public static NetworkTableEntry writeVideo;
  public static NetworkTableEntry robotStop;
  public NetworkTableEntry ballDistance;
  public NetworkTableEntry ballAngle;
  public NetworkTableEntry ballOffset;
  public NetworkTableEntry ballScreenPercent;
  public NetworkTableEntry foundBall;
  public NetworkTableEntry tapeOffset;
  public NetworkTableEntry foundTape;
  public NetworkTableEntry visionTargetAngle;
  public NetworkTableEntry visionTargetDistance;
  public NetworkTableEntry visionTargetOffset;
  public NetworkTableEntry foundVisionTarget;
     
  //Declare subsystems
  public static GenericDriveTrain drivetrain;
  public static ArmLiftSubsystem arm;
  public static HabClimberSubsystem climber;
  public static IntakeSubsystem end;

  //Declare sensors and control inputs
	public static OI oi;

	//Declare commands
  private Command autonomousCommand;
  
  //Declare Smart dashboard chooser
  private SendableChooser<String> autoStyleChooser;
  private SendableChooser<String> autoSideChooser;
  private SendableChooser<String> autoTargetChooser;
  
  //Variables for auto logic
  public String mySide;
  public String myTarget;
  public String myStyle;

  
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
    ballOffset = visionTable.getEntry("BallOffset");
    ballDistance = visionTable.getEntry("BallDistance");
    ballAngle = visionTable.getEntry("BallAngle");
    ballScreenPercent = visionTable.getEntry("BallScreenPercent");
    foundBall = visionTable.getEntry("FoundBall");
    tapeOffset = visionTable.getEntry("TapeOffset");
    foundTape = visionTable.getEntry("FoundTape");
    visionTargetDistance = visionTable.getEntry("VisionTargetDistance");
    visionTargetAngle = visionTable.getEntry("VisionTargetAngle");
    visionTargetOffset = visionTable.getEntry("VisionTargetOffset");
    foundVisionTarget = visionTable.getEntry("FoundVisionTarget");
    
    driveAngle = navxTable.getEntry("GyroAngle");
    gyroYaw = navxTable.getEntry("GyroYaw");
    gyroPitch = navxTable.getEntry("GyroPitch");
		yVelocity = navxTable.getEntry("YVelocity");
		xVelocity = navxTable.getEntry("XVelocity");
		yDisplacement = navxTable.getEntry("YDisplacement");
		xDisplacement = navxTable.getEntry("XDisplacement");
    zeroGyro = navxTable.getEntry("ZeroGyro");
    //zeroDisplace = navxTable.getEntry("ZeroDisplace");

		//Initialize NetworkTable values
    robotStop.setDouble(0.0);
    zeroGyro.setDouble(0.0);
    //zeroDisplace.setDouble(0.0);
    
    //Declare drive configuration
		/* Allows for simple alteration of drive train type.  
    * 1: West Coast
    * 2: Mecanum 
    * 2019 default: Mecanum
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
        drivetrain = new MecanumDriveTrain();
		
    }
    
    //Init other subsystems
    arm = new ArmLiftSubsystem();
    end = new IntakeSubsystem();
    climber = new HabClimberSubsystem();
		
    //Init output-input systems
    oi = new OI();

    //Initialize dashboard choosers
    autoSideChooser = new SendableChooser<>();
    autoStyleChooser = new SendableChooser<>();
    autoTargetChooser = new SendableChooser<>();

    autoSideChooser.addOption("Left", "Left");
    autoSideChooser.addOption("Center", "Center");
    autoSideChooser.addOption("Right", "Right");
    
    autoStyleChooser.setDefaultOption("Full Auto", "Auto");
    autoStyleChooser.addOption("Driver Assist", "Sandstorm");

    autoTargetChooser.setDefaultOption("Cargo Front", "Front");
    autoTargetChooser.addOption("Cargo Side", "Side");
    autoTargetChooser.addOption("Straight", "Straight");

    SmartDashboard.putData("Auto Side", autoSideChooser);
    SmartDashboard.putData("Auto Style", autoStyleChooser);
    SmartDashboard.putData("Auto Target", autoTargetChooser);    
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

    SmartDashboard.putBoolean("Hatch Drive Limit", Robot.oi.hatchLimitSwitch.get());
    SmartDashboard.putBoolean("Hatch Loaded Limit", Robot.oi.hatchLoadedLimitSwitch.get());
    SmartDashboard.putBoolean("Ball Limit", Robot.oi.ballLimitSwitch.get());
    //SmartDashboard.putBoolean("Climb Top Limit", Robot.oi.climbTopLimitSwitch.get());
    //SmartDashboard.putBoolean("Climb Bottom Limit", Robot.oi.climbBottomLimitSwitch.get());
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {

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
    
    zeroGyro.setDouble(1.0);
    //zeroDisplace.setDouble(1.0);

    myStyle = autoStyleChooser.getSelected();
    mySide = autoSideChooser.getSelected();
    myTarget = autoTargetChooser.getSelected();
   
    autonomousCommand = null;

    //Logic tree for choosing our auto program
    if(myStyle.equals("Sandstorm"))
    {
      autonomousCommand = null;
    } 
    else 
    {
      if(mySide.equals("Left"))
      {
        if(myTarget.equals("Front"))
        {
          autonomousCommand = new AutoRobotLeftCargoFront();
        } 
        else if(myTarget.equals("Side"))
        {
          autonomousCommand = new AutoRobotLeftCargoSide();
        }
        else
        {
          autonomousCommand = new AutoDefaultStraight();
        }
      }
      else if(mySide.equals("Right"))
      {
        if(myTarget.equals("Front"))
        {
          autonomousCommand = new AutoRobotRightCargoFront();
        }
        else if(myTarget.equals("Side"))
        {
          autonomousCommand = new AutoRobotRightCargoSide();
        }
        else
        {
          autonomousCommand = new AutoDefaultStraight();
        }
      }
      else //Center starting position; may add some more options here in the future
      {
        autonomousCommand = new AutoDefaultStraight();
      }
    }

    // schedule the autonomous command
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

    //Zero gyro and displacement
    robotStop.setDouble(0.0);
    //zeroGyro.setDouble(1.0);
    //zeroDisplace.setDouble(1.0);

  }


  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

  }

  
  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
