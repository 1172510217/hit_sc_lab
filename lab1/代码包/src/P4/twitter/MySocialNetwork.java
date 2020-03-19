package P4.twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MySocialNetwork {

    public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        Set<String> allUserNames = new HashSet<>();
        List<Tweet> writtenBy = new ArrayList<>();
        Set<String> followNames = new HashSet<>();
        for (Tweet temp : tweets) {
            allUserNames.add(temp.getAuthor().toLowerCase());
        }
        for (String temp : allUserNames) {
            writtenBy = Filter.writtenBy(tweets, temp);
            followNames = Extract.getMentionedUsers(writtenBy);
            followNames.remove(temp);
            followsGraph.put(temp, followNames);
        } // ����Ϊ��ͨ�Ĳ²���ϵ����
        for (String temp : followsGraph.keySet()) {
            Set<String> mySocilSet = new HashSet<>();
            for (String follow : followsGraph.get(temp)) {
                mySocilSet = followsGraph.get(temp);
                mySocilSet.addAll(followsGraph.get(follow));//��������follow�����follow����
            }
            mySocilSet.remove(temp);//ͬʱҪ���Լ��Ƴ�follow����
            followsGraph.put(temp, mySocilSet);
        }//�����ǸĽ���Ĳ²���ϵ��������a׷��b��b׷��c����a׷��c��
        return followsGraph;
    }
}
