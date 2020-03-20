package systemFile;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import circularOrbit.AtomStructure;
import myException.FileChooseException;

class AtomStructureFileTest {

    /**
     * ����Ԫ����ֻ��һ��Сд��ĸ+����������Ϣ��
     */
    @Test void testAtomLabelCase() throws IOException {
        try {
            AtomStructure atomStructure = new AtomStructure();
            File file = new File("src/TestFile/AtomFile/LabelTest1.txt");
            atomStructure.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�!");
        } catch (Exception e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

    /**
     * ����Ԫ������������ĸ+����������Ϣ��
     */
    @Test void testAtomLabelLength() throws IOException {
        try {
            AtomStructure atomStructure = new AtomStructure();
            File file = new File("src/TestFile/AtomFile/LabelTest2.txt");
            atomStructure.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�!");
        } catch (Exception e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

    /**
     * ���Թ����Ŀ��ʵ�ʸ����Ĺ������һ��+����������Ϣ��
     */
    @Test void testAtomTrackNum() throws IOException {
        try {
            AtomStructure atomStructure = new AtomStructure();
            File file = new File("src/TestFile/AtomFile/TrackTest.txt");
            atomStructure.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�!");
        } catch (Exception e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

    /**
     * ���Զ�ȡ�ļ�ȱ����Ϣ��
     */
    @Test void testLackOfLine() throws IOException {
        try {
            AtomStructure atomStructure = new AtomStructure();
            File file = new File("src/TestFile/AtomFile/LackOfLineTest.txt");
            atomStructure.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�!");
        } catch (Exception e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

    /**
     * �����ļ�������˳������
     */
    @Test void testTrackOrder() throws IOException {
        try {
            AtomStructure atomStructure = new AtomStructure();
            File file = new File("src/TestFile/AtomFile/TrackNumOrderTest.txt");
            atomStructure.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�!");
        } catch (Exception e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

}
