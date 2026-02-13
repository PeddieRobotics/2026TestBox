// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.LimelightTurret;
import frc.robot.subsystems.Servo;
import frc.robot.subsystems.Turret;

public class RobotContainer {
    // private Flywheel flywheel;
    private Servo servo;
    // private Turret turret;
    // private LimelightTurret llTurret;

    public RobotContainer() {
        // flywheel = Flywheel.getInstance();
        // turret = Turret.getInstance();
        // llTurret = LimelightTurret.getInstance();
        servo = Servo.getInstance();
    }
}
