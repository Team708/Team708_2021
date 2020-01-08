// package org.usfirst.frc.team708.robot.commands.intake;

// import org.usfirst.frc.team708.robot.Constants;
// import org.usfirst.frc.team708.robot.OI;
// import org.usfirst.frc.team708.robot.Robot;
// import org.usfirst.frc.team708.robot.RobotMap;
// //import org.team708.robot.subsystems.*;
// //import org.usfirst.frc.team708.robot.subsystems.Loader;


// import edu.wpi.first.wpilibj.Relay;
// import edu.wpi.first.wpilibj.command.Command;



// /**
//  *@author James Alex Thomas Mikhael
//  */
// public class IntakeHatchIn extends Command {

//     public IntakeHatchIn() {
// //    	requires(Robot.intakeCube);
//     }
    

//     // Called just before this Command runs the first time
//     protected void initialize() {
//     }

//     // Called repeatedly when this Command is scheduled to run
//     protected void execute() {
//     }

//     // Make this return true when this Command no longer needs to run execute()
//     protected boolean isFinished() {
//         if(Robot.intake.hasHatch()){
//             Robot.intake.stopMotorHatch();
//             return true;
//         }
//         else {
//             Robot.intake.moveMotorHatch(Constants.HATCH_IN);
//     	    return false;
//         }    }

//     // Called once after isFinished returns true
//     protected void end() {
//      Robot.intake.stopMotorHatch();

//     }

//     // Called when another command which requires one or more of the same
//     // subsystems are scheduled to run
//     protected void interrupted() {
//     	end();
//     }
// }
