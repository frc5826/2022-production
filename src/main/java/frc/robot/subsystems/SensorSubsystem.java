package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.Port;
import org.photonvision.PhotonCamera;

public class SensorSubsystem extends SubsystemBase {

    //final LidarLitePWM lidar;
    //final AnalogInput sonar;
    //final Rev2mDistanceSensor dist2m;
    final PhotonCamera photonCamera;

    boolean targetRed;

    public SensorSubsystem(int lidarIn, int sonarIn, boolean targetRed){
        //this.lidar = new LidarLitePWM(new DigitalInput(lidarIn));
        //this.sonar = new AnalogInput(sonarIn);
        //this.dist2m = new Rev2mDistanceSensor(Port.kOnboard);
        this.targetRed = targetRed;
        this.photonCamera = new PhotonCamera(Constants.cameraName);
    }



    public double getDistanceLidar() {
        return 0;
    }

    public double getDistanceSonar(){
        return 0;
    }

    public double getDistance2m(){
        return 0;
    }

    public boolean isBall() { return photonCamera.getLatestResult().hasTargets();}

    public double getBallYaw() {
        double ballYaw = -1;
        if (photonCamera.getLatestResult().hasTargets()){
            ballYaw = photonCamera.getLatestResult().getBestTarget().getYaw();
        }
        return ballYaw;
    }

    public boolean isBlue() { return !this.targetRed; }

    public boolean isRed() { return this.targetRed; }

    public double getBallDistance() {
        double ballDistance = -1;
        if (photonCamera.getLatestResult().hasTargets()) {
            ballDistance = 93 / Math.sqrt(photonCamera.getLatestResult().getBestTarget().getArea());
        }
        return ballDistance;
    }

    public void setBlue() {
        this.targetRed = false;
    }

    public void setRed() {
        this.targetRed = true;
    }
}
