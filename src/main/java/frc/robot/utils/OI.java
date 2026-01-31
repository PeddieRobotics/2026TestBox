// package frc.robot.utils;

// import edu.wpi.first.wpilibj.PS4Controller;

// public class OI {
//     private static OI oi;
//     private PS4Controller controller;

//     public OI() {
//         controller = new PS4Controller(0);
//     }

//     public static OI getInstance() {
//         if (oi == null)
//             oi = new OI();
//         return oi;
//     }

//     public double getForward() {
//         double val = -controller.getRawAxis(PS4Controller.Axis.kLeftY.value);
//         return Math.abs(val) < 0.1 ? 0 : val;
//     }

//     public double getStrafe() {
//         double val = -controller.getRawAxis(PS4Controller.Axis.kLeftX.value);
//         return Math.abs(val) < 0.1 ? 0 : val;
//     }
// }
