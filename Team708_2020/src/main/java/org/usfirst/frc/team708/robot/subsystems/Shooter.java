package org.usfirst.frc.team708.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

    public CANSparkMax shooterMotor, intakeMotor;

    public CANEncoder shooterEncoder, intakeEncoder;

    public DoubleSolenoid solenoid;
    private boolean pistonIn;

    private double RPMAdjust = 300;
    private double FlyWheelEffeciency = .235;

    public Shooter(){

        shooterMotor = new CANSparkMax(RobotMap.kshooterShootMotor, MotorType.kBrushless);
        //format motor

        shooterEncoder = new CANEncoder(shooterMotor);

        intakeMotor = new CANSparkMax(22, MotorType.kBrushless); //change 22 to constant, decide port #
        //format motor

        intakeEncoder = new CANEncoder(intakeMotor);
        // solenoid = new DoubleSolenoid(forwardChannel, reverseChannel);
    }

    public void intake(CANSparkMax motor, double speed){
        
    }

    public double adjustAnglePosition(boolean extended, double distance){
        // if(extended){
        //     //extend solenoid if not already
        // }else{
        //     //retract solenoid if not already
        // }

        double angleInRadians = Math.atan(1/(distance / (2 * Constants.heightOfGoal)));
        return angleInRadians;
    }

    /**
     * 
     * @param distance
     *                 Defines a distance away from the goal and makes calculations based on it.
     */
    public double determineVelocity(double distance){

        double angle = adjustAnglePosition(false, distance);
        //velocity = sqrt((32.2*distance^2)/2(cos(theta(RADIANS)))^2 * (-height+distance*tan(theta(RAIDANS))))

        double p1 = (32.2 * (distance * distance));
        double p2 = (2 * (Math.cos(angle) * Math.cos(angle)));
        double p3 = (-Constants.heightOfGoal + distance * Math.tan(angle));

        double velocity = Math.sqrt(p1 / (p2 * p3));

        double RPM = (velocity) / (4 * FlyWheelEffeciency * 2) * 60 * Math.PI + RPMAdjust; //change 4 to cnst.

        return RPM;
    }


    @Override
    protected void initDefaultCommand() {
        if (Constants.DEBUG) {
		}  
    }
    
}