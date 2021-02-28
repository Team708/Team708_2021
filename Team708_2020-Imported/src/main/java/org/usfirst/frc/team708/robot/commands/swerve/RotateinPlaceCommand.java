package org.usfirst.frc.team708.robot.commands.swerve;
import org.usfirst.frc.team708.robot.subsystems.Pigeon;
import org.usfirst.frc.team708.robot.subsystems.Swerve;
import org.usfirst.frc.team708.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
// import org.usfirst.frc.team254.lib.util.math.*;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import java.lang.*;

public class RotateinPlaceCommand extends Command {

    // private Translation2d driveVector;
    private double angle;
    private double timeout = 1.0;

    public RotateinPlaceCommand(double angle) {
        this.angle=angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        // Swerve.getInstance().zeroSensors();
        Swerve.timesCalled++;

        Swerve.getInstance().rotate(angle);

        if (angle > 180) angle = angle - 180;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // Robot.swerve.updatePose(Timer.getFPGATimestamp());
        // Robot.swerve.updateControlCycle(Timer.getFPGATimestamp());
        // Robot.swerve.lastUpdateTimestamp = Timer.getFPGATimestamp();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (this.timeSinceInitialized()>=timeout) || Math.abs(Pigeon.getInstance().getAngle().getDegrees()) >= Math.abs(angle);
    }

    // Called once after isFinished returns true
    protected void end() {
        // Swerve.getInstance().zeroSensors();

        Robot.swerve.xInput = 0;
        Robot.swerve.yInput = 0;
        Robot.swerve.rotationalInput = 0;
        Robot.swerve.headingController.temporarilyDisable();
        Robot.swerve.stop();
        Robot.swerve.lastUpdateTimestamp = Timer.getFPGATimestamp();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
