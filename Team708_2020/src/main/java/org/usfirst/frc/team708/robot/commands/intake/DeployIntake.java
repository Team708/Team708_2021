package org.usfirst.frc.team708.robot.commands.intake;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.Constants;

public class DeployIntake extends Command {


    public DeployIntake() {
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
        if (Robot.elevator.getEncoderDistance() >= (Math.abs(Constants.ELEV_LVL0)-Constants.ELEV_TOLERANCE)) {
            Robot.intake.intakeDeploy();
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return(true);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}
