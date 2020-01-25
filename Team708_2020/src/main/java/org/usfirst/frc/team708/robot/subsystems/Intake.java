package org.usfirst.frc.team708.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.usfirst.frc.team708.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {

    public CANSparkMax intakeMotor;
    public Solenoid intakeSolenoid;
    private boolean intakeIn;


    public Intake(){
        intakeMotor = new CANSparkMax(RobotMap.kintakeMotor, MotorType.kBrushless);
        intakeMotor.setInverted(false);
        
        intakeSolenoid = new Solenoid(RobotMap.intakeSolenoid);
        intakeSolenoid.set(intakeIn);
    }

    public boolean getIntakePosition(){
        return intakeIn;
    }

    public void moveIntakeIn(){
        intakeIn = true;
        intakeMotor.set(0);
        intakeSolenoid.set(intakeIn);
    }

    public void moveIntakeOut(){
        intakeIn = false;
        intakeSolenoid.set(intakeIn);
    }

    public void toggleIntake(){
        intakeIn = !intakeIn;
        intakeSolenoid.set(intakeIn);
    }

    public void intakeMotorOn(double speed){
        if(!intakeIn){
            intakeMotor.set(speed);
        }
        else
            intakeMotor.set(0);

    

    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }
    
}