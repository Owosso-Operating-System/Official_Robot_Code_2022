// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class TwoPointAuton extends CommandBase {
  
  public final int oneFoot = 161;
  private final DriveTrain driveTrain;

  boolean timeUp = false;

  /** Creates a new MinPointAuton. */
  public TwoPointAuton(DriveTrain driveTrain) {
    this.driveTrain = driveTrain;
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
  
    //Bot moves forwards
    driveTrain.mecDrive.driveCartesian(0.25, 0, 0);
    Timer.delay(0.75);
    //Bot ceases movement, end of TwoPointAuton
    driveTrain.mecDrive.driveCartesian(0, 0, 0);
    Timer.delay(14.25);
    isFinished();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
