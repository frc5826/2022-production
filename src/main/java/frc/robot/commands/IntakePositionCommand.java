package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import java.lang.Math;

public class IntakePositionCommand extends CommandBase {

    private final IntakeSubsystem intakeSubsystem;
    private int position;
    private boolean rightDone = false;
    private boolean leftDone = false;

    public IntakePositionCommand(int position, IntakeSubsystem intakeSubsystem) {
        this.position = position;
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
    }

    @Override
    public boolean isFinished(){

        double leftDiff = Math.abs((intakeSubsystem.getOpenValueLeftIntake() - position) - intakeSubsystem.getLeftTalon().getSelectedSensorPosition());
        double rightDiff = Math.abs((intakeSubsystem.getOpenValueRightIntake() - position) - intakeSubsystem.getRightTalon().getSelectedSensorPosition());

        if (leftDiff <= 100){
            leftDone=true;
        }
        if (rightDiff <= 100){
            rightDone=true;
        }
        //System.out.println(leftDiff);
        //System.out.println(rightDiff);
        return leftDone && rightDone;
    }
}
