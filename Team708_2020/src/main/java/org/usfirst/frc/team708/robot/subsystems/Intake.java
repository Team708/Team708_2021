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
    private boolean intakeIn;


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
    }

    public void toggleIntake(){
        intakeIn = !intakeIn;
        intakeSolenoid0.set(intakeIn);
        intakeSolenoid1.set(intakeIn);

    }

    public void intakeMotorOn(double speed){
        if(!intakeIn){
            if(speed>0.2)
                intakeMotor.set(.2);
            else if (speed<-0.2)
                intakeMotor.set(-.2);
            else
                intakeMotor.set(0);
        }
        else
            intakeMotor.set(0);

    

    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }

    public void outputToSmartDashboard() {
        SmartDashboard.putBoolean("Intake is in",intakeIn);

    }

    
}