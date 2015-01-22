package com.ags.assetto.connector;

import com.ags.assetto.connector.utils.OperationEnum;
import com.ags.assetto.connector.utils.ReaderUtils;
import com.ags.assetto.connector.vo.AssettoCorsaSocketIntoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by Gavalda on 1/22/2015.
 */
public class ACConnector implements Runnable {

    private String gameHost = "127.0.0.1";

    private static final Logger LOGGER = LoggerFactory.getLogger(ACConnector.class);

    private DatagramSocket socket;
    private DatagramPacket packet;
    private byte[] buffer;
    private String carName;
    private String driverName;
    private String trackName;
    private String trackConfig;
    private boolean updating;
    private boolean connected;

    private AssettoCorsaSocketIntoVo assettoInfo;


    public ACConnector(String host) throws IOException {
        this.gameHost = host;
        this.init();
    }


    @Override
    public void run() {
        try {
            initHandshake();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        this.buffer = new byte[1024];
        this.packet = new DatagramPacket(this.buffer, this.buffer.length);
        this.socket = new DatagramSocket();
        this.socket.setSoTimeout(1000);
        this.updating = true;
    }


    private void initHandshake() throws InterruptedException {

        try {
            ACConnector.HandshakeHandler var3 = new ACConnector.HandshakeHandler();
            var3.start();
            LOGGER.debug("Handshake start preparation ...");
            sendHandshake(OperationEnum.HANDSHAKE);
            LOGGER.debug("Handshake start sent");

            while(var3.isAlive()) {
                Thread.sleep(100L);
            }

            if(this.carName == null && this.driverName == null && this.trackName == null && this.trackConfig == null) {
                LOGGER.warn("Connection lost... retrying...");
                initHandshake();
            } else {
                Updater var4 = new Updater();
                var4.start();
                LOGGER.trace("Handshake connection confirmation preparation ...");
                this.sendHandshake(OperationEnum.SUBSCRIBE_UPDATE);
                LOGGER.trace("Handshake connection confirmation sent");

                while(var4.isAlive()) {
                    Thread.sleep(1000L);
                }

                LOGGER.trace("Handshake connection dismiss preparation ...");
                this.sendHandshake(OperationEnum.DISMISS);
                LOGGER.trace("Handshake connection dismiss sent");
                this.socket.close();
                LOGGER.warn("Socket closed");
            }


        } catch (IOException e) {
            try {
                LOGGER.info("no connection... retrying...");
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            initHandshake();
        }
    }


    private void sendHandshake(OperationEnum operationEnum) throws IOException {
        byte[] allocation = ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN).putInt(0).putInt(0).putInt(operationEnum.getValue()).array();
        DatagramPacket datagramPacket = new DatagramPacket(allocation, allocation.length, InetAddress.getByName(gameHost), 9996);
        this.socket.send(datagramPacket);
    }

    private void readHandshakeResponse(byte[] var1) {
        this.carName = ReaderUtils.readStringUTF16LE(var1, 0, 100);
        this.driverName = ReaderUtils.readStringUTF16LE(var1, 100, 100);
        this.trackName = ReaderUtils.readStringUTF16LE(var1, 208, 100);
        this.trackConfig = ReaderUtils.readStringUTF16LE(var1, 308, 100);
    }

    private void readDataUpdate(byte[] var1) {
        char var2 = ReaderUtils.readChar(var1[0]);
        int var3 = ReaderUtils.readUInt32LE(var1, 4);
        if(var2 != 97) {
            throw new RuntimeException("Bad \'identifier\' in data update: " + var2);
        } else if(var3 != 328) {
            throw new RuntimeException("Bad \'size\' in data update: " + var3);
        } else {
            assettoInfo = new AssettoCorsaSocketIntoVo();



            assettoInfo.setSpeed_Kmh(ReaderUtils.readFloat32(var1, 8));
            assettoInfo.setSpeed_Mph(ReaderUtils.readFloat32(var1, 12));
            assettoInfo.setSpeed_Ms(ReaderUtils.readFloat32(var1, 16));
            assettoInfo.setAbsEnabled(ReaderUtils.readBoolean(var1[20]));
            assettoInfo.setAbsInAction(ReaderUtils.readBoolean(var1[21]));
            assettoInfo.setTcInAction(ReaderUtils.readBoolean(var1[22]));
            assettoInfo.setTcEnabled(ReaderUtils.readBoolean(var1[23]));
            assettoInfo.setInPit(ReaderUtils.readBoolean(var1[26]));
            assettoInfo.setEngineLimiterOn(ReaderUtils.readBoolean(var1[27]));
            assettoInfo.setAccG_vertical(ReaderUtils.readFloat32(var1, 28));
            assettoInfo.setAccG_horizontal(ReaderUtils.readFloat32(var1, 32));
            assettoInfo.setAccG_frontal(ReaderUtils.readFloat32(var1, 36));
            assettoInfo.setLapTime(ReaderUtils.readUInt32LE(var1, 40));
            assettoInfo.setLastLap(ReaderUtils.readUInt32LE(var1, 44));
            assettoInfo.setBestLap(ReaderUtils.readUInt32LE(var1, 48));
            assettoInfo.setLapCount(ReaderUtils.readUInt32LE(var1, 52));
            assettoInfo.setGas(ReaderUtils.readFloat32(var1, 56));
            assettoInfo.setBrake(ReaderUtils.readFloat32(var1, 60));
            assettoInfo.setClutch(ReaderUtils.readFloat32(var1, 64));
            assettoInfo.setEngineRPM(ReaderUtils.readFloat32(var1, 68));
            assettoInfo.setSteer(ReaderUtils.readFloat32(var1, 72));
            assettoInfo.setGear(ReaderUtils.readUInt32LE(var1, 76));
            assettoInfo.setCgHeight(ReaderUtils.readFloat32(var1, 80));
            assettoInfo.setWheelAngularSpeed(ReaderUtils.read4Float32(var1, 84));
            assettoInfo.setSlipAngle(ReaderUtils.read4Float32(var1, 100));
            assettoInfo.setSlipAngle_ContactPatch(ReaderUtils.read4Float32(var1, 116));
            assettoInfo.setSlipRatio(ReaderUtils.read4Float32(var1, 132));
            assettoInfo.setTyreSlip(ReaderUtils.read4Float32(var1, 148));
            assettoInfo.setNdSlip(ReaderUtils.read4Float32(var1, 164));
            assettoInfo.setLoad(ReaderUtils.read4Float32(var1, 180));
            assettoInfo.setDy(ReaderUtils.read4Float32(var1, 196));
            assettoInfo.setMz(ReaderUtils.read4Float32(var1, 212));
            assettoInfo.setTyreDirtyLevel(ReaderUtils.read4Float32(var1, 228));
            assettoInfo.setCamberRAD(ReaderUtils.read4Float32(var1, 244));
            assettoInfo.setTyreRadius(ReaderUtils.read4Float32(var1, 260));
            assettoInfo.setTyreLoadedRadius(ReaderUtils.read4Float32(var1, 276));
            assettoInfo.setSuspensionHeight(ReaderUtils.read4Float32(var1, 292));
            assettoInfo.setCarPositionNormalized(ReaderUtils.readFloat32(var1, 308));
            assettoInfo.setCarSlope(ReaderUtils.readFloat32(var1, 312));
            assettoInfo.setCarCoordinates(ReaderUtils.read3Float32(var1, 316));

        }
    }

    public boolean isConnected() {
        return connected;
    }

    public AssettoCorsaSocketIntoVo getData() {
        return assettoInfo;
    }

    private class HandshakeHandler extends Thread {
        private HandshakeHandler() {
        }

        public void run() {
            try {
                //assetto corsa closes the connection if nothing happened!

                LOGGER.debug("Waiting for handshake response ...");
                ACConnector.this.socket.receive(ACConnector.this.packet);
                LOGGER.debug("Handshake response received");
                ACConnector.this.readHandshakeResponse(ACConnector.this.buffer);
                connected = true;
            } catch (SocketTimeoutException timeout) {
                LOGGER.error("Handshake response timeout");
            } catch (IOException ioExp) {
                LOGGER.error("error on handshake", ioExp);
            }

        }
    }


    private class Updater extends Thread {
        private Updater() {
        }

        public void run() {
            while(true) {
                try {
                    if(ACConnector.this.updating) {
                        LOGGER.debug("Waiting for data update ...");
                        ACConnector.this.socket.receive(ACConnector.this.packet);
                        LOGGER.debug("Data update received");
                        ACConnector.this.readDataUpdate(ACConnector.this.buffer);
                        continue;
                    }
                } catch (SocketTimeoutException sout) {
                    LOGGER.error("Data update timeout", sout);
                    connected = false;
                } catch (IOException io) {
                    LOGGER.error("Error receiving data", io);
                    connected = false;
                }
                return;
            }
        }
    }
}
