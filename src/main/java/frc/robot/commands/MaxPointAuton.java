// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import javax.swing.JList.DropLocation;

import edu.wpi.first.math.Drake;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IntakeMotors;
import frc.robot.PIDMath;

public class MaxPointAuton extends CommandBase {
  
  public final int oneFoot = 161;
  private final DriveTrain driveTrain;
  private final int setAngle;

  boolean timeUp = false;

  /** Creates a new TestAuton. */
  public MaxPointAuton(DriveTrain driveTrain, int setAngle) {
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

  // Math to determin the direction we are going in autonomous.
  @Override
  public void execute() {
    
    
    driveTrain.mecDrive.setSafetyEnabled(false);

    Timer.delay(3);
    driveTrain.mecDrive.driveCartesian(.25, 0, PIDMath.getTurnSpeed(driveTrain, setAngle));
    Timer.delay(1.5);
    //turn Right
    driveTrain.mecDrive.driveCartesian(0, 0, 0.1);
    Timer.delay(0.5);
    driveTrain.mecDrive.driveCartesian(0.1, 0, 0);
    Timer.delay(0.5);
    //turn Left
    /*
    driveTrain.mecDrive.driveCartesian(0, 0, -0.1);
    Timer.delay(0.5);
    driveTrain.mecDrive.driveCartesian(0.1, 0, 0);
    Timer.delay(0.5);
    */
    IntakeMotors.intake.set(-1);
    driveTrain.mecDrive.driveCartesian(0, 0, 0);
    Timer.delay(2.5);
    IntakeMotors.intake.set(0);
    driveTrain.mecDrive.driveCartesian(-0.25, 0, PIDMath.getTurnSpeed(driveTrain, setAngle));
    Timer.delay(3.5);
    driveTrain.mecDrive.driveCartesian(0, 0, 0);
    // Timer.delay(2);
    /*IntakeMotors.intake.set(1);
    Timer.delay(1);
    driveTrain.mecDrive.driveCartesian(0, 0, -0.5);
    IntakeMotors.intake.set(-1);
    Timer.delay(1);
    driveTrain.mecDrive.driveCartesian(0, 0, 0.5);
    IntakeMotors.intake.set(0);
    Timer.delay(2);
   */
    //isFinished();
    /*(Encoder) DriveTrain.lbEncoder).reset();
    ((Encoder) DriveTrain.lfEncoder).reset();
    ((Encoder) DriveTrain.rbEncoder).reset();
    ((Encoder) DriveTrain.rfEncoder).reset();
    driveTrain.gyro.reset();
    */
    
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
  // if(driveTrain.lbEncoder.getPosition() + driveTrain.lfEncoder.getPosition() + driveTrain.rbEncoder.getPosition() + driveTrain.rfEncoder.getPosition() / 4 > oneFoot * 6.1) {
      timeUp = true;
   // }
    return timeUp;
  }
}
