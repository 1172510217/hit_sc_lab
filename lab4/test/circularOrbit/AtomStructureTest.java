package circularOrbit;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import myException.FileChooseException;
import physicalObject.PhysicalObject;

class AtomStructureTest {

    /**
     * testing strategy : ����ԾǨǰ��ԾǨ��������+�������
     */
    @Test void testTransit() throws Exception {
        AtomStructure atomStructure = new AtomStructure();
        atomStructure.readFileAndCreateSystem(new File("src/Spring2019_HITCS_SC_Lab3-master/AtomicStructure.txt"));
        int num1 = atomStructure.getTrackObjectsNumber(1);
        int num2 = atomStructure.getTrackObjectsNumber(2);
        atomStructure.transit(atomStructure.getTrack(1).getTrackObjects().get(0), atomStructure.getTrack(2));
        assertTrue(atomStructure.getTrackObjectsNumber(1) == (num1 - 1)
                && atomStructure.getTrackObjectsNumber(2) == num2 + 1);
        assertFalse(
                atomStructure.transit(atomStructure.getTrack(1).getTrackObjects().get(0), atomStructure.getTrack(1)));
    }

    /**
     * testing strategy : ���Բ���ĳ����
     */
    @Test void testDeletePhysicalObject() throws IOException, FileChooseException {
        AtomStructure atomStructure = new AtomStructure();
        atomStructure.readFileAndCreateSystem(new File("src/Spring2019_HITCS_SC_Lab3-master/AtomicStructure.txt"));
        PhysicalObject physicalObject = new PhysicalObject();
        assertFalse(atomStructure.deletePhysicalObject(physicalObject));        
    }

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
