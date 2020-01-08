package org.usfirst.frc.team708.robot.commands.climber;

import org.usfirst.frc.team708.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ClimberStop extends Command {

    public ClimberStop() { 
        requires(Robot.climber);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.climber.stopAll();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.climber.climbStarted = false;
        Robot.climber.stage1 = false;
        Robot.climber.stage2 = false;
        Robot.climber.stage3 = false;
        Robot.climber.stage4 = false;
        Robot.climber.stage5 = false;
        Robot.climber.stopAll();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        Robot.climber.stopAll();
        return true;
    }
    
    // Called once after isFinished returns true
    protected void end() {
     Robot.climber.stopAll();
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}
