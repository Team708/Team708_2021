package org.usfirst.frc.team708.robot.commands.intake;

//import java.awt.Robot;

import org.usfirst.frc.team708.robot.Constants;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.subsystems.Intake;

public class DeployGamePiece extends Command {


    public DeployGamePiece() {
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {    
        if (Robot.intake.hasBall()) {
            Robot.intake.moveMotorBall(Constants.BALL_OUT);
        }
        else {
            Robot.intake.hatchExtend();
            Robot.intake.moveMotorBall(Constants.BALL_OUT);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return(!(Robot.intake.hasBall() && Robot.intake.hasHatch()));
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
