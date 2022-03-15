// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.PIDMath;
import frc.robot.subsystems.DriveTrain;

public class MinPointAuton extends CommandBase {
  
  public final int oneFoot = 161;
  private final DriveTrain driveTrain;
  private final int setAngle;

  boolean timeUp = false;

  /** Creates a new TestAuton. */
  public MinPointAuton(DriveTrain driveTrain, int setAngle) {
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
  

    driveTrain.mecDrive.driveCartesian(-0.25, 0, PIDMath.getTurnSpeed(driveTrain, setAngle));
    Timer.delay(1.50);
    isFinished();

    
    //((Encoder) DriveTrain.lbEncoder).reset();
    //((Encoder) DriveTrain.lfEncoder).reset();
    //((Encoder) DriveTrain.rbEncoder).reset();
    //((Encoder) DriveTrain.rfEncoder).reset();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
  /*  if(driveTrain.lbEncoder.getPosition() + driveTrain.lfEncoder.getPosition() + driveTrain.rbEncoder.getPosition() + driveTrain.rfEncoder.getPosition() / 4 < oneFoot * 3) {
      timeUp = true;
    }*/
    return timeUp;
  }
}
