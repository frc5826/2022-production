package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.*;

public class DriveSubsystem extends SubsystemBase {

    private CANSparkMax spark1;
    private CANSparkMax spark2;
    private CANSparkMax spark3;
    private CANSparkMax spark4;

    private MotorControllerGroup leftSpeedControllers;
    private MotorControllerGroup rightSpeedControllers;

    private DifferentialDrive differentialDrive;

    public DriveSubsystem() {
        spark1 = new CANSparkMax(DriveID1, CANSparkMaxLowLevel.MotorType.kBrushless);
        spark2 = new CANSparkMax(DriveID2, CANSparkMaxLowLevel.MotorType.kBrushless);
        spark3 = new CANSparkMax(DriveID3, CANSparkMaxLowLevel.MotorType.kBrushless);
        spark4 = new CANSparkMax(DriveID4, CANSparkMaxLowLevel.MotorType.kBrushless);

        leftSpeedControllers = new MotorControllerGroup(spark1, spark2);
        rightSpeedControllers = new MotorControllerGroup(spark3, spark4);

        differentialDrive = new DifferentialDrive(leftSpeedControllers, rightSpeedControllers);
    }

    public DifferentialDrive getDiffDrive(){
        return differentialDrive;
    }

    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}
