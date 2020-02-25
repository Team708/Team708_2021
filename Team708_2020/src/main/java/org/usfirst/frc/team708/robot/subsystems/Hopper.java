package org.usfirst.frc.team708.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Hopper extends Subsystem {

    public CANSparkMax hopperMotor;
    public CANEncoder  hopperEncoder;
    private CANPIDController hopperPIDController;

    // private double hopperspeed = Constants.kHOPPER_SPEED; //speed of Hooper
    private double hopperspeed = 2000; //speed of Hooper

    public Hopper(){
        hopperMotor = new CANSparkMax(RobotMap.khopperMotor, MotorType.kBrushless);
        hopperMotor.setInverted(false);

        hopperEncoder = new CANEncoder(hopperMotor);
        hopperPIDController = hopperMotor.getPIDController();

        hopperPIDController.setP(.00008);
        hopperPIDController.setI(0);
        hopperPIDController.setD(0);
        hopperPIDController.setIZone(0);
        hopperPIDController.setFF(.00015);  //.1
        hopperPIDController.setOutputRange(-1,1); 
    }

    public void moveMotor(){
        // hopperMotor.set(hopperspeed);  
        hopperPIDController.setReference(hopperspeed, ControlType.kSmartVelocity);
    }

    public void stopMotor(){
        hopperMotor.set(0.0);
    }

    //uses same speed as above, sets it to negative
    public void reverseMotor(){
        hopperspeed *= -1;
        moveMotor();
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }
    
    public void outputToSmartDashboard() {
        // SmartDashboard.putNumber("Hopper direction", hopperForward);
    }
}