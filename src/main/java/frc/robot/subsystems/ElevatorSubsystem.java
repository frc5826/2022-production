package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ElevatorSubsystem extends SubsystemBase {
    private final TalonSRX talon;

    public ElevatorSubsystem(int talonID){
        this.talon = new TalonSRX(talonID);
    }

    public TalonSRX getTalon(){
        return talon;
    }
}
