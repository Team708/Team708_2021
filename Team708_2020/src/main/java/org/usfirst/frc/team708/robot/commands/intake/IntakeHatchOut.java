// package org.usfirst.frc.team708.robot.commands.intake;

// import org.usfirst.frc.team708.robot.Constants;
// import org.usfirst.frc.team708.robot.Robot;

// import edu.wpi.first.wpilibj.command.Command;

// /**
//  *@author James_Makovics
//  *@author Alex Tysak
//  *@author Thomas Zhao
//  */
// public class IntakeHatchOut extends Command {

	
//     public IntakeHatchOut() {
//     }
    

//     // Called just before this Command runs the first time
//     protected void initialize() {
//     	Robot.intake.moveMotorHatch(Constants.HATCH_OUT);
//     }

//     // Called repeatedly when this Command is scheduled to run
//     protected void execute() {
//     }

//     // Make this return true when this Command no longer needs to run execute()
//     protected boolean isFinished() {
//     	return(false);
//     }

//     // Called once after isFinished returns true
//     protected void end() {
//     	Robot.intake.stopMotorHatch();
//     }

//     // Called when another command which requires one or more of the same
//     // subsystems are scheduled to run
//     protected void interrupted() {
//     	end();
//     }
// }
