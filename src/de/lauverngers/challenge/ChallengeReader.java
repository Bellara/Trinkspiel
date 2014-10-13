package de.lauverngers.challenge;

import com.sun.tools.internal.jxc.apt.Const;
import com.sun.tools.javac.code.Attribute;
import de.lauverngers.Constants;
import de.lauverngers.objects.Challenge;
import de.lauverngers.objects.Die;
import org.apache.commons.lang3.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChallengeReader {

    public static List<Challenge> readChallengesFromFile() throws IOException {
        return readChallengesFromFile(Constants.CHALLENGE_FILE_PATH);
    }

    public static List<Challenge> readChallengesFromFile(String path) throws IOException {
        final List<Challenge> challengeList = new ArrayList();
        final File xmlFile = new File(path);

        if (xmlFile.exists() && xmlFile.isFile() && xmlFile.canRead() && xmlFile.length() > 0) {
            try {
                final SAXBuilder builder = new SAXBuilder();
                final Document document = builder.build(xmlFile);
                final Element rootElement = document.getRootElement();

                final List<Element> challengeElements = rootElement.getChildren(Constants.CHALLENGE);

                for (int i = 0; i < challengeElements.size(); i++) {
                    final Element element = challengeElements.get(i);

                    final String title = element.getAttributeValue(Constants.TITLE);
                    final String text = element.getText();

                    //when points are NULL roll a die
                    final String pointString = element.getAttributeValue(Constants.CREDITS);
                    Integer points = null;
                    if (StringUtils.isNotEmpty(pointString)) {
                        points = Integer.valueOf(pointString);
                    }

                    final String action = element.getAttributeValue(Constants.ACTION);


                    final String lifeTimeString = element.getAttributeValue(Constants.ROUND);
                    Integer lifeTime = null;
                    if (StringUtils.isNotEmpty(lifeTimeString)) {
                        if (Constants.PLACE_HOLDER_STRING.equals(lifeTimeString)) {
                            lifeTime = new Integer(0);
                        } else {
                            lifeTime = Integer.valueOf(lifeTimeString);
                        }
                    }


                    Integer timer = null;
                    if (Constants.TIMER_ACTION.equals(action)) {
                        final String timerString = element.getAttributeValue(Constants.TIMER);
                        if(StringUtils.isNotEmpty(timerString) && StringUtils.isNumeric(timerString)) {
                            timer = Integer.valueOf(timerString);
                        }
                    }

                    challengeList.add(new Challenge(i, title, text, points, lifeTime, timer, action));

                }
            } catch (JDOMException e) {
                throw new RuntimeException(e);
            }
        }

        return challengeList;
    }

}
