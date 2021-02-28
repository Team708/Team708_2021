package org.usfirst.frc.team708.robot.commands.visionProcessor;
import org.usfirst.frc.team708.robot.subsystems.Swerve;
import org.usfirst.frc.team708.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class FindTargetCommand extends Command {

    public FindTargetCommand() {
    
    }

    protected void initialize() {
        Swerve.timesCalled++;
        //Robot.visionprocessor.toggleLEDMode();
        Robot.visionprocessor.findTarget();

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // return (-1.5<Robot.visionprocessor.getNTInfo("ty") & Robot.visionprocessor.getNTInfo("ty")<1.5);
        return(true);
    }

    // Called once after isFinished returns true
    protected void end() {
        // Swerve.getInstance().zeroSensors();
        // Robot.swerve.xInput = 0;
        // Robot.swerve.yInput = 0;
        // Robot.swerve.rotationalInput = 0;
        // Robot.swerve.headingController.temporarilyDisable();
        // Robot.swerve.stop();
        // Robot.swerve.lastUpdateTimestamp = Timer.getFPGATimestamp();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
