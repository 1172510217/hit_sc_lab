package recordiotime;

import circularorbit.StellarSystem;
import iostrategy.BufferedIoStrategy;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import myexception.FileChooseException;

public class MemoryDump {

  /**
   * �˺������ڷ����ȡ������곬���ļ�֮���ڴ�ռ�����.
   * 
   * @param  args                       ����
   * @throws IOException                IO�쳣
   * @throws FileChooseException        �ļ�ѡȡ�쳣
   * @throws CloneNotSupportedException clone �쳣
   */
  public static void main(String[] args)
      throws IOException, FileChooseException, CloneNotSupportedException {
    StellarSystem stellarSystem = new StellarSystem();
    stellarSystem.setReadFile(
        new File("src/Spring2019_HITCS_SC_Lab5-master/StellarSystem.txt"));
    stellarSystem.readFileAndCreateSystem(new BufferedIoStrategy());
    // �ó���ֹͣ���������ڻ�ȡ��ǰ�ڴ�ռ�����
    System.out.println("���ڿ��Ե����ڴ�ռ�����");
    Scanner scanner = new Scanner(System.in);
    String string = scanner.next();
    scanner.close();
    System.out.println(string);
  }

}
