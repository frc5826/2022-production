package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeHomeCommand extends IntakePositionCommand {

    public IntakeHomeCommand(IntakeSubsystem intakeSubsystem) {
        super(Constants.INTAKE_HOME_POS, intakeSubsystem);

    }
}
