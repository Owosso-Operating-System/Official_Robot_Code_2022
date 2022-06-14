// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.PIDMath;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IntakeMotors;

public class TenPointAutonLeft extends CommandBase {

  private final DriveTrain driveTrain;

  /** Creates a new SixPointAuton. */
  public TenPointAutonLeft(DriveTrain driveTrain) {
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
    DriveTrain.gyro.getYaw();
    
    //Moves bot backwards to drop the intake
    driveTrain.mecDrive.driveCartesian(-0.25, 0, 0);
    Timer.delay(0.2);
    //Turns on FlyWheel, Belt, and intake, moves the bot foreward
    driveTrain.mecDrive.driveCartesian(0.25, 0, 0);
    IntakeMotors.flyWheel.set(-1);
    IntakeMotors.intake.set(1);
    IntakeMotors.belt.set(1);
    Timer.delay(2.1);
    //Stops the flyWheel, intake, and belt
    IntakeMotors.flyWheel.set(0);
    IntakeMotors.intake.set(0);
    IntakeMotors.belt.set(0);

    while(true){
      driveTrain.mecDrive.driveCartesian(0, 0, PIDMath.getTurnSpeed(driveTrain, 80));
      if(DriveTrain.gyro.getYaw() > 82.5){
        break;
      }
    }

    //Moves the bot foreward to the tarmack
    driveTrain.mecDrive.driveCartesian(0.25, 0, 0);
    Timer.delay(2.3);
    //moves the bot bsck wards for 0.01 seconds to cancel the bot's inertia
    driveTrain.mecDrive.driveCartesian(-0.25, 0, 0);
    Timer.delay(0.01);
    //Turns bot towards the hubs

    while(true){
      driveTrain.mecDrive.driveCartesian(0, 0, PIDMath.getTurnSpeed(driveTrain, 160));
      if(DriveTrain.gyro.getYaw() < 157.5){
        break;
      }
    }

    //Turns on FlyWheel
    IntakeMotors.flyWheel.set(1);
    Timer.delay(1);
    //Turns on Belt
    IntakeMotors.belt.set(1);
    Timer.delay(1.5);
    //Turns off both FlyWheel and Belt, moves bot backwards
    IntakeMotors.flyWheel.set(0);
    IntakeMotors.belt.set(0);
    driveTrain.mecDrive.driveCartesian(-0.1, 0, 0);
    Timer.delay(1);
    //Turns bot to angle 0, moves bot backwards
    while(true){
      driveTrain.mecDrive.driveCartesian(0, 0, PIDMath.getTurnSpeed(driveTrain, 180));
      if(DriveTrain.gyro.getYaw() > 177.5){
        break;
      }
    }

    driveTrain.mecDrive.driveCartesian(-0.25, 0, 0);
    Timer.delay(5.5);
    //All bot movement ceases, end of SixPointAutonLeft
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
