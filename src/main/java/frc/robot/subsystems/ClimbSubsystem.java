package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbSubsystem extends SubsystemBase {

    private DoubleSolenoid solenoid;

    public ClimbSubsystem(){
//        solenoid = new DoubleSolenoid(Constants.Compressor, PneumaticsModuleType.CTREPCM, Constants.armFWD, Constants.armREV);
    }

    public void raise(){
        solenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void lower(){
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }
}
