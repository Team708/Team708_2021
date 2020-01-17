package org.usfirst.frc.team708.robot.commands.swerve;
import org.usfirst.frc.team708.robot.subsystems.Swerve;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team254.lib.util.math.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;
import java.lang.*;

public class DriveStraightCommand extends Command {
	
    private Translation2d driveVector;

	
    public DriveStraightCommand(double angle, double power) {
        this.driveVector = Rotation2d.fromDegrees(angle+90).toTranslation().scale(power);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Swerve.getInstance().zeroSensors();
        Swerve.timesCalled++;
        Swerve.getInstance().sendInput(driveVector.x(), driveVector.y(), 0.0, false, false);
        //Swerve.getInstance().setPositionTarget(directionDegrees, magnitudeInches);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.swerve.updatePose(Timer.getFPGATimestamp());
        Robot.swerve.updateControlCycle(Timer.getFPGATimestamp());
        Robot.swerve.lastUpdateTimestamp = Timer.getFPGATimestamp();

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
        Swerve.getInstance().stop();
    }
}
