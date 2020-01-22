package org.usfirst.frc.team708.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.usfirst.frc.team708.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Hopper extends Subsystem {

    public CANSparkMax hopperMotor;

    private static final double speed = 0.2;

    public Hopper(){
        hopperMotor = new CANSparkMax(RobotMap.khopperMotor, MotorType.kBrushless);
        hopperMotor.setInverted(false);
    }

    public void moveMotor(){
        hopperMotor.set(speed);
    }

    public void stopMotor(){
        hopperMotor.set(.0);
    }

    //uses same speed as above, sets it to negative
    public void reverseMotor(){
        hopperMotor.set(-speed);
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }
    
}