/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.DriveWithJoysticksCommand;

/**
 * The general drive train class for all various drive train styles. Methods are
 * inherited and overridden by the individual DT classes. All methods present in
 * any DT class should also exist here.
 */

public class GenericDriveTrain extends Subsystem {

    
    // //For WCD only
    // SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(frontLeftMotor, backLeftMotor);
    // SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(frontRightMotor, backRightMotor);

    
    //Methods of Generic class are common to all drive train types. 
    //Specific method code is unique to each drive train type.
    public void initDefaultCommand() {
        
    	//All drive trains have default command to drive with the joysticks
        setDefaultCommand(new DriveWithJoysticksCommand());
    }
    
    public void drive(double leftJoyX, double leftJoyY, double leftJoyZ, double rightJoyX, double rightJoyY, double rightJoyZ) {}
    
    public void autoDrive(double speed, double angle) {}
}
