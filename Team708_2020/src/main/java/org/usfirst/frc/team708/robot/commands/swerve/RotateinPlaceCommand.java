package org.usfirst.frc.team708.robot.commands.swerve;
import org.usfirst.frc.team708.robot.subsystems.Swerve;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team254.lib.util.math.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;
import java.lang.*;

public class RotateinPlaceCommand extends Command {
	
    private double rotatePower;
	
    public RotateinPlaceCommand(double rotatePower) {

    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Swerve.getInstance().zeroSensors();
        Swerve.timesCalled++;
        Swerve.getInstance().sendInput(.1, .1, 0, false, false);
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
