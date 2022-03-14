// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer
{
    // The robot's subsystems and commands are defined here...

    private final IntakeSubsystem intakeSubsystem;
    private final ClimbSubsystem climbSubsystem;
    private final ShooterSubsystem shooterSubsystem;
    private final ElevatorSubsystem elevatorSubsystem;
    private final DriveSubsystem driveSubsystem;
    //private final ClimbSubsystem2 climbSubsystem2;


//    private final SensorSubsystem sensorSubsystem;

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer()
    {
        driveSubsystem = new DriveSubsystem();
        elevatorSubsystem = new ElevatorSubsystem();
        climbSubsystem = new ClimbSubsystem();
        shooterSubsystem = new ShooterSubsystem();
        intakeSubsystem = new IntakeSubsystem();
        //climbSubsystem2 = new ClimbSubsystem2();


//        testEncoderCommandClose = new TestEncoderCommand(2500, intakeSubsystem);
//        testEncoderCommandOpen = new TestEncoderCommandLimit(, intakeSubsystem);
//        testEncoderCommandHome = new TestEncoderCommand(3400, intakeSubsystem);
//        elevatorControllerCommandDown = new ElevatorControllerCommand(Constants.elevatorDownSpeed, elevatorSubsystem);
//        //elevatorControllerCommandUp = new ElevatorControllerCommand(Constants.elevatorUpSpeed, elevatorSubsystem);
//        elevatorControllerCommandUp = new ElevatorUpCloseCommand(testEncoderCommandClose, new ElevatorControllerCommand(Constants.elevatorUpSpeed, elevatorSubsystem));

        // Configure the button bindings
        configureButtonBindings();
    }
    
    
    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings()
    {
        // Add button to command mappings here.
        // See https://docs.wpilib.org/en/stable/docs/software/commandbased/binding-commands-to-triggers.html
        //- Create a JoystickButton and pass it our joystick and the button number.

        JoystickButton trigger = new JoystickButton(Constants.joystick, 1);
        JoystickButton button2 = new JoystickButton(Constants.joystick, 2);
        JoystickButton button3 = new JoystickButton(Constants.joystick, 3);
        JoystickButton button4 = new JoystickButton(Constants.joystick, 4);
        JoystickButton button5 = new JoystickButton(Constants.joystick, 5);
        JoystickButton button6 = new JoystickButton(Constants.joystick, 6);
        JoystickButton button7 = new JoystickButton(Constants.joystick, 7);
        JoystickButton button8 = new JoystickButton(Constants.joystick, 8);
        JoystickButton button9 = new JoystickButton(Constants.joystick, 9);
        JoystickButton button10 = new JoystickButton(Constants.joystick, 10);
        JoystickButton button11 = new JoystickButton(Constants.joystick, 11);
        JoystickButton button12 = new JoystickButton(Constants.joystick, 12);


        //TODO make this easy to read with like a comment or something because I changed it again :)
        trigger.whenPressed(new ShooterElevatorCommand(getShooterSubsystem(), getElevatorSubsystem()));
        button2.whenPressed(new IntakeResetCommand(getIntakeSubsystem()));
        button3.whenPressed(new IntakeOpenCommand(getIntakeSubsystem()));
        button4.whenPressed(new IntakeCloseCommand(getIntakeSubsystem()));
        button5.whenPressed(new IntakeHomeCommand(getIntakeSubsystem()));
        button9.whenPressed(new ElevatorUpCommand(getIntakeSubsystem(), getElevatorSubsystem()));
        button10.whenPressed(new ElevatorDownCommand(getElevatorSubsystem()));
        button11.whenPressed(new RaisePneumatics(getClimbSubsystem()));
        button12.whenPressed(new LowerPneumatics(getClimbSubsystem()));


    }


    public IntakeSubsystem getIntakeSubsystem() {
        return intakeSubsystem;
    }

    public ClimbSubsystem getClimbSubsystem() {
        return climbSubsystem;
    }

//    public ClimbSubsystem2 getClimbSubsystem2() {
//        return climbSubsystem2;
//    }

    public ShooterSubsystem getShooterSubsystem() {
        return shooterSubsystem;
    }

    public ElevatorSubsystem getElevatorSubsystem() {
        return elevatorSubsystem;
    }

    public DriveSubsystem getDriveSubsystem() {
        return driveSubsystem;
    }

    public AutonomousCommandGroup createAutoCommand(){
        return new AutonomousCommandGroup(getShooterSubsystem(), getElevatorSubsystem(), getDriveSubsystem(), getIntakeSubsystem());
    }

    public JoystickDriveCommand createJoystickCommand(){
        return new JoystickDriveCommand(getDriveSubsystem());
    }
}
