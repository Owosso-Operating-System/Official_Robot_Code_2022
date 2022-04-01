// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IntakeMotors;
import frc.robot.PIDMath;

public class FourPointAutonLeft extends CommandBase {
  
  public final int oneFoot = 161;
  private final DriveTrain driveTrain;

  boolean timeUp = false;

  /** Creates a new MaxPointAuton. */
  public FourPointAutonLeft(DriveTrain driveTrain) {
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
    //Turns on FlyWheel and drives forward
    IntakeMotors.flyWheel.set(-1);
    driveTrain.mecDrive.driveCartesian(0.25, 0, 0);
    Timer.delay(3.5);
    //Turns off FlyWheel, stops driving, then turns until at setAngle
    IntakeMotors.flyWheel.set(0);
    driveTrain.mecDrive.driveCartesian(0, 0, 0);

    while(true){
      driveTrain.mecDrive.driveCartesian(0, 0, PIDMath.getTurnSpeed(driveTrain, -30));
      if(DriveTrain.gyro.getYaw() < -32.5){
        break; 
      }
    }

    //Stops driving then slowly moves forward 
    driveTrain.mecDrive.driveCartesian(0, 0, 0);
    driveTrain.mecDrive.driveCartesian(0.1, 0, 0);
    Timer.delay(0.25);
    //Stops then turns on FlyWheel
    driveTrain.mecDrive.driveCartesian(0, 0, 0);
    IntakeMotors.flyWheel.set(1);
    Timer.delay(1);
    //Turns on Belt
    IntakeMotors.belt.set(1);
    Timer.delay(1.5);
    //Turns off FlyWheel and Belt then moves backwards
    IntakeMotors.flyWheel.set(0);
    IntakeMotors.belt.set(0);
    driveTrain.mecDrive.driveCartesian(-0.1, 0, 0);
    Timer.delay(1);
    //Stops moving then turns back to an angle of 0
    driveTrain.mecDrive.driveCartesian(0, 0, 0);

    while(true){
      driveTrain.mecDrive.driveCartesian(0, 0, PIDMath.getTurnSpeed(driveTrain, 0));
      if(DriveTrain.gyro.getYaw() > -2.5){
        break;
      }

    }
    Timer.delay(1);
    //Moves backwards
    driveTrain.mecDrive.driveCartesian(-0.25, 0, 0);
    Timer.delay(4.5);
    //Stops the bot, end of FourPointAutonLeft
    driveTrain.mecDrive.driveCartesian(0, 0, 0);
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    timeUp = true;
    return timeUp;
  }
}
