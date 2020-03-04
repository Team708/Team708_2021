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
<<<<<<< HEAD
<<<<<<< HEAD
    public boolean inIntakePosition = false;
    public boolean stopHanger = false;

    private double motordirection = .5; //intake Motor speed
    private double intakeMotorSpeed;     //start with motor spinning forward
=======
=======
>>>>>>> parent of ae3b25d... almost working
    
    private double motordirection = 1; //intake Motor speed
                                       //start with motor spinning forward
>>>>>>> parent of ae3b25d... almost working
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

<<<<<<< HEAD
        // hangerExtended 	= new DigitalInput(0);
        // hangerRetracted	= new DigitalInput(1);
        unlockHanger();
=======
        hangerExtended 	= new DigitalInput(0);
        hangerRetracted	= new DigitalInput(1);
<<<<<<< HEAD
>>>>>>> parent of ae3b25d... almost working
=======
>>>>>>> parent of ae3b25d... almost working

        toColorFromIntake();
    }

    public void toIntake(){
        unlockHanger();
        Robot.spinner.pistonRetract();
        camSolenoid.set(DoubleSolenoid.Value.kForward);   // I
        pivotSolenoid.set(DoubleSolenoid.Value.kReverse); // O
        moveMotorIntakeOut();
<<<<<<< HEAD
<<<<<<< HEAD
        Robot.hopper.moveMotorClockwise();
        Robot.shooter.feederSlow();
=======
>>>>>>> parent of ae3b25d... almost working
=======
>>>>>>> parent of ae3b25d... almost working
        inHangerPosition = false;
        inIntakePosition = true;
    }

    public void toHanger(){
<<<<<<< HEAD
<<<<<<< HEAD
        if (Timer.getMatchTime() <= 35){
            StopMotorIntake();
            Robot.spinner.pistonRetract();
            camSolenoid.set(DoubleSolenoid.Value.kForward);   // I
            pivotSolenoid.set(DoubleSolenoid.Value.kForward); // I
            lockHanger();
            inHangerPosition = true;
            inIntakePosition = false;
            Robot.spinner.resetSpinnerEncoder();
        }
=======
=======
>>>>>>> parent of ae3b25d... almost working
        Robot.spinner.pistonRetract();
        camSolenoid.set(DoubleSolenoid.Value.kForward);   // I
        pivotSolenoid.set(DoubleSolenoid.Value.kForward); // I
        StopMotorIntake();
        shiftToHanger();
        inHangerPosition = true;
<<<<<<< HEAD
>>>>>>> parent of ae3b25d... almost working
=======
>>>>>>> parent of ae3b25d... almost working
    }

    public void toColorFromIntake(){
        camSolenoid.set(DoubleSolenoid.Value.kReverse);   // O
        pivotSolenoid.set(DoubleSolenoid.Value.kForward); // I
<<<<<<< HEAD
<<<<<<< HEAD
        unlockHanger();
=======
>>>>>>> parent of ae3b25d... almost working
=======
>>>>>>> parent of ae3b25d... almost working
        StopMotorIntake();
        inHangerPosition = false;
        inIntakePosition = false;
    }
    public void toColorFromHanger(){
        camSolenoid.set(DoubleSolenoid.Value.kReverse);   // O
        pivotSolenoid.set(DoubleSolenoid.Value.kReverse); // O
<<<<<<< HEAD
<<<<<<< HEAD
        unlockHanger();
=======
>>>>>>> parent of ae3b25d... almost working
=======
>>>>>>> parent of ae3b25d... almost working
        StopMotorIntake();
        inHangerPosition = false;
        inIntakePosition = false;
    }

    public void shiftToHanger(){
        if(inHangerPosition)
            shifterHanger.set(false);
        else
            shifterHanger.set(true);
    }

    private boolean notExtended(){
        return hangerExtended.get();
    }

    private boolean notRetracted(){
        return hangerRetracted.get();
    }

    public void moveHanger(double Y){
        if (inHangerPosition){
<<<<<<< HEAD
<<<<<<< HEAD
            if (Y<0 && notExtended())
                Robot.spinner.spinnerMotor.set(-Y);
                // Robot.spinner.spinnerPID.setReference(50, ControlType.kPosition); //169 max
            else if (Y>0 && notRetracted())
                // Robot.spinner.spinnerPID.setReference(20, ControlType.kPosition); //5 min
                Robot.spinner.spinnerMotor.set(-Y);
            else 
                Robot.spinner.spinnerMotor.set(0);
              stopHanger = true;
        }
    }
    public void stopHanger(){
        if (inHangerPosition){
              Robot.spinner.spinnerMotor.set(0.0);
              stopHanger = false;
=======
=======
>>>>>>> parent of ae3b25d... almost working
            if ((Y>0 && notExtended() ) || 
                (Y<0 && notRetracted())
               )
              Robot.spinner.spinnerMotor.set(Y);
<<<<<<< HEAD
>>>>>>> parent of ae3b25d... almost working
=======
>>>>>>> parent of ae3b25d... almost working
        }
    }

    public void lockHanger(){
        shifterHanger.set(false);
    }
    public void unlockHanger(){
        shifterHanger.set(true);
    }
    public boolean getIntakePosition(){
        return intakeIn;
    }

    public void intakeToggleMotor(){
        if (intakeMotorSpeed != 0)
           intakeMotorSpeed = 0 * motordirection;
        else
            intakeMotorSpeed = 1 * motordirection;
        
            Robot.spinner.spinnerMotor.set(intakeMotorSpeed);  //turns motor off
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
            Robot.spinner.spinnerMotor.set(motordirection);  //turns motor off
        else
            moveMotorIntakeOut();
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub
    }

    public void sendToDashboard() {
<<<<<<< HEAD
<<<<<<< HEAD
        SmartDashboard.putBoolean("Hanger extended",!notExtended());
        SmartDashboard.putBoolean("Hanger retracted",!notRetracted());
        SmartDashboard.putNumber("FMS Match Time", Timer.getMatchTime());
        SmartDashboard.putNumber("Hanger Get Reference", Robot.spinner.spinnerEncoder.getPosition());
=======
        // SmartDashboard.putBoolean("Intake is in",intakeIn);
>>>>>>> parent of ae3b25d... almost working
=======
        // SmartDashboard.putBoolean("Intake is in",intakeIn);
>>>>>>> parent of ae3b25d... almost working
    }
}