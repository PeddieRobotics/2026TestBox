// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Turret;

@SuppressWarnings("unused")
public class RobotContainer {
    // private Flywheel flywheel;
    private Turret turret;

    public RobotContainer() {
        // flywheel = Flywheel.getInstance();
        turret = Turret.getInstance();
    }
}
