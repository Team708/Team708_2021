// package org.usfirst.frc.team708.robot.commands.elevator;

// import org.usfirst.frc.team708.robot.Constants;
// import org.usfirst.frc.team708.robot.Robot;

// import edu.wpi.first.wpilibj.command.Command;

// public class ElevatorUp extends Command {

//     public ElevatorUp() {
//     }
    
//     // Called just before this Command runs the first time
//     protected void initialize() {
//     }

//     // Called repeatedly when this Command is scheduled to run
//     protected void execute() {
//     }

//     // Make this return true when this Command no longer needs to run execute()
//     protected boolean isFinished() {
//         if(Robot.elevator.isElevatorMax()) {
//             Robot.elevator.moveMotor(Constants.ELEVATOR_STOP); 
//             return true;
//         }
//         else {
//             Robot.elevator.moveMotor(Constants.ELEVATOR_MOTOR_UP);
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
