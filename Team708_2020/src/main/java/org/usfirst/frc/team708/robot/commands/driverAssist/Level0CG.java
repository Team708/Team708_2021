package org.usfirst.frc.team708.robot.commands.driverAssist;

import  org.usfirst.frc.team708.robot.commands.intake.IntakeBallIn;
import  org.usfirst.frc.team708.robot.commands.intake.DeployIntake;
import  org.usfirst.frc.team708.robot.commands.intake.RetractIntake;
import  org.usfirst.frc.team708.robot.commands.elevator.ElevatorToLevel0;
import  org.usfirst.frc.team708.robot.commands.elevator.ElevatorToGround;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.shuffleboard.*;



import edu.wpi.first.wpilibj.command.Command;

/**
 * this does nothing
 */
public class Level0CG extends CommandGroup {

    public Level0CG() {
        // Use requires() here to declare subsystem dependencies
        addSequential(new ElevatorToLevel0());
        addSequential(new DeployIntake());        
        addSequential(new IntakeBallIn());
        addSequential(new RetractIntake());
        addSequential(new ElevatorToGround());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
