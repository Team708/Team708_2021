package org.usfirst.frc.team708.robot.commands.visionProcessor;
import org.usfirst.frc.team708.robot.subsystems.VisionProcessor;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FindBall extends Command {
    
    private double maxTime = 5.0;
    private boolean notAligned = false;
    public FindBall()
    {
    	this.setTimeout(maxTime);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        notAligned = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.visionprocessor.setNTInfo("ledMode", Constants.kVISION_LED_OFF);
        // Robot.visionProcessor.setNTInfo("led", Constants.VISION_LED_OFF);
        // if  (Robot.visionProcessor.seesTarget()) {
        //     if (!Robot.visionProcessor.isCentered()) {
        //         Robot.drivetrain.haloDrive(0.0, Robot.visionProcessor.getRotate(), false);
        //     }
        //     else {
        //         Robot.drivetrain.haloDrive(Robot.visionProcessor.getMoveRocket(), 0.0, false);
        //     }
        // }
        // else {
        //     notAligned = true;
        // }
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // return ((Robot.visionProcessor.isCentered()) && (Robot.visionProcessor.isAtY())) || notAligned || isTimedOut();
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
