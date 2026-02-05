package frc.robot.utils;

public final class Constants {
    public static class LimelightConstants {
        public static final String kTurretLimelightName = "limelight-turret";
    }
    public static class TurretConstants {
        public static final int kTurretMotorDeviceId = 60;
        public static final int kEncoderId1 = 61;
        public static final int kEncoderId2 = 62;
        
        // set to true when zeroing turret
        public static final boolean ZEROING_MODE = false;
        
        // WHEN SETTING THE MAGNET OFFSET, DO NOT MAKE THESE ZERO
        // SET ZEROING_MODE TO TRUE, VALUES HERE DON'T MATTER
        // negative of "raw absolute position", don't add mod amount offset
        public static final double kEncoder1MagnetOffset = -0.428711;
        public static final double kEncoder2MagnetOffset = -0.805664;
        
        // valid range to not destroy turret is [-kTurretRange, kTurretRange]
        public static final double kTurretRange = 270;

        public static final int kTurretGearTeeth = 200;
        public static final int kEncoderGear1Teeth = 20; // n1
        public static final int kEncoderGear2Teeth = 21; // n2

        // this is the "number of gears moved" (found by CRT)
        // where the turret's position is 0 degrees, aka forward
        // 210
        public static final int kZeroPositionTeethRaw = kEncoderGear1Teeth * kEncoderGear2Teeth / 2;

        // 1 degree = (kTurretGearTeeth / 360) teeth
        public static double positionDegreeToTeeth(double degree) {
            return degree * kTurretGearTeeth / 360;
        }

        // 1 tooth = (360 / kTurretGearTeeth) degrees
        public static double positionTeethToDegree(double teeth) {
            return teeth * 360 / kTurretGearTeeth;
        }

        public static final double kP = 0.0;
        public static final double kI = 0.0;
        public static final double kD = 0.0;
        public static final double kS = 0.0;
        public static final double kFF = 0.0;
        
        public static final double kEpsilon = 0.0;
        public static final double kVoltageMax = 0;
        
        // these are always positive, see Turret.java for explanation
        public static final double kMinPositionTeethRaw = positionDegreeToTeeth(-kTurretRange) + kZeroPositionTeethRaw;
        public static final double kMaxPositionTeethRaw = positionDegreeToTeeth(kTurretRange) + kZeroPositionTeethRaw;
        
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
