package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.servohub.ServoHub;
import com.revrobotics.servohub.ServoChannel.ChannelId;
import com.revrobotics.servohub.ServoChannel;
import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.ResetMode;
import com.revrobotics.servohub.config.ServoHubConfig;
import com.revrobotics.spark.SparkClosedLoopController;

public class Servo extends SubsystemBase {
    private ServoHubConfig config;
    private ServoHub servoHub;
    private ServoChannel channel0, channel1, channel2, channel3, channel4, channel5;
    private static Servo servo;

    private PIDController controller;

    private CANcoder leftCANcoder, rightCANcoder;
    private CANBus canbus;

    private double kS, kP, kI, kD, kEpsilon;
    private final double kMaxRotation;

    public Servo() {

        servoHub = new ServoHub(3);

        canbus = new CANBus("rio");
        leftCANcoder = new CANcoder(0, canbus);
        rightCANcoder = new CANcoder(0, canbus);

        leftCANcoder.setPosition(0);
        rightCANcoder.setPosition(0);

        channel0 = servoHub.getServoChannel(ChannelId.kChannelId0);
        channel1 = servoHub.getServoChannel(ChannelId.kChannelId1);
        channel2 = servoHub.getServoChannel(ChannelId.kChannelId2);
        channel3 = servoHub.getServoChannel(ChannelId.kChannelId3);
        channel4 = servoHub.getServoChannel(ChannelId.kChannelId4);
        channel5 = servoHub.getServoChannel(ChannelId.kChannelId5);

        channel0.setPowered(true);
        channel0.setEnabled(true);

        channel1.setPowered(true);
        channel1.setEnabled(true);

        channel2.setPowered(true);
        channel2.setEnabled(true);

        channel3.setPowered(true);
        channel3.setEnabled(true);

        channel4.setPowered(true);
        channel4.setEnabled(true);

        channel5.setPowered(true);
        channel5.setEnabled(true);

        config = new ServoHubConfig();
        config.channel0.pulseRange(500, 1500, 2500);
        config.channel1.pulseRange(500, 1500, 2500);
        config.channel2.pulseRange(500, 1500, 2500);
        config.channel3.pulseRange(500, 1500, 2500);
        config.channel4.pulseRange(500, 1500, 2500);
        config.channel5.pulseRange(500, 1500, 2500);

        servoHub.configure(config, ResetMode.kResetSafeParameters).toString();

        System.out.println("3-5 status: " + servoHub.setBankPulsePeriod(ServoHub.Bank.kBank3_5, 5000).toString());
        System.out.println("0-2 status: " + servoHub.setBankPulsePeriod(ServoHub.Bank.kBank0_2, 5000).toString());

        SmartDashboard.putNumber("0-1 desired rotation", 0);
        SmartDashboard.putNumber("4-5 desired rotation", 0.5);
        SmartDashboard.putNumber("kP", 0);
        SmartDashboard.putNumber("kI", 0);
        SmartDashboard.putNumber("kD", 0);
        SmartDashboard.putNumber("kEpsilon", 0);
        SmartDashboard.putNumber("CANCoder max rotations", 5);
        SmartDashboard.putNumber("change speed", 0);
        SmartDashboard.putNumber("CANCoder current position", 0);

        kS = 25;
        kEpsilon = SmartDashboard.getNumber("kEpsilon", 0);
        kP = SmartDashboard.getNumber("kP", 0.0);
        kI = SmartDashboard.getNumber("kI", 0.0);
        kD = SmartDashboard.getNumber("kD", 0.0);
        kMaxRotation = 1;

        controller = new PIDController(kP, kI, kD);
    }

    public static Servo getInstance() {
        if (servo == null)
            servo = new Servo();
        return servo;
    }

    @Override
    public void periodic() {

        kEpsilon = SmartDashboard.getNumber("kEpsilon", 0.0);
        kP = SmartDashboard.getNumber("kP", 0.0);
        kI = SmartDashboard.getNumber("kI", 0.0);
        kD = SmartDashboard.getNumber("kD", 0.0);

        // [-1000, 1000]
        controller.setPID(kP, kI, kD);

        double change = controller.calculate(SmartDashboard.getNumber("CANCoder current position", 0),
                SmartDashboard.getNumber("4-5 desired rotation", 0));

        change = 1000 * (Math.abs(change) > kMaxRotation ? Math.signum(change) : change);
        change += kS * Math.signum(change);
        SmartDashboard.putNumber("change speed", change);

        // if (leftCANcoder.getPosition().getValueAsDouble() > kMaxRotation
        // || leftCANcoder.getPosition().getValueAsDouble() < 0)
        // change = 0;
        // epsilon, kMax (?)
        double currentDifference = Math.abs(SmartDashboard.getNumber("CANCoder current position", 0)
                - SmartDashboard.getNumber("4-5 desired rotation", 0));
        if (currentDifference < kEpsilon
                || Math.abs(SmartDashboard.getNumber("CANCoder current position", 0)) > kMaxRotation)
            change = 0;

        channel4.setPulseWidth(1500 + (int) change);
        channel5.setPulseWidth(1500 - (int) change);

        // int currentPosition = ???; // 
        // SmartDashboard.putNumber("CANCoder current position", currentPosition);
    }
}
