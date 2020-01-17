// package org.usfirst.frc.team708.robot.subsystems;

// import org.usfirst.frc.team708.robot.Constants;
// import org.usfirst.frc.team708.robot.Robot;
// import org.usfirst.frc.team708.robot.RobotMap;
// // import org.usfirst.frc.team708.robot.commands.drivetrain.JoystickDrive;
// // import org.usfirst.frc.team708.robot.commands.drivetrain.SwagDrive;
// import org.usfirst.frc.team708.robot.util.IRSensor;
// import org.usfirst.frc.team708.robot.util.UltrasonicSensor;
// import org.usfirst.frc.team708.robot.util.Math708;
// import edu.wpi.first.wpilibj.SpeedControllerGroup;

// import edu.wpi.first.wpilibj.DigitalInput;
// import edu.wpi.first.wpilibj.DoubleSolenoid;
// import edu.wpi.first.wpilibj.Solenoid;

// import com.analog.adis16448.frc.ADIS16448_IMU;
// import edu.wpi.first.wpilibj.command.PIDSubsystem;
// import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANEncoder;
// import com.revrobotics.CANSparkMax.IdleMode;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// public class Drivetrain extends PIDSubsystem {

// 	private CANSparkMax leftMaster, rightMaster, leftSlave1, rightSlave1;
// 	private CANEncoder encoderLeft;
// 	private CANEncoder encoderRight;
	
// 	// Variables specific for drivetrain PID loop
// 	private double moveSpeed = 0.0;
// 	private double pidOutput = 0.0;
	
// 	private double gearratio;
	
// 	private DifferentialDrive drivetrain;						// FRC provided drivetrain class
// 	private double revPerInch;
// 	private boolean gearHigh;
// 	private ADIS16448_IMU gyro;
	
// 	private Solenoid	gearShifter;
	
// 	private IRSensor drivetrainIRSensor;					// IR Sensor for <=25inches
// 	private UltrasonicSensor drivetrainUltrasonicSensor;	// Sonar used for <=21feet
// 	private DigitalInput lineSensor;
	
// 	private boolean brake = true;	
// 	private boolean usePID = false;
// 	public boolean tilting = false;

// 	public boolean runningCG = false;
// 	public boolean runningAuto = false;

	
// 	public Drivetrain() {
// 		// Passes variables from this class into the superclass constructor
// 		super("Drivetrain", Constants.Kp, Constants.Ki, Constants.Kd);
    	
//     // Initializes motor controllers with device IDs from RobotMap
// 		// leftMaster = new CANSparkMax(RobotMap.drivetrainLeftMotorMaster, MotorType.kBrushless);
// 		// leftSlave1 = new CANSparkMax(RobotMap.drivetrainLeftMotorSlave1, MotorType.kBrushless);
// 		// rightMaster = new CANSparkMax(RobotMap.drivetrainRightMotorMaster, MotorType.kBrushless);
// 		// rightSlave1 = new CANSparkMax(RobotMap.drivetrainRightMotorSlave1, MotorType.kBrushless);
		
// 		// SpeedControllerGroup leftMotors = new SpeedControllerGroup(leftMaster, leftSlave1);
// 		// SpeedControllerGroup rightMotors = new SpeedControllerGroup(rightMaster, rightSlave1);
		
// 		// drivetrain = new DifferentialDrive(leftMotors, rightMotors);	// Initializes drivetrain class
		
// 		gyro = new ADIS16448_IMU();
// 		gyro.reset();
		
// 		encoderLeft = new CANEncoder(leftMaster);
// 		encoderRight = new CANEncoder(rightMaster);

// 		// Initializes the sensors
// 		resetEncoder();
// 	}
		
// 	/**
// 	 * Initializes the default command for this subsystem
// 	 */
// 	@Override
// 	public void initDefaultCommand() {
//     setDefaultCommand(new JoystickDrive());
//     }
  		

    
// 	// public synchronized void stop() {
// 	// 	leftMaster.set(Constants.DRIVE_MOTOR_OFF);
// 	// 	rightMaster.set(Constants.DRIVE_MOTOR_OFF);
// 	// }

// 	public double getAngle() {
// 			// return  Math708.round(gyro.getAngleZ(),0);
// 			return 0;
// 	}
	
// 	public synchronized void resetGyro() {
// 		gyro.reset();
//     }
    
	   
// 	public void setBrakeMode(boolean setBrake) {
// 		brake = setBrake;
// 		if (brake) {
// 			leftMaster.setIdleMode(IdleMode.kBrake);
// 			leftSlave1.setIdleMode(IdleMode.kBrake);
// 			rightMaster.setIdleMode(IdleMode.kBrake);
// 			rightSlave1.setIdleMode(IdleMode.kBrake);
// 		} 
// 		else {
// 			leftMaster.setIdleMode(IdleMode.kCoast);
// 			leftSlave1.setIdleMode(IdleMode.kCoast);
// 			rightMaster.setIdleMode(IdleMode.kCoast);
// 			rightSlave1.setIdleMode(IdleMode.kCoast);
// 		}
// 	}
		
// 	/**
// 	 * 
// 	 * @return Distance traveled since last encoder reset
// 	 */
// 	public double getEncoderDistanceLeft() {
// 		return encoderLeft.getPosition() * revPerInch;
// 	}
		
// 	public double getEncoderDistanceRight() {
// 		return encoderRight.getPosition() * revPerInch;
// 	}
	
// 	/**
// 	 * Resets the encoder to 0.0
// 	 */
// 	public void resetEncoder() {
// 		encoderLeft.setPosition(0.0);
// 		encoderRight.setPosition(0.0);
// 	}
	
    
// 	/**
// 	 * Sends data for this subsystem to the dashboard
// 	 */
// 	public void sendToDashboard() {
// 		if (Constants.DEBUG) {
// 		}
// 			SmartDashboard.putNumber("DT Encoder Left Rev", encoderLeft.getPosition());		// Encoder raw count
// 			SmartDashboard.putNumber("DT Encoder Right Rev", encoderRight.getPosition());		// Encoder raw count
// 			SmartDashboard.putNumber("DT Encoder Left Inches", getEncoderDistanceLeft());		// Encoder inches
// 			SmartDashboard.putNumber("DT Encoder Right Inches", getEncoderDistanceRight());		// Encoder inches

// 			SmartDashboard.putNumber("Gyro turn angle", getAngle());
// 			SmartDashboard.putNumber("AllianceColor", Robot.allianceColor);
// 			SmartDashboard.putString("ColorWheel", Robot.ColorWheelStage3);
// 	}
// }
