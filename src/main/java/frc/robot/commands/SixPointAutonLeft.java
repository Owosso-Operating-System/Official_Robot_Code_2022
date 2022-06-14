// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IntakeMotors;
import frc.robot.PIDMath;

public class SixPointAutonLeft extends CommandBase {
  
  /* Dear fellow programer:
  *  When I wrote this code
  *  only God and I knew how it worked
  *  now only God knows it.
  *  
  *  Therefore, if you are trying to optimize
  *  this code and it fails (most likely)
  *  please increase this counter as a
  *  warning for the next person:
  * 
  *  Total hours wasted here = 10 
  */

  public final int oneFoot = 161;
  private final DriveTrain driveTrain;

  boolean timeUp = false;

  /** Creates a new MaxPointAuton. */
  public SixPointAutonLeft(DriveTrain driveTrain) {
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

    //Turns on FlyWheel in reverse
    IntakeMotors.flyWheel.set(-1);
    Timer.delay(0.5);
    //Turns off FlyWheel and turns until at setAngle
    IntakeMotors.flyWheel.set(0);

    /*while(true){
      driveTrain.mecDrive.driveCartesian(0, 0, PIDMath.getTurnSpeed(driveTrain, -30));
      if(driveTrain.gyro.getYaw() > 27.5){
        break; 
      }
    }*/
    do {
      driveTrain.mecDrive.driveCartesian(0, 0, PIDMath.getTurnSpeed(driveTrain, -30)/3);
    } while (driveTrain.gyro.getYaw() < 29);

    driveTrain.mecDrive.driveCartesian(0, 0, 0);
    //Stops Turning then turns on FlyWheel
    driveTrain.mecDrive.driveCartesian(0, 0, 0);
    IntakeMotors.flyWheel.set(1);
    Timer.delay(1);
    //Turns on Belt
    IntakeMotors.belt.set(1);
    Timer.delay(1.5);
    //Turns off FlyWheel and Belt then moves backwards
    IntakeMotors.flyWheel.set(0);
    IntakeMotors.belt.set(0);
    //Turns back to an angle of 0

    /*while(true){
      driveTrain.mecDrive.driveCartesian(0, 0, PIDMath.getTurnSpeed(driveTrain, 0));
      if(DriveTrain.gyro.getYaw() < -2.5){
        break;
      }
    }*/
    do {
    driveTrain.mecDrive.driveCartesian(0, 0, -PIDMath.getTurnSpeed(driveTrain, 1)/2);
    } while (driveTrain.gyro.getYaw() > -1);

    driveTrain.mecDrive.driveCartesian(0, 0, 0);
    //Moves backwards
    driveTrain.mecDrive.driveCartesian(-0.25, 0, 0);
    Timer.delay(4.5);
    //Stops the bot, end of FourPointAutonLeft
    driveTrain.mecDrive.driveCartesian(0, 0, 0);
    Timer.delay(5.25);
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
