package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.Arrays;
import java.util.List;

import static frc.robot.Constants.*;

public class IntakeSubsystem extends SubsystemBase {

    private final TalonSRX leftTalon;
    private final TalonSRX rightTalon;

    private double openValueLeftIntake;
    private double openValueRightIntake;

    private int leftInitCount = 0;
    private int rightInitCount = 0;

    private boolean leftInitDone = false;
    private boolean rightInitDone = false;

    private boolean readyToInit = false;

    public IntakeSubsystem() {
        this.leftTalon = new TalonSRX(leftTalonIntakeID);
        this.rightTalon = new TalonSRX(rightTalonIntakeID);
        this.rightTalon.setInverted(true);

        this.leftTalon.configPeakOutputForward(INTAKE_SLOW);
        this.leftTalon.configPeakOutputReverse(INTAKE_FAST);
        this.rightTalon.configPeakOutputForward(INTAKE_FAST);
        this.rightTalon.configPeakOutputReverse(INTAKE_SLOW);

        List<TalonSRX> talonSRXES = Arrays.asList(this.leftTalon, this.rightTalon);

        for (TalonSRX t : talonSRXES) {
            t.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,
                    kPIDLoopIdx,
                    kTimeoutMs);

            t.config_kF(kPIDLoopIdx, 0, kTimeoutMs);
            t.config_kP(kPIDLoopIdx, kIntakeP, kTimeoutMs);
            t.config_kI(kPIDLoopIdx, kIntakeI, kTimeoutMs);
            t.config_kD(kPIDLoopIdx, kIntakeD, kTimeoutMs);
            t.configPeakOutputForward(kShooterPeakOutput, kTimeoutMs);
            t.configPeakOutputReverse(-kShooterPeakOutput, kTimeoutMs);
            t.configClosedloopRamp(kRampRate);
        }
    }

    public TalonSRX getLeftTalon() {
        return leftTalon;
    }
    public TalonSRX getRightTalon() { return rightTalon; }


    public void resetInitialize(){
        leftInitCount = 0;
        leftInitDone = false;
        rightInitCount = 0;
        rightInitDone = false;
    }

    public double getOpenValueLeftIntake() {
        return openValueLeftIntake;
    }

    public double getOpenValueRightIntake() {return openValueRightIntake;}

    public void periodic() {
        if(readyToInit) {
            if (leftInitCount <= MOTOR_INIT_COUNT && !leftInitDone) {
                leftTalon.set(TalonSRXControlMode.PercentOutput, 0.4);
                leftInitCount++;
            } else if (leftTalon.getMotorOutputVoltage() <= 0.01 && !leftInitDone) {
                leftInitDone = true;
                leftTalon.set(TalonSRXControlMode.PercentOutput, 0);
                openValueLeftIntake = leftTalon.getSelectedSensorPosition();
            }

            if (rightInitCount <= MOTOR_INIT_COUNT && !rightInitDone) {
                rightTalon.set(TalonSRXControlMode.PercentOutput, 0.4);
                rightInitCount++;
            } else if (rightTalon.getMotorOutputVoltage() <= 0.01 && !rightInitDone) {
                rightInitDone = true;
                rightTalon.set(TalonSRXControlMode.PercentOutput, 0);
                openValueRightIntake = rightTalon.getSelectedSensorPosition();
            }
        }
    }

    public void setReadyToInit(){
        readyToInit = true;
    }

}
