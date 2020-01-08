package org.usfirst.frc.team708.robot.commands.visionProcessor;

import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.Constants;

import edu.wpi.first.wpilibj.command.Command;
//
///**
// *
// */
public class FindRocket extends Command {
    
    private double maxTime      = 4.0;
    private boolean notAligned  = false;
    // private double targetArea   = Constants.ROCKET_HATCH_TARGET_AREA;
    private double targetY      = Constants.TARGET_Y;

    public FindRocket() {
        this.setTimeout(maxTime);
        requires(Robot.drivetrain);
    }
   
    // Called just before this Command runs the first time
    protected void initialize() {
        notAligned = false;
        Robot.visionProcessor.setNTInfo("led", Constants.VISION_LED_ON);
        Robot.drivetrain.setBrakeMode(true);
        Robot.drivetrain.shiftGearlow();

        if (Robot.intake.hasBall()) {
            Robot.visionProcessor.setNTInfo("pipeline", 1);
        }
        else {
            Robot.visionProcessor.setNTInfo("pipeline", 0);
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if  (Robot.visionProcessor.seesTarget()) {
            if (!Robot.visionProcessor.isCentered()) {
                // if (!Robot.visionProcessor.isAtY(targetY)) {
                Robot.drivetrain.curvatureDrive(Robot.visionProcessor.getMoveRocket(targetY), 
                                                    Robot.visionProcessor.getRotate(), false);
                // }
            //  Robot.drivetrain.haloDrive(Robot.visionProcessor.getMoveRocket(targetY),
            //                                      Robot.visionProcessor.getRotate(), false);
        }
            else {
                  Robot.drivetrain.curvatureDrive(Robot.visionProcessor.getMoveRocket(targetY), 0.0, false);
            //    Robot.drivetrain.haloDrive(Robot.visionProcessor.getMoveRocket(targetY),
            //                                                    Robot.visionProcessor.getRotate(), false);
            }

        }
        else {
            notAligned = false;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (((Robot.visionProcessor.isCentered()) && (Robot.visionProcessor.isAtY(targetY)))
                        || notAligned || isTimedOut());    
        //  return ((Robot.visionProcessor.isCentered())
        //                              || notAligned || isTimedOut());
    }

    // Called once after isFinished returns true
    protected void end() {
        // Robot.drivetrain.setBrakeMode(false);
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
