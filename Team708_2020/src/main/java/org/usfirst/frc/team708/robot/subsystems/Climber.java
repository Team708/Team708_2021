package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.ControlMode;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team708.robot.commands.climber.JoystickMoveClimberFront;


public class Climber extends Subsystem {
	
private WPI_TalonSRX 	climberFrontMaster, climberFrontSlave, climberRearMaster;
private WPI_TalonSRX	climberRoller;
private double countsPerInch;

public double HABLevel = Constants.HAB_LVL3_ENCODER_DISTANCE;
public boolean stage1  = true;
public boolean stage2  = false;
public boolean stage3  = false;
public boolean stage4  = false;
public boolean stage5  = false;
public boolean stage6  = false;
public boolean climbStarted = true;

public Climber() {
	climberFrontMaster		= new WPI_TalonSRX(RobotMap.ClimberLeftFrontMotor);
	climberFrontSlave			= new WPI_TalonSRX(RobotMap.ClimberRightFrontMotor);
	climberRearMaster			= new WPI_TalonSRX(RobotMap.ClimberLeftRearMotor);
	climberRoller					= new WPI_TalonSRX(RobotMap.ClimberRollerMotor);
	
	climberFrontSlave.follow(climberFrontMaster);

	//Encoders
	climberRoller.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
	countsPerInch = (Constants.ROLLER_DIAMETER * Math.PI)/
										(Constants.ROLLER_ENCODER_COUNTS_PER_REV*Constants.ROLLER_GEAR_RATIO);
	
	//Brake
	climberRoller.setNeutralMode(NeutralMode.Brake);
	climberFrontMaster.setNeutralMode(NeutralMode.Brake);
	climberFrontSlave.setNeutralMode(NeutralMode.Brake);
	climberRearMaster.setNeutralMode(NeutralMode.Brake);
}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new JoystickMoveClimber());
    }
	/**
	 * Motion methods for the climber/roller
	 * @param speed
	 */
	public void manualMove(double speed) {
		climberFrontMaster.set(speed);
		climberRearMaster.set(speed);
	}
	
	public void moveFrontMotor(double speed) {
		climberFrontMaster.set(speed);
	}

	public void moveRearMotor(double speed) {
		climberRearMaster.set(speed);
	}
	public void moveRollerMotor(double speed) {
		climberRoller.set(-speed);
	}

	public void stopAll(){
		climberFrontMaster.stopMotor();
		climberRearMaster.stopMotor();
		climberRoller.stopMotor();

	}

	public void stopFront(){
		climberFrontMaster.stopMotor();
	}

	public void stopRear(){
		climberRearMaster.stopMotor();
	}

	public double getEncoderRoller() {
		return climberRoller.getSensorCollection().getQuadraturePosition() * countsPerInch;
	}

	public double getEncoderFront() {
		return -climberFrontMaster.getSensorCollection().getQuadraturePosition();
	}

	public double getEncoderRear() {
		return climberRearMaster.getSensorCollection().getQuadraturePosition();
	}

	public void resetClimberRoller() {
		climberRoller.setSelectedSensorPosition(0, 0, 0);
	}
	public void resetClimberRear() {
		climberRearMaster.setSelectedSensorPosition(0, 0, 0);
	}	
	public void resetClimberFront() {
		climberFrontMaster.setSelectedSensorPosition(0, 0, 0);
	}
	public void resetClimberAll() {
		resetClimberFront();
		resetClimberRear();
		resetClimberRoller();
	}
 

	// Limit Switch Methods
	public boolean frontExtend() {
		// return climberFrontMaster.getSensorCollection().isRevLimitSwitchClosed();	//Used to be Fwd
		return (getEncoderFront() >= HABLevel);
		// return false;
	}
	public boolean frontRetract() {
		// return climberFrontMaster.getSensorCollection().isFwdLimitSwitchClosed(); //Used to be Rev
		return (getEncoderFront() <= 0.0);
		// return false;
	}

	public boolean rearExtend() {
		return (getEncoderRear() >= (HABLevel-1000));
		// return false;
		// return climberRearMaster.getSensorCollection().isRevLimitSwitchClosed(); //Used to be Fwd
	}

	public boolean rearRetract() {
		// return false;
		return (getEncoderRear() <= 0.0);
		// return climberRearMaster.getSensorCollection().isFwdLimitSwitchClosed(); //Used to be Rev
	}
  
	public void sendToDashboard() {
		if (Constants.DEBUG) {
		}
		SmartDashboard.putBoolean("Front Extend", frontExtend());
		SmartDashboard.putBoolean("Front Retract", frontRetract());
		SmartDashboard.putBoolean("Rear Extend",  rearExtend());
		SmartDashboard.putBoolean("Rear Retract",  rearRetract());	
		SmartDashboard.putNumber("HAB Level",	HABLevel);
		SmartDashboard.putBoolean("Stage 1", stage1);
		SmartDashboard.putBoolean("Stage 2", stage2);
		SmartDashboard.putBoolean("Stage 3", stage3);
		SmartDashboard.putBoolean("Stage 4", stage4);
		SmartDashboard.putBoolean("Stage 5", stage5);
		SmartDashboard.putBoolean("Stage 6", stage6);
		SmartDashboard.putNumber("Roller Encoder", getEncoderRoller());		//Encoder raw count
		SmartDashboard.putNumber("Front Encoder", getEncoderFront());		//Encoder raw count
		SmartDashboard.putNumber("Rear Encoder", getEncoderRear());		//Encoder raw count
  } 
}
