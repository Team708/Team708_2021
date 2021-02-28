package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.commands.swerve.DriveStraightCommand;
import org.usfirst.frc.team708.robot.commands.swerve.StopAtDistanceCommand;
import org.usfirst.frc.team708.robot.commands.intake.ExtendIntakeCommand;
import org.usfirst.frc.team708.robot.commands.shooter.shootAutoCommand;
import org.usfirst.frc.team708.robot.commands.shooter.shootAutoCommandShort;
// import org.usfirst.frc.team708.robot.commands.swerve.RotateinPlaceCommand;
// import org.usfirst.frc.team708.robot.commands.visionProcessor.*;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

//********************************************************* */
//8 ball need to fix shoot commands inorder to work!!
//********************************************************* */
public class EightBallAuto extends CommandGroup {

    public EightBallAuto() {
    
        addSequential(new ExtendIntakeCommand());
        addSequential(new WaitCommand(1));
        
        addSequential(new DriveStraightCommand(180, 0.7));
        addSequential(new StopAtDistanceCommand(120, 5.0));
 
        // addSequential(new WaitCommand(.5));
        // addSequential(new FindTargetCommand());

        // addSequential(new WaitCommand(.5));
        addSequential(new shootAutoCommandShort());
        // addSequential(new WaitCommand(1.0));
        
        // addSequential(new RotateinPlaceCommand(10)); //clockwise
        // addSequential(new WaitCommand(1.0));
        // addSequential(new RotateinPlaceCommand(0));  //clockwise

        addSequential(new DriveStraightCommand(180, 0.5));
        addSequential(new StopAtDistanceCommand(110, 2.0));

        // addSequential(new RotateinPlaceCommand(-8));  //counter clockwise
        addSequential(new WaitCommand(0.5));
        
        addSequential(new DriveStraightCommand(5, 0.8));
        addSequential(new StopAtDistanceCommand(145, 3.0));

        // addSequential(new FindTargetCommand());

        // addSequential(new WaitCommand(.5));
        addSequential(new shootAutoCommand());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //Nothing goes here
    }


    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
