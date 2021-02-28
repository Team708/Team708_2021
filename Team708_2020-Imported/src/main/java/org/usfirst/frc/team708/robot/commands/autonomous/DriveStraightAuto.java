package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.commands.swerve.DriveStraightCommand;
import org.usfirst.frc.team708.robot.commands.swerve.StopAtDistanceCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DriveStraightAuto extends CommandGroup {

    public DriveStraightAuto() {


        // addSequential(new WaitCommand(1));
        // addSequential(new RotateinPlasceCommand(14));  //clockwise

        addSequential(new WaitCommand(1));
        addSequential(new DriveStraightCommand(0, .5));
        addSequential(new StopAtDistanceCommand(60, 2));
        
        // addSequential(new WaitCommand(1));
        // addSequential(new RotateinPlaceCommand(90));
        
        addSequential(new WaitCommand(1));
        addSequential(new DriveStraightCommand(90, .5));  //+ is counterclockwise
        addSequential(new StopAtDistanceCommand(36, 2));
        
        addSequential(new WaitCommand(1));
        addSequential(new DriveStraightCommand(270, .5));
        addSequential(new StopAtDistanceCommand(24, 2));
        
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
