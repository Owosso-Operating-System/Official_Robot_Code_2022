// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeMotors;

/** Class: Intake
   * Creates a new Intake Command.
   *  */

public class Intake extends CommandBase {
 
  public final XboxController controller;

  /**Method: Intake
   * Parameters: IntakeMotors, XboxController, and boolean
   * Variables used: intakeMotors, controller, and hatchOrIntake
   * What it does: Assigns the parameter intakeMotors to intakeMotors
   *               Assigns the parameter controller to controller
   *               Uses addRequirements to tie IntakeMotors to Intake            
   *  */

  public Intake(IntakeMotors intakeMotors, XboxController controller) {
    this.controller = controller;
    addRequirements(intakeMotors);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Right bumper moves the flyWheel out
    if(controller.getRightBumper() == true){
      IntakeMotors.flyWheel.set(1);
    }
    //both controllers start buttons moves the flyWheel in
    if(controller.getStartButton() == true){
      IntakeMotors.flyWheel.set(-1);
    }
    //X button moves the intake in
    if(controller.getXButton() == true){
       IntakeMotors.intake.set(1);
    }
    //A button moves the intake out
    if(controller.getAButton() == true){
       IntakeMotors.intake.set(-1);
    }
    //B button moves the belt up
    if(controller.getBButton() == true){
       IntakeMotors.belt.set(1);
    }
    //Y button moves the belt down
    if(controller.getYButton() == true) {
       IntakeMotors.belt.set(-1);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    IntakeMotors.belt.set(0);
    IntakeMotors.intake.set(0);
    IntakeMotors.flyWheel.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
