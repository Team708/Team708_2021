package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.commands.drivetrain.*;
import org.usfirst.frc.team708.robot.commands.swerve.DriveStraightCommand;
import org.usfirst.frc.team708.robot.commands.swerve.StopAtDistance;
import org.usfirst.frc.team708.robot.commands.swerve.StopatAngle;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.autonomous.*;
import org.usfirst.frc.team708.robot.commands.visionProcessor.*;
import org.usfirst.frc.team254.lib.util.math.*;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.shuffleboard.*;
import org.usfirst.frc.team708.robot.subsystems.*;



import edu.wpi.first.wpilibj.command.Command;

/**
 * this does nothing
 */
public class DriveStraight extends CommandGroup {

    public DriveStraight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        addSequential(new WaitCommand(1));
        addSequential(new DriveStraightCommand(0, .1));
        //addSequential(new StopatAngle(180, 5));
        // addSequential(new WaitCommand(.5));
        // addSequential(new DriveStraightCommand(90, .7));
        // addSequential(new StopAtDistance(60, 10));
        // addSequential(new WaitCommand(.5));
        // addSequential(new DriveStraightCommand(0, .7));
        // addSequential(new StopAtDistance(60, 10));
        // addSequential(new WaitCommand(.5));
        // addSequential(new DriveStraightCommand(270, .7));
        // addSequential(new StopAtDistance(60, 10));
        // addSequential(new WaitCommand(.5));
        // addSequential(new DriveStraightCommand(135, .7));
        // addSequential(new StopAtDistance(60, 10));
        // addSequential(new WaitCommand(.5));
        // addSequential(new DriveStraightCommand(270, .7));
        // addSequential(new StopAtDistance(60, 10));
        // addSequential(new WaitCommand(.5));
        // addSequential(new DriveStraightCommand(45, .7));
        // addSequential(new StopAtDistance(60, 10));
        // addSequential(new WaitCommand(.5));
        // addSequential(new DriveStraightCommand(270, .7));
        // addSequential(new StopAtDistance(60, 10));
        // addSequential(new WaitCommand(1));
        
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        
        //Nothing goes here

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
