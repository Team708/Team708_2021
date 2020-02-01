package org.usfirst.frc.team708.robot;

//  added color back in to master - 1/31 jnp

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
    private Xbox driver, operator;
    public static Swerve swerve;
    public static VisionProcessor visionprocessor;
    public static Shooter shooter;
    public static Hopper hopper;
    public static Intake intake;
    public static Spinner spinner;

    public double speed;
    public String gameData;
    public String robotLocation;
    public String autoMode;
    // public boolean enabled = false;
    public static String wheelTargetColor   = " ";
    public static Boolean[] colors; //0=B, 1=G, 2=R, 3=Y, 4=Null

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
        driver   = new Xbox(0);
        operator = new Xbox(1);

        
        swerve = Swerve.getInstance();
        Robot.swerve.zeroSensors();
        
        spinner = new Spinner();
        intake = new Intake();
        shooter = new Shooter();
        visionprocessor = new VisionProcessor();
        hopper = new Hopper();
        
        
        driver.setDeadband(0.2);
        operator.setDeadband(0.2);
        visionprocessor.setNTInfo("ledMode", Constants.kVISION_LED_OFF);

        colors = new Boolean[4];
        colors[0] = false; //Blue
        colors[1] = false; //Green
        colors[2] = false; //Red
        colors[3] = false; //Yellow
        // colors[4] = false; //Null
    
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
                // enabled = true;
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
           }
           else{ // robot is NOT ENABLED
                allianceColor = 20;
                colors[0] = false; //Blue
                colors[1] = false; //Green
                colors[2] = false; //Red
                colors[3] = false; //Yellow
                // colors[4] = false; //Null
                // enabled = false;
            }
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
        swerve.SetDriveBrakesOn();
        speed = 0.5;

        //Sets all colors to false as teleop begins
        colors[0] = false; //Blue
        colors[1] = false; //Green
        colors[2] = false; //Red
        colors[3] = false; //Yellow
        // colors[4] = false; //Null
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

        ds = DriverStation.getInstance();
        ColorWheelStage3 = ds.getGameSpecificMessage();
        if(ColorWheelStage3.length() > 0){
            switch(ColorWheelStage3.charAt(0)){
                case 'B' :
                    wheelTargetColor = "Blue";
                    colors[0] = true;
                    break;
                case 'G' :
                    wheelTargetColor = "Green";
                    colors[1] = true;
                    break;
                case 'R' :
                    wheelTargetColor = "Red";
                    colors[2] = true;
                    break;
                case 'Y' :
                    wheelTargetColor = "Yellow";
                    colors[3] = true;
                    break;
                default:
                    wheelTargetColor = "Color Not Read";
                    for(int i = 0; i < colors.length; i++){
                        colors[i] = false;
                    }
            }
        }else{
            wheelTargetColor = "Color Not Available";
            for(int i = 0; i < colors.length; i++){
                colors[i] = false;
                }
        }
        
        swerve.sendInput(driver.getX(Hand.kLeft), -driver.getY(Hand.kLeft), driver.getX(Hand.kRight), false, driver.leftTrigger.isBeingPressed());
        
        operator.update();

        if(operator.leftCenterClick.wasPressed())
            intake.toggleIntake();
        else if(operator.rightCenterClick.wasPressed())
            visionprocessor.toggleLEDMode();
        else if (operator.leftBumper.wasPressed())
            intake.reverseIntakeMotor();
        else if (operator.startButton.wasPressed())
            spinner.spinnerRotateThreeTimes();
        else if(operator.yButton.wasPressed())
            speed += 0.1;
        else if (operator.bButton.wasPressed()){
            spinner.spinnerRotateOneColor();
            operator.rumble(1.0, 1.0);
        }
        else if(operator.aButton.wasPressed())
            speed -= 0.1;
        else if(operator.backButton.wasPressed())
            hopper.stopMotor();
        else if(operator.xButton.wasPressed())
            hopper.reverseMotor();
        else if(operator.rightTrigger.isBeingPressed())
            shooter.shootAuto();
        else if(operator.rightBumper.isBeingPressed())
            shooter.feederOn(speed);
        else {
            shooter.stopShooter();
            shooter.feederOff();
        }
        
        driver.update();

		if(driver.yButton.wasPressed())
			swerve.rotate(0);
		else if(driver.bButton.wasPressed())
			swerve.rotate(90);
        else if(driver.aButton.wasPressed())
			swerve.rotate(180);
        else if(driver.xButton.wasPressed())
            swerve.rotate(270);
        else if(driver.startButton.wasPressed()){
            swerve.wheelBrake();
            driver.rumble(1.0, 1.0);
        }
        else if(driver.backButton.wasPressed()){
            driver.rumble(1.0, 1.0);
            swerve.temporarilyDisableHeadingController();
            swerve.zeroSensors(new RigidTransform2d(new Translation2d(Constants.ROBOT_HALF_LENGTH, Constants.kAutoStartingCorner.y() + Constants.ROBOT_HALF_WIDTH), Rotation2d.fromDegrees(0)));
        }
        else if(driver.leftBumper.wasPressed())
            if (Math.abs(Pigeon.getInstance().getAngle().getDegrees())>60) {
                swerve.rotate(0);
                visionprocessor.findTarget();
            }
            else {
                visionprocessor.findTarget();
                driver.rumble(1.0, 1.0);
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
        swerve.sendToDashboard();
        shooter.sendToDashboard();
        intake.sendToDashboard();
        spinner.sendToDashboard();
        visionprocessor.sendToDashboard();
    }

    /**
     * Adds every autonomous mode to the selection box and adds the box to the Smart
     * Dashboard
     */
    private void queueAutonomousModes() {
        
        autonomousMode.addOption("Do Nothing", new DoNothing());
        autonomousMode.addOption("Five Ball Auto", new FiveBallAuto());
        autonomousMode.addOption("Three Ball Auto", new ThreeBallAuto());
        autonomousMode.addOption("Drive off line", new DriveOffLineAuto());
        autonomousMode.addOption("Testing", new DriveStraightAuto());
        // autonomousMode.addOption("Tests Every Motor", new EverythingAuto());

        SmartDashboard.putData("Autonomous Selection", autonomousMode);
    }

    /**
     * Sends every subsystem to the Smart Dashboard
     */
    private void sendDashboardSubsystems() {
       SmartDashboard.putData(visionprocessor);
       SmartDashboard.putData(swerve);
       SmartDashboard.putData(shooter);
       SmartDashboard.putData(intake);
       SmartDashboard.putData(spinner);
    }
}
