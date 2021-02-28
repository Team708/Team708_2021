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
	public static final double kDT_WHEELBASE_LENGTH = 26.5 / 12.0; //feet  17.5=test  26.5=prod
    public static final double kDT_WHEELBASE_WIDTH  = 20.5 / 12.0; //feet  17.5=test 20=prod
    public static final double kDT_SWERVE_DIAGONAL = Math.hypot(kDT_WHEELBASE_LENGTH, kDT_WHEELBASE_WIDTH);
	
	//Physical Robot Dimensions
	public static final double ROBOT_WIDTH = 26.5 / 12.0;  // 24=test 26.5=prod
	public static final double ROBOT_LENGTH = 33 / 12.0;  // 24=test 33=prod
	public static final double ROBOT_HALF_WIDTH = ROBOT_WIDTH / 2.0;
	public static final double ROBOT_HALF_LENGTH = ROBOT_LENGTH / 2.0;
	public static final double ROBOT_INTAKE_EXTRUSION = 11.5/12.0;

    //Swerve Speed Constants
    public static final double SWERVE_ROTATION_MAX_SPEED = 1992.0; //1250
    public static final double SWERVE_DRIVE_MAX_SPEED    = 5432.0;
	
    //Swerve Module Wheel Offsets
	// public static final int FRONT_LEFT_ENCODER_STARTING_POS  = 1478+2048; // mod 0   1478 
	// public static final int FRONT_RIGHT_ENCODER_STARTING_POS = 1451+2048; // mod 1   1451
	// public static final int REAR_LEFT_ENCODER_STARTING_POS   = 2600-2048; // mod 2  2600
	// public static final int REAR_RIGHT_ENCODER_STARTING_POS  = 550+2048;  // mod 3  500
	
	public static final int FRONT_RIGHT_ENCODER_STARTING_POS = 1402+2048;  // mod 0   -1078
	public static final int FRONT_LEFT_ENCODER_STARTING_POS  = 3058-2048;  // mod 1  1693 1010
	public static final int REAR_LEFT_ENCODER_STARTING_POS   = -3879; //+2048;  // mod 2  1250 1928
	public static final int REAR_RIGHT_ENCODER_STARTING_POS  = 270+2048;   // mod 3  -1545

	//Swerve Module Positions
	public static final Translation2d kVehicleToModuleOne = new Translation2d(kDT_WHEELBASE_LENGTH/2, kDT_WHEELBASE_WIDTH/2);
	public static final Translation2d kVehicleToModuleTwo = new Translation2d(kDT_WHEELBASE_LENGTH/2, -kDT_WHEELBASE_WIDTH/2);
	public static final Translation2d kVehicleToModuleThree = new Translation2d(-kDT_WHEELBASE_LENGTH/2, -kDT_WHEELBASE_WIDTH/2);
	public static final Translation2d kVehicleToModuleFour = new Translation2d(-kDT_WHEELBASE_LENGTH/2, kDT_WHEELBASE_WIDTH/2);
	
	//Scrub Factors
	public static final double[] kWheelScrubFactors = new double[]{1.0, 1.0, 1.0, /*5.0/5.15*/1.0};	

	//Swerve Odometry Constants
	public static final double SWERVE_WHEEL_DIAMETER = 3.952; //inches
	public static final double ROTATION_ENCODER_RESOLUTION = 4096.0;
	public static final double DRIVE_ENCODER_RESOLUTION    = 42.0;

	//Rotation pid
	public static final double rotateKp = 0.005; //26
	public static final double rotateKi = 0; //15
	public static final double rotateKd = 0; //3
	public static final double rotateKf = 0;



	/**
	 * The number of rotations the swerve drive encoder undergoes for every rotation of the wheel.
	 */
	public static final double SWERVE_ENCODER_TO_WHEEL_RATIO = .119;  //10.0/9.0;
	public static final double SWERVE_ENC_UNITS_PER_WHEEL_REV = DRIVE_ENCODER_RESOLUTION * SWERVE_ENCODER_TO_WHEEL_RATIO;
	public static final double SWERVE_ENC_UNITS_PER_INCH = SWERVE_ENC_UNITS_PER_WHEEL_REV / (Math.PI * SWERVE_WHEEL_DIAMETER);

/*
 * Hanger
 */
	public static final double 	kHANGER_MAX_SPEED_UP		=   1.0;
	public static final double 	kHANGER_MAX_SPEED_DOWN		=  -1.0;
	public static final double	kHANGER_JOYSTICK_DEAD_ZONE	=  .5;   //min power needed to move motor
	public static final double 	kHANGER_ADJUST_ANGLE_SPEED	=   .4;

/*
 * shooter
 */
	
	public static final double	kSHOOTER_MAXSPEED			= 5676;
	public static final double	kSHOOTER_BACKWARDSPEED		= -1.0;
	public static final double	kSHOOTERHEIGHT		        = 15;

	public static final double  kHOODANGLE_CLOSESHOT		= 10000;
	public static final double  kHOODANGLE_FARSHOT		    = 500;
	public static final double  kHOODANGLE_LONGSHOT		    = 80; //inches

	public static final double	kSHOOTER_WHEELSPEED_LONG	= 3900.0;  //3700//RPM or encode counts?
	public static final double	kSHOOTER_WHEELSPEED_SHORT	= 1850.0;  //RPM or encode counts?

	public static final double kFEEDERMOTORSPEED = 1.0;

	public static final int TURRET_ENCODER_STARTING_POS  = -2614; //7889; //2636 * 8; //550+2048;  // turret offset
	public static final int TURRET_ENCODER_COUNT = 32768;  // 4096 * 8;
	public static final int kTurretOnTargetTolerance = 1;
	public static final int kTurretSafeTolerance = 2;
	public static final double TURRET_ROTATION_MAX_SPEED = 9625; //17694; //2664 * 8; //counts/100ms on 775w/gearbox
// 13000 / 40 = 325/min  5.4/sec 
	public static final double kHOPPER_SPEED = .25;


/*
 * Color Wheel
 */
	public static final double kSPIN_ONE_COLOR			= 3;//5  //one color = 4096
	public static final double kSPIN_THREE_TIMES		= 60; //70// <encoder * ratio * 3.25 = 106500


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
    public static final double kCAMERAHEIGHT = 0.0;

/*
 * Smart Dashboard  & FMS
 */

	public static final int kALLIANCE_RED 	 		=  1;
	public static final int kALLIANCE_BLUE 	 		= -1;

	public static final double SEND_STATS_INTERVAL	= .2;	// Interval for reporting in seconds
	public static final boolean DEBUG 				= true;	 
	
	
/*
 * 2019 Field Landmarks (copied from 1323 for pathfinder)
 */

	public static final double kCubeWidth = 13.0/12.0;
	public static final Translation2d kAutoStartingCorner = new Translation2d(0.0, 12.5);
	public static final Translation2d kRightSwitchCloseCorner = new Translation2d(140.0 / 12.0, 27.0 - (85.25/12.0));
	public static final Translation2d kRightSwitchFarCorner = new Translation2d(196.0 / 12.0, 27.0 - (85.25/12.0));
	public static final Translation2d kLeftSwitchCloseCorner = new Translation2d(140.0 / 12.0, 85.25/12.0);
	public static final Translation2d kLeftSwitchFarCorner = new Translation2d(196.0 / 12.0, 85.25/12.0);
	public static final Translation2d kRightScaleCorner = new Translation2d(299.65 / 12.0, 27.0 - (95.25/12.0));
	public static final Translation2d kLeftScaleCorner = new Translation2d(299.65 / 12.0, 95.25 / 12.0);
	public static final Translation2d kRightMostCube = kRightSwitchFarCorner.translateBy(new Translation2d(kCubeWidth, -0.25));
	public static final Translation2d kLeftMostCube = kLeftSwitchFarCorner.translateBy(new Translation2d(kCubeWidth, kCubeWidth/2.0));
	public static final Translation2d kLeftMostCubeCorner = kLeftSwitchFarCorner.translateBy(new Translation2d(kCubeWidth, 0.0));
	public static final Translation2d kSecondLeftCube = kLeftMostCube.translateBy(new Translation2d(0.0, kCubeWidth + (15.1/12.0)));
	public static final Translation2d kSecondLeftCubeCorner = kSecondLeftCube.translateBy(new Translation2d(0.0, -kCubeWidth/2.0));
	
/*
 * 2020 Field Landmarks
 */

	public static final double kGOALHEIGHT = 98.25;

}
