package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbSubsystem extends SubsystemBase {

    private DoubleSolenoid leftSolenoid;
    private DoubleSolenoid rightSolenoid;

    public ClimbSubsystem(){
        leftSolenoid = new DoubleSolenoid(Constants.Compressor, PneumaticsModuleType.CTREPCM, Constants.leftArmFWD, Constants.leftArmREV);
        rightSolenoid = new DoubleSolenoid(Constants.Compressor, PneumaticsModuleType.CTREPCM, Constants.rightArmFWD, Constants.rightArmREV);

    }

    public void raise(){
        leftSolenoid.set(DoubleSolenoid.Value.kReverse);
        rightSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void lower(){
        leftSolenoid.set(DoubleSolenoid.Value.kForward);
        rightSolenoid.set(DoubleSolenoid.Value.kForward);
    }
}
