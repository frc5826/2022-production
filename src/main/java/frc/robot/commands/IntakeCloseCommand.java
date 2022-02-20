package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCloseCommand extends IntakePositionCommand {

    public IntakeCloseCommand(IntakeSubsystem intakeSubsystem) {
        super(Constants.INTAKE_CLOSE_POS, intakeSubsystem);

    }
}
