package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem  extends SubsystemBase {

    private DoubleSolenoid solenoid;

    public ShooterSubsystem(){
        solenoid = new DoubleSolenoid(Constants.Compressor, PneumaticsModuleType.CTREPCM, Constants.shooterPneumFWD, Constants.shooterPneumREV);
    }

    public void shoot(){
        solenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void retract(){
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }
}
