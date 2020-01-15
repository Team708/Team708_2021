package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureToEncoderOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.GearLow;
import org.usfirst.frc.team708.robot.commands.drivetrain.Send;
import org.usfirst.frc.team708.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.*;

public class DriveDistance extends CommandGroup {
   
    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.drivetrain.resetEncoder();
//    	Robot.drivetrain.resetEncoder2();
//    	Robot.drivetrain.resetGyro();
    }
	
    pu() {
		// addSequential(new Sen - Start"));
    	addSequential(new GearLow());

   	    addSequential(new WaitCommand(2.0));
        //    addSequential(new DriveCurvatureToEncoderOrTime(.4, -0.1, false, 20, 1.0));
        addSequential(new DriveStraightToEncoderDistanceOrTime(60, .6, 2.0));
        // addSequential(new DriveStraightToEncoderDistanceOrTime(-120, -.8, 10.0));
        // addSequential(new Sen - End"));
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
