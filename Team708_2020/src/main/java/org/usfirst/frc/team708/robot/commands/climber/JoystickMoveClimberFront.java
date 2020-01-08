package org.usfirst.frc.team708.robot.commands.climber;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.util.Gamepad;
import edu.wpi.first.wpilibj.command.Command;

public class JoystickMoveClimberFront extends Command {

  private double moveSpeed;

  public JoystickMoveClimberFront() {
  }

  // Called just before this Command runs the first time
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    moveSpeed = OI.climbingGamepad.getAxis(Gamepad.leftStick_Y);
    Robot.climber.moveFrontMotor(moveSpeed);

    if((Robot.climber.frontExtend()) && (moveSpeed < 0)) {
      Robot.climber.moveFrontMotor(0.0); 
    }
    else if((Robot.climber.frontRetract()) && (moveSpeed > 0)) {
      Robot.climber.moveFrontMotor(0.0);
    }    
  }

  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    return false;
  }

    // Called once after isFinished returns true
  protected void end() {
    Robot.climber.stopAll();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
    Robot.climber.stopAll();
  }
}