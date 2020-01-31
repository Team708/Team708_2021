package org.usfirst.frc.team708.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.usfirst.frc.team708.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Hopper extends Subsystem {

    public CANSparkMax hopperMotor;

    private int hopperForward = 1;

    public Hopper(){
        hopperMotor = new CANSparkMax(RobotMap.khopperMotor, MotorType.kBrushless);
        hopperMotor.setInverted(false);
    }

    public void moveMotor(){
        hopperMotor.set(hopperForward * .4);
    }

    public void stopMotor(){
        hopperMotor.set(0);
    }

    //uses same speed as above, sets it to negative
    public void reverseMotor(){
        hopperForward *= -1;
        moveMotor();
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }
    
    public void outputToSmartDashboard() {
        SmartDashboard.putNumber("Hopper direction", hopperForward);
    }
}