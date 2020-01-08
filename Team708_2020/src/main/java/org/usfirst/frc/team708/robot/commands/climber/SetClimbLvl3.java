package org.usfirst.frc.team708.robot.commands.climber;

import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.Constants;

import edu.wpi.first.wpilibj.command.Command;
//
///**
// *
// */
public class SetClimbLvl3 extends Command {

    public SetClimbLvl3() {
    }
   
    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.climber.HABLevel = Constants.HAB_LVL3_ENCODER_DISTANCE;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
