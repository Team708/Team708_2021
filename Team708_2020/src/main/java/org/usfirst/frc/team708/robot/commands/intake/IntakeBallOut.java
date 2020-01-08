package org.usfirst.frc.team708.robot.commands.intake;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeBallOut extends Command {

	
    public IntakeBallOut() {
    	requires(Robot.intake);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intake.moveMotorBall(Constants.BALL_OUT);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return(false);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.stopMotorBall();
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}
