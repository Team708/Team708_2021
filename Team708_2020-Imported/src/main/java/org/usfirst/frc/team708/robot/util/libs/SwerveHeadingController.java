package org.usfirst.frc.team708.robot.util.libs;

import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team708.robot.Constants;

public class SwerveHeadingController {
	private double targetHeading;
	private double disabledTimestamp;
	private double lastUpdateTimestamp;
	private final double disableTimeLength = 0.3;
	public SynchronousPIDF stabilizationPID = new SynchronousPIDF(Constants.rotateKp, Constants.rotateKi, Constants.rotateKd, Constants.rotateKf);
	private SynchronousPIDF snapPID = new SynchronousPIDF(0.015, 0.0, 0.0, 0.0);
	private SynchronousPIDF stationaryPID = new SynchronousPIDF(.01, 0.001, 0.00208, 0.0);
	
	public enum State{
		Off, Stabilize, Snap, TemporaryDisable, Stationary
	}
	private State currentState = State.Off;
	public State getState(){
		return currentState;
	}
	private void setState(State newState){
		currentState = newState;
	}
	
	public SwerveHeadingController(){
		targetHeading = 0;
		lastUpdateTimestamp = Timer.getFPGATimestamp();
	}
	
	public void setStabilizationTarget(double angle){
		targetHeading = angle;
		setState(State.Stabilize);
	}
	
	public void setSnapTarget(double angle){
		targetHeading = angle;
		setState(State.Snap);
	}
	
	public void setStationaryTarget(double angle){
		targetHeading = angle;
		setState(State.Stationary);
	}
	
	public void disable(){
		setState(State.Off);
	}
	
	public void temporarilyDisable(){
		setState(State.TemporaryDisable);
		disabledTimestamp = Timer.getFPGATimestamp();
	}
	
	public double getTargetHeading(){
		return targetHeading;
	}
	
	public double updateRotationCorrection(double heading, double timestamp){
		double correction = 0;
		double error = heading - targetHeading;
		double dt = timestamp - lastUpdateTimestamp;
		
		switch(currentState){
		case Off:
			
			break;
		case TemporaryDisable:
			targetHeading = heading;
			if(timestamp - disabledTimestamp >= disableTimeLength)
				setState(State.Stabilize);
			break;
		case Stabilize:
			correction = stabilizationPID.calculate(error, dt);
			break;
		case Snap:
			correction = snapPID.calculate(error, dt);
			break;
		case Stationary:
			correction = stationaryPID.calculate(error, dt);
			break;
		}
		
		lastUpdateTimestamp = timestamp;
		return correction;
	}
	
}
