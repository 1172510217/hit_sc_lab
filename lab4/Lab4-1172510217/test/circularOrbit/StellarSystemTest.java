package circularOrbit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

import centralObject.Stellar;
import myException.FileChooseException;
import physicalObject.StellarSystemObject;
import track.Track;

class StellarSystemTest {

    /**
     * ����label����
     */
    @Test void lableTest() throws IOException {
        File file = new File("src/TestFile/StellarSystemFile/LabelTest.txt");
        StellarSystem stellarSystem = new StellarSystem();
        try {
            stellarSystem.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�");
        } catch (FileChooseException e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

    /**
     * ��ѧ��������ʾ����
     */
    @Test void e_notationTest() throws IOException {
        File file = new File("src/TestFile/StellarSystemFile/E_NotationTest.txt");
        StellarSystem stellarSystem = new StellarSystem();
        try {
            stellarSystem.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�");
        } catch (FileChooseException e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

    /**
     * ���Թ�ת�������
     */
    @Test void revolutionDirectionTest() throws IOException {
        File file = new File("src/TestFile/StellarSystemFile/RevolutionDirectionTest.txt");
        StellarSystem stellarSystem = new StellarSystem();
        try {
            stellarSystem.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�");
        } catch (FileChooseException e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

    /**
     * ���Գ�ʼ�Ƕȴ���
     */
    @Test void angelTest() throws IOException {
        File file = new File("src/TestFile/StellarSystemFile/AngelTest.txt");
        StellarSystem stellarSystem = new StellarSystem();
        try {
            stellarSystem.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�");
        } catch (FileChooseException e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }
    
    /**
     * �������ĺ���ȱʧ
     */
    @Test void noStellarTest() throws IOException {
        File file = new File("src/TestFile/StellarSystemFile/NoStellarTest.txt");
        StellarSystem stellarSystem = new StellarSystem();
        try {
            stellarSystem.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�");
        } catch (FileChooseException e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

    /**
     * testing strategy : ���������� + �����������Ŀ�쳣 + ���ڹ���뾶֮��С�����������ǰ뾶֮��+��ǩ��ͬ
     */
    @Test void testCheckRep() {
        StellarSystem stellarSystem = new StellarSystem();
        try {
            stellarSystem.checkRep();
        } catch (FileChooseException e) {
            assertTrue(true);
        }
        Stellar stellar = new Stellar();
        stellar.setName("@Author ZJR");
        stellar.setMess(100);
        stellar.setRadius(1);
        stellarSystem.addCentralPoint(stellar);
        try {
            stellarSystem.checkRep();
            assertTrue(true);
        } catch (FileChooseException e) {
            assertTrue(false);
        }
        Track<StellarSystemObject> track = new Track<>(7);
        stellarSystem.addTrack(track);
        try {
            stellarSystem.checkRep();
        } catch (FileChooseException e) {
            assertTrue(true);
        }
        StellarSystemObject planet = new StellarSystemObject("A", "B", "C", 1, 2, "CW", 0);
        planet.setTrackRadius(7);
        track.add(planet);
        try {
            assertTrue(true);
            stellarSystem.checkRep();
        } catch (FileChooseException e) {
            assertTrue(false);
        }
        Track<StellarSystemObject> track2 = new Track<>(10);
        stellarSystem.addTrack(track2);
        StellarSystemObject planet2 = new StellarSystemObject("A", "B", "C", 1, 2, "CW", 0);
        planet2.setTrackRadius(10);
        track2.add(planet2);
        try {
            stellarSystem.checkRep();
        } catch (FileChooseException e) {
            assertTrue(true);
        }
        Track<StellarSystemObject> track3 = new Track<>(11);
        stellarSystem.addTrack(track3);
        StellarSystemObject planet3 = new StellarSystemObject("D", "B", "C", 4, 2, "CW", 0);
        planet3.setTrackRadius(11);
        track3.add(planet3);
        stellarSystem.deleteTrack(track2);
        try {
            stellarSystem.checkRep();
        } catch (FileChooseException e) {
            assertTrue(true);
        }
    }

    /**
     * testing strategy : ��ת����ΪCW����ת����ΪCCW
     */
    @Test void testCalculatePosition() {
        StellarSystem stellarSystem = new StellarSystem();
        Stellar stellar = new Stellar();
        Track<StellarSystemObject> track = new Track<>(4);
        StellarSystemObject planet = new StellarSystemObject("A", "B", "C", 1, 2, "CW", 0);
        stellarSystem.addCentralPoint(stellar);
        stellarSystem.addTrack(track);
        track.add(planet);
        planet.setTrackRadius(4);
        Calendar targetTime = Calendar.getInstance();
        stellarSystem.setReadTime(targetTime);
        Calendar newTime = Calendar.getInstance();
        newTime.setTimeInMillis(newTime.getTimeInMillis() + 1000);
        // ��ת����ΪCW
        assertEquals(0, stellarSystem.calculatePosition(planet, targetTime));
        // ��ת����ΪCCW
        StellarSystemObject planet2 = new StellarSystemObject("A", "B", "C", 1, 2, "CCW", 0);
        assertEquals(0, stellarSystem.calculatePosition(planet2, targetTime));
    }

    /**
     * testing strategy : ����������������ǿ�
     */
    @Test void testGetPhysicalDistance() {
        StellarSystem stellarSystem = new StellarSystem();
        Stellar stellar = new Stellar();
        Track<StellarSystemObject> track1 = new Track<>(2);
        Track<StellarSystemObject> track2 = new Track<>(4);
        StellarSystemObject planet1 = new StellarSystemObject("A", "B", "C", 1, 2, "CW", 0);
        StellarSystemObject planet2 = new StellarSystemObject("A1", "B", "C", 1, 2, "CW", 0);
        stellarSystem.addCentralPoint(stellar);
        stellarSystem.addTrack(track1);
        track1.add(planet1);
        track2.add(planet2);
        planet1.setTrackRadius(2);
        planet2.setTrackRadius(4);
        assertEquals(2, stellarSystem.getPhysicalDistance(planet1, planet2));
    }

    /**
     * testing strategy : �����������
     */
    @Test void testGetPhysicalDistanceStar() {
        StellarSystem stellarSystem = new StellarSystem();
        Stellar stellar = new Stellar();
        Track<StellarSystemObject> track = new Track<>(2);
        StellarSystemObject planet = new StellarSystemObject("A", "B", "C", 1, 2, "CW", 0);
        stellarSystem.addCentralPoint(stellar);
        stellarSystem.addTrack(track);
        track.add(planet);
        planet.setTrackRadius(2);
        assertEquals(2, stellarSystem.getPhysicalDistanceStar(planet));
    }

}
