package org.usfirst.frc.team708.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Spinner extends Subsystem {

    public CANSparkMax      spinnerMotor;
    public CANEncoder       spinnerEncoder; 
    private Solenoid         spinnerSolenoid;
    public  CANPIDController spinnerPID;

    private boolean pistonExtend;
    private double  spinnerMotorSpeed = .3;

    // public  boolean spinnerRotateStop;
    // public Solenoid  deployColorWheel;

public Spinner() {
    spinnerMotor = new CANSparkMax(RobotMap.kspinnerMotor, MotorType.kBrushless);
    spinnerEncoder = new CANEncoder(spinnerMotor);

    spinnerSolenoid = new Solenoid(RobotMap.littlePecker);

    spinnerPID = spinnerMotor.getPIDController();
    
    spinnerPID.setP(0.1);
    spinnerPID.setI(0);
    spinnerPID.setD(0);
    spinnerPID.setFF(0.1);
    spinnerPID.setIZone(0);
    spinnerPID.setOutputRange(-0.42, 0.42);
    
    // spinnerPID.setP(.00008);
    // spinnerPID.setI(0);
    // spinnerPID.setD(0);
    // spinnerPID.setIZone(0);
    // spinnerPID.setFF(.00015);  //.1
    // spinnerPID.setOutputRange(-1,1); 
    
    spinnerMotor.setIdleMode(IdleMode.kBrake);
    
    spinnerEncoder.setPosition(0);

    spinnerMotor.setInverted(false);

    spinnerSolenoid.set(false);
}

public void SpinMotor(double speed) {
    spinnerMotor.set(spinnerMotorSpeed);
}

// public void StopSpin() {
//     spinner.set(0.0);
// }

public void spinnerRotateOneColor() {
    pistonExtend();
    resetSpinnerEncoder();
    spinnerPID.setReference(-Constants.kSPIN_ONE_COLOR, ControlType.kPosition);
}

public void spinnerRotateThreeTimes() {
    pistonExtend();
    resetSpinnerEncoder();
    spinnerPID.setReference(-Constants.kSPIN_THREE_TIMES, ControlType.kPosition);
}

public void spinnerMotorStop() {
    spinnerMotor.set(0);
}

public void resetSpinnerEncoder() {
    spinnerEncoder.setPosition(0.0);
}

public double getSpinMotorCount() {
    return(spinnerEncoder.getPosition());
}

public boolean getPistonPosition() {
    return pistonExtend;
}

public void pistonExtend() {
    pistonExtend = true;
    spinnerSolenoid.set(pistonExtend);
}

public void pistonRetract() {
    pistonExtend = false;
    spinnerSolenoid.set(pistonExtend);
}
    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }
    
    public void sendToDashboard() {
        SmartDashboard.putNumber("SpinnerEncoder", getSpinMotorCount());

        SmartDashboard.putString("SpinnerTargetColor", Robot.wheelTargetColor);
        SmartDashboard.putBoolean("SpinnerPistion", pistonExtend);

        //puts colors to dashboard
        // SmartDashboard.putBoolean("SpinnerBlue",   Robot.colors[0]);
        // SmartDashboard.putBoolean("SpinnerGreen",  Robot.colors[1]);
        // SmartDashboard.putBoolean("SpinnerRed",    Robot.colors[2]);
        // SmartDashboard.putBoolean("SpinnerYellow", Robot.colors[3]);
    }

}

