// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IntakeMotors;
import frc.robot.PIDMath;

public class FourPointAutonRight extends CommandBase {
  
  public final int oneFoot = 161;
  private final DriveTrain driveTrain;

  boolean timeUp = false;

  /** Creates a new MaxPointAuton. */
  public FourPointAutonRight(DriveTrain driveTrain) {
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

    //Turns on FlyWheel and sets forward speed
    IntakeMotors.flyWheel.set(-1);
    driveTrain.mecDrive.driveCartesian(0.25, 0, 0);
    Timer.delay(3.5);
    //Sets FlyWheel speed to 0, stops movement of the bot, turns bot to setAngle
    IntakeMotors.flyWheel.set(0);
    driveTrain.mecDrive.driveCartesian(0, 0, 0);
    while(true){
      driveTrain.mecDrive.driveCartesian(0, 0, PIDMath.getTurnSpeed(driveTrain, 30));
      if(DriveTrain.gyro.getAngle() > 27.5){
        break;
      }
    }
    Timer.delay(1);
    //Stops bot movement, slowly moves robot forward
    driveTrain.mecDrive.driveCartesian(0, 0, 0);
    driveTrain.mecDrive.driveCartesian(0.1, 0, 0);
    Timer.delay(0.25);
    //Stops bot movement, turns on FlyWheel
    driveTrain.mecDrive.driveCartesian(0, 0, 0);
    IntakeMotors.flyWheel.set(1);
    Timer.delay(1);
    //Turns on Belt
    IntakeMotors.belt.set(1);
    Timer.delay(1.5);
    //Turns off both FlyWheel and Belt, then reverses the bot slowly
    IntakeMotors.flyWheel.set(0);
    IntakeMotors.belt.set(0);
    driveTrain.mecDrive.driveCartesian(-0.1, 0, 0);
    Timer.delay(1);
    //Stops bot, turns the bot to an angle of 0
    driveTrain.mecDrive.driveCartesian(0, 0, 0);
    while(true){
      driveTrain.mecDrive.driveCartesian(0, 0, PIDMath.getTurnSpeed(driveTrain, 0));
      if(DriveTrain.gyro.getAngle() < -2.5){
        break;
      }
    }
    Timer.delay(1);
    //Bot backs up
    driveTrain.mecDrive.driveCartesian(-0.25, 0, 0);
    Timer.delay(4.5);
    //Bots ceases movement, end of FourPointAutonRight
    driveTrain.mecDrive.driveCartesian(0, 0, 0);
    // Timer.delay(2);
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
