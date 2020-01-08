package org.usfirst.frc.team708.robot.commands.visionProcessor;

import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.Constants;

import edu.wpi.first.wpilibj.command.Command;

public class FindCargoship extends Command {
    
    private double maxTime = 4.0;
    private boolean notAligned = false;
    private double targetY      = Constants.TARGET_Y;

    public FindCargoship() {
       this.setTimeout(maxTime);
    }
   
    // Called just before this Command runs the first time
    protected void initialize() {
        notAligned = false;
        Robot.drivetrain.setBrakeMode(true);
        Robot.drivetrain.shiftGearlow();
        Robot.visionProcessor.setNTInfo("pipeline", 0);
        Robot.visionProcessor.setNTInfo("led", Constants.VISION_LED_ON);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if  (Robot.visionProcessor.seesTarget()) {
            if (!Robot.visionProcessor.isCentered()) {
                // Robot.drivetrain.curvatureDrive(Robot.visionProcessor.getMoveCargoship(targetY), 
                //                                         Robot.visionProcessor.getRotate(), false);
             Robot.drivetrain.haloDrive(Robot.visionProcessor.getMoveCargoship(targetY),
                                                     Robot.visionProcessor.getRotate(), false);
            }
            else {
                // Robot.drivetrain.curvatureDrive(Robot.visionProcessor.getMoveCargoship(targetY), 0.0, false);
             Robot.drivetrain.haloDrive(Robot.visionProcessor.getMoveCargoship(targetY),
                                                                Robot.visionProcessor.getRotate(), false);
            }
            // }
        }
        else {
            notAligned = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (((Robot.visionProcessor.isCentered()) && (Robot.visionProcessor.isAtY(targetY)))
                             || notAligned || isTimedOut());
        // return ((Robot.visionProcessor.isCentered())
        //                              || notAligned || isTimedOut());
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
