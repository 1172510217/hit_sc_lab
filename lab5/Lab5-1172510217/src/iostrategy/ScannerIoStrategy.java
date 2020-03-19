package iostrategy;

import centralobject.Stellar;
import circularorbit.AtomStructure;
import circularorbit.SocialNetworkCircle;
import circularorbit.StellarSystem;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import myexception.CentralObjectException;
import myexception.FileChooseException;
import myexception.FileGrammerException;
import myexception.LabelSameException;
import physicalobject.Friend;
import physicalobject.StellarSystemObject;
import track.Track;

public class ScannerIoStrategy implements IoStrategy {

  /**
   * read file in the scanner method.
   */
  public void readFileAndCreateSystem(StellarSystem stellarSystem, File file)
      throws IOException, FileChooseException {
    try {
      String lineString = "";
      Pattern pattern1 = Pattern.compile(
          "Stellar ::= <[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*>");
      Pattern pattern2 =
          Pattern.compile("Planet ::= <[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*"
              + ",[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*"
              + ",[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*>");
      Matcher matcher;
      int lineCount = 0;
      int count = 0;
      Scanner scanner = new Scanner(file);
      long startTime = System.currentTimeMillis();
      while (scanner.hasNextLine()) {
        lineString = scanner.nextLine();
        lineCount++;
        if ((matcher = pattern1.matcher(lineString)).find()) {
          count++;
          String group1 = matcher.group(1);
          String group2 = matcher.group(2);
          String group3 = matcher.group(3);
          if (!Pattern.matches("[0-9a-zA-Z]+", group1)) {
            scanner.close();
            throw new FileGrammerException("�������ִ����кţ�" + lineCount, 10);
          }
          if (!Pattern.matches(
              "[0-9]{1,4}([.][0-9]+)?|[1-9]([.][0-9]+)?([e][+-]?[0-9]+)?",
              group2)) {
            scanner.close();
            throw new FileGrammerException("���ǰ뾶�����кţ�" + lineCount, 10);
          }
          if (!Pattern.matches(
              "[0-9]{1,4}([.][0-9]+)?|[1-9]([.][0-9]+)?([e][+-]?[0-9]+)?",
              group3)) {
            scanner.close();
            throw new FileGrammerException("�������������кţ�" + lineCount, 10);
          }
          Stellar stellar = new Stellar();
          stellar.setName(group1);
          stellar.setRadius(Double.parseDouble(group2));
          stellar.setMess(Double.parseDouble(group3));
          stellarSystem.addCentralPoint(stellar);
        } else if ((matcher = pattern2.matcher(lineString)).find()) {
          String group1 = matcher.group(1);
          String group2 = matcher.group(2);
          String group3 = matcher.group(3);
          if (!Pattern.matches("[0-9a-zA-Z]+", group1)) {
            scanner.close();
            throw new FileGrammerException("�����������кţ�" + lineCount, 10);
          }
          if (!Pattern.matches("[0-9a-zA-Z]+", group2)) {
            scanner.close();
            throw new FileGrammerException("����״̬�����кţ�" + lineCount, 10);
          }
          if (!Pattern.matches("[0-9a-zA-Z]+", group3)) {
            scanner.close();
            throw new FileGrammerException("������ɫ�����кţ�" + lineCount, 10);
          }
          String group4 = matcher.group(4);
          String group5 = matcher.group(5);
          String group6 = matcher.group(6);

          if (!Pattern.matches(
              "[0-9]{1,4}([.][0-9]+)?|[1-9]([.][0-9]+)?([e][+-]?[0-9]+)?",
              group4)) {
            scanner.close();
            throw new FileGrammerException("���ǰ뾶�����кţ�" + lineCount + group4,
                10);
          }
          if (!Pattern.matches(
              "[0-9]{1,4}([.][0-9]+)?|[1-9]([.][0-9]+)?([e][+-]?[0-9]+)?",
              group5)) {
            scanner.close();
            throw new FileGrammerException("����뾶�����кţ�" + lineCount, 10);
          }
          if (!Pattern.matches(
              "[0-9]{1,4}([.][0-9]+)?|[1-9]([.][0-9]+)?([e][+-]?[0-9]+)?",
              group6)) {
            scanner.close();
            throw new FileGrammerException("��ת�ٶȴ����кţ�" + lineCount, 10);
          }
          String group7 = matcher.group(7);
          String group8 = matcher.group(8);
          if (!Pattern.matches("(CW)|(CCW)", group7)) {
            scanner.close();
            throw new FileGrammerException("��ת��������кţ�" + lineCount, 10);
          }
          if (!Pattern.matches("[0-9]{1,3}([.][0-9]+)?", group8)) {
            scanner.close();
            throw new FileGrammerException("��ʼ�Ƕȴ����кţ�" + lineCount, 10);
          }
          double angle = Double.parseDouble(group8);
          if (angle < 0 || angle >= 360) {
            scanner.close();
            throw new FileGrammerException("��ʼ�Ƕȷ�Χ�����кţ�" + lineCount, 10);
          }
          StellarSystemObject planet = new StellarSystemObject(group1, group2,
              group3, Double.parseDouble(group4), Double.parseDouble(group6),
              group7, angle);
          planet.setTrackRadius(Double.parseDouble(group5));
          Track<StellarSystemObject> track =
              stellarSystem.getTrackByRadius(Double.parseDouble(group5));
          if (track == null) {
            track = new Track<>(Double.parseDouble(matcher.group(5)));
          }
          track.add(planet);
          stellarSystem.addTrack(track);
        } else {
          if (!lineString.equals("")) {
            scanner.close();
            throw new FileGrammerException("������Ϣ�У�������" + lineCount, 10);
          }
        }
      }
      long endTime = System.currentTimeMillis();
      System.out.println("����ϵͳScannerIO���ļ�:" + (endTime - startTime) + "ms");
      if (count != 1) {
        scanner.close();
        throw new CentralObjectException("����ȱʧ�����");
      }
      scanner.close();
      stellarSystem.sortTrack();
      stellarSystem.checkRep();
    } catch (CentralObjectException e) {
      throw new FileChooseException("�ļ����ĺ��Ǵ�����Ҫ����ѡ���ļ���" + e.getMessage());
    } catch (FileGrammerException e) {
      throw new FileChooseException("�ļ��﷨����������Ҫ����ѡ���ļ���" + e.getMessage());
    }
  }

  /**
   * atomStructure io strategy.
   */
  public void readFileAndCreateSystem(AtomStructure atomStructure, File file)
      throws IOException, FileChooseException {
    try {
      throw new NoSuchMethodException("ԭ��ϵͳ����֧�ֵĲ���");
    } catch (NoSuchMethodException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * socialNetwork read file.
   */
  public void readFileAndCreateSystem(SocialNetworkCircle socialNetworkCircle,
      File file) throws IOException, FileChooseException {
    try {
      Pattern pattern1 = Pattern.compile(
          "CentralUser ::= <[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*>");
      Pattern pattern2 = Pattern
          .compile("Friend ::= <[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*>");
      Pattern pattern3 = Pattern.compile(
          "SocialTie ::= <[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*,[ ]*(\\S+)[ ]*>");
      String lineString = "";
      int count = 0;
      Matcher matcher;
      int lineCount = 0;
      long startTime = System.currentTimeMillis();
      Scanner scanner = new Scanner(file);
      while (scanner.hasNext()) {
        lineString = scanner.nextLine();
        lineCount++;// �м����Լ�һ
        if ((matcher = pattern1.matcher(lineString)).find()) {
          count++;
          if (!Pattern.matches("[A-Za-z0-9]+", matcher.group(1))) {
            scanner.close();
            throw new FileGrammerException("�����û����Ƿ���������" + lineCount, 30);
          }
          if (!Pattern.matches("\\d+", matcher.group(2))) {
            scanner.close();
            throw new FileGrammerException("����Ƿ���������" + lineCount, 30);
          }
          if (!Pattern.matches("M|F", matcher.group(3))) {
            scanner.close();
            throw new FileGrammerException("�Ա���ض������ַ���������" + lineCount, 30);
          }
          Friend centralUser = new Friend();
          centralUser.setAge(Integer.parseInt(matcher.group(2)));
          centralUser.setFriendName(matcher.group(1));
          centralUser.setSex(matcher.group(3));
          centralUser.setTrackRadius(0);
          socialNetworkCircle.addCentralPoint(centralUser);
          socialNetworkCircle.addFriend(centralUser);
        } else if ((matcher = pattern2.matcher(lineString)).find()) {
          if (!Pattern.matches("[A-Za-z0-9]+", matcher.group(1))) {
            scanner.close();
            throw new FileGrammerException("���������Ƿ���������" + lineCount, 30);
          }
          if (!Pattern.matches("\\d+", matcher.group(2))) {
            scanner.close();
            throw new FileGrammerException("����Ƿ���������" + lineCount, 30);
          }
          if (!Pattern.matches("M|F", matcher.group(3))) {
            scanner.close();
            throw new FileGrammerException("�Ա���ض������ַ���������" + lineCount, 30);
          }
          Friend friend = socialNetworkCircle.getFriendByName(matcher.group(1));
          if (friend == null) {
            friend = new Friend();
            friend.setFriendName(matcher.group(1));
            friend.setAge(Integer.parseInt(matcher.group(2)));
            friend.setSex(matcher.group(3));
            socialNetworkCircle.addFriend(friend);
          } else {
            if (!friend.getSex().equals("")) {
              scanner.close();
              throw new LabelSameException("�����������Ѵ��ڣ�������" + lineCount);
            } else {
              friend.setAge(Integer.parseInt(matcher.group(2)));
              friend.setSex(matcher.group(3));
            }
          }
        } else if ((matcher = pattern3.matcher(lineString)).find()) {
          String f1String = matcher.group(1);
          String f2String = matcher.group(2);
          if (!Pattern.matches("[A-Za-z0-9]+", f1String)) {
            scanner.close();
            throw new FileGrammerException("���������Ƿ���������" + lineCount, 30);
          }
          if (!Pattern.matches("[A-Za-z0-9]+", f2String)) {
            scanner.close();
            throw new FileGrammerException("���������Ƿ���������" + lineCount, 30);
          }
          if (!Pattern.matches("([0][.][0-9]{0,3})|([1]([.][0]{0,3})?)",
              matcher.group(3))) {
            scanner.close();
            throw new FileGrammerException("���ܶȲ�������������" + lineCount, 30);
          }
          if (f1String.equals(f2String)) {
            scanner.close();
            throw new LabelSameException("����ӹ�ϵ������Ϊͬһ���ˣ�������" + lineCount);
          }
          double intimacy = Double.parseDouble(matcher.group(3));
          if (intimacy == 0) {
            scanner.close();
            throw new FileGrammerException("���ܶȲ�������������" + lineCount, 30);
          }
          Friend friend1 = socialNetworkCircle.getFriendByName(f1String);
          Friend friend2 = socialNetworkCircle.getFriendByName(f2String);
          if (friend1 == null) {
            friend1 = new Friend();
            friend1.setFriendName(f1String);
            socialNetworkCircle.addFriend(friend1);
          }
          if (friend2 == null) {
            friend2 = new Friend();
            friend2.setFriendName(f2String);
            socialNetworkCircle.addFriend(friend2);
          }
          if (friend1.getSocialTie(friend2) != 0
              && friend1.getSocialTie(friend2) != intimacy) {
            scanner.close();
            throw new LabelSameException(
                "����ӵ������Ѵ������ܶ�����˴����ܶȲ�ͬ��������" + lineCount);
          }
          friend1.addSocialTie(friend2, intimacy);
          friend2.addSocialTie(friend1, intimacy);
        } else {
          if (!lineString.equals("")) {
            scanner.close();
            throw new FileGrammerException("������Ϣ�У�������" + lineCount, 31);
          }
        }
      }
      long endTime = System.currentTimeMillis();
      System.out.println("�罻����ScannerIO���ļ�:" + (endTime - startTime) + "ms");
      if (count != 1) {
        scanner.close();
        throw new CentralObjectException("�����û�ȱʧ����࣬������" + lineCount);
      }
      socialNetworkCircle.getAllDistance();
      scanner.close();
      socialNetworkCircle.sortTrack();
      socialNetworkCircle.checkRep();
    } catch (FileGrammerException e) {
      throw new FileChooseException("�ļ��﷨����������Ҫ����ѡ���ļ���" + e.getMessage());
    } catch (LabelSameException e) {
      throw new FileChooseException("���ڱ�ǩ��ͬԪ�أ���Ҫ����ѡ���ļ���" + e.getMessage());
    } catch (CentralObjectException e) {
      throw new FileChooseException("�����û�������Ҫ����ѡ���ļ���" + e.getMessage());
    }
  }

  /**
   * printer writer strategy, stellarSystem.
   */
  public void saveSystemInfoInFile(StellarSystem stellarSystem, String filePath)
      throws IOException {
    StringBuilder stringBuilder = new StringBuilder("Stellar ::= <");
    stringBuilder.append(stellarSystem.getCentralPoint().getName()).append(",")
        .append(stellarSystem
            .enotationTransform(stellarSystem.getCentralPoint().getRadius()))
        .append(",")
        .append(stellarSystem
            .enotationTransform(stellarSystem.getCentralPoint().getMess()))
        .append(">\n");// ������
    // ������д������
    int size = stellarSystem.getTracksNumber();
    StellarSystemObject planet = null;
    for (int i = 0; i < size; i++) {
      planet = stellarSystem.getPlanetByNum(i + 1);
      stringBuilder.append("Planet ::= <").append(planet.getPlanetName())
          .append(",").append(planet.getPlanetState()).append(",")
          .append(planet.getPlanetColor()).append(",")
          .append(stellarSystem.enotationTransform(planet.getPlanetRadius()))
          .append(",")
          .append(stellarSystem.enotationTransform(planet.getTrackRadius()))
          .append(",")
          .append(stellarSystem.enotationTransform(planet.getRevolutionSpeed()))
          .append(",").append(planet.getRevolutionDiretion()).append(",")
          .append(planet.getAngle()).append(">\n");
    }
    long startTime = System.currentTimeMillis();
    PrintWriter writer = new PrintWriter(new File(filePath));
    writer.write(stringBuilder.toString());
    long endTime = System.currentTimeMillis();
    System.out.println("����ϵͳPrintWriterIOд�ļ�:" + (endTime - startTime) + "ms");
    writer.close();
  }

  /**
   * atomStruture.
   */
  public void saveSystemInfoInFile(AtomStructure atomStructure, String filePath)
      throws IOException {
    try {
      throw new NoSuchMethodException("ԭ��ϵͳ����֧�ֵĲ���");
    } catch (NoSuchMethodException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * socialNetwork file writer in printWriter.
   */
  public void saveSystemInfoInFile(SocialNetworkCircle socialNetworkCircle,
      String filePath) throws IOException {
    StringBuilder stringBuilder = new StringBuilder("CentralUser ::= <");
    // �����û���
    stringBuilder.append(socialNetworkCircle.getCentralPoint().getFriendName())
        .append(",").append(socialNetworkCircle.getCentralPoint().getAge())
        .append(",").append(socialNetworkCircle.getCentralPoint().getSex())
        .append(">");
    // ���е������û���
    for (Friend friend : socialNetworkCircle.getAllFriends()) {
      if (!friend.equals(socialNetworkCircle.getCentralPoint())) {
        stringBuilder.append("\nFriend ::= <").append(friend.getFriendName())
            .append(",").append(friend.getAge()).append(",")
            .append(friend.getSex()).append(">");
      }
    }
    // �������ܶȹ�ϵ��
    int size = socialNetworkCircle.getAllFriends().size();
    for (int i = 0; i < size; i++) {
      Friend friend = socialNetworkCircle.getAllFriends().get(i);
      for (int j = i; j < size; j++) {
        double intimacy =
            friend.getSocialTie(socialNetworkCircle.getAllFriends().get(j));
        if (intimacy != 0) {
          stringBuilder.append("\nSocialTie ::= <")
              .append(friend.getFriendName()).append(",")
              .append(
                  socialNetworkCircle.getAllFriends().get(j).getFriendName())
              .append(",").append(intimacy).append(">");
        }
      }
    }
    long startTime = System.currentTimeMillis();
    PrintWriter writer = new PrintWriter(new File(filePath));
    writer.write(stringBuilder.toString());
    long endTime = System.currentTimeMillis();
    System.out.println("�罻����PrintWriterIOд�ļ�:" + (endTime - startTime) + "ms");
    writer.close();
  }

}
