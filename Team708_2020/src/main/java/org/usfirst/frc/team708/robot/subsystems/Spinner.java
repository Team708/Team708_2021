package org.usfirst.frc.team708.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Spinner extends Subsystem {

    private CANSparkMax     spinnerMotor;
    private CANEncoder      spinnerEncoder; 
    private Solenoid        spinnerSolenoid;
    public CANPIDController spinnerPID;

    private boolean pistonExtend;
    private double  spinnerMotorSpeed = .3;
    public boolean spinnerRotateStop;

public Spinner() {
    spinnerMotor = new CANSparkMax(RobotMap.kspinnerMotor, MotorType.kBrushless);
    spinnerEncoder = new CANEncoder(spinnerMotor);

    spinnerSolenoid = new Solenoid(RobotMap.spinnerSolenoid);

    spinnerEncoder.setPosition(0);
    spinnerPID = spinnerMotor.getPIDController();
    spinnerPID.setP(0.1);
    spinnerPID.setI(0);
    spinnerPID.setD(0);
    spinnerPID.setFF(0.1);
    spinnerPID.setIZone(0);
    spinnerPID.setOutputRange(-0.5, 0.5);
}

public void SpinMotor(double speed) {
    spinnerMotor.set(spinnerMotorSpeed);
}

// public void StopSpin() {
//     spinner.set(0.0);
// }

public void spinnerRotateOneColor() {
    resetSpinnerEncoder();
    spinnerPID.setReference(Constants.kSPIN_ONE_COLOR/2, ControlType.kPosition);
}

public void spinnerRotateThreeTimes() {
    resetSpinnerEncoder();
    spinnerPID.setReference(Constants.kSPIN_THREE_TIMES/2, ControlType.kPosition);
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

        SmartDashboard.putString("Target Color for Spinner", Robot.wheelTargetColor);

        //puts colors to dashboard
        SmartDashboard.putBoolean("Blue",   Robot.colors[0]);
        SmartDashboard.putBoolean("Green",  Robot.colors[1]);
        SmartDashboard.putBoolean("Red",    Robot.colors[2]);
        SmartDashboard.putBoolean("Yellow", Robot.colors[3]);
    }

}

