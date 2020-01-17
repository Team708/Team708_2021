package org.usfirst.frc.team708.robot;

/**
 * Class containing all the code-related constants
 * Wiring controls are found in RobotMap and 
 * gamepad controls are found in OI
 */

import org.usfirst.frc.team254.lib.util.math.Translation2d;

public final class Constants {

	//Looper Constants
	public static final double kLooperDt = 0.02;
	
	public static final double kEpsilon = 0.0001;

/*
 * Drivetrain
 */
	public static final double	kDT_DRIVE_MOTOR_MAX_SPEED 					= 1.0; 
	public static final double	kDT_ROTATE_MOTOR_MAX_SPEED 					= 1.0;

	public static final double	kDT_TANK_STICK_TOLERANCE 					= .20;
	public static final int  	kDT_DRIVETRAIN_WHEEL_DIAMETER 				=   4; 

	//Swerve Calculations Constants
	public static final double kDT_WHEELBASE_LENGTH = 24 / 12.0; //feet
    public static final double kDT_WHEELBASE_WIDTH  = 24 / 12.0; //feet
    public static final double kDT_SWERVE_DIAGONAL = Math.hypot(kDT_WHEELBASE_LENGTH, kDT_WHEELBASE_WIDTH);
	
	//Physical Robot Dimensions
	public static final double ROBOT_WIDTH = 17.50 / 12.0;
	public static final double ROBOT_LENGTH = 17.5 / 12.0;
	public static final double ROBOT_HALF_WIDTH = ROBOT_WIDTH / 2.0;
	public static final double ROBOT_HALF_LENGTH = ROBOT_LENGTH / 2.0;
	public static final double ROBOT_INTAKE_EXTRUSION = 11.0/12.0;

    //Swerve Speed Constants
    public static final double SWERVE_ROTATION_MAX_SPEED = 1250.0;
    public static final double SWERVE_DRIVE_MAX_SPEED    = 5432.0;
	
    //Swerve Module Wheel Offsets
	public static final int FRONT_LEFT_ENCODER_STARTING_POS  = 1478+2048; // mod 0   1478 
	public static final int FRONT_RIGHT_ENCODER_STARTING_POS = 1451; // mod 1   1451
	public static final int REAR_LEFT_ENCODER_STARTING_POS   = 2600-2048; // mod 2  2600
	public static final int REAR_RIGHT_ENCODER_STARTING_POS  = 550+2048;  // mod 3  500
	
	//Swerve Module Positions
	public static final Translation2d kVehicleToModuleOne = new Translation2d(kDT_WHEELBASE_LENGTH/2, kDT_WHEELBASE_WIDTH/2);
	public static final Translation2d kVehicleToModuleTwo = new Translation2d(kDT_WHEELBASE_LENGTH/2, -kDT_WHEELBASE_WIDTH/2);
	public static final Translation2d kVehicleToModuleThree = new Translation2d(-kDT_WHEELBASE_LENGTH/2, -kDT_WHEELBASE_WIDTH/2);
	public static final Translation2d kVehicleToModuleFour = new Translation2d(-kDT_WHEELBASE_LENGTH/2, kDT_WHEELBASE_WIDTH/2);
	
	//Scrub Factors
	public static final double[] kWheelScrubFactors = new double[]{1.0, 1.0, 1.0, /*5.0/5.15*/1.0};	

	//Swerve Odometry Constants
	public static final double SWERVE_WHEEL_DIAMETER = 3.93; //inches
	public static final double ROTATION_ENCODER_RESOLUTION = 4096.0;
	public static final double DRIVE_ENCODER_RESOLUTION    = 42.0;

	//pid
	public static final double Kp = 0;
	public static final double Ki = 0;
	public static final double Kd = 0;


	/**
	 * The number of rotations the swerve drive encoder undergoes for every rotation of the wheel.
	 */
	public static final double SWERVE_ENCODER_TO_WHEEL_RATIO = 10.0/9.0;
	public static final double SWERVE_ENC_UNITS_PER_WHEEL_REV = DRIVE_ENCODER_RESOLUTION * SWERVE_ENCODER_TO_WHEEL_RATIO;
	public static final double SWERVE_ENC_UNITS_PER_INCH = SWERVE_ENC_UNITS_PER_WHEEL_REV / (Math.PI * SWERVE_WHEEL_DIAMETER);

/*
 * Hanger
 */
	public static final double 	kHANGER_MAX_SPEED_UP		=   1.0;
	public static final double 	kHANGER_MAX_SPPEED_DOWN		=  -1.0;
	public static final double	kHANGER_JOYSTICK_DEAD_ZONE	=  .5;   //min power needed to move motor
	public static final double 	kHANGER_ADJUST_ANGLE_SPEED	=   .4;

/*
 * shooter
 */
	
	public static final double	kSHOOTER_MAXSPEED			= 1.0;
	public static final double	kSHOOTER_BACKWARDSPEED		= -1.0;

	public static final double  kHOODANGLE_CLOSESHOT		= 10000;
	public static final double  kHOODANGLE_FARSHOT		    = 500;

	public static final double	kSHOOTER_WHEELSPEED_LONG	= 4000.0;  //RPM or encode counts?
	public static final double	kSHOOTER_WHEELSPEED_SHORT	= 2000.0;  //RPM or encode counts?

/*
 * Intake
 */

	public static final double BALL_IN 				= 1.0;

	public static final int	INTAKE_HATCH_OPEN		=  1;
	public static final int	INTAKE_HATCH_CLOSE		=  0;
	
/*
 * Color Wheel
 * /
 

/*
 * Vision
 */

	public static final int kVISION_LED_ON				= 0;
	public static final int kVISION_LED_OFF				= 1;
	public static final int kVISION_LED_BLINK			= 2;
	public static final int kVISION_PIPELINE_0			= 0;

	public static final int kVISION_TARGET_NOT_FOUND	= 0;
	public static final int kVISION_TARGET_FOUND		= 1;

	public static final double kVISION_TARGET_Y 		=  0.0;		
	public static final double kVISION_Y_THRESHOLD		=  1.0;	  //Target angle in degrees
	public static final double kVISION_X_THRESHOLD 		=   .6;   //Target angle in degrees

	public static final double kVISION_AREA_THRESHOLD		= 	2.0;
	public static final double kVISION_HIGHGOAL_TARGETAREA	=	10.0;

	public static final double kVISION_ROTATE = 0.0;
/*
 * camera
 */
	public static final double kCameraYOffset = 2.25 / 12.0;
    public static final double kCameraXOffset = 4.5 / 12.0;
    public static final double kCameraZOffset = 17.0 / 12.0;
    public static final double kCameraYawAngleDegrees = 0.0;
    public static final double kCameraPitchAngleDegrees = -10.0;
    

/*
 * Smart Dashboard  & FMS
 */

	public static final int kALLIANCE_RED 	 		=  1;
	public static final int kALLIANCE_BLUE 	 		= -1;

	public static final double SEND_STATS_INTERVAL	= .2;	// Interval for reporting in seconds
	public static final boolean DEBUG 				= true;	   
}
