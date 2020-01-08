// package org.usfirst.frc.team708.robot.commands.climber;

// import org.usfirst.frc.team708.robot.Constants;
// import org.usfirst.frc.team708.robot.Robot;

// import edu.wpi.first.wpilibj.command.Command;

// public class InitiateClimb extends Command {

//     public InitiateClimb() {  	
//     }
    

//     // Called just before this Command runs the first time
//     protected void initialize() {
//         Robot.climber.resetClimberRoller();
//         Robot.climber.stage1 = true;
//     }

//     // Called repeatedly when this Command is scheduled to run
//     protected void execute() 
//     {
//         if (Robot.climber.stage1) 
//         {
//             if (!((Robot.climber.frontRetract()) && (Robot.climber.rearRetract())) )
//             {
//                 if (Robot.drivetrain.isTiltingBack())
//                 {
//                     Robot.climber.moveFrontMotor(Constants.STOP_CLIMBER);
//                     Robot.climber.moveRearMotor(Constants.MOVE_CLIMBER_EXTEND);
//                 }
//                 else if (Robot.drivetrain.isTiltingForward())
//                 {
//                     Robot.climber.moveFrontMotor(Constants.MOVE_CLIMBER_EXTEND);
//                     Robot.climber.moveRearMotor(Constants.STOP_CLIMBER);
//                 }
//                 else
//                 {
//                     Robot.climber.moveFrontMotor(Constants.MOVE_CLIMBER_EXTEND);
//                     Robot.climber.moveRearMotor(Constants.MOVE_CLIMBER_EXTEND);
//                 } 
//             }
//             else 
//             {
//                 Robot.climber.moveFrontMotor(Constants.STOP_CLIMBER);
//                 Robot.climber.moveRearMotor(Constants.STOP_CLIMBER);
//                 Robot.climber.stage1 = false;
//                 Robot.climber.stage2 = true;
//                 Robot.climber.resetClimberRoller();
//             }
//         }
//         else if (Robot.climber.stage2) 
//         {
//             if (Robot.climber.getEncoderRoller()<Constants.REAR_CLIMBER_ROLLER_FIRST_DISTANCE) 
//             {
//                 Robot.climber.moveRollerMotor(Constants.CLIMBER_ROLLER_FORWARD);
//             }
//             else 
//             {
//                 Robot.climber.stage2 = false;
//                 Robot.climber.stage3 = true;
//                 Robot.climber.moveRollerMotor(0.0);
//             }
//         }
//         else if (Robot.climber.stage3) 
//         {
//             if (!(Robot.climber.frontExtend()))
//             {
//                 Robot.climber.moveFrontMotor(Constants.MOVE_CLIMBER_RETRACT);
//             }
//             else
//             {
//                 Robot.climber.stage3 = false;
//                 Robot.climber.stage4 = true;
//                 Robot.climber.resetClimberRoller();
//                 Robot.drivetrain.resetEncoder();
//                 Robot.climber.moveFrontMotor(Constants.STOP_CLIMBER);
//             }
//         }
//         else if (Robot.climber.stage4) 
//         {
//             if ((Robot.climber.getEncoderRoller()<Constants.REAR_CLIMBER_ROLLER_SECOND_DISTANCE))
//                 //    || (Robot.drivetrain.getEncoderDistanceLeft()<Constants.REAR_CLIMBER_ROLLER_DISTANCE_INCHES)) 
//             {
//                 Robot.climber.moveRollerMotor(Constants.CLIMBER_ROLLER_FORWARD);
//                 Robot.drivetrain.haloDrive(Constants.CLIMBER_ROLLER_FORWARD, 0.0, false);
//             }
//             else 
//             {
//                 Robot.climber.stage4 = false;
//                 Robot.climber.stage5 = true;
//                 Robot.climber.moveRollerMotor(0.0);
//                 Robot.drivetrain.stop();
//             }
//         }
//         else if (Robot.climber.stage5) 
//         {
//             if (!Robot.climber.rearExtend()) 
//             {
//                 Robot.climber.moveRearMotor(Constants.MOVE_CLIMBER_RETRACT);
//             }
//              else 
//              {
//                 Robot.climber.moveRearMotor(Constants.STOP_CLIMBER);
//                 Robot.climber.stage5 = false;
//                 Robot.climber.stage6 = true;
//                 Robot.drivetrain.resetEncoder();
//             }
//         }
//         else if (Robot.climber.stage6) 
//         {
//             if (Robot.drivetrain.getEncoderDistanceLeft()<Constants.REAR_CLIMBER_ROLLER_DISTANCE_FINAL)
//             {
//                 Robot.drivetrain.haloDrive(Constants.CLIMBER_ROLLER_FORWARD, 0.0, false);
//             }
//             else 
//             {
//                 Robot.climber.stage6 = false;
//                 Robot.climber.climbStarted = false;
//                 Robot.drivetrain.stop();
//             }
//         }
//     }

//     // Make this return true when this Command no longer needs to run execute()
//     protected boolean isFinished() {
//         if (Robot.climber.climbStarted) {
//             return false;
//         } 
//         else {
//             return true;
//         }
//     }

//     // Called once after isFinished returns true
//     protected void end() {
//         Robot.climber.stopAll();
//     }

//     // Called when another command which requires one or more of the same
//     // subsystems are scheduled to run
//     protected void interrupted() {
//         Robot.climber.stopAll();
//         end();
//     }
// }