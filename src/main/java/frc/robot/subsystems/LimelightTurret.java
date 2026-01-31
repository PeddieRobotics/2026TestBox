package frc.robot.subsystems;

import frc.robot.utils.Constants.LimelightConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.utils.LimelightHelpers;

public class LimelightTurret extends Limelight {
    private static LimelightTurret llTurret;

    private LimelightTurret() {
        super(LimelightConstants.kTurretLimelightName);
        SmartDashboard.putNumber("Pretend gyro yaw", 0);
    }

    public static LimelightTurret getInstance() {
        if (llTurret == null)
            llTurret = new LimelightTurret();
        return llTurret;
    }

    @Override
    public void periodic() {
        double gyro = SmartDashboard.getNumber("Pretend gyro yaw", 0);
        LimelightHelpers.SetRobotOrientation(
            LimelightConstants.kTurretLimelightName, gyro,
            0, 0, 0, 0, 0
        );
        
        // super.periodic();
    }
}