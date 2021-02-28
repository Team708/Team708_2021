package org.usfirst.frc.team708.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 *  */
public class RobotMap {
	
// Gamepad USB ports

	public static final int driverGamepad   = 0;
	public static final int operatorGamepad = 1;
	
// PWM Ports

//	public static final int 			 	= 0;
//	public static final int 			 	= 1;
//	public static final int  			 	= 2;
//	public static final int  				= 3;
//	public static final int  				= 4;
//	public static final int  				= 5;
//	public static final int  				= 6;
//	public static final int  				= 7;
//	public static final int  				= 8;
//	public static final int  				= 9;
	
// Motor Controler Device IDS

// Drivetrain
	 public static final int kdrivetrainFrontRightDrive	= 11; 
	 public static final int kdrivetrainFrontLeftDrive	= 12; 
	 public static final int kdrivetrainRearLeftDrive  	= 13;
	 public static final int kdrivetrainRearRightDrive 	= 14;

	 public static final int kdrivetrainFrontRightTurn	= 15; 
 	 public static final int kdrivetrainFrontLeftTurn	= 16; 
	 public static final int kdrivetrainRearLeftTurn 	= 17;
	 public static final int kdrivetrainRearRightTurn 	= 18;

	 public static final int kshooterShootMotor         = 21;
	 public static final int kshooterShootMotor2        = 22;

	 public static final int kfeederFeedMotor           = 25; 
	 public static final int khopperMotor               = 28; 
	 public static final int kturretMotor               = 23; 

	 public static final int kintakeMotor               = 31;
	 public static final int kspinnerMotor              = 41;





// CAN Gyro
	 public static final int PIGEON     				= 0;

// Digital IO
//   public static final int 				= 0;  	
//   public static final int 				= 1;
//   public static final int 				= 2;
//   public static final int 				= 3;
	
	
// RELAY
//	public static final int 			 	= 0;
//	public static final int 			 	= 1;
//	public static final int 			 	= 2;
//	public static final int 			 	= 3;
	
// Analog sensor IDs
// public static final int 			= 0;
// public static final int 			= 1;
//	public static final int			= 2;
//	public static final int 		= 3;
	
// PCM Ports
	public static final int hoodSolenoid	= 0; 	
	public static final int hangerEngage	= 1; 	
 	// public static final int hangerLock		= 2;	
	public static final int	littlePecker    = 3;	
	public static final int	armCam0			= 4;	
	public static final int armCam1			= 5;
	public static final int armPivot0		= 6;
	public static final int armPivot1       = 7;
	
	
}
