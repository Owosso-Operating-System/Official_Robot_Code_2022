// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbMotors;

public class Climb extends CommandBase {
  public final XboxController controller1;
  public final XboxController controller0;

  /** Creates a new Climb. 
   * @param controller0 */

  public Climb(ClimbMotors climbMotors, XboxController controller0, XboxController controller1) {
    this.controller1 = controller0;
    this.controller0 = controller1;

    addRequirements(climbMotors);
  
    // Use addRequirements() here to declare subsystem dependencies.
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.

  /* Ties the joysticks and the triggers to their motors
  */

  @Override
  public void execute() {
    //controller 1
     if(controller1.getRawAxis(2) > 0.1){
      ClimbMotors.liftMotor.set(-controller1.getRawAxis(2));
    } else if (controller1.getRawAxis(3) > 0.1){
      ClimbMotors.liftMotor.set(controller1.getRawAxis(3));
    } else{
      ClimbMotors.liftMotor.set(0);
    }

    //controller 0
    if(controller0.getRawAxis(2) > 0.1){
      ClimbMotors.pitchMotor.set(-controller0.getRawAxis(2)/4);
    }else if(controller0.getRawAxis(3) > 0.1){
      ClimbMotors.pitchMotor.set(controller0.getRawAxis(3)/3);
    }else{
      ClimbMotors.pitchMotor.set(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    ClimbMotors.liftMotor.set(0);
    ClimbMotors.pitchMotor.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
