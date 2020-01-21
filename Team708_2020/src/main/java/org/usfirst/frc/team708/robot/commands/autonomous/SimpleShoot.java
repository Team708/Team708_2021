package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.autonomous.*;
import org.usfirst.frc.team708.robot.commands.shooter.Feed;
import org.usfirst.frc.team708.robot.commands.shooter.Stop;
import org.usfirst.frc.team708.robot.commands.shooter.shootAuto;
import org.usfirst.frc.team708.robot.commands.visionProcessor.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.shuffleboard.*;



import edu.wpi.first.wpilibj.command.Command;

/**
 * this does nothing
 */
public class SimpleShoot extends CommandGroup {

    public SimpleShoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        addSequential(new shootAuto());
        addSequential(new WaitCommand(1));
        addSequential(new Feed());
        addSequential(new WaitCommand(1));
        addSequential(new Stop());
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
