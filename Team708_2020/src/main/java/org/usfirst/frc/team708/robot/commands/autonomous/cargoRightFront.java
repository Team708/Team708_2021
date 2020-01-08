package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToBallOrTime;

import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureToDegreesOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureToEncoderOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegrees;
import org.usfirst.frc.team708.robot.commands.drivetrain.GearLow;
import org.usfirst.frc.team708.robot.commands.drivetrain.GearHigh;
import org.usfirst.frc.team708.robot.commands.drivetrain.Send;
import org.usfirst.frc.team708.robot.commands.visionProcessor.*;
import org.usfirst.frc.team708.robot.commands.intake.*;
import org.usfirst.frc.team708.robot.commands.elevator.*;
import org.usfirst.frc.team708.robot.commands.driverAssist.Level1CG;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.*;

public class cargoRightFront extends CommandGroup {
   
    // Called just before this Command runs the first time
    protected void initialize() {
    }
	
    public  cargoRightFront() {
        addSequential(new GearHigh());

// Leave HAB2 and Square off position
        addSequential(new DriveStraightToEncoderDistanceOrTime(-40, -.8, 3.0));
        addSequential(new WaitCommand(1.0));

        addSequential(new DriveStraightToEncoderDistanceOrTime(30, .8, 3.0));
// addSequential(new WaitCommand(1.0));

        addSequential(new DriveCurvatureToDegreesOrTime(-.6, -0.35, false, -65, 3.0));
        addSequential(new WaitCommand(1.0));
        addSequential(new TurnToDegrees(.6, -110));

        addSequential(new FindRocket());
        addSequential(new ElevatorToLevel1());
        addSequential(new ExtendHatch());
    //     addSequential(new Level1CG()); 
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
