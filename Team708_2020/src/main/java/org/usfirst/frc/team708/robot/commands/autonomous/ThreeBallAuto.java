package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.commands.swerve.DriveStraightCommand;
import org.usfirst.frc.team708.robot.commands.swerve.StopAtDistanceCommand;
import org.usfirst.frc.team708.robot.commands.shooter.shootAutoCommand;
import org.usfirst.frc.team708.robot.commands.intake.*;
// import org.usfirst.frc.team708.robot.commands.shooter.feedAutoCommand;
// import org.usfirst.frc.team708.robot.commands.visionProcessor.*;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ThreeBallAuto extends CommandGroup {

    public ThreeBallAuto() {
        
        // addSequential(new WaitCommand(1));
        addSequential(new ExtendIntakeCommand());
        addSequential(new WaitCommand(1));

        addSequential(new DriveStraightCommand(180, .5));
        addSequential(new StopAtDistanceCommand(50, 3));

        // addSequential(new WaitCommand(.5));
        // addSequential(new FindTargetCommand());

        addSequential(new shootAutoCommand());
        // addSequential(new WaitCommand(2.0));
        // addSequential(new feedAutoCommand());
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
