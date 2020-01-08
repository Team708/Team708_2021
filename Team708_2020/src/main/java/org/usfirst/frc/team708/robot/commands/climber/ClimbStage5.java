package org.usfirst.frc.team708.robot.commands.climber;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbStage5 extends Command {

    public ClimbStage5() {  	
        requires(Robot.climber);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.climber.stage5 = true;
        Robot.drivetrain.resetEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (!Robot.climber.rearRetract()) 
            Robot.climber.moveRearMotor(Constants.MOVE_CLIMBER_REAR_RETRACT);
        else {
            Robot.climber.moveRearMotor(Constants.STOP_CLIMBER);
            Robot.climber.stage5 = false;
        }
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.climber.rearRetract();
    }
    
    // Called once after isFinished returns true
    protected void end() {
        Robot.drivetrain.resetEncoder();
        Robot.climber.stopAll();
        Robot.climber.stage6 = true;
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
        Robot.climber.stopAll();
        end();
    }
}