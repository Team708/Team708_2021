package org.usfirst.frc.team708.robot.commands.elevator;

import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.util.Gamepad;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team708.robot.Constants;

public class JoystickMoveElevator extends Command {
	
private double moveSpeed;
	
public JoystickMoveElevator() {
 // requires(Robot.elevator);
}

// Called just before this Command runs the first time
protected void initialize() {
}

// Called repeatedly when this Command is scheduled to run
protected void execute() {
    moveSpeed = OI.operatorGamepad.getAxis(Gamepad.leftStick_Y);
    
    // Robot.elevator.moveMotor(moveSpeed);
    // Robot.elevator.elev_position = Robot.elevator.getEncoderDistance();
      
    if (moveSpeed >= .2)
        Robot.elevator.elev_position +=2;
    else if (moveSpeed <= -.2)
        Robot.elevator.elev_position -=2;
    if (Robot.elevator.elev_position <= Constants.ELEV_GROUND)
        Robot.elevator.elev_position = Constants.ELEV_GROUND;
    else if (Robot.elevator.elev_position >= Constants.ELEV_MAX)
        Robot.elevator.elev_position = Constants.ELEV_MAX;

    // if(Robot.elevator.isElevatorMin() && (moveSpeed <= 0)) {
    //     // Robot.elevator.moveMotor(Constants.ELEVATOR_STOP); 
    //     Robot.elevator.elev_position = Constants.ELEV_GROUND;
    //     Robot.elevator.resetElevatorEncoder();
    // }
    // else if(Robot.elevator.isElevatorMax() && (moveSpeed >= 0)) {
    //     // Robot.elevator.moveMotor(Constants.ELEVATOR_STOP);
    //     Robot.elevator.elev_position = Constants.ELEV_MAX;
    // }
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