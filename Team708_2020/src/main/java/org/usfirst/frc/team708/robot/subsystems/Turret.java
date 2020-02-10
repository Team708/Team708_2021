package org.usfirst.frc.team708.robot.subsystems;
import org.usfirst.frc.team254.lib.util.math.Rotation2d;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.ControlMode;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.subsystems.*;
import edu.wpi.first.wpilibj.Solenoid;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Turret extends Subsystem {


    public TalonSRX turretMotor;
    public boolean useLimelight = true;

    int turretEncoderReverseFactor = 1;
    int[] adjusted = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    int nextsample = 0;
    int samplerate = 9;
    boolean ignorePigeon = false;
    double onedegree = Constants.TURRET_ENCODER_COUNT / 360;

    public Turret() {

        turretMotor = new TalonSRX(RobotMap.kturretMotor);
        turretMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
    	turretMotor.setSensorPhase(true);
    	turretMotor.setInverted(false);
    	turretMotor.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 10, 10);
    	turretMotor.enableVoltageCompensation(true);
        turretMotor.setNeutralMode(NeutralMode.Brake);

        // turretMotor.configForwardSoftLimitEnable(true);
        // turretMotor.configReverseSoftLimitEnable(true);
        // talon_.setForwardSoftLimit(Constants.kSoftMaxTurretAngle / (360.0 * Constants.kTurretRotationsPerTick));
        // talon_.setReverseSoftLimit(Constants.kSoftMinTurretAngle / (360.0 * Constants.kTurretRotationsPerTick));
        // turretMotor.configForwardSoftLimitThreshold(forwardSensorLimit);

    	turretMotor.configVoltageCompSaturation(12, 10);
    	turretMotor.configNominalOutputForward(0.0, 10);
    	turretMotor.configNominalOutputReverse(0.0, 10);
    	turretMotor.configAllowableClosedloopError(0, 0, 10);
    	turretMotor.configMotionAcceleration((int)(Constants.TURRET_ROTATION_MAX_SPEED*5.0), 10);  // jnp
    	turretMotor.configMotionCruiseVelocity((int)(Constants.TURRET_ROTATION_MAX_SPEED*5.0), 10);//  jnp
    	turretMotor.selectProfileSlot(0, 0);
    	turretMotor.config_kP(0, 2.1, 10);//4
    	turretMotor.config_kI(0, 0, 10);
    	turretMotor.config_kD(0, 21, 10);//80
    	turretMotor.config_kF(0, (.75*1023.0)/Constants.TURRET_ROTATION_MAX_SPEED, 10);
		turretMotor.set(ControlMode.MotionMagic, turretMotor.getSelectedSensorPosition(0));
    }

    public synchronized void updateAngle() {
        // turretMotor.set(ControlMode.MotionMagic, angle / (2 * Math.PI * Constants.TURRET_ENCODER_COUNT));
        int normalized = 0;
        if (useLimelight){
            if (nextsample < samplerate) nextsample += 1; else nextsample=0; 
            adjusted[nextsample] = (int) -Robot.visionprocessor.getRotate();
            for (int i = 0; i < samplerate; i++) {
                normalized += adjusted[i]; 
            }
            normalized = normalized / samplerate;
            normalized+=10; //camera error;
            // if (Math.abs(normalized) <= 5) adjusted = 0;
        }

        turretMotor.set(ControlMode.MotionMagic, ((Robot.swerve.getPigeonRotation() + normalized) * onedegree));
        
        // SmartDashboard.putNumber("turret_from", ((turretMotor.getSelectedSensorPosition(0)/Constants.TURRET_ENCODER_COUNT) / 360));
        // SmartDashboard.putNumber("turret_to", ((Robot.swerve.getPigeonRotation() + normalized) * onedegree));
        // SmartDashboard.putNumber("turre_Pigeon", ((Robot.swerve.getPigeonRotation() * onedegree)));
        // SmartDashboard.putNumber("turret_Camera", (normalized * onedegree));


    }

    // public void updateTarget(){

    //     if (useLimelight){
    //       adjusted[0] = (int) -Robot.visionprocessor.getRotate();
    //     }
    //     else
    //       adjusted[0] = 0;

    //     turretMotor.set(ControlMode.MotionMagic, ((Robot.swerve.getPigeonRotation() + adjusted[0]) * onedegree));
    // }

    synchronized void reset(Rotation2d actual_rotation) {
        turretMotor.set(ControlMode.MotionMagic, actual_rotation.getRadians() / (2 * Math.PI * Constants.TURRET_ENCODER_COUNT));
    }

    public synchronized Rotation2d getAngle() {
        return Rotation2d.fromRadians(Constants.TURRET_ENCODER_COUNT * turretMotor.getSelectedSensorPosition() * 2 * Math.PI);
    }

    public synchronized boolean getForwardLimitSwitch() {
        return turretMotor.isFwdLimitSwitchClosed()==1 ? true : false;
    }

    public synchronized boolean getReverseLimitSwitch() {
        return turretMotor.isRevLimitSwitchClosed()==1 ? true : false;
    }

    public synchronized double getSetpoint() {
        return turretMotor.getClosedLoopTarget() * Constants.TURRET_ENCODER_COUNT * 360.0;
    }

    private synchronized double getError() {
        return turretMotor.getClosedLoopError() * Constants.TURRET_ENCODER_COUNT * 360.0;
    }

    public synchronized boolean isOnTarget() {
        return (Math.abs(getError()) < Constants.kTurretOnTargetTolerance);
    }

    public synchronized boolean isSafe() {
        return ( turretMotor.getClosedLoopTarget() == 0 && 
             Math.abs(getAngle().getDegrees() * Constants.TURRET_ENCODER_COUNT * 360.0) < 
                                                             Constants.kTurretSafeTolerance);

    }

    public void sendToDashboard() {
        // SmartDashboard.putNumber("turret_error", getError());
        // SmartDashboard.putNumber("turret_angle", getAngle().getDegrees());
        // SmartDashboard.putNumber("Turret Encoder count", turretMotor.getSelectedSensorPosition(0));
        // SmartDashboard.putNumber("turret_setpoint", getSetpoint());
        // SmartDashboard.putBoolean("turret_fwd_limit", getForwardLimitSwitch());
        // SmartDashboard.putBoolean("turret_rev_limit", getReverseLimitSwitch());
        // SmartDashboard.putBoolean("turret_on_target", isOnTarget());
    }

    @Override
    protected void initDefaultCommand() {
        if (Constants.DEBUG) {
		}  
    }
    
    // @Override
    // public void zeroSensors() {
    //     reset(new Rotation2d());
    // }
}