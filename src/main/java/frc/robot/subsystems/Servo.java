package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants.ServoConstants;

import com.revrobotics.servohub.ServoHub;
import com.revrobotics.servohub.ServoChannel.ChannelId;
import com.revrobotics.servohub.ServoChannel;
import com.revrobotics.ResetMode;
import com.revrobotics.servohub.config.ServoHubConfig;

public class Servo extends SubsystemBase {
    private static Servo servo;
    private ServoHub servoHub;
    private ServoChannel servoChannel0, servoChannel3;
    private ServoHubConfig servoHubConfig;

    public Servo() {
        servoHubConfig = new ServoHubConfig();
        servoHubConfig.channel0.pulseRange(500,1500,2500);
        servoHubConfig.channel1.pulseRange(500,1500,2500);

        servoHub = new ServoHub(ServoConstants.kServoHubId);
        servoHub.configure(servoHubConfig,ResetMode.kResetSafeParameters);

        servoChannel0 = servoHub.getServoChannel(ChannelId.kChannelId0);
        servoChannel3 = servoHub.getServoChannel(ChannelId.kChannelId3);

        servoHub.setBankPulsePeriod(ServoHub.Bank.kBank0_2, 5000);
        servoHub.setBankPulsePeriod(ServoHub.Bank.kBank3_5, 5000);

        SmartDashboard.putNumber("servo 0 pulse width", 1500);
        SmartDashboard.putNumber("servo 3 pulse width", 1500);
    }

    public static Servo getInstance() {
        if (servo == null)
            servo = new Servo();
        return servo;
    }

    @Override
    public void periodic() {
        servoChannel0.setPowered(true);
        servoChannel0.setEnabled(true);

        servoChannel3.setPowered(true);
        servoChannel3.setEnabled(true);

        servoChannel0.setPulseWidth((int)SmartDashboard.getNumber("servo 0 pulse width", 1500));
        servoChannel3.setPulseWidth((int)SmartDashboard.getNumber("servo 3 pulse width", 1500));

    }
}

// [Servo Hub] IDs: 3, The specified value is not within the valid range 1
