package org.usfirst.frc.team708.robot.commands.driverAssist;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.EndDriverAssistCG;
import org.usfirst.frc.team708.robot.commands.drivetrain.StartDriverAssistCG;
import org.usfirst.frc.team708.robot.commands.visionProcessor.FindCargoship;
import  org.usfirst.frc.team708.robot.commands.intake.CloseBeak;
import  org.usfirst.frc.team708.robot.commands.intake.ExtendHatch;
import  org.usfirst.frc.team708.robot.commands.intake.RetractHatch;
import  org.usfirst.frc.team708.robot.commands.intake.AutoIntakeBallOutForTime;
import  org.usfirst.frc.team708.robot.commands.intake.DeployGamePiece;
import  org.usfirst.frc.team708.robot.commands.elevator.ElevatorToCargoship;
import  org.usfirst.frc.team708.robot.commands.elevator.ElevatorToGround;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj.command.Command;


public class CargoshipCG extends CommandGroup {

    public CargoshipCG() {
        addSequential(new StartDriverAssistCG());
        addSequential(new ElevatorToGround());

        addSequential(new FindCargoship());
        addSequential(new ElevatorToCargoship());
        
        // addSequential(new ExtendHatch());
        // addSequential(new DriveStraightToEncoderDistanceOrTime(20, Constants.ASSIST_MOVE_SPEED, 2.0));
        // // addSequential(new WaitCommand(1.0));
        
        // // addSequential(new DeployGamePiece());
        // addSequential(new CloseBeak());

        // addParallel(new ExtendHatch());
        // addSequential(new AutoIntakeBallOutForTime(1.0));
        
        // // addSequential(new WaitCommand(.2));
        // addSequential(new RetractHatch());
        // addSequential(new DriveStraightToEncoderDistanceOrTime(-Constants.ASSIST_DISTANCE, -Constants.ASSIST_MOVE_SPEED, 2.0));
        // addSequential(new ElevatorToGround());
        addSequential(new EndDriverAssistCG());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Robot.drivetrain.runningCG;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
