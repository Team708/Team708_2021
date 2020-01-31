package org.usfirst.frc.team708.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.usfirst.frc.team708.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake extends Subsystem {

    public CANSparkMax intakeMotor;
    public Solenoid intakeSolenoid0, intakeSolenoid1;
    private boolean intakeIn, intakeMotorIn = true;


    public Intake(){
        intakeMotor = new CANSparkMax(RobotMap.kintakeMotor, MotorType.kBrushless);
        intakeMotor.setInverted(false);
        
        intakeSolenoid0 = new Solenoid(RobotMap.intakeSolenoid0);
        intakeSolenoid0.set(intakeIn);
        intakeSolenoid1 = new Solenoid(RobotMap.intakeSolenoid1);
        intakeSolenoid1.set(intakeIn);
    }

    public boolean getIntakePosition(){
        return intakeIn;
    }

    public void moveIntakeIn(){
        intakeIn = true;
        intakeMotor.set(0);
        intakeSolenoid0.set(intakeIn);
        intakeSolenoid1.set(intakeIn);
    }

    public void moveIntakeOut(){
        intakeIn = false;
        intakeSolenoid0.set(intakeIn);
        intakeSolenoid1.set(intakeIn);
        intakeMotor.set(.2);
    }

    public void StopIntake(){
        intakeMotor.set(0);
    }
    public void toggleIntake(){
        intakeIn = !intakeIn;
        intakeSolenoid0.set(intakeIn);
        intakeSolenoid1.set(intakeIn);
         
        if (!intakeIn)
            moveIntakeOut();
        else
            moveIntakeIn();
    }

    public void reverseIntakeMotor(){
        if(!intakeIn){
            intakeMotorIn = !intakeMotorIn;
            if(intakeMotorIn)
                intakeMotor.set(.2);
            else
                intakeMotor.set(-.2);
        }
        else
            intakeMotor.set(0);
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub
    }

    public void sendToDashboard() {
        SmartDashboard.putBoolean("Intake is in",intakeIn);
        SmartDashboard.putBoolean("Intake Motor spin in",intakeMotorIn);
    }
}