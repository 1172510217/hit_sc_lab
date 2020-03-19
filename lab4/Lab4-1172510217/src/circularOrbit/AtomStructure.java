package circularOrbit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import applications.App;
import centralObject.CentralObject;
import myException.DependencyException;
import myException.FileChooseException;
import myException.FileGrammerException;
import physicalObject.PhysicalObject;
import track.Track;

public class AtomStructure extends ConcreteCircularOrbit<CentralObject, PhysicalObject> {

    private static final Logger logger = Logger.getLogger(App.class.getSimpleName());

    /**
     * transit an object to a track
     * 
     * @param physicalObject an object
     * @param track          the new track
     * @return true if the new track is not the same as the older one; else false
     */
    public boolean transit(PhysicalObject physicalObject, Track<PhysicalObject> track) {
        assert physicalObject != null && track != null : logIn("��������null");
        assert getAlltracks().contains(track) : logIn("��������ϵͳ����track");
        if (physicalObject.getTrackRadius() == track.getRadius()) {
            assert track.contains(physicalObject)
                    && physicalObject.getTrackRadius() == track.getRadius() : logIn("ԾǨʧ��");
            return false;
        }
        if (deletePhysicalObject(physicalObject)) {
            physicalObject.setTrackRadius(track.getRadius());
            track.add(physicalObject);
            assert track.contains(physicalObject)
                    && physicalObject.getTrackRadius() == track.getRadius() : logIn("ԾǨʧ��");
            return true;
        } else {
            assert track.contains(physicalObject)
                    && physicalObject.getTrackRadius() == track.getRadius() : logIn("ԾǨʧ��");
            return false;
        }
    }

    /**
     * add an object to the system
     * 
     * @param physicalObject an object
     * @return true if the object is not in the system; else false
     */
    public boolean addPhysicalObject(PhysicalObject physicalObject) {
        assert physicalObject != null : logIn("��������null");
        Track<PhysicalObject> track = getTrackByRadius(physicalObject.getTrackRadius());
        if (track == null) {
            track = new Track<>(physicalObject.getTrackRadius());
            track.add(physicalObject);
            this.addTrack(track);
            return true;
        } else {
            return track.add(physicalObject);
        }
    }

    /**
     * delete an object
     * 
     * @param physicalObject an object
     * @return true if the object is in the system; else false
     */
    public boolean deletePhysicalObject(PhysicalObject physicalObject) {
        assert physicalObject != null : logIn("��������null");
        Track<PhysicalObject> track = getTrackByRadius(physicalObject.getTrackRadius());
        if (track != null) {
            return track.remove(physicalObject);
        } else {
            return false;
        }
    }

    /**
     * read the files and create the system of AtomStructure
     * 
     * @param file ����ȡ���ļ�
     * @throws IOException
     * @throws FileChooseException
     */
    public void readFileAndCreateSystem(File file) throws IOException, FileChooseException {
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bfReader = new BufferedReader(reader);
            Pattern pattern1 = Pattern.compile("ElementName ::= ((?:[a-z]|[A-Z])+)");
            Pattern pattern11 = Pattern.compile("[A-Z][a-z]?");
            Pattern pattern2 = Pattern.compile("NumberOfTracks ::= ([-]?[0-9]?)");
            Pattern pattern3 = Pattern.compile("NumberOfElectron ::= [ ]*([1-9][/]\\d+(?:[ ]*[;][ ]*[1-9][/]\\d+)*)");
            String lineString = new String();
            int trackNum = 0, trackAct = 0;
            Matcher matcher;
            int count = 0;// ͳ����Ч����
            while ((lineString = bfReader.readLine()) != null) {
                if ((matcher = pattern1.matcher(lineString)).find()) {
                    count++;
                    String eleName = matcher.group(1);
                    if (eleName.length() > 2) {
                        bfReader.close();
                        throw new FileGrammerException("ElementName length should not be" + eleName.length(), 20);
                    } else if (eleName.length() == 2) {
                        if (!pattern11.matcher(eleName).find()) {
                            bfReader.close();
                            throw new FileGrammerException("�˴���Ԫ����Ӧ���Ǵ�д��ĸ+Сд��ĸ", 21, eleName);
                        }
                    } else if (eleName.length() == 1) {
                        if (!pattern11.matcher(eleName).find()) {
                            bfReader.close();
                            throw new FileGrammerException("Ԫ����Ӧ���Ǵ�д��ĸ", 22, eleName);
                        }
                    }
                    CentralObject element = new CentralObject();
                    element.setName(eleName);
                    addCentralPoint(element);
                } else if ((matcher = pattern2.matcher(lineString)).find()) {
                    count++;
                    trackNum = Integer.parseInt(matcher.group(1));
                    if (trackNum <= 0) {
                        bfReader.close();
                        throw new FileGrammerException("���������" + trackNum, 23);
                    }
                } else if ((matcher = pattern3.matcher(lineString)).find()) {
                    count++;
                    lineString = matcher.group(1);
                    String[] strings = lineString.split(";");
                    trackAct = strings.length;
                    for (int i = 0; i < strings.length; i++) {
                        matcher = Pattern.compile("[ ]*([1-9])[/](\\d+)[ ]*").matcher(strings[i]);
                        if (matcher.find()) {
                            if (Integer.parseInt(matcher.group(1)) != i + 1) {
                                bfReader.close();
                                throw new FileGrammerException("�����δ����˳��", 25);
                            }
                            for (int j = 0; j < Integer.parseInt(matcher.group(2)); j++) {
                                PhysicalObject physicalObject = new PhysicalObject();
                                physicalObject.setTrackRadius(i + 1);
                                addPhysicalObject(physicalObject);
                            }
                        }
                    }
                } else {
                    if (!lineString.equals("")) {
                        bfReader.close();
                        throw new FileGrammerException("�Ƿ��У�����Ӧ�������", 20);
                    }
                }
            }
            if (trackAct != trackNum) {
                bfReader.close();
                throw new DependencyException("�ļ����������Ͳ����������һ��", 24, "" + trackAct);
            }
            if (count != 3) {
                bfReader.close();
                throw new FileGrammerException("ȱ����Ϣ��", 20);
            }
            bfReader.close();
            sortTrack();
        } catch (FileGrammerException e) {
            throw new FileChooseException("�ļ��﷨����������Ҫ����ѡ���ļ���" + e.getMessage());
        } catch (DependencyException e) {
            throw new FileChooseException("������ϵ������Ҫ����ѡ���ļ���" + e.getMessage());
        }
    }

    private static String logIn(String message) {
        logger.severe(message);
        return "�ѽ�assert������Ϣ��������־�ļ���";
    }

}
