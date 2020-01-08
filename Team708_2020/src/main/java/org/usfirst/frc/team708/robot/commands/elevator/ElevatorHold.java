package org.usfirst.frc.team708.robot.commands.elevator;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ElevatorHold extends Command {

    public ElevatorHold() {
        requires(Robot.elevator);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	}    	
  
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.elevator.goToPosition(Robot.elevator.elev_position);
    }    	

    protected boolean isFinished()
    {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    }
}