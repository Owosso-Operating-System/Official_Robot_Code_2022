// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.DriveTrain;

/** Add your docs here. */

/** Class: PIDMath
   * Creates PID caommand.
   * The class calculates the direction needed to face foreward.
   *  */

public class PIDMath {

    public static double getTurnSpeed(DriveTrain driveTrain, int setAngle){
    
    double kP = 0.003;
    double kI = 0.001;
    double kD = 0.005;

    double proportional;
    double integral;
    double derivative;

    double kAngleSetpoint = setAngle;
    double speedLimit = 0.25;

    double error = 0;
    double totalError = 0;
    double lastError = 0;

    error = kAngleSetpoint - driveTrain.gyro.getAngle();
    totalError += error;

    proportional = error * kP;
    integral = totalError * kI;
    derivative = (error - lastError) * kD;

    double output = proportional + integral + derivative;

    speedLimit = Math.copySign(speedLimit, output);

    double turnSpeed = output > speedLimit ? speedLimit : output;

    return turnSpeed;
    }
    
}
