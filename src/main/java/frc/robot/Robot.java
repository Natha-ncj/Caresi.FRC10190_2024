package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
  /*
   * Autonomous selection options.
   */
  private static final String kNothingAuto = "do nothing";
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  CANSparkMax driveLeftSparkMaster = new CANSparkMax(4, CANSparkLowLevel.MotorType.kBrushless);
  CANSparkMax driveRightSparkMaster = new CANSparkMax(2, CANSparkLowLevel.MotorType.kBrushless);
  
  CANSparkMax driveLeftSparkSlave = new CANSparkMax(3, CANSparkLowLevel.MotorType.kBrushless);
  CANSparkMax driveRightSparkSlave = new CANSparkMax(1, CANSparkLowLevel.MotorType.kBrushless);
  
  DifferentialDrive drive = new DifferentialDrive(driveLeftSparkMaster, driveRightSparkMaster);
  
  Joystick j = new Joystick(0);
  
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("do nothing", kNothingAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    
    driveLeftSparkMaster.setInverted(false);
    driveLeftSparkSlave.setInverted(false);
    
    driveRightSparkMaster.setInverted(true);
    driveRightSparkSlave.setInverted(true);

    
    driveLeftSparkSlave.follow(driveLeftSparkMaster);
    driveRightSparkSlave.follow(driveRightSparkMaster);
    

    driveLeftSparkMaster.setIdleMode(IdleMode.kBrake);
    driveLeftSparkSlave.setIdleMode(IdleMode.kBrake);
    
    driveRightSparkMaster.setIdleMode(IdleMode.kBrake);
    driveRightSparkSlave.setIdleMode(IdleMode.kBrake);

    
  }

  public void setDriveMotors(double forward, double turn) {
    drive.arcadeDrive(forward*0.7, turn*0.6);
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Time (seconds)", Timer.getFPGATimestamp());
  }

  double autonomousStartTime;
  double autonomousIntakePower;

  @Override
  public void autonomousInit() {
    autonomousStartTime = Timer.getFPGATimestamp();
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    setDriveMotors(-j.getRawAxis(1), -j.getRawAxis(4));
  }
}

