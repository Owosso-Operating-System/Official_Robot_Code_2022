// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/** Class: IntakeMotors
   * Creates new Intake motors.
   *  */

public class IntakeMotors extends SubsystemBase {
  
public static CANSparkMax intake;
public static CANSparkMax belt;
public static CANSparkMax flyWheel;

/**Method: IntakeMotors
   * Parameters: None
   * Variables used: belt, intake, and flywheel.
   * What it does: Assigns the CANSparkMax variables their output ports
   *    */

public IntakeMotors() {
  belt = new CANSparkMax(6, MotorType.kBrushless);
  intake = new CANSparkMax(7, MotorType.kBrushless);
  flyWheel = new CANSparkMax(9, MotorType.kBrushless);
} 


@Override
public void periodic() {
  // This method will be called once per scheduler run
}

@Override
public void simulationPeriodic() {
  // This method will be called once per scheduler run during simulation
}
}
