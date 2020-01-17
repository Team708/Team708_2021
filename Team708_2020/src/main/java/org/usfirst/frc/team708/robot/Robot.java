package org.usfirst.frc.team708.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import edu.wpi.first.cameraserver.CameraServer;

//import edu.wpi.cscore.UsbCamera;

import org.usfirst.frc.team708.robot.commands.autonomous.*;
import org.usfirst.frc.team708.robot.commands.swerve.DriveStraightCommand;
import org.usfirst.frc.team708.robot.subsystems.*;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Xbox;
import org.usfirst.frc.team254.lib.util.math.RigidTransform2d;
import org.usfirst.frc.team254.lib.util.math.Rotation2d;
import org.usfirst.frc.team254.lib.util.math.Translation2d;
import edu.wpi.first.wpilibj.GenericHID.Hand;



public class Robot extends TimedRobot {

    Timer statsTimer; // Timer used for Smart Dash statistics

    //
    // public static Drivetrain        drivetrain;
    private Xbox driver;
    public static Swerve swerve;
    public static VisionProcessor visionprocessor;
    

    public String gameData;
    public String robotLocation;
    public String autoMode;

    public static DriverStation 			ds;
    public static DriverStation.Alliance 	alliance;
    public static int 						allianceColor;
    public static String                    ColorWheelStage3;

    SendableChooser<Command> autonomousMode = new SendableChooser<>();

    Command autonomousCommand;

    Preferences prefs;

    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    public void robotInit() {

        statsTimer = new Timer(); // Initializes the timer for sending Smart Dashboard data
        statsTimer.start(); // Starts the timer for the Smart Dashboard
 
        // Subsystem Initialization
        driver = new Xbox(0);
        driver.setDeadband(0.2);
        swerve = Swerve.getInstance();
        Robot.swerve.zeroSensors();
        visionprocessor = new VisionProcessor();
   
        // drivetrain      = new Drivetrain();


        visionprocessor.setNTInfo("ledMode", Constants.kVISION_LED_OFF);
    
        sendDashboardSubsystems(); // Sends each subsystem's cmds to Smart Dashboard

        queueAutonomousModes();    // Adds autonomous modes to the selection box
    }

    /**
     * Runs periodically while the robot is enabled
     */
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        sendStatistics();
        prefs = Preferences.getInstance();

        try {
            // robot is ENABLED
     		if (RobotController.isSysActive()){
                 // connected to FMS
                 ds = DriverStation.getInstance();
                alliance = ds.getAlliance();
	            if (alliance == Alliance.Blue){
                       allianceColor = Constants.kALLIANCE_BLUE;
                }
                else if (alliance == Alliance.Red){
                    allianceColor = Constants.kALLIANCE_RED;
                }
                else {
                    allianceColor = 0;
                }
            ColorWheelStage3 = ds.getGameSpecificMessage();
            if (ColorWheelStage3.length() <=0)
                ColorWheelStage3 = "X NOT SET X";
           }
           else // robot is NOT ENABLED
           {allianceColor=20;}
		}
		catch (Exception e)
		{
             allianceColor = 11;
        }
       
    }

    /**
     * Runs at the start of autonomous mode
     */

    public void autonomousInit() {
    // schedule the autonomous command
        // drivetrain.setBrakeMode(true);
        // drivetrain.resetGyro();
    // intake.intakeRetract();

        autonomousCommand = (Command) autonomousMode.getSelected();
        if (autonomousCommand != null)
            autonomousCommand.start();
        swerve.zeroSensors();
        swerve.SetDriveBrakesOn();
        
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        sendStatistics();
    
    }

    /**
     * Runs when teleop mode initializes
     */
    public void teleopInit() {
        // This makes sure that the autonomous stops running when teleop starts running.
        // If you want the autonomous to continue until interrupted by another command,
        // remove this line or comment it out.
        if (autonomousCommand != null)
            autonomousCommand.cancel();
        swerve.SetDriveBrakesOff();
        

     // intake.intakeRetract();
        // drivetrain.setBrakeMode(false);
    }

    /**
     * This function is called when the disabled button is hit. You can use it to
     * reset subsystems before shutting down.
     */
    public void disabledInit() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        sendStatistics();
        driver.update();
		
		swerve.sendInput(driver.getX(Hand.kLeft), -driver.getY(Hand.kLeft), driver.getX(Hand.kRight), false, driver.leftTrigger.isBeingPressed());
		if(driver.yButton.wasPressed())
			swerve.rotate(0);
		else if(driver.bButton.wasPressed())
			swerve.rotate(90);
		else if(driver.aButton.wasPressed())
			swerve.rotate(180);
		else if(driver.xButton.wasPressed())
			swerve.rotate(270);
		if(driver.backButton.isBeingPressed()){
			swerve.temporarilyDisableHeadingController();
			// swerve.zeroSensors(new RigidTransform2d(new Translation2d(Constants.ROBOT_HALF_LENGTH, Constants.kAutoStartingCorner.y() + Constants.ROBOT_HALF_WIDTH), Rotation2d.fromDegrees(0)));
		}
		
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        sendStatistics();
    }

    /**
     * Sends data from each subsystem periodically as set by sendStatsIntervalSec
     */

    private void sendStatistics() {
        // if (statsTimer.get() >= Constants.SEND_STATS_INTERVAL) statsTimer.reset();
        // drivetrain.sendToDashboard();
        swerve.outputToSmartDashboard();
    }

    /**
     * Adds every autonomous mode to the selection box and adds the box to the Smart
     * Dashboard
     */
    private void queueAutonomousModes() {

        autonomousMode.addOption("Do Nothing", new DoNothing());
        autonomousMode.addOption("Drive Straight", new DriveStraight());
        SmartDashboard.putData("Autonomous Selection", autonomousMode);
    }

    /**
     * Sends every subsystem to the Smart Dashboard
     */
    private void sendDashboardSubsystems() {
       SmartDashboard.putData(swerve);

    }
}
