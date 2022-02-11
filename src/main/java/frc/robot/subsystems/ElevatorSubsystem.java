package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ElevatorSubsystem extends SubsystemBase {

    private final TalonSRX elevatorTalon;

    public ElevatorSubsystem(int talonID){
        this.elevatorTalon = new TalonSRX(talonID);
    }

    public TalonSRX getElevatorTalon(){
        return elevatorTalon;
    }
}
