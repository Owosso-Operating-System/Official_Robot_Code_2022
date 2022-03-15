// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

  /** Class: DriveTrain
   * Creates a new DriveTrain.
   *  */

public class DriveTrain extends SubsystemBase {

  private final CANSparkMax leftBack;
  private final CANSparkMax leftFront;
  private final CANSparkMax rightBack;
  private final CANSparkMax rightFront; 

     public final MecanumDrive mecDrive;

     public static SPI.Port kGyroPort = SPI.Port.kOnboardCS0;
     public ADXRS450_Gyro gyro = new ADXRS450_Gyro(kGyroPort);

  /**Method: DriveTrain
   * Parameters: None
   * Variables used: leftBack, leftFront, rightBack, rightFront, and mecDrive
   * What it does: Assigns the CANSparkMax variables their output ports
   *               Assigns the Mecanum variable its Spark outputs
   *  */

  public DriveTrain() {

    leftBack = new CANSparkMax( 3, MotorType.kBrushless);
    leftFront = new CANSparkMax( 4, MotorType.kBrushless);
    rightBack = new CANSparkMax( 2, MotorType.kBrushless);
    rightFront = new CANSparkMax( 1, MotorType.kBrushless);

    rightBack.setInverted(true);
    rightFront.setInverted(true);

   mecDrive = new MecanumDrive(leftFront, leftBack, rightFront, rightBack);

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
