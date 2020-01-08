package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureToDegreesOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureToEncoderOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegrees;
import org.usfirst.frc.team708.robot.commands.drivetrain.GearLow;
import org.usfirst.frc.team708.robot.commands.drivetrain.GearHigh;
import org.usfirst.frc.team708.robot.commands.driverAssist.Level1CG;
import org.usfirst.frc.team708.robot.commands.driverAssist.FindFeederCG;

import org.usfirst.frc.team708.robot.commands.elevator.ElevatorToLevel1;
import org.usfirst.frc.team708.robot.commands.visionProcessor.FindRocket;
import org.usfirst.frc.team708.robot.commands.intake.ExtendHatch;
import org.usfirst.frc.team708.robot.commands.drivetrain.StartAutoCG;
import org.usfirst.frc.team708.robot.commands.drivetrain.EndAutoCG;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class cargoLeftSide extends CommandGroup {
   
    // Called just before this Command runs the first time
    protected void initialize() {
    }
	
    public  cargoLeftSide() {
                addSequential(new StartAutoCG());
                addSequential(new GearHigh());
        // Leave HAB2 and Square off position
                addSequential(new DriveStraightToEncoderDistanceOrTime(-50,-.8, 2.0));
                addSequential(new WaitCommand(1.0));
        
                addSequential(new DriveStraightToEncoderDistanceOrTime(40, .8, 3.0));
                // addSequential(new WaitCommand(1.0));
        
        // Place 1st HATCH onto CARGOSHIP        
                addSequential(new DriveCurvatureToEncoderOrTime(-6, -.1, false, -175, 4.0));
                // addSequential(new DriveStraightToEncoderDistanceOrTime(180, .8, 4.0));
                // addSequential(new WaitCommand(.2));
        
                addSequential(new TurnToDegrees(.6, -65));
                addSequential(new WaitCommand(1.0));
        
                // addSequential(new Level1CG()); 
                
                addSequential(new FindRocket());
                addSequential(new ElevatorToLevel1());
                addSequential(new ExtendHatch());
                addSequential(new WaitCommand(1.0));
                addSequential(new DriveStraightToEncoderDistanceOrTime(20, Constants.ASSIST_MOVE_SPEED, 3.0));
                addSequential(new GearLow());
                addSequential(new EndAutoCG());

        // Aquire 2nd HATCH from the FEEDER
                // addSequential(new TurnToDegrees(.6, 95));
                // addSequential(new WaitCommand(0.2));
        
                // addSequential(new DriveStraightToEncoderDistanceOrTime(170, .8, 3.0));
                // addSequential(new WaitCommand(0.2));
        
                // addSequential(new FindFeederCG());
        
        // Place 2nd HATCH onto CARGOSHIP    
                // addSequential(new DriveCurvatureToEncoderOrTime(-.8, .1, false, -260, 3.0));
        
                // addSequential(new TurnToDegrees(.6, -100));
                // addSequential(new WaitCommand(0.5));
        
                // addSequential(new Level1CG()); 
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
