// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbMotors;

public class Climb extends CommandBase {
  private final ClimbMotors climbMotors;
  public final XboxController controller1;
  public final XboxController controller0;

  /** Creates a new Climb. 
   * @param controller0 */
  public Climb(ClimbMotors climbMotors,XboxController controller1, XboxController controller0) {
    this.climbMotors = climbMotors;
    this.controller1 = controller1;
    this.controller0 = controller0;

    addRequirements(climbMotors);
  
    // Use addRequirements() here to declare subsystem dependencies.
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(controller1.getRawAxis(4) > 0.1 || controller1.getRawAxis(4) < -0.1){
    //  climbMotors.pitchMotor.set(controller.getRawAxis(4));
    } else if(controller1.getRawAxis(2) > 0.1){
      climbMotors.liftMotor.set(-controller1.getRawAxis(2));
    } else if (controller1.getRawAxis(3) > 0.1){
      climbMotors.liftMotor.set(controller1.getRawAxis(3));
    } else{
      climbMotors.liftMotor.set(0);
      //climbMotors.pitchMotor.set(0);
    }
    if(controller0.getRawAxis(2) > 0.1){
      climbMotors.pitchMotor.set(-controller0.getRawAxis(2));
    }else if(controller0.getRawAxis(3) > 0.1){
      climbMotors.pitchMotor.set(controller0.getRawAxis(3));
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
