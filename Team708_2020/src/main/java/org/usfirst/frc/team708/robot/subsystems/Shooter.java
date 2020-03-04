package org.usfirst.frc.team708.robot.subsystems;

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
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SpeedControllerGroup;


public class Shooter extends Subsystem {

    public CANSparkMax shooterMotor, shooterMotor2, feederMotor;
    public CANEncoder  shooterEncoder;
    private CANPIDController shooterPIDController;

    public TalonSRX turretMotor;
    public double   targetSpeed;

    public Solenoid hoodSolenoid;
    public boolean  shooterSet = false;
    public boolean  hoodUp     = false;

    private double  feederMotorSpeed       = Constants.kFEEDERMOTORSPEED;
	        int turretEncoderReverseFactor = 1;

    public Shooter(){

        shooterMotor  = new CANSparkMax(RobotMap.kshooterShootMotor,  MotorType.kBrushless);
        shooterMotor2 = new CANSparkMax(RobotMap.kshooterShootMotor2, MotorType.kBrushless);
        feederMotor   = new CANSparkMax(RobotMap.kfeederFeedMotor,    MotorType.kBrushless);

        shooterMotor.setInverted(true);
        shooterMotor2.setInverted(true);

        shooterMotor2.follow(shooterMotor);
        
        feederMotor.setIdleMode(IdleMode.kBrake);

        shooterEncoder = new CANEncoder(shooterMotor);
        shooterPIDController = shooterMotor.getPIDController();
        shooterPIDController.setP(.0005);
        shooterPIDController.setI(0.0000002);
        shooterPIDController.setD(0); //.00006
        shooterPIDController.setFF(.0002);
        shooterPIDController.setIZone(0);
        shooterPIDController.setOutputRange(-1, 1);

<<<<<<< HEAD
        shooterEncoder2 = new CANEncoder(shooterMotor2);
        shooterPIDController2 = shooterMotor2.getPIDController();
        shooterPIDController2.setP(.0005);
        shooterPIDController2.setI(0.0000002);
        shooterPIDController2.setD(0); //.00006
        shooterPIDController2.setFF(.0002);
        shooterPIDController2.setIZone(0);
        shooterPIDController2.setOutputRange(-1, 1);

=======
>>>>>>> parent of ae3b25d... almost working
        hoodSolenoid = new Solenoid(RobotMap.hoodSolenoid);
        hoodSolenoid.set(hoodUp); //Starts with hood down
    }
    
<<<<<<< HEAD
    public void feederOn(){
        // double RPM = determineShooterSpeed(Robot.visionprocessor.getDistance());
        // setTargetSpeed(RPM); //setTargetSpeed(RPM);
        Robot.hopper.moveMotorClockwise();
        if (isShooterAtSpeed()){
            feederMotor.set(feederMotorSpeed);
            Robot.hopper.moveMotorCounterClockwise();
           }   // set feeder motor power
        // else
            // feederMotor.set(0);
            // shooterMotor.set(-speed);        
            // shooterMotor2.set(speed);        
<<<<<<< HEAD
=======
    public void feederOn(double speed){
        if (isShooterAtSpeed())
            feederMotor.set(feederMotorSpeed);   // set feeder motor power
        else
            feederMotor.set(0);
        shooterMotor.set(-speed);        
>>>>>>> parent of ae3b25d... almost working
    }
=======
        }
>>>>>>> parent of 882a475... ohmy-crap after HH

        public void feederOff(){
            feederMotor.set(0);
        }
        
        public void feederSlow(){
            feederMotor.set(-.17);
        }

    public void stopShooter() {
        shooterMotor.stopMotor();
    }

    private void setTargetSpeed(double speed) {
        targetSpeed=speed;
    }
    
    public double determineShooterSpeed(double distance){
       if (distance >= Constants.kHOODANGLE_LONGSHOT) {
           moveHoodUp();
           return(Constants.kSHOOTER_WHEELSPEED_LONG);
        }
        else
        {
           moveHoodDown();
           return(Constants.kSHOOTER_WHEELSPEED_SHORT);
        }
    }

    public void shootAuto(){
        double RPM = determineShooterSpeed(Robot.visionprocessor.getDistance());
        setTargetSpeed(RPM); //setTargetSpeed(RPM);
<<<<<<< HEAD
        // Robot.intake.StopMotorIntake();
        // Robot.hopper.stopMotor();
        // Robot.hopper.moveMotorCounterClockwise();
        // shooterMotor.set(.95);        
        // shooterMotor2.set(.95); 
        shooterPIDController.setReference(RPM, ControlType.kVelocity);
        shooterPIDController2.setReference(RPM, ControlType.kVelocity);
    }

    public boolean isShooterAtSpeed(){
<<<<<<< HEAD
        if ((Math.abs(shooterEncoder.getVelocity())>(targetSpeed)*0.85))// && Math.abs(shooterEncoder.getVelocity())<(targetSpeed)*1.20)
=======
        shooterPIDController.setReference((-RPM), ControlType.kVelocity);  //was -RPM-100
    }

    public boolean isShooterAtSpeed(){
        if ((Math.abs(shooterEncoder.getVelocity())>(targetSpeed)*0.95) && Math.abs(shooterEncoder.getVelocity())<(targetSpeed)*1.05)
>>>>>>> parent of ae3b25d... almost working
=======
        if ((Math.abs(shooterEncoder.getVelocity())>(targetSpeed)*0.80))// && Math.abs(shooterEncoder.getVelocity())<(targetSpeed)*1.20)
>>>>>>> parent of 882a475... ohmy-crap after HH
            return true;
        else
            return false;
    }

    public boolean getHoodPosition(){
        return hoodUp;
    }

    public void moveHoodUp(){
        hoodUp = true;
        hoodSolenoid.set(hoodUp);
    }

    public void moveHoodDown(){
        hoodUp = false;
        hoodSolenoid.set(hoodUp);
    }

    public void toggleHood(){
        hoodUp = !hoodUp;
        hoodSolenoid.set(hoodUp);
    }
    
    public void sendToDashboard() {
        
        // SmartDashboard.putNumber("Theor. RPM", ((determineShooterSpeed(Robot.visionprocessor.getDistance())*Constants.kSHOOTER_MAXSPEED)));
        // SmartDashboard.putNumber("Theor. RPM %", ((determineShooterSpeed(Robot.visionprocessor.getDistance()))));
<<<<<<< HEAD
        SmartDashboard.putNumber("Shooter RPM", determineShooterSpeed(Robot.visionprocessor.getDistance()));
        SmartDashboard.putBoolean("Shooter Target Speed Achieved", isShooterAtSpeed());
        SmartDashboard.putBoolean("Shooter Hood up", hoodUp);
        SmartDashboard.putNumber("Shooter1 velocity", shooterEncoder.getVelocity());
        SmartDashboard.putNumber("Shooter2 velocity", shooterEncoder2.getVelocity());
        SmartDashboard.putNumber("Shooter target speed", targetSpeed);
=======
        SmartDashboard.putBoolean("Target Speed Achieved", isShooterAtSpeed());
        SmartDashboard.putBoolean("Hood up", hoodUp);
        SmartDashboard.putNumber("RPM", determineShooterSpeed(Robot.visionprocessor.getDistance()));
        SmartDashboard.putNumber("velocity", shooterEncoder.getVelocity());
        SmartDashboard.putNumber("target speed", targetSpeed);
>>>>>>> parent of ae3b25d... almost working
    }

    @Override
    protected void initDefaultCommand() {
        if (Constants.DEBUG) {
		}  
    }
    
}