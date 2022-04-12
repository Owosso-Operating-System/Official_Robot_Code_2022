// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IntakeMotors;

public class FourteenPointAuton extends CommandBase {

  private final DriveTrain driveTrain;

  /** Creates a new FourteenPointAuton. */
  public FourteenPointAuton(DriveTrain driveTrain) {
    this.driveTrain = driveTrain;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  /*IntakeMotors.flyWheel.set(-1);
  Timer.delay(0.5);
  IntakeMotors.flyWheel.set(0);
  Timer.delay(2);
  IntakeMotors.flyWheel.set(1);
  Timer.delay(1);
  IntakeMotors.belt.set(1);
  Timer.delay(1);
  IntakeMotors.flyWheel.set(0);
  IntakeMotors.belt.set(0);
  driveTrain.mecDrive.driveCartesian(0, 0, -0.5);
  Timer.delay(0.5);
  driveTrain.mecDrive.driveCartesian(1, 0, 0);
  IntakeMotors.intake.set(1);
  IntakeMotors.belt.set(1);
  Timer.delay(1);
  driveTrain.mecDrive.driveCartesian(0, 0, -0.5);
  Timer.delay(0.25);
  driveTrain.mecDrive.driveCartesian(1, 0, 0);
  Timer.delay(2.5);
  driveTrain.mecDrive.driveCartesian(0, 0, -0.5);
  Timer.delay(0.25);
  driveTrain.mecDrive.driveCartesian(0.5, 0, 0);
  Timer.delay(0.5);
  IntakeMotors.belt.set(-1);
  Timer.delay(0.1);
  IntakeMotors.belt.set(0);
  IntakeMotors.flyWheel.set(1);
  Timer.delay(1);
  IntakeMotors.belt.set(1);
  Timer.delay(1);
  IntakeMotors.belt.set(0);
  IntakeMotors.flyWheel.set(0);
  driveTrain.mecDrive.driveCartesian(-0.5, 0, 0);
  Timer.delay(4.4);
  driveTrain.mecDrive.driveCartesian(0, 0, 0);*/

  driveTrain.mecDrive.setSafetyEnabled(false);

  IntakeMotors.flyWheel.set(-1);
  Timer.delay(0.5);
  IntakeMotors.flyWheel.set(0);
  Timer.delay(2); 
  IntakeMotors.flyWheel.set(1);
  Timer.delay(1);
  IntakeMotors.belt.set(1); 
  Timer.delay(1.5);
  IntakeMotors.flyWheel.set(0);
  IntakeMotors.belt.set(0);
  driveTrain.mecDrive.driveCartesian(0, 0, .8);
  Timer.delay(0.5);
  driveTrain.mecDrive.driveCartesian(1, 0, 0);
  IntakeMotors.belt.set(1);
  Timer.delay(1);
  driveTrain.mecDrive.driveCartesian(0, 0, 1);
  Timer.delay(0.3);
  driveTrain.mecDrive.driveCartesian(1, 0, 0);
  Timer.delay(2);
  driveTrain.mecDrive.driveCartesian(0, 0, 1);
  Timer.delay(0.32);
  driveTrain.mecDrive.driveCartesian(1, 0, 0);
  Timer.delay(3);
  driveTrain.mecDrive.driveCartesian(0, 0, 0);
  IntakeMotors.flyWheel.set(1);
  IntakeMotors.belt.set(-.5);
  Timer.delay(1);
  IntakeMotors.belt.set(1);
  Timer.delay(1.5);
  IntakeMotors.flyWheel.set(0);
  IntakeMotors.belt.set(0);
  driveTrain.mecDrive.driveCartesian(-1, 0, 0);
  Timer.delay(0.38);
  driveTrain.mecDrive.driveCartesian(0, 0, 0);
  isFinished();

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