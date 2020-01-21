package org.usfirst.frc.team708.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.RobotMap;
// import org.usfirst.frc.team708.robot.subsystems.VisionProcessor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem {

    public CANSparkMax shooterMotor, feederMotor;

    public boolean shooterSet = false;
    public CANEncoder shooterEncoder, feederEncoder;
    private CANPIDController shooterPIDController;

    public Solenoid hoodSolenoid;
    public boolean hoodUp = true;
    private double FlyWheelEffeciency = .235;

    public Shooter(){

        shooterMotor = new CANSparkMax(RobotMap.kshooterShootMotor, MotorType.kBrushless);
        feederMotor = new CANSparkMax(RobotMap.kfeederFeedMotor, MotorType.kBrushless);
        shooterMotor.setInverted(true);
        shooterEncoder = new CANEncoder(shooterMotor);
        shooterPIDController = shooterMotor.getPIDController();
        shooterPIDController.setP(5e-4);
        shooterPIDController.setI(0);
        shooterPIDController.setD(6e-5);
        shooterPIDController.setFF(2e-4);
        shooterPIDController.setIZone(0);
        shooterPIDController.setOutputRange(-1, 1);

        feederEncoder = new CANEncoder(feederMotor);
        hoodSolenoid = new Solenoid(RobotMap.hoodSolenoid);
        hoodSolenoid.set(hoodUp); //Starts with hood up (45 degrees)
    }

    public void feederOn(){
        
       // if (shooterSet & Robot.visionprocessor.targetFound)
    feederMotor.set(0.84);
    }

    public void feederOff(){
        feederMotor.set(0);
    }

    public void shootManual(double speed){
        shooterMotor.set(-speed);
    }

    public void stopShooter(){
        shooterMotor.stopMotor();
    }

    
    public double adjustAnglePosition(boolean extended, double distance){
        double angle;
        if(extended){
            angle = 25;
        }else{
            angle = 45;
        }
        double angleInRadians=angle*Math.PI/180;
        //angleInRadians = Math.atan2(1,(distance / (2 * (Constants.kGOALHEIGHT-Constants.kCAMERAHEIGHT))));
        return angleInRadians;
    }

    /**
     * 
     * @param distance
     *                 Defines a distance away from the goal and makes calculations based on it.
     */
    public double determineShooterSpeed(double distance){
        distance = distance / 12;
        double angle = adjustAnglePosition(getHoodPosition(), distance);
        //velocity = sqrt((32.2*distance^2)/2(cos(Θ(RADIANS)))^2 * (-height+distance*tan(Θ(RAIDANS))))
        
        double p1 = (32.2 * Math.pow(distance, 2));
        double p2 = (2 * (Math.pow(Math.cos(angle) , 2)));
        double p3 = -1*(Constants.kGOALHEIGHT-Constants.kSHOOTERHEIGHT)/12 + distance * Math.tan(angle);
        
        double velocity = Math.sqrt(p1 / (p2 * p3));
        
        double RPM = (velocity) / (4 * FlyWheelEffeciency * 2) * 60 * Math.PI; //change 4 to cnst.
        //double velocity = 0;
        return RPM;
    }

    public void shootAuto(){
        double RPM = determineShooterSpeed(Robot.visionprocessor.getDistance());
        shooterPIDController.setReference((-4700), ControlType.kVelocity);
    }
    // public void outputToSmartDashboard() {

    //     //SmartDashboard.getNumber(key, defaultValue)
    // }

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


    @Override
    protected void initDefaultCommand() {
        if (Constants.DEBUG) {
		}  
    }
    
}