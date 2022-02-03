package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.Arrays;
import java.util.List;

import static frc.robot.Constants.*;

public class IntakeSubsystem extends SubsystemBase {

    final TalonSRX leftTalon;
    //final TalonSRX rightTalon;

    public IntakeSubsystem() {
        this.leftTalon = new TalonSRX(leftTalonIntakeID);
        //this.rightTalon = new TalonSRX(rightTalonIntakeID);

        List<TalonSRX> talonSRXES = Arrays.asList(this.leftTalon);

        for (TalonSRX t : talonSRXES) {
            t.configSelectedFeedbackSensor(FeedbackDevice.PulseWidthEncodedPosition,
                    kPIDLoopIdx,
                    kTimeoutMs);

            t.config_kF(kPIDLoopIdx, 0, kTimeoutMs);
            t.config_kP(kPIDLoopIdx, kShooterP, kTimeoutMs);
            t.config_kI(kPIDLoopIdx, kShooterI, kTimeoutMs);
            t.config_kD(kPIDLoopIdx, kShooterD, kTimeoutMs);
            t.configPeakOutputForward(kShooterPeakOutput, kTimeoutMs);
            t.configPeakOutputReverse(-kShooterPeakOutput, kTimeoutMs);
            t.configClosedloopRamp(kRampRate);
        }
    }

    public TalonSRX getLeftTalon() {
        return leftTalon;
    }

//    public TalonSRX getRightTalon(){
//        return rightTalon;
//    }

    public void setPosition(double position) {
        leftTalon.set(TalonSRXControlMode.Position, position);
        //rightTalon.set(TalonSRXControlMode.Position, position);
    }

    public void periodic() {
        getLeftTalon().set(TalonSRXControlMode.Position, 0);
        //System.out.println(getLeftTalon().getSelectedSensorPosition());
    }

}
