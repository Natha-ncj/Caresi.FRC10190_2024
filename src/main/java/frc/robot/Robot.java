// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
   /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  private Spark leftmotor1 = new Spark(0);
  private Spark leftmotor2 = new Spark(1);
  private Spark rightmotor1 = new Spark(2);
  private Spark rightmotor2 = new Spark(3);

  private Joystick joy1 = new Joystick(0);

  private double startTime; 

  @Override 
  public void robotInit() {}

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    startTime = Timer.getFPGATimestamp();
  }
  @Override
  public void autonomousPeriodic() {
    var time = Timer.getFPGATimestamp();  

if (time - startTime < 3); {

   leftmotor1.set(0.6);
   leftmotor2.set(0.6);
   rightmotor1.set(0.6);
   rightmotor2.set(0.6); 
}  {
  leftmotor1.set(0);
   leftmotor2.set(0);
   rightmotor1.set(0);
   rightmotor2.set(0);
}
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    double speed = -joy1.getRawAxis(1) * 0.6;
    double turn = joy1.getRawAxis(4) * 0.3;
     
    double left = speed + turn; 
    double right = speed - turn; 


    leftmotor1.set(left);
    leftmotor2.set(left);
    rightmotor1.set(-right);
    rightmotor2.set(-right); 
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
