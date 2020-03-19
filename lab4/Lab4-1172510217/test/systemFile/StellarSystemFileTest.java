package systemFile;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import circularOrbit.StellarSystem;
import myException.FileChooseException;

class StellarSystemFileTest {

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
     * ���Դ��ڱ�ǩ��ͬ������������ͬ��Ԫ��
     */
    @Test void labelSameTest() throws IOException {
        File file = new File("src/TestFile/StellarSystemFile/LabelSameTest.txt");
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
     * ����һ��������ж������
     */
    @Test void mutiTrackObjectTest() throws IOException {
        File file = new File("src/TestFile/StellarSystemFile/MutiTrackObjectTest.txt");
        StellarSystem stellarSystem = new StellarSystem();
        try {
            stellarSystem.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�");
        } catch (FileChooseException e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }
    
    /**
     * ���Ե�i�������i-1����֮��С�ڵ����������ǰ뾶֮��
     */
    @Test void trackRadiusTest() throws IOException {
        File file = new File("src/TestFile/StellarSystemFile/TrackRaidusTest.txt");
        StellarSystem stellarSystem = new StellarSystem();
        try {
            stellarSystem.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�");
        } catch (FileChooseException e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

}
