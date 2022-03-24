// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Climb;
import frc.robot.commands.Drive;
import frc.robot.commands.Intake;
import frc.robot.commands.MaxPointAuton;
import frc.robot.commands.MinPointAuton;
import frc.robot.subsystems.ClimbMotors;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IntakeMotors;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final DriveTrain driveTrain;
  private final IntakeMotors intakeMotors;
  private final ClimbMotors climbMotors;
  public final XboxController controller0;
  public final XboxController controller1;

    /**Method: RobotContainer
   * Parameters: N/A
   * Variables used: DriveTrain, IntakeMotors, and controller 
   * What it does: Creating a new DriveTrain called driveTrain, 
   *               Creating new IntakeMotors called intakeMotors,
   *               and XboxController called controller on port 0
   *               Sets a default command for driveTrain with the 
   *               paramaters driveTrain, intakeMotors, and controller
   *  */

  /** The container for the robot. Contains subsystems, OI devices, and commands. 
   * @param MinPointAuton 
   * @param MaxPointAuton */

  public RobotContainer(Command MinPointAuton, Command MaxPointAuton) {
    driveTrain = new DriveTrain();
    intakeMotors = new IntakeMotors();
    climbMotors = new ClimbMotors();
    controller0 = new XboxController(0);
    controller1 = new XboxController(1);
    
    // Configure the button bindings
    configureButtonBindings();

    driveTrain.setDefaultCommand(new Drive(driveTrain, controller0));       
    climbMotors.setDefaultCommand(new Climb(climbMotors, controller1, controller0));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */

  private void configureButtonBindings() {
    new JoystickButton(controller1, XboxController.Button.kA.value).whenHeld(new Intake(intakeMotors, controller1));
    new JoystickButton(controller1, XboxController.Button.kX.value).whenHeld(new Intake(intakeMotors, controller1));
    new JoystickButton(controller1, XboxController.Button.kY.value).whenHeld(new Intake(intakeMotors, controller1));
    new JoystickButton(controller1, XboxController.Button.kB.value).whenHeld(new Intake(intakeMotors, controller1));

    new JoystickButton(controller1, XboxController.Button.kLeftBumper.value).whenHeld(new Intake(intakeMotors, controller1));
    new JoystickButton(controller1, XboxController.Button.kRightBumper.value).whenHeld(new Intake(intakeMotors, controller1));
  }
  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
 * @return 
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    SmartDashboard.putString("Auto List","MaxPointAuton");
    SmartDashboard.putString("Auto List","MinPointAuton");

    String autoName = SmartDashboard.getString("Auto Selector", "MaxPointAuton");

    switch(autoName){
      case "MaxPointAuton":
        return new MaxPointAuton(driveTrain, 90);
      case "MinPointAuton":
        return new MinPointAuton(driveTrain, 0);
    }
    return null;
  }
}