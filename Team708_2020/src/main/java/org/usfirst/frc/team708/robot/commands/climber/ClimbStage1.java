package org.usfirst.frc.team708.robot.commands.climber;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbStage1 extends Command {

    public ClimbStage1() {  
        requires(Robot.climber);	
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.climber.stage1 = true;
        Robot.climber.resetClimberAll();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (!((Robot.climber.frontExtend()) && (Robot.climber.rearExtend()))) {
            if (Robot.drivetrain.isTiltingBack()) {
                Robot.climber.moveFrontMotor(Constants.STOP_CLIMBER);
                Robot.climber.moveRearMotor(Constants.MOVE_CLIMBER_REAR_EXTEND);
            }
            else if (Robot.drivetrain.isTiltingForward()) {
                Robot.climber.moveFrontMotor(Constants.MOVE_CLIMBER_FRONT_EXTEND);
                Robot.climber.moveRearMotor(Constants.STOP_CLIMBER);
            }
            else {
                Robot.climber.resetClimberRoller();
                Robot.climber.moveFrontMotor(Constants.MOVE_CLIMBER_FRONT_EXTEND);
                Robot.climber.moveRearMotor(Constants.MOVE_CLIMBER_REAR_EXTEND);
            } 
        }
        else {
            Robot.climber.moveFrontMotor(Constants.STOP_CLIMBER);
            Robot.climber.moveRearMotor(Constants.STOP_CLIMBER);
            Robot.climber.stage1 = false;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.climber.frontExtend()) && (Robot.climber.rearExtend());
        
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