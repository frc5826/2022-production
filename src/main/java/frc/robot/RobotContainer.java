// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
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

    private final DriveSubsystem driveSubsystem;
    private final JoystickDriveCommand joystickDriveCommand;
    private final DriveDistanceCommand driveDistanceCommand;
    private final TurnAngleCommand turnAngleCommand;
    private final TurnCommand2 turnCommand2;

    private final BarrySandersCommandGroupLeft barrySandersCommandGroupLeft;
    private final BarrySandersCommandGroupRight barrySandersCommandGroupRight;

    private final TestCommandGroup testCommandGroup;
    private final TestCommandGroupTwo testCommandGroupTwo;

    private final ShooterElevatorCommand shooterCommand;

//    private final DriveSubsystem driveSubsystem;
//    private final JoystickDriveCommand joystickDriveCommand;
//    private final DriveDistanceCommand driveDistanceCommand;
//    private final TurnAngleCommand turnAngleCommand;
//
//    private final TestCommandGroup testCommandGroup;
//    private final TestCommandGroupTwo testCommandGroupTwo;

//    private final ChaseCommand chaseCommand;

    private final IntakeSubsystem intakeSubsystem;

    private final ClimbSubsystem climbSubsystem;
    private final RaisePneumatics raisePneumatics;
    private final LowerPneumatics lowerPneumatics;
    private final ShooterSubsystem shooterSubsystem;

    private final TestEncoderCommand testEncoderCommandClose;
    private final TestEncoderCommandLimit testEncoderCommandOpen;
    private final TestEncoderCommand testEncoderCommandHome;
    private final ElevatorUpCloseCommand elevatorControllerCommandUp;
    private final ElevatorControllerCommand elevatorControllerCommandDown;

    private final AutonomousCommandGroup autonomousCommand;
    private final ElevatorSubsystem elevatorSubsystem;

//    private final SensorSubsystem sensorSubsystem;

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer()
    {
//        sensorSubsystem = new SensorSubsystem(-1,-1, true);

        driveSubsystem = new DriveSubsystem();
        joystickDriveCommand = new JoystickDriveCommand(driveSubsystem);
        driveDistanceCommand = new DriveDistanceCommand(-60, driveSubsystem);

        testCommandGroup = new TestCommandGroup(driveSubsystem);
        testCommandGroupTwo = new TestCommandGroupTwo(driveSubsystem);

        barrySandersCommandGroupLeft = new BarrySandersCommandGroupLeft(driveSubsystem);
        barrySandersCommandGroupRight = new BarrySandersCommandGroupRight(driveSubsystem);

        turnAngleCommand = new TurnAngleCommand(45, driveSubsystem);
        turnCommand2 = new TurnCommand2(driveSubsystem, -180);

        elevatorSubsystem = new ElevatorSubsystem();
        climbSubsystem = new ClimbSubsystem();
        raisePneumatics = new RaisePneumatics(climbSubsystem);
        lowerPneumatics = new LowerPneumatics(climbSubsystem);
        shooterSubsystem = new ShooterSubsystem();


        intakeSubsystem = new IntakeSubsystem();
        testEncoderCommandClose = new TestEncoderCommand(2500, intakeSubsystem);
        testEncoderCommandOpen = new TestEncoderCommandLimit(0.314159265359, intakeSubsystem);
        testEncoderCommandHome = new TestEncoderCommand(3400, intakeSubsystem);

        //elevatorSubsystem = new ElevatorSubsystem(0);

        elevatorControllerCommandDown = new ElevatorControllerCommand(Constants.elevatorDownSpeed, elevatorSubsystem);
        //elevatorControllerCommandUp = new ElevatorControllerCommand(Constants.elevatorUpSpeed, elevatorSubsystem);
        elevatorControllerCommandUp = new ElevatorUpCloseCommand(testEncoderCommandClose, new ElevatorControllerCommand(Constants.elevatorUpSpeed, elevatorSubsystem));

        shooterCommand = new ShooterElevatorCommand(new ShooterCommand(shooterSubsystem), elevatorControllerCommandDown);
        autonomousCommand = new AutonomousCommandGroup(driveSubsystem, new ShooterCommand(shooterSubsystem), elevatorControllerCommandUp, elevatorControllerCommandDown);
//        elevatorControllerCommand = new ElevatorControllerCommand(elevatorSubsystem);
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

        JoystickButton button12 = new JoystickButton(Constants.joystick, 12);

        //- You can now tie commands to actions of that button. Some examples (not exhaustive) below...

//        button8.whenPressed(raisePneumatics);
//        button7.whenPressed(lowerPneumatics);

//        button5.whenPressed(barrySandersCommandGroupLeft);
//        button6.whenPressed(barrySandersCommandGroupRight);

        button3.whenPressed(testEncoderCommandClose);
        button4.whenPressed(testEncoderCommandOpen);
        button12.whenPressed(testEncoderCommandHome);

        button10.whenPressed(elevatorControllerCommandUp);
        button9.whenPressed(elevatorControllerCommandDown);
        trigger.whenPressed(shooterCommand);

        button2.whenPressed(autonomousCommand);
    }
    
    
    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */

    public Subsystem getDriveSubsystem(){
        return driveSubsystem;
    }

    public Command getJoystickDrive() {
        return joystickDriveCommand;
    }

//    public Subsystem getElevatorSubsystem() {
//        return elevatorSubsystem;
//    }
//
//    public Command getElevatorCommand() {
//        return elevatorControllerCommand;
//    }
    public IntakeSubsystem getIntakeSubsystem() {
        return intakeSubsystem;
    }
//    public Command getChaseCommand() { return chaseCommand;}
}
