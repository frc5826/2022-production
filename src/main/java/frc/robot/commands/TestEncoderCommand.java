package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class TestEncoderCommand extends CommandBase {

    private final IntakeSubsystem intakeSubsystem;
    private int count = 0;

    public TestEncoderCommand(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute() {
        intakeSubsystem.getLeftTalon().set(TalonSRXControlMode.Position, 8192);
        if(count++ % 25 == 0){
            System.out.println(intakeSubsystem.getLeftTalon().getSelectedSensorPosition());
            //System.out.println(intakeSubsystem.getRightTalon().getSelectedSensorPosition());
        }
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.getLeftTalon().set(TalonSRXControlMode.Position, 0);
    }
}
