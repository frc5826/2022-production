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

    private DifferentialDrive differentialDrive;

    public DriveSubsystem() {
        leftSpark1 = new CANSparkMax(leftID1, CANSparkMaxLowLevel.MotorType.kBrushless);
        leftSpark2 = new CANSparkMax(leftID2, CANSparkMaxLowLevel.MotorType.kBrushless);
        rightSpark1 = new CANSparkMax(rightID1, CANSparkMaxLowLevel.MotorType.kBrushless);
        rightSpark2 = new CANSparkMax(rightID2, CANSparkMaxLowLevel.MotorType.kBrushless);

        leftSpeedControllers = new MotorControllerGroup(leftSpark1, leftSpark2);
        rightSpeedControllers = new MotorControllerGroup(rightSpark1, rightSpark2);

        differentialDrive = new DifferentialDrive(leftSpark1, rightSpark1);
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
