package org.usfirst.frc.team708.robot.commands.climber;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbStage2 extends Command {

    public ClimbStage2() {  	
        requires(Robot.climber);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.climber.resetClimberRoller();
        Robot.climber.stage2 = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (Robot.climber.getEncoderRoller()<Constants.REAR_CLIMBER_ROLLER_FIRST_DISTANCE) 
            Robot.climber.moveRollerMotor(Constants.CLIMBER_ROLLER_FORWARD);
        else {
            // Robot.climber.moveRollerMotor(0.0);
            Robot.climber.stage2 = false;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.climber.getEncoderRoller()>=Constants.REAR_CLIMBER_ROLLER_FIRST_DISTANCE;
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