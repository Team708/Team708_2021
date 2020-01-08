package org.usfirst.frc.team708.robot.commands.climber;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.util.Gamepad;
import edu.wpi.first.wpilibj.command.Command;

public class JoystickMoveClimberRear extends Command {

    private double moveSpeed;

  public JoystickMoveClimberRear() {
  }

  // Called just before this Command runs the first time
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    moveSpeed = OI.climbingGamepad.getAxis(Gamepad.rightStick_Y);
    Robot.climber.moveRearMotor(moveSpeed*(-Constants.MOVE_CLIMBER_REAR_EXTEND));

    if((Robot.climber.rearExtend()) && (moveSpeed < 0)) {
      Robot.climber.moveRearMotor(0.0); 
    }
    else if((Robot.climber.rearRetract()) && (moveSpeed > 0)) {
    Robot.climber.moveRearMotor(0.0);
    }
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
    Robot.climber.stopAll();
  }
}