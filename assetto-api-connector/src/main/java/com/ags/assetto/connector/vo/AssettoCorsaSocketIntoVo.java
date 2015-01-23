package com.ags.assetto.connector.vo;

/**
 * {
         "speed_Kmh":0.013587577,
         "speed_Mph":0.00844293,
         "speed_Ms":0.0037743272,
         "accG_vertical":0.0,
         "accG_horizontal":0.0,
         "accG_frontal":0.0,
         "lapTime":0,
         "lastLap":0,
         "bestLap":0,
         "lapCount":0,
         "gas":0.0,
         "brake":0.0,
         "clutch":1.0,
         "engineRPM":850.0056,
         "steer":0.0,
         "gear":1,
         "cgHeight":0.4654007,
         "wheelAngularSpeed":[-0.026404204,-0.014231022,-0.0155581385,0.004395854],
         "slipAngle":[9.125631,1.7346281,1.7864097,-3.274189],
         "slipAngle_ContactPatch":[9.12565,1.7344664,1.7861152,-3.273534],
         "slipRatio":[-0.017278217,0.19511239,-0.20067248,-0.5543986],
         "tyreSlip":[0.0012719478,7.661486E-4,0.0026888337,0.0021755125],
         "ndSlip":[3.973832E-4,6.984498E-4,9.00086E-4,0.0015044169],
         "load":[3472.4275,3241.7056,4523.5186,4313.4736],
         "tyreDirtyLevel":[0.0,0.0,0.0,0.0],
         "camberRAD":[-0.0065600844,-0.009537158,-0.018841863,-0.02084547],
         "tyreRadius":[0.33405,0.33405,0.3425,0.3425],
         "tyreLoadedRadius":[0.32666266,0.3271535,0.33237463,0.3328448],
         "suspensionHeight":[0.0644842,0.062095918,0.062162407,0.059433915],
         "carPositionNormalized":0.9773867,
         "carSlope":0.0,
         "carCoordinates":[-12.98155,64.621826,-877.4704],
         "tcEnabled":true,
         "absInAction":false,
         "inPit":true,
         "absEnabled":true,
         "tcInAction":false,
         "engineLimiterOn":true,
         "mz":[-0.0033371085,-0.00202661,-0.00879017,-0.0076215114],
         "dy":[1.239459,1.2497879,1.256359,1.2639492]
 }


 * Created by Gavalda on 1/22/2015.
 */
public class AssettoCorsaSocketIntoVo {


    private float speed_Kmh;
    private float speed_Mph;
    private float speed_Ms;
    private boolean isAbsEnabled;
    private boolean isAbsInAction;
    private boolean isTcInAction;
    private boolean isTcEnabled;
    private boolean isInPit;
    private boolean isEngineLimiterOn;
    private float accG_vertical;
    private float accG_horizontal;
    private float accG_frontal;
    private int lapTime;
    private int lastLap;
    private int bestLap;
    private int lapCount;
    private float gas;
    private float brake;
    private float clutch;
    private float engineRPM;
    private float steer;
    private int gear;
    private float cgHeight;
    private float[] wheelAngularSpeed;
    private float[] slipAngle;
    private float[] slipAngle_ContactPatch;
    private float[] slipRatio;
    private float[] tyreSlip;
    private float[] ndSlip;
    private float[] load;
    private float[] Dy;
    private float[] Mz;
    private float[] tyreDirtyLevel;
    private float[] camberRAD;
    private float[] tyreRadius;
    private float[] tyreLoadedRadius;
    private float[] suspensionHeight;
    private float carPositionNormalized;
    private float carSlope;
    private float[] carCoordinates;


    public int getSpeed_Kmh() {
        return (int)speed_Kmh;
    }

    public void setSpeed_Kmh(float speed_Kmh) {
        this.speed_Kmh = speed_Kmh;
    }

    public float getSpeed_Mph() {
        return speed_Mph;
    }

    public void setSpeed_Mph(float speed_Mph) {
        this.speed_Mph = speed_Mph;
    }

    public float getSpeed_Ms() {
        return speed_Ms;
    }

    public void setSpeed_Ms(float speed_Ms) {
        this.speed_Ms = speed_Ms;
    }

    public boolean isAbsEnabled() {
        return isAbsEnabled;
    }

    public void setAbsEnabled(boolean isAbsEnabled) {
        this.isAbsEnabled = isAbsEnabled;
    }

    public boolean isAbsInAction() {
        return isAbsInAction;
    }

    public void setAbsInAction(boolean isAbsInAction) {
        this.isAbsInAction = isAbsInAction;
    }

    public boolean isTcInAction() {
        return isTcInAction;
    }

    public void setTcInAction(boolean isTcInAction) {
        this.isTcInAction = isTcInAction;
    }

    public boolean isTcEnabled() {
        return isTcEnabled;
    }

    public void setTcEnabled(boolean isTcEnabled) {
        this.isTcEnabled = isTcEnabled;
    }

    public boolean isInPit() {
        return isInPit;
    }

    public void setInPit(boolean isInPit) {
        this.isInPit = isInPit;
    }

    public boolean isEngineLimiterOn() {
        return isEngineLimiterOn;
    }

    public void setEngineLimiterOn(boolean isEngineLimiterOn) {
        this.isEngineLimiterOn = isEngineLimiterOn;
    }

    public float getAccG_vertical() {
        return accG_vertical;
    }

    public void setAccG_vertical(float accG_vertical) {
        this.accG_vertical = accG_vertical;
    }

    public float getAccG_horizontal() {
        return accG_horizontal;
    }

    public void setAccG_horizontal(float accG_horizontal) {
        this.accG_horizontal = accG_horizontal;
    }

    public float getAccG_frontal() {
        return accG_frontal;
    }

    public void setAccG_frontal(float accG_frontal) {
        this.accG_frontal = accG_frontal;
    }

    public int getLapTime() {
        return lapTime;
    }

    public void setLapTime(int lapTime) {
        this.lapTime = lapTime;
    }

    public int getLastLap() {
        return lastLap;
    }

    public void setLastLap(int lastLap) {
        this.lastLap = lastLap;
    }

    public int getBestLap() {
        return bestLap;
    }

    public void setBestLap(int bestLap) {
        this.bestLap = bestLap;
    }

    public int getLapCount() {
        return lapCount;
    }

    public void setLapCount(int lapCount) {
        this.lapCount = lapCount;
    }

    public float getGas() {
        return gas;
    }

    public void setGas(float gas) {
        this.gas = gas;
    }

    public float getBrake() {
        return brake;
    }

    public void setBrake(float brake) {
        this.brake = brake;
    }

    public float getClutch() {
        return clutch;
    }

    public void setClutch(float clutch) {
        this.clutch = clutch;
    }

    public int getEngineRPM() {
        return (int)engineRPM;
    }

    public void setEngineRPM(float engineRPM) {
        this.engineRPM = engineRPM;
    }

    public float getSteer() {
        return steer;
    }

    public void setSteer(float steer) {
        this.steer = steer;
    }

    public int getGear() {
        return gear;
    }

    public void setGear(int gear) {
        this.gear = gear;
    }

    public float getCgHeight() {
        return cgHeight;
    }

    public void setCgHeight(float cgHeight) {
        this.cgHeight = cgHeight;
    }

    public float[] getWheelAngularSpeed() {
        return wheelAngularSpeed;
    }

    public void setWheelAngularSpeed(float[] wheelAngularSpeed) {
        this.wheelAngularSpeed = wheelAngularSpeed;
    }

    public float[] getSlipAngle() {
        return slipAngle;
    }

    public void setSlipAngle(float[] slipAngle) {
        this.slipAngle = slipAngle;
    }

    public float[] getSlipAngle_ContactPatch() {
        return slipAngle_ContactPatch;
    }

    public void setSlipAngle_ContactPatch(float[] slipAngle_ContactPatch) {
        this.slipAngle_ContactPatch = slipAngle_ContactPatch;
    }

    public float[] getSlipRatio() {
        return slipRatio;
    }

    public void setSlipRatio(float[] slipRatio) {
        this.slipRatio = slipRatio;
    }

    public float[] getTyreSlip() {
        return tyreSlip;
    }

    public void setTyreSlip(float[] tyreSlip) {
        this.tyreSlip = tyreSlip;
    }

    public float[] getNdSlip() {
        return ndSlip;
    }

    public void setNdSlip(float[] ndSlip) {
        this.ndSlip = ndSlip;
    }

    public float[] getLoad() {
        return load;
    }

    public void setLoad(float[] load) {
        this.load = load;
    }

    public float[] getDy() {
        return Dy;
    }

    public void setDy(float[] dy) {
        Dy = dy;
    }

    public float[] getMz() {
        return Mz;
    }

    public void setMz(float[] mz) {
        Mz = mz;
    }

    public float[] getTyreDirtyLevel() {
        return tyreDirtyLevel;
    }

    public void setTyreDirtyLevel(float[] tyreDirtyLevel) {
        this.tyreDirtyLevel = tyreDirtyLevel;
    }

    public float[] getCamberRAD() {
        return camberRAD;
    }

    public void setCamberRAD(float[] camberRAD) {
        this.camberRAD = camberRAD;
    }

    public float[] getTyreRadius() {
        return tyreRadius;
    }

    public void setTyreRadius(float[] tyreRadius) {
        this.tyreRadius = tyreRadius;
    }

    public float[] getTyreLoadedRadius() {
        return tyreLoadedRadius;
    }

    public void setTyreLoadedRadius(float[] tyreLoadedRadius) {
        this.tyreLoadedRadius = tyreLoadedRadius;
    }

    public float[] getSuspensionHeight() {
        return suspensionHeight;
    }

    public void setSuspensionHeight(float[] suspensionHeight) {
        this.suspensionHeight = suspensionHeight;
    }

    public float getCarPositionNormalized() {
        return carPositionNormalized;
    }

    public void setCarPositionNormalized(float carPositionNormalized) {
        this.carPositionNormalized = carPositionNormalized;
    }

    public float getCarSlope() {
        return carSlope;
    }

    public void setCarSlope(float carSlope) {
        this.carSlope = carSlope;
    }

    public float[] getCarCoordinates() {
        return carCoordinates;
    }

    public void setCarCoordinates(float[] carCoordinates) {
        this.carCoordinates = carCoordinates;
    }
}
