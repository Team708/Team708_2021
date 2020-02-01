package org.usfirst.frc.team708.robot.commands.visionProcessor;
import org.usfirst.frc.team708.robot.subsystems.Pigeon;
import org.usfirst.frc.team708.robot.subsystems.Swerve;
import org.usfirst.frc.team708.robot.subsystems.VisionProcessor;
import org.usfirst.frc.team708.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
// import org.usfirst.frc.team254.lib.util.math.*;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import java.lang.*;

public class FindTargetCommand extends Command {

    // private Translation2d driveVector;

    public FindTargetCommand() {
    
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Swerve.timesCalled++;
        //Robot.visionprocessor.toggleLEDMode();
        Robot.visionprocessor.findTarget();

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // Robot.swerve.updatePose(Timer.getFPGATimestamp());
        // Robot.swerve.updateControlCycle(Timer.getFPGATimestamp());
        // Robot.swerve.lastUpdateTimestamp = Timer.getFPGATimestamp();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // return (-1.5<Robot.visionprocessor.getNTInfo("tx") & Robot.visionprocessor.getNTInfo("tx")<1.5);
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
