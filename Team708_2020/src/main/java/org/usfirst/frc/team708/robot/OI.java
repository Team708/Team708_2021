package org.usfirst.frc.team708.robot;

import edu.wpi.first.wpilibj.buttons.*;
import org.usfirst.frc.team708.robot.util.Gamepad;
import org.usfirst.frc.team708.robot.util.triggers.*;
import org.usfirst.frc.team708.robot.commands.drivetrain.*;
import org.usfirst.frc.team708.robot.commands.visionProcessor.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
	
// Gamepads
	public final static Gamepad driverGamepad 	= new Gamepad(RobotMap.driverGamepad);	// Driver gamepad
	public final static Gamepad operatorGamepad = new Gamepad(RobotMap.operatorGamepad);// Operator gamepad
	
// look in Gamepad.java for button constants
	
/*
 * Driver Button Assignment
 */

// Driver Buttons
	private static final int FIND_HIGHGOAL_BUTTON 		    = Gamepad.button_R_Shoulder; 	// boolean

	private static final int TURN_BACK_TO_ZERO 				= Gamepad.button_Y;  			//go back to 0
	private static final int TURN_90_CLOCKWISE				= Gamepad.button_B;  			//Turn 90 clockwise 
	private static final int TURN_90_COUNTER_CLOCKWISE		= Gamepad.button_X;  			//turn 90 counter clockwise

/*
 * OPEN BUTTONS
 * 
  
    private static final int <BUTTON_DESCRIPTION>			= Gamepad.button_L_Shoulder; 	// boolean
	private static final int <BUTTON_DESCRIPTION>			= Gamepad.button_A;          	// boolean
	private static final int <BUTTON_DESCRIPTION>			= Gamepad.button_Back;       	// boolean
	private static final int <BUTTON_DESCRIPTION>			= Gamepad.button_Start;      	// boolean
	private static final int <BUTTON_DESCRIPTION>			= Gamepad.button_LeftStick;  	// boolean
	private static final int <BUTTON_DESCRIPTION>			= Gamepad.button_RighttStick;	// boolean

	private static final int  <BUTTON_DESCRIPTION>			= Gamepad.shoulderAxisLeft;  // left TRIGER  -1.0 - 0.0
	private static final int  <BUTTON_DESCRIPTION>			= Gamepad.shoulderAxisRight; // right trigger 0.0 -1.0

	private static final int  <BUTTON_DESCRIPTION>			= Gamepad.dpadAXIX;          // 0-360 degress

 //defined in drive train for driver console
    private static final int <BUTTON_DESCRIPTION>			= Gamepad.leftStick_X; 			// -1.0 - 1.0
    private static final int <BUTTON_DESCRIPTION>			= Gamepad.leftStick_Y;			// -1.0 - 1.0
	private static final int <BUTTON_DESCRIPTION>			= Gamepad.rightStick_X;			// -1.0 - 1.0
	private static final int <BUTTON_DESCRIPTION>			= Gamepad.rightStick_Y;			// -1.0 - 1.0
*/


/*
 * Operator Button Assignment
 */
	
/*
 * OPEN BUTTONS
 * 
  
    private static final int <BUTTON_DESCRIPTION>			= Gamepad.button_L_Shoulder; 	// boolean
    private static final int <BUTTON_DESCRIPTION>			= Gamepad.button_R_Shoulder; 	// boolean
	private static final int <BUTTON_DESCRIPTION>			= Gamepad.button_Y;          	// boolean
	private static final int <BUTTON_DESCRIPTION>			= Gamepad.button_B;          	// boolean
	private static final int <BUTTON_DESCRIPTION>			= Gamepad.button_A;          	// boolean
	private static final int <BUTTON_DESCRIPTION>			= Gamepad.button_X;          	// boolean
	private static final int <BUTTON_DESCRIPTION>			= Gamepad.button_Back;       	// boolean
	private static final int <BUTTON_DESCRIPTION>			= Gamepad.button_Start;      	// boolean
	private static final int <BUTTON_DESCRIPTION>			= Gamepad.button_LeftStick;  	// boolean
	private static final int <BUTTON_DESCRIPTION>			= Gamepad.button_RighttStick;	// boolean

	private static final int  <BUTTON_DESCRIPTION>			= Gamepad.shoulderAxisLeft;  // left TRIGER  -1.0 - 0.0
	private static final int  <BUTTON_DESCRIPTION>			= Gamepad.shoulderAxisRight; // right trigger 0.0 -1.0

	private static final int  <BUTTON_DESCRIPTION>			= Gamepad.dpadAXIX;          // 0-360 degress

    private static final int <BUTTON_DESCRIPTION>			= Gamepad.leftStick_X; 			// -1.0 - 1.0
    private static final int <BUTTON_DESCRIPTION>			= Gamepad.leftStick_Y;			// -1.0 - 1.0
	private static final int <BUTTON_DESCRIPTION>			= Gamepad.rightStick_X;			// -1.0 - 1.0
	private static final int <BUTTON_DESCRIPTION>			= Gamepad.rightStick_Y;			// -1.0 - 1.0
*/


/*
 * Driver Button events
 */
	public static final Button FindHighGoal			= new JoystickButton(driverGamepad, FIND_HIGHGOAL_BUTTON);
	public static final Button TurnToFront			= new JoystickButton(driverGamepad, TURN_BACK_TO_ZERO);
	public static final Button TurnClockWise		= new JoystickButton(driverGamepad, TURN_90_CLOCKWISE);
	public static final Button TurnCounterClockwise	= new JoystickButton(driverGamepad, TURN_90_COUNTER_CLOCKWISE);

	
/*
 * Driver Trigger events
 */

	//	public static final Trigger highGearEngaged	= new AxisUp(driverGamepad, HOLDGEARHIGH);
	//	public static final Trigger highGearRelease	= new AxisDown(driverGamepad, HOLDGEARHIGH);
	//	public static final Trigger lowGear			= new AxisUp(driverGamepad, HOLDGEARHIGH);
	//	public static final Trigger lowGear			= new AxisDown(driverGamepad, HOLDGEARHIGH);
	

/*
 * Operator Button events
 */
	// public static final Button hatchIn			= new JoystickButton(operatorGamepad, HATCH_IN_BUTTON);
	// public static final Button hatchOut			= new JoystickButton(operatorGamepad, HATCH_OUT_BUTTON);


    public OI() {

// Driver
		// FindHighGoal.whenPressed(new TargetHighGoal());				
		// TurnToFront.whenPressed(new RotateToZero());				
		// TurnClockWise.whenPressed(new RotateClockwise());				
		// TurnCounterClockwise.whenPressed(new RotateCounterClockwise());				
		
//Operator


/*
 		.whileActive(new 
		.whenPressed(new
		.whileHeld(new
		.whenReleased(new 
*/
		}
}

