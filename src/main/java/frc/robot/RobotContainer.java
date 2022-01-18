// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.JoystickDriveCommand;
import frc.robot.subsystems.DriveSubsystem;



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

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer()
    {
        driveSubsystem = new DriveSubsystem();
        joystickDriveCommand = new JoystickDriveCommand(driveSubsystem);
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
        //JoystickButton button = new JoystickButton(Constants.joystick, 1);
        //- You can now tie commands to actions of that button. Some examples (not exhaustive) below...
        //button.whileHeld(new SomeCommand());
        //button.whenPressed(new SomeCommand());
        //button.whenReleased(new SomeCommand());
        //- When creating these bindings, think through if you want a new command or want to reuse an existing one.
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
}
