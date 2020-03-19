package P4.twitter;

import static org.junit.Assert.assertEquals;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
// ����һ���������SocialNetworkTest���У���������Ե�������
import org.junit.Test;

public class MySocialNetworkTest {

    @Test public void test() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        Map<String, Set<String>> resultMap = new HashMap<>();
        Instant d = Instant.parse("2016-02-17T10:00:00Z");
        Tweet tweet1 = new Tweet(1, "a", "@b", d);
        Tweet tweet2 = new Tweet(2, "b", "@c", d);
        Tweet tweet3 = new Tweet(3, "c", "@d", d);
        Tweet tweet4 = new Tweet(4, "d", "", d);
        // ���Ȳ���follow�����˴���
        resultMap.put("a", Set.of("b", "c"));
        resultMap.put("b", Set.of("c", "d"));
        resultMap.put("c", Set.of("d"));
        resultMap.put("d", Set.of());
        followsGraph = MySocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2, tweet3, tweet4));
        assertEquals(resultMap, followsGraph);
        // �ٲ���@follow�Լ������
        Tweet tweet5 = new Tweet(5, "e", "@e", d);
        resultMap.clear();
        resultMap.put("e", Set.of());
        followsGraph = MySocialNetwork.guessFollowsGraph(Arrays.asList(tweet5));
        assertEquals(resultMap, followsGraph);
    }

}
