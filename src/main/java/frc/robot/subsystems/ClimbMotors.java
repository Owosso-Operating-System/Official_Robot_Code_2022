// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

 /** Class: ClimbMotors
  * Creates a new Climb Motors.
  */

public class ClimbMotors extends SubsystemBase {

  public static CANSparkMax liftMotor;
  public static CANSparkMax pitchMotor;

  /**Method: ClimbMotors
   * Parameters: None
   * Variables used: none
   * What it does: Assigns the CANSparkMax variables their output ports
   *    */

  public ClimbMotors() {

    liftMotor = new CANSparkMax(5, MotorType.kBrushless);
  //  pitchMotor = new CANSparkMax(8, MotorType.kBrushless);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
