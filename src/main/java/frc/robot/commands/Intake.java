// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.subsystems.IntakeMotors;

/** Class: Intake
   * Creates a new Intake Command.
   *  */

public class Intake extends CommandBase {
 
  private final IntakeMotors intakeMotors;
  public final XboxController controller;
  public final boolean hatchOrIntake;

  public boolean open;

  /**Method: Intake
   * Parameters: IntakeMotors, XboxController, and boolean
   * Variables used: intakeMotors, controller, and hatchOrIntake
   * What it does: Assigns the parameter intakeMotors to intakeMotors
   *               Assigns the parameter controller to controller
   *               Assigns the parameter hatchOrIntake to hatchOrIntake
   *               Uses addRequirements to tie IntakeMotors to Intake            
   *  */

  public Intake(IntakeMotors intakeMotors, XboxController controller, boolean hatchOrIntake) {
    this.intakeMotors = intakeMotors;
    this.controller = controller;
    this.hatchOrIntake = hatchOrIntake;
    addRequirements(intakeMotors);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   /* if(hatchOrIntake){
        System.out.println(intakeMotors.hatchEncoder.getPosition());
      if(intakeMotors.hatchEncoder.getPosition() < 100 ){
        intakeMotors.hatch.set(0.5);
      }
      if(intakeMotors.hatchEncoder.getPosition() > 110 ){
        intakeMotors.hatch.set(-0.1);
      }

      if(intakeMotors.hatchEncoder.getPosition() > 100){
        intakeMotors.hatch.set(0);
      }
    }
    else{*/
     // }
     if(controller.getBButton() == true){
      intakeMotors.intake.set(255);
    }
    if(controller.getAButton() == true){
      intakeMotors.intake.set(-255);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intakeMotors.intake.set(0);
  //  intakeMotors.hatch.set(0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
