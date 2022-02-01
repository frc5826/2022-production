package frc.robot.commands;

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
        if(count++ % 25 == 0){
            System.out.println(intakeSubsystem.getLeftTalon().getSelectedSensorPosition());
            //System.out.println(intakeSubsystem.getRightTalon().getSelectedSensorPosition());
        }
    }
}
