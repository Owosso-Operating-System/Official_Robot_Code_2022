// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;


public class PIDTurn extends CommandBase {

   
  private DriveTrain driveTrain;
  private final int setAngle;
  private final double setSpeed;
  public boolean timeUp = false;

  /** Creates a new PIDTurn. */
  public PIDTurn(DriveTrain driveTrain, int setAngle, double setSpeed) {
    this.driveTrain = driveTrain;
    this.setAngle = setAngle;
    this.setSpeed = setSpeed;

    addRequirements(driveTrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double kP = 0.003;
    double kI = 0.001;
    double kD = 0.005;

    double proportional;
    double integral;
    double derivative;

    double kAngleSetpoint = setAngle;
    double speedLimit = 0.1;

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

   /*  if(driveTrain.gyro.getAngle() >= setAngle){
       timeUp = true;
    }
  */
    driveTrain.mecDrive.driveCartesian(setSpeed, 0, turnSpeed);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timeUp;
  }
}
