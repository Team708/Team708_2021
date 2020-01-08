package org.usfirst.frc.team708.robot.commands.intake;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class AutoIntakeBallOutForTime extends Command {

    public AutoIntakeBallOutForTime(double maxTime) {
    	this.setTimeout(maxTime);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.intakeCube.moveMotor(-.6);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.moveMotorBall(Constants.BALL_OUT);
   }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return(isTimedOut());
//    	return(true);
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
