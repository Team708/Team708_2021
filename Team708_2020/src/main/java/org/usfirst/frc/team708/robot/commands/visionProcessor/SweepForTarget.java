// package org.usfirst.frc.team708.robot.commands.visionProcessor;

// import org.usfirst.frc.team708.robot.Robot;
// import org.usfirst.frc.team708.robot.Constants;

// import edu.wpi.first.wpilibj.command.Command;
// //
// ///**
// // *
// // */
// public class SweepForTarget extends Command {
    
//     private double currentAngle;
//     private double sweepRotate;

//     public SweepForTarget() {
//     }
   
//     // Called just before this Command runs the first time
//     protected void initialize() {
//         Robot.drivetrain.resetGyro();
//     }
    
//     // Called repeatedly when this Command is scheduled to run
//     protected void execute() {
//         Robot.drivetrain.haloDrive(0.0, sweepRotate, false);
//         currentAngle = Robot.drivetrain.getAngle();

//         if (!Robot.visionProcessor.seesTarget()) {
//             if (currentAngle >= 30) {
//                 sweepRotate = -0.2;
//             }
//             else if (currentAngle >= -30) {
//                 sweepRotate = 0.2;
//             }
//             else {
//                 sweepRotate = Robot.visionProcessor.robotSide;
//             }
//         }
//     }

//     // Make this return true when this Command no longer needs to run execute()
//     protected boolean isFinished() {
//         return Robot.visionProcessor.isCentered(); //Robot.visionProcessor.seesTarget()
//     }

//     // Called once after isFinished returns true
//     protected void end() {
//     }

//     // Called when another command which requires one or more of the same
//     // subsystems is scheduled to run
//     protected void interrupted() {
//     }
// }
