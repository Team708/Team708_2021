package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.intake.RetractIntake;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogInput;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Solenoid;

public class Intake extends Subsystem {
	
	private WPI_TalonSRX 	ballMaster;
//	private WPI_TalonSRX 	hatchMaster;
//	private WPI_VictorSPX	ballSlave;

	private Solenoid intakeSolenoid;
	private Solenoid hatchSolenoid;
	private Solenoid beakSolenoid;

	private boolean intakeDeployed	 = true;
	private boolean hatchExtended	 = true;
	private boolean beakClosed		 = true;				

	private AnalogInput 	ballSensor;
	// public DigitalInput 	ballSensor;
	public DigitalInput 	hatchSensor;

	public Intake() {
		ballMaster	= new WPI_TalonSRX(RobotMap.ballIntakeMotorMaster);
		// ballSlave 	= new WPI_VictorSPX(RobotMap.ballIntakeMotorSlave1);

		intakeSolenoid 	= new Solenoid(RobotMap.intake);
		hatchSolenoid	= new Solenoid(RobotMap.hatch);
		beakSolenoid	= new Solenoid(RobotMap.beak);

		intakeSolenoid.set(intakeDeployed);
		hatchSolenoid.set(hatchExtended);

		ballSensor 		= new AnalogInput(RobotMap.ballSensor);
		// ballSensor 		= new DigitalInput(RobotMap.ballSensor);
		hatchSensor 	= new DigitalInput(RobotMap.hatchSensor);

		ballMaster.setNeutralMode(NeutralMode.Brake);

		// ballSlave.follow(ballMaster);
	}
	
	public void initDefaultCommand() {
	}
	
	public void moveMotorBall(double speed) {
		ballMaster.set(speed);
	}
	
	public void stopMotorBall() {
		ballMaster.set(0.0);
	}

	public void moveMotorHatch(double speed) {
		ballMaster.set(-speed);
	}
	
	public void stopMotorHatch() {
		ballMaster.set(0.0);
	}
	
	public boolean hasBall() {		
		// if (!ballSensor.get())
		if (((ballSensor.getVoltage() >= 2) && (ballSensor.getVoltage() <= 5)))
			return (true);	    
		else 
			return (false);	
		// return false;
			
	}

	public boolean hasHatch() {		
		if (!hatchSensor.get())
			return (true);	    
		else 
			return (false);		
	}

 	//Pneumatics
	public void intakeRetract() {
		intakeDeployed = false;
		intakeSolenoid.set(intakeDeployed);
	}

	public void intakeDeploy() {
		intakeDeployed = true;
		intakeSolenoid.set(intakeDeployed);
	}

	public void intakeToggle() {
		intakeDeployed = !intakeDeployed;
		intakeSolenoid.set(intakeDeployed);
	}

	public void hatchRetract() {
		hatchExtended = false;
		hatchSolenoid.set(hatchExtended);
	}
	
	public void hatchExtend() {
		hatchExtended = true;
		hatchSolenoid.set(hatchExtended);
	}

	public void hatchToggle() {
		hatchExtended = !hatchExtended;
		hatchSolenoid.set(hatchExtended);
	}
	public void beakOpen() {
		beakClosed = false;
		beakSolenoid.set(beakClosed);
	}
	
	public void beakClose() {
		beakClosed = true;
		beakSolenoid.set(beakClosed);
	}

	public void beakToggle() {
		beakClosed = !beakClosed;
		beakSolenoid.set(beakClosed);
	}

	public void sendToDashboard() {
		if (Constants.DEBUG) {
		}
		SmartDashboard.putBoolean("Has Hatch:", hasHatch());
		SmartDashboard.putBoolean("Has Ball:", hasBall());
		SmartDashboard.putNumber("Ball Voltage", ballSensor.getVoltage());
		SmartDashboard.putNumber("Ball Voltage", ballSensor.getValue());
		SmartDashboard.putBoolean("Intake Deployed", intakeDeployed);
		SmartDashboard.putBoolean("Beak Closed:", beakClosed);
		SmartDashboard.putBoolean("Hatch Extended:", hatchExtended);
	}  
}