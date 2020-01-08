// package org.usfirst.frc.team708.robot.commands.elevator;

// import org.usfirst.frc.team708.robot.Constants;
// import org.usfirst.frc.team708.robot.Robot;
// import edu.wpi.first.wpilibj.command.Command;

// public class ElevatorHoldForBall extends Command {

//     public ElevatorHoldForBall() {
//         requires(Robot.elevator);
//         requires(Robot.intake);
//     }
    
//     // Called just before this Command runs the first time
//     protected void initialize() {
//     	}    	
  
//     // Called repeatedly when this Command is scheduled to run
//     protected void execute() {
//     	}    	

//     protected boolean isFinished()
//     {
//         Robot.elevator.goToPosition(Robot.elevator.elev_position);
//         if (Robot.intake.hasBall()) {
//             Robot.intake.stopMotorBall();
//             return true;
//         }
//         else {
//             Robot.intake.moveMotorBall(Constants.BALL_IN);
//             return false;
//         }
//     }

//     // Called once after isFinished returns true
//     protected void end() {
//         Robot.elevator.moveMotor(Constants.ELEVATOR_STOP); 
//     }

//     // Called when another command which requires one or more of the same
//     // subsystems are scheduled to run
//     protected void interrupted() {
//     	end();
//     }
// }