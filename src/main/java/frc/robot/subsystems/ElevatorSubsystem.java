package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {

    private final TalonSRX elevatorTalon;

    public ElevatorSubsystem(){
        this.elevatorTalon = new TalonSRX(Constants.ElevatorID);
    }

    public TalonSRX getElevatorTalon(){
        return elevatorTalon;
    }
}
