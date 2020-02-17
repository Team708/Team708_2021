package org.usfirst.frc.team708.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANEncoder;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.subsystems.*;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;

public class Intake extends Subsystem {

    public  CANSparkMax    intakeMotor;

    public DoubleSolenoid  camSolenoid;
    public DoubleSolenoid  pivotSolenoid;

    public Solenoid  shifterHanger;
    public Solenoid  lockHanger;

    private boolean intakeIn = true;
    public boolean inHangerPosition = false;
    
    private double motordirection = 1; //intake Motor speed
                                       //start with motor spinning forward
    private DigitalInput hangerExtended;
    private DigitalInput hangerRetracted;

    public Intake(){
        intakeMotor = new CANSparkMax(RobotMap.kintakeMotor, MotorType.kBrushless);
        intakeMotor.setInverted(false);
        
        intakeMotor.follow(Robot.spinner.spinnerMotor);

        camSolenoid   = new DoubleSolenoid(RobotMap.armCam0, RobotMap.armCam1);
        pivotSolenoid = new DoubleSolenoid(RobotMap.armPivot0, RobotMap.armPivot1);

        shifterHanger = new Solenoid(RobotMap.hangerEngage);
        // lockHanger    = new Solenoid(RobotMap.hangerLock);

        hangerExtended 	= new DigitalInput(0);
        hangerRetracted	= new DigitalInput(1);

        toColorFromIntake();
    }

    public void toIntake(){
        Robot.spinner.pistonRetract();
        camSolenoid.set(DoubleSolenoid.Value.kForward);   // I
        pivotSolenoid.set(DoubleSolenoid.Value.kReverse); // O
        moveMotorIntakeOut();
        inHangerPosition = false;
    }

    public void toHanger(){
        Robot.spinner.pistonRetract();
        camSolenoid.set(DoubleSolenoid.Value.kForward);   // I
        pivotSolenoid.set(DoubleSolenoid.Value.kForward); // I
        StopMotorIntake();
        shiftToHanger();
        inHangerPosition = true;
    }

    public void toColorFromIntake(){
        camSolenoid.set(DoubleSolenoid.Value.kReverse);   // O
        pivotSolenoid.set(DoubleSolenoid.Value.kForward); // I
        StopMotorIntake();
        inHangerPosition = false;
    }
    public void toColorFromHanger(){
        camSolenoid.set(DoubleSolenoid.Value.kReverse);   // O
        pivotSolenoid.set(DoubleSolenoid.Value.kReverse); // O
        StopMotorIntake();
        inHangerPosition = false;
    }

    public void shiftToHanger(){
        if(inHangerPosition)
            shifterHanger.set(true);
        else
            shifterHanger.set(false);
    }

    private boolean notExtended(){
        return hangerExtended.get();
    }

    private boolean notRetracted(){
        return hangerRetracted.get();
    }

    public void moveHanger(double Y){
        if (inHangerPosition){
            if ((Y>0 && notExtended() ) || 
                (Y<0 && notRetracted())
               )
              Robot.spinner.spinnerMotor.set(Y);
        }
    }

    public void lockHanger(){
        shifterHanger.set(false);
    }

    public boolean getIntakePosition(){
        return intakeIn;
    }

    public void moveMotorIntakeIn(){
        intakeIn = true;
        Robot.spinner.spinnerMotor.set(0);  //turns motor off
    }

    public void moveMotorIntakeOut(){
        intakeIn = false;
        Robot.spinner.spinnerMotor.set(motordirection);  //turns motor on
    }

    public void StopMotorIntake(){
        Robot.spinner.spinnerMotor.set(0);
    }

    public void toggleMotorIntake(){
        motordirection *= -1;

        if (intakeIn)
            Robot.spinner.spinnerMotor.set(0);  //turns motor off
        else
            moveMotorIntakeOut();
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub
    }

    public void sendToDashboard() {
        // SmartDashboard.putBoolean("Intake is in",intakeIn);
    }
}