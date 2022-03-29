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
    
    //Moves bot forward
    driveTrain.mecDrive.driveCartesian(0.25, 0, 0);
    Timer.delay(2);
    /*Turns on FlyWheel and Belt, stops the bot's movement, turns off FlyWheel and Belt,
     then turns the bot to setAngle*6, then moves the bot forward*/
    IntakeMotors.flyWheel.set(-1);
    IntakeMotors.belt.set(1);
    driveTrain.mecDrive.driveCartesian(0, 0, 0);
    IntakeMotors.flyWheel.set(0);
    IntakeMotors.belt.set(0);
    while(true){
      driveTrain.mecDrive.driveCartesian(0, 0, PIDMath.getTurnSpeed(driveTrain, setAngle*6));
      if(DriveTrain.gyro.getAngle() < setAngle*6){
        break;
      }
    }
    driveTrain.mecDrive.driveCartesian(0.25, 0, 0);
    Timer.delay(3);
    //Turns bot to setAngle then drives forward
    while(true){
      driveTrain.mecDrive.driveCartesian(0, 0, -PIDMath.getTurnSpeed(driveTrain, setAngle));
      if(DriveTrain.gyro.getAngle() > setAngle){
        break;
      }
    }
    driveTrain.mecDrive.driveCartesian(0.25, 0, 0);
    Timer.delay(0.5);
    //Stops bot, turns on FlyWheel
    driveTrain.mecDrive.driveCartesian(0, 0, 0);
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
      driveTrain.mecDrive.driveCartesian(0, 0, PIDMath.getTurnSpeed(driveTrain, 0));
      if(DriveTrain.gyro.getAngle() < 0){
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
