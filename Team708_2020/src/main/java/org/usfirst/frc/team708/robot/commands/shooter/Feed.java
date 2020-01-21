package org.usfirst.frc.team708.robot.commands.shooter;

import org.usfirst.frc.team708.robot.subsystems.Pigeon;
import org.usfirst.frc.team708.robot.subsystems.Shooter;
import org.usfirst.frc.team708.robot.subsystems.VisionProcessor;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
// import org.usfirst.frc.team254.lib.util.math.*;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import java.lang.*;

public class Feed extends Command {

    // private Translation2d driveVector;

    public Feed() {
    
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.shooter.feederOn();

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true; 
    }

    // Called once after isFinished returns true
    protected void end() {
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
