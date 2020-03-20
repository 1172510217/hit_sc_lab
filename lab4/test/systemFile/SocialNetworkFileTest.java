package systemFile;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import circularOrbit.SocialNetworkCircle;
import myException.FileChooseException;

class SocialNetworkFileTest {

    /**
     * ���Ա�ǩ���Ϸ�
     */
    @Test void testLabel() throws IOException {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        File file = new File("src/TestFile/SocialNetworkFile/LabelTest.txt");
        try {
            socialNetworkCircle.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�");
        } catch (Exception e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

    /**
     * �����Ա𲻺Ϸ�
     */
    @Test void testSex() throws IOException {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        File file = new File("src/TestFile/SocialNetworkFile/SexTest.txt");
        try {
            socialNetworkCircle.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�");
        } catch (Exception e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

    /**
     * �������ܶȲ��Ϸ���Ϊ0
     */
    @Test void testIntimacyZero() throws IOException {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        File file = new File("src/TestFile/SocialNetworkFile/IntimacyTest1.txt");
        try {
            socialNetworkCircle.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�");
        } catch (Exception e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

    /**
     * �������ܶȲ��Ϸ���λ��������
     */
    @Test void testIntimacyDigit() throws IOException {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        File file = new File("src/TestFile/SocialNetworkFile/IntimacyTest2.txt");
        try {
            socialNetworkCircle.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�");
        } catch (Exception e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

    /**
     * �������䲻�Ϸ�����Ϊ����
     */
    @Test void testAge() throws IOException {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        File file = new File("src/TestFile/SocialNetworkFile/AgeTest.txt");
        try {
            socialNetworkCircle.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�");
        } catch (Exception e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

    /**
     * ���������û�ȱʧ
     */
    @Test void testNoCentralUser() throws IOException {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        File file = new File("src/TestFile/SocialNetworkFile/CentralUserTest.txt");
        try {
            socialNetworkCircle.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�");
        } catch (Exception e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

    /**
     * �������û�ͬ��
     */
    @Test void testLabelSame() throws IOException {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        File file = new File("src/TestFile/SocialNetworkFile/LabelSameTest.txt");
        try {
            socialNetworkCircle.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�");
        } catch (Exception e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

    /**
     * ����������ϵ����������δ������Ͳ��������ܶȹ���
     */
    @Test void testDependency() throws IOException {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        File file = new File("src/TestFile/SocialNetworkFile/DependencyTest.txt");
        try {
            socialNetworkCircle.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�");
        } catch (Exception e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }

    /**
     * ���Զ���������Ϣ��
     */
    @Test void testUselessLine() throws IOException {
        SocialNetworkCircle socialNetworkCircle = new SocialNetworkCircle();
        File file = new File("src/TestFile/SocialNetworkFile/UselessLineTest.txt");
        try {
            socialNetworkCircle.readFileAndCreateSystem(file);
            assertTrue(false, "�˴����ɴ�");
        } catch (Exception e) {
            assertEquals(e.getClass(), new FileChooseException().getClass());
        }
    }
}
