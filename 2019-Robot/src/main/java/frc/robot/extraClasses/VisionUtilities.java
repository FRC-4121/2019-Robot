/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.extraClasses;

/**
 * This class provides helper methods for vision processing
 */
public class VisionUtilities {

    //Declare class level variables
    double[] targetDirections;

    //Default constructor
    public VisionUtilities()
    {

        //Set up target directions for 2019 FRC game
        targetDirections = new double[9];
        targetDirections[0] = 0.0;
        targetDirections[1] = 90.0;
        targetDirections[2] = 180.0;
        targetDirections[3] = -90.0;
        targetDirections[4] = -180.0;
        targetDirections[5] = 28.75;
        targetDirections[6] = 118.75;
        targetDirections[7] = -28.75;
        targetDirections[8] = -118.75;

    }

    //Find Target angle method
    public double FindTargetAngle(double gyroAngle)
    {

        //Declare return variable
        double targetAngle = 0.0;

        //Find shortest distance to known target angles
        double minDistance = 360.0;
        for (int i = 0; i < targetDirections.length; i++)
        {
            double testDistance = Math.abs(gyroAngle - targetDirections[i]);

            if (testDistance < minDistance)
            {
                minDistance = testDistance;
                targetAngle = targetDirections[i];
            }
        }

        //Return target angle
        return targetAngle;

    }
}
