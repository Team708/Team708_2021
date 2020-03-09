package org.usfirst.frc.team708.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
// import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.util.libs.Util;
import org.usfirst.frc.team254.lib.util.math.RigidTransform2d;
import org.usfirst.frc.team254.lib.util.math.Rotation2d;
import org.usfirst.frc.team254.lib.util.math.Translation2d;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.revrobotics.CANSparkMax;                    
import com.revrobotics.CANEncoder;                     
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax.IdleMode;           
import com.revrobotics.CANSparkMaxLowLevel.MotorType;  


public class SwerveDriveModule extends Subsystem{
	TalonSRX rotationMotor;   
	CANSparkMax driveMotor;   
	CANPIDController  drivePIDController; 
	CANEncoder driveEncoder;

	int moduleID;
	String name = "Module ";
	double rotationSetpoint = 0;
	double driveSetpoint = 0;
	double currentAngle = 0;
	int encoderOffset;
	int encoderReverseFactor = 1;

	private double previousEncDistance = 0;
	private Rotation2d previousWheelAngle = new Rotation2d();
	private Translation2d position;
	private Translation2d startingPosition;
	private RigidTransform2d estimatedRobotPose = new RigidTransform2d();
	
	public SwerveDriveModule(int rotationSlot, int driveSlot, int moduleID, 
			int encoderOffset, Translation2d startingPose){
		rotationMotor = new TalonSRX(rotationSlot);
		driveMotor    = new CANSparkMax(driveSlot, MotorType.kBrushless);  
		drivePIDController  = new CANPIDController(driveMotor);  
		driveEncoder = new CANEncoder(driveMotor);
		
		configureMotors();
		this.moduleID = moduleID;
		name += (moduleID + " ");
		this.encoderOffset = encoderOffset;
		previousEncDistance = 0;
		position = startingPose;
		this.startingPosition = startingPose;
		updateRawAngle();
	}
	
	public synchronized void invertDriveMotor(boolean invert){
		driveMotor.setInverted(invert);
	}
	
	public synchronized void invertRotationMotor(boolean invert){
		rotationMotor.setInverted(invert);
	}
	
	public synchronized void reverseDriveSensor(boolean reverse){
		driveMotor.setInverted(reverse);
	}
	
	public synchronized void reverseRotationSensor(boolean reverse){
		encoderReverseFactor = reverse ? -1 : 1;
		rotationMotor.setSensorPhase(reverse);
	}
	
	private void configureMotors(){
    	rotationMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
    	rotationMotor.setSensorPhase(true);
    	rotationMotor.setInverted(false);
    	rotationMotor.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 10, 10);
    	rotationMotor.enableVoltageCompensation(true);
    	rotationMotor.setNeutralMode(NeutralMode.Brake);
    	rotationMotor.configVoltageCompSaturation(7.0, 10);
    	rotationMotor.configNominalOutputForward(0.0, 10);
    	rotationMotor.configNominalOutputReverse(0.0, 10);
    	rotationMotor.configAllowableClosedloopError(0, 0, 10);
    	rotationMotor.configMotionAcceleration((int)(Constants.SWERVE_ROTATION_MAX_SPEED*1.0), 10);  //10.0 jnp
    	rotationMotor.configMotionCruiseVelocity((int)(Constants.SWERVE_ROTATION_MAX_SPEED*1.0), 10);//0.8  jnp
    	rotationMotor.selectProfileSlot(0, 0);
    	rotationMotor.config_kP(0, 4.0, 10);//1
    	rotationMotor.config_kI(0, 0.0, 10);
    	rotationMotor.config_kD(0, 80.0, 10);//10 
    	rotationMotor.config_kF(0, 0.75 * (1023.0/Constants.SWERVE_ROTATION_MAX_SPEED), 10);
		rotationMotor.set(ControlMode.MotionMagic, rotationMotor.getSelectedSensorPosition(0));
		
		drivePIDController.setFeedbackDevice(driveEncoder);
		driveEncoder.setPosition(0.0);
		driveMotor.setIdleMode(IdleMode.kCoast);
		drivePIDController.setP(0.2);
		drivePIDController.setI(0);
		drivePIDController.setD(24);
	}
	
	private double updateRawAngle(){
		currentAngle = encUnitsToDegrees(rotationMotor.getSelectedSensorPosition(0));
		return currentAngle;
	}
	
	public Rotation2d getModuleAngle(){
		return Rotation2d.fromDegrees(currentAngle - encUnitsToDegrees(encoderOffset));
	}
	
	public Rotation2d getFieldCentricAngle(Rotation2d robotHeading){
		Rotation2d normalizedAngle = getModuleAngle();
		return normalizedAngle.rotateBy(robotHeading);
	}
	
	public void setModuleAngle(double goalAngle){
		double newAngle = Util.placeInAppropriate0To360Scope(currentAngle, goalAngle + encUnitsToDegrees(encoderOffset));
		rotationMotor.set(ControlMode.MotionMagic, degreesToEncUnits(newAngle));
		rotationSetpoint = degreesToEncUnits(newAngle);
	}
	
	public void setRotationOpenLoop(double power){
		rotationMotor.set(ControlMode.PercentOutput, power);
		rotationSetpoint = power;
	}
	
	public void setDriveOpenLoop(double power){
		
		driveMotor.set(power);
		driveSetpoint = power;
	}
	
	public void setDrivePositionTarget(double deltaDistanceInches){
		double setpoint = driveEncoder.getPosition() + inchesToEncUnits(deltaDistanceInches);
	
		driveEncoder.setPosition(setpoint);
		driveSetpoint = setpoint;
	}
	
	public boolean drivePositionOnTarget(){
		return false;
	}
	
	private double getDriveDistanceFeet(){
		return getDriveDistanceInches() / 12.0;
	}
	
	private double getDriveDistanceInches(){
		return encUnitsToInches(driveEncoder.getPosition());
	}
	
	

	public double encUnitsToInches(double encUnits){
		return encUnits/Constants.SWERVE_ENC_UNITS_PER_INCH;
	}
	
	public int inchesToEncUnits(double inches){
		return (int) (inches*Constants.SWERVE_ENC_UNITS_PER_INCH);
	}
	
	public double encUnitsPer100msToFeetPerSecond(int encUnitsPer100ms){
		return encUnitsToInches(encUnitsPer100ms) / 12.0 * 10;
	}
	
	public int degreesToEncUnits(double degrees){
		return (int) (degrees/360.0*Constants.ROTATION_ENCODER_RESOLUTION);
	}
	
	public double encUnitsToDegrees(int encUnits){
		return encUnits/Constants.ROTATION_ENCODER_RESOLUTION*360.0;
	}
	
	public Translation2d getPosition(){
		return position;
	}
	
	public RigidTransform2d getEstimatedRobotPose(){
		return estimatedRobotPose;
	}
	
	public synchronized void updatePose(Rotation2d robotHeading){
		double currentEncDistance = getDriveDistanceFeet();
		double deltaEncDistance = (currentEncDistance - previousEncDistance) * Constants.kWheelScrubFactors[moduleID];
		updateRawAngle();
		Rotation2d currentWheelAngle = getFieldCentricAngle(Pigeon.getInstance().getAngle());
		currentWheelAngle.normalize();
		Rotation2d averagedAngle = Rotation2d.fromDegrees((currentWheelAngle.getDegrees() + previousWheelAngle.getDegrees())/2.0);
		Translation2d deltaPosition = new Translation2d(currentWheelAngle.cos()*deltaEncDistance, 
				currentWheelAngle.sin()*deltaEncDistance);
		Translation2d updatedPosition = position.translateBy(deltaPosition);
		RigidTransform2d staticWheelPose = new RigidTransform2d(updatedPosition, robotHeading);
		RigidTransform2d robotPose = staticWheelPose.transformBy(RigidTransform2d.fromTranslation(startingPosition).inverse());
		position = updatedPosition;
		estimatedRobotPose =  robotPose;
		previousEncDistance = currentEncDistance;
		previousWheelAngle = currentWheelAngle;
	}
	
	public synchronized void resetPose(RigidTransform2d robotPose){
		Translation2d modulePosition = robotPose.transformBy(RigidTransform2d.fromTranslation(startingPosition)).getTranslation();
		position = modulePosition;
	}
	public void setDriveBrakeOn(){
		driveMotor.setIdleMode(IdleMode.kBrake);
	}
	
	public void setDriveBrakeOff(){
		driveMotor.setIdleMode(IdleMode.kCoast);
	}
	
	public synchronized void resetPose(){
		position = startingPosition;
	}
	
	public synchronized void stop(){
		setModuleAngle(getModuleAngle().getDegrees());
		setRotationOpenLoop(0.0);
		setDriveOpenLoop(0.0);
	}
	
	public synchronized void resetRotationToAbsolute(){
		rotationMotor.setSelectedSensorPosition(
				encoderReverseFactor * (rotationMotor.getSensorCollection().getPulseWidthPosition() - encoderOffset), 0, 10);
	}

	public synchronized void zeroSensors() {
		zeroSensors(new RigidTransform2d());
	}
	
	public synchronized void zeroSensors(RigidTransform2d robotPose) {
		driveEncoder.setPosition(0.0);
		resetPose(robotPose);
		estimatedRobotPose = robotPose;
		previousEncDistance = 0;
		previousWheelAngle = getFieldCentricAngle(robotPose.getRotation());
	}

	public void initDefaultCommand() {
		setDefaultCommand(null);
	}

	public void outputToSmartDashboard() {
		updateRawAngle();
		SmartDashboard.putNumber(name + "Angle", getModuleAngle().getDegrees());
		SmartDashboard.putNumber(name + "Pulse Width", rotationMotor.getSelectedSensorPosition(0));
		SmartDashboard.putNumber(name + "Inches Driven", getDriveDistanceInches());
		SmartDashboard.putNumber(name + "raw value", driveEncoder.getPosition());
	}
		
	
}