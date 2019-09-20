/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.ArmLiftSubsystem;
import frc.robot.subsystems.GenericDriveTrain;
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
  
  public static NetworkTableEntry sendVision;
  public static NetworkTableEntry writeVideo;
  public static NetworkTableEntry robotStop;
  public static NetworkTableEntry ballDistance;
  public static NetworkTableEntry ballAngle;
  public static NetworkTableEntry ballOffset;
  public static NetworkTableEntry ballScreenPercent;
  public static NetworkTableEntry foundBall;
  public static NetworkTableEntry tapeOffset;
  public static NetworkTableEntry foundTape;
  public static NetworkTableEntry visionTargetAngle;
  public static NetworkTableEntry visionTargetDistance;
  public static NetworkTableEntry visionTargetOffset;
  public static NetworkTableEntry foundVisionTarget;
     
  //Declare subsystems
  public static GenericDriveTrain drivetrain;
  public static ArmLiftSubsystem arm;
  public static IntakeSubsystem end;

  //Declare sensors and control inputs
	public static OI oi;

	//Declare commands
  private Command autonomousCommand;

  public boolean firstDisabled;
  
  //Auto removed for 2019 season in order to stay out of our teammates' ways in sandstorm
  // //Declare Smart dashboard chooser
  // private SendableChooser<String> autoStyleChooser;
  // private SendableChooser<String> autoPositionChooser;
  // private SendableChooser<String> autoTargetChooser;
  // private SendableChooser<String> autoGamePieceChooser;
  
  // //Variables for auto logic
  // public String myPosition;
  // public String myTarget;
  // public String myStyle = "Sandstorm";
  // public String myGamePiece = "Hatch";

  //Declare driver camera variables
  public static CameraServer camServer;
  public static UsbCamera driverCamera;
  public static UsbCamera ballCamera;
  
	/**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
	 */
  @Override
	public void robotInit() {
    
		//Initialize NetworkTables
		// dataTableInstance = NetworkTableInstance.getDefault();
		// visionTable = dataTableInstance.getTable("vision");
		// navxTable = dataTableInstance.getTable("navx");

    // //Initialize NetworkTable entries
    // sendVision = visionTable.getEntry("SendVision");
		// robotStop = visionTable.getEntry("RobotStop");
    // writeVideo = visionTable.getEntry("WriteVideo");
    // ballOffset = visionTable.getEntry("BallOffset");
    // ballDistance = visionTable.getEntry("BallDistance");
    // ballAngle = visionTable.getEntry("BallAngle");
    // ballScreenPercent = visionTable.getEntry("BallScreenPercent");
    // foundBall = visionTable.getEntry("FoundBall");
    // tapeOffset = visionTable.getEntry("TapeOffset");
    // foundTape = visionTable.getEntry("FoundTape");
    // visionTargetDistance = visionTable.getEntry("VisionTargetDistance");
    // visionTargetAngle = visionTable.getEntry("VisionTargetAngle");
    // visionTargetOffset = visionTable.getEntry("VisionTargetOffset");
    // foundVisionTarget = visionTable.getEntry("FoundVisionTarget");
    
    // driveAngle = navxTable.getEntry("GyroAngle");
    // gyroYaw = navxTable.getEntry("GyroYaw");
    // gyroPitch = navxTable.getEntry("GyroPitch");
		// yVelocity = navxTable.getEntry("YVelocity");
		// xVelocity = navxTable.getEntry("XVelocity");
		// yDisplacement = navxTable.getEntry("YDisplacement");
		// xDisplacement = navxTable.getEntry("XDisplacement");
    // zeroGyro = navxTable.getEntry("ZeroGyro");
    // //zeroDisplace = navxTable.getEntry("ZeroDisplace");

    // //Initialize NetworkTable values
    // sendVision.setBoolean(false);
    // robotStop.setDouble(0.0);
    // zeroGyro.setDouble(0.0);
    // //zeroDisplace.setDouble(0.0);
    // firstDisabled = false;
    
    // //Declare drive configuration
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
		
    //Init output-input systems
    oi = new OI();

    //Initialize dashboard choosers
    // autoPositionChooser = new SendableChooser<>();
    // autoStyleChooser = new SendableChooser<>();
    // autoTargetChooser = new SendableChooser<>();
    // autoGamePieceChooser = new SendableChooser<>();

    // autoPositionChooser.addOption("Left Level 1", "Left Level 1");
    // autoPositionChooser.addOption("Left Level 2", "Left Level 2");
    // autoPositionChooser.addOption("Center Level 1", "Center");
    // autoPositionChooser.addOption("Right Level 1", "Right Level 1");
    // autoPositionChooser.addOption("Right Level 2", "Right Level 2");

    // autoStyleChooser.setDefaultOption("Full Auto", "Auto");
    // autoStyleChooser.addOption("Driver Assist", "Sandstorm");

    // autoTargetChooser.setDefaultOption("Cargo Front", "Front");
    // //autoTargetChooser.addOption("Cargo Side", "Side");
    // autoTargetChooser.addOption("Straight", "Straight");

    // autoGamePieceChooser.setDefaultOption("Hatch", "Hatch");
    // //autoGamePieceChooser.addOption("Hatch", "Hatch");
    // //autoGamePieceChooser.addOption("Ball", "Ball");

    // SmartDashboard.putData("Auto Side", autoPositionChooser);
    // SmartDashboard.putData("Auto Game Piece", autoGamePieceChooser);
    // SmartDashboard.putData("Auto Style", autoStyleChooser);
    // SmartDashboard.putData("Auto Target", autoTargetChooser);
    
    //Initialize and start driver and ball cameras
    camServer = CameraServer.getInstance();
    driverCamera = camServer.startAutomaticCapture("Driver View", 1);
    driverCamera.setResolution(160, 120);
    driverCamera.setFPS(15);
    driverCamera.setBrightness(50);

    ballCamera = camServer.startAutomaticCapture("Ball View", 0);
    ballCamera.setResolution(160, 120);
    ballCamera.setFPS(15);
    ballCamera.setBrightness(50);
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

    SmartDashboard.putBoolean("Hatch Drive Limit", !Robot.oi.hatchLimitSwitch.get());
    SmartDashboard.putBoolean("Hatch Loaded Limit", !Robot.oi.hatchLoadedLimitSwitch.get());
    SmartDashboard.putBoolean("Arm Limit", Robot.oi.armLimitSwitch.get());

    SmartDashboard.putNumber("Arm Current:", Robot.arm.armMotor.getOutputCurrent());
    double encoderValue = (double) Robot.arm.armMotor.getSelectedSensorPosition();
    SmartDashboard.putNumber("Arm Encoder Value: ", encoderValue);
    SmartDashboard.putNumber("Arm Rotations: ", encoderValue / 4096);
    SmartDashboard.putBoolean("Reset Encoders?", RobotMap.RESET_ARM_ENCODER);

    SmartDashboard.putNumber("Target Angle: ", RobotMap.VISION_TARGET_ANGLE);
    SmartDashboard.putBoolean("Kill Auto?", RobotMap.KILL_AUTO_COMMAND);

    SmartDashboard.putString("Gear", RobotMap.TELEOP_SPEED);

    //Using arm limit switch to reset encoders in case of skipping chains.
    //If the limit switch returns true...
    if(Robot.oi.armLimitSwitch.get() == false)
    {
      //...and the reset flag is false...
      if(!RobotMap.RESET_ARM_ENCODER)
      {
        //...reset the encoder and lock
        Robot.arm.armMotor.setSelectedSensorPosition(0);
        Robot.arm.armMotor.set(ControlMode.Position, 0);

        RobotMap.RESET_ARM_ENCODER = true;
      }
    }
    else //if the limit switch moves away, make it reset the next time it comes down.
    {
      RobotMap.RESET_ARM_ENCODER = false;
    }
    
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() 
  {
    // if(!firstDisabled)
    // {
    //   firstDisabled = true;
    // }
    // else
    // {
    //   robotStop.setDouble(1);
    // }
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
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    
    //zeroGyro.setDouble(1.0);

    // myStyle = autoStyleChooser.getSelected();
    // myPosition = autoPositionChooser.getSelected();
    // myGamePiece = autoGamePieceChooser.getSelected();
    // myTarget = autoTargetChooser.getSelected();
   
    autonomousCommand = null;

    //Logic tree for choosing our auto program, currently removed because true auto is not sufficiently
    //advantageous in the 2019 season.  We don't want to autonomously get in the way of our alliance teammates.
    // if(myStyle.equals("Sandstorm") || myStyle.equals(null))
    // {
    //   autonomousCommand = null;
    // } 
    // else 
    // {
    //   if(myPosition.equals("Left Level 1"))
    //   {
    //     if(myTarget.equals("Front"))
    //     {
    //       if(myGamePiece.equals("Hatch"))
    //       {
    //         autonomousCommand = new AutoRobotLeftLevel1FrontHatch();
    //       }
    //       else
    //       {
    //         //Cannot do ball in cargo front position
    //         autonomousCommand = new AutoDefaultStraight();
    //       }
    //     } 
    //     else if(myTarget.equals("Side"))
    //     {
    //       if(myGamePiece.equals("Hatch"))
    //       {
    //         autonomousCommand = new AutoRobotLeftLevel1SideHatch();
    //       }
    //       else
    //       {
    //         autonomousCommand = new AutoRobotLeftLevel1SideBall();
    //       }
    //     }
    //     else
    //     {
    //       autonomousCommand = new AutoDefaultStraight();
    //     }
    //   }
    //   else if (myPosition.equals("Left Level 2"))
    //   {
    //     if(myTarget.equals("Front"))
    //     {
    //       if(myGamePiece.equals("Hatch"))
    //       {
    //         autonomousCommand = new AutoRobotLeftLevel2FrontHatch();
    //       }
    //       else
    //       {
    //         //Cannot do ball in cargo front position
    //         autonomousCommand = new AutoDefaultStraight();
    //       }
    //     } 
    //     else if(myTarget.equals("Side"))
    //     {
    //       if(myGamePiece.equals("Hatch"))
    //       {
    //         autonomousCommand = new AutoRobotLeftLevel2SideHatch();
    //       }
    //       else
    //       {
    //         autonomousCommand = new AutoRobotLeftLevel2SideBall();
    //       }
    //     }
    //     else
    //     {
    //       autonomousCommand = new AutoDefaultStraight();
    //     }
    //   }
    //   else if(myPosition.equals("Right Level 1"))
    //   {
    //     if(myTarget.equals("Front"))
    //     {
    //       if(myGamePiece.equals("Hatch"))
    //       {
    //         autonomousCommand = new AutoRobotRightLevel1FrontHatch();
    //       }
    //       else
    //       {
    //         autonomousCommand = new AutoDefaultStraight();
    //       }
    //     }
    //     else if(myTarget.equals("Side"))
    //     {
    //       if(myGamePiece.equals("Hatch"))
    //       {
    //         autonomousCommand = new AutoRobotRightLevel1SideHatch();
    //       }
    //       else
    //       {
    //         autonomousCommand = new AutoRobotRightLevel1SideBall();
    //       }
    //     }
    //     else
    //     {
    //       autonomousCommand = new AutoDefaultStraight();
    //     }
    //   }
    //   else if(myPosition.equals("Right Level 2"))
    //   {
    //     if(myTarget.equals("Front"))
    //     {
    //       if(myGamePiece.equals("Hatch"))
    //       {
    //         autonomousCommand = new AutoRobotRightLevel2FrontHatch();
    //       }
    //       else
    //       {
    //         autonomousCommand = new AutoDefaultStraight();
    //       }
    //     }
    //     else if(myTarget.equals("Side"))
    //     {
    //       if(myGamePiece.equals("Hatch"))
    //       {
    //         autonomousCommand = new AutoRobotRightLevel2SideHatch();
    //       }
    //       else
    //       {
    //         autonomousCommand = new AutoRobotRightLevel2SideBall();
    //       }
    //     }
    //     else
    //     {
    //       autonomousCommand = new AutoDefaultStraight();
    //     }
    //   }
    //   else //Center starting position
    //   {
    //     if(myTarget.equals("Front"))
    //     {
    //       if(myGamePiece.equals("Hatch"))
    //       {
    //         autonomousCommand = new AutoRobotCenterFrontHatch();
    //       }
    //       else
    //       {
    //         autonomousCommand = new frc.robot.autocommands.AutoDefaultStraight();
    //       }
    //     }
    //     else if(myTarget.equals("Side"))
    //     {
    //       if(myGamePiece.equals("Hatch"))
    //       {
    //         //autonomousCommand = new AutoRobotCenterSideHatch();
    //       }
    //       else
    //       {
    //         //autonomousCommand = new AutoRobotCenterSideBall();
    //       }
    //     }
    //     else
    //     {
    //       autonomousCommand = new AutoDefaultStraight();
    //     }
        
    //   }
    // }

    // // schedule the autonomous command
    // if (autonomousCommand != null) {
    //   autonomousCommand.start();
    // }
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

    //Zero the arm.  If problems ensue during a match, lower the arm all the way down and comment this out later
    Robot.arm.armMotor.setSelectedSensorPosition(0);
    Robot.arm.armMotor.set(ControlMode.Position, 0);

    //Zero gyro and displacement
    //robotStop.setDouble(0.0);
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
