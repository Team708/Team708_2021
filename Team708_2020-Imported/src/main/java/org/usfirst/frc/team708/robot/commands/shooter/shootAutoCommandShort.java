package org.usfirst.frc.team708.robot.commands.shooter;

import org.usfirst.frc.team708.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class shootAutoCommandShort extends Command {

    private double timeout;

    public shootAutoCommandShort() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.timeout = 5;
        Robot.shooter.shootLong();
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.shooter.feederAutoShoot();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (this.timeSinceInitialized()>=timeout);
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.shooter.stopShooter();
        Robot.intake.toIntake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
