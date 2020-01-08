package org.usfirst.frc.team708.robot.commands.climber;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbStage6 extends Command {

    public ClimbStage6() {  	
        requires(Robot.drivetrain);
        requires(Robot.climber);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.drivetrain.resetEncoder();
        Robot.climber.stage6 = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if ((Robot.drivetrain.getEncoderDistanceRight()) < Constants.REAR_CLIMBER_ROLLER_DISTANCE_FINAL) {
            Robot.drivetrain.haloDrive(Constants.CLIMBER_ROLLER_FORWARD, 0.0, false);
            Robot.climber.stage3 = true;
        }
        else {
            Robot.drivetrain.stop();
            // Robot.climber.stage6 = false;
            Robot.climber.stage2 = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       return (Robot.drivetrain.getEncoderDistanceLeft()>=Constants.REAR_CLIMBER_ROLLER_DISTANCE_FINAL);
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.climber.stopAll();
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
        Robot.climber.stopAll();
        end();
    }
}