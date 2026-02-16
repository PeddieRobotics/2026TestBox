package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.servohub.ServoHub;
import com.revrobotics.servohub.ServoChannel.ChannelId;
import com.revrobotics.servohub.ServoChannel;
import com.revrobotics.ResetMode;
import com.revrobotics.servohub.config.ServoHubConfig;

public class Servo extends SubsystemBase{

    private ServoHubConfig config;
    private ServoHub servohub;
    private ServoChannel channel0, channel1, channel2, channel3, channel4, channel5;
    public static Servo servo;

    public Servo() {
        servohub = new ServoHub(3);
        
        channel0 = servohub.getServoChannel(ChannelId.kChannelId0);
        channel1 = servohub.getServoChannel(ChannelId.kChannelId1);
        channel2 = servohub.getServoChannel(ChannelId.kChannelId2);
        channel3 = servohub.getServoChannel(ChannelId.kChannelId3);
        channel4 = servohub.getServoChannel(ChannelId.kChannelId4);
        channel5 = servohub.getServoChannel(ChannelId.kChannelId5);

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

        servohub.setBankPulsePeriod(ServoHub.Bank.kBank0_2,5000);
        servohub.setBankPulsePeriod(ServoHub.Bank.kBank3_5,5000);

        SmartDashboard.putNumber("Channel 0 pulse width", 1500);
        SmartDashboard.putNumber("Channel 1 pulse width", 1500);
        SmartDashboard.putNumber("Channel 2 pulse width", 1500);
        SmartDashboard.putNumber("Channel 3 pulse width", 1500);
        SmartDashboard.putNumber("Channel 4 pulse width", 1500);
        SmartDashboard.putNumber("Channel 5 pulse width", 1500);

    }

    public static Servo getInstance() {
        if (servo == null)
          servo = new Servo();
        return servo;
    }

    @Override
    public void periodic() {
       channel0.setPulseWidth((int)SmartDashboard.getNumber("Channel 0 pulse width", 1500));
       channel1.setPulseWidth((int)SmartDashboard.getNumber("Channel 1 pulse width", 1500));
       channel2.setPulseWidth((int)SmartDashboard.getNumber("Channel 2 pulse width", 1500));
       channel3.setPulseWidth((int)SmartDashboard.getNumber("Channel 3 pulse width", 1500));
       channel4.setPulseWidth((int)SmartDashboard.getNumber("Channel 4 pulse width", 1500));
       channel5.setPulseWidth((int)SmartDashboard.getNumber("Channel 5 pulse width", 1500));
    }
}

// [Servo Hub] IDs: 3, The specified value is not within the valid range 1




