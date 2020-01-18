package org.usfirst.frc.team708.robot.commands.swerve;

import org.usfirst.frc.team708.robot.subsystems.Swerve;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team254.lib.util.math.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.lang.*;

public class StopAtDistance extends Command {
	
    private double timeout;
    private double distance;
	
    public StopAtDistance(double distance, double timeout) {
        this.timeout = timeout;
        this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Swerve.getInstance().zeroSensors();
        timeSinceInitialized();
        Swerve.timesCalled++;
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        
        return (this.timeSinceInitialized()>=timeout) || (distance <= Math.abs(Swerve.getInstance().getDistanceInches()));
    }

    // Called once after isFinished returns true
    protected void end() {
        Swerve.getInstance().stop();
        Swerve.getInstance().zeroSensors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
