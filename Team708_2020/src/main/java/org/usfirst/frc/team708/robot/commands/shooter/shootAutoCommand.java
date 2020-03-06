package org.usfirst.frc.team708.robot.commands.shooter;

import org.usfirst.frc.team708.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;


public class shootAutoCommand extends Command {

    private double speed;
    private double timeout;

    // private Translation2d driveVector;

    public shootAutoCommand() {
    
    }

    // Called just before this Command runs the first time
    protected void initialize() {

        // this.timeout = 11;

        // Robot.intake.unlockHanger();
        // Robot.spinner.pistonRetract();
        // Robot.turret.updateAngle();
        Robot.shooter.moveHoodUp();
        Robot.shooter.shootAuto();
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

        // Robot.shooter.feederOn();
        // Robot.shooter.moveHoodUp();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // return (this.timeSinceInitialized()>=timeout);

        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
        // Robot.shooter.stopShooter();
        // Robot.intake.toIntake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
