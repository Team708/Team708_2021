package org.usfirst.frc.team708.robot.commands.climber;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbStage3 extends Command {

    public ClimbStage3() {  	
        requires(Robot.climber);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.climber.stage3 = true;
        Robot.climber.resetClimberRoller();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (!(Robot.climber.frontRetract())) {
            Robot.climber.moveFrontMotor(Constants.MOVE_CLIMBER_FRONT_RETRACT);
        }
        else {
            Robot.climber.stage3 = false;
            Robot.climber.moveFrontMotor(Constants.STOP_CLIMBER);
            Robot.climber.resetClimberRoller();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.climber.frontRetract();
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