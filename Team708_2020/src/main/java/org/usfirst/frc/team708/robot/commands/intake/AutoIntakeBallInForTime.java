// package org.usfirst.frc.team708.robot.commands.intake;

// import org.usfirst.frc.team708.robot.Constants;
// import org.usfirst.frc.team708.robot.Robot;


// import edu.wpi.first.wpilibj.command.Command;

// public class AutoIntakeBallInForTime extends Command {

//     public AutoIntakeBallInForTime(double maxTime) {
//     	this.setTimeout(maxTime);
//     }
    

//     // Called just before this Command runs the first time
//     protected void initialize() {
//     }

//     // Called repeatedly when this Command is scheduled to run
//     protected void execute() {
//         if(Robot.intake.hasBall())
//             Robot.intake.stopMotorBall();
//         else
//             Robot.intake.moveMotorBall(Constants.INTAKE_BALL_IN);
//     }

//     // Make this return true when this Command no longer needs to run execute()
//     protected boolean isFinished() {
//     	return (isTimedOut() || Robot.intake.hasBall());
//     }

//     // Called once after isFinished returns true
//     protected void end() {
//     	Robot.intake.stopMotorBall();
//     }

//     // Called when another command which requires one or more of the same
//     // subsystems are scheduled to run
//     protected void interrupted() {
//     	end();
//     }
// }
