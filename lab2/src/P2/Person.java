package P2;

import java.util.HashSet;
import java.util.Set;


public class Person {

    private String name;
    private static Set<String> allName = new HashSet<>();// ���ڴ������е����֣��ж��Ƿ�Υ������Ψһԭ��

    /**
     * �½�һ��Person����
     * 
     * @param name �ö��������
     */
    public Person(String name) {
        this.name = name;// person����
        if(Person.allName.contains(name)) {
            System.out.println("Error, everyone should have an unique name!");
            System.exit(-1);
        }
        Person.allName.add(name);
    }

    /**
     * �õ��˶����string����
     * 
     * @return ����string����
     */
    public String getName() {
        return this.name;// �õ�person����
    }
}
