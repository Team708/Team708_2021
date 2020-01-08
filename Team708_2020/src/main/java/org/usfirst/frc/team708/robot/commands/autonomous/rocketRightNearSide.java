package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTime;

import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureToDegreesOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureToEncoderOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegrees;
import org.usfirst.frc.team708.robot.commands.elevator.ElevatorToGround;
import org.usfirst.frc.team708.robot.commands.drivetrain.GearHigh;
import org.usfirst.frc.team708.robot.commands.drivetrain.GearLow;
import org.usfirst.frc.team708.robot.commands.drivetrain.Send;
import org.usfirst.frc.team708.robot.commands.visionProcessor.FindRocket;
import org.usfirst.frc.team708.robot.commands.drivetrain.StartAutoCG;
import org.usfirst.frc.team708.robot.commands.drivetrain.EndAutoCG;
import org.usfirst.frc.team708.robot.commands.driverAssist.Level1CG;
import org.usfirst.frc.team708.robot.commands.driverAssist.Level2CG;
import org.usfirst.frc.team708.robot.commands.driverAssist.FindFeederCG;
import org.usfirst.frc.team708.robot.commands.drivetrain.ResetEncoder;
import org.usfirst.frc.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class rocketRightNearSide extends CommandGroup {
   
    // Called just before this Command runs the first time
    protected void initialize() {
    }
	
    public  rocketRightNearSide() {
        addSequential(new StartAutoCG());
        addSequential(new GearLow());

// Leave HAB2 and Square off position

        // addSequential(new DriveStraightToEncoderDistanceOrTime(120, .6, 3.0));
        // addSequential(new WaitCommand(1.0));

        addSequential(new DriveStraightToEncoderDistanceOrTime(45, .8, 2.0));
        addSequential(new WaitCommand(1.0));
        addSequential(new DriveStraightToEncoderDistanceOrTime(-50, -.8, 2.0));
        addSequential(new WaitCommand(2.0));

        addSequential(new DriveStraightToEncoderDistanceOrTime(48, .6, 3.0));
        addSequential(new TurnToDegrees(.6, 30)); //.7
        addSequential(new ResetEncoder());
        addSequential(new WaitCommand(1.0)); 
        addSequential(new DriveStraightToEncoderDistanceOrTime(30, .6, 3.0));

        addSequential(new WaitCommand(.5));

         addSequential(new Level2CG());
         addSequential(new ElevatorToGround()); 
        
// Aquire 2nd HATCH from the FEEDER

        addSequential(new TurnToDegrees(.6, 126)); //.7
        // addSequential(new WaitCommand(0.2));

        // addSequential(new GearHigh());
        // addSequential(new ResetEncoder());
        // addSequential(new DriveStraightToEncoderDistanceOrTime(100, .8, 3.0));
        // addSequential(new WaitCommand(1.0));

        // addSequential(new FindFeederCG());

// Curve around the ROCKET and align with the far side    
        // addSequential(new TurnToDegrees(.4, -5));
        // addSequential(new DriveStraightToEncoderDistanceOrTime(-190, -.7, 4.0));
        
        // addSequential(new WaitCommand(0.2));

// Place 2nd HATCH onto ROCKET
        // addSequential(new DriveCurvatureToEncoderOrTime(.6, .4, false, 30, 2.0));
        //     addSequential(new Level2CG());

        addSequential(new EndAutoCG());
     }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Robot.drivetrain.runningAuto;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
