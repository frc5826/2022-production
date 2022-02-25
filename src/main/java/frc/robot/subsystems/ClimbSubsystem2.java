package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbSubsystem2 extends SubsystemBase {

    private DoubleSolenoid leftSolenoid;
    private DoubleSolenoid rightSolenoid;

    public ClimbSubsystem2(){
        leftSolenoid = new DoubleSolenoid(Constants.Compressor, PneumaticsModuleType.CTREPCM, Constants.leftArm2FWD, Constants.leftArm2REV);
        rightSolenoid = new DoubleSolenoid(Constants.Compressor, PneumaticsModuleType.CTREPCM, Constants.rightArm2FWD, Constants.rightArm2REV);

    }

    public void raise(){
        leftSolenoid.set(DoubleSolenoid.Value.kForward);
        rightSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void lower(){
        leftSolenoid.set(DoubleSolenoid.Value.kReverse);
        rightSolenoid.set(DoubleSolenoid.Value.kReverse);

    }
}
