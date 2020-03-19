/*
 * Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course
 * staff.
 */
package P4.twitter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *               list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
        Instant early = tweets.get(0).getTimestamp();
        Instant late = tweets.get(0).getTimestamp();
        for (int i = 0; i < tweets.size(); i++) {
            if (late.isBefore(tweets.get(i).getTimestamp())) {//�ҵ������ʱ��
                late = tweets.get(i).getTimestamp();
            }
            if (early.isAfter(tweets.get(i).getTimestamp())) {//֪���������ʱ��
                early = tweets.get(i).getTimestamp();
            }
        }
        Timespan res = new Timespan(early, late);
        return res;
    }

    public static boolean judgeLegalChar(char ch) {//�ж��ַ��Ƿ����û����Ϸ��ַ�
        boolean condition1 = (ch >= 'a' && ch <= 'z');
        boolean condition2 = (ch >= 'A' && ch <= 'Z');
        boolean condition3 = (ch >= '0' && ch <= '9');
        boolean condition4 = (ch == '_' || ch == '-');
        return (condition1 || condition2 || condition3 || condition4);
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *               list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by
     *         any character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */

    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        Set<String> userNames = new HashSet<>();
        int count = tweets.size();
        String s = null;// ���ڴ���һ��text
        for (int i = 0; i < count; i++) {
            s = tweets.get(i).getText();
            int k = -1;// ���ڼ�¼һƪtweet��@��λ��
            int l = 0;// ���ڼ�¼�û���ĩβλ��
            do {
                k = s.indexOf("@", k + 1);// ��ȡ@��λ��
                l = k + 1;
                if (k == -1) {// ��δ�ҵ�����ֱ�ӷ���
                    break;
                }//�ж��Ƿ������û����ĺϷ�����
                if ((k == 0 || !judgeLegalChar(s.charAt(k - 1))) && judgeLegalChar(s.charAt(k + 1))) {
                    while (judgeLegalChar(s.charAt(l))) {
                        if(l == s.length()-1) {
                            l++;
                            break;
                        }
                        l++;
                    }//�����ִ�Сд���ض�ԭ�����ִ���������뷵��ֵ�С�
                    userNames.add(s.substring(k + 1, l).toLowerCase());
                }
            } while (k != -1);
        }
        return userNames;
    }

}
