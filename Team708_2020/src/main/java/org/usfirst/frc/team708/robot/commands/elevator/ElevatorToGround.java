package org.usfirst.frc.team708.robot.commands.elevator;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorToGround extends Command {
	
	
public ElevatorToGround() {
    // requires(Robot.elevator);
}
    

// Called just before this Command runs the first time
protected void initialize() {
        Robot.elevator.elev_position = Constants.ELEV_GROUND;
	}    	


// Called repeatedly when this Command is scheduled to run
protected void execute() {
    }

// Make this return true when this Command no longer needs to run execute()
protected boolean isFinished() {
    Robot.drivetrain.resetEncoder(); 
        if (Robot.elevator.getEncoderDistance() <= (Robot.elevator.elev_position-Constants.ELEV_TOLERANCE))
            return false;
        else if (Robot.elevator.getEncoderDistance() > (Robot.elevator.elev_position+Constants.ELEV_TOLERANCE))
            return false;
        else 
            return true;
    // }	
    // else
    // {
    //     // Robot.elevator.moveMotor(Constants.ELEVATOR_MOTOR_DOWN); 
    //     return false;
    // }
    }

    // Called once after isFinished returns true
    protected void end() {
        // Robot.elevator.moveMotor(Constants.ELEVATOR_STOP); 
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}