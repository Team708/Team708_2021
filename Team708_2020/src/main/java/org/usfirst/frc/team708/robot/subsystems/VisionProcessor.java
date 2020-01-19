package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
// import org.usfirst.frc.team708.robot.subsystems.Drivetrain;
// import org.usfirst.frc.team708.robot.subsystems.Intake;
// import org.usfirst.frc.team708.robot.util.Math708;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *	@author Viet Tran
 */
public class VisionProcessor extends Subsystem {
	
	// public static Drivetrain 		drivetrain;
	// private static Intake			intake;

	private boolean isCentered 	= false;
	private boolean led 		= false;
	private boolean isAtY		= false;
	private boolean isAtArea	= false;
	public double robotSide;

//	Required Network Table Data 	
	private boolean seesTarget;	//Get from Network Table
	private double tv;
	private double xAngle;	//Get from Network Table
	private double yAngle;
	private double area;
	private double difference;

//	Accessing the Limelight's Network Table	
	private NetworkTableInstance 	limeLightInstance 	= NetworkTableInstance.getDefault();
	private NetworkTable			limeLightTable		= limeLightInstance.getTable("/limelight");
	   	
	private double rotate			= 0.0;
	private double move				= 0.0;
	
//	Method for getting different data from a Network Table	
	public double getNTInfo(String tableInfo) {
		NetworkTableEntry limeLightEntry = limeLightTable.getEntry(tableInfo);		
		return limeLightEntry.getDouble(0);	
		 
	}
	
//	Method for setting different data into a Network Table    
	public void setNTInfo(String tableInfo, int setValue) {
		NetworkTableEntry limeLightEntry = limeLightTable.getEntry(tableInfo);		
		limeLightEntry.setNumber(setValue);
	}

	public VisionProcessor() {
		super("Vision Processor");
	}

	public boolean seesTarget() {
		tv = getNTInfo("tv");
		if (tv != 0.0) 
			seesTarget = true;		
		else 
			seesTarget = false;		
		return seesTarget;
	}
	public void toggleLEDMode() {
		led = !led;
		if(led) 
			setNTInfo("ledMode", Constants.kVISION_LED_ON);		
		else 
			setNTInfo("ledMode", Constants.kVISION_LED_OFF);		
	}

	public boolean isAtY(double targetY) {
		yAngle = getNTInfo("ty");
		difference = yAngle - targetY;
		
		if (Math.abs(difference) <= Constants.kVISION_Y_THRESHOLD)
			isAtY = true;			
		else 
			isAtY = false;			
		return isAtY;
	}

	public boolean isCentered() {
		xAngle = getNTInfo("tx");	
		if (Math.abs(xAngle) <= Constants.kVISION_X_THRESHOLD) 
			isCentered = true;		
		else
			isCentered = false;
		return isCentered;
	}

	public double getRotate() {	
		return getNTInfo("tx");
	}

	public void findTarget() {
		double angle = getRotate();
		Swerve.getInstance().rotateDegreesfromPosition(angle);
		

	}
	
	// public double getMoveCargoship (double targetValue) {
	// 	if (seesTarget)
	// 		if (!isAtY(targetValue))
	// 			if (yAngle < (targetValue - Constants.Y_THRESHOLD))
	// 				move = 0.0;				
	// 			else if (yAngle > (targetValue + Constants.Y_THRESHOLD))
	// 				move = Constants.VISION_MOVE;
	// 			else 
	// 				move = 0.0;
	// 		else
	// 			move = 0.0;
	// 	else
	// 		move = 0.0;
	// 	return move;
	// }
	
	// public double getMoveRocket(double targetValue) {	
	// 	if (seesTarget()) 
	// 		if (!isAtY(targetValue)) 	
	// 			if (Robot.intake.hasBall()) {
	// 				if (yAngle < (targetValue - Constants.Y_THRESHOLD))
	// 					move = Constants.VISION_MOVE;				
	// 				else if (yAngle > (targetValue + Constants.Y_THRESHOLD)) 
	// 					move = 0.0;	
	// 				else 
	// 					move = 0.0;
	// 			}
	// 			else {
	// 				if (yAngle < (targetValue - Constants.Y_THRESHOLD))
	// 					move = 0.0;				
	// 				else if (yAngle > (targetValue + Constants.Y_THRESHOLD))
	// 					move = Constants.VISION_MOVE;
	// 				else 
	// 					move = 0.0;
	// 			}						
	// 		else  // centered
	// 			move= 0.0;			
	// 	else
	// 		move = 0.0; 
	// 	return move;
	// }

	// public boolean isAtArea(double targetArea) {
	// 	area = getNTInfo("ta");
	// 	difference = area - targetArea;
	// 	if (Math.abs(difference) <= Constants.AREA_THRESHOLD) 
	// 		isAtArea = true;		
	// 	else 
	// 		isAtArea = false;		
	// 	return isAtArea;
	// }

	// public double getMoveBall() {	
	// 	if (seesTarget()) 
	// 		if (!isAtY())	
	// 			if (yAngle > 0)
	// 				move = Constants.VISION_MOVE;
	// 			else 
	// 				move = -Constants.VISION_MOVE;
	// 		else // centered
	// 			move= 0.0;		 
	// 	else 
	// 		move = 0.0;		
	// 	return move;
	// }

	public void sendToDashboard() {
		SmartDashboard.putBoolean("Is Centered", isCentered());
		SmartDashboard.putNumber("Displacement X", xAngle);
		SmartDashboard.putNumber("Displacement Y", yAngle);
		// SmartDashboard.putBoolean("Is At Y", isAtY(Constants.TARGET_Y));
		// SmartDashboard.putBoolean("Is At Area-Ball", isAtArea(Constants.ROCKET_CARGO_TARGET_AREA));
		// SmartDashboard.putBoolean("Is At Area-Hatch", isAtArea(Constants.ROCKET_HATCH_TARGET_AREA));
		SmartDashboard.putBoolean("Has Target", seesTarget());
		SmartDashboard.putNumber("TV", tv);
		SmartDashboard.putNumber("TA", area);
	}

	public void initDefaultCommand() {
		if (Constants.DEBUG) {
		}    	
	}
    
}

