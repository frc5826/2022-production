package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {

    private final TalonSRX elevatorTalon;
    private boolean done = false;

    public ElevatorSubsystem(){
        this.elevatorTalon = new TalonSRX(Constants.ElevatorID);
    }

    public TalonSRX getElevatorTalon(){
        return elevatorTalon;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
        SmartDashboard.putBoolean("ElevatorDoneMoving", done);
    }
}
