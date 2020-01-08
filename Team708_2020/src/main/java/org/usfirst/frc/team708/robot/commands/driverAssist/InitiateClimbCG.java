package org.usfirst.frc.team708.robot.commands.driverAssist;

import org.usfirst.frc.team708.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import  org.usfirst.frc.team708.robot.commands.climber.*;

import edu.wpi.first.wpilibj.command.Command;

public class InitiateClimbCG extends CommandGroup {

    public InitiateClimbCG() {  	        
        addSequential(new ClimbStage1());
        addSequential(new ClimbStage2());
        addSequential(new ClimbStage3());
        addSequential(new ClimbStage4());
        addSequential(new ClimbStage5());
        addSequential(new ClimbStage6());
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Robot.climber.climbStarted;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
        Robot.climber.stopAll();
        end();
    }
}