/*
*FOR TESTING GENERAL COMMANDS
*SHOULDN'T BE USED WITH ANY BALLS, ISN'T FORMATTED TO ACCURATELY MANIPULATE THEM 
*/
package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.commands.swerve.DriveStraightCommand;
import org.usfirst.frc.team708.robot.commands.swerve.RotateinPlaceCommand;
import org.usfirst.frc.team708.robot.commands.swerve.StopAtDistance;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.autonomous.*;
import org.usfirst.frc.team708.robot.commands.hopper.RotateHopper;
import org.usfirst.frc.team708.robot.commands.hopper.StopHopper;
import org.usfirst.frc.team708.robot.commands.intake.ExtendIntake;
import org.usfirst.frc.team708.robot.commands.intake.IntakeMotor;
import org.usfirst.frc.team708.robot.commands.intake.RetractIntake;
import org.usfirst.frc.team708.robot.commands.intake.StopIntake;
import org.usfirst.frc.team708.robot.commands.shooter.Feed;
import org.usfirst.frc.team708.robot.commands.shooter.shootAuto;
import org.usfirst.frc.team708.robot.commands.shooter.Stop;
import org.usfirst.frc.team708.robot.commands.visionProcessor.*;
import org.usfirst.frc.team254.lib.util.math.*;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.shuffleboard.*;
import org.usfirst.frc.team708.robot.subsystems.*;

import edu.wpi.first.wpilibj.command.Command;


public class EverythingAuto extends CommandGroup {

    public EverythingAuto() {

        //drives swerve
        addSequential(new WaitCommand(0.5));
        addSequential(new DriveStraightCommand(0, .5));
        addSequential(new StopAtDistance(20, 10));
        addSequential(new WaitCommand(0.5));
        addSequential(new RotateinPlaceCommand(360));
        addSequential(new WaitCommand(0.5));
        
        //aims target
        addSequential(new FindTarget());
        addSequential(new WaitCommand(0.5));

        //intakes
        addSequential(new ExtendIntake());
        addSequential(new WaitCommand(0.5));
        addSequential(new IntakeMotor(0.5));
        addSequential(new WaitCommand(0.5));
        addSequential(new StopIntake());
        addSequential(new WaitCommand(0.5));
        addSequential(new RetractIntake());
        addSequential(new WaitCommand(0.5));

        //moves hopper
        addSequential(new RotateHopper());
        addSequential(new WaitCommand(0.5));
        addSequential(new StopHopper());
        addSequential(new WaitCommand(0.5));

        //shoots NOTE NOT IN CORRECT WAY, FEEDS AND SHOOTS WHILE FEED AND HOPPER SHOULD BE SYNONYMOUS
        addSequential(new shootAuto());
        addSequential(new Feed());
        addSequential(new WaitCommand(0.5));
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
