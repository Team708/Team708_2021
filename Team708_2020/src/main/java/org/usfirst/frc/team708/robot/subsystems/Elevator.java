package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.elevator.ElevatorHold;
import org.usfirst.frc.team708.robot.commands.elevator.JoystickMoveElevator;

import java.lang.invoke.ConstantCallSite;

import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANDigitalInput.LimitSwitch;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Elevator extends Subsystem {
	
	private CANSparkMax 			elevatorMotor;
	private CANEncoder 				elevatorEncoder;
	private CANDigitalInput 	upperLimit, lowerLimit;
	private CANPIDController	elevatorPIDController;

	public double  elev_position = 0.0;

	// public boolean lvl0CG = false;
	// public boolean lvl1CG = false;
	// public boolean lvl2CG = false;
	// public boolean lvl3CG = false;
	// public boolean cargoshipCG = false;
	// public boolean feederCG = false;
	
    /**
      * Constructor
      */
	public Elevator() {

		elevatorMotor 		= new CANSparkMax(RobotMap.elevatorMotorMaster,MotorType.kBrushless);
		elevatorEncoder 	= new CANEncoder(elevatorMotor);
		
    upperLimit = new CANDigitalInput(elevatorMotor, LimitSwitch.kForward, LimitSwitchPolarity.kNormallyOpen);
    lowerLimit = new CANDigitalInput(elevatorMotor, LimitSwitch.kReverse, LimitSwitchPolarity.kNormallyOpen);
		
		upperLimit.enableLimitSwitch(true);
		lowerLimit.enableLimitSwitch(true);
		elevatorMotor.setIdleMode(IdleMode.kBrake);

		elevatorEncoder.setPosition(Constants.ELEV_ENC_STARTING_POSITION);
		elevatorPIDController = elevatorMotor.getPIDController();
		elevatorPIDController.setP(Constants.ELEV_P);
    elevatorPIDController.setI(Constants.ELEV_I);
		elevatorPIDController.setD(Constants.ELEV_D);
		// elevatorPIDController.setP(SmartDashboard.getNumber("P", 0.0));
    // elevatorPIDController.setI(SmartDashboard.getNumber("I", 0.0));
		// elevatorPIDController.setD(SmartDashboard.getNumber("D", 0.0));
    // elevatorPIDController.setIZone(SmartDashboard.getNumber("Iz", 0.0));
    elevatorPIDController.setIZone(Constants.ELEV_Iz);
		elevatorPIDController.setFF(Constants.ELEV_FF);
		elevatorPIDController.setOutputRange(Constants.ELEVATOR_MOTOR_DOWN, Constants.ELEVATOR_MOTOR_UP);
	}
	
	public void initDefaultCommand() {
		// setDefaultCommand(new JoystickMoveElevator());
		setDefaultCommand(new ElevatorHold());
  }
	
	public void manualMove(double speed) {
		elevatorMotor.set(speed);
	}
	
	public void moveMotor(double speed) {
		elevatorMotor.set(speed);
	}
	    
	public boolean isElevatorMin() {
		if (lowerLimit.get()) {
			elevatorEncoder.setPosition(Constants.ELEV_ENC_MIN);
			resetElevatorEncoder();

			return (true);
		}  
		else
			return (false);
	}

	public boolean isElevatorMax() {
//  if (upperLimit.get()) {
		if (elevatorEncoder.getPosition() > Constants.ELEV_MAX) {
			return (true);
	    }
		else 
			return (false);
	}
	
  public double getEncoderDistance() {
     return elevatorEncoder.getPosition();
  }
   
  public void resetElevatorEncoder() {
		elevatorEncoder.setPosition(Constants.ELEV_ENC_MIN);
		elev_position = Constants.ELEV_ENC_MIN; 
	}

	public boolean elevAtLevel() {
		double difference = Math.abs(getEncoderDistance() - elev_position);
		return difference <= Constants.ELEV_TOLERANCE;
	}

	public void goToPosition(double elevatorLevel) {
		elevatorPIDController.setReference(elevatorLevel, ControlType.kPosition);
	}
	 
	public void sendToDashboard() {
    if (Constants.DEBUG) {
		}
			SmartDashboard.putBoolean("Elev Down:", 	lowerLimit.get());
   		SmartDashboard.putBoolean("Elev Up", 			upperLimit.get());	
			SmartDashboard.putNumber("Elev Distance", elevatorEncoder.getPosition());
			SmartDashboard.putNumber("Elev Set Positon", elev_position);
			// SmartDashboard.putBoolean("Running Level 1", lvl1CG);	
			// SmartDashboard.putBoolean("Running Level 2", lvl2CG);	
			// SmartDashboard.putBoolean("Running Level 3", lvl3CG);	
			// SmartDashboard.putBoolean("Running Feeder", feederCG);


	}
}
