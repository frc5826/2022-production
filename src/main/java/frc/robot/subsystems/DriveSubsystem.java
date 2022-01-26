package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

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

    public DriveSubsystem() {
        leftSpark1 = new CANSparkMax(leftID1, CANSparkMaxLowLevel.MotorType.kBrushless);
        leftSpark2 = new CANSparkMax(leftID2, CANSparkMaxLowLevel.MotorType.kBrushless);
        rightSpark1 = new CANSparkMax(rightID1, CANSparkMaxLowLevel.MotorType.kBrushless);
        rightSpark2 = new CANSparkMax(rightID2, CANSparkMaxLowLevel.MotorType.kBrushless);

        leftSpeedControllers = new MotorControllerGroup(leftSpark1, leftSpark2);
        rightSpeedControllers = new MotorControllerGroup(rightSpark1, rightSpark2);

        differentialDrive = new DifferentialDrive(leftSpeedControllers, rightSpeedControllers);

        this.leftSpark1.getEncoder().setVelocityConversionFactor((Math.PI * WHEEL_DIAMETER) / 60);
        this.leftSpark2.getEncoder().setVelocityConversionFactor((Math.PI * WHEEL_DIAMETER) / 60);
        this.rightSpark1.getEncoder().setVelocityConversionFactor((Math.PI * WHEEL_DIAMETER) / 60);
        this.rightSpark2.getEncoder().setVelocityConversionFactor((Math.PI * WHEEL_DIAMETER) / 60);

        this.distance = 0;
    }

    private DifferentialDrive differentialDrive;

    public DifferentialDrive getDiffDrive(){

        return differentialDrive;
    }



    public void periodic() {
        if(timeStamp != 0) {
            double deltaTime = System.currentTimeMillis() - timeStamp;

            double deltaDistance = 0;

            this.leftSpark1.getEncoder().getVelocity();
            this.leftSpark2.getEncoder().getVelocity();
            this.rightSpark1.getEncoder().getVelocity();
            this.rightSpark2.getEncoder().getVelocity();

            distance += deltaDistance;
        }
        timeStamp = System.currentTimeMillis();
    }

    public double getDistance(){
        return distance;
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}
