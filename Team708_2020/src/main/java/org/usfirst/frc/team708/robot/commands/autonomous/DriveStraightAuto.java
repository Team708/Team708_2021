package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.commands.swerve.DriveStraightCommand;
import org.usfirst.frc.team708.robot.commands.swerve.RotateinPlaceCommand;
import org.usfirst.frc.team708.robot.commands.swerve.StopAtDistanceCommand;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.autonomous.*;
import org.usfirst.frc.team708.robot.commands.shooter.shootAutoCommand;
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
public class DriveStraightAuto extends CommandGroup {

    public DriveStraightAuto() {


        // addSequential(new WaitCommand(1));
        // addSequential(new RotateinPlasceCommand(14));  //clockwise

        // addSequential(new WaitCommand(1));
        // addSequential(new DriveStraightCommand(0, .5));
        // addSequential(new StopAtDistanceCommand(20, 2));
        
        // addSequential(new WaitCommand(1));
        // addSequential(new RotateinPlaceCommand(90));
        
        addSequential(new WaitCommand(1));
        addSequential(new DriveStraightCommand(100, .5));  //+ is counterclockwise
        addSequential(new StopAtDistanceCommand(30, 2));
        
        // addSequential(new WaitCommand(1));
        // addSequential(new DriveStraightCommand(170, .5));
        // addSequential(new StopAtDistanceCommand(20, 2));
        
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
