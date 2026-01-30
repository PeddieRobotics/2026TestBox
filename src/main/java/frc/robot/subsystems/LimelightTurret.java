package frc.robot.subsystems;

import frc.robot.utils.Constants.LimelightConstants;

public class LimelightTurret extends Limelight {
    private static LimelightTurret llTurret;

    private LimelightTurret() {
        super(LimelightConstants.kTurretLimelightName);
    }

    public static LimelightTurret getInstance() {
        if (llTurret == null)
            llTurret = new LimelightTurret();
        return llTurret;
    }

    @Override
    public void periodic() {
        super.periodic();
    }
}