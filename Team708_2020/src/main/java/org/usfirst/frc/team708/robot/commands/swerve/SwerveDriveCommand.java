package org.usfirst.frc.team708.robot.commands.swerve;

import org.usfirst.frc.team708.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

public class SwerveDriveCommand extends Command {

    public SwerveDriveCommand() {
        requires(Robot.swerve);
    }

    // Called just before this Command runs the first time
    protected void initialize() {  
        Robot.swerve.xInput = 0;
        Robot.swerve.yInput = 0;
        Robot.swerve.rotationalInput = 0;
        Robot.swerve.headingController.temporarilyDisable();
        Robot.swerve.stop();
        Robot.swerve.lastUpdateTimestamp = Timer.getFPGATimestamp();  
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.swerve.updatePose(Timer.getFPGATimestamp());
        Robot.swerve.updateControlCycle(Timer.getFPGATimestamp());
        Robot.swerve.lastUpdateTimestamp = Timer.getFPGATimestamp();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.swerve.xInput = 0;
        Robot.swerve.yInput = 0;
        Robot.swerve.rotationalInput = 0;
        Robot.swerve.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
