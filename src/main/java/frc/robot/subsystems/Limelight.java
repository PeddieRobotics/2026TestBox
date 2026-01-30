package frc.robot.subsystems;

import java.util.Optional;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.networktables.StructPublisher;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.LimelightHelpers;
import frc.robot.utils.LimelightHelpers.PoseEstimate;

public abstract class Limelight extends SubsystemBase {
    private static AprilTagFieldLayout aprilTagFieldLayout;

    private String limelightName;

    private Field2d fieldMT2;
    private StructPublisher<Pose2d> publisherMT2;

    protected Limelight(String limelightName) {
        aprilTagFieldLayout = AprilTagFieldLayout.loadField(AprilTagFields.kDefaultField);

        this.limelightName = limelightName;

        fieldMT2 = new Field2d();
        SmartDashboard.putData(limelightName + " estimated pose", fieldMT2);
    }

    @Override
    public void periodic() {
        // double gyro = 0;
        // LimelightHelpers.SetRobotOrientation(
        //     limelightName, gyro,
        //     0, 0, 0, 0, 0
        // );

        // Optional<Pose2d> estimatedPoseMT2 = getEstimatedPoseMT2();
        // if (estimatedPoseMT2.isPresent()) {
        //     fieldMT2.setRobotPose(estimatedPoseMT2.get());
        //     publisherMT2.set(estimatedPoseMT2.get());
        // }
    }

    // ========================================================
    //                 Pose/Translation Getters
    // ========================================================

    private Optional<PoseEstimate> getPoseEstimateMT2() {
        PoseEstimate poseEstimate = LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2(limelightName);
        return poseEstimate == null ? Optional.empty() : Optional.of(poseEstimate);
    }

    public Optional<Pose2d> getEstimatedPoseMT2() {
        PoseEstimate poseEstimate = LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2(limelightName);
        return poseEstimate == null ? Optional.empty() : Optional.of(poseEstimate.pose);
    }

    // ================================================
    //                 Distance Getters
    // ================================================

    // distance to CENTER OF ROBOT
    public double getDistanceEstimatedPose() {
        if (!hasTarget())
            return 0;

        var aprilTagPose = aprilTagFieldLayout.getTagPose(getTargetID());
        if (!aprilTagPose.isPresent())
            return 0;

        Pose2d tag = aprilTagPose.get().toPose2d();

        Optional<PoseEstimate> estimatedPoseEstimate = getPoseEstimateMT2();
        if (!estimatedPoseEstimate.isPresent())
            return 0;
        Pose2d robotPose = estimatedPoseEstimate.get().pose;

        double dx = tag.getX() - robotPose.getX();
        double dy = tag.getY() - robotPose.getY();

        return Math.sqrt(dx * dx + dy * dy);
    }

    // ======================================================
    //                 Other AprilTag Getters
    // ======================================================

    public int getNumberOfTagsSeen() {
        return LimelightHelpers.getTargetCount(limelightName);
    }

    public int getTargetID() {
        return (int) LimelightHelpers.getFiducialID(limelightName);
    }

    public boolean hasTarget() {
        return LimelightHelpers.getTV(limelightName);
    }

    public static Pose2d getAprilTagPose(int tag) {
        var aprilTagPose = aprilTagFieldLayout.getTagPose(tag);
        if (!aprilTagPose.isPresent())
            return new Pose2d();
        return aprilTagPose.get().toPose2d();
    }

    public Pose2d getAprilTagPose() {
        var aprilTagPose = aprilTagFieldLayout.getTagPose(getTargetID());
        if (!aprilTagPose.isPresent())
            return new Pose2d();
        return aprilTagPose.get().toPose2d();
    }

    // ====================================================
    //                 Pipeline Controllers
    // ====================================================

    public int getPipeline() {
        return (int) LimelightHelpers.getCurrentPipelineIndex(limelightName);
    }

    public void setPipeline(int pipelineIndex) {
        LimelightHelpers.setPipelineIndex(limelightName, pipelineIndex);
    }

    // ======================================
    //                 Others
    // ======================================

    public double getTotalLatencyInMS() {
        return LimelightHelpers.getLatency_Capture(limelightName) + LimelightHelpers.getLatency_Pipeline(limelightName);
    }

    public String getName() {
        return limelightName;
    }
}
