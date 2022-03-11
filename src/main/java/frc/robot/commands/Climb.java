// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbMotors;

public class Climb extends CommandBase {
  private final ClimbMotors climbMotors;
  public final XboxController controller;

  /** Creates a new Climb. */
  public Climb(ClimbMotors climbMotors,XboxController controller) {
    this.climbMotors = climbMotors;
    this.controller = controller;
    addRequirements(climbMotors);
  
    // Use addRequirements() here to declare subsystem dependencies.
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(controller.getRawAxis(4) > 0.1 || controller.getRawAxis(4) < -0.1){
    //  climbMotors.pitchMotor.set(controller.getRawAxis(4));
    } else if(controller.getRawAxis(2) > 0.1){
      climbMotors.liftMotor.set(-controller.getRawAxis(2));
    } else if (controller.getRawAxis(3) > 0.1){
      climbMotors.liftMotor.set(controller.getRawAxis(3));
    } else{
      climbMotors.liftMotor.set(0);
      //climbMotors.pitchMotor.set(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climbMotors.liftMotor.set(0);
   // climbMotors.pitchMotor.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
