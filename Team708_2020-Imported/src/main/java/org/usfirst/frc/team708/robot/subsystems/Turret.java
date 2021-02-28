package org.usfirst.frc.team708.robot.subsystems;
import org.usfirst.frc.team254.lib.util.math.Rotation2d;

// import com.revrobotics.CANEncoder;
// import com.revrobotics.CANPIDController;
// import com.revrobotics.CANSparkMax;
// import com.revrobotics.ControlType;
// import com.revrobotics.CANSparkMax.IdleMode;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.ControlMode;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.RobotMap;
// import org.usfirst.frc.team708.robot.subsystems.*;
// import edu.wpi.first.wpilibj.Solenoid;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Turret extends Subsystem {


    public TalonSRX turretMotor;
    public boolean useLimelight = true;
    
    int turretEncoderReverseFactor = 1;
    int nextsample = 0;
    int samplerate = 3;
    boolean ignorePigeon = false;
    double onedegree = Constants.TURRET_ENCODER_COUNT / 360;
    double normalized = 0;
    double TURRET_MAX_ROTATION = 360;
    double requestedAngleInEnc = 0;
    double requestedAngleInDegress = 0;
    

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
        //calcuate the angle of the turret and add it to Tx
        // double turretPos  = (turretMotor.getSelectedSensorPosition(0)) / onedegree; // turret is at this degree
        double cameraAngle = Robot.visionprocessor.getRotate();  //target is Tx degrees
        double robotAngle  = Robot.swerve.getPigeonRotation();   //Angle in degrees robot is at
        double turretAngle = (turretMotor.getSelectedSensorPosition(0)) / onedegree; // turret is at this degree

        double rotateToTarget = (turretAngle - cameraAngle);       //calc numberof degrees to target
        double toEncoderCount = (rotateToTarget * onedegree); //% 360     //calc number of encoder tickets for degrees

        if (Robot.intake.inIntakePosition){
                // if (Robot.visionprocessor.seesTarget()) {//  && Math.abs(turretAngle) < TURRET_MAX_ROTATION )
                //     if (!(rotateToTarget > 270 || rotateToTarget < -90))
                //         turretMotor.set(ControlMode.MotionMagic, toEncoderCount);
                //   }  //turn turret to encoder value to find target
                //     else if (Math.abs(robotAngle) <= 15){
                //         turretMotor.set(ControlMode.MotionMagic, (robotAngle * onedegree)+Constants.TURRET_ENCODER_STARTING_POS);
                //     }
        
    
                if (Robot.visionprocessor.seesTarget()) //  && Math.abs(turretAngle) < TURRET_MAX_ROTATION )
                    requestedAngleInEnc = toEncoderCount;
                else
                    // requestedAngleInEnc = (robotAngle * onedegree)+Constants.TURRET_ENCODER_STARTING_POS;
                    turretMotor.set(ControlMode.MotionMagic, Constants.TURRET_ENCODER_STARTING_POS);

                requestedAngleInDegress = requestedAngleInEnc/onedegree;

                if (requestedAngleInDegress > 280 || requestedAngleInDegress < -100){
                    requestedAngleInDegress = (Math.abs(requestedAngleInDegress)-360)*Integer.signum((int)requestedAngleInDegress);
                    requestedAngleInEnc     = requestedAngleInDegress * onedegree;
                }

                if (Robot.shooter.findtarget)
                    turretMotor.set(ControlMode.MotionMagic, requestedAngleInEnc);  //turn turret to encoder value to find target
        }

        // turretMotor.set(ControlMode.MotionMagic, angle / (2 * Math.PI * Constants.TURRET_ENCODER_COUNT));
        // turretMotor.set(ControlMode.MotionMagic, ((Robot.swerve.getPigeonRotation() + normalized) * onedegree));

        SmartDashboard.putBoolean("turret_SeesTarget", Robot.visionprocessor.seesTarget());
        SmartDashboard.putNumber("turret_toEncoderCount", toEncoderCount);
        SmartDashboard.putNumber("turret_Angle", turretAngle);
        SmartDashboard.putNumber("turret_Camera", cameraAngle);
        SmartDashboard.putNumber("turret_Robot_Angle", robotAngle);
        SmartDashboard.putNumber("turret_Rotateto", rotateToTarget);
        SmartDashboard.putNumber("turret_Requested_angle_In_Enc", requestedAngleInEnc);
        SmartDashboard.putNumber("turret_Requested_angle_In_Dec", requestedAngleInDegress);
    }

    public synchronized void resetTurret(){
        turretMotor.set(ControlMode.MotionMagic, Constants.TURRET_ENCODER_STARTING_POS);
        // turretMotor.set(ControlMode.MotionMagic, turretMotor.getSelectedSensorPosition(0));
    }

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
        // SmartDashboard.putNumber("turret Encoder count", turretMotor.getSelectedSensorPosition(0));
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