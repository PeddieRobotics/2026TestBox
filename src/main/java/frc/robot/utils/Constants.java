package frc.robot.utils;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

public final class Constants {
    public static class LimelightConstants {
        public static final String kTurretLimelightName = "limelight-turret";
    }

    public static class TurretConstants {
        public static final double kTurretToHubHeight = 0.0;
        public static final Translation2d kRobotCenterToTurretCenter = new Translation2d(0.18, 0.05);

        // valid range to not destroy turret is [-kTurretRange, kTurretRange]
        // from 0 (FORWARD), not the wire neutral position (which is at 90 degrees)
        public static final double kLeftTurretMin = -360;
        public static final double kLeftTurretMax = 180;

        public static final double kRightTurretMin = -180;
        public static final double kRightTurretMax = 360;
        
        public static final double kLeftNeutralDegrees = -90;
        public static final double kRightNeutralDegrees = 90;
        
        public static final double kSpringDeadbandMin = -0.452637;
        public static final double kSpringDeadbandMax = -0.217041;
        
        public static final double kSpringFeedforward = 0.85;
        
        public static final int kKrakenGearTeeth = 12;
        public static final int kTurretGearTeeth = 200;
        public static final int kEncoderGear1Teeth = 20; // n1
        public static final int kEncoderGear2Teeth = 21; // n2

        public static final double kKrakenToTurretRatio = (double)kTurretGearTeeth/kKrakenGearTeeth;

        public static final int kTurretMotorDeviceId = 60;
        public static final int kEncoderId1 = 61;
        public static final int kEncoderId2 = 62;
        
        // set to true when zeroing turret
        // WHEN SETTING THE MAGNET OFFSET, DO NOT MAKE THE MAGNET OFFSETS ZERO
        // SET ZEROING_MODE TO TRUE TO ZERO
        // negative of "raw absolute position", don't add mod amount offset

        public static final boolean ZEROING_MODE = false;
        public static final double kEncoder1MagnetOffset = -0.353271484375;
        public static final double kEncoder2MagnetOffset = -0.39404296875;

        // this is the "number of gears moved" (found by CRT)
        // where the turret's position is when facing forward for both turrets
        public static final int kNeutralPositionTeethRaw = kEncoderGear1Teeth * kEncoderGear2Teeth / 2;
        
        // 1 degree = (kTurretGearTeeth / 360) teeth
        public static double positionDegreeToTeeth(double degree) {
            return degree * kTurretGearTeeth / 360;
        }

        // 1 tooth = (360 / kTurretGearTeeth) degrees
        public static double positionTeethToDegree(double teeth) {
            return teeth * 360 / kTurretGearTeeth;
        }

        public static final double kP = 160.0;
        public static final double kI = 0.0;
        public static final double kD = 2.0;
        public static final double kS = 0.0;
        public static final double kFF = 0.0;

        public static final double kCruiseVelocity = 2;
        public static final double kMaxAcceleration = 10; // radians/seconds^2

        public static final double kEpsilon = 1.0;
        
        // these are always positive, see Turret.java for explanation
        public static final double kLeftMinPositionRotations = kLeftTurretMin / 360.0;
        public static final double kLeftMaxPositionRotations = kLeftTurretMax / 360.0;

        public static final double kRightMinPositionRotations = kRightTurretMin / 360.0;
        public static final double kRightMaxPositionRotations = kRightTurretMax / 360.0;
        
        public static class CRTConstants {
            public static final int y_1 = 1; 
            public static final int y_2 = -1; 
            
            // by definition from CRT: y_1 is such that y_1 * m_1 ≡ 1 (mod n_1)
            // from the CRT: m_1 = n_2 = 21, m_2 = n_1 = 20
            // y_1 * m_1 = 1 * 21 = 21 ≡ 1 (mod 20)
            // y_2 * m_2 = -1 * 20 = -20 ≡ 1 (mod 21)
        }
    }
}
