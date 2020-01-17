// package org.usfirst.frc.team708.robot.commands.drivetrain;

// import org.usfirst.frc.team708.robot.Constants;
// import org.usfirst.frc.team708.robot.Robot;

// import edu.wpi.first.wpilibj.command.Command;

// import edu.wpi.first.wpilibj.command.*;



// public class DriveDirectionDistanceOrTime extends Command {

// 	private double 		 targetDirection;
// 	private double 		 targetDistance;
// 	private double 		 tspeed;
	
  
//     public DriveDirectionDistanceOrTime(double direction, double distance, double speed, double maxTime) {
       
//         this.tspeed = speed;
//         this.targetDistance = distance;
//         this.targetDirection = direction;
//     	this.setTimeout(maxTime);
//     }

//     // Called just before this Command runs the first time
//     // Enables the PIDController (if it was not already) before attempting to drive straight
//     protected void initialize() {
//     	Robot.drivetrain.resetEncoder();
//     }

//     // Called repeatedly when this Command is scheduled to run
//     protected void execute() {
//     	// Robot.drivetrain.haloDrive(tspeed, rotate, false);
//     }

//     // Make this return true when this Command no longer needs to run execute()
//     protected boolean isFinished() {
//         if(targetDirection >= 270 || targetDirection < 90)
//         	return (Robot.drivetrain.getEncoderDistanceLeft() >= targetDistance) || isTimedOut();
//         else
//         	return (Robot.drivetrain.getEncoderDistanceLeft() <= targetDistance) || isTimedOut();
//     }

//     // Called once after isFinished returns true
//     protected void end() {
//         Robot.drivetrain.stop();
//     }

//     // Called when another command which requires one or more of the same
//     // subsystems is scheduled to run
//     protected void interrupted() {
//     	end();
//     }
// }
