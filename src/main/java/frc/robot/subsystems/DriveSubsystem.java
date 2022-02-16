package frc.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import static frc.robot.Constants.*;

public class DriveSubsystem extends SubsystemBase {

    private CANSparkMax leftSpark1;
    private CANSparkMax leftSpark2;
    private CANSparkMax rightSpark1;
    private CANSparkMax rightSpark2;

    private MotorControllerGroup leftSpeedControllers;
    private MotorControllerGroup rightSpeedControllers;

    private double distance;
    private long timeStamp;

    private int count = 0;

    private SparkMaxPIDController leftSpark1_PID;
    private SparkMaxPIDController leftSpark2_PID;
    private SparkMaxPIDController rightSpark1_PID;
    private SparkMaxPIDController rightSpark2_PID;

    public DriveSubsystem() {
        leftSpark1 = new CANSparkMax(leftID1, CANSparkMaxLowLevel.MotorType.kBrushless);
        leftSpark2 = new CANSparkMax(leftID2, CANSparkMaxLowLevel.MotorType.kBrushless);
        rightSpark1 = new CANSparkMax(rightID1, CANSparkMaxLowLevel.MotorType.kBrushless);
        rightSpark2 = new CANSparkMax(rightID2, CANSparkMaxLowLevel.MotorType.kBrushless);

        leftSpark1.restoreFactoryDefaults();
        leftSpark2.restoreFactoryDefaults();
        rightSpark1.restoreFactoryDefaults();
        rightSpark2.restoreFactoryDefaults();

        leftSpark1.setInverted(false);
        leftSpark2.setInverted(false);
        rightSpark1.setInverted(false);
        rightSpark2.setInverted(false);

        leftSpark1_PID = leftSpark1.getPIDController();
        leftSpark2_PID = leftSpark2.getPIDController();
        rightSpark1_PID = rightSpark1.getPIDController();
        rightSpark2_PID = rightSpark2.getPIDController();

        leftSpeedControllers = new MotorControllerGroup(leftSpark1, leftSpark2);
        rightSpeedControllers = new MotorControllerGroup(rightSpark1, rightSpark2);

        this.leftSpark1_PID.setP(DRIVE_P);
        this.leftSpark1_PID.setI(DRIVE_I);
        this.leftSpark1_PID.setD(DRIVE_D);
        this.leftSpark1_PID.setOutputRange(-PEAK_OUTPUT, PEAK_OUTPUT);

        this.leftSpark2_PID.setP(DRIVE_P);
        this.leftSpark2_PID.setI(DRIVE_I);
        this.leftSpark2_PID.setD(DRIVE_D);
        this.leftSpark2_PID.setOutputRange(-PEAK_OUTPUT, PEAK_OUTPUT);

        this.rightSpark1_PID.setP(DRIVE_P);
        this.rightSpark1_PID.setI(DRIVE_I);
        this.rightSpark1_PID.setD(DRIVE_D);
        this.rightSpark1_PID.setOutputRange(-PEAK_OUTPUT, PEAK_OUTPUT);

        this.rightSpark2_PID.setP(DRIVE_P);
        this.rightSpark2_PID.setI(DRIVE_I);
        this.rightSpark2_PID.setD(DRIVE_D);
        this.rightSpark2_PID.setOutputRange(-PEAK_OUTPUT, PEAK_OUTPUT);

        this.leftSpark1.getEncoder().setVelocityConversionFactor((Math.PI * WHEEL_DIAMETER) / (60 * DRIVE_GEAR_RATIO));
        this.leftSpark2.getEncoder().setVelocityConversionFactor((Math.PI * WHEEL_DIAMETER) / (60 * DRIVE_GEAR_RATIO));
        this.rightSpark1.getEncoder().setVelocityConversionFactor((Math.PI * WHEEL_DIAMETER) / (60 * DRIVE_GEAR_RATIO));
        this.rightSpark2.getEncoder().setVelocityConversionFactor((Math.PI * WHEEL_DIAMETER) / (60 * DRIVE_GEAR_RATIO));

        this.leftSpark1.getEncoder().setPositionConversionFactor((Math.PI * WHEEL_DIAMETER) / (DRIVE_GEAR_RATIO));
        this.leftSpark2.getEncoder().setPositionConversionFactor((Math.PI * WHEEL_DIAMETER) / (DRIVE_GEAR_RATIO));
        this.rightSpark1.getEncoder().setPositionConversionFactor((Math.PI * WHEEL_DIAMETER) / (DRIVE_GEAR_RATIO));
        this.rightSpark2.getEncoder().setPositionConversionFactor((Math.PI * WHEEL_DIAMETER) / (DRIVE_GEAR_RATIO));

        leftSpark2.follow(leftSpark1);
        rightSpark2.follow(rightSpark1);

        //differentialDrive = new DifferentialDrive(leftSpeedControllers, rightSpeedControllers);
        differentialDrive = new DifferentialDrive(leftSpark1, rightSpark1);
        differentialDrive.setSafetyEnabled(false);
        this.distance = 0;
    }

    private DifferentialDrive differentialDrive;

    public DifferentialDrive getDiffDrive(){

        return differentialDrive;
    }


    public void periodic() {
        if(timeStamp != 0) {
            double deltaTime = System.currentTimeMillis() - timeStamp;

            double l1 = this.leftSpark1.getEncoder().getVelocity() * -1;
            double l2 = this.leftSpark2.getEncoder().getVelocity() * -1;
            double r1 = this.rightSpark1.getEncoder().getVelocity() * -1;
            double r2 = this.rightSpark2.getEncoder().getVelocity() * -1;

            double currentVelocity = (l1 + l2 + r1 + r2) / 4;

            double deltaDistance = (deltaTime * currentVelocity) / 1000;

            SmartDashboard.putNumber("Left Spark 1 Velocity", l1);

            if(count++ % 40 == 0) {
                //System.out.println(leftSpark1.getEncoder().getPosition());
            }

            distance += deltaDistance;

        }
        timeStamp = System.currentTimeMillis();
        if(Constants.joystick.getRawButton(2)){
            resetDistance();
        }

    }

    public void setVelocity(double mps) {
        this.leftSpark1_PID.setReference(mps, CANSparkMax.ControlType.kVelocity);
        this.rightSpark1_PID.setReference(mps, CANSparkMax.ControlType.kVelocity);
    }

    public void resetDistance(){
        distance = 0;
    }

    public double getDistance(){
        return distance;
    }

    public double getLeftPosition(){
        return leftSpark1.getEncoder().getPosition();
    }

    public double getRightPosition(){
        return rightSpark1.getEncoder().getPosition();
    }

    public void setLeftPosition(double position){
        leftSpark1_PID.setReference(position, CANSparkMax.ControlType.kPosition);
    }

    public void setRightPosition(double position){
        rightSpark1_PID.setReference(position, CANSparkMax.ControlType.kPosition);
    }

    public double getMotorPower(){
        return leftSpark1.getAppliedOutput();
    }

    public void setSpeedOutputRange(double min, double max){
        leftSpark1_PID.setOutputRange(min, max);
        leftSpark2_PID.setOutputRange(min, max);
        rightSpark1_PID.setOutputRange(min, max);
        rightSpark2_PID.setOutputRange(min, max);
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}
