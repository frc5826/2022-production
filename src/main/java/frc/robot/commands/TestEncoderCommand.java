package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import java.lang.Math;
import org.w3c.dom.ls.LSOutput;

public class TestEncoderCommand extends CommandBase {

    private final IntakeSubsystem intakeSubsystem;
    private int count = 0;
    private int position;
    private boolean rightDone = false;
    private boolean leftDone = false;

    public TestEncoderCommand(int position, IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute() {
        intakeSubsystem.getLeftTalon().set(TalonSRXControlMode.Position, intakeSubsystem.getOpenValueLeftIntake() - position);
        intakeSubsystem.getRightTalon().set(TalonSRXControlMode.Position, intakeSubsystem.getOpenValueRightIntake() - position);
    }

    @Override
    public void end(boolean interrupted) {
        leftDone = false;
        rightDone = false;
//        intakeSubsystem.getLeftTalon().set(TalonSRXControlMode.Position, intakeSubsystem.getClosedValueLeftIntake());
//        intakeSubsystem.getRightTalon().set(TalonSRXControlMode.Position, intakeSubsystem.getClosedValueRightIntake());
    }

    @Override
    public boolean isFinished(){
        if (Math.abs( (intakeSubsystem.getOpenValueLeftIntake() - position) - intakeSubsystem.getLeftTalon().getSelectedSensorPosition()) <= 5){
            leftDone=true;
        }
        if (Math.abs( (intakeSubsystem.getOpenValueRightIntake() - position) - intakeSubsystem.getRightTalon().getSelectedSensorPosition()) <= 5){
            rightDone=true;
        }
        return leftDone && rightDone;
    }
}
