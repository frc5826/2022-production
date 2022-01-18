package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {

    final TalonSRX talon;

    public IntakeSubsystem(int intakeTalonID) {
        this.talon = new TalonSRX(intakeTalonID);
    }

    public TalonSRX getTalon(){
        return talon;
    }

}
