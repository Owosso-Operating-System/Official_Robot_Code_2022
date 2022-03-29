// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.PIDMath;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IntakeMotors;

public class SixPointAutonLeft extends CommandBase {

  private final DriveTrain driveTrain;
  private final int setAngle;

  /** Creates a new SixPointAuton. */
  public SixPointAutonLeft(DriveTrain driveTrain, int setAngle) {
    this.driveTrain = driveTrain;
    this.setAngle = setAngle;

    addRequirements(driveTrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    driveTrain.mecDrive.setSafetyEnabled(false);
    
    driveTrain.mecDrive.driveCartesian(0.25, 0, 0);
    Timer.delay(2);
    IntakeMotors.flyWheel.set(-1);
    IntakeMotors.belt.set(1);
    driveTrain.mecDrive.driveCartesian(0, 0, 0);
    IntakeMotors.flyWheel.set(0);
    IntakeMotors.belt.set(0);
    while(DriveTrain.gyro.getAngle() < setAngle*6){
      driveTrain.mecDrive.driveCartesian(0, 0, PIDMath.getTurnSpeed(driveTrain, setAngle*6));
    }
    driveTrain.mecDrive.driveCartesian(0.25, 0, 0);
    Timer.delay(3);
    while(DriveTrain.gyro.getAngle() > setAngle){
      driveTrain.mecDrive.driveCartesian(0, 0, -PIDMath.getTurnSpeed(driveTrain, setAngle));
    }
    driveTrain.mecDrive.driveCartesian(0.25, 0, 0);
    Timer.delay(0.5);
    driveTrain.mecDrive.driveCartesian(0, 0, 0);
    IntakeMotors.flyWheel.set(1);
    Timer.delay(1);
    IntakeMotors.belt.set(1);
    Timer.delay(1.5);
    IntakeMotors.flyWheel.set(0);
    IntakeMotors.belt.set(0);
    driveTrain.mecDrive.driveCartesian(-0.1, 0, 0);
    Timer.delay(1);
    while(DriveTrain.gyro.getAngle() < setAngle){
      driveTrain.mecDrive.driveCartesian(0, 0, PIDMath.getTurnSpeed(driveTrain, setAngle));
    }
    driveTrain.mecDrive.driveCartesian(-0.25, 0, 0);
    Timer.delay(5.5);
    driveTrain.mecDrive.driveCartesian(0, 0, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
