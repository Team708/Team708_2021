package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.commands.swerve.DriveStraightCommand;
import org.usfirst.frc.team708.robot.commands.swerve.RotateinPlaceCommand;
import org.usfirst.frc.team708.robot.commands.swerve.StopAtDistanceCommand;
import org.usfirst.frc.team708.robot.commands.swerve.StopAtDistanceCommand;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.autonomous.*;
import org.usfirst.frc.team708.robot.commands.shooter.shootAutoCommand;
import org.usfirst.frc.team708.robot.commands.shooter.shootAutoCommand;
import org.usfirst.frc.team708.robot.commands.visionProcessor.*;
import org.usfirst.frc.team254.lib.util.math.*;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.shuffleboard.*;
import org.usfirst.frc.team708.robot.subsystems.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives Off Line
 */
public class EightBallAuto extends CommandGroup {

    public EightBallAuto() {
        
        addSequential(new DriveStraightCommand(180, 0.45));
        addSequential(new StopAtDistanceCommand(140, 5.0));
        
        addSequential(new WaitCommand(1.0));
        addSequential(new shootAutoCommand());
        // addSequential(new WaitCommand(1.0));
        
        // addSequential(new RotateinPlaceCommand(10)); //clockwise
        // addSequential(new WaitCommand(1.0));
        
        addSequential(new DriveStraightCommand(180, 0.45));
        addSequential(new StopAtDistanceCommand(110, 2.0));

        // addSequential(new RotateinPlaceCommand(-8));  //counter clockwise
        addSequential(new WaitCommand(1.0));
        
        addSequential(new DriveStraightCommand(5, 0.45));
        addSequential(new StopAtDistanceCommand(80, 3.0));

        addSequential(new WaitCommand(1.0));
        addSequential(new shootAutoCommand());
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
