package org.usfirst.frc.team708.robot.commands.intake;

import org.usfirst.frc.team708.robot.subsystems.Pigeon;
import org.usfirst.frc.team708.robot.subsystems.Shooter;
import org.usfirst.frc.team708.robot.subsystems.VisionProcessor;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
// import org.usfirst.frc.team254.lib.util.math.*;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import java.lang.*;

public class IntakeMotor extends Command {

    // private Translation2d driveVector;
    private double speed;

    /**
     * 
     * @param speed Speed of intake from -1.0 to 1.0
     */
    public IntakeMotor(double speed) {
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.intake.moveIntakeMotorAtSpeed(speed); //moves motor  whatever speed from constructor
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}