package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTime;

import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureToDegreesOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureToEncoderOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegrees;
import org.usfirst.frc.team708.robot.commands.drivetrain.GearLow;
import org.usfirst.frc.team708.robot.commands.drivetrain.GearHigh;
import org.usfirst.frc.team708.robot.commands.drivetrain.Send;
import org.usfirst.frc.team708.robot.commands.driverAssist.Level1CG;
import org.usfirst.frc.team708.robot.commands.drivetrain.StartAutoCG;
import org.usfirst.frc.team708.robot.commands.drivetrain.EndAutoCG;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.*;

public class cargoLeftFront extends CommandGroup {
   
    // Called just before this Command runs the first time
    protected void initialize() {
    }
	
    public  cargoLeftFront() {
        addSequential(new StartAutoCG());
        addSequential(new GearHigh());

// Leave HAB2 and Square off position
        addSequential(new DriveStraightToEncoderDistanceOrTime(-65,-1.0, 3.0));
        addSequential(new WaitCommand(1.0));

        addSequential(new DriveStraightToEncoderDistanceOrTime(45, .8, 3.0));
        addSequential(new WaitCommand(1.0));
        addSequential(new DriveStraightToEncoderDistanceOrTime(-24, -.6, 2.0));
        // addSequential(new TurnToDegrees(.6, -110), 3.0);
        addSequential(new GearLow());
        addSequential(new EndAutoCG());

        
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (!Robot.drivetrain.runningAuto);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
