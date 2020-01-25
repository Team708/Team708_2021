package org.usfirst.frc.team708.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Spinner extends Subsystem {

    private CANSparkMax     spinner;
    private CANEncoder      spinnerEncoder; 
    private Solenoid        spinnerSolenoid;

    private boolean pistonExtend;

public Spinner() {
    spinner = new CANSparkMax(25, MotorType.kBrushless);
    spinnerEncoder = new CANEncoder(spinner);

    spinnerSolenoid = new Solenoid(0);


}

public void SpinMotor(double speed) {
    spinner.set(speed);
}

public void StopSpin() {
    spinner.set(0.0);
}

public double getSpinMotorCount() {
    // spinnerEncoder.setPosition(0.0);
    spinnerEncoder.getPosition();
    return getSpinMotorCount();

}

public void AutoPosition() {
    // spinnerEncoder.setPosition(0.0);
    spinnerEncoder.setPosition(1.33);
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
    


}

